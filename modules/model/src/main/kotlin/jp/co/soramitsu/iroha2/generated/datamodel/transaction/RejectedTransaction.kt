//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.signature.SignaturesOf
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.TransactionRejectionReason
import jp.co.soramitsu.iroha2.wrapException

/**
 * RejectedTransaction
 *
 * Generated from 'iroha_data_model::transaction::RejectedTransaction' regular structure
 */
public data class RejectedTransaction(
    public val payload: Payload,
    public val signatures: SignaturesOf<Payload>,
    public val rejectionReason: TransactionRejectionReason
) {
    public companion object : ScaleReader<RejectedTransaction>, ScaleWriter<RejectedTransaction> {
        public override fun read(reader: ScaleCodecReader): RejectedTransaction = try {
            RejectedTransaction(
                Payload.read(reader),
                SignaturesOf.read(reader) as SignaturesOf<Payload>,
                TransactionRejectionReason.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RejectedTransaction) = try {
            Payload.write(writer, instance.payload)
            SignaturesOf.write(writer, instance.signatures)
            TransactionRejectionReason.write(writer, instance.rejectionReason)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
