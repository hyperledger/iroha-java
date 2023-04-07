//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto.signature

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.wrapException
import kotlin.ByteArray

/**
 * Signature
 *
 * Generated from 'iroha_crypto::signature::Signature' regular structure
 */
public data class Signature(
    public val publicKey: PublicKey,
    public val payload: ByteArray
) {
    public companion object : ScaleReader<Signature>, ScaleWriter<Signature> {
        public override fun read(reader: ScaleCodecReader): Signature = try {
            Signature(
                PublicKey.read(reader),
                reader.readByteArray(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Signature) = try {
            PublicKey.write(writer, instance.publicKey)
            writer.writeAsList(instance.payload)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
