//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.smartcontracts.isi.error

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.core.block.VersionedCommittedBlock
import jp.co.soramitsu.iroha2.generated.crypto.hash.HashOf
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int
import kotlin.String

/**
 * FindError
 *
 * Generated from 'iroha_core::smartcontracts::isi::error::FindError' enum
 */
public sealed class FindError : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Asset' variant
     */
    public data class Asset(
        public val id: Id
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Asset>, ScaleWriter<Asset> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Asset = try {
                Asset(
                    Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Asset) = try {
                Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AssetDefinition' variant
     */
    public data class AssetDefinition(
        public val definitionId: DefinitionId
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetDefinition>, ScaleWriter<AssetDefinition> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): AssetDefinition = try {
                AssetDefinition(
                    DefinitionId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetDefinition) = try {
                DefinitionId.write(writer, instance.definitionId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Account' variant
     */
    public data class Account(
        public val id: jp.co.soramitsu.iroha2.generated.datamodel.account.Id
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Account>, ScaleWriter<Account> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Account = try {
                Account(
                    jp.co.soramitsu.iroha2.generated.datamodel.account.Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Account) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.account.Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Domain' variant
     */
    public data class Domain(
        public val id: jp.co.soramitsu.iroha2.generated.datamodel.domain.Id
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Domain>, ScaleWriter<Domain> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Domain = try {
                Domain(
                    jp.co.soramitsu.iroha2.generated.datamodel.domain.Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Domain) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.domain.Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MetadataKey' variant
     */
    public data class MetadataKey(
        public val name: Name
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MetadataKey>, ScaleWriter<MetadataKey> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): MetadataKey = try {
                MetadataKey(
                    Name.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: MetadataKey) = try {
                Name.write(writer, instance.name)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Block' variant
     */
    public data class Block(
        public val hashOf: HashOf<VersionedCommittedBlock>
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Block>, ScaleWriter<Block> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): Block = try {
                Block(
                    HashOf.read(reader) as HashOf<VersionedCommittedBlock>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Block) = try {
                HashOf.write(writer, instance.hashOf)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Transaction' variant
     */
    public data class Transaction(
        public val hashOf: HashOf<VersionedTransaction>
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): Transaction = try {
                Transaction(
                    HashOf.read(reader) as HashOf<VersionedTransaction>,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Transaction) = try {
                HashOf.write(writer, instance.hashOf)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Context' variant
     */
    public data class Context(
        public val string: String
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Context>, ScaleWriter<Context> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): Context = try {
                Context(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Context) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Peer' variant
     */
    public data class Peer(
        public val id: jp.co.soramitsu.iroha2.generated.datamodel.peer.Id
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Peer>, ScaleWriter<Peer> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): Peer = try {
                Peer(
                    jp.co.soramitsu.iroha2.generated.datamodel.peer.Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Peer) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.peer.Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Trigger' variant
     */
    public data class Trigger(
        public val id: jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Trigger>, ScaleWriter<Trigger> {
            public const val DISCRIMINANT: Int = 9

            public override fun read(reader: ScaleCodecReader): Trigger = try {
                Trigger(
                    jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Trigger) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Role' variant
     */
    public data class Role(
        public val id: jp.co.soramitsu.iroha2.generated.datamodel.role.Id
    ) : FindError() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Role>, ScaleWriter<Role> {
            public const val DISCRIMINANT: Int = 10

            public override fun read(reader: ScaleCodecReader): Role = try {
                Role(
                    jp.co.soramitsu.iroha2.generated.datamodel.role.Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Role) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.role.Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<FindError>, ScaleWriter<FindError> {
        public override fun read(reader: ScaleCodecReader): FindError = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Asset.read(reader)
            1 -> AssetDefinition.read(reader)
            2 -> Account.read(reader)
            3 -> Domain.read(reader)
            4 -> MetadataKey.read(reader)
            5 -> Block.read(reader)
            6 -> Transaction.read(reader)
            7 -> Context.read(reader)
            8 -> Peer.read(reader)
            9 -> Trigger.read(reader)
            10 -> Role.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: FindError) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Asset.write(writer, instance as Asset)
                1 -> AssetDefinition.write(writer, instance as AssetDefinition)
                2 -> Account.write(writer, instance as Account)
                3 -> Domain.write(writer, instance as Domain)
                4 -> MetadataKey.write(writer, instance as MetadataKey)
                5 -> Block.write(writer, instance as Block)
                6 -> Transaction.write(writer, instance as Transaction)
                7 -> Context.write(writer, instance as Context)
                8 -> Peer.write(writer, instance as Peer)
                9 -> Trigger.write(writer, instance as Trigger)
                10 -> Role.write(writer, instance as Role)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
