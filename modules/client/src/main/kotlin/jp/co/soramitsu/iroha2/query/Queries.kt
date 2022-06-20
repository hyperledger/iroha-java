package jp.co.soramitsu.iroha2.query

import jp.co.soramitsu.iroha2.evaluatesTo
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
import jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindDomainKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.peer.FindAllPeers
import jp.co.soramitsu.iroha2.generated.datamodel.query.permissions.FindPermissionTokensByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.role.FindRolesByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindTransactionByHash
import jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindTransactionsByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.trigger.FindAllActiveTriggerIds
import jp.co.soramitsu.iroha2.generated.datamodel.query.trigger.FindTriggerById
import jp.co.soramitsu.iroha2.generated.datamodel.query.trigger.FindTriggerKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id as DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id as TriggerId

/**
 * Queries are sent to an Iroha peer and prompt a response with details from the current world state view.
 */
object Queries {

    /**
     * Find all accounts registered globally in the blockchain.
     */
    fun findAllAccounts(): QueryBox.FindAllAccounts {
        return QueryBox.FindAllAccounts(FindAllAccounts())
    }

    /**
     * Return the full account information corresponding to the given [AccountId]
     */
    fun findAccountById(accountId: AccountId): QueryBox.FindAccountById {
        return QueryBox.FindAccountById(FindAccountById(accountId.evaluatesTo()))
    }

