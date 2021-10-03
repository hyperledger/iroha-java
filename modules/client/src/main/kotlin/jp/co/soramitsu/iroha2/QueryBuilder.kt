package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.query.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.query.SignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.query._VersionedSignedQueryRequestV1
import java.math.BigInteger
import java.security.KeyPair
import java.time.Instant
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

class QueryBuilder(builder: QueryBuilder.() -> Unit = {}) {

    var accountId: AccountId?
    var creationTimeMillis: BigInteger?
    var query: QueryBox?

    init {
        accountId = null
        creationTimeMillis = null
        query = null
        builder(this)
    }

    fun account(accountId: AccountId) = this.apply { this.accountId = accountId }

    fun account(accountName: String, domain: String) = this.account(AccountId(accountName, domain))

    fun creationTime(creationTimeMillis: BigInteger) = this.apply { this.creationTimeMillis = creationTimeMillis }

    fun creationTime(creationTimeMillis: Instant) = this.apply { this.creationTime(creationTimeMillis.toEpochMilli()) }

    fun creationTime(creationTimeMillis: Long) =
        this.apply { this.creationTime(BigInteger.valueOf(creationTimeMillis)) }

    fun query(query: QueryBox) = this.apply { this.query = query }

    fun buildSigned(keyPair: KeyPair): VersionedSignedQueryRequest {
        val payload = Payload(
            creationTimeMillis ?: fallbackCreationTime(),
            checkNotNull(query) { "Query kind is mandatory" },
            checkNotNull(accountId) { "Account Id of the sender is mandatory" }
        )

        val encodedPayload = payload.encode(Payload)
        val signature = Signature(
            keyPair.public.toIrohaPublicKey(),
            keyPair.private.sign(encodedPayload)
        )

        return VersionedSignedQueryRequest.V1(
            _VersionedSignedQueryRequestV1(
                SignedQueryRequest(
                    payload,
                    signature
                )
            )
        )
    }

    fun findAccountById(accountId: AccountId) = this.apply { query = Queries.findAccountById(accountId) }

    fun findAssetById(assetId: AssetId) = this.apply { query = Queries.findAssetById(assetId) }

    fun findAssetsByDomainName(domain: String) = this.apply { query = Queries.findAssetsByDomainName(domain) }

    fun findAssetsByAssetDefinitionId(assetDefinition: DefinitionId) = this.apply { query = Queries.findAssetsByAssetDefinitionId(assetDefinition) }

    fun findAllAssetsDefinitions() = this.apply { query = Queries.findAllAssetsDefinitions() }

    private fun fallbackCreationTime() = BigInteger.valueOf(System.currentTimeMillis())

    companion object {
        fun builder() = QueryBuilder()
    }
}
