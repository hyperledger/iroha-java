package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.SignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.query._VersionedSignedQueryRequestV1
import java.math.BigInteger
import java.security.KeyPair
import java.time.Instant
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

class QueryBuilder<R>(private val query: QueryBox, private val resultExtractor: ResultExtractor<R>) {

    private var accountId: AccountId? = null
    private var creationTimeMillis: BigInteger? = null

    fun account(accountId: AccountId) = this.apply { this.accountId = accountId }

    fun account(accountName: String, domain: String) = this.account(AccountId(accountName, domain))

    fun creationTime(creationTimeMillis: BigInteger) = this.apply { this.creationTimeMillis = creationTimeMillis }

    fun creationTime(creationTimeMillis: Instant) = this.apply { this.creationTime(creationTimeMillis.toEpochMilli()) }

    fun creationTime(creationTimeMillis: Long) =
        this.apply { this.creationTime(BigInteger.valueOf(creationTimeMillis)) }

    fun buildSigned(keyPair: KeyPair): QueryAndExtractor<R> {
        val payload = Payload(
            creationTimeMillis ?: fallbackCreationTime(),
            query,
            checkNotNull(accountId) { "Account Id of the sender is mandatory" }
        )

        val encodedPayload = payload.encode(Payload)
        val signature = Signature(
            keyPair.public.toIrohaPublicKey(),
            keyPair.private.sign(encodedPayload)
        )

        val query = VersionedSignedQueryRequest.V1(
            _VersionedSignedQueryRequestV1(
                SignedQueryRequest(
                    payload,
                    signature
                )
            )
        )
        return QueryAndExtractor(query, resultExtractor)
    }

    private fun fallbackCreationTime() = BigInteger.valueOf(System.currentTimeMillis())

    companion object {
        fun findAllAccounts() = QueryBuilder(
            Queries.findAllAccounts(),
            AccountsExtractor
        )

        fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: String) = QueryBuilder(
            Queries.findAccountKeyValueByIdAndKey(accountId, key),
            ValueExtractor
        )

        fun findAccountsByName(name: String) = QueryBuilder(
            Queries.findAccountsByName(name),
            AccountsExtractor
        )

        fun findAccountsByDomainName(domain: String) = QueryBuilder(
            Queries.findAccountsByDomainName(domain),
            AccountsExtractor
        )

        fun findAllAssets() = QueryBuilder(
            Queries.findAllAssets(),
            AssetsExtractor
        )

        fun findAllAssetsDefinitions() = QueryBuilder(
            Queries.findAllAssetsDefinitions(),
            AssetDefinitionsExtractor
        )

        fun findAssetsByName(name: String) = QueryBuilder(
            Queries.findAssetsByName(name),
            AssetsExtractor
        )

        fun findAssetsByAccountId(accountId: AccountId) = QueryBuilder(
            Queries.findAssetsByAccountId(accountId),
            AssetsExtractor
        )

        fun findAccountById(accountId: AccountId) = QueryBuilder(
            Queries.findAccountById(accountId),
            AccountExtractor
        )

        fun findAssetById(assetId: AssetId) = QueryBuilder(
            Queries.findAssetById(assetId),
            AssetExtractor
        )

        fun findAssetsByDomainName(domain: String) = QueryBuilder(
            Queries.findAssetsByDomainName(domain),
            AssetsExtractor
        )

        fun findAssetsByAssetDefinitionId(assetDefinition: DefinitionId) =
            QueryBuilder(
                Queries.findAssetsByAssetDefinitionId(assetDefinition),
                AssetsExtractor
            )

        fun findAllAssetsDefinitions(assetDefinition: DefinitionId) =
            QueryBuilder(
                Queries.findAssetsByAssetDefinitionId(assetDefinition),
                AssetDefinitionsExtractor
            )

        fun findAssetsByDomainNameAndAssetDefinitionId(
            domain: String,
            assetDefinition: DefinitionId
        ) = QueryBuilder(
            Queries.findAssetsByDomainNameAndAssetDefinitionId(domain, assetDefinition),
            AssetsExtractor
        )

        fun findAssetQuantityById(assetId: AssetId) = QueryBuilder(
            Queries.findAssetQuantityById(assetId),
            U32Extractor
        )

        fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: String) = QueryBuilder(
            Queries.findAssetKeyValueByIdAndKey(assetId, key),
            ValueExtractor
        )

        fun findAssetDefinitionKeyValueByIdAndKey(
            assetDefinition: DefinitionId,
            key: String
        ) = QueryBuilder(
            Queries.findAssetDefinitionKeyValueByIdAndKey(assetDefinition, key),
            ValueExtractor
        )

        fun findAllDomains() = QueryBuilder(Queries.findAllDomains(), DomainsExtractor)

        fun findDomainByName(name: String) = QueryBuilder(Queries.findDomainByName(name), DomainExtractor)

        fun findAllPeers() = QueryBuilder(Queries.findAllPeers(), PeersExtractor)

        fun findTransactionsByAccountId(accountId: AccountId) = QueryBuilder(
            Queries.findTransactionsByAccountId(accountId),
            TransactionValuesExtractor
        )

        fun findPermissionTokensByAccountId(accountId: AccountId) = QueryBuilder(
            Queries.findPermissionTokensByAccountId(accountId),
            PermissionTokensExtractor
        )
    }
}

/**
 * Encapsulate signed [query] and extractor of result of the query
 *
 * [R] is a type of extracted value as a result of query execution
 */
class QueryAndExtractor<R>(val query: VersionedSignedQueryRequest, val resultExtractor: ResultExtractor<R>)
