//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.account.AccountEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.asset.AssetDefinitionEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.asset.AssetEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.config.ConfigurationEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.domain.DomainEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.peer.PeerEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.permission.PermissionTokenEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.permission.PermissionValidatorEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.role.RoleEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.trigger.TriggerEvent
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * Event
 *
 * Generated from 'iroha_data_model::events::data::events::Event' enum
 */
public sealed class Event : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Peer' variant
     */
    public data class Peer(
        public val peerEvent: PeerEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Peer>, ScaleWriter<Peer> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Peer = try {
                Peer(
                    PeerEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Peer) = try {
                PeerEvent.write(writer, instance.peerEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Domain' variant
     */
    public data class Domain(
        public val domainEvent: DomainEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Domain>, ScaleWriter<Domain> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Domain = try {
                Domain(
                    DomainEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Domain) = try {
                DomainEvent.write(writer, instance.domainEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Account' variant
     */
    public data class Account(
        public val accountEvent: AccountEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Account>, ScaleWriter<Account> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Account = try {
                Account(
                    AccountEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Account) = try {
                AccountEvent.write(writer, instance.accountEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AssetDefinition' variant
     */
    public data class AssetDefinition(
        public val assetDefinitionEvent: AssetDefinitionEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetDefinition>, ScaleWriter<AssetDefinition> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): AssetDefinition = try {
                AssetDefinition(
                    AssetDefinitionEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetDefinition) = try {
                AssetDefinitionEvent.write(writer, instance.assetDefinitionEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Asset' variant
     */
    public data class Asset(
        public val assetEvent: AssetEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Asset>, ScaleWriter<Asset> {
            public const val DISCRIMINANT: Int = 4

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
     * 'Trigger' variant
     */
    public data class Trigger(
        public val triggerEvent: TriggerEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Trigger>, ScaleWriter<Trigger> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): Trigger = try {
                Trigger(
                    TriggerEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Trigger) = try {
                TriggerEvent.write(writer, instance.triggerEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Role' variant
     */
    public data class Role(
        public val roleEvent: RoleEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Role>, ScaleWriter<Role> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): Role = try {
                Role(
                    RoleEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Role) = try {
                RoleEvent.write(writer, instance.roleEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionToken' variant
     */
    public data class PermissionToken(
        public val permissionTokenEvent: PermissionTokenEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PermissionToken>, ScaleWriter<PermissionToken> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): PermissionToken = try {
                PermissionToken(
                    PermissionTokenEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PermissionToken) = try {
                PermissionTokenEvent.write(writer, instance.permissionTokenEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'PermissionValidator' variant
     */
    public data class PermissionValidator(
        public val permissionValidatorEvent: PermissionValidatorEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<PermissionValidator>, ScaleWriter<PermissionValidator> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): PermissionValidator = try {
                PermissionValidator(
                    PermissionValidatorEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: PermissionValidator) = try {
                PermissionValidatorEvent.write(writer, instance.permissionValidatorEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Configuration' variant
     */
    public data class Configuration(
        public val configurationEvent: ConfigurationEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Configuration>, ScaleWriter<Configuration> {
            public const val DISCRIMINANT: Int = 9

            public override fun read(reader: ScaleCodecReader): Configuration = try {
                Configuration(
                    ConfigurationEvent.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Configuration) = try {
                ConfigurationEvent.write(writer, instance.configurationEvent)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Event>, ScaleWriter<Event> {
        public override fun read(reader: ScaleCodecReader): Event = when (
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
            7 -> PermissionToken.read(reader)
            8 -> PermissionValidator.read(reader)
            9 -> Configuration.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Event) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Peer.write(writer, instance as Peer)
                1 -> Domain.write(writer, instance as Domain)
                2 -> Account.write(writer, instance as Account)
                3 -> AssetDefinition.write(writer, instance as AssetDefinition)
                4 -> Asset.write(writer, instance as Asset)
                5 -> Trigger.write(writer, instance as Trigger)
                6 -> Role.write(writer, instance as Role)
                7 -> PermissionToken.write(writer, instance as PermissionToken)
                8 -> PermissionValidator.write(writer, instance as PermissionValidator)
                9 -> Configuration.write(writer, instance as Configuration)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
