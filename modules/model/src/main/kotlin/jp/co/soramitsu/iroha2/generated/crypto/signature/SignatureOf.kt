//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto.signature

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Any

/**
 * SignatureOf
 *
 * Generated from 'iroha_crypto::signature::SignatureOf<iroha_data_model::transaction::Payload>'
 * tuple structure
 */
public data class SignatureOf<T0>(
    public val signature: Signature
) {
    public companion object : ScaleReader<SignatureOf<out Any>>, ScaleWriter<SignatureOf<out Any>> {
        public override fun read(reader: ScaleCodecReader): SignatureOf<out Any> = try {
            SignatureOf(
                Signature.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SignatureOf<out Any>) = try {
            Signature.write(writer, instance.signature)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
