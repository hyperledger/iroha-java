package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.core.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.RegistrableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount
import jp.co.soramitsu.iroha2.generated.datamodel.asset.NewAssetDefinition
import jp.co.soramitsu.iroha2.generated.datamodel.domain.NewDomain
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.isi.RegisterBox
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

/**
 * Genesis block is used to initialise a blockchain
 */
open class Genesis(open val genesisBlock: RawGenesisBlock) {

    /**
     * Write genesis to file
     */
    fun writeToFile(path: Path): Path = Files.write(
        path,
        asJson().toByteArray(Charsets.UTF_8),
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.CREATE
    )

    /**
     * Represent genesis as JSON
     */
    fun asJson(): String = JSON_SERDE.writeValueAsString(this.genesisBlock)

    companion object {
        /**
         * List of genesis blocks to single block with unique instructions
         */
        fun List<Genesis>.toSingle(): Genesis {
            val uniqueIsi: MutableSet<Instruction> = mutableSetOf()
            this.forEach { genesis ->
                uniqueIsi.addAll(genesis.genesisBlock.transactions.map { it.isi }.flatten())
            }

            return Genesis(RawGenesisBlock(listOf(GenesisTransaction(uniqueIsi.mergeMetadata()))))
        }

        /**
         * Return empty genesis
         */
        fun getEmpty() = Genesis(RawGenesisBlock(listOf(GenesisTransaction(listOf()))))

        private fun MutableSet<Instruction>.mergeMetadata(): List<Instruction> {
            val metadataMap = mutableMapOf<Any, Metadata>()

            this.extractIdentifiableBoxes().forEach { idBox ->
                metadataMap.putMergedMetadata(idBox)
            }
            this.findIsiToReplace(metadataMap).forEach { (metadata, toReplace) ->
                toReplace.forEach { isi -> this.remove(isi) }

                val idBox = toReplace.first().extractIdentifiableBox()
                val registrableBox = idBox.toRegisterBox(metadata)
                this.add(Instruction.Register(RegisterBox(registrableBox.evaluatesTo())))
            }

            return this.sortedWith(compareBy { it !is Instruction.Register })
        }

        private fun IdentifiableBox.toRegisterBox(metadata: Metadata) = when (this) {
            is IdentifiableBox.NewAccount -> RegistrableBox.Account(
                NewAccount(this.newAccount.id, this.newAccount.signatories, metadata)
            )

            is IdentifiableBox.NewDomain -> RegistrableBox.Domain(
                NewDomain(this.newDomain.id, this.newDomain.logo, metadata)
            )

            is IdentifiableBox.NewAssetDefinition -> RegistrableBox.AssetDefinition(
                NewAssetDefinition(
                    this.newAssetDefinition.id,
                    this.newAssetDefinition.valueType,
                    this.newAssetDefinition.mintable,
                    metadata
                )
            )

            else -> throw IrohaSdkException("Unexpected type ${this::class}")
        }

        private fun MutableMap<Any, Metadata>.putMergedMetadata(idBox: IdentifiableBox) {
            fun MutableMap<Any, Metadata>.putOrMerge(id: Any, metadata: Metadata) = when (this[id]) {
                null -> this[id] = metadata
                else -> this[id] = this[id]!!.merge(metadata)
            }

            when (idBox) {
                is IdentifiableBox.NewAccount -> this.putOrMerge(idBox.newAccount.id, idBox.newAccount.metadata)
                is IdentifiableBox.NewDomain -> this.putOrMerge(idBox.newDomain.id, idBox.newDomain.metadata)
                is IdentifiableBox.NewAssetDefinition -> this.putOrMerge(
                    idBox.newAssetDefinition.id,
                    idBox.newAssetDefinition.metadata
                )

                else -> {}
            }
        }

        private fun MutableSet<Instruction>.findIsiToReplace(
            metadata: Map<Any, Metadata>
        ): MutableMap<Metadata, MutableList<Instruction.Register>> {
            val isiToReplace = mutableMapOf<Metadata, MutableList<Instruction.Register>>()

            this.forEach { instruction ->
                runCatching {
                    instruction.cast<Instruction.Register>()
                        .registerBox.`object`.expression
                        .cast<Expression.Raw>().value
                        .cast<Value.Identifiable>().identifiableBox
                }.onSuccess { idBox ->
                    when (idBox) {
                        is IdentifiableBox.NewAccount -> metadata[idBox.newAccount.id]
                        is IdentifiableBox.NewDomain -> metadata[idBox.newDomain.id]
                        is IdentifiableBox.NewAssetDefinition -> metadata[idBox.newAssetDefinition.id]
                        else -> null
                    }?.takeIf { it.map.isNotEmpty() }?.let { metadata ->
                        isiToReplace.merge(metadata, mutableListOf(instruction.cast())) { old, new ->
                            old.plus(new).toMutableList()
                        }
                    }
                }
            }
            return isiToReplace
        }
    }
}
