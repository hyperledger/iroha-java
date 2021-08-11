//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.genesis

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.collections.MutableList

/**
 * RawGenesisBlock
 *
 * Generated from 'iroha::genesis::RawGenesisBlock' regular structure
 */
public class RawGenesisBlock(
    public val transactions: MutableList<GenesisTransaction>
) {
    public companion object : ScaleReader<RawGenesisBlock>, ScaleWriter<RawGenesisBlock> {
        public override fun read(reader: ScaleCodecReader): RawGenesisBlock = RawGenesisBlock(
            MutableList(reader.readCompactInt()) { GenesisTransaction.read(reader) as GenesisTransaction },
        )

        public override fun write(writer: ScaleCodecWriter, instance: RawGenesisBlock) {
            writer.writeCompact(instance.transactions.size)
            instance.transactions.forEach { value -> GenesisTransaction.write(writer, value) }
        }
    }
}
