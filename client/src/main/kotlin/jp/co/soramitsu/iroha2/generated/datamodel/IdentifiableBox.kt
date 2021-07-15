//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel

/**
 * IdentifiableBox
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox' enum
 */
public sealed class IdentifiableBox {
  /**
   * 'Account' variant
   */
  public class Account(
    private val account: jp.co.soramitsu.iroha2.generated.datamodel.account.Account
  ) : IdentifiableBox()

  /**
   * 'NewAccount' variant
   */
  public class NewAccount(
    private val newAccount: jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount
  ) : IdentifiableBox()

  /**
   * 'Asset' variant
   */
  public class Asset(
    private val asset: jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
  ) : IdentifiableBox()

  /**
   * 'AssetDefinition' variant
   */
  public class AssetDefinition(
    private val assetDefinition: jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
  ) : IdentifiableBox()

  /**
   * 'Domain' variant
   */
  public class Domain(
    private val domain: jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain
  ) : IdentifiableBox()

  /**
   * 'Peer' variant
   */
  public class Peer(
    private val peer: jp.co.soramitsu.iroha2.generated.datamodel.peer.Peer
  ) : IdentifiableBox()

  /**
   * 'World' variant
   */
  public class World : IdentifiableBox()
}
