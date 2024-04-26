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
 * SignedQueryV1
 *
 * Generated from 'SignedQueryV1' regular structure
 */
public data class SignedQueryV1(
    public val signature: SignatureOf<QueryPayload>,
    public val payload: QueryPayload,
) {
    public companion object : ScaleReader<SignedQueryV1>, ScaleWriter<SignedQueryV1> {
        override fun read(reader: ScaleCodecReader): SignedQueryV1 = try {
            SignedQueryV1(
                SignatureOf.read(reader) as SignatureOf<QueryPayload>,
                QueryPayload.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SignedQueryV1): Unit = try {
            SignatureOf.write(writer, instance.signature)
            QueryPayload.write(writer, instance.payload)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
