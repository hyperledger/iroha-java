package jp.co.soramitsu.iroha2.query

import jp.co.soramitsu.iroha2.*
import jp.co.soramitsu.iroha2.generated.*
import java.math.BigInteger
import java.security.KeyPair

class QueryBuilder<R>(
    private val query: QueryBox,
    private val resultExtractor: ResultExtractor<R>,
    private val queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
) {
    private var accountId: AccountId? = null

    fun account(accountId: AccountId) = this.apply { this.accountId = accountId }

    fun account(signatory: PublicKey, domainId: DomainId) = this.account(AccountId(domainId, signatory))

    fun buildSigned(keyPair: KeyPair): QueryAndExtractor<R> {
        val filter = queryFilter ?: GenericPredicateBox.Raw(QueryOutputPredicate.Pass())
        val payload = ClientQueryPayload(checkNotNull(accountId) { "Account Id of the sender is mandatory" }, query, filter, Sorting(null), Pagination(null, null), FetchSize(null))
        val encodedPayload = ClientQueryPayload.encode(payload)
        val signature = QuerySignature(Signature(keyPair.private.sign(encodedPayload)).asSignatureOf())

        val query = SignedQuery.V1(SignedQueryV1(signature, payload))
        return QueryAndExtractor(query, resultExtractor)
    }

    private fun fallbackCreationTime() = BigInteger.valueOf(System.currentTimeMillis())

    companion object {
        @JvmStatic
        @JvmOverloads
        fun findAllAccounts(queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) = QueryBuilder(
            Queries.findAllAccounts(),
            AccountsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: Name) = QueryBuilder(
            Queries.findAccountKeyValueByIdAndKey(accountId, key),
            ValueExtractor,
        )

        @JvmStatic
        fun findAccountKeyValueByIdAndKey(
            accountId: AccountId,
            key: String,
        ) = findAccountKeyValueByIdAndKey(accountId, key.asName())

        @JvmStatic
        @JvmOverloads
        fun findAccountsByDomainId(
            domainId: DomainId,
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(Queries.findAccountsByDomainId(domainId), AccountsExtractor, queryFilter)

        @JvmStatic
        @JvmOverloads
        fun findAccountsWithAsset(
            definitionId: AssetDefinitionId,
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(Queries.findAccountsWithAsset(definitionId), AccountsExtractor, queryFilter)

        @JvmStatic
        @JvmOverloads
        fun findAllAssets(queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) = QueryBuilder(
            Queries.findAllAssets(),
            AssetsExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findAllAssetsDefinitions(
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(
            Queries.findAllAssetsDefinitions(),
            AssetDefinitionsExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findAssetsByName(
            name: Name,
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(
            Queries.findAssetsByName(name),
            AssetsExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findAssetsByAccountId(
            accountId: AccountId,
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(
            Queries.findAssetsByAccountId(accountId),
            AssetsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAccountById(accountId: AccountId) = QueryBuilder(Queries.findAccountById(accountId), AccountExtractor)

        @JvmStatic
        fun findAssetById(assetId: AssetId) = QueryBuilder(Queries.findAssetById(assetId), AssetExtractor)

        @JvmStatic
        @JvmOverloads
        fun findAssetsByDomainId(
            domainId: DomainId,
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(
            Queries.findAssetsByDomainId(domainId),
            AssetsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAssetsByAssetDefinitionId(assetDefinition: AssetDefinitionId) = QueryBuilder(
            Queries.findAssetsByAssetDefinitionId(assetDefinition),
            AssetsExtractor,
        )

        @JvmStatic
        @JvmOverloads
        fun findAllAssetsDefinitions(
            assetDefinition: AssetDefinitionId,
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(
            Queries.findAssetsByAssetDefinitionId(assetDefinition),
            AssetDefinitionsExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findAssetsByDomainIdAndAssetDefinitionId(
            domainId: DomainId,
            assetDefinition: AssetDefinitionId,
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(
            Queries.findAssetsByDomainIdAndAssetDefinitionId(domainId, assetDefinition),
            AssetsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAssetQuantityById(assetId: AssetId) = QueryBuilder(
            Queries.findAssetQuantityById(assetId),
            AssetsExtractor, // todo
        )

        @JvmStatic
        fun findTotalAssetQuantityByAssetDefinitionId(definitionId: AssetDefinitionId) = QueryBuilder(
            Queries.findTotalAssetQuantityByAssetDefinitionId(definitionId),
            AssetsExtractor, // todo
        )

        @JvmStatic
        fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: Name) = QueryBuilder(
            Queries.findAssetKeyValueByIdAndKey(assetId, key),
            ValueExtractor,
        )

        @JvmStatic
        fun findAssetKeyValueByIdAndKey(
            assetId: AssetId,
            key: String,
        ) = findAssetKeyValueByIdAndKey(assetId, key.asName())

        @JvmStatic
        fun findAssetDefinitionKeyValueByIdAndKey(id: AssetDefinitionId, key: Name) = QueryBuilder(
            Queries.findAssetDefinitionKeyValueByIdAndKey(id, key),
            ValueExtractor,
        )

        @JvmStatic
        fun findAssetDefinitionKeyValueByIdAndKey(
            id: AssetDefinitionId,
            key: String,
        ) = findAssetDefinitionKeyValueByIdAndKey(id, key.asName())

        @JvmStatic
        fun findDomainKeyValueByIdAndKey(id: DomainId, key: String) = findDomainKeyValueByIdAndKey(id, key.asName())

        @JvmStatic
        fun findDomainKeyValueByIdAndKey(id: DomainId, key: Name) = QueryBuilder(
            Queries.findDomainKeyValueByIdAndKey(id, key),
            ValueExtractor,
        )

        @JvmStatic
        @JvmOverloads
        fun findAllDomains(
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(Queries.findAllDomains(), DomainsExtractor, queryFilter)

        @JvmStatic
        fun findDomainById(domainId: DomainId) = QueryBuilder(Queries.findDomainById(domainId), DomainExtractor)

        @JvmStatic
        @JvmOverloads
        fun findAllPeers(queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) =
            QueryBuilder(Queries.findAllPeers(), PeersExtractor, queryFilter)

        @JvmStatic
        fun findTransactionsByAccountId(
            accountId: AccountId,
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(
            Queries.findTransactionsByAccountId(accountId),
            TransactionValuesExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findPermissionsByAccountId(
            accountId: AccountId,
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(
            Queries.findPermissionsByAccountId(accountId),
            PermissionTokensExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findExecutorDataModel(
            queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null,
        ) = QueryBuilder(
            Queries.findExecutorDataModel(),
            ExecutorDataModelExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findRolesByAccountId(accountId: AccountId, queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) =
            QueryBuilder(
                Queries.findRolesByAccountId(accountId),
                RoleIdsExtractor,
                queryFilter,
            )

        @JvmStatic
        @JvmOverloads
        fun findAllRoleIds(queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) = QueryBuilder(
            Queries.findAllRoleIds(),
            RoleIdsExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findAllRoles(queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) = QueryBuilder(
            Queries.findAllRoles(),
            RolesExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findRoleByRoleId(roleId: RoleId, queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) =
            QueryBuilder(
                Queries.findRoleByRoleId(roleId),
                RoleExtractor,
                queryFilter,
            )

        @JvmStatic
        fun findTransactionByHash(hash: Hash) = QueryBuilder(
            Queries.findTransactionByHash(hash),
            TransactionValueExtractor,
        )

        @JvmStatic
        fun findTransactionByHash(bytes: ByteArray) = findTransactionByHash(bytes.toIrohaHash())

        @JvmStatic
        fun findTransactionByHash(hex: String) = findTransactionByHash(hex.fromHex().hash().toIrohaHash())

        @JvmStatic
        fun findAllTransactions(queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) = QueryBuilder(
            Queries.findAllTransactions(),
            TransactionValuesExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findAllBlocks(queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) = QueryBuilder(
            Queries.findAllBlocks(),
            BlocksValueExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findAllBlockHeaders(queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) = QueryBuilder(
            Queries.findAllBlockHeaders(),
            BlockHeadersExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findBlockHeaderByHash(hash: Hash) = QueryBuilder(
            Queries.findBlockHeaderByHash(hash),
            BlockHeaderExtractor,
        )

        @JvmStatic
        fun findAssetDefinitionById(definitionId: AssetDefinitionId) = QueryBuilder(
            Queries.findAssetDefinitionById(definitionId),
            AssetDefinitionExtractor,
        )

        @JvmStatic
        fun findTriggerById(id: TriggerId) = QueryBuilder(
            Queries.findTriggerById(id),
            TriggerBoxExtractor,
        )

        @JvmStatic
        fun findTriggerKeyValueByIdAndKey(id: TriggerId, key: Name) = QueryBuilder(
            Queries.findTriggerKeyValueByIdAndKey(id, key),
            ValueExtractor,
        )

        @JvmStatic
        @JvmOverloads
        fun findAllActiveTriggerIds(queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) = QueryBuilder(
            Queries.findAllActiveTriggerIds(),
            TriggerIdsExtractor,
            queryFilter,
        )

        @JvmStatic
        @JvmOverloads
        fun findAllParameters(queryFilter: GenericPredicateBox<QueryOutputPredicate>? = null) = QueryBuilder(
            Queries.findAllParameters(),
            ValueExtractor,
            queryFilter,
        )
    }
}

/**
 * Encapsulate signed [query] and extractor of result of the query
 *
 * [R] is a type of extracted value as a result of query execution
 */
class QueryAndExtractor<R>(val query: SignedQuery, val resultExtractor: ResultExtractor<R>)
