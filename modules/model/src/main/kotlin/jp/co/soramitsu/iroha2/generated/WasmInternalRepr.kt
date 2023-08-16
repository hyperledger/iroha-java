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
import kotlin.Unit

/**
 * WasmInternalRepr
 *
 * Generated from 'WasmInternalRepr' regular structure
 */
public data class WasmInternalRepr(
    public val serialized: ByteArray,
    public val blobHash: HashOf<WasmSmartContract>,
) {
    public companion object : ScaleReader<WasmInternalRepr>, ScaleWriter<WasmInternalRepr> {
        override fun read(reader: ScaleCodecReader): WasmInternalRepr = try {
            WasmInternalRepr(
                reader.readByteArray(),
                HashOf.read(reader) as HashOf<WasmSmartContract>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: WasmInternalRepr): Unit = try {
            writer.writeAsList(instance.serialized)
            HashOf.write(writer, instance.blobHash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
