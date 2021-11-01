//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.signature.Signature
import jp.co.soramitsu.iroha2.wrapException

/**
 * SignedQueryRequest
 *
 * Generated from 'iroha_data_model::query::SignedQueryRequest' regular structure
 */
public data class SignedQueryRequest(
    public val payload: Payload,
    public val signature: Signature
) {
    public companion object : ScaleReader<SignedQueryRequest>, ScaleWriter<SignedQueryRequest> {
        public override fun read(reader: ScaleCodecReader): SignedQueryRequest = try {
            SignedQueryRequest(
                Payload.read(reader),
                Signature.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SignedQueryRequest) = try {
            Payload.write(writer, instance.payload)
            Signature.write(writer, instance.signature)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
