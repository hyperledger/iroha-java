//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * WasmExecutionFail
 *
 * Generated from 'iroha_data_model::transaction::WasmExecutionFail' regular structure
 */
public data class WasmExecutionFail(
    public val reason: String
) {
    public companion object : ScaleReader<WasmExecutionFail>, ScaleWriter<WasmExecutionFail> {
        public override fun read(reader: ScaleCodecReader): WasmExecutionFail = try {
            WasmExecutionFail(
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: WasmExecutionFail) = try {
            writer.writeAsList(instance.reason.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
