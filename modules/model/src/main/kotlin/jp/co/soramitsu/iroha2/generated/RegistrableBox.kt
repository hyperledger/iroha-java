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
 * RegistrableBox
 *
 * Generated from 'RegistrableBox' enum
 */
public sealed class RegistrableBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Peer' variant
     */
    public data class Peer(
        public val peer: jp.co.soramitsu.iroha2.generated.Peer,
    ) : RegistrableBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.RegistrableBox.Peer>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.RegistrableBox.Peer> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.RegistrableBox.Peer = try {
                Peer(
                    jp.co.soramitsu.iroha2.generated.Peer.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.RegistrableBox.Peer,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Peer.write(writer, instance.peer)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Domain' variant
     */
    public data class Domain(
        public val newDomain: NewDomain,
    ) : RegistrableBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.RegistrableBox.Domain>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.RegistrableBox.Domain> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.RegistrableBox.Domain = try {
                Domain(
                    NewDomain.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.RegistrableBox.Domain,
            ): Unit = try {
                NewDomain.write(writer, instance.newDomain)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Account' variant
     */
    public data class Account(
        public val newAccount: NewAccount,
    ) : RegistrableBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.RegistrableBox.Account>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.RegistrableBox.Account> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.RegistrableBox.Account = try {
                Account(
                    NewAccount.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.RegistrableBox.Account,
            ): Unit = try {
                NewAccount.write(writer, instance.newAccount)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AssetDefinition' variant
     */
    public data class AssetDefinition(
        public val newAssetDefinition: NewAssetDefinition,
    ) : RegistrableBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.RegistrableBox.AssetDefinition>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.RegistrableBox.AssetDefinition> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.RegistrableBox.AssetDefinition = try {
                AssetDefinition(
                    NewAssetDefinition.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.RegistrableBox.AssetDefinition,
            ): Unit = try {
                NewAssetDefinition.write(writer, instance.newAssetDefinition)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Asset' variant
     */
    public data class Asset(
        public val asset: jp.co.soramitsu.iroha2.generated.Asset,
    ) : RegistrableBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.RegistrableBox.Asset>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.RegistrableBox.Asset> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.RegistrableBox.Asset = try {
                Asset(
                    jp.co.soramitsu.iroha2.generated.Asset.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.RegistrableBox.Asset,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Asset.write(writer, instance.asset)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Trigger' variant
     */
    public data class Trigger(
        public val triggerOfFilterBoxAndExecutable: TriggerOfFilterBoxAndExecutable,
    ) : RegistrableBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.RegistrableBox.Trigger>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.RegistrableBox.Trigger> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.RegistrableBox.Trigger = try {
                Trigger(
                    TriggerOfFilterBoxAndExecutable.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.RegistrableBox.Trigger,
            ): Unit = try {
                TriggerOfFilterBoxAndExecutable.write(writer, instance.triggerOfFilterBoxAndExecutable)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Role' variant
     */
    public data class Role(
        public val newRole: NewRole,
    ) : RegistrableBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.RegistrableBox.Role>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.RegistrableBox.Role> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.RegistrableBox.Role = try {
                Role(
                    NewRole.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.RegistrableBox.Role,
            ): Unit = try {
                NewRole.write(writer, instance.newRole)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<RegistrableBox>, ScaleWriter<RegistrableBox> {
        override fun read(reader: ScaleCodecReader): RegistrableBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Peer.read(reader)
            1 -> Domain.read(reader)
            2 -> Account.read(reader)
            3 -> AssetDefinition.read(reader)
            4 -> Asset.read(reader)
            5 -> Trigger.read(reader)
            6 -> Role.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: RegistrableBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Peer.write(writer, instance as Peer)
                1 -> Domain.write(writer, instance as Domain)
                2 -> Account.write(writer, instance as Account)
                3 -> AssetDefinition.write(writer, instance as AssetDefinition)
                4 -> Asset.write(writer, instance as Asset)
                5 -> Trigger.write(writer, instance as Trigger)
                6 -> Role.write(writer, instance as Role)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
