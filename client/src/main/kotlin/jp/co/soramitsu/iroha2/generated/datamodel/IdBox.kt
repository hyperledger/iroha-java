//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
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
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<AccountId>, ScaleWriter<AccountId> {
      public const val DISCRIMINANT: Int = 0

      public override fun read(reader: ScaleCodecReader): AccountId {
      }

      public override fun write(writer: ScaleCodecWriter, instance: AccountId): Unit {
      }
    }
  }

  /**
   * 'AssetId' variant
   */
  public class AssetId(
    private val assetId: jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
  ) : IdBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<AssetId>, ScaleWriter<AssetId> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): AssetId {
      }

      public override fun write(writer: ScaleCodecWriter, instance: AssetId): Unit {
      }
    }
  }

  /**
   * 'AssetDefinitionId' variant
   */
  public class AssetDefinitionId(
    private val assetDefinitionId: DefinitionId
  ) : IdBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<AssetDefinitionId>, ScaleWriter<AssetDefinitionId> {
      public const val DISCRIMINANT: Int = 2

      public override fun read(reader: ScaleCodecReader): AssetDefinitionId {
      }

      public override fun write(writer: ScaleCodecWriter, instance: AssetDefinitionId): Unit {
      }
    }
  }

  /**
   * 'DomainName' variant
   */
  public class DomainName(
    private val domainName: String
  ) : IdBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<DomainName>, ScaleWriter<DomainName> {
      public const val DISCRIMINANT: Int = 3

      public override fun read(reader: ScaleCodecReader): DomainName {
      }

      public override fun write(writer: ScaleCodecWriter, instance: DomainName): Unit {
      }
    }
  }

  /**
   * 'PeerId' variant
   */
  public class PeerId(
    private val peerId: jp.co.soramitsu.iroha2.generated.datamodel.peer.Id
  ) : IdBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<PeerId>, ScaleWriter<PeerId> {
      public const val DISCRIMINANT: Int = 4

      public override fun read(reader: ScaleCodecReader): PeerId {
      }

      public override fun write(writer: ScaleCodecWriter, instance: PeerId): Unit {
      }
    }
  }

  /**
   * 'WorldId' variant
   */
  public class WorldId : IdBox() {
    public override fun discriminant(): Int = DISCRIMINANT
  }

  public companion object : ScaleReader<IdBox>, ScaleWriter<IdBox> {
    public override fun read(reader: ScaleCodecReader): IdBox = when(val discriminant =
        reader.readUByte()) {
    	0 -> AccountId.read(reader)
    	1 -> AssetId.read(reader)
    	2 -> AssetDefinitionId.read(reader)
    	3 -> DomainName.read(reader)
    	4 -> PeerId.read(reader)
    	5 -> WorldId.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: IdBox): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	0 -> AccountId.write(writer, instance as AccountId)
      	1 -> AssetId.write(writer, instance as AssetId)
      	2 -> AssetDefinitionId.write(writer, instance as AssetDefinitionId)
      	3 -> DomainName.write(writer, instance as DomainName)
      	4 -> PeerId.write(writer, instance as PeerId)
      	5 -> WorldId.write(writer, instance as WorldId)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant $discriminant")}
    }
  }
}
