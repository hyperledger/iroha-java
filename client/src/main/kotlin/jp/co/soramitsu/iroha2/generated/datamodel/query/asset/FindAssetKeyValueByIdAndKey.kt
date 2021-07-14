//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.asset

import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String

/**
 * FindAssetKeyValueByIdAndKey
 *
 * Generated from 'iroha_data_model::query::asset::FindAssetKeyValueByIdAndKey' regular structure
 */
public class FindAssetKeyValueByIdAndKey(
  public val id: EvaluatesTo<Id>,
  public val key: EvaluatesTo<String>
)
