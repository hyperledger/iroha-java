//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * EntityFilter
 *
 * Generated from 'iroha_data_model::events::data::EntityFilter' enum
 */
public sealed class EntityFilter : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Account' variant
     */
    public data class Account(
        public val option: Id?
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Account>, ScaleWriter<Account> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Account = try {
                Account(
                    reader.readNullable(Id),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Account) = try {
                writer.writeNullable(Id, instance.option)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AssetDefinition' variant
     */
    public data class AssetDefinition(
        public val option: DefinitionId?
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetDefinition>, ScaleWriter<AssetDefinition> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): AssetDefinition = try {
                AssetDefinition(
                    reader.readNullable(DefinitionId),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetDefinition) = try {
                writer.writeNullable(DefinitionId, instance.option)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Asset' variant
     */
    public data class Asset(
        public val option: jp.co.soramitsu.iroha2.generated.datamodel.asset.Id?
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Asset>, ScaleWriter<Asset> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Asset = try {
                Asset(
                    reader.readNullable(jp.co.soramitsu.iroha2.generated.datamodel.asset.Id),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Asset) = try {
                writer.writeNullable(jp.co.soramitsu.iroha2.generated.datamodel.asset.Id, instance.option)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Domain' variant
     */
    public data class Domain(
        public val option: jp.co.soramitsu.iroha2.generated.datamodel.domain.Id?
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Domain>, ScaleWriter<Domain> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Domain = try {
                Domain(
                    reader.readNullable(jp.co.soramitsu.iroha2.generated.datamodel.domain.Id),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Domain) = try {
                writer.writeNullable(jp.co.soramitsu.iroha2.generated.datamodel.domain.Id, instance.option)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Peer' variant
     */
    public data class Peer(
        public val option: jp.co.soramitsu.iroha2.generated.datamodel.peer.Id?
    ) : EntityFilter() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Peer>, ScaleWriter<Peer> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): Peer = try {
                Peer(
                    reader.readNullable(jp.co.soramitsu.iroha2.generated.datamodel.peer.Id),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Peer) = try {
                writer.writeNullable(jp.co.soramitsu.iroha2.generated.datamodel.peer.Id, instance.option)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<EntityFilter>, ScaleWriter<EntityFilter> {
        public override fun read(reader: ScaleCodecReader): EntityFilter = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Account.read(reader)
            1 -> AssetDefinition.read(reader)
            2 -> Asset.read(reader)
            3 -> Domain.read(reader)
            4 -> Peer.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: EntityFilter) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Account.write(writer, instance as Account)
                1 -> AssetDefinition.write(writer, instance as AssetDefinition)
                2 -> Asset.write(writer, instance as Asset)
                3 -> Domain.write(writer, instance as Domain)
                4 -> Peer.write(writer, instance as Peer)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
