//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.String

/**
 * SetKeyValueBox
 *
 * Generated from 'iroha_data_model::isi::SetKeyValueBox' regular structure
 */
public class SetKeyValueBox(
  public val objectId: EvaluatesTo<IdBox>,
  public val key: EvaluatesTo<String>,
  public val `value`: EvaluatesTo<Value>
)
