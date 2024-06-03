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
import kotlin.Unit

/**
 * AccountEvent
 *
 * Generated from 'AccountEvent' enum
 */
public sealed class AccountEvent : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Asset' variant
     */
    public data class Asset(
        public val assetEvent: AssetEvent,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.Asset>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.Asset> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.Asset = try {
                Asset(
                    AssetEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.Asset,
            ): Unit = try {
                AssetEvent.write(writer, instance.assetEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Created' variant
     */
    public data class Created(
        public val account: Account,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.Created>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.Created> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.Created = try {
                Created(
                    Account.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.Created,
            ): Unit = try {
                Account.write(writer, instance.account)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Deleted' variant
     */
    public data class Deleted(
        public val accountId: AccountId,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.Deleted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.Deleted> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.Deleted = try {
                Deleted(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.Deleted,
            ): Unit = try {
                AccountId.write(writer, instance.accountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AuthenticationAdded' variant
     */
    public data class AuthenticationAdded(
        public val accountId: AccountId,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.AuthenticationAdded>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.AuthenticationAdded> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.AuthenticationAdded = try {
                AuthenticationAdded(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.AuthenticationAdded,
            ): Unit = try {
                AccountId.write(writer, instance.accountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AuthenticationRemoved' variant
     */
    public data class AuthenticationRemoved(
        public val accountId: AccountId,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.AuthenticationRemoved>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.AuthenticationRemoved> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.AuthenticationRemoved = try {
                AuthenticationRemoved(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.AuthenticationRemoved,
            ): Unit = try {
                AccountId.write(writer, instance.accountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionAdded' variant
     */
    public data class PermissionAdded(
        public val accountPermissionChanged: AccountPermissionChanged,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.PermissionAdded>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.PermissionAdded> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.PermissionAdded = try {
                PermissionAdded(
                    AccountPermissionChanged.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.PermissionAdded,
            ): Unit = try {
                AccountPermissionChanged.write(writer, instance.accountPermissionChanged)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionRemoved' variant
     */
    public data class PermissionRemoved(
        public val accountPermissionChanged: AccountPermissionChanged,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.PermissionRemoved>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.PermissionRemoved> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.PermissionRemoved = try {
                PermissionRemoved(
                    AccountPermissionChanged.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.PermissionRemoved,
            ): Unit = try {
                AccountPermissionChanged.write(writer, instance.accountPermissionChanged)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RoleRevoked' variant
     */
    public data class RoleRevoked(
        public val accountRoleChanged: AccountRoleChanged,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.RoleRevoked>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.RoleRevoked> {
            public const val DISCRIMINANT: Int = 7

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.RoleRevoked = try {
                RoleRevoked(
                    AccountRoleChanged.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.RoleRevoked,
            ): Unit = try {
                AccountRoleChanged.write(writer, instance.accountRoleChanged)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RoleGranted' variant
     */
    public data class RoleGranted(
        public val accountRoleChanged: AccountRoleChanged,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.RoleGranted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.RoleGranted> {
            public const val DISCRIMINANT: Int = 8

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.RoleGranted = try {
                RoleGranted(
                    AccountRoleChanged.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.RoleGranted,
            ): Unit = try {
                AccountRoleChanged.write(writer, instance.accountRoleChanged)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MetadataInserted' variant
     */
    public data class MetadataInserted(
        public val metadataChangedOfAccountId: MetadataChangedOfAccountId,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.MetadataInserted>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.MetadataInserted> {
            public const val DISCRIMINANT: Int = 9

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.MetadataInserted = try {
                MetadataInserted(
                    MetadataChangedOfAccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.MetadataInserted,
            ): Unit = try {
                MetadataChangedOfAccountId.write(writer, instance.metadataChangedOfAccountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MetadataRemoved' variant
     */
    public data class MetadataRemoved(
        public val metadataChangedOfAccountId: MetadataChangedOfAccountId,
    ) : AccountEvent() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.AccountEvent.MetadataRemoved>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.AccountEvent.MetadataRemoved> {
            public const val DISCRIMINANT: Int = 10

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.AccountEvent.MetadataRemoved = try {
                MetadataRemoved(
                    MetadataChangedOfAccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.AccountEvent.MetadataRemoved,
            ): Unit = try {
                MetadataChangedOfAccountId.write(writer, instance.metadataChangedOfAccountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<AccountEvent>, ScaleWriter<AccountEvent> {
        override fun read(reader: ScaleCodecReader): AccountEvent = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Asset.read(reader)
            1 -> Created.read(reader)
            2 -> Deleted.read(reader)
            3 -> AuthenticationAdded.read(reader)
            4 -> AuthenticationRemoved.read(reader)
            5 -> PermissionAdded.read(reader)
            6 -> PermissionRemoved.read(reader)
            7 -> RoleRevoked.read(reader)
            8 -> RoleGranted.read(reader)
            9 -> MetadataInserted.read(reader)
            10 -> MetadataRemoved.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: AccountEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Asset.write(writer, instance as Asset)
                1 -> Created.write(writer, instance as Created)
                2 -> Deleted.write(writer, instance as Deleted)
                3 -> AuthenticationAdded.write(writer, instance as AuthenticationAdded)
                4 -> AuthenticationRemoved.write(writer, instance as AuthenticationRemoved)
                5 -> PermissionAdded.write(writer, instance as PermissionAdded)
                6 -> PermissionRemoved.write(writer, instance as PermissionRemoved)
                7 -> RoleRevoked.write(writer, instance as RoleRevoked)
                8 -> RoleGranted.write(writer, instance as RoleGranted)
                9 -> MetadataInserted.write(writer, instance as MetadataInserted)
                10 -> MetadataRemoved.write(writer, instance as MetadataRemoved)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
