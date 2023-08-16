//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * Add
 *
 * Generated from 'Add' regular structure
 */
public data class Add(
    public val left: EvaluatesTo<NumericValue>,
    public val right: EvaluatesTo<NumericValue>,
) {
    public companion object : ScaleReader<Add>, ScaleWriter<Add> {
        override fun read(reader: ScaleCodecReader): Add = try {
            Add(
                EvaluatesTo.read(reader) as EvaluatesTo<NumericValue>,
                EvaluatesTo.read(reader) as EvaluatesTo<NumericValue>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Add): Unit = try {
            EvaluatesTo.write(writer, instance.left)
            EvaluatesTo.write(writer, instance.right)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
