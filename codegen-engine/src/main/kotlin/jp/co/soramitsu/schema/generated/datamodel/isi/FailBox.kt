// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.String
import kotlin.Unit

/**
 * FailBox
 *
 * Generated from 'iroha_data_model::isi::FailBox' regular structure
 */
public class FailBox(
  private val message: String
) {
  public companion object CODEC : ScaleReader<FailBox>, ScaleWriter<FailBox> {
    public override fun read(reader: ScaleCodecReader): FailBox = FailBox(reader.readString())

    public override fun write(writer: ScaleCodecWriter, instance: FailBox): Unit {
      writer.writeString(instance.message)
    }
  }
}