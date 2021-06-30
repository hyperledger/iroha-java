// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.query.asset

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleReader
import jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo

/**
 * FindAssetsByAccountId
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByAccountId' regular structure
 */
public class FindAssetsByAccountId(
  private val accountId: EvaluatesTo
) {
  public companion object READER : ScaleReader<FindAssetsByAccountId> {
    public override fun read(reader: ScaleCodecReader): FindAssetsByAccountId =
        FindAssetsByAccountId(jp.co.soramitsu.schema.generated.datamodel.expression.EvaluatesTo.READER.read(reader))
  }
}
