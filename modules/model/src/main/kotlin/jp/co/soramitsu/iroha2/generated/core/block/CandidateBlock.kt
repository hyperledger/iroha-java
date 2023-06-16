//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.block

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.signature.SignaturesOf
import jp.co.soramitsu.iroha2.generated.datamodel.events.Event
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedSignedTransaction
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * CandidateBlock
 *
 * Generated from 'iroha_core::block::CandidateBlock' regular structure
 */
public data class CandidateBlock(
    public val header: BlockHeader,
    public val rejectedTransactions: List<VersionedSignedTransaction>,
    public val transactions: List<VersionedSignedTransaction>,
    public val signatures: SignaturesOf<CandidateBlock>,
    public val eventRecommendations: List<Event>
) {
    public companion object : ScaleReader<CandidateBlock>, ScaleWriter<CandidateBlock> {
        public override fun read(reader: ScaleCodecReader): CandidateBlock = try {
            CandidateBlock(
                BlockHeader.read(reader),
                reader.readVec(reader.readCompactInt()) { VersionedSignedTransaction.read(reader) },
                reader.readVec(reader.readCompactInt()) { VersionedSignedTransaction.read(reader) },
                SignaturesOf.read(reader) as SignaturesOf<CandidateBlock>,
                reader.readVec(reader.readCompactInt()) { Event.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: CandidateBlock) = try {
            BlockHeader.write(writer, instance.header)
            writer.writeCompact(instance.rejectedTransactions.size)
            instance.rejectedTransactions.forEach { value ->
                VersionedSignedTransaction.write(writer, value)
            }
            writer.writeCompact(instance.transactions.size)
            instance.transactions.forEach { value ->
                VersionedSignedTransaction.write(writer, value)
            }
            SignaturesOf.write(writer, instance.signatures)
            writer.writeCompact(instance.eventRecommendations.size)
            instance.eventRecommendations.forEach { value ->
                Event.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
