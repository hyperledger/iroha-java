//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.isi

import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import kotlin.Boolean

/**
 * If
 *
 * Generated from 'iroha_data_model::isi::If' regular structure
 */
public class If(
  public val condition: EvaluatesTo<Boolean>,
  public val then: Instruction,
  public val otherwise: Instruction?
)
