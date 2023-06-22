//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * RawGenesisBlock
 *
 * Generated from 'RawGenesisBlock' regular structure
 */
public data class RawGenesisBlock(
    public val transactions: List<List<InstructionBox>>,
    public val validator: ValidatorMode
) {
    public companion object : ScaleReader<RawGenesisBlock>, ScaleWriter<RawGenesisBlock> {
        public override fun read(reader: ScaleCodecReader): RawGenesisBlock = try {
            RawGenesisBlock(
                reader.readVec(reader.readCompactInt()) { reader.readVec(reader.readCompactInt()) { InstructionBox.read(reader) } },
                ValidatorMode.read(reader),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: RawGenesisBlock) = try {
            writer.writeCompact(instance.transactions.size)
            instance.transactions.forEach { value ->
                writer.writeCompact(value.size)
                value.forEach { value ->
                    InstructionBox.write(writer, value)
                }
            }
            ValidatorMode.write(writer, instance.validator)
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
