//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * SignedQuery
 *
 * Generated from 'SignedQuery' regular structure
 */
public data class SignedQuery(
    public val signature: SignatureOf<QueryPayload>,
    public val payload: QueryPayload,
) {
    public companion object : ScaleReader<SignedQuery>, ScaleWriter<SignedQuery> {
        override fun read(reader: ScaleCodecReader): SignedQuery = try {
            SignedQuery(
                SignatureOf.read(reader) as SignatureOf<QueryPayload>,
                QueryPayload.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SignedQuery): Unit = try {
            SignatureOf.write(writer, instance.signature)
            QueryPayload.write(writer, instance.payload)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
