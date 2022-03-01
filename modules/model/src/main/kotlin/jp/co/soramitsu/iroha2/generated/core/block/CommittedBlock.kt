//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.block

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.signature.SignaturesOf
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedRejectedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedValidTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Action
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * CommittedBlock
 *
 * Generated from 'iroha_core::block::CommittedBlock' regular structure
 */
public data class CommittedBlock(
    public val header: BlockHeader,
    public val rejectedTransactions: List<VersionedRejectedTransaction>,
    public val transactions: List<VersionedValidTransaction>,
    public val triggerRecommendations: List<Action>,
    public val signatures: SignaturesOf<CommittedBlock>
) {
    public companion object : ScaleReader<CommittedBlock>, ScaleWriter<CommittedBlock> {
        public override fun read(reader: ScaleCodecReader): CommittedBlock = try {
            CommittedBlock(
                BlockHeader.read(reader),
                reader.readVec(reader.readCompactInt()) { VersionedRejectedTransaction.read(reader) },
                reader.readVec(reader.readCompactInt()) { VersionedValidTransaction.read(reader) },
                reader.readVec(reader.readCompactInt()) { Action.read(reader) },
                SignaturesOf.read(reader) as SignaturesOf<CommittedBlock>,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: CommittedBlock) = try {
            BlockHeader.write(writer, instance.header)
            writer.writeCompact(instance.rejectedTransactions.size)
            instance.rejectedTransactions.forEach { value ->
                VersionedRejectedTransaction.write(
                    writer,
                    value
                )
            }
            writer.writeCompact(instance.transactions.size)
            instance.transactions.forEach { value -> VersionedValidTransaction.write(writer, value) }
            writer.writeCompact(instance.triggerRecommendations.size)
            instance.triggerRecommendations.forEach { value -> Action.write(writer, value) }
            SignaturesOf.write(writer, instance.signatures)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
