//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import jp.co.soramitsu.iroha2.hashSetWithSize
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.Set

/**
 * Transaction
 *
 * Generated from 'iroha_data_model::transaction::Transaction' regular structure
 */
public data class Transaction(
    public val payload: Payload,
    public val signatures: Set<Signature>
) {
    public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
        public override fun read(reader: ScaleCodecReader): Transaction = try {
            Transaction(
                Payload.read(reader),
                hashSetWithSize(reader.readCompactInt()) { Signature.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Transaction) = try {
            Payload.write(writer, instance.payload)
            writer.writeCompact(instance.signatures.size)
            instance.signatures.forEach { value -> Signature.write(writer, value) }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
