//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.hashMapWithSize
import kotlin.String
import kotlin.collections.MutableMap

/**
 * Where
 *
 * Generated from 'iroha_data_model::expression::Where' regular structure
 */
public data class Where(
    public val expression: EvaluatesTo<Value>,
    public val values: MutableMap<String, EvaluatesTo<Value>>
) {
    public companion object : ScaleReader<Where>, ScaleWriter<Where> {
        public override fun read(reader: ScaleCodecReader): Where = Where(
            EvaluatesTo.read(reader) as EvaluatesTo<Value>,
            hashMapWithSize(
                reader.readCompactInt(), { reader.readString() },
                {
                    EvaluatesTo.read(reader) as
                        EvaluatesTo<Value>
                }
            ),
        )

        public override fun write(writer: ScaleCodecWriter, instance: Where) {
            EvaluatesTo.write(writer, instance.expression)
            writer.writeCompact(instance.values.size)
            instance.values.forEach { (key, value) ->
                writer.writeAsList(key.toByteArray(Charsets.UTF_8))
                EvaluatesTo.write(writer, value)
            }
        }
    }
}
