//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * HashOfWasmSmartContract
 *
 * Generated from 'HashOfWasmSmartContract' regular structure
 */
public data class HashOfWasmSmartContract(
    public val hash: Hash,
) {
    public companion object :
        ScaleReader<HashOfWasmSmartContract>,
        ScaleWriter<HashOfWasmSmartContract> {
        override fun read(reader: ScaleCodecReader): HashOfWasmSmartContract = try {
            HashOfWasmSmartContract(
                Hash.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: HashOfWasmSmartContract): Unit = try {
            Hash.write(writer, instance.hash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
