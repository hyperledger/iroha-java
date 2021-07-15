//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import kotlin.String

/**
 * IdBox
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.IdBox' enum
 */
public sealed class IdBox {
  /**
   * 'AccountId' variant
   */
  public class AccountId(
    private val accountId: Id
  ) : IdBox()

  /**
   * 'AssetId' variant
   */
  public class AssetId(
    private val assetId: jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
  ) : IdBox()

  /**
   * 'AssetDefinitionId' variant
   */
  public class AssetDefinitionId(
    private val assetDefinitionId: DefinitionId
  ) : IdBox()

  /**
   * 'DomainName' variant
   */
  public class DomainName(
    private val domainName: String
  ) : IdBox()

  /**
   * 'PeerId' variant
   */
  public class PeerId(
    private val peerId: jp.co.soramitsu.iroha2.generated.datamodel.peer.Id
  ) : IdBox()

  /**
   * 'WorldId' variant
   */
  public class WorldId : IdBox()
}
