//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.ByteArray

/**
 * WasmSmartContract
 *
 * Generated from 'iroha_data_model::transaction::WasmSmartContract' regular structure
 */
public data class WasmSmartContract(
    public val rawData: ByteArray
) {
    public companion object : ScaleReader<WasmSmartContract>, ScaleWriter<WasmSmartContract> {
        public override fun read(reader: ScaleCodecReader): WasmSmartContract = try {
            WasmSmartContract(
                reader.readByteArray(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: WasmSmartContract) = try {
            writer.writeAsList(instance.rawData)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
