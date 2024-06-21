package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.NewAccount
import jp.co.soramitsu.iroha2.generated.NewAssetDefinition
import jp.co.soramitsu.iroha2.generated.NewDomain
import jp.co.soramitsu.iroha2.generated.RegisterBox
import jp.co.soramitsu.iroha2.generated.RegisterOfAccount
import jp.co.soramitsu.iroha2.generated.RegisterOfAssetDefinition
import jp.co.soramitsu.iroha2.generated.RegisterOfDomain
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

/**
 * Genesis block is used to initialise a blockchain
 */
open class Genesis(open val block: RawGenesisBlockFile) {
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

        const val EXECUTOR_FILE_NAME = "executor.wasm"

        /**
         * List of genesis blocks to single block with unique instructions
         */
        fun List<Genesis>.toSingle(): Genesis {
            val uniqueIsi: MutableSet<InstructionBox> = mutableSetOf()

            this.forEach { genesis ->
                uniqueIsi.addAll(genesis.block.transactions.map { tx -> tx.isi }.flatten())
            }

            return Genesis(
                RawGenesisBlockFile(
                    listOf(GenesisTransactionBuilder(uniqueIsi.mergeMetadata())),
                    EXECUTOR_FILE_NAME,
                ),
            )
        }

        private fun MutableSet<InstructionBox>.mergeMetadata(): List<InstructionBox> {
            // entity id to its metadata
            val metadataMap = mutableMapOf<Any, Metadata>()

            // only for InstructionBox.Register
            this.extractIdentifiableBoxes().forEach { idBox ->
                metadataMap.putMergedMetadata(idBox)
            }
            this.findIsiToReplace(metadataMap).forEach { (idToMetadata, toReplace) ->
                toReplace.forEach { isi -> this.remove(isi) }

                val idBox = toReplace.first().extractIdentifiableBox()
                val registerBox = idBox?.toRegisterBox(idToMetadata.second)
                    ?: throw RuntimeException("IdentifiableBox shouldn't be null")
                this.add(InstructionBox.Register(registerBox))
            }

            return this.sorted()
        }

        private fun MutableSet<InstructionBox>.sorted() = this.sortedWith(
            compareByDescending { instruction ->
                when (instruction) {
                    is InstructionBox.Register -> when (instruction.cast<InstructionBox.Register>().extractIdentifiableBox()) {
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
            is IdentifiableBox.NewAccount -> RegisterBox.Account(
                RegisterOfAccount(
                    NewAccount(this.newAccount.id, metadata),
                ),
            )

            is IdentifiableBox.NewDomain -> RegisterBox.Domain(
                RegisterOfDomain(
                    NewDomain(this.newDomain.id, this.newDomain.logo, metadata),
                ),
            )

            is IdentifiableBox.NewAssetDefinition -> RegisterBox.AssetDefinition(
                RegisterOfAssetDefinition(
                    NewAssetDefinition(
                        this.newAssetDefinition.id,
                        this.newAssetDefinition.valueType,
                        this.newAssetDefinition.mintable,
                        metadata = metadata,
                    ),
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
                    metadata.sortedMapOfName.forEach { (k, v) ->
                        if (value.sortedMapOfName.containsKey(k) && value.sortedMapOfName[k] != v) {
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

        private fun MutableSet<InstructionBox>.findIsiToReplace(
            metadata: Map<Any, Metadata>,
        ): MutableMap<Pair<Any, Metadata>, MutableList<InstructionBox.Register>> {
            val isiToReplace =
                mutableMapOf<Pair<Any, Metadata>, MutableList<InstructionBox.Register>>()

            this.forEach { instruction ->
                runCatching {
                    instruction.cast<InstructionBox.Register>().extractIdentifiableBox()
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
                    }?.takeIf { it.second.sortedMapOfName.isNotEmpty() }
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
