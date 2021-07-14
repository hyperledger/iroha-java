//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query.account

import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String

/**
 * FindAccountKeyValueByIdAndKey
 *
 * Generated from 'iroha_data_model::query::account::FindAccountKeyValueByIdAndKey' regular
 * structure
 */
public class FindAccountKeyValueByIdAndKey(
  public val id: EvaluatesTo<Id>,
  public val key: EvaluatesTo<String>
)
