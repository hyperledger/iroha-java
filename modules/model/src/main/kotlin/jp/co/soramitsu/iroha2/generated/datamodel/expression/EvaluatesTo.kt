//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Any

/**
 * EvaluatesTo
 *
 * Generated from 'iroha_data_model::expression::EvaluatesTo<u32>' regular structure
 */
public data class EvaluatesTo<T0>(
    public val expression: Expression
) {
    public companion object : ScaleReader<EvaluatesTo<out Any>>, ScaleWriter<EvaluatesTo<out Any>> {
        public override fun read(reader: ScaleCodecReader): EvaluatesTo<out Any> = EvaluatesTo(
            Expression.read(reader) as Expression,
        )

        public override fun write(writer: ScaleCodecWriter, instance: EvaluatesTo<out Any>) {
            Expression.write(writer, instance.expression)
        }
    }
}
