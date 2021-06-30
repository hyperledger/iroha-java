// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.crypto.Signature
import kotlin.Unit
import kotlin.collections.List

/**
 * Transaction
 *
 * Generated from 'iroha_data_model::transaction::Transaction' regular structure
 */
public class Transaction(
  private val payload: Payload,
  private val signatures: List<Signature>
) : ScaleReader<Transaction>, ScaleWriter<Transaction> {
  public override fun read(reader: ScaleCodecReader): Transaction =
      Transaction(payload.read(reader), signatures.read(reader))

  public override fun write(writer: ScaleCodecWriter, instance: Transaction): Unit {
    payload.write(writer, instance.payload),
    signatures.write(writer, instance.signatures)
  }
}
