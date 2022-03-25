//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.block

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.signature.SignatureOf
import jp.co.soramitsu.iroha2.generated.datamodel.events.Event
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedRejectedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedValidTransaction
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List
import kotlin.collections.Set

/**
 * ValidBlock
 *
 * Generated from 'iroha_core::block::ValidBlock' regular structure
 */
public data class ValidBlock(
    public val header: BlockHeader,
    public val rejectedTransactions: List<VersionedRejectedTransaction>,
    public val transactions: List<VersionedValidTransaction>,
    public val signatures: Set<SignatureOf<ValidBlock>>,
    public val eventRecommendations: List<Event>
) {
    public companion object : ScaleReader<ValidBlock>, ScaleWriter<ValidBlock> {
        public override fun read(reader: ScaleCodecReader): ValidBlock = try {
            ValidBlock(
                BlockHeader.read(reader),
                reader.readVec(reader.readCompactInt()) { VersionedRejectedTransaction.read(reader) },
                reader.readVec(reader.readCompactInt()) { VersionedValidTransaction.read(reader) },
                reader.readSet(reader.readCompactInt()) {
                    SignatureOf.read(reader) as
                        SignatureOf<ValidBlock>
                },
                reader.readVec(reader.readCompactInt()) { Event.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: ValidBlock) = try {
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
            writer.writeCompact(instance.signatures.size)
            instance.signatures.forEach { value -> SignatureOf.write(writer, value) }
            writer.writeCompact(instance.eventRecommendations.size)
            instance.eventRecommendations.forEach { value -> Event.write(writer, value) }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
