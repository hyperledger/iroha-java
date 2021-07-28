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
    public override fun read(reader: ScaleCodecReader): If = If(
      EvaluatesTo<Boolean>.read(reader),
      EvaluatesTo<Value>.read(reader),
      EvaluatesTo<Value>.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: If): Unit {



    }
  }
}
