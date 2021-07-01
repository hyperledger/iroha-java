// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.datamodel.metadata.Metadata
import kotlin.Int
import kotlin.Unit

/**
 * AssetValue
 *
 * Generated from 'iroha_data_model::asset::AssetValue' enum
 */
public sealed class AssetValue {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'Quantity' variant
   */
  public class Quantity(
    private val quantity: Int
  ) : AssetValue() {
    public override fun discriminant(): Int = 0

    public companion object CODEC : ScaleReader<Quantity>, ScaleWriter<Quantity> {
      public override fun read(reader: ScaleCodecReader): Quantity =
          jp.co.soramitsu.schema.generated.datamodel.asset.AssetValue.Quantity(reader.readLong().toInt())

      public override fun write(writer: ScaleCodecWriter, instance: Quantity): Unit {
        writer.writeLong(instance.`quantity`.toLong())
      }
    }
  }

  /**
   * 'BigQuantity' variant
   */
  public class BigQuantity(
    private val bigQuantity: Int
  ) : AssetValue() {
    public override fun discriminant(): Int = 1

    public companion object CODEC : ScaleReader<BigQuantity>, ScaleWriter<BigQuantity> {
      public override fun read(reader: ScaleCodecReader): BigQuantity =
          jp.co.soramitsu.schema.generated.datamodel.asset.AssetValue.BigQuantity(reader.readLong().toInt())

      public override fun write(writer: ScaleCodecWriter, instance: BigQuantity): Unit {
        writer.writeLong(instance.`bigQuantity`.toLong())
      }
    }
  }

  /**
   * 'Store' variant
   */
  public class Store(
    private val store: Metadata
  ) : AssetValue() {
    public override fun discriminant(): Int = 2

    public companion object CODEC : ScaleReader<Store>, ScaleWriter<Store> {
      public override fun read(reader: ScaleCodecReader): Store =
          jp.co.soramitsu.schema.generated.datamodel.asset.AssetValue.Store(jp.co.soramitsu.schema.generated.datamodel.metadata.Metadata.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Store): Unit {
        jp.co.soramitsu.schema.generated.datamodel.metadata.Metadata.write(writer, instance.`store`)
      }
    }
  }

  public companion object CODEC : ScaleReader<AssetValue>, ScaleWriter<AssetValue> {
    public override fun read(reader: ScaleCodecReader): AssetValue = when(reader.readUByte()) {
    	0 -> Quantity.read(reader)
    	1 -> BigQuantity.read(reader)
    	2 -> Store.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
    }

    public override fun write(writer: ScaleCodecWriter, instance: AssetValue): Unit {
      writer.directWrite(instance.discriminant())
      when(instance.discriminant()) {
      	0 -> Quantity.write(writer, instance as Quantity)
      	1 -> BigQuantity.write(writer, instance as BigQuantity)
      	2 -> Store.write(writer, instance as Store)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
      }
    }
  }
}
