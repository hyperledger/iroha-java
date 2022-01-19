//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.schema.irohacrypto.signature

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any
import kotlin.ByteArray

/**
 * SignatureOf
 *
 * Generated from
 * 'iroha_schema::iroha_crypto::signature::SignatureOf<iroha_data_model::transaction::Payload>' regular
 * structure
 */
public data class SignatureOf<T0>(
    public val publicKey: PublicKey,
    public val signature: ByteArray
) {
    public companion object : ScaleReader<SignatureOf<out Any>>, ScaleWriter<SignatureOf<out Any>> {
        public override fun read(reader: ScaleCodecReader): SignatureOf<out Any> = try {
            SignatureOf(
                PublicKey.read(reader),
                reader.readByteArray(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SignatureOf<out Any>) = try {
            PublicKey.write(writer, instance.publicKey)
            writer.writeAsList(instance.signature)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
