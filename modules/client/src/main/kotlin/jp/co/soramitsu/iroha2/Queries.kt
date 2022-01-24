package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountById
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByDomainId
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByName
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAllAccounts
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAllAssets
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAllAssetsDefinitions
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetDefinitionKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetQuantityById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainIdAndAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByName
import jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindAllDomains
import jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindDomainById
import jp.co.soramitsu.iroha2.generated.datamodel.query.peer.FindAllPeers
import jp.co.soramitsu.iroha2.generated.datamodel.query.permissions.FindPermissionTokensByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindTransactionByHash
import jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindTransactionsByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id as DomainId

object Queries {

    fun findAllAccounts(): QueryBox.FindAllAccounts {
        return QueryBox.FindAllAccounts(FindAllAccounts())
    }

    fun findAccountById(accountId: AccountId): QueryBox.FindAccountById {
        return QueryBox.FindAccountById(FindAccountById(accountId.evaluatesTo()))
    }

    fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: Name): QueryBox.FindAccountKeyValueByIdAndKey {
        return QueryBox.FindAccountKeyValueByIdAndKey(
            FindAccountKeyValueByIdAndKey(
                accountId.evaluatesTo(),
                key.evaluatesTo()
            )
        )
    }

    fun findAccountsByName(name: Name): QueryBox.FindAccountsByName {
        return QueryBox.FindAccountsByName(FindAccountsByName(name.evaluatesTo()))
    }

    fun findAccountsByDomainId(domainId: DomainId): QueryBox.FindAccountsByDomainId {
        return QueryBox.FindAccountsByDomainId(
            FindAccountsByDomainId(domainId.evaluatesTo())
        )
    }

    fun findAllAssets(): QueryBox.FindAllAssets {
        return QueryBox.FindAllAssets(FindAllAssets())
    }

    fun findAssetById(assetId: AssetId): QueryBox.FindAssetById {
        return QueryBox.FindAssetById(FindAssetById(assetId.evaluatesTo()))
    }

    fun findAssetsByDomainId(domainId: DomainId): QueryBox.FindAssetsByDomainId {
        return QueryBox.FindAssetsByDomainId(FindAssetsByDomainId(domainId.evaluatesTo()))
    }

    fun findAssetsByName(name: Name): QueryBox.FindAssetsByName {
        return QueryBox.FindAssetsByName(FindAssetsByName(name.evaluatesTo()))
    }

    fun findAssetsByAccountId(accountId: AccountId): QueryBox.FindAssetsByAccountId {
        return QueryBox.FindAssetsByAccountId(FindAssetsByAccountId(accountId.evaluatesTo()))
    }

    fun findAssetsByAssetDefinitionId(assetDefinition: DefinitionId): QueryBox.FindAssetsByAssetDefinitionId {
        return QueryBox.FindAssetsByAssetDefinitionId(
            FindAssetsByAssetDefinitionId(assetDefinition.evaluatesTo())
        )
    }

    fun findAssetsByDomainIdAndAssetDefinitionId(
        domainId: DomainId,
        assetDefinition: DefinitionId
    ): QueryBox.FindAssetsByDomainIdAndAssetDefinitionId {
        return QueryBox.FindAssetsByDomainIdAndAssetDefinitionId(
            FindAssetsByDomainIdAndAssetDefinitionId(
                domainId.evaluatesTo(),
                assetDefinition.evaluatesTo()
            )
        )
    }

    fun findAllAssetsDefinitions(): QueryBox.FindAllAssetsDefinitions {
        return QueryBox.FindAllAssetsDefinitions(
            FindAllAssetsDefinitions()
        )
    }

    fun findAssetQuantityById(assetId: AssetId): QueryBox.FindAssetQuantityById {
        return QueryBox.FindAssetQuantityById(
            FindAssetQuantityById(assetId.evaluatesTo())
        )
    }

    fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: Name): QueryBox.FindAssetKeyValueByIdAndKey {
        return QueryBox.FindAssetKeyValueByIdAndKey(
            FindAssetKeyValueByIdAndKey(
                assetId.evaluatesTo(),
                key.evaluatesTo()
            )
        )
    }

    fun findAssetDefinitionKeyValueByIdAndKey(
        assetDefinition: DefinitionId,
        key: Name
    ): QueryBox.FindAssetDefinitionKeyValueByIdAndKey {
        return QueryBox.FindAssetDefinitionKeyValueByIdAndKey(
            FindAssetDefinitionKeyValueByIdAndKey(
                assetDefinition.evaluatesTo(),
                key.evaluatesTo()
            )
        )
    }

    fun findAllDomains(): QueryBox.FindAllDomains {
        return QueryBox.FindAllDomains(FindAllDomains())
    }

    fun findDomainById(domainId: DomainId): QueryBox.FindDomainById {
        return QueryBox.FindDomainById(FindDomainById(domainId.evaluatesTo()))
    }

    fun findAllPeers(): QueryBox.FindAllPeers {
        return QueryBox.FindAllPeers(FindAllPeers())
    }

    fun findTransactionsByAccountId(accountId: AccountId): QueryBox.FindTransactionsByAccountId {
        return QueryBox.FindTransactionsByAccountId(
            FindTransactionsByAccountId(accountId.evaluatesTo())
        )
    }

    fun findPermissionTokensByAccountId(accountId: AccountId): QueryBox.FindPermissionTokensByAccountId {
        return QueryBox.FindPermissionTokensByAccountId(
            FindPermissionTokensByAccountId(accountId.evaluatesTo())
        )
    }

    fun findTransactionByHash(hash: Hash): QueryBox.FindTransactionByHash {
        return QueryBox.FindTransactionByHash(
            FindTransactionByHash(hash.evaluatesTo())
        )
    }
}
