//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.String
import kotlin.Unit

/**
 * NotPermittedFail
 *
 * Generated from 'iroha_data_model::events::pipeline::NotPermittedFail' regular structure
 */
public class NotPermittedFail(
  public val reason: String
) {
  public companion object : ScaleReader<NotPermittedFail>, ScaleWriter<NotPermittedFail> {
    public override fun read(reader: ScaleCodecReader): NotPermittedFail =
        NotPermittedFail(jp.co.soramitsu.iroha2.scale.StringReader.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: NotPermittedFail): Unit {
      jp.co.soramitsu.iroha2.scale.StringWriter.write(writer, instance.reason)
    }
  }
}
