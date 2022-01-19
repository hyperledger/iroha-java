//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.crypto.signature

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.schema.irohacrypto.signature.SignatureOf
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
    public val signature: SignatureOf<Payload>,
    public val reason: String
) {
    public companion object :
        ScaleReader<SignatureVerificationFail<out Any>>,
        ScaleWriter<SignatureVerificationFail<out Any>> {
        public override fun read(reader: ScaleCodecReader): SignatureVerificationFail<out Any> = try {
            SignatureVerificationFail(
                SignatureOf.read(reader) as SignatureOf<Payload>,
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
            SignatureOf.write(writer, instance.signature)
            writer.writeAsList(instance.reason.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
