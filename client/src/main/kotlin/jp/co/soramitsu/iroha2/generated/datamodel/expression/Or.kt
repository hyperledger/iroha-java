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
 * Or
 *
 * Generated from 'iroha_data_model::expression::Or' regular structure
 */
public class Or(
  public val left: EvaluatesTo<Boolean>,
  public val right: EvaluatesTo<Boolean>
) {
  public companion object : ScaleReader<Or>, ScaleWriter<Or> {
    public override fun read(reader: ScaleCodecReader): Or = Or(
      EvaluatesTo<Boolean>.read(reader),
      EvaluatesTo<Boolean>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: Or): Unit {
        EvaluatesTo<Boolean>.write(writer, instance.left)
        EvaluatesTo<Boolean>.write(writer, instance.right)
    }
  }
}
