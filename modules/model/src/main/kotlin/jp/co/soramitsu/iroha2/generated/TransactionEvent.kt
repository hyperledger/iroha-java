//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.Unit

/**
 * TransactionEvent
 *
 * Generated from 'TransactionEvent' regular structure
 */
public data class TransactionEvent(
    public val hash: HashOf<SignedTransaction>,
    public val blockHeight: BigInteger? = null,
    public val status: TransactionStatus,
) {
    public companion object : ScaleReader<TransactionEvent>, ScaleWriter<TransactionEvent> {
        override fun read(reader: ScaleCodecReader): TransactionEvent = try {
            TransactionEvent(
                HashOf.read(reader) as HashOf<SignedTransaction>,
                reader.readNullable(),
                TransactionStatus.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: TransactionEvent): Unit = try {
            HashOf.write(writer, instance.hash)
            writer.writeNullable(instance.blockHeight)
            TransactionStatus.write(writer, instance.status)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}