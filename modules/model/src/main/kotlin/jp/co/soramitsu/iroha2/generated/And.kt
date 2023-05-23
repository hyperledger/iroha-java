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
 * And
 *
 * Generated from 'And' regular structure
 */
public data class And(
    public val left: EvaluatesTo<Bool>,
    public val right: EvaluatesTo<Bool>
) {
    public companion object : ScaleReader<And>, ScaleWriter<And> {
        public override fun read(reader: ScaleCodecReader): And = try {
            And(
                EvaluatesTo.read(reader) as EvaluatesTo<Bool>,
                EvaluatesTo.read(reader) as EvaluatesTo<Bool>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: And) = try {
            EvaluatesTo.write(writer, instance.left)
            EvaluatesTo.write(writer, instance.right)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
