//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.blockvalue

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.events.Event
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedRejectedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedValidTransaction
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * BlockValue
 *
 * Generated from 'iroha_data_model::block_value::BlockValue' regular structure
 */
public data class BlockValue(
    public val header: BlockHeaderValue,
    public val transactions: List<VersionedValidTransaction>,
    public val rejectedTransactions: List<VersionedRejectedTransaction>,
    public val eventRecommendations: List<Event>
) {
    public companion object : ScaleReader<BlockValue>, ScaleWriter<BlockValue> {
        public override fun read(reader: ScaleCodecReader): BlockValue = try {
            BlockValue(
                BlockHeaderValue.read(reader),
                reader.readVec(reader.readCompactInt()) { VersionedValidTransaction.read(reader) },
                reader.readVec(reader.readCompactInt()) { VersionedRejectedTransaction.read(reader) },
                reader.readVec(reader.readCompactInt()) { Event.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: BlockValue) = try {
            BlockHeaderValue.write(writer, instance.header)
            writer.writeCompact(instance.transactions.size)
            instance.transactions.forEach { value ->
                VersionedValidTransaction.write(writer, value)
            }
            writer.writeCompact(instance.rejectedTransactions.size)
            instance.rejectedTransactions.forEach { value ->
                VersionedRejectedTransaction.write(writer, value)
            }
            writer.writeCompact(instance.eventRecommendations.size)
            instance.eventRecommendations.forEach { value ->
                Event.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
