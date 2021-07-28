//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import kotlin.String

/**
 * InstructionExecutionFail
 *
 * Generated from 'iroha_data_model::events::pipeline::InstructionExecutionFail' regular structure
 */
public class InstructionExecutionFail(
    public val instruction: Instruction,
    public val reason: String
) {
    public companion object :
        ScaleReader<InstructionExecutionFail>,
        ScaleWriter<InstructionExecutionFail> {
        public override fun read(reader: ScaleCodecReader): InstructionExecutionFail =
            InstructionExecutionFail(
                Instruction.read(reader) as Instruction,
                reader.readString(),
            )

        public override fun write(writer: ScaleCodecWriter, instance: InstructionExecutionFail) {
            Instruction.write(writer, instance.instruction)
            writer.writeAsList(instance.reason.toByteArray(Charsets.UTF_8))
        }
    }
}
