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
      EvaluatesTo<Boolean>.read(reader),
      EvaluatesTo<Boolean>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: And): Unit {
        EvaluatesTo<Boolean>.write(writer, instance.left)
        EvaluatesTo<Boolean>.write(writer, instance.right)
    }
  }
}
