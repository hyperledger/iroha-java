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
 * SignedTransaction
 *
 * Generated from 'SignedTransaction' regular structure
 */
public data class SignedTransaction(
    public val signatures: SignaturesOfOfTransactionPayload,
    public val payload: TransactionPayload,
) {
    public companion object : ScaleReader<SignedTransaction>, ScaleWriter<SignedTransaction> {
        override fun read(reader: ScaleCodecReader): SignedTransaction = try {
            SignedTransaction(
                SignaturesOfOfTransactionPayload.read(reader),
                TransactionPayload.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SignedTransaction): Unit = try {
            SignaturesOfOfTransactionPayload.write(writer, instance.signatures)
            TransactionPayload.write(writer, instance.payload)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
