//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.block

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.core.sumeragi.networktopology.Topology
import jp.co.soramitsu.iroha2.generated.crypto.hash.HashOf
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedSignedTransaction
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.collections.List

/**
 * BlockHeader
 *
 * Generated from 'iroha_core::block::BlockHeader' regular structure
 */
public data class BlockHeader(
    public val timestamp: BigInteger,
    public val consensusEstimation: BigInteger,
    public val height: BigInteger,
    public val viewChangeIndex: BigInteger,
    public val previousBlockHash: HashOf<VersionedCommittedBlock>? = null,
    public val transactionsHash: HashOf<List<VersionedSignedTransaction>>? = null,
    public val rejectedTransactionsHash: HashOf<List<VersionedSignedTransaction>>? = null,
    public val genesisTopology: Topology? = null
) {
    public companion object : ScaleReader<BlockHeader>, ScaleWriter<BlockHeader> {
        public override fun read(reader: ScaleCodecReader): BlockHeader = try {
            BlockHeader(
                reader.readUint128(),
                reader.readUint64(),
                reader.readUint64(),
                reader.readUint64(),
                reader.readNullable(HashOf) as HashOf<VersionedCommittedBlock>?,
                reader.readNullable(HashOf) as HashOf<List<VersionedSignedTransaction>>?,
                reader.readNullable(HashOf) as HashOf<List<VersionedSignedTransaction>>?,
                reader.readNullable(Topology) as Topology?,
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: BlockHeader) = try {
            writer.writeUint128(instance.timestamp)
            writer.writeUint64(instance.consensusEstimation)
            writer.writeUint64(instance.height)
            writer.writeUint64(instance.viewChangeIndex)
            writer.writeNullable(HashOf, instance.previousBlockHash)
            writer.writeNullable(HashOf, instance.transactionsHash)
            writer.writeNullable(HashOf, instance.rejectedTransactionsHash)
            writer.writeNullable(Topology, instance.genesisTopology)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
