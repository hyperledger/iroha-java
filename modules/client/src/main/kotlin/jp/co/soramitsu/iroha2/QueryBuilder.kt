package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.query.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.generated.datamodel.query.SignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.query._VersionedSignedQueryRequestV1
import java.math.BigInteger
import java.security.KeyPair
import java.time.Instant

class QueryBuilder private constructor() {

    var accountId: Id? = null
    var creationTimeMillis: BigInteger? = null
    var query: QueryBox? = null

    fun account(accountId: Id) = this.apply { this.accountId = accountId }

    fun account(accountName: String, domain: String) = this.account(Id(accountName, domain))

    fun creationTime(creationTimeMillis: BigInteger) = this.apply { this.creationTimeMillis = creationTimeMillis }

    fun creationTime(creationTimeMillis: Instant) = this.apply { this.creationTime(creationTimeMillis.toEpochMilli()) }

    fun creationTime(creationTimeMillis: Long) =
        this.apply { this.creationTime(BigInteger.valueOf(creationTimeMillis)) }

    fun query(query: QueryBox) = this.apply { this.query = query }

    fun query(query: Queries.() -> QueryBox) = this.apply { this.query = query(Queries) }

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

    private fun fallbackCreationTime() = BigInteger.valueOf(System.currentTimeMillis())

    companion object {
        fun builder() = QueryBuilder()
    }
}
