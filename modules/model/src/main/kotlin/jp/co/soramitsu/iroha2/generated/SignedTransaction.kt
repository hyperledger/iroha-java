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
 * SignedTransaction
 *
 * Generated from 'SignedTransaction' regular structure
 */
public data class SignedTransaction(
    public val payload: TransactionPayload,
    public val signatures: SignaturesOf<TransactionPayload>
) {
    public companion object : ScaleReader<SignedTransaction>, ScaleWriter<SignedTransaction> {
        public override fun read(reader: ScaleCodecReader): SignedTransaction = try {
            SignedTransaction(
                TransactionPayload.read(reader),
                SignaturesOf.read(reader) as SignaturesOf<TransactionPayload>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SignedTransaction) = try {
            TransactionPayload.write(writer, instance.payload)
            SignaturesOf.write(writer, instance.signatures)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
