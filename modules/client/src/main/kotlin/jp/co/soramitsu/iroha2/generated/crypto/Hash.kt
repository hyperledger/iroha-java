//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.ByteArray

/**
 * Hash
 *
 * Generated from 'iroha_crypto::Hash' tuple structure
 */
public class Hash(
    public val array: ByteArray
) {
    public companion object : ScaleReader<Hash>, ScaleWriter<Hash> {
        public override fun read(reader: ScaleCodecReader): Hash = Hash(
            reader.readByteArray(32),
        )

        public override fun write(writer: ScaleCodecWriter, instance: Hash) {
            writer.writeByteArray(instance.array)
        }
    }
}
