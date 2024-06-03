//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Unit

/**
 * TransactionValue
 *
 * Generated from 'TransactionValue' regular structure
 */
public data class TransactionValue(
    public val `value`: SignedTransaction,
    public val error: TransactionRejectionReason? = null,
) {
    public companion object : ScaleReader<TransactionValue>, ScaleWriter<TransactionValue> {
        override fun read(reader: ScaleCodecReader): TransactionValue = try {
            TransactionValue(
                SignedTransaction.read(reader),
                reader.readNullable(TransactionRejectionReason) as TransactionRejectionReason?,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: TransactionValue): Unit = try {
            SignedTransaction.write(writer, instance.`value`)
            writer.writeNullable(TransactionRejectionReason, instance.error)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
