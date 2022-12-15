//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.TokenId
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
     * 'DomainId' variant
     */
    public data class DomainId(
        public val domainId: jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<DomainId>, ScaleWriter<DomainId> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): DomainId = try {
                DomainId(
                    jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: DomainId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId.write(writer, instance.domainId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AccountId' variant
     */
    public data class AccountId(
        public val accountId: jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AccountId>, ScaleWriter<AccountId> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): AccountId = try {
                AccountId(
                    jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AccountId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId.write(
                    writer,
                    instance.accountId
                )
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
     * 'AssetId' variant
     */
    public data class AssetId(
        public val assetId: jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetId>, ScaleWriter<AssetId> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): AssetId = try {
                AssetId(
                    jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId.write(writer, instance.assetId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PeerId' variant
     */
    public data class PeerId(
        public val peerId: jp.co.soramitsu.iroha2.generated.datamodel.peer.PeerId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PeerId>, ScaleWriter<PeerId> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): PeerId = try {
                PeerId(
                    jp.co.soramitsu.iroha2.generated.datamodel.peer.PeerId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PeerId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.peer.PeerId.write(writer, instance.peerId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'TriggerId' variant
     */
    public data class TriggerId(
        public val triggerId: jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<TriggerId>, ScaleWriter<TriggerId> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): TriggerId = try {
                TriggerId(
                    jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: TriggerId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId.write(
                    writer,
                    instance.triggerId
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RoleId' variant
     */
    public data class RoleId(
        public val roleId: jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<RoleId>, ScaleWriter<RoleId> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): RoleId = try {
                RoleId(
                    jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: RoleId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId.write(writer, instance.roleId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionTokenDefinitionId' variant
     */
    public data class PermissionTokenDefinitionId(
        public val tokenId: TokenId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<PermissionTokenDefinitionId>,
            ScaleWriter<PermissionTokenDefinitionId> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): PermissionTokenDefinitionId = try {
                PermissionTokenDefinitionId(
                    TokenId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PermissionTokenDefinitionId) =
                try {
                    TokenId.write(writer, instance.tokenId)
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'ValidatorId' variant
     */
    public data class ValidatorId(
        public val validatorId:  
            jp.co.soramitsu.iroha2.generated.datamodel.permission.validator.ValidatorId
    ) : IdBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ValidatorId>, ScaleWriter<ValidatorId> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): ValidatorId = try {
                ValidatorId(
                    jp.co.soramitsu.iroha2.generated.datamodel.permission.validator.ValidatorId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ValidatorId) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.permission.validator.ValidatorId.write(
                    writer,
                    instance.validatorId
                )
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
            8 -> ValidatorId.read(reader)
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
                8 -> ValidatorId.write(writer, instance as ValidatorId)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
