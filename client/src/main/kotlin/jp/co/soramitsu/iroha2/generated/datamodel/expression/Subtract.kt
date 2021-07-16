//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.UInt
import kotlin.Unit

/**
 * Subtract
 *
 * Generated from 'iroha_data_model::expression::Subtract' regular structure
 */
public class Subtract(
  public val left: EvaluatesTo<UInt>,
  public val right: EvaluatesTo<UInt>
) {
  public companion object : ScaleReader<Subtract>, ScaleWriter<Subtract> {
    public override fun read(reader: ScaleCodecReader): Subtract = Subtract(UInt.read(reader),
    UInt.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Subtract): Unit {
      UInt.write(writer, instance.left)
      UInt.write(writer, instance.right)
    }
  }
}
