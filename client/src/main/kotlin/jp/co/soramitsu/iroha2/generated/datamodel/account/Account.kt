//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.account

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet

/**
 * Account
 *
 * Generated from 'iroha_data_model::account::Account' regular structure
 */
public class Account(
  public val id: Id,
  public val assets: MutableMap<jp.co.soramitsu.iroha2.generated.datamodel.asset.Id, Asset>,
  public val signatories: MutableList<PublicKey>,
  public val permissionTokens: MutableSet<PermissionToken>,
  public val signatureCheckCondition: SignatureCheckCondition,
  public val metadata: Metadata
)
