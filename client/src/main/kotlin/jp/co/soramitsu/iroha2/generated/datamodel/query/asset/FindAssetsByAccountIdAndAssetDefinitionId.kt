//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo

/**
 * FindAssetsByAccountIdAndAssetDefinitionId
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByAccountIdAndAssetDefinitionId'
 * regular structure
 */
public class FindAssetsByAccountIdAndAssetDefinitionId(
  public val accountId: EvaluatesTo<Id>,
  public val assetDefinitionId: EvaluatesTo<DefinitionId>
)
