package jp.co.soramitsu.iroha2.query

import jp.co.soramitsu.iroha2.evaluatesTo
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountById
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByDomainId
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByName
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsWithAsset
import jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAllAccounts
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAllAssets
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAllAssetsDefinitions
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetDefinitionById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetDefinitionKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetQuantityById
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainIdAndAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByName
import jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindTotalAssetQuantityByAssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.block.FindAllBlockHeaders
import jp.co.soramitsu.iroha2.generated.datamodel.query.block.FindAllBlocks
import jp.co.soramitsu.iroha2.generated.datamodel.query.block.FindBlockHeaderByHash
import jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindAllDomains
import jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindDomainById
import jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindDomainKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.peer.FindAllParameters
import jp.co.soramitsu.iroha2.generated.datamodel.query.peer.FindAllPeers
import jp.co.soramitsu.iroha2.generated.datamodel.query.permissions.FindAllPermissionTokenDefinitions
import jp.co.soramitsu.iroha2.generated.datamodel.query.permissions.FindPermissionTokensByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.role.FindAllRoleIds
import jp.co.soramitsu.iroha2.generated.datamodel.query.role.FindAllRoles
import jp.co.soramitsu.iroha2.generated.datamodel.query.role.FindRoleByRoleId
import jp.co.soramitsu.iroha2.generated.datamodel.query.role.FindRolesByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindAllTransactions
import jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindTransactionByHash
import jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindTransactionsByAccountId
import jp.co.soramitsu.iroha2.generated.datamodel.query.trigger.FindAllActiveTriggerIds
import jp.co.soramitsu.iroha2.generated.datamodel.query.trigger.FindTriggerById
import jp.co.soramitsu.iroha2.generated.datamodel.query.trigger.FindTriggerKeyValueByIdAndKey
import jp.co.soramitsu.iroha2.generated.datamodel.query.trigger.FindTriggersByDomainId
import jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId

/**
 * Queries are sent to an Iroha peer and prompt a response with details from the current world state view
 */
object Queries {

    /**
     * Find all accounts registered globally in the blockchain
     */
    fun findAllAccounts() = QueryBox.FindAllAccounts(FindAllAccounts())

    /**
     * Return the full account information corresponding to the given [AccountId]
     */
    fun findAccountById(accountId: AccountId) = QueryBox.FindAccountById(FindAccountById(accountId.evaluatesTo()))

