package jp.co.soramitsu.iroha2

import java.math.BigInteger
import java.security.KeyPair
import java.time.Instant
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.crypto.signature.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.SignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id as DomainId

class QueryBuilder<R>(private val query: QueryBox, private val resultExtractor: ResultExtractor<R>) {

    private var accountId: AccountId? = null
    private var creationTimeMillis: BigInteger? = null

    fun account(accountId: AccountId) = this.apply { this.accountId = accountId }

    fun account(accountName: Name, domainId: DomainId) = this.account(AccountId(accountName, domainId))

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
        fun findAllAccounts() = QueryBuilder(
            Queries.findAllAccounts(),
            AccountsExtractor
        )

        fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: Name) = QueryBuilder(
            Queries.findAccountKeyValueByIdAndKey(accountId, key),
            ValueExtractor
        )

        fun findAccountKeyValueByIdAndKey(accountId: AccountId, key: String) =
            findAccountKeyValueByIdAndKey(accountId, key.asName())

        fun findAccountsByName(name: Name) = QueryBuilder(
            Queries.findAccountsByName(name),
            AccountsExtractor
        )

        fun findAccountsByName(name: String) = findAccountsByName(name.asName())

        fun findAccountsByDomainId(domainId: DomainId) = QueryBuilder(
            Queries.findAccountsByDomainId(domainId),
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

        fun findAssetsByName(name: Name) = QueryBuilder(
            Queries.findAssetsByName(name),
            AssetsExtractor
        )

        fun findAssetsByName(name: String) = findAssetsByName(name.asName())

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

        fun findAssetsByDomainId(domainId: DomainId) = QueryBuilder(
            Queries.findAssetsByDomainId(domainId),
            AssetsExtractor
        )

        fun findAssetsByAssetDefinitionId(assetDefinition: DefinitionId) = QueryBuilder(
            Queries.findAssetsByAssetDefinitionId(assetDefinition),
            AssetsExtractor
        )

        fun findAllAssetsDefinitions(assetDefinition: DefinitionId) = QueryBuilder(
            Queries.findAssetsByAssetDefinitionId(assetDefinition),
            AssetDefinitionsExtractor
        )

        fun findAssetsByDomainIdAndAssetDefinitionId(
            domainId: DomainId,
            assetDefinition: DefinitionId
        ) = QueryBuilder(
            Queries.findAssetsByDomainIdAndAssetDefinitionId(domainId, assetDefinition),
            AssetsExtractor
        )

        fun findAssetQuantityById(assetId: AssetId) = QueryBuilder(
            Queries.findAssetQuantityById(assetId),
            U32Extractor
        )

        fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: Name) = QueryBuilder(
            Queries.findAssetKeyValueByIdAndKey(assetId, key),
            ValueExtractor
        )

        fun findAssetKeyValueByIdAndKey(assetId: AssetId, key: String) =
            findAssetKeyValueByIdAndKey(assetId, key.asName())

        fun findAssetDefinitionKeyValueByIdAndKey(id: DefinitionId, key: Name) = QueryBuilder(
            Queries.findAssetDefinitionKeyValueByIdAndKey(id, key),
            ValueExtractor
        )

        fun findAssetDefinitionKeyValueByIdAndKey(id: DefinitionId, key: String) =
            findAssetDefinitionKeyValueByIdAndKey(id, key.asName())

        fun findAllDomains() = QueryBuilder(Queries.findAllDomains(), DomainsExtractor)

        fun findDomainById(domainId: DomainId) = QueryBuilder(Queries.findDomainById(domainId), DomainExtractor)

        fun findAllPeers() = QueryBuilder(Queries.findAllPeers(), PeersExtractor)

        fun findTransactionsByAccountId(accountId: AccountId) = QueryBuilder(
            Queries.findTransactionsByAccountId(accountId),
            TransactionValuesExtractor
        )

        fun findPermissionTokensByAccountId(accountId: AccountId) = QueryBuilder(
            Queries.findPermissionTokensByAccountId(accountId),
            PermissionTokensExtractor
        )

        fun findTransactionByHash(hash: Hash) = QueryBuilder(
            Queries.findTransactionByHash(hash),
            TransactionValueExtractor
        )

        fun findTransactionByHash(hashBytes: ByteArray) = findTransactionByHash(Hash(hashBytes))

        fun findTransactionByHash(hex: String) = findTransactionByHash(Hash(hex.fromHex().hash()))
    }
}

/**
 * Encapsulate signed [query] and extractor of result of the query
 *
 * [R] is a type of extracted value as a result of query execution
 */
class QueryAndExtractor<R>(val query: VersionedSignedQueryRequest, val resultExtractor: ResultExtractor<R>)
