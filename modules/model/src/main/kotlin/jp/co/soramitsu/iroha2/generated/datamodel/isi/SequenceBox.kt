//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.collections.List

/**
 * SequenceBox
 *
 * Generated from 'iroha_data_model::isi::SequenceBox' regular structure
 */
public data class SequenceBox(
    public val instructions: List<Instruction>
) {
    public companion object : ScaleReader<SequenceBox>, ScaleWriter<SequenceBox> {
        public override fun read(reader: ScaleCodecReader): SequenceBox = try {
            SequenceBox(
                reader.readVec(reader.readCompactInt()) { Instruction.read(reader) },
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: SequenceBox) = try {
            writer.writeCompact(instance.instructions.size)
            instance.instructions.forEach { value ->
                Instruction.write(writer, value)
            }
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
