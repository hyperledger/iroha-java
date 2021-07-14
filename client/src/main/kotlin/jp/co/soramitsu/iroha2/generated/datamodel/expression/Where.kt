//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import jp.co.soramitsu.iroha2.generated.datamodel.Value
import kotlin.String
import kotlin.collections.MutableMap

/**
 * Where
 *
 * Generated from 'iroha_data_model::expression::Where' regular structure
 */
public class Where(
  public val expression: EvaluatesTo<Value>,
  public val values: MutableMap<String, EvaluatesTo<Value>>
)
