//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import java.util.List
import jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAny.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAny.Value

/**
 * ContainsAny
 *
 * Generated from 'iroha_data_model::expression::ContainsAny' regular structure
 */
public class ContainsAny(
  public val collection: EvaluatesTo<List<Value>>,
  public val elements: EvaluatesTo<List<Value>>
)
