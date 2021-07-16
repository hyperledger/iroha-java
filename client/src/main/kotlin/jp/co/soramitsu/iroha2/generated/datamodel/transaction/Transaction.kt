//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import kotlin.Unit
import kotlin.collections.MutableList

/**
 * Transaction
 *
 * Generated from 'iroha_data_model::transaction::Transaction' regular structure
 */
public class Transaction(
  public val payload: Payload,
  public val signatures: MutableList<Signature>
) {
  public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
    public override fun read(reader: ScaleCodecReader): Transaction =
        Transaction(Payload.read(reader),
    reader.read(io.emeraldpay.polkaj.scale.reader.ListReader(Signature)))

    public override fun write(writer: ScaleCodecWriter, instance: Transaction): Unit {
      Payload.write(writer, instance.payload)
      writer.write(io.emeraldpay.polkaj.scale.writer.ListWriter, instance.signatures)
    }
  }
}
