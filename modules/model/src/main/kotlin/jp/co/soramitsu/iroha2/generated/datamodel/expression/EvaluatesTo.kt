//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.GsonSerializable
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any

/**
 * EvaluatesTo
 *
 * Generated from 'iroha_data_model::expression::EvaluatesTo<u32>' regular structure
 */
public data class EvaluatesTo<T0>(
    public val expression: Expression
) : GsonSerializable {
    public companion object : ScaleReader<EvaluatesTo<out Any>>, ScaleWriter<EvaluatesTo<out Any>> {
        public override fun read(reader: ScaleCodecReader): EvaluatesTo<out Any> = try {
            EvaluatesTo(
                Expression.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: EvaluatesTo<out Any>) = try {
            Expression.write(writer, instance.expression)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
