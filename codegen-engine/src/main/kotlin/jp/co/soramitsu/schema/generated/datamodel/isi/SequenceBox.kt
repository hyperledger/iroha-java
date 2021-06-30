// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Unit
import kotlin.collections.List

/**
 * SequenceBox
 *
 * Generated from 'iroha_data_model::isi::SequenceBox' regular structure
 */
public class SequenceBox(
  private val instructions: List<Instruction>
) : ScaleReader<SequenceBox>, ScaleWriter<SequenceBox> {
  public override fun read(reader: ScaleCodecReader): SequenceBox =
      SequenceBox(instructions.read(reader))

  public override fun write(writer: ScaleCodecWriter, instance: SequenceBox): Unit {
    instructions.write(writer, instance.instructions)
  }
}
