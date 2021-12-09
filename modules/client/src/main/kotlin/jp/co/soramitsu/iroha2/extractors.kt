package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain
import jp.co.soramitsu.iroha2.generated.datamodel.peer.Peer
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedQueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionValue
import java.math.BigInteger

/**
 * Extractors are used by **[QueryBuilder]** to extract data from query result
 */
interface ResultExtractor<T> {
    fun extract(result: VersionedQueryResult): T
}

interface V1ResultExtractor<T> : ResultExtractor<T> {
    override fun extract(result: VersionedQueryResult): T {
        return when(result){
            is VersionedQueryResult.V1 -> extract(result._VersionedQueryResultV1.queryResult)
        }
    }

    fun extract(result: QueryResult): T
}

object AsIs : V1ResultExtractor<QueryResult> {
    override fun extract(result: QueryResult) = result
}

object AssetExtractor : V1ResultExtractor<Asset> {
    override fun extract(result: QueryResult): Asset {
        return extractIdentifiable(result.value, IdentifiableBox.Asset::asset)
    }
}

object AssetDefinitionExtractor : V1ResultExtractor<AssetDefinition> {
    override fun extract(result: QueryResult): AssetDefinition {
        return extractIdentifiable(result.value, IdentifiableBox.AssetDefinition::assetDefinition)
    }
}

object AccountExtractor : V1ResultExtractor<Account> {
    override fun extract(result: QueryResult): Account {
        return extractIdentifiable(result.value, IdentifiableBox.Account::account)
    }
}

object AccountsExtractor : V1ResultExtractor<List<Account>> {
    override fun extract(result: QueryResult): List<Account> {
        return extractVec(result.value) { extractIdentifiable(it, IdentifiableBox.Account::account) }
    }
}

object AssetsExtractor : V1ResultExtractor<List<Asset>> {
    override fun extract(result: QueryResult): List<Asset> {
        return extractVec(result.value) { extractIdentifiable(it, IdentifiableBox.Asset::asset) }
    }
}

object AssetDefinitionsExtractor : V1ResultExtractor<List<AssetDefinition>> {
    override fun extract(result: QueryResult): List<AssetDefinition> {
        return extractVec(result.value) { extractIdentifiable(it, IdentifiableBox.AssetDefinition::assetDefinition) }
    }
}

object DomainExtractor : V1ResultExtractor<Domain> {
    override fun extract(result: QueryResult): Domain {
        return extractIdentifiable(result.value, IdentifiableBox.Domain::domain)
    }
}

object DomainsExtractor : V1ResultExtractor<List<Domain>> {
    override fun extract(result: QueryResult): List<Domain> {
        return extractVec(result.value) { extractIdentifiable(it, IdentifiableBox.Domain::domain) }
    }
}

object PeersExtractor : V1ResultExtractor<List<Peer>> {
    override fun extract(result: QueryResult): List<Peer> {
        return extractVec(result.value) { extractIdentifiable(it, IdentifiableBox.Peer::peer) }
    }
}

object PermissionTokensExtractor : V1ResultExtractor<List<PermissionToken>> {
    override fun extract(result: QueryResult): List<PermissionToken> {
        return extractVec(result.value) { extractValue(it, Value.PermissionToken::permissionToken) }
    }
}

object TransactionValuesExtractor : V1ResultExtractor<List<TransactionValue>> {
    override fun extract(result: QueryResult): List<TransactionValue> {
        return extractVec(result.value) { extractValue(it, Value.TransactionValue::transactionValue) }
    }
}

object TransactionValueExtractor : V1ResultExtractor<TransactionValue> {
    override fun extract(result: QueryResult): TransactionValue {
        return extractValue(result.value, Value.TransactionValue::transactionValue)
    }
}

object U32Extractor : V1ResultExtractor<Long> {
    override fun extract(result: QueryResult): Long {
        return extractValue(result.value, Value.U32::u32)
    }
}

object U128Extractor : V1ResultExtractor<BigInteger> {
    override fun extract(result: QueryResult): BigInteger {
        return extractValue(result.value, Value.U128::u128)
    }
}

object ValueExtractor : V1ResultExtractor<Value> {
    override fun extract(result: QueryResult): Value {
        return result.value
    }
}

/**
 * Extracts one of **[IdentifiableBox]** objects from value
 *
 * @param downstream Type to extract
 */
inline fun <reified I : Value, R> extractIdentifiable(value: Value, downstream: (I) -> R): R {
    return when (value) {
        is Value.Identifiable -> when (val box = value.identifiableBox) {
            is I -> downstream(box)
            else -> throw QueryPayloadExtractionException("Expected `${I::class.qualifiedName}`, but got `${box::class.qualifiedName}`")
        }
        else -> throw QueryPayloadExtractionException("Expected `${Value.Identifiable::class.qualifiedName}`, but got `${value::class.qualifiedName}`")
    }
}

/**
 * Extracts collection from Value.Vec
 *
 * @param downstream Type to extract
 */
inline fun <reified R> extractVec(value: Value, downstream: (Value) -> R): List<R> {
    when (value) {
        is Value.Vec -> {
            return value.vec.map {
                downstream(it)
            }
        }
        else -> throw QueryPayloadExtractionException("Expected `${Value.Vec::class.qualifiedName}`, but got `${value::class.qualifiedName}`")
    }
}

/**
 * Extracts value
 *
 * @param downstream Type to extract
 */
inline fun <reified V : Value, R> extractValue(value: Value, downstream: (V) -> R): R {
    return when (value) {
        is V -> downstream(value)
        else -> throw QueryPayloadExtractionException(
            "Expected `${V::class.qualifiedName}`, but got `${value::class.qualifiedName}`"
        )
    }
}
