//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

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
 * IdBox
 *
 * Generated from 'iroha_data_model::IdBox' enum
 */
public sealed class IdBox : ModelEnum {
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
     * 'DomainId' variant
     */
    public data class DomainId(
        public val id: jp.co.soramitsu.iroha2.generated.datamodel.domain.Id
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<DomainId>, ScaleWriter<DomainId> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): DomainId = try {
                DomainId(
                    jp.co.soramitsu.iroha2.generated.datamodel.domain.Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: DomainId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.domain.Id.write(writer, instance.id)
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
     * 'RoleId' variant
     */
    public data class RoleId(
        public val id: jp.co.soramitsu.iroha2.generated.datamodel.role.Id
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<RoleId>, ScaleWriter<RoleId> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): RoleId = try {
                RoleId(
                    jp.co.soramitsu.iroha2.generated.datamodel.role.Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: RoleId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.role.Id.write(writer, instance.id)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TriggerId' variant
     */
    public data class TriggerId(
        public val id: jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<TriggerId>, ScaleWriter<TriggerId> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): TriggerId = try {
                TriggerId(
                    jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TriggerId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id.write(writer, instance.id)
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
            public const val DISCRIMINANT: Int = 7

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
                reader.readUByte().toInt()
        ) {
            0 -> AccountId.read(reader)
            1 -> AssetId.read(reader)
            2 -> AssetDefinitionId.read(reader)
            3 -> DomainId.read(reader)
            4 -> PeerId.read(reader)
            5 -> RoleId.read(reader)
            6 -> TriggerId.read(reader)
            7 -> WorldId.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: IdBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> AccountId.write(writer, instance as AccountId)
                1 -> AssetId.write(writer, instance as AssetId)
                2 -> AssetDefinitionId.write(writer, instance as AssetDefinitionId)
                3 -> DomainId.write(writer, instance as DomainId)
                4 -> PeerId.write(writer, instance as PeerId)
                5 -> RoleId.write(writer, instance as RoleId)
                6 -> TriggerId.write(writer, instance as TriggerId)
                7 -> WorldId.write(writer, instance as WorldId)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
