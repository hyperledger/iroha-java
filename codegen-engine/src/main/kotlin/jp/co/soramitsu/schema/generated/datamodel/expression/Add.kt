// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader

/**
 * Add
 *
 * Generated from 'iroha_data_model::expression::Add' regular structure
 */
public class Add(
  private val left: EvaluatesTo,
  private val right: EvaluatesTo
) {
  public companion object READER : ScaleReader<Add> {
    public override fun read(reader: ScaleCodecReader): Add =
        Add(jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader),
        jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader))
  }
}
