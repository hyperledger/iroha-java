//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import jp.co.soramitsu.iroha2.generated.datamodel.Value
import kotlin.Boolean

/**
 * If
 *
 * Generated from 'iroha_data_model::expression::If' regular structure
 */
public class If(
  public val condition: EvaluatesTo<Boolean>,
  public val thenExpression: EvaluatesTo<Value>,
  public val elseExpression: EvaluatesTo<Value>
)
