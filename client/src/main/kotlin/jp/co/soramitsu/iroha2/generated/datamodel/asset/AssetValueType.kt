//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int
import kotlin.Unit

/**
 * AssetValueType
 *
 * Generated from 'iroha_data_model::asset::AssetValueType' enum
 */
public sealed class AssetValueType {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'Quantity' variant
   */
  public class Quantity : AssetValueType() {
    public override fun discriminant(): Int = DISCRIMINANT
  }

  /**
   * 'BigQuantity' variant
   */
  public class BigQuantity : AssetValueType() {
    public override fun discriminant(): Int = DISCRIMINANT
  }

  /**
   * 'Store' variant
   */
  public class Store : AssetValueType() {
    public override fun discriminant(): Int = DISCRIMINANT
  }

  public companion object : ScaleReader<AssetValueType>, ScaleWriter<AssetValueType> {
    public override fun read(reader: ScaleCodecReader): AssetValueType = when(val discriminant =
        reader.readUByte()) {
    	0 -> Quantity.read(reader)
    	1 -> BigQuantity.read(reader)
    	2 -> Store.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: AssetValueType): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
      	0 -> Quantity.write(writer, instance as Quantity)
      	1 -> BigQuantity.write(writer, instance as BigQuantity)
      	2 -> Store.write(writer, instance as Store)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant $discriminant")}
    }
  }
}
