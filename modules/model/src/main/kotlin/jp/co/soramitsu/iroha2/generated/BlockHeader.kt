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
import kotlin.Long
import kotlin.Unit
import kotlin.collections.List

/**
 * BlockHeader
 *
 * Generated from 'BlockHeader' regular structure
 */
public data class BlockHeader(
    public val height: BigInteger,
    public val prevBlockHash: HashOf<SignedBlock>? = null,
    public val transactionsHash: HashOf<List<SignedTransaction>>,
    public val timestampMs: BigInteger,
    public val viewChangeIndex: Long,
    public val consensusEstimationMs: BigInteger,
) {
    public companion object : ScaleReader<BlockHeader>, ScaleWriter<BlockHeader> {
        override fun read(reader: ScaleCodecReader): BlockHeader = try {
            BlockHeader(
                reader.readUint64(),
                reader.readNullable(HashOf) as HashOf<SignedBlock>?,
                HashOf.read(reader) as HashOf<List<SignedTransaction>>,
                reader.readUint64(),
                reader.readUint32(),
                reader.readUint64(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: BlockHeader): Unit = try {
            writer.writeUint64(instance.height)
            writer.writeNullable(HashOf, instance.prevBlockHash)
            HashOf.write(writer, instance.transactionsHash)
            writer.writeUint64(instance.timestampMs)
            writer.writeUint32(instance.viewChangeIndex)
            writer.writeUint64(instance.consensusEstimationMs)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
