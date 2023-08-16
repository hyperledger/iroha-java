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
 * SequenceBox
 *
 * Generated from 'SequenceBox' regular structure
 */
public data class SequenceBox(
    public val instructions: List<InstructionBox>,
) {
    public companion object : ScaleReader<SequenceBox>, ScaleWriter<SequenceBox> {
        override fun read(reader: ScaleCodecReader): SequenceBox = try {
            SequenceBox(
                reader.readVec(reader.readCompactInt()) { InstructionBox.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        override fun write(writer: ScaleCodecWriter, instance: SequenceBox): Unit = try {
            writer.writeCompact(instance.instructions.size)
            instance.instructions.forEach { value ->
                InstructionBox.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
