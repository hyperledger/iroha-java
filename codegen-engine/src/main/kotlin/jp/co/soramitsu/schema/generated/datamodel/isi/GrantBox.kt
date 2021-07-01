// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo
import kotlin.Unit

/**
 * GrantBox
 *
 * Generated from 'iroha_data_model::isi::GrantBox' regular structure
 */
public class GrantBox(
  private val `object`: EvaluatesTo,
  private val destinationId: EvaluatesTo
) {
  public companion object CODEC : ScaleReader<GrantBox>, ScaleWriter<GrantBox> {
    public override fun read(reader: ScaleCodecReader): GrantBox =
        GrantBox(jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.read(reader),
        jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: GrantBox): Unit {
      jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.write(writer,
          instance.`object`)
      jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.write(writer,
          instance.`destinationId`)
    }
  }
}
