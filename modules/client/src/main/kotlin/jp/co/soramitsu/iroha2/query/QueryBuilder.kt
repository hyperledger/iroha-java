package jp.co.soramitsu.iroha2.query

import jp.co.soramitsu.iroha2.AccountExtractor
import jp.co.soramitsu.iroha2.AccountsExtractor
import jp.co.soramitsu.iroha2.AssetDefinitionExtractor
import jp.co.soramitsu.iroha2.AssetDefinitionsExtractor
import jp.co.soramitsu.iroha2.AssetExtractor
import jp.co.soramitsu.iroha2.AssetsExtractor
import jp.co.soramitsu.iroha2.BlockHeaderExtractor
import jp.co.soramitsu.iroha2.BlockHeadersExtractor
import jp.co.soramitsu.iroha2.BlocksValueExtractor
import jp.co.soramitsu.iroha2.DomainExtractor
import jp.co.soramitsu.iroha2.DomainsExtractor
import jp.co.soramitsu.iroha2.PeersExtractor
import jp.co.soramitsu.iroha2.PermissionTokensExtractor
import jp.co.soramitsu.iroha2.ResultExtractor
import jp.co.soramitsu.iroha2.RoleExtractor
import jp.co.soramitsu.iroha2.RoleIdsExtractor
import jp.co.soramitsu.iroha2.RolesExtractor
import jp.co.soramitsu.iroha2.TriggerBoxExtractor
import jp.co.soramitsu.iroha2.TriggerBoxesExtractor
import jp.co.soramitsu.iroha2.TriggerIdsExtractor
import jp.co.soramitsu.iroha2.U32Extractor
import jp.co.soramitsu.iroha2.ValueExtractor
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asSignatureOf
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.GenericPredicateBox
import jp.co.soramitsu.iroha2.generated.Hash
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.QueryBox
import jp.co.soramitsu.iroha2.generated.QueryPayload
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.Signature
import jp.co.soramitsu.iroha2.generated.SignedQuery
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.ValuePredicate
import jp.co.soramitsu.iroha2.generated.VersionedSignedQuery
import jp.co.soramitsu.iroha2.sign
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import java.math.BigInteger
import java.security.KeyPair
import java.time.Instant

