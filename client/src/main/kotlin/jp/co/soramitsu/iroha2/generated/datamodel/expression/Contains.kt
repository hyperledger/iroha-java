//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import java.util.List
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Contains.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Contains.Value

/**
 * Contains
 *
 * Generated from 'iroha_data_model::expression::Contains' regular structure
 */
public class Contains(
  public val collection: EvaluatesTo<List<Value>>,
  public val element: EvaluatesTo<Value>
)
