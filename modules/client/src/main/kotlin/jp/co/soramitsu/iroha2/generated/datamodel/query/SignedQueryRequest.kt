//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import java.math.BigInteger

/**
 * SignedQueryRequest
 *
 * Generated from 'iroha_data_model::query::SignedQueryRequest' regular structure
 */
public class SignedQueryRequest(
    public val timestampMs: BigInteger,
    public val signature: Signature,
    public val query: QueryBox
) {
    public companion object : ScaleReader<SignedQueryRequest>, ScaleWriter<SignedQueryRequest> {
        public override fun read(reader: ScaleCodecReader): SignedQueryRequest = SignedQueryRequest(
            reader.readCompactInt().toBigInteger(),
            Signature.read(reader) as Signature,
            QueryBox.read(reader) as QueryBox,
        )

        public override fun write(writer: ScaleCodecWriter, instance: SignedQueryRequest) {
            writer.writeCompact(instance.timestampMs.toInt())
            Signature.write(writer, instance.signature)
            QueryBox.write(writer, instance.query)
        }
    }
}
