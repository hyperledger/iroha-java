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
import kotlin.collections.List

/**
 * BlockHeader
 *
 * Generated from 'BlockHeader' regular structure
 */
public data class BlockHeader(
    public val timestamp: BigInteger,
    public val consensusEstimation: BigInteger,
    public val height: BigInteger,
    public val viewChangeIndex: BigInteger,
    public val previousBlockHash: HashOf<VersionedCommittedBlock>? = null,
    public val transactionsHash: HashOf<List<VersionedSignedTransaction>>? = null,
    public val rejectedTransactionsHash: HashOf<List<VersionedSignedTransaction>>? = null,
    public val committedWithTopology: List<PeerId>,
) {
    public companion object : ScaleReader<BlockHeader>, ScaleWriter<BlockHeader> {
        override fun read(reader: ScaleCodecReader): BlockHeader = try {
            BlockHeader(
                reader.readUint128(),
                reader.readUint64(),
                reader.readUint64(),
                reader.readUint64(),
                reader.readNullable(HashOf) as HashOf<VersionedCommittedBlock>?,
                reader.readNullable(HashOf) as HashOf<List<VersionedSignedTransaction>>?,
                reader.readNullable(HashOf) as HashOf<List<VersionedSignedTransaction>>?,
                reader.readVec(reader.readCompactInt()) { PeerId.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: BlockHeader): Unit = try {
            writer.writeUint128(instance.timestamp)
            writer.writeUint64(instance.consensusEstimation)
            writer.writeUint64(instance.height)
            writer.writeUint64(instance.viewChangeIndex)
            writer.writeNullable(HashOf, instance.previousBlockHash)
            writer.writeNullable(HashOf, instance.transactionsHash)
            writer.writeNullable(HashOf, instance.rejectedTransactionsHash)
            writer.writeCompact(instance.committedWithTopology.size)
            instance.committedWithTopology.forEach { value ->
                PeerId.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
