//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.transaction

import jp.co.soramitsu.iroha2.generated.crypto.Signature
import kotlin.collections.MutableList

/**
 * Transaction
 *
 * Generated from 'iroha_data_model::transaction::Transaction' regular structure
 */
public class Transaction(
  public val payload: Payload,
  public val signatures: MutableList<Signature>
)
