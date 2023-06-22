//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.ByteArray

/**
 * WasmSmartContract
 *
 * Generated from 'WasmSmartContract' regular structure
 */
public data class WasmSmartContract(
    public val vecOfU8: ByteArray
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
            writer.writeAsList(instance.vecOfU8)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
