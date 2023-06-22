//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * PendingTransactions
 *
 * Generated from 'PendingTransactions' regular structure
 */
public data class PendingTransactions(
    public val vecOfSignedTransaction: List<SignedTransaction>
) {
    public companion object : ScaleReader<PendingTransactions>, ScaleWriter<PendingTransactions> {
        public override fun read(reader: ScaleCodecReader): PendingTransactions = try {
            PendingTransactions(
                reader.readVec(reader.readCompactInt()) { SignedTransaction.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: PendingTransactions) = try {
            writer.writeCompact(instance.vecOfSignedTransaction.size)
            instance.vecOfSignedTransaction.forEach { value ->
                SignedTransaction.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
