//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.ByteArray
import kotlin.String

/**
 * PublicKey
 *
 * Generated from 'iroha_crypto::PublicKey' regular structure
 */
public data class PublicKey(
    public val digestFunction: String,
    public val payload: ByteArray
) {
    public companion object : ScaleReader<PublicKey>, ScaleWriter<PublicKey> {
        public override fun read(reader: ScaleCodecReader): PublicKey = try {
            PublicKey(
                reader.readString(),
                reader.readByteArray(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PublicKey) = try {
            writer.writeAsList(instance.digestFunction.toByteArray(Charsets.UTF_8))
            writer.writeAsList(instance.payload)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
