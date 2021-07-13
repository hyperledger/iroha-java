//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import java.util.List
import jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAll.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAll.Value

/**
 * ContainsAll
 *
 * Generated from 'iroha_data_model::expression::ContainsAll' regular structure
 */
public class ContainsAll(
  public val collection: EvaluatesTo<List<Value>>,
  public val elements: EvaluatesTo<List<Value>>
)