    /**
     * Return the value keyed by the provided [Name] for the given [AccountId].
     */
    fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: Name): QueryBox.FindAccountKeyValueByIdAndKey {
        return QueryBox.FindAccountKeyValueByIdAndKey(
            FindAccountKeyValueByIdAndKey(
                accountId.evaluatesTo(),
                key.evaluatesTo()
            )
        )
    }

    /**
     * Return all of the accounts that have the given [Name].
     */
    fun findAccountsByName(name: Name): QueryBox.FindAccountsByName {
        return QueryBox.FindAccountsByName(FindAccountsByName(name.evaluatesTo()))
    }

    /**
     * Return all accounts that belong to a specific domain [DomainId].
     */
    fun findAccountsByDomainId(domainId: DomainId): QueryBox.FindAccountsByDomainId {
        return QueryBox.FindAccountsByDomainId(
            FindAccountsByDomainId(domainId.evaluatesTo())
        )
    }

    /**
     * Return all known assets by value.
     */
    fun findAllAssets(): QueryBox.FindAllAssets {
        return QueryBox.FindAllAssets(FindAllAssets())
    }

    /**
     * Return the aggregated data about the [AssetId] usage across the network.
     */
    fun findAssetById(assetId: AssetId): QueryBox.FindAssetById {
        return QueryBox.FindAssetById(FindAssetById(assetId.evaluatesTo()))
    }

    /**
     * Return all assets that are registered in the given domain [DomainId].
     */
    fun findAssetsByDomainId(domainId: DomainId): QueryBox.FindAssetsByDomainId {
        return QueryBox.FindAssetsByDomainId(FindAssetsByDomainId(domainId.evaluatesTo()))
    }

    /**
     * Search the network for all assets that match the given [Name].
     */
    fun findAssetsByName(name: Name): QueryBox.FindAssetsByName {
        return QueryBox.FindAssetsByName(FindAssetsByName(name.evaluatesTo()))
    }

    /**
     * Return all of the assets that belong to a given [AccountId].
     */
    fun findAssetsByAccountId(accountId: AccountId): QueryBox.FindAssetsByAccountId {
        return QueryBox.FindAssetsByAccountId(FindAssetsByAccountId(accountId.evaluatesTo()))
    }

    /**
     * Search for all of the assets that have the given [DefinitionID].
     */
    fun findAssetsByAssetDefinitionId(assetDefinition: DefinitionId): QueryBox.FindAssetsByAssetDefinitionId {
        return QueryBox.FindAssetsByAssetDefinitionId(
            FindAssetsByAssetDefinitionId(assetDefinition.evaluatesTo())
        )
    }

    /**
     * Search the domain with the [DomainId] for assets that have the given [DefinitionId].
     */
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

    /**
     * Return all known asset definitions by value.
     */
    fun findAllAssetsDefinitions(): QueryBox.FindAllAssetsDefinitions {
        return QueryBox.FindAllAssetsDefinitions(
            FindAllAssetsDefinitions()
        )
    }

    /**
     * Return the asset quantity for the given asset with [AssetId].
     */
    fun findAssetQuantityById(assetId: AssetId): QueryBox.FindAssetQuantityById {
        return QueryBox.FindAssetQuantityById(
            FindAssetQuantityById(assetId.evaluatesTo())
        )
    }

    /**
     * Return the value keyed by the given [Name] in the metadata of the asset corresponding to the given [AssetId].
     */
    fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: Name): QueryBox.FindAssetKeyValueByIdAndKey {
        return QueryBox.FindAssetKeyValueByIdAndKey(
            FindAssetKeyValueByIdAndKey(
                assetId.evaluatesTo(),
                key.evaluatesTo()
            )
        )
    }

    /**
     * Return the value keyed by the given [Name] in the metadata of the asset definition corresponding to the given [DefinitionId].
     */
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

    /**
     * Return all of the known registered domains.
     */
    fun findAllDomains(): QueryBox.FindAllDomains {
        return QueryBox.FindAllDomains(FindAllDomains())
    }

    /**
     * Return the domain corresponding to the given [DomainId].
     */
    fun findDomainById(domainId: DomainId): QueryBox.FindDomainById {
        return QueryBox.FindDomainById(FindDomainById(domainId.evaluatesTo()))
    }

    /**
     * Return all known peers identified by their key and accompanied by the address of the API endpoint of each.
     */
    fun findAllPeers(): QueryBox.FindAllPeers {
        return QueryBox.FindAllPeers(FindAllPeers())
    }

    /**
     * Return the full set of transactions that an account with [AccountId] has submitted throughout the existence of the blockchain.
     */
    fun findTransactionsByAccountId(accountId: AccountId): QueryBox.FindTransactionsByAccountId {
        return QueryBox.FindTransactionsByAccountId(
            FindTransactionsByAccountId(accountId.evaluatesTo())
        )
    }

    /**
     * Return all of the permission tokens granted to the specified [AccountId].
     */
    fun findPermissionTokensByAccountId(accountId: AccountId): QueryBox.FindPermissionTokensByAccountId {
        return QueryBox.FindPermissionTokensByAccountId(
            FindPermissionTokensByAccountId(accountId.evaluatesTo())
        )
    }

    /**
     * Return the transaction by [Hash].
     */
    fun findTransactionByHash(hash: Hash): QueryBox.FindTransactionByHash {
        return QueryBox.FindTransactionByHash(
            FindTransactionByHash(hash.evaluatesTo())
        )
    }

    /**
     * Return all of the role IDs that are attached to the given [AccountId].
     */
    fun findRolesByAccountId(accountId: AccountId): QueryBox.FindRolesByAccountId {
        return QueryBox.FindRolesByAccountId(
            FindRolesByAccountId(accountId.evaluatesTo())
        )
    }

    /**
     * Return the value keyed by the given [Name] in the domain corresponding to the given [DomainId].
     */
    fun findDomainKeyValueByIdAndKey(id: DomainId, key: Name): QueryBox.FindDomainKeyValueByIdAndKey {
        return QueryBox.FindDomainKeyValueByIdAndKey(
            FindDomainKeyValueByIdAndKey(
                id.evaluatesTo(),
                key.evaluatesTo()
            )
        )
    }

    /**
     * Find the value corresponding to the given key [Name] in the metadata of the trigger with the given [TriggerId].
     */
    fun findTriggerKeyValueByIdAndKey(id: TriggerId, key: Name): QueryBox.FindTriggerKeyValueByIdAndKey {
        return QueryBox.FindTriggerKeyValueByIdAndKey(
            FindTriggerKeyValueByIdAndKey(
                id.evaluatesTo(),
                key.evaluatesTo()
            )
        )
    }

    /**
     * Find the trigger with the given [TriggerId].
     */
    fun findTriggerById(id: TriggerId): QueryBox.FindTriggerById {
        return QueryBox.FindTriggerById(
            FindTriggerById(id.evaluatesTo())
        )
    }

    /**
     * Find all currently active triggers, that is, triggers that have not expired at the time of the query.
     */
    fun findAllActiveTriggerIds(): QueryBox.FindAllActiveTriggerIds {
        return QueryBox.FindAllActiveTriggerIds(
            FindAllActiveTriggerIds()
        )
    }
}
