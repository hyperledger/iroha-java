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
 * Not
 *
 * Generated from 'Not' regular structure
 */
public data class Not(
    public val expression: EvaluatesTo<Bool>
) {
    public companion object : ScaleReader<Not>, ScaleWriter<Not> {
        public override fun read(reader: ScaleCodecReader): Not = try {
            Not(
                EvaluatesTo.read(reader) as EvaluatesTo<Bool>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Not) = try {
            EvaluatesTo.write(writer, instance.expression)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
