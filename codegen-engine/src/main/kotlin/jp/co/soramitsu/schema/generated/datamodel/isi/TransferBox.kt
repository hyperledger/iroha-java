// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo

/**
 * TransferBox
 *
 * Generated from 'iroha_data_model::isi::TransferBox' regular structure
 */
public class TransferBox(
  private val sourceId: EvaluatesTo,
  private val `object`: EvaluatesTo,
  private val destinationId: EvaluatesTo
) {
  public companion object READER : ScaleReader<TransferBox> {
    public override fun read(reader: ScaleCodecReader): TransferBox =
        TransferBox(jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader),
        jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader),
        jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader))
  }
}
