//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.Signature

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
        public override fun read(reader: ScaleCodecReader): SignedQueryRequest = SignedQueryRequest(
            Payload.read(reader) as Payload,
            Signature.read(reader) as Signature,
        )

        public override fun write(writer: ScaleCodecWriter, instance: SignedQueryRequest) {
            Payload.write(writer, instance.payload)
            Signature.write(writer, instance.signature)
        }
    }
}
