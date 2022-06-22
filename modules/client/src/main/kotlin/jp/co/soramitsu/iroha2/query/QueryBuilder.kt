package jp.co.soramitsu.iroha2.query

import jp.co.soramitsu.iroha2.AccountExtractor
import jp.co.soramitsu.iroha2.AccountsExtractor
import jp.co.soramitsu.iroha2.AssetDefinitionExtractor
import jp.co.soramitsu.iroha2.AssetDefinitionsExtractor
import jp.co.soramitsu.iroha2.AssetExtractor
import jp.co.soramitsu.iroha2.AssetsExtractor
import jp.co.soramitsu.iroha2.BlocksValueExtractor
import jp.co.soramitsu.iroha2.DomainExtractor
import jp.co.soramitsu.iroha2.DomainsExtractor
import jp.co.soramitsu.iroha2.PeersExtractor
import jp.co.soramitsu.iroha2.PermissionTokensExtractor
import jp.co.soramitsu.iroha2.ResultExtractor
import jp.co.soramitsu.iroha2.RolesExtractor
import jp.co.soramitsu.iroha2.TransactionValueExtractor
import jp.co.soramitsu.iroha2.TransactionValuesExtractor
import jp.co.soramitsu.iroha2.TransactionsValueExtractor
import jp.co.soramitsu.iroha2.TriggerExtractor
import jp.co.soramitsu.iroha2.TriggerIdsExtractor
import jp.co.soramitsu.iroha2.U32Extractor
import jp.co.soramitsu.iroha2.ValueExtractor
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asSignatureOf
import jp.co.soramitsu.iroha2.fromHex
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.crypto.signature.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.PredicateBox
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.value.Predicate
import jp.co.soramitsu.iroha2.generated.datamodel.query.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.SignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.hash
import jp.co.soramitsu.iroha2.sign
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import java.math.BigInteger
import java.security.KeyPair
import java.time.Instant

