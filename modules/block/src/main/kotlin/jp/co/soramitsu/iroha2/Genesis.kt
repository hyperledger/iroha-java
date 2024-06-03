package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.ExecutorMode
import jp.co.soramitsu.iroha2.generated.Expression
import jp.co.soramitsu.iroha2.generated.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.InstructionExpr
import jp.co.soramitsu.iroha2.generated.InstructionExpr.Register
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.NewAccount
import jp.co.soramitsu.iroha2.generated.NewAssetDefinition
import jp.co.soramitsu.iroha2.generated.NewDomain
import jp.co.soramitsu.iroha2.generated.RawGenesisBlock
import jp.co.soramitsu.iroha2.generated.RegisterExpr
import jp.co.soramitsu.iroha2.generated.RegistrableBox
import jp.co.soramitsu.iroha2.generated.Value
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

/**
 * Genesis block is used to initialise a blockchain
 */
open class Genesis(open val block: RawGenesisBlock) {
    /**
     * Write genesis to file
     */
    fun writeToFile(path: Path): Path = Files.write(
        path,
        asJson().toByteArray(Charsets.UTF_8),
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.CREATE,
    )

    /**
     * Represent genesis as JSON
     */
    fun asJson(): String = JSON_SERDE.writeValueAsString(this.block)

    companion object {

        val executorMode = this::class.java.classLoader.getResource("executor.wasm")
            ?.let { ExecutorMode.Path("executor.wasm") }
            ?: throw IrohaSdkException("executor.wasm not found")

        /**
         * Return empty genesis
         */
        fun getEmpty() = Genesis(RawGenesisBlock(listOf(listOf()), executorMode))

        /**
         * List of genesis blocks to single block with unique instructions
         */
        fun List<Genesis>.toSingle(): Genesis {
            val uniqueIsi: MutableSet<InstructionExpr> = mutableSetOf()
            this.forEach { genesis ->
                uniqueIsi.addAll(genesis.block.transactions.flatten())
            }

            return Genesis(RawGenesisBlock(listOf(uniqueIsi.mergeMetadata()), executorMode))
        }

        private fun MutableSet<InstructionExpr>.mergeMetadata(): List<InstructionExpr> {
            // entity id to its metadata
            val metadataMap = mutableMapOf<Any, Metadata>()

            // only for InstructionExpr.Register
            this.extractIdentifiableBoxes().forEach { idBox ->
                metadataMap.putMergedMetadata(idBox)
            }
            this.findIsiToReplace(metadataMap).forEach { (idToMetadata, toReplace) ->
                toReplace.forEach { isi -> this.remove(isi) }

                val idBox = toReplace.first().extractIdentifiableBox()
                val registrableBox = idBox?.toRegisterBox(idToMetadata.second)
                    ?: throw RuntimeException("IdentifiableBox shouldn't be null")
                this.add(Register(RegisterExpr(registrableBox.evaluatesTo())))
            }

            return this.sorted()
        }

        private fun MutableSet<InstructionExpr>.sorted() = this.sortedWith(
            compareByDescending { instruction ->
                when (instruction) {
                    is Register -> when (instruction.extractIdentifiableBox()) {
                        is IdentifiableBox.NewDomain -> 5
                        is IdentifiableBox.NewAccount -> 4
                        is IdentifiableBox.NewAssetDefinition -> 3
                        is IdentifiableBox.NewRole -> 2
                        else -> 1
                    }

                    else -> -1
                }
            },
        )

        private fun IdentifiableBox.toRegisterBox(metadata: Metadata) = when (this) {
            is IdentifiableBox.NewAccount -> RegistrableBox.Account(
                NewAccount(this.newAccount.id, this.newAccount.signatories, metadata),
            )

            is IdentifiableBox.NewDomain -> RegistrableBox.Domain(
                NewDomain(this.newDomain.id, this.newDomain.logo, metadata),
            )

            is IdentifiableBox.NewAssetDefinition -> RegistrableBox.AssetDefinition(
                NewAssetDefinition(
                    this.newAssetDefinition.id,
                    this.newAssetDefinition.valueType,
                    this.newAssetDefinition.mintable,
                    metadata = metadata,
                ),
            )

            else -> throw IrohaSdkException("Unexpected type ${this::class}")
        }

        private fun MutableMap<Any, Metadata>.putMergedMetadata(idBox: IdentifiableBox) {
            fun MutableMap<Any, Metadata>.putOrMerge(
                id: Any,
                metadata: Metadata,
            ) = when (val value = this[id]) {
                null -> this[id] = metadata
                else -> {
                    metadata.map.forEach { (k, v) ->
                        if (value.map.containsKey(k) && value.map[k] != v) {
                            throw IrohaSdkException("Value for this key is already set in metadata")
                        }
                    }
                    this[id] = this[id]!!.merge(metadata)
                }
            }

            when (idBox) {
                is IdentifiableBox.NewAccount -> this.putOrMerge(idBox.newAccount.id, idBox.newAccount.metadata)
                is IdentifiableBox.NewDomain -> this.putOrMerge(idBox.newDomain.id, idBox.newDomain.metadata)
                is IdentifiableBox.NewAssetDefinition -> this.putOrMerge(
                    idBox.newAssetDefinition.id,
                    idBox.newAssetDefinition.metadata,
                )

                else -> {}
            }
        }

        private fun MutableSet<InstructionExpr>.findIsiToReplace(
            metadata: Map<Any, Metadata>,
        ): MutableMap<Pair<Any, Metadata>, MutableList<Register>> {
            val isiToReplace =
                mutableMapOf<Pair<Any, Metadata>, MutableList<Register>>()

            this.forEach { instruction ->
                runCatching {
                    instruction.cast<Register>()
                        .registerExpr.`object`.expression
                        .cast<Expression.Raw>().value
                        .cast<Value.Identifiable>().identifiableBox
                }.onSuccess { idBox ->
                    when (idBox) {
                        is IdentifiableBox.NewAccount -> {
                            val id = idBox.newAccount.id
                            id to metadata[id]!!
                        }
                        is IdentifiableBox.NewDomain -> {
                            val id = idBox.newDomain.id
                            id to metadata[id]!!
                        }
                        is IdentifiableBox.NewAssetDefinition -> {
                            val id = idBox.newAssetDefinition.id
                            id to metadata[id]!!
                        }
                        else -> null
                    }?.takeIf { it.second.map.isNotEmpty() }
                        ?.let {
                            isiToReplace.merge(it, mutableListOf(instruction.cast())) { old, new ->
                                old.plus(new).toMutableList()
                            }
                        }
                }
            }
            return isiToReplace
        }
    }
}
