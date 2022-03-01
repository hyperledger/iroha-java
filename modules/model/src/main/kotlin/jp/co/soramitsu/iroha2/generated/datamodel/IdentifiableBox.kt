//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * IdentifiableBox
 *
 * Generated from 'iroha_data_model::IdentifiableBox' enum
 */
public sealed class IdentifiableBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Account' variant
     */
    public data class Account(
        public val account: jp.co.soramitsu.iroha2.generated.datamodel.account.Account
    ) : IdentifiableBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Account>, ScaleWriter<Account> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Account = try {
                Account(
                    jp.co.soramitsu.iroha2.generated.datamodel.account.Account.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Account) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.account.Account.write(writer, instance.account)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'NewAccount' variant
     */
    public data class NewAccount(
        public val newAccount: jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount
    ) : IdentifiableBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<NewAccount>, ScaleWriter<NewAccount> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): NewAccount = try {
                NewAccount(
                    jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: NewAccount) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount.write(
                    writer,
                    instance.newAccount
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Asset' variant
     */
    public data class Asset(
        public val asset: jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
    ) : IdentifiableBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Asset>, ScaleWriter<Asset> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Asset = try {
                Asset(
                    jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Asset) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset.write(writer, instance.asset)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'AssetDefinition' variant
     */
    public data class AssetDefinition(
        public val assetDefinition: jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
    ) : IdentifiableBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<AssetDefinition>, ScaleWriter<AssetDefinition> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): AssetDefinition = try {
                AssetDefinition(
                    jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: AssetDefinition) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition.write(
                    writer,
                    instance.assetDefinition
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Domain' variant
     */
    public data class Domain(
        public val domain: jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain
    ) : IdentifiableBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Domain>, ScaleWriter<Domain> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): Domain = try {
                Domain(
                    jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Domain) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain.write(writer, instance.domain)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Peer' variant
     */
    public data class Peer(
        public val peer: jp.co.soramitsu.iroha2.generated.datamodel.peer.Peer
    ) : IdentifiableBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Peer>, ScaleWriter<Peer> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): Peer = try {
                Peer(
                    jp.co.soramitsu.iroha2.generated.datamodel.peer.Peer.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Peer) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.peer.Peer.write(writer, instance.peer)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Trigger' variant
     */
    public data class Trigger(
        public val trigger: jp.co.soramitsu.iroha2.generated.datamodel.trigger.Trigger
    ) : IdentifiableBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Trigger>, ScaleWriter<Trigger> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): Trigger = try {
                Trigger(
                    jp.co.soramitsu.iroha2.generated.datamodel.trigger.Trigger.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Trigger) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.trigger.Trigger.write(writer, instance.trigger)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'World' variant
     */
    public class World : IdentifiableBox() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<World>, ScaleWriter<World> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): World = try {
                World()
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: World) = try {
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<IdentifiableBox>, ScaleWriter<IdentifiableBox> {
        public override fun read(reader: ScaleCodecReader): IdentifiableBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Account.read(reader)
            1 -> NewAccount.read(reader)
            2 -> Asset.read(reader)
            3 -> AssetDefinition.read(reader)
            4 -> Domain.read(reader)
            5 -> Peer.read(reader)
            6 -> Trigger.read(reader)
            7 -> World.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: IdentifiableBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Account.write(writer, instance as Account)
                1 -> NewAccount.write(writer, instance as NewAccount)
                2 -> Asset.write(writer, instance as Asset)
                3 -> AssetDefinition.write(writer, instance as AssetDefinition)
                4 -> Domain.write(writer, instance as Domain)
                5 -> Peer.write(writer, instance as Peer)
                6 -> Trigger.write(writer, instance as Trigger)
                7 -> World.write(writer, instance as World)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