    /**
     * Return the value keyed by the provided [Name] for the given [AccountId]
     */
    fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: Name) = QueryBox.FindAccountKeyValueByIdAndKey(
        FindAccountKeyValueByIdAndKey(accountId.evaluatesTo(), key.evaluatesTo())
    )

    /**
     * Return all the accounts that have the given [Name]
     */
    fun findAccountsByName(name: Name) = QueryBox.FindAccountsByName(FindAccountsByName(name.evaluatesTo()))

    /**
     * Return all accounts that belong to a specific domain [DomainId]
     */
    fun findAccountsByDomainId(domainId: DomainId) = QueryBox.FindAccountsByDomainId(
        FindAccountsByDomainId(domainId.evaluatesTo())
    )

    /**
     * Return all accounts that belong to a specific asset definition [AssetDefinitionId]
     */
    fun findAccountsWithAsset(definitionId: AssetDefinitionId) = QueryBox.FindAccountsWithAsset(
        FindAccountsWithAsset(definitionId.evaluatesTo())
    )

    /**
     * Return all known assets by value
     */
    fun findAllAssets() = QueryBox.FindAllAssets(FindAllAssets())

    /**
     * Return the aggregated data about the [AssetId] usage across the network
     */
    fun findAssetById(assetId: AssetId) = QueryBox.FindAssetById(FindAssetById(assetId.evaluatesTo()))

    /**
     * Return all assets that are registered in the given domain [DomainId]
     */
    fun findAssetsByDomainId(domainId: DomainId) = QueryBox.FindAssetsByDomainId(
        FindAssetsByDomainId(domainId.evaluatesTo())
    )

    /**
     * Search the network for all assets that match the given [Name]
     */
    fun findAssetsByName(name: Name) = QueryBox.FindAssetsByName(FindAssetsByName(name.evaluatesTo()))

    /**
     * Return all the assets that belong to a given [AccountId]
     */
    fun findAssetsByAccountId(accountId: AccountId) = QueryBox.FindAssetsByAccountId(
        FindAssetsByAccountId(accountId.evaluatesTo())
    )

    /**
     * Search for all the assets that have the given [AssetDefinitionId]
     */
    fun findAssetsByAssetDefinitionId(assetDefinition: AssetDefinitionId) = QueryBox.FindAssetsByAssetDefinitionId(
        FindAssetsByAssetDefinitionId(assetDefinition.evaluatesTo())
    )

    /**
     * Search the domain with the [DomainId] for assets that have the given [AssetDefinitionId]
     */
    fun findAssetsByDomainIdAndAssetDefinitionId(
        domainId: DomainId,
        assetDefinition: AssetDefinitionId
    ) = QueryBox.FindAssetsByDomainIdAndAssetDefinitionId(
        FindAssetsByDomainIdAndAssetDefinitionId(
            domainId.evaluatesTo(),
            assetDefinition.evaluatesTo()
        )
    )

    /**
     * Return all known asset definitions by value
     */
    fun findAllAssetsDefinitions() = QueryBox.FindAllAssetsDefinitions(FindAllAssetsDefinitions())

    /**
     * Return the asset quantity for the given asset with [AssetId]
     */
    fun findAssetQuantityById(assetId: AssetId) = QueryBox.FindAssetQuantityById(
        FindAssetQuantityById(assetId.evaluatesTo())
    )

    /**
     * Return the asset quantity for the given asset with [AssetId]
     */
    fun findTotalAssetQuantityByAssetDefinitionId(
        definitionId: AssetDefinitionId
    ) = QueryBox.FindTotalAssetQuantityByAssetDefinitionId(
        FindTotalAssetQuantityByAssetDefinitionId(definitionId.evaluatesTo())
    )

    /**
     * Return the value keyed by the given [Name] in the metadata of the asset corresponding to the given [AssetId]
     */
    fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: Name) = QueryBox.FindAssetKeyValueByIdAndKey(
        FindAssetKeyValueByIdAndKey(assetId.evaluatesTo(), key.evaluatesTo())
    )

    /**
     * Return the value keyed by the given [Name]
     * in the metadata of the asset definition corresponding to the given [AssetDefinitionId]
     */
    fun findAssetDefinitionKeyValueByIdAndKey(
        assetDefinition: AssetDefinitionId,
        key: Name
    ) = QueryBox.FindAssetDefinitionKeyValueByIdAndKey(
        FindAssetDefinitionKeyValueByIdAndKey(
            assetDefinition.evaluatesTo(),
            key.evaluatesTo()
        )
    )

    /**
     * Return the asset definition corresponding to the given [AssetDefinitionId]
     */
    fun findAssetDefinitionById(definitionId: AssetDefinitionId) = QueryBox.FindAssetDefinitionById(
        FindAssetDefinitionById(definitionId.evaluatesTo())
    )

    /**
     * Return all the known registered domains
     */
    fun findAllDomains() = QueryBox.FindAllDomains(FindAllDomains())

    /**
     * Return the domain corresponding to the given [DomainId]
     */
    fun findDomainById(domainId: DomainId) = QueryBox.FindDomainById(FindDomainById(domainId.evaluatesTo()))

    /**
     * Return all known peers identified by their key and accompanied by the address of the API endpoint of each
     */
    fun findAllPeers() = QueryBox.FindAllPeers(FindAllPeers())

    /**
     * Return the full set of transactions that an account with [AccountId] has submitted throughout the existence of the blockchain
     */
    fun findTransactionsByAccountId(accountId: AccountId) = QueryBox.FindTransactionsByAccountId(
        FindTransactionsByAccountId(accountId.evaluatesTo())
    )

    /**
     * Return all the permission tokens granted to the specified [AccountId]
     */
    fun findPermissionTokensByAccountId(accountId: AccountId) = QueryBox.FindPermissionTokensByAccountId(
        FindPermissionTokensByAccountId(accountId.evaluatesTo())
    )

    /**
     * Return all the permission token definitions
     */
    fun findAllPermissionTokenDefinitions() = QueryBox.FindAllPermissionTokenDefinitions(
        FindAllPermissionTokenDefinitions()
    )

    /**
     * Return the transaction by [Hash]
     */
    fun findTransactionByHash(hash: Hash) = QueryBox.FindTransactionByHash(FindTransactionByHash(hash.evaluatesTo()))

    /**
     * Return all the role IDs that are attached to the given [AccountId]
     */
    fun findRolesByAccountId(accountId: AccountId) = QueryBox.FindRolesByAccountId(
        FindRolesByAccountId(accountId.evaluatesTo())
    )

    /**
     * Return all roles
     */
    fun findAllRoles() = QueryBox.FindAllRoles(FindAllRoles())

    /**
     * Return role by [RoleId]
     */
    fun findRoleByRoleId(roleId: RoleId) = QueryBox.FindRoleByRoleId(FindRoleByRoleId(roleId.evaluatesTo()))

    /**
     * Return all the role IDs
     */
    fun findAllRoleIds() = QueryBox.FindAllRoleIds(FindAllRoleIds())

    /**
     * Return the value keyed by the given [Name] in the domain corresponding to the given [DomainId]
     */
    fun findDomainKeyValueByIdAndKey(id: DomainId, key: Name) = QueryBox.FindDomainKeyValueByIdAndKey(
        FindDomainKeyValueByIdAndKey(id.evaluatesTo(), key.evaluatesTo())
    )

    /**
     * Return the value corresponding to the given key [Name] in the metadata of the trigger with the given [TriggerId]
     */
    fun findTriggerKeyValueByIdAndKey(id: TriggerId, key: Name) = QueryBox.FindTriggerKeyValueByIdAndKey(
        FindTriggerKeyValueByIdAndKey(id.evaluatesTo(), key.evaluatesTo())
    )

    /**
     * Return the trigger with the given [TriggerId]
     */
    fun findTriggerById(id: TriggerId) = QueryBox.FindTriggerById(FindTriggerById(id.evaluatesTo()))

    /**
     * Return all triggers that are attached to the given [DomainId]
     */
    fun findTriggersByDomainId(id: DomainId) = QueryBox.FindTriggersByDomainId(FindTriggersByDomainId(id.evaluatesTo()))

    /**
     * Return all currently active triggers, that is, triggers that have not expired at the time of the query
     */
    fun findAllActiveTriggerIds() = QueryBox.FindAllActiveTriggerIds(FindAllActiveTriggerIds())

    /**
     * Return all blocks
     */
    fun findAllBlocks() = QueryBox.FindAllBlocks(FindAllBlocks())

    /**
     * Return all block headers
     */
    fun findAllBlockHeaders() = QueryBox.FindAllBlockHeaders(FindAllBlockHeaders())

    /**
     * Return block header corresponding to the given [Hash]
     */
    fun findBlockHeaderByHash(hash: Hash) = QueryBox.FindBlockHeaderByHash(FindBlockHeaderByHash(hash.evaluatesTo()))

    /**
     * Return all transactions
     */
    fun findAllTransactions() = QueryBox.FindAllTransactions(FindAllTransactions())

    /**
     * Return all parameters
     */
    fun findAllParameters() = QueryBox.FindAllParameters(FindAllParameters())
}
