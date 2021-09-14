//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int
import kotlin.String

/**
 * IdBox
 *
 * Generated from 'iroha_data_model::IdBox' enum
 */
public sealed class IdBox {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'AccountId' variant
     */
    public data class AccountId(
        public val id: Id
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AccountId>, ScaleWriter<AccountId> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): AccountId = try {
                AccountId(
                    Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AccountId) = try {
                Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AssetId' variant
     */
    public data class AssetId(
        public val id: jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetId>, ScaleWriter<AssetId> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): AssetId = try {
                AssetId(
                    jp.co.soramitsu.iroha2.generated.datamodel.asset.Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.asset.Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AssetDefinitionId' variant
     */
    public data class AssetDefinitionId(
        public val definitionId: DefinitionId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetDefinitionId>, ScaleWriter<AssetDefinitionId> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): AssetDefinitionId = try {
                AssetDefinitionId(
                    DefinitionId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionId) = try {
                DefinitionId.write(writer, instance.definitionId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'DomainName' variant
     */
    public data class DomainName(
        public val string: String
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<DomainName>, ScaleWriter<DomainName> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): DomainName = try {
                DomainName(
                    reader.readString(),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: DomainName) = try {
                writer.writeAsList(instance.string.toByteArray(Charsets.UTF_8))
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PeerId' variant
     */
    public data class PeerId(
        public val id: jp.co.soramitsu.iroha2.generated.datamodel.peer.Id
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PeerId>, ScaleWriter<PeerId> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): PeerId = try {
                PeerId(
                    jp.co.soramitsu.iroha2.generated.datamodel.peer.Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PeerId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.peer.Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'WorldId' variant
     */
    public class WorldId : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<WorldId>, ScaleWriter<WorldId> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): WorldId = try {
                WorldId()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: WorldId) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<IdBox>, ScaleWriter<IdBox> {
        public override fun read(reader: ScaleCodecReader): IdBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> AccountId.read(reader)
            1 -> AssetId.read(reader)
            2 -> AssetDefinitionId.read(reader)
            3 -> DomainName.read(reader)
            4 -> PeerId.read(reader)
            5 -> WorldId.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: IdBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> AccountId.write(writer, instance as AccountId)
                1 -> AssetId.write(writer, instance as AssetId)
                2 -> AssetDefinitionId.write(writer, instance as AssetDefinitionId)
                3 -> DomainName.write(writer, instance as DomainName)
                4 -> PeerId.write(writer, instance as PeerId)
                5 -> WorldId.write(writer, instance as WorldId)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
