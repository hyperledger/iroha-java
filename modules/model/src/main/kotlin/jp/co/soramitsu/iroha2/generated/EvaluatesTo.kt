//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any

/**
 * EvaluatesTo
 *
 * Generated from 'EvaluatesTo<Vec<Value>>' regular structure
 */
public data class EvaluatesTo<T0>(
    public val expression: Expression
) {
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
