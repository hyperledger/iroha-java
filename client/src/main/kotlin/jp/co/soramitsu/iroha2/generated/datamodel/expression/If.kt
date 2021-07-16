//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import kotlin.Boolean
import kotlin.Unit

/**
 * If
 *
 * Generated from 'iroha_data_model::expression::If' regular structure
 */
public class If(
  public val condition: EvaluatesTo<Boolean>,
  public val thenExpression: EvaluatesTo<Value>,
  public val elseExpression: EvaluatesTo<Value>
) {
  public companion object : ScaleReader<If>, ScaleWriter<If> {
    public override fun read(reader: ScaleCodecReader): If = If(Boolean.read(reader),
    Value.read(reader),
    Value.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: If): Unit {
      Boolean.write(writer, instance.condition)
      Value.write(writer, instance.thenExpression)
      Value.write(writer, instance.elseExpression)
    }
  }
}
