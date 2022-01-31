//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto.hash

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.ByteArray

/**
 * Hash
 *
 * Generated from 'iroha_crypto::hash::Hash' tuple structure
 */
public data class Hash(
    public val array: ByteArray
) {
    public companion object : ScaleReader<Hash>, ScaleWriter<Hash> {
        public override fun read(reader: ScaleCodecReader): Hash = try {
            Hash(
                reader.readByteArray(32),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Hash) = try {
            writer.writeByteArray(instance.array)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
