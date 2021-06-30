// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Unit

/**
 * If
 *
 * Generated from 'iroha_data_model::expression::If' regular structure
 */
public class If(
  private val condition: EvaluatesTo,
  private val thenExpression: EvaluatesTo,
  private val elseExpression: EvaluatesTo
) : ScaleReader<If>, ScaleWriter<If> {
  public override fun read(reader: ScaleCodecReader): If = If(condition.read(reader),
      thenExpression.read(reader), elseExpression.read(reader))

  public override fun write(writer: ScaleCodecWriter, instance: If): Unit {
    condition.write(writer, instance.condition),
    thenExpression.write(writer, instance.thenExpression),
    elseExpression.write(writer, instance.elseExpression)
  }
}
