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
) {
  public companion object CODEC : ScaleReader<SequenceBox>, ScaleWriter<SequenceBox> {
    public override fun read(reader: ScaleCodecReader): SequenceBox =
        SequenceBox(reader.read(io.emeraldpay.polkaj.scale.reader.ListReader(Instruction)))

    public override fun write(writer: ScaleCodecWriter, instance: SequenceBox): Unit {
      writer.write(io.emeraldpay.polkaj.scale.writer.ListWriter(Instruction),
          instance.`instructions`)
    }
  }
}
