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
import kotlin.Unit
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
    public override fun read(reader: ScaleCodecReader): RejectedTransaction =
        RejectedTransaction(Payload.read(reader),
    io.emeraldpay.polkaj.scale.reader.ListReader(jp.co.soramitsu.iroha2.generated.crypto.Signature).read(reader),
    TransactionRejectionReason.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: RejectedTransaction): Unit {
      Payload.write(writer, instance.payload)
      writer.write(io.emeraldpay.polkaj.scale.writer.ListWriter(Signature), instance.signatures)
      TransactionRejectionReason.write(writer, instance.rejectionReason)
    }
  }
}
