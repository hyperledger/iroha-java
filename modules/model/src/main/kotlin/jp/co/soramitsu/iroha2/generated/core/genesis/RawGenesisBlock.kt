//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.core.genesis

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * RawGenesisBlock
 *
 * Generated from 'iroha_core::genesis::RawGenesisBlock' regular structure
 */
public data class RawGenesisBlock(
    public val transactions: List<GenesisTransaction>
) {
    public companion object : ScaleReader<RawGenesisBlock>, ScaleWriter<RawGenesisBlock> {
        public override fun read(reader: ScaleCodecReader): RawGenesisBlock = try {
            RawGenesisBlock(
                List(reader.readCompactInt()) { GenesisTransaction.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RawGenesisBlock) = try {
            writer.writeCompact(instance.transactions.size)
            instance.transactions.forEach { value -> GenesisTransaction.write(writer, value) }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
