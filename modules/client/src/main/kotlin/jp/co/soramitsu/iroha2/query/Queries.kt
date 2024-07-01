package jp.co.soramitsu.iroha2.query

import jp.co.soramitsu.iroha2.generated.* // ktlint-disable no-wildcard-imports

/**
 * Queries are sent to an Iroha peer and prompt a response with details from the current world state view.
 */
object Queries {

    /**
     * Find all accounts registered globally in the blockchain
     */
    fun findAllAccounts() = QueryBox.FindAllAccounts(FindAllAccounts())

    /**
     * Return the full account information corresponding to the given [AccountId]
     */
    fun findAccountById(accountId: AccountId) = QueryBox.FindAccountById(FindAccountById(accountId))

    /**
     * Return the value keyed by the provided [Name] for the given [AccountId]
     */
    fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: Name) = QueryBox.FindAccountKeyValueByIdAndKey(
        FindAccountKeyValueByIdAndKey(accountId, key),
    )

    /**
     * Return all the accounts that belong to a specific domain [DomainId]
     */
    fun findAccountsByDomainId(domainId: DomainId) = QueryBox.FindAccountsByDomainId(
        FindAccountsByDomainId(domainId),
    )

    /**
     * Return all the accounts that belong to a specific asset definition [AssetDefinitionId]
     */
    fun findAccountsWithAsset(definitionId: AssetDefinitionId) = QueryBox.FindAccountsWithAsset(
        FindAccountsWithAsset(definitionId),
    )

    /**
     * Return the values of all known assets
     */
    fun findAllAssets() = QueryBox.FindAllAssets(FindAllAssets())

    /**
     * Return the aggregated data about the [AssetId] usage across the network
     */
    fun findAssetById(assetId: AssetId) = QueryBox.FindAssetById(FindAssetById(assetId))

    /**
     * Return all the assets that are registered in the given domain [DomainId]
     */
    fun findAssetsByDomainId(domainId: DomainId) = QueryBox.FindAssetsByDomainId(
        FindAssetsByDomainId(domainId),
    )

    /**
     * Search the network for all assets that match the given [Name]
     */
    fun findAssetsByName(name: Name) = QueryBox.FindAssetsByName(FindAssetsByName(name))

    /**
     * Return all the assets that belong to a given [AccountId]
     */
    fun findAssetsByAccountId(accountId: AccountId) = QueryBox.FindAssetsByAccountId(
        FindAssetsByAccountId(accountId),
    )

    /**
     * Search for all the assets that have the given [AssetDefinitionId]
     */
    fun findAssetsByAssetDefinitionId(assetDefinition: AssetDefinitionId) = QueryBox.FindAssetsByAssetDefinitionId(
        FindAssetsByAssetDefinitionId(assetDefinition),
    )

    /**
     * Search the domain with the [DomainId] for assets that have the given [AssetDefinitionId]
     */
    fun findAssetsByDomainIdAndAssetDefinitionId(
        domainId: DomainId,
        assetDefinition: AssetDefinitionId,
    ) = QueryBox.FindAssetsByDomainIdAndAssetDefinitionId(
        FindAssetsByDomainIdAndAssetDefinitionId(
            domainId,
            assetDefinition,
        ),
    )

    /**
     * Return the values of all known asset definitions
     */
    fun findAllAssetsDefinitions() = QueryBox.FindAllAssetsDefinitions(FindAllAssetsDefinitions())

    /**
     * Return the asset quantity for the given asset with [AssetId]
     */
    fun findAssetQuantityById(assetId: AssetId) = QueryBox.FindAssetQuantityById(
        FindAssetQuantityById(assetId),
    )

    /**
     * Return the asset quantity for the given asset with [AssetDefinitionId]
     */
    fun findTotalAssetQuantityByAssetDefinitionId(
        definitionId: AssetDefinitionId,
    ) = QueryBox.FindTotalAssetQuantityByAssetDefinitionId(
        FindTotalAssetQuantityByAssetDefinitionId(definitionId),
    )

    /**
     * Return the value keyed by the given [Name] in the metadata of the asset corresponding to the given [AssetId]
     */
    fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: Name) = QueryBox.FindAssetKeyValueByIdAndKey(
        FindAssetKeyValueByIdAndKey(assetId, key),
    )

    /**
     * Return the value keyed by the given [Name]
     * in the metadata of the asset definition corresponding to the given [AssetDefinitionId]
     */
    fun findAssetDefinitionKeyValueByIdAndKey(
        assetDefinition: AssetDefinitionId,
        key: Name,
    ) = QueryBox.FindAssetDefinitionKeyValueByIdAndKey(
        FindAssetDefinitionKeyValueByIdAndKey(
            assetDefinition,
            key,
        ),
    )

    /**
     * Return the asset definition corresponding to the given [AssetDefinitionId]
     */
    fun findAssetDefinitionById(definitionId: AssetDefinitionId) = QueryBox.FindAssetDefinitionById(
        FindAssetDefinitionById(definitionId),
    )

    /**
     * Return all known registered domains
     */
    fun findAllDomains() = QueryBox.FindAllDomains(FindAllDomains())

    /**
     * Return the domain corresponding to the given [DomainId]
     */
    fun findDomainById(domainId: DomainId) = QueryBox.FindDomainById(FindDomainById(domainId))

    /**
     * Return all known peers identified by their key and accompanied by the address of their API endpoint
     */
    fun findAllPeers() = QueryBox.FindAllPeers(FindAllPeers())

    /**
     * Return the full set of transactions that an account with [AccountId] has submitted throughout the existence of the blockchain
     */
    fun findTransactionsByAccountId(accountId: AccountId) = QueryBox.FindTransactionsByAccountId(
        FindTransactionsByAccountId(accountId),
    )

    /**
     * Return all the permission tokens granted to the specified [AccountId]
     */
    fun findPermissionsByAccountId(accountId: AccountId) = QueryBox.FindPermissionsByAccountId(
        FindPermissionsByAccountId(accountId),
    )

    /**
     * Return executor data model
     */
    fun findExecutorDataModel() = QueryBox.FindExecutorDataModel(
        FindExecutorDataModel(),
    )

    /**
     * Return the transaction by [Hash]
     */
    fun findTransactionByHash(hash: Hash) = QueryBox.FindTransactionByHash(
        FindTransactionByHash(HashOf<SignedTransaction>(hash)),
    )

    /**
     * Return all the role IDs that are attached to the given [AccountId]
     */
    fun findRolesByAccountId(accountId: AccountId) = QueryBox.FindRolesByAccountId(
        FindRolesByAccountId(accountId),
    )

    /**
     * Return all roles
     */
    fun findAllRoles() = QueryBox.FindAllRoles(FindAllRoles())

    /**
     * Return the role by [RoleId]
     */
    fun findRoleByRoleId(roleId: RoleId) = QueryBox.FindRoleByRoleId(FindRoleByRoleId(roleId))

    /**
     * Return all role IDs
     */
    fun findAllRoleIds() = QueryBox.FindAllRoleIds(FindAllRoleIds())

    /**
     * Return the value keyed by the given [Name] in the domain corresponding to the given [DomainId]
     */
    fun findDomainKeyValueByIdAndKey(id: DomainId, key: Name) = QueryBox.FindDomainKeyValueByIdAndKey(
        FindDomainKeyValueByIdAndKey(id, key),
    )

    /**
     * Return the value corresponding to the given key [Name] in the metadata of the trigger with the given [TriggerId]
     */
    fun findTriggerKeyValueByIdAndKey(id: TriggerId, key: Name) = QueryBox.FindTriggerKeyValueByIdAndKey(
        FindTriggerKeyValueByIdAndKey(id, key),
    )

    /**
     * Return the trigger with the given [TriggerId]
     */
    fun findTriggerById(id: TriggerId) = QueryBox.FindTriggerById(FindTriggerById(id))

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
     * Return the block header corresponding to the given [Hash]
     */
    fun findBlockHeaderByHash(hash: Hash) = QueryBox.FindBlockHeaderByHash(
        FindBlockHeaderByHash(HashOf(hash)),
    )

    /**
     * Return all transactions
     */
    fun findAllTransactions() = QueryBox.FindAllTransactions(FindAllTransactions())

    /**
     * Return all parameters
     */
    fun findAllParameters() = QueryBox.FindAllParameters(FindAllParameters())
}
