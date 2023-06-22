//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.comparator
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * SignaturesOfOfTransactionPayload
 *
 * Generated from 'SignaturesOfOfTransactionPayload' regular structure
 */
public data class SignaturesOfOfTransactionPayload(
    public val signatures: List<SignatureOf<TransactionPayload>>
) {
    public companion object :
        ScaleReader<SignaturesOfOfTransactionPayload>,
        ScaleWriter<SignaturesOfOfTransactionPayload> {
        public override fun read(reader: ScaleCodecReader): SignaturesOfOfTransactionPayload = try {
            SignaturesOfOfTransactionPayload(
                reader.readVec(reader.readCompactInt()) {
                    SignatureOf.read(reader) as
                        SignatureOf<TransactionPayload>
                },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SignaturesOfOfTransactionPayload) =
            try {
                writer.writeCompact(instance.signatures.size)
                instance.signatures.sortedWith(
                    SignatureOf.comparator()
                ).forEach { value ->
                    SignatureOf.write(writer, value)
                }
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
    }
}
