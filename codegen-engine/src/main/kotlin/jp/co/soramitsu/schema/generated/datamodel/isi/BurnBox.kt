// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo

/**
 * BurnBox
 *
 * Generated from 'iroha_data_model::isi::BurnBox' regular structure
 */
public class BurnBox(
  private val `object`: EvaluatesTo,
  private val destinationId: EvaluatesTo
) {
  public companion object READER : ScaleReader<BurnBox> {
    public override fun read(reader: ScaleCodecReader): BurnBox =
        BurnBox(jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader),
        jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader))
  }
}
