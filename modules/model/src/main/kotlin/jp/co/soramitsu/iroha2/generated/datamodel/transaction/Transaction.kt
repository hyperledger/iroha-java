//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import kotlin.collections.MutableList

/**
 * Transaction
 *
 * Generated from 'iroha_data_model::transaction::Transaction' regular structure
 */
public data class Transaction(
    public val payload: Payload,
    public val signatures: MutableList<Signature>
) {
    public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
        public override fun read(reader: ScaleCodecReader): Transaction = Transaction(
            Payload.read(reader) as Payload,
            MutableList(reader.readCompactInt()) { Signature.read(reader) as Signature },
        )

        public override fun write(writer: ScaleCodecWriter, instance: Transaction) {
            Payload.write(writer, instance.payload)
            writer.writeCompact(instance.signatures.size)
            instance.signatures.forEach { value -> Signature.write(writer, value) }
        }
    }
}
