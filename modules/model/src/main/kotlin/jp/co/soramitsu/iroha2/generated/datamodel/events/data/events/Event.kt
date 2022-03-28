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
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.domain.DomainEvent
import jp.co.soramitsu.iroha2.generated.datamodel.events.`data`.events.peer.PeerEvent
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
     * 'Domain' variant
     */
    public data class Domain(
        public val domainEvent: DomainEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Domain>, ScaleWriter<Domain> {
            public const val DISCRIMINANT: Int = 0

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
     * 'Peer' variant
     */
    public data class Peer(
        public val peerEvent: PeerEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Peer>, ScaleWriter<Peer> {
            public const val DISCRIMINANT: Int = 1

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
     * 'Role' variant
     */
    public data class Role(
        public val roleEvent: RoleEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Role>, ScaleWriter<Role> {
            public const val DISCRIMINANT: Int = 2

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
     * 'Account' variant
     */
    public data class Account(
        public val accountEvent: AccountEvent
    ) : Event() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Account>, ScaleWriter<Account> {
            public const val DISCRIMINANT: Int = 3

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
            public const val DISCRIMINANT: Int = 4

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
            public const val DISCRIMINANT: Int = 5

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
            public const val DISCRIMINANT: Int = 6

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

    public companion object : ScaleReader<Event>, ScaleWriter<Event> {
        public override fun read(reader: ScaleCodecReader): Event = when (
            val discriminant =
                reader.readUByte().toInt()
        ) {
            0 -> Domain.read(reader)
            1 -> Peer.read(reader)
            2 -> Role.read(reader)
            3 -> Account.read(reader)
            4 -> AssetDefinition.read(reader)
            5 -> Asset.read(reader)
            6 -> Trigger.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Event) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Domain.write(writer, instance as Domain)
                1 -> Peer.write(writer, instance as Peer)
                2 -> Role.write(writer, instance as Role)
                3 -> Account.write(writer, instance as Account)
                4 -> AssetDefinition.write(writer, instance as AssetDefinition)
                5 -> Asset.write(writer, instance as Asset)
                6 -> Trigger.write(writer, instance as Trigger)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
