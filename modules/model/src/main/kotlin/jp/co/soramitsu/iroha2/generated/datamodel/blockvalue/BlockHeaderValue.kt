//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.blockvalue

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.crypto.hash.HashOf
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.wrapException
import java.math.BigInteger
import kotlin.collections.List

/**
 * BlockHeaderValue
 *
 * Generated from 'iroha_data_model::block_value::BlockHeaderValue' regular structure
 */
public data class BlockHeaderValue(
    public val timestamp: BigInteger,
    public val height: BigInteger,
    public val previousBlockHash: Hash,
    public val transactionsHash: HashOf<List<VersionedTransaction>>,
    public val rejectedTransactionsHash: HashOf<List<VersionedTransaction>>,
    public val invalidatedBlocksHashes: List<Hash>,
    public val currentBlockHash: Hash
) {
    public companion object : ScaleReader<BlockHeaderValue>, ScaleWriter<BlockHeaderValue> {
        public override fun read(reader: ScaleCodecReader): BlockHeaderValue = try {
            BlockHeaderValue(
                reader.readUint128(),
                reader.readUint64(),
                Hash.read(reader),
                HashOf.read(reader) as HashOf<List<VersionedTransaction>>,
                HashOf.read(reader) as HashOf<List<VersionedTransaction>>,
                reader.readVec(reader.readCompactInt()) { Hash.read(reader) },
                Hash.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: BlockHeaderValue) = try {
            writer.writeUint128(instance.timestamp)
            writer.writeUint64(instance.height)
            Hash.write(writer, instance.previousBlockHash)
            HashOf.write(writer, instance.transactionsHash)
            HashOf.write(writer, instance.rejectedTransactionsHash)
            writer.writeCompact(instance.invalidatedBlocksHashes.size)
            instance.invalidatedBlocksHashes.forEach { value ->
                Hash.write(writer, value)
            }
            Hash.write(writer, instance.currentBlockHash)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
