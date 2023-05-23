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
 * RejectedTransaction
 *
 * Generated from 'RejectedTransaction' regular structure
 */
public data class RejectedTransaction(
    public val payload: TransactionPayload,
    public val signatures: SignaturesOf<TransactionPayload>,
    public val rejectionReason: TransactionRejectionReason
) {
    public companion object : ScaleReader<RejectedTransaction>, ScaleWriter<RejectedTransaction> {
        public override fun read(reader: ScaleCodecReader): RejectedTransaction = try {
            RejectedTransaction(
                TransactionPayload.read(reader),
                SignaturesOf.read(reader) as SignaturesOf<TransactionPayload>,
                TransactionRejectionReason.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RejectedTransaction) = try {
            TransactionPayload.write(writer, instance.payload)
            SignaturesOf.write(writer, instance.signatures)
            TransactionRejectionReason.write(writer, instance.rejectionReason)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
