//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * Contains
 *
 * Generated from 'iroha_data_model::expression::Contains' regular structure
 */
public data class Contains(
    public val collection: EvaluatesTo<List<Value>>,
    public val element: EvaluatesTo<Value>
) {
    public companion object : ScaleReader<Contains>, ScaleWriter<Contains> {
        public override fun read(reader: ScaleCodecReader): Contains = try {
            Contains(
                EvaluatesTo.read(reader) as EvaluatesTo<List<Value>>,
                EvaluatesTo.read(reader) as EvaluatesTo<Value>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Contains) = try {
            EvaluatesTo.write(writer, instance.collection)
            EvaluatesTo.write(writer, instance.element)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