class QueryBuilder<R>(
    private val query: QueryBox,
    private val resultExtractor: ResultExtractor<R>,
    private val queryFilter: GenericPredicateBox<ValuePredicate>? = null,
) {

    private var accountId: AccountId? = null
    private var creationTimeMillis: BigInteger? = null

    fun account(accountId: AccountId) = this.apply { this.accountId = accountId }

    fun account(accountName: Name, domainId: DomainId) = this.account(AccountId(accountName, domainId))

    fun creationTime(creationTimeMillis: BigInteger) = this.apply { this.creationTimeMillis = creationTimeMillis }

    fun creationTime(creationTimeMillis: Instant) = this.apply { this.creationTime(creationTimeMillis.toEpochMilli()) }

    fun creationTime(creationTimeMillis: Long) =
        this.apply { this.creationTime(BigInteger.valueOf(creationTimeMillis)) }

    fun buildSigned(keyPair: KeyPair): QueryAndExtractor<R> {
        val filter = queryFilter ?: GenericPredicateBox.Raw(ValuePredicate.Pass())
        val payload = QueryPayload(checkNotNull(accountId) { "Account Id of the sender is mandatory" }, query, filter)
        val encodedPayload = QueryPayload.encode(payload)
        val signature = Signature(keyPair.public.toIrohaPublicKey(), keyPair.private.sign(encodedPayload))

        val query = VersionedSignedQuery.V1(SignedQuery(signature.asSignatureOf(), payload))
        return QueryAndExtractor(query, resultExtractor)
    }

    private fun fallbackCreationTime() = BigInteger.valueOf(System.currentTimeMillis())

    companion object {
        @JvmStatic
        fun findAllAccounts(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder(
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
        fun findAccountsByName(
            name: Name,
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
        ) = QueryBuilder(
            Queries.findAccountsByName(name),
            AccountsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAccountsByDomainId(
            domainId: DomainId,
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
        ) = QueryBuilder(Queries.findAccountsByDomainId(domainId), AccountsExtractor, queryFilter)

        @JvmStatic
        fun findAccountsWithAsset(
            definitionId: AssetDefinitionId,
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
        ) = QueryBuilder(Queries.findAccountsWithAsset(definitionId), AccountsExtractor, queryFilter)

        @JvmStatic
        fun findAllAssets(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder(
            Queries.findAllAssets(),
            AssetsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAllAssetsDefinitions(
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
        ) = QueryBuilder(
            Queries.findAllAssetsDefinitions(),
            AssetDefinitionsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAssetsByName(
            name: Name,
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
        ) = QueryBuilder(
            Queries.findAssetsByName(name),
            AssetsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAssetsByAccountId(
            accountId: AccountId,
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
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
        fun findAssetsByDomainId(
            domainId: DomainId,
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
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
        fun findAllAssetsDefinitions(
            assetDefinition: AssetDefinitionId,
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
        ) = QueryBuilder(
            Queries.findAssetsByAssetDefinitionId(assetDefinition),
            AssetDefinitionsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAssetsByDomainIdAndAssetDefinitionId(
            domainId: DomainId,
            assetDefinition: AssetDefinitionId,
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
        ) = QueryBuilder(
            Queries.findAssetsByDomainIdAndAssetDefinitionId(domainId, assetDefinition),
            AssetsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAssetQuantityById(assetId: AssetId) = QueryBuilder(
            Queries.findAssetQuantityById(assetId),
            U32Extractor,
        )

        @JvmStatic
        fun findTotalAssetQuantityByAssetDefinitionId(definitionId: AssetDefinitionId) = QueryBuilder(
            Queries.findTotalAssetQuantityByAssetDefinitionId(definitionId),
            U32Extractor,
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
        fun findAllDomains(
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
        ) = QueryBuilder(Queries.findAllDomains(), DomainsExtractor, queryFilter)

        @JvmStatic
        fun findDomainById(domainId: DomainId) = QueryBuilder(Queries.findDomainById(domainId), DomainExtractor)

        @JvmStatic
        fun findAllPeers(queryFilter: GenericPredicateBox<ValuePredicate>? = null) =
            QueryBuilder(Queries.findAllPeers(), PeersExtractor, queryFilter)

//        @JvmStatic
//        fun findTransactionsByAccountId(
//            accountId: AccountId,
//            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
//        ) = QueryBuilder(
//            Queries.findTransactionsByAccountId(accountId),
//            TransactionValuesExtractor,
//            queryFilter,
//        )

        @JvmStatic
        fun findPermissionTokensByAccountId(
            accountId: AccountId,
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
        ) = QueryBuilder(
            Queries.findPermissionTokensByAccountId(accountId),
            PermissionTokensExtractor,
            queryFilter,
        )

//        @JvmStatic
//        fun findAllPermissionTokenDefinitions(
//            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
//        ) = QueryBuilder(
//            Queries.findAllPermissionTokenDefinitions(),
//            PermissionTokenDefinitionsExtractor,
//            queryFilter,
//        )

        @JvmStatic
        fun findRolesByAccountId(accountId: AccountId, queryFilter: GenericPredicateBox<ValuePredicate>? = null) =
            QueryBuilder(
                Queries.findRolesByAccountId(accountId),
                RoleIdsExtractor,
                queryFilter,
            )

        @JvmStatic
        fun findAllRoleIds(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder(
            Queries.findAllRoleIds(),
            RoleIdsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAllRoles(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder(
            Queries.findAllRoles(),
            RolesExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findRoleByRoleId(roleId: RoleId, queryFilter: GenericPredicateBox<ValuePredicate>? = null) =
            QueryBuilder(
                Queries.findRoleByRoleId(roleId),
                RoleExtractor,
                queryFilter,
            )

//        @JvmStatic
//        fun findTransactionByHash(hash: Hash) = QueryBuilder(
//            Queries.findTransactionByHash(hash),
//            TransactionValueExtractor,
//        )

//        @JvmStatic
//        fun findTransactionByHash(bytes: ByteArray) = findTransactionByHash(bytes.toIrohaHash())

//        @JvmStatic
//        fun findTransactionByHash(hex: String) = findTransactionByHash(hex.fromHex().hash().toIrohaHash())

//        @JvmStatic
//        fun findAllTransactions(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder(
//            Queries.findAllTransactions(),
//            TransactionQueryResultExtractor,
//            queryFilter,
//        )

        @JvmStatic
        fun findAllBlocks(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder(
            Queries.findAllBlocks(),
            BlocksValueExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAllBlockHeaders(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder(
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
        fun findAllActiveTriggerIds(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder(
            Queries.findAllActiveTriggerIds(),
            TriggerIdsExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findTriggersByDomainId(
            domainId: DomainId,
            queryFilter: GenericPredicateBox<ValuePredicate>? = null,
        ) = QueryBuilder(
            Queries.findTriggersByDomainId(domainId),
            TriggerBoxesExtractor,
            queryFilter,
        )

        @JvmStatic
        fun findAllParameters(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder(
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
class QueryAndExtractor<R>(val query: VersionedSignedQuery, val resultExtractor: ResultExtractor<R>)
