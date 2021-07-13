//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import java.util.List
import java.util.Map
import java.util.Set
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account.PermissionToken
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account.SignatureCheckCondition

/**
 * Account
 *
 * Generated from 'iroha_data_model::account::Account' regular structure
 */
public class Account(
  public val id: Id,
  public val assets: Map<Id, Asset>,
  public val signatories: List<PublicKey>,
  public val permissionTokens: Set<PermissionToken>,
  public val signatureCheckCondition: SignatureCheckCondition,
  public val metadata: Metadata
)
