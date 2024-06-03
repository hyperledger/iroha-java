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
 * ContainsAll
 *
 * Generated from 'ContainsAll' regular structure
 */
public data class ContainsAll(
    public val collection: EvaluatesTo<List<Value>>,
    public val elements: EvaluatesTo<List<Value>>,
) {
    public companion object : ScaleReader<ContainsAll>, ScaleWriter<ContainsAll> {
        override fun read(reader: ScaleCodecReader): ContainsAll = try {
            ContainsAll(
                EvaluatesTo.read(reader) as EvaluatesTo<List<Value>>,
                EvaluatesTo.read(reader) as EvaluatesTo<List<Value>>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: ContainsAll): Unit = try {
            EvaluatesTo.write(writer, instance.collection)
            EvaluatesTo.write(writer, instance.elements)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
