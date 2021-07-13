//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import java.util.List
import jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount.Id
import jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount.PublicKey

/**
 * NewAccount
 *
 * Generated from 'iroha_data_model::account::NewAccount' regular structure
 */
public class NewAccount(
  public val id: Id,
  public val signatories: List<PublicKey>,
  public val metadata: Metadata
)
