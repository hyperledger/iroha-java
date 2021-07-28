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
 * Multiply
 *
 * Generated from 'iroha_data_model::expression::Multiply' regular structure
 */
public class Multiply(
  public val left: EvaluatesTo<UInt>,
  public val right: EvaluatesTo<UInt>
) {
  public companion object : ScaleReader<Multiply>, ScaleWriter<Multiply> {
    public override fun read(reader: ScaleCodecReader): Multiply = Multiply(
      EvaluatesTo<UInt>.read(reader),
      EvaluatesTo<UInt>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: Multiply): Unit {


    }
  }
}
