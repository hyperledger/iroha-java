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
import kotlin.collections.MutableList

/**
 * ContainsAny
 *
 * Generated from 'iroha_data_model::expression::ContainsAny' regular structure
 */
public data class ContainsAny(
    public val collection: EvaluatesTo<MutableList<Value>>,
    public val elements: EvaluatesTo<MutableList<Value>>
) {
    public companion object : ScaleReader<ContainsAny>, ScaleWriter<ContainsAny> {
        public override fun read(reader: ScaleCodecReader): ContainsAny = try {
            ContainsAny(
                EvaluatesTo.read(reader) as EvaluatesTo<MutableList<Value>>,
                EvaluatesTo.read(reader) as EvaluatesTo<MutableList<Value>>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ContainsAny) = try {
            EvaluatesTo.write(writer, instance.collection)
            EvaluatesTo.write(writer, instance.elements)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
