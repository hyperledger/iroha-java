// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Unit

/**
 * Less
 *
 * Generated from 'iroha_data_model::expression::Less' regular structure
 */
public class Less(
  private val left: EvaluatesTo,
  private val right: EvaluatesTo
) {
  public companion object CODEC : ScaleReader<Less>, ScaleWriter<Less> {
    public override fun read(reader: ScaleCodecReader): Less =
        Less(jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.read(reader),
        jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.read(reader))

    public override fun write(writer: ScaleCodecWriter, instance: Less): Unit {
      jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.write(writer, instance.left)
      jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.write(writer,
          instance.right)
    }
  }
}