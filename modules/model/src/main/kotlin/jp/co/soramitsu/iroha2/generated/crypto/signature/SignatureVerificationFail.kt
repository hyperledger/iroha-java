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
import kotlin.String

/**
 * SignatureVerificationFail
 *
 * Generated from
 * 'iroha_crypto::signature::SignatureVerificationFail<iroha_data_model::transaction::Payload>' regular
 * structure
 */
public data class SignatureVerificationFail<T0>(
    public val signature: Signature,
    public val reason: String
) {
    public companion object :
        ScaleReader<SignatureVerificationFail<out Any>>,
        ScaleWriter<SignatureVerificationFail<out Any>> {
        public override fun read(reader: ScaleCodecReader): SignatureVerificationFail<out Any> = try {
            SignatureVerificationFail(
                Signature.read(reader),
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(
            writer: ScaleCodecWriter,
            instance: SignatureVerificationFail<out        
                Any>
        ) = try {
            Signature.write(writer, instance.signature)
            writer.writeAsList(instance.reason.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
