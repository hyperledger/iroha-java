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
 * TransactionQueryOutput
 *
 * Generated from 'TransactionQueryOutput' regular structure
 */
public data class TransactionQueryOutput(
    public val transaction: TransactionValue,
    public val blockHash: HashOf<VersionedCommittedBlock>,
) {
    public companion object : ScaleReader<TransactionQueryOutput>, ScaleWriter<TransactionQueryOutput> {
        override fun read(reader: ScaleCodecReader): TransactionQueryOutput = try {
            TransactionQueryOutput(
                TransactionValue.read(reader),
                HashOf.read(reader) as HashOf<VersionedCommittedBlock>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: TransactionQueryOutput): Unit = try {
            TransactionValue.write(writer, instance.transaction)
            HashOf.write(writer, instance.blockHash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
