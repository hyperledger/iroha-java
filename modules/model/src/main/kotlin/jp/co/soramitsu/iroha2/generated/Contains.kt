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
import kotlin.collections.List

/**
 * Contains
 *
 * Generated from 'Contains' regular structure
 */
public data class Contains(
    public val collection: EvaluatesTo<List<Value>>,
    public val element: EvaluatesTo<Value>,
) {
    public companion object : ScaleReader<Contains>, ScaleWriter<Contains> {
        override fun read(reader: ScaleCodecReader): Contains = try {
            Contains(
                EvaluatesTo.read(reader) as EvaluatesTo<List<Value>>,
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: Contains): Unit = try {
            EvaluatesTo.write(writer, instance.collection)
            EvaluatesTo.write(writer, instance.element)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
