// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.datamodel.account.Id
import jp.co.soramitsu.schema.generated.datamodel.asset.DefinitionId
import kotlin.Int
import kotlin.String
import kotlin.Unit

/**
 * IdBox
 *
 * Generated from 'iroha_data_model::IdBox' enum
 */
public sealed class IdBox {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'AccountId' variant
   */
  public class AccountId(
    private val accountId: Id
  ) : IdBox() {
    public override fun discriminant(): Int = 0

    public companion object CODEC : ScaleReader<AccountId>, ScaleWriter<AccountId> {
      public override fun read(reader: ScaleCodecReader): AccountId =
          jp.co.soramitsu.schema.generated.datamodel.IdBox.AccountId(jp.co.soramitsu.schema.generated.datamodel.account.Id.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: AccountId): Unit {
        jp.co.soramitsu.schema.generated.datamodel.account.Id.write(writer, instance.`accountId`)
      }
    }
  }

  /**
   * 'AssetId' variant
   */
  public class AssetId(
    private val assetId: jp.co.soramitsu.schema.generated.datamodel.asset.Id
  ) : IdBox() {
    public override fun discriminant(): Int = 1

    public companion object CODEC : ScaleReader<AssetId>, ScaleWriter<AssetId> {
      public override fun read(reader: ScaleCodecReader): AssetId =
          jp.co.soramitsu.schema.generated.datamodel.IdBox.AssetId(jp.co.soramitsu.schema.generated.datamodel.asset.Id.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: AssetId): Unit {
        jp.co.soramitsu.schema.generated.datamodel.asset.Id.write(writer, instance.`assetId`)
      }
    }
  }

  /**
   * 'AssetDefinitionId' variant
   */
  public class AssetDefinitionId(
    private val assetDefinitionId: DefinitionId
  ) : IdBox() {
    public override fun discriminant(): Int = 2

    public companion object CODEC : ScaleReader<AssetDefinitionId>, ScaleWriter<AssetDefinitionId> {
      public override fun read(reader: ScaleCodecReader): AssetDefinitionId =
          jp.co.soramitsu.schema.generated.datamodel.IdBox.AssetDefinitionId(jp.co.soramitsu.schema.generated.datamodel.asset.DefinitionId.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionId): Unit {
        jp.co.soramitsu.schema.generated.datamodel.asset.DefinitionId.write(writer,
            instance.`assetDefinitionId`)
      }
    }
  }

  /**
   * 'DomainName' variant
   */
  public class DomainName(
    private val domainName: String
  ) : IdBox() {
    public override fun discriminant(): Int = 3

    public companion object CODEC : ScaleReader<DomainName>, ScaleWriter<DomainName> {
      public override fun read(reader: ScaleCodecReader): DomainName =
          jp.co.soramitsu.schema.generated.datamodel.IdBox.DomainName(reader.readString())

      public override fun write(writer: ScaleCodecWriter, instance: DomainName): Unit {
        writer.writeString(instance.`domainName`)
      }
    }
  }

  /**
   * 'PeerId' variant
   */
  public class PeerId(
    private val peerId: jp.co.soramitsu.schema.generated.datamodel.peer.Id
  ) : IdBox() {
    public override fun discriminant(): Int = 4

    public companion object CODEC : ScaleReader<PeerId>, ScaleWriter<PeerId> {
      public override fun read(reader: ScaleCodecReader): PeerId =
          jp.co.soramitsu.schema.generated.datamodel.IdBox.PeerId(jp.co.soramitsu.schema.generated.datamodel.peer.Id.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: PeerId): Unit {
        jp.co.soramitsu.schema.generated.datamodel.peer.Id.write(writer, instance.`peerId`)
      }
    }
  }

  /**
   * 'WorldId' variant
   */
  public class WorldId : IdBox() {
    public override fun discriminant(): Int = 5

    public companion object CODEC : ScaleReader<WorldId>, ScaleWriter<WorldId> {
      public override fun read(reader: ScaleCodecReader): WorldId =
          jp.co.soramitsu.schema.generated.datamodel.IdBox.WorldId()

      public override fun write(writer: ScaleCodecWriter, instance: WorldId): Unit {
        //nothing to write, enum variant do not have properties
      }
    }
  }

  public companion object CODEC : ScaleReader<IdBox>, ScaleWriter<IdBox> {
    public override fun read(reader: ScaleCodecReader): IdBox = when(reader.readUByte()) {
    	0 -> AccountId.read(reader)
    	1 -> AssetId.read(reader)
    	2 -> AssetDefinitionId.read(reader)
    	3 -> DomainName.read(reader)
    	4 -> PeerId.read(reader)
    	5 -> WorldId.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
    }

    public override fun write(writer: ScaleCodecWriter, instance: IdBox): Unit {
      writer.directWrite(instance.discriminant())
      when(instance.discriminant()) {
      	0 -> AccountId.write(writer, instance as AccountId)
      	1 -> AssetId.write(writer, instance as AssetId)
      	2 -> AssetDefinitionId.write(writer, instance as AssetDefinitionId)
      	3 -> DomainName.write(writer, instance as DomainName)
      	4 -> PeerId.write(writer, instance as PeerId)
      	5 -> WorldId.write(writer, instance as WorldId)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
      }
    }
  }
}
