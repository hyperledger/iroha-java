//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * Validator
 *
 * Generated from 'Validator' regular structure
 */
public data class Validator(
    public val wasm: WasmSmartContract
) {
    public companion object : ScaleReader<Validator>, ScaleWriter<Validator> {
        public override fun read(reader: ScaleCodecReader): Validator = try {
            Validator(
                WasmSmartContract.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Validator) = try {
            WasmSmartContract.write(writer, instance.wasm)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
