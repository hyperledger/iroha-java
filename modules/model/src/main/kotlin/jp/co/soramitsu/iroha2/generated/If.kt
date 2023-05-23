//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * If
 *
 * Generated from 'If' regular structure
 */
public data class If(
    public val condition: EvaluatesTo<Bool>,
    public val then: EvaluatesTo<Value>,
    public val otherwise: EvaluatesTo<Value>
) {
    public companion object : ScaleReader<If>, ScaleWriter<If> {
        public override fun read(reader: ScaleCodecReader): If = try {
            If(
                EvaluatesTo.read(reader) as EvaluatesTo<Bool>,
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: If) = try {
            EvaluatesTo.write(writer, instance.condition)
            EvaluatesTo.write(writer, instance.then)
            EvaluatesTo.write(writer, instance.otherwise)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
