//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.schema.irohacrypto.hash

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.ByteArray

/**
 * HashOf
 *
 * Generated from
 * 'iroha_schema::iroha_crypto::hash::HashOf<iroha_core::sumeragi::view_change::Proof>' tuple structure
 */
public data class HashOf<T0>(
    public val array: ByteArray
) {
    public companion object : ScaleReader<HashOf<out Any>>, ScaleWriter<HashOf<out Any>> {
        public override fun read(reader: ScaleCodecReader): HashOf<out Any> = try {
            HashOf(
                reader.readByteArray(32),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: HashOf<out Any>) = try {
            writer.writeByteArray(instance.array)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
