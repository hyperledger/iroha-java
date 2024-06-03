package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.GenericPredicateBox
import jp.co.soramitsu.iroha2.generated.ValuePredicate
import jp.co.soramitsu.iroha2.query.QueryBuilder
import java.security.KeyPair

open class Query(
    private val client: AdminIroha2Client,
    private val admin: AccountId,
    private val keyPair: KeyPair,
) {

    suspend fun findAllDomains(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder
        .findAllDomains(queryFilter)
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }

    suspend fun findAllAccounts(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder
        .findAllAccounts(queryFilter)
        .account(admin)
        .buildSigned(keyPair)
        .let {
            client.sendQuery(it)
        }

    suspend fun findAllAssets(queryFilter: GenericPredicateBox<ValuePredicate>? = null) = QueryBuilder
        .findAllAssets(queryFilter)
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }

    suspend fun getAccountAmount(accountId: String, assetId: String): Long =
        QueryBuilder.findAccountById(accountId.asAccountId())
            .account(admin)
            .buildSigned(keyPair)
            .let { query ->
                client.sendQuery(query).assets[assetId.asAssetId()]?.value
            }.let { value ->
                value?.cast<AssetValue.Quantity>()?.u32
            } ?: throw RuntimeException("NOT FOUND")
}
