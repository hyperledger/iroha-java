//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * IdBox
 *
 * Generated from 'IdBox' enum
 */
public sealed class IdBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'DomainId' variant
     */
    public data class DomainId(
        public val domainId: jp.co.soramitsu.iroha2.generated.DomainId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<DomainId>, ScaleWriter<DomainId> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): DomainId = try {
                DomainId(
                    jp.co.soramitsu.iroha2.generated.DomainId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: DomainId) = try {
                jp.co.soramitsu.iroha2.generated.DomainId.write(writer, instance.domainId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AccountId' variant
     */
    public data class AccountId(
        public val accountId: jp.co.soramitsu.iroha2.generated.AccountId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AccountId>, ScaleWriter<AccountId> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): AccountId = try {
                AccountId(
                    jp.co.soramitsu.iroha2.generated.AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AccountId) = try {
                jp.co.soramitsu.iroha2.generated.AccountId.write(writer, instance.accountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AssetDefinitionId' variant
     */
    public data class AssetDefinitionId(
        public val assetDefinitionId: jp.co.soramitsu.iroha2.generated.AssetDefinitionId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetDefinitionId>, ScaleWriter<AssetDefinitionId> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): AssetDefinitionId = try {
                AssetDefinitionId(
                    jp.co.soramitsu.iroha2.generated.AssetDefinitionId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionId) = try {
                jp.co.soramitsu.iroha2.generated.AssetDefinitionId.write(writer, instance.assetDefinitionId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AssetId' variant
     */
    public data class AssetId(
        public val assetId: jp.co.soramitsu.iroha2.generated.AssetId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetId>, ScaleWriter<AssetId> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): AssetId = try {
                AssetId(
                    jp.co.soramitsu.iroha2.generated.AssetId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetId) = try {
                jp.co.soramitsu.iroha2.generated.AssetId.write(writer, instance.assetId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PeerId' variant
     */
    public data class PeerId(
        public val peerId: jp.co.soramitsu.iroha2.generated.PeerId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PeerId>, ScaleWriter<PeerId> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): PeerId = try {
                PeerId(
                    jp.co.soramitsu.iroha2.generated.PeerId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PeerId) = try {
                jp.co.soramitsu.iroha2.generated.PeerId.write(writer, instance.peerId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TriggerId' variant
     */
    public data class TriggerId(
        public val triggerId: jp.co.soramitsu.iroha2.generated.TriggerId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<TriggerId>, ScaleWriter<TriggerId> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): TriggerId = try {
                TriggerId(
                    jp.co.soramitsu.iroha2.generated.TriggerId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TriggerId) = try {
                jp.co.soramitsu.iroha2.generated.TriggerId.write(writer, instance.triggerId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RoleId' variant
     */
    public data class RoleId(
        public val roleId: jp.co.soramitsu.iroha2.generated.RoleId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<RoleId>, ScaleWriter<RoleId> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): RoleId = try {
                RoleId(
                    jp.co.soramitsu.iroha2.generated.RoleId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: RoleId) = try {
                jp.co.soramitsu.iroha2.generated.RoleId.write(writer, instance.roleId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionTokenDefinitionId' variant
     */
    public data class PermissionTokenDefinitionId(
        public val permissionTokenId: PermissionTokenId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<PermissionTokenDefinitionId>,
            ScaleWriter<PermissionTokenDefinitionId> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): PermissionTokenDefinitionId = try {
                PermissionTokenDefinitionId(
                    PermissionTokenId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PermissionTokenDefinitionId) =
                try {
                    PermissionTokenId.write(writer, instance.permissionTokenId)
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'ParameterId' variant
     */
    public data class ParameterId(
        public val parameterId: jp.co.soramitsu.iroha2.generated.ParameterId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ParameterId>, ScaleWriter<ParameterId> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): ParameterId = try {
                ParameterId(
                    jp.co.soramitsu.iroha2.generated.ParameterId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ParameterId) = try {
                jp.co.soramitsu.iroha2.generated.ParameterId.write(writer, instance.parameterId)
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
            0 -> DomainId.read(reader)
            1 -> AccountId.read(reader)
            2 -> AssetDefinitionId.read(reader)
            3 -> AssetId.read(reader)
            4 -> PeerId.read(reader)
            5 -> TriggerId.read(reader)
            6 -> RoleId.read(reader)
            7 -> PermissionTokenDefinitionId.read(reader)
            8 -> ParameterId.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: IdBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> DomainId.write(writer, instance as DomainId)
                1 -> AccountId.write(writer, instance as AccountId)
                2 -> AssetDefinitionId.write(writer, instance as AssetDefinitionId)
                3 -> AssetId.write(writer, instance as AssetId)
                4 -> PeerId.write(writer, instance as PeerId)
                5 -> TriggerId.write(writer, instance as TriggerId)
                6 -> RoleId.write(writer, instance as RoleId)
                7 -> PermissionTokenDefinitionId.write(writer, instance as PermissionTokenDefinitionId)
                8 -> ParameterId.write(writer, instance as ParameterId)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
