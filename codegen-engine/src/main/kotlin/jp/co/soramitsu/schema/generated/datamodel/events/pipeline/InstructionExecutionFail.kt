// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.datamodel.isi.Instruction
import kotlin.String
import kotlin.Unit

/**
 * InstructionExecutionFail
 *
 * Generated from 'iroha_data_model::events::pipeline::InstructionExecutionFail' regular structure
 */
public class InstructionExecutionFail(
  private val instruction: Instruction,
  private val reason: String
) {
  public companion object CODEC : ScaleReader<InstructionExecutionFail>,
      ScaleWriter<InstructionExecutionFail> {
    public override fun read(reader: ScaleCodecReader): InstructionExecutionFail =
        InstructionExecutionFail(jp.co.soramitsu.schema.generated.datamodel.isi.Instruction.read(reader),
        reader.readString())

    public override fun write(writer: ScaleCodecWriter, instance: InstructionExecutionFail): Unit {
      jp.co.soramitsu.schema.generated.datamodel.isi.Instruction.write(writer,
          instance.`instruction`)
      writer.writeString(instance.`reason`)
    }
  }
}
