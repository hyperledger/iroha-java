//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.signature.SignatureOf
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.Set

/**
 * Transaction
 *
 * Generated from 'iroha_data_model::transaction::Transaction' regular structure
 */
public data class Transaction(
    public val payload: Payload,
    public val signatures: Set<SignatureOf<Payload>>
) {
    public companion object : ScaleReader<Transaction>, ScaleWriter<Transaction> {
        public override fun read(reader: ScaleCodecReader): Transaction = try {
            Transaction(
                Payload.read(reader),
                reader.readSet(reader.readCompactInt()) { SignatureOf.read(reader) as SignatureOf<Payload> },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: Transaction) = try {
            Payload.write(writer, instance.payload)
            writer.writeCompact(instance.signatures.size)
            instance.signatures.forEach { value -> SignatureOf.write(writer, value) }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
