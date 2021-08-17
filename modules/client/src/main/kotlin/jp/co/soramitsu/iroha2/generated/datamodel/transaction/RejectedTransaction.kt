//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.TransactionRejectionReason
import kotlin.collections.MutableList

/**
 * RejectedTransaction
 *
 * Generated from 'iroha_data_model::transaction::RejectedTransaction' regular structure
 */
public class RejectedTransaction(
    public val payload: Payload,
    public val signatures: MutableList<Signature>,
    public val rejectionReason: TransactionRejectionReason
) {
    public companion object : ScaleReader<RejectedTransaction>, ScaleWriter<RejectedTransaction> {
        public override fun read(reader: ScaleCodecReader): RejectedTransaction = RejectedTransaction(
            Payload.read(reader) as Payload,
            MutableList(reader.readCompactInt()) { Signature.read(reader) as Signature },
            TransactionRejectionReason.read(reader) as TransactionRejectionReason,
        )

        public override fun write(writer: ScaleCodecWriter, instance: RejectedTransaction) {
            Payload.write(writer, instance.payload)
            writer.writeCompact(instance.signatures.size)
            instance.signatures.forEach { value -> Signature.write(writer, value) }
            TransactionRejectionReason.write(writer, instance.rejectionReason)
        }
    }
}
