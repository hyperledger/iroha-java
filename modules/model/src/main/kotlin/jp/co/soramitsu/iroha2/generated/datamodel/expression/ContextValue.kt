//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.String

/**
 * ContextValue
 *
 * Generated from 'iroha_data_model::expression::ContextValue' regular structure
 */
public data class ContextValue(
    public val valueName: String
) {
    public companion object : ScaleReader<ContextValue>, ScaleWriter<ContextValue> {
        public override fun read(reader: ScaleCodecReader): ContextValue = ContextValue(
            reader.readString(),
        )

        public override fun write(writer: ScaleCodecWriter, instance: ContextValue) {
            writer.writeAsList(instance.valueName.toByteArray(Charsets.UTF_8))
        }
    }
}
