//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import jp.co.soramitsu.iroha2.generated.datamodel.Value
import kotlin.collections.MutableList

/**
 * ContainsAny
 *
 * Generated from 'iroha_data_model::expression::ContainsAny' regular structure
 */
public class ContainsAny(
  public val collection: EvaluatesTo<MutableList<Value>>,
  public val elements: EvaluatesTo<MutableList<Value>>
)
