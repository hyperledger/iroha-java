//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.account

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.asset.AssetEvent
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * AccountEvent
 *
 * Generated from 'iroha_data_model::events::data::events::account::AccountEvent' enum
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
        public val assetEvent: AssetEvent
    ) : AccountEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Asset>, ScaleWriter<Asset> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Asset = try {
                Asset(
                    AssetEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Asset) = try {
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
        public val accountId: AccountId
    ) : AccountEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Created>, ScaleWriter<Created> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Created = try {
                Created(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Created) = try {
                AccountId.write(writer, instance.accountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Deleted' variant
     */
    public data class Deleted(
        public val accountId: AccountId
    ) : AccountEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Deleted>, ScaleWriter<Deleted> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Deleted = try {
                Deleted(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Deleted) = try {
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
        public val accountId: AccountId
    ) : AccountEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AuthenticationAdded>, ScaleWriter<AuthenticationAdded> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): AuthenticationAdded = try {
                AuthenticationAdded(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AuthenticationAdded) = try {
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
        public val accountId: AccountId
    ) : AccountEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AuthenticationRemoved>, ScaleWriter<AuthenticationRemoved> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): AuthenticationRemoved = try {
                AuthenticationRemoved(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AuthenticationRemoved) = try {
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
        public val accountId: AccountId
    ) : AccountEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PermissionAdded>, ScaleWriter<PermissionAdded> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): PermissionAdded = try {
                PermissionAdded(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PermissionAdded) = try {
                AccountId.write(writer, instance.accountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionRemoved' variant
     */
    public data class PermissionRemoved(
        public val accountId: AccountId
    ) : AccountEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PermissionRemoved>, ScaleWriter<PermissionRemoved> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): PermissionRemoved = try {
                PermissionRemoved(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PermissionRemoved) = try {
                AccountId.write(writer, instance.accountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MetadataInserted' variant
     */
    public data class MetadataInserted(
        public val accountId: AccountId
    ) : AccountEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MetadataInserted>, ScaleWriter<MetadataInserted> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): MetadataInserted = try {
                MetadataInserted(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: MetadataInserted) = try {
                AccountId.write(writer, instance.accountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'MetadataRemoved' variant
     */
    public data class MetadataRemoved(
        public val accountId: AccountId
    ) : AccountEvent() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<MetadataRemoved>, ScaleWriter<MetadataRemoved> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): MetadataRemoved = try {
                MetadataRemoved(
                    AccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: MetadataRemoved) = try {
                AccountId.write(writer, instance.accountId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<AccountEvent>, ScaleWriter<AccountEvent> {
        public override fun read(reader: ScaleCodecReader): AccountEvent = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Asset.read(reader)
            1 -> Created.read(reader)
            2 -> Deleted.read(reader)
            3 -> AuthenticationAdded.read(reader)
            4 -> AuthenticationRemoved.read(reader)
            5 -> PermissionAdded.read(reader)
            6 -> PermissionRemoved.read(reader)
            7 -> MetadataInserted.read(reader)
            8 -> MetadataRemoved.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: AccountEvent) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Asset.write(writer, instance as Asset)
                1 -> Created.write(writer, instance as Created)
                2 -> Deleted.write(writer, instance as Deleted)
                3 -> AuthenticationAdded.write(writer, instance as AuthenticationAdded)
                4 -> AuthenticationRemoved.write(writer, instance as AuthenticationRemoved)
                5 -> PermissionAdded.write(writer, instance as PermissionAdded)
                6 -> PermissionRemoved.write(writer, instance as PermissionRemoved)
                7 -> MetadataInserted.write(writer, instance as MetadataInserted)
                8 -> MetadataRemoved.write(writer, instance as MetadataRemoved)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
