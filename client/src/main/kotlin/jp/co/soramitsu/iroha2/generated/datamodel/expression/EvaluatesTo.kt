//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Any
import kotlin.Unit

/**
 * EvaluatesTo
 *
 * Generated from 'iroha_data_model::expression::EvaluatesTo<u32>' regular structure
 */
public class EvaluatesTo<T0>(
  public val expression: Expression
) {
  public companion object : ScaleReader<EvaluatesTo<out Any>>, ScaleWriter<EvaluatesTo<out Any>> {
    public override fun read(reader: ScaleCodecReader): EvaluatesTo<out Any> = EvaluatesTo(
      Expression.read(reader),
    )

    public override fun write(writer: ScaleCodecWriter, instance: EvaluatesTo<out Any>): Unit {
        Expression.write(writer, instance.expression)
    }
  }
}
