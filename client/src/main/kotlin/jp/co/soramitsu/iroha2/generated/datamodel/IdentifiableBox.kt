//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int
import kotlin.Unit

/**
 * IdentifiableBox
 *
 * Generated from 'iroha_data_model::IdentifiableBox' enum
 */
public sealed class IdentifiableBox {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'Account' variant
   */
  public class Account(
    private val account: jp.co.soramitsu.iroha2.generated.datamodel.account.Account
  ) : IdentifiableBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Account>, ScaleWriter<Account> {
      public const val DISCRIMINANT: Int = 0

      public override fun read(reader: ScaleCodecReader): Account = Account(
        jp.co.soramitsu.iroha2.generated.datamodel.account.Account.read(reader),
      )

      public override fun write(writer: ScaleCodecWriter, instance: Account): Unit {

      }
    }
  }

  /**
   * 'NewAccount' variant
   */
  public class NewAccount(
    private val newAccount: jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount
  ) : IdentifiableBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<NewAccount>, ScaleWriter<NewAccount> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): NewAccount = NewAccount(
        jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount.read(reader),
      )

      public override fun write(writer: ScaleCodecWriter, instance: NewAccount): Unit {

      }
    }
  }

  /**
   * 'Asset' variant
   */
  public class Asset(
    private val asset: jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
  ) : IdentifiableBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Asset>, ScaleWriter<Asset> {
      public const val DISCRIMINANT: Int = 2

      public override fun read(reader: ScaleCodecReader): Asset = Asset(
        jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset.read(reader),
      )

      public override fun write(writer: ScaleCodecWriter, instance: Asset): Unit {

      }
    }
  }

  /**
   * 'AssetDefinition' variant
   */
  public class AssetDefinition(
    private val assetDefinition: jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
  ) : IdentifiableBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<AssetDefinition>, ScaleWriter<AssetDefinition> {
      public const val DISCRIMINANT: Int = 3

      public override fun read(reader: ScaleCodecReader): AssetDefinition = AssetDefinition(
        jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition.read(reader),
      )

      public override fun write(writer: ScaleCodecWriter, instance: AssetDefinition): Unit {

      }
    }
  }

  /**
   * 'Domain' variant
   */
  public class Domain(
    private val domain: jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain
  ) : IdentifiableBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Domain>, ScaleWriter<Domain> {
      public const val DISCRIMINANT: Int = 4

      public override fun read(reader: ScaleCodecReader): Domain = Domain(
        jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain.read(reader),
      )

      public override fun write(writer: ScaleCodecWriter, instance: Domain): Unit {

      }
    }
  }

  /**
   * 'Peer' variant
   */
  public class Peer(
    private val peer: jp.co.soramitsu.iroha2.generated.datamodel.peer.Peer
  ) : IdentifiableBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Peer>, ScaleWriter<Peer> {
      public const val DISCRIMINANT: Int = 5

      public override fun read(reader: ScaleCodecReader): Peer = Peer(
        jp.co.soramitsu.iroha2.generated.datamodel.peer.Peer.read(reader),
      )

      public override fun write(writer: ScaleCodecWriter, instance: Peer): Unit {

      }
    }
  }

  /**
   * 'World' variant
   */
  public class World : IdentifiableBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<World>, ScaleWriter<World> {
      public const val DISCRIMINANT: Int = 6

      public override fun read(reader: ScaleCodecReader): World = World(
      )

      public override fun write(writer: ScaleCodecWriter, instance: World): Unit {
      }
    }
  }

  public companion object : ScaleReader<IdentifiableBox>, ScaleWriter<IdentifiableBox> {
    public override fun read(reader: ScaleCodecReader): IdentifiableBox = when(val discriminant =
        reader.readUByte()) {
    	0 -> Account.read(reader)
    	1 -> NewAccount.read(reader)
    	2 -> Asset.read(reader)
    	3 -> AssetDefinition.read(reader)
    	4 -> Domain.read(reader)
    	5 -> Peer.read(reader)
    	6 -> World.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: IdentifiableBox): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	0 -> Account.write(writer, instance as Account)
      	1 -> NewAccount.write(writer, instance as NewAccount)
      	2 -> Asset.write(writer, instance as Asset)
      	3 -> AssetDefinition.write(writer, instance as AssetDefinition)
      	4 -> Domain.write(writer, instance as Domain)
      	5 -> Peer.write(writer, instance as Peer)
      	6 -> World.write(writer, instance as World)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}
    }
  }
}
