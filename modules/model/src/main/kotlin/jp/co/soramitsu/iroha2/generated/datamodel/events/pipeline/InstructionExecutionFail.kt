//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.wrapException
import kotlin.String

/**
 * InstructionExecutionFail
 *
 * Generated from 'iroha_data_model::events::pipeline::InstructionExecutionFail' regular structure
 */
public data class InstructionExecutionFail(
    public val instruction: Instruction,
    public val reason: String
) {
    public companion object :
        ScaleReader<InstructionExecutionFail>,
        ScaleWriter<InstructionExecutionFail> {
        public override fun read(reader: ScaleCodecReader): InstructionExecutionFail = try {
            InstructionExecutionFail(
                Instruction.read(reader),
                reader.readString(),
            )
        } catch (ex: Exception) {
            throw wrapException(ex)
        }

        public override fun write(writer: ScaleCodecWriter, instance: InstructionExecutionFail) = try {
            Instruction.write(writer, instance.instruction)
            writer.writeAsList(instance.reason.toByteArray(Charsets.UTF_8))
        } catch (ex: Exception) {
            throw wrapException(ex)
        }
    }
}
