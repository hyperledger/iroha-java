// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.isi

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo

/**
 * UnregisterBox
 *
 * Generated from 'iroha_data_model::isi::UnregisterBox' regular structure
 */
public class UnregisterBox(
  private val objectId: EvaluatesTo
) {
  public companion object READER : ScaleReader<UnregisterBox> {
    public override fun read(reader: ScaleCodecReader): UnregisterBox =
        UnregisterBox(jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader))
  }
}
