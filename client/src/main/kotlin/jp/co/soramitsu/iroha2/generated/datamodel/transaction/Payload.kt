//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import kotlin.Any
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map

/**
 * Payload
 *
 * Generated from 'iroha_data_model::transaction::Payload' regular structure
 */
public class Payload(
  public val accountId: Any,
  public val instructions: List<Any>,
  public val creationTime: Int,
  public val timeToLiveMs: Int,
  public val metadata: Map<String, Any>
)
