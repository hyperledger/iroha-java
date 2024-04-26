//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Boolean
import kotlin.Unit

/**
 * If
 *
 * Generated from 'If' regular structure
 */
public data class If(
    public val condition: EvaluatesTo<Boolean>,
    public val then: EvaluatesTo<Value>,
    public val otherwise: EvaluatesTo<Value>,
) {
    public companion object : ScaleReader<If>, ScaleWriter<If> {
        override fun read(reader: ScaleCodecReader): If = try {
            If(
                EvaluatesTo.read(reader) as EvaluatesTo<Boolean>,
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: If): Unit = try {
            EvaluatesTo.write(writer, instance.condition)
            EvaluatesTo.write(writer, instance.then)
            EvaluatesTo.write(writer, instance.otherwise)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
