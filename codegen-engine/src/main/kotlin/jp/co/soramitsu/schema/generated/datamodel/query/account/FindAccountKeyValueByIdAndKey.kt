// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.query.account

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo

/**
 * FindAccountKeyValueByIdAndKey
 *
 * Generated from 'iroha_data_model::query::account::FindAccountKeyValueByIdAndKey' regular
 * structure
 */
public class FindAccountKeyValueByIdAndKey(
  private val id: EvaluatesTo,
  private val key: EvaluatesTo
) {
  public companion object READER : ScaleReader<FindAccountKeyValueByIdAndKey> {
    public override fun read(reader: ScaleCodecReader): FindAccountKeyValueByIdAndKey =
        FindAccountKeyValueByIdAndKey(jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader),
        jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader))
  }
}
