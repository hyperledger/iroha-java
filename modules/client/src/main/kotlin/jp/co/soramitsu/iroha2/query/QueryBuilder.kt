package jp.co.soramitsu.iroha2.query

import jp.co.soramitsu.iroha2.AccountExtractor
import jp.co.soramitsu.iroha2.AccountsExtractor
import jp.co.soramitsu.iroha2.AssetDefinitionsExtractor
import jp.co.soramitsu.iroha2.AssetExtractor
import jp.co.soramitsu.iroha2.AssetsExtractor
import jp.co.soramitsu.iroha2.DomainExtractor
import jp.co.soramitsu.iroha2.DomainsExtractor
import jp.co.soramitsu.iroha2.PeersExtractor
import jp.co.soramitsu.iroha2.PermissionTokensExtractor
import jp.co.soramitsu.iroha2.ResultExtractor
import jp.co.soramitsu.iroha2.RolesExtractor
import jp.co.soramitsu.iroha2.TransactionValueExtractor
import jp.co.soramitsu.iroha2.TransactionValuesExtractor
import jp.co.soramitsu.iroha2.TriggerExtractor
import jp.co.soramitsu.iroha2.TriggersExtractor
import jp.co.soramitsu.iroha2.U32Extractor
import jp.co.soramitsu.iroha2.ValueExtractor
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asSignatureOf
import jp.co.soramitsu.iroha2.fromHex
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.crypto.signature.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.SignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.hash
import jp.co.soramitsu.iroha2.sign
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import java.math.BigInteger
import java.security.KeyPair
import java.time.Instant
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id as DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id as TriggerId

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
        @JvmStatic
        fun findAllAccounts() = QueryBuilder(
            Queries.findAllAccounts(),
            AccountsExtractor
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
        fun findAccountsByName(name: Name) = QueryBuilder(
            Queries.findAccountsByName(name),
            AccountsExtractor
        )

        @JvmStatic
        fun findAccountsByName(name: String) = findAccountsByName(name.asName())

        @JvmStatic
        fun findAccountsByDomainId(domainId: DomainId) = QueryBuilder(
            Queries.findAccountsByDomainId(domainId),
            AccountsExtractor
        )

        @JvmStatic
        fun findAllAssets() = QueryBuilder(
            Queries.findAllAssets(),
            AssetsExtractor
        )

        @JvmStatic
        fun findAllAssetsDefinitions() = QueryBuilder(
            Queries.findAllAssetsDefinitions(),
            AssetDefinitionsExtractor
        )

        @JvmStatic
        fun findAssetsByName(name: Name) = QueryBuilder(
            Queries.findAssetsByName(name),
            AssetsExtractor
        )

        @JvmStatic
        fun findAssetsByName(name: String) = findAssetsByName(name.asName())

        @JvmStatic
        fun findAssetsByAccountId(accountId: AccountId) = QueryBuilder(
            Queries.findAssetsByAccountId(accountId),
            AssetsExtractor
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
        fun findAssetsByDomainId(domainId: DomainId) = QueryBuilder(
            Queries.findAssetsByDomainId(domainId),
            AssetsExtractor
        )

        @JvmStatic
        fun findAssetsByAssetDefinitionId(assetDefinition: DefinitionId) = QueryBuilder(
            Queries.findAssetsByAssetDefinitionId(assetDefinition),
            AssetsExtractor
        )

        @JvmStatic
        fun findAllAssetsDefinitions(assetDefinition: DefinitionId) = QueryBuilder(
            Queries.findAssetsByAssetDefinitionId(assetDefinition),
            AssetDefinitionsExtractor
        )

        @JvmStatic
        fun findAssetsByDomainIdAndAssetDefinitionId(
            domainId: DomainId,
            assetDefinition: DefinitionId
        ) = QueryBuilder(
            Queries.findAssetsByDomainIdAndAssetDefinitionId(domainId, assetDefinition),
            AssetsExtractor
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
        fun findAssetDefinitionKeyValueByIdAndKey(id: DefinitionId, key: Name) = QueryBuilder(
            Queries.findAssetDefinitionKeyValueByIdAndKey(id, key),
            ValueExtractor
        )

        @JvmStatic
        fun findAssetDefinitionKeyValueByIdAndKey(id: DefinitionId, key: String) =
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
        fun findAllDomains() = QueryBuilder(Queries.findAllDomains(), DomainsExtractor)

        @JvmStatic
        fun findDomainById(domainId: DomainId) = QueryBuilder(Queries.findDomainById(domainId), DomainExtractor)

        @JvmStatic
        fun findAllPeers() = QueryBuilder(Queries.findAllPeers(), PeersExtractor)

        @JvmStatic
        fun findTransactionsByAccountId(accountId: AccountId) = QueryBuilder(
            Queries.findTransactionsByAccountId(accountId),
            TransactionValuesExtractor
        )

        @JvmStatic
        fun findPermissionTokensByAccountId(accountId: AccountId) = QueryBuilder(
            Queries.findPermissionTokensByAccountId(accountId),
            PermissionTokensExtractor
        )

        @JvmStatic
        fun findRolesByAccountId(accountId: AccountId) = QueryBuilder(
            Queries.findRolesByAccountId(accountId),
            RolesExtractor
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
        fun findAllActiveTriggerIds() = QueryBuilder(
            Queries.findAllActiveTriggerIds(),
            TriggersExtractor
        )
    }
}

/**
 * Encapsulate signed [query] and extractor of result of the query
 *
 * [R] is a type of extracted value as a result of query execution
 */
class QueryAndExtractor<R>(val query: VersionedSignedQueryRequest, val resultExtractor: ResultExtractor<R>)
