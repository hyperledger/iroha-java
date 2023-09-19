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
import kotlin.collections.List

/**
 * CommittedBlock
 *
 * Generated from 'CommittedBlock' regular structure
 */
public data class CommittedBlock(
    public val `header`: BlockHeader,
    public val transactions: List<TransactionValue>,
    public val eventRecommendations: List<Event>,
    public val signatures: SignaturesOfOfCommittedBlock,
) {
    public companion object : ScaleReader<CommittedBlock>, ScaleWriter<CommittedBlock> {
        override fun read(reader: ScaleCodecReader): CommittedBlock = try {
            CommittedBlock(
                BlockHeader.read(reader),
                reader.readVec(reader.readCompactInt()) { TransactionValue.read(reader) },
                reader.readVec(reader.readCompactInt()) { Event.read(reader) },
                SignaturesOfOfCommittedBlock.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: CommittedBlock): Unit = try {
            BlockHeader.write(writer, instance.`header`)
            writer.writeCompact(instance.transactions.size)
            instance.transactions.forEach { value ->
                TransactionValue.write(writer, value)
            }
            writer.writeCompact(instance.eventRecommendations.size)
            instance.eventRecommendations.forEach { value ->
                Event.write(writer, value)
            }
            SignaturesOfOfCommittedBlock.write(writer, instance.signatures)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
