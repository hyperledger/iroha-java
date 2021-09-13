//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.ByteArray

/**
 * Hash
 *
 * Generated from 'iroha_crypto::Hash' tuple structure
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
