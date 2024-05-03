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
    public val transactions: List<GenesisTransactionBuilder>,
    public val executor: Executor,
) {
    public companion object : ScaleReader<RawGenesisBlock>, ScaleWriter<RawGenesisBlock> {
        override fun read(reader: ScaleCodecReader): RawGenesisBlock = try {
            RawGenesisBlock(
                reader.readVec(reader.readCompactInt()) { GenesisTransactionBuilder.read(reader) },
                Executor.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: RawGenesisBlock): Unit = try {
            writer.writeCompact(instance.transactions.size)
            instance.transactions.forEach { value ->
                GenesisTransactionBuilder.write(writer, value)
            }
            Executor.write(writer, instance.executor)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
