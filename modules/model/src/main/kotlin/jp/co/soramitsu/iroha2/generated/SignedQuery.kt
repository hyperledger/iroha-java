//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException

/**
 * SignedQuery
 *
 * Generated from 'SignedQuery' regular structure
 */
public data class SignedQuery(
    public val payload: QueryPayload,
    public val signature: SignatureOf<QueryPayload>
) {
    public companion object : ScaleReader<SignedQuery>, ScaleWriter<SignedQuery> {
        public override fun read(reader: ScaleCodecReader): SignedQuery = try {
            SignedQuery(
                QueryPayload.read(reader),
                SignatureOf.read(reader) as SignatureOf<QueryPayload>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SignedQuery) = try {
            QueryPayload.write(writer, instance.payload)
            SignatureOf.write(writer, instance.signature)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
