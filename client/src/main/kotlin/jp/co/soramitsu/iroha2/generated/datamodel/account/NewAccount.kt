//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import kotlin.collections.MutableList

/**
 * NewAccount
 *
 * Generated from 'iroha_data_model::account::NewAccount' regular structure
 */
public class NewAccount(
  public val id: Id,
  public val signatories: MutableList<PublicKey>,
  public val metadata: Metadata
)
