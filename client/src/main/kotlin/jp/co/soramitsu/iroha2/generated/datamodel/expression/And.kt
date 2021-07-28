//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Boolean
import kotlin.Unit

/**
 * And
 *
 * Generated from 'iroha_data_model::expression::And' regular structure
 */
public class And(
  public val left: EvaluatesTo<Boolean>,
  public val right: EvaluatesTo<Boolean>
) {
  public companion object : ScaleReader<And>, ScaleWriter<And> {
    public override fun read(reader: ScaleCodecReader): And = And(
      EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
      EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
    )

    public override fun write(writer: ScaleCodecWriter, instance: And): Unit {
        EvaluatesTo.write(writer, instance.left)
        EvaluatesTo.write(writer, instance.right)
    }
  }
}
