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
 * RawGenesisBlock
 *
 * Generated from 'RawGenesisBlock' regular structure
 */
public data class RawGenesisBlock(
    public val transactions: List<List<InstructionExpr>>,
    public val executor: ExecutorMode,
) {
    public companion object : ScaleReader<RawGenesisBlock>, ScaleWriter<RawGenesisBlock> {
        override fun read(reader: ScaleCodecReader): RawGenesisBlock = try {
            RawGenesisBlock(
                reader.readVec(reader.readCompactInt()) { reader.readVec(reader.readCompactInt()) { InstructionExpr.read(reader) } },
                ExecutorMode.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: RawGenesisBlock): Unit = try {
            writer.writeCompact(instance.transactions.size)
            instance.transactions.forEach { value ->
                writer.writeCompact(value.size)
                value.forEach { value ->
                    InstructionExpr.write(writer, value)
                }
            }
            ExecutorMode.write(writer, instance.executor)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
