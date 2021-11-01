//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
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
        public override fun read(reader: ScaleCodecReader): ContextValue = try {
            ContextValue(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ContextValue) = try {
            writer.writeAsList(instance.valueName.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
