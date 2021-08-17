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
public data class If(
  public val condition: EvaluatesTo<Boolean>,
  public val thenExpression: EvaluatesTo<Value>,
  public val elseExpression: EvaluatesTo<Value>
) {
  public companion object : ScaleReader<If>, ScaleWriter<If> {
    public override fun read(reader: ScaleCodecReader): If = If(
      EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
      EvaluatesTo.read(reader) as EvaluatesTo<Value>,
      EvaluatesTo.read(reader) as EvaluatesTo<Value>,
    )

    public override fun write(writer: ScaleCodecWriter, instance: If): Unit {
        EvaluatesTo.write(writer, instance.condition)
        EvaluatesTo.write(writer, instance.thenExpression)
        EvaluatesTo.write(writer, instance.elseExpression)
    }
  }
}
