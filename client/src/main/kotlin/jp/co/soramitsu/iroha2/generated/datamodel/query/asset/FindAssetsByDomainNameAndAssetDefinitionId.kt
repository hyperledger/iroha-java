//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String

/**
 * FindAssetsByDomainNameAndAssetDefinitionId
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetsByDomainNameAndAssetDefinitionId'
 * regular structure
 */
public class FindAssetsByDomainNameAndAssetDefinitionId(
  public val domainName: EvaluatesTo<String>,
  public val assetDefinitionId: EvaluatesTo<DefinitionId>
)
