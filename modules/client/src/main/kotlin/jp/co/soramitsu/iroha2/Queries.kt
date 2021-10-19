package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountById
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByDomainName
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
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainName
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainNameAndAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByName
import jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindAllDomains
import jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindDomainByName
import jp.co.soramitsu.iroha2.generated.datamodel.query.peer.FindAllPeers
import jp.co.soramitsu.iroha2.generated.datamodel.query.permissions.FindPermissionTokensByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindTransactionByHash
import jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindTransactionsByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

object Queries {

    fun findAllAccounts(): QueryBox.FindAllAccounts {
        return QueryBox.FindAllAccounts(FindAllAccounts())
    }

    fun findAccountById(accountId: AccountId): QueryBox.FindAccountById {
        return QueryBox.FindAccountById(FindAccountById(accountId.evaluatesTo()))
    }

    fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: String): QueryBox.FindAccountKeyValueByIdAndKey {
        return QueryBox.FindAccountKeyValueByIdAndKey(
            FindAccountKeyValueByIdAndKey(
                accountId.evaluatesTo(),
                key.evaluatesTo()
            )
        )
    }

    fun findAccountsByName(name: String): QueryBox.FindAccountsByName {
        return QueryBox.FindAccountsByName(FindAccountsByName(name.evaluatesTo()))
    }

    fun findAccountsByDomainName(domain: String): QueryBox.FindAccountsByDomainName {
        return QueryBox.FindAccountsByDomainName(
            FindAccountsByDomainName(domain.evaluatesTo())
        )
    }

    fun findAllAssets(): QueryBox.FindAllAssets {
        return QueryBox.FindAllAssets(FindAllAssets())
    }

    fun findAssetById(assetId: AssetId): QueryBox.FindAssetById {
        return QueryBox.FindAssetById(FindAssetById(assetId.evaluatesTo()))
    }

    fun findAssetsByDomainName(domain: String): QueryBox.FindAssetsByDomainName {
        return QueryBox.FindAssetsByDomainName(FindAssetsByDomainName(domain.evaluatesTo()))
    }

    fun findAssetsByName(name: String): QueryBox.FindAssetsByName {
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

    fun findAssetsByDomainNameAndAssetDefinitionId(
        domain: String,
        assetDefinition: DefinitionId
    ): QueryBox.FindAssetsByDomainNameAndAssetDefinitionId {
        return QueryBox.FindAssetsByDomainNameAndAssetDefinitionId(
            FindAssetsByDomainNameAndAssetDefinitionId(
                domain.evaluatesTo(),
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

    fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: String): QueryBox.FindAssetKeyValueByIdAndKey {
        return QueryBox.FindAssetKeyValueByIdAndKey(
            FindAssetKeyValueByIdAndKey(
                assetId.evaluatesTo(),
                key.evaluatesTo()
            )
        )
    }

    fun findAssetDefinitionKeyValueByIdAndKey(
        assetDefinition: DefinitionId,
        key: String
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

    fun findDomainByName(name: String): QueryBox.FindDomainByName {
        return QueryBox.FindDomainByName(FindDomainByName(name.evaluatesTo()))
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
