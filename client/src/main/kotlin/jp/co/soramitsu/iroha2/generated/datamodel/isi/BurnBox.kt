//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo

/**
 * BurnBox
 *
 * Generated from 'iroha_data_model::isi::BurnBox' regular structure
 */
public class BurnBox(
  public val `object`: EvaluatesTo<Value>,
  public val destinationId: EvaluatesTo<IdBox>
)