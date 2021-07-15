//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

/**
 * QueryBox
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox' enum
 */
public sealed class QueryBox {
  /**
   * 'FindAllAccounts' variant
   */
  public class FindAllAccounts(
    private val findAllAccounts:
        jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAllAccounts
  ) : QueryBox()

  /**
   * 'FindAccountById' variant
   */
  public class FindAccountById(
    private val findAccountById:
        jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountById
  ) : QueryBox()

  /**
   * 'FindAccountKeyValueByIdAndKey' variant
   */
  public class FindAccountKeyValueByIdAndKey(
    private val findAccountKeyValueByIdAndKey:
        jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountKeyValueByIdAndKey
  ) : QueryBox()

  /**
   * 'FindAccountsByName' variant
   */
  public class FindAccountsByName(
    private val findAccountsByName:
        jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByName
  ) : QueryBox()

  /**
   * 'FindAccountsByDomainName' variant
   */
  public class FindAccountsByDomainName(
    private val findAccountsByDomainName:
        jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByDomainName
  ) : QueryBox()

  /**
   * 'FindAllAssets' variant
   */
  public class FindAllAssets(
    private val findAllAssets: jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAllAssets
  ) : QueryBox()

  /**
   * 'FindAllAssetsDefinitions' variant
   */
  public class FindAllAssetsDefinitions(
    private val findAllAssetsDefinitions:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAllAssetsDefinitions
  ) : QueryBox()

  /**
   * 'FindAssetById' variant
   */
  public class FindAssetById(
    private val findAssetById: jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetById
  ) : QueryBox()

  /**
   * 'FindAssetsByName' variant
   */
  public class FindAssetsByName(
    private val findAssetsByName:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByName
  ) : QueryBox()

  /**
   * 'FindAssetsByAccountId' variant
   */
  public class FindAssetsByAccountId(
    private val findAssetsByAccountId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAccountId
  ) : QueryBox()

  /**
   * 'FindAssetsByAssetDefinitionId' variant
   */
  public class FindAssetsByAssetDefinitionId(
    private val findAssetsByAssetDefinitionId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAssetDefinitionId
  ) : QueryBox()

  /**
   * 'FindAssetsByDomainName' variant
   */
  public class FindAssetsByDomainName(
    private val findAssetsByDomainName:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainName
  ) : QueryBox()

  /**
   * 'FindAssetsByAccountIdAndAssetDefinitionId' variant
   */
  public class FindAssetsByAccountIdAndAssetDefinitionId(
    private val findAssetsByAccountIdAndAssetDefinitionId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAccountIdAndAssetDefinitionId
  ) : QueryBox()

  /**
   * 'FindAssetsByDomainNameAndAssetDefinitionId' variant
   */
  public class FindAssetsByDomainNameAndAssetDefinitionId(
    private val findAssetsByDomainNameAndAssetDefinitionId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainNameAndAssetDefinitionId
  ) : QueryBox()

  /**
   * 'FindAssetQuantityById' variant
   */
  public class FindAssetQuantityById(
    private val findAssetQuantityById:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetQuantityById
  ) : QueryBox()

  /**
   * 'FindAssetKeyValueByIdAndKey' variant
   */
  public class FindAssetKeyValueByIdAndKey(
    private val findAssetKeyValueByIdAndKey:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetKeyValueByIdAndKey
  ) : QueryBox()

  /**
   * 'FindAllDomains' variant
   */
  public class FindAllDomains(
    private val findAllDomains:
        jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindAllDomains
  ) : QueryBox()

  /**
   * 'FindDomainByName' variant
   */
  public class FindDomainByName(
    private val findDomainByName:
        jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindDomainByName
  ) : QueryBox()

  /**
   * 'FindAllPeers' variant
   */
  public class FindAllPeers(
    private val findAllPeers: jp.co.soramitsu.iroha2.generated.datamodel.query.peer.FindAllPeers
  ) : QueryBox()

  /**
   * 'FindTransactionsByAccountId' variant
   */
  public class FindTransactionsByAccountId(
    private val findTransactionsByAccountId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindTransactionsByAccountId
  ) : QueryBox()

  /**
   * 'FindPermissionTokensByAccountId' variant
   */
  public class FindPermissionTokensByAccountId(
    private val findPermissionTokensByAccountId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.permissions.FindPermissionTokensByAccountId
  ) : QueryBox()
}