class QueryBuilder<R>(
    private val query: QueryBox,
    private val resultExtractor: ResultExtractor<R>,
    private val queryFilter: PredicateBox? = null
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
        val filter = queryFilter ?: PredicateBox.Raw(Predicate.Pass())
        val payload = Payload(
            creationTimeMillis ?: fallbackCreationTime(),
            query,
            checkNotNull(accountId) { "Account Id of the sender is mandatory" },
            filter
        )
        val encodedPayload = Payload.encode(payload)
        val signature = Signature(
            keyPair.public.toIrohaPublicKey(),
            keyPair.private.sign(encodedPayload)
        )

        val query = VersionedSignedQueryRequest.V1(
            SignedQueryRequest(payload, signature.asSignatureOf())
        )
        return QueryAndExtractor(query, resultExtractor)
    }

    private fun fallbackCreationTime() = BigInteger.valueOf(System.currentTimeMillis())

    companion object {
        @JvmStatic
        fun findAllAccounts(queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAllAccounts(),
            AccountsExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: Name) = QueryBuilder(
            Queries.findAccountKeyValueByIdAndKey(accountId, key),
            ValueExtractor
        )

        @JvmStatic
        fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: String) =
            findAccountKeyValueByIdAndKey(accountId, key.asName())

        @JvmStatic
        fun findAccountsByName(name: Name, queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAccountsByName(name),
            AccountsExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAccountsByDomainId(domainId: DomainId, queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAccountsByDomainId(domainId),
            AccountsExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAllAssets(queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAllAssets(),
            AssetsExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAllAssetsDefinitions(queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAllAssetsDefinitions(),
            AssetDefinitionsExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAssetsByName(name: Name, queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAssetsByName(name),
            AssetsExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAssetsByAccountId(accountId: AccountId, queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAssetsByAccountId(accountId),
            AssetsExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAccountById(accountId: AccountId) = QueryBuilder(
            Queries.findAccountById(accountId),
            AccountExtractor
        )

        @JvmStatic
        fun findAssetById(assetId: AssetId) = QueryBuilder(
            Queries.findAssetById(assetId),
            AssetExtractor
        )

        @JvmStatic
        fun findAssetsByDomainId(domainId: DomainId, queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAssetsByDomainId(domainId),
            AssetsExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAssetsByAssetDefinitionId(assetDefinition: AssetDefinitionId) = QueryBuilder(
            Queries.findAssetsByAssetDefinitionId(assetDefinition),
            AssetsExtractor
        )

        @JvmStatic
        fun findAllAssetsDefinitions(assetDefinition: AssetDefinitionId, queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAssetsByAssetDefinitionId(assetDefinition),
            AssetDefinitionsExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAssetsByDomainIdAndAssetDefinitionId(
            domainId: DomainId,
            assetDefinition: AssetDefinitionId,
            queryFilter: PredicateBox? = null
        ) = QueryBuilder(
            Queries.findAssetsByDomainIdAndAssetDefinitionId(domainId, assetDefinition),
            AssetsExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAssetQuantityById(assetId: AssetId) = QueryBuilder(
            Queries.findAssetQuantityById(assetId),
            U32Extractor
        )

        @JvmStatic
        fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: Name) = QueryBuilder(
            Queries.findAssetKeyValueByIdAndKey(assetId, key),
            ValueExtractor
        )

        @JvmStatic
        fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: String) =
            findAssetKeyValueByIdAndKey(assetId, key.asName())

        @JvmStatic
        fun findAssetDefinitionKeyValueByIdAndKey(id: AssetDefinitionId, key: Name) = QueryBuilder(
            Queries.findAssetDefinitionKeyValueByIdAndKey(id, key),
            ValueExtractor
        )

        @JvmStatic
        fun findAssetDefinitionKeyValueByIdAndKey(id: AssetDefinitionId, key: String) =
            findAssetDefinitionKeyValueByIdAndKey(id, key.asName())

        @JvmStatic
        fun findDomainKeyValueByIdAndKey(id: DomainId, key: String) =
            findDomainKeyValueByIdAndKey(id, key.asName())

        @JvmStatic
        fun findDomainKeyValueByIdAndKey(id: DomainId, key: Name) = QueryBuilder(
            Queries.findDomainKeyValueByIdAndKey(id, key),
            ValueExtractor
        )

        @JvmStatic
        fun findAllDomains(queryFilter: PredicateBox? = null) = QueryBuilder(Queries.findAllDomains(), DomainsExtractor, queryFilter)

        @JvmStatic
        fun findDomainById(domainId: DomainId) = QueryBuilder(Queries.findDomainById(domainId), DomainExtractor)

        @JvmStatic
        fun findAllPeers(queryFilter: PredicateBox? = null) = QueryBuilder(Queries.findAllPeers(), PeersExtractor, queryFilter)

        @JvmStatic
        fun findTransactionsByAccountId(accountId: AccountId, queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findTransactionsByAccountId(accountId),
            TransactionValuesExtractor,
            queryFilter
        )

        @JvmStatic
        fun findPermissionTokensByAccountId(accountId: AccountId, queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findPermissionTokensByAccountId(accountId),
            PermissionTokensExtractor,
            queryFilter
        )

        @JvmStatic
        fun findRolesByAccountId(accountId: AccountId, queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findRolesByAccountId(accountId),
            RolesExtractor,
            queryFilter
        )

        @JvmStatic
        fun findTransactionByHash(hash: Hash) = QueryBuilder(
            Queries.findTransactionByHash(hash),
            TransactionValueExtractor
        )

        @JvmStatic
        fun findTransactionByHash(hashBytes: ByteArray) = findTransactionByHash(Hash(hashBytes))

        @JvmStatic
        fun findTransactionByHash(hex: String) = findTransactionByHash(Hash(hex.fromHex().hash()))

        @JvmStatic
        fun findAllTransactions(queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAllTransactions(),
            TransactionsValueExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAllBlocks(queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAllBlocks(),
            BlocksValueExtractor,
            queryFilter
        )

        @JvmStatic
        fun findAssetDefinitionById(definitionId: DefinitionId) = QueryBuilder(
            Queries.findAssetDefinitionById(definitionId),
            AssetDefinitionExtractor
        )

        @JvmStatic
        fun findTriggerById(id: TriggerId) = QueryBuilder(
            Queries.findTriggerById(id),
            TriggerExtractor
        )

        @JvmStatic
        fun findTriggerKeyValueByIdAndKey(id: TriggerId, key: Name) = QueryBuilder(
            Queries.findTriggerKeyValueByIdAndKey(id, key),
            ValueExtractor
        )

        @JvmStatic
        fun findAllActiveTriggerIds(queryFilter: PredicateBox? = null) = QueryBuilder(
            Queries.findAllActiveTriggerIds(),
            TriggerIdsExtractor,
            queryFilter
        )
    }
}

/**
 * Encapsulate signed [query] and extractor of result of the query
 *
 * [R] is a type of extracted value as a result of query execution
 */
class QueryAndExtractor<R>(val query: VersionedSignedQueryRequest, val resultExtractor: ResultExtractor<R>)
