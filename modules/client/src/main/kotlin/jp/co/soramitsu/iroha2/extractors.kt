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
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionValue

interface ResultExtractor<T> {
    fun extract(result: QueryResult): T
}

object AsIs : ResultExtractor<QueryResult> {
    override fun extract(result: QueryResult) = result
}

object AssetExtractor : ResultExtractor<Asset> {
    override fun extract(result: QueryResult): Asset {
        return extractIdentifiable(result.value, IdentifiableBox.Asset::asset)
    }
}

object AssetDefinitionExtractor : ResultExtractor<AssetDefinition> {
    override fun extract(result: QueryResult): AssetDefinition {
        return extractIdentifiable(result.value, IdentifiableBox.AssetDefinition::assetDefinition)
    }
}

object AccountExtractor : ResultExtractor<Account> {
    override fun extract(result: QueryResult): Account {
        return extractIdentifiable(result.value, IdentifiableBox.Account::account)
    }
}

object AccountsExtractor : ResultExtractor<List<Account>> {
    override fun extract(result: QueryResult): List<Account> {
        return extractVec(result.value) { extractIdentifiable(it, IdentifiableBox.Account::account) }
    }
}

object AssetsExtractor : ResultExtractor<List<Asset>> {
    override fun extract(result: QueryResult): List<Asset> {
        return extractVec(result.value) { extractIdentifiable(it, IdentifiableBox.Asset::asset) }
    }
}

object AssetDefinitionsExtractor : ResultExtractor<List<AssetDefinition>> {
    override fun extract(result: QueryResult): List<AssetDefinition> {
        return extractVec(result.value) { extractIdentifiable(it, IdentifiableBox.AssetDefinition::assetDefinition) }
    }
}

object DomainExtractor : ResultExtractor<Domain> {
    override fun extract(result: QueryResult): Domain {
        return extractIdentifiable(result.value, IdentifiableBox.Domain::domain)
    }
}

object DomainsExtractor : ResultExtractor<List<Domain>> {
    override fun extract(result: QueryResult): List<Domain> {
        return extractVec(result.value) { extractIdentifiable(it, IdentifiableBox.Domain::domain) }
    }
}

object PeersExtractor : ResultExtractor<List<Peer>> {
    override fun extract(result: QueryResult): List<Peer> {
        return extractVec(result.value) { extractIdentifiable(it, IdentifiableBox.Peer::peer) }
    }
}

object PermissionTokensExtractor : ResultExtractor<List<PermissionToken>> {
    override fun extract(result: QueryResult): List<PermissionToken> {
        return extractVec(result.value) { extractValue(it, Value.PermissionToken::permissionToken) }
    }
}

object TransactionValuesExtractor : ResultExtractor<List<TransactionValue>> {
    override fun extract(result: QueryResult): List<TransactionValue> {
        return extractVec(result.value) { extractValue(it, Value.TransactionValue::transactionValue) }
    }
}

object TransactionValueExtractor : ResultExtractor<TransactionValue> {
    override fun extract(result: QueryResult): TransactionValue {
        return extractValue(result.value, Value.TransactionValue::transactionValue)
    }
}

object U32Extractor : ResultExtractor<UInt> {
    override fun extract(result: QueryResult): UInt {
        return extractValue(result.value, Value.U32::u32)
    }
}

object ValueExtractor : ResultExtractor<Value> {
    override fun extract(result: QueryResult): Value {
        return result.value
    }
}

inline fun <reified I : Value, R> extractIdentifiable(value: Value, downstream: (I) -> R): R {
    return when (value) {
        is Value.Identifiable -> when (val box = value.identifiableBox) {
            is I -> downstream(box)
            else -> throw QueryPayloadExtractionException("Expected `${I::class.qualifiedName}`, but got `${box::class.qualifiedName}`")
        }
        else -> throw QueryPayloadExtractionException("Expected `${Value.Identifiable::class.qualifiedName}`, but got `${value::class.qualifiedName}`")
    }
}

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

inline fun <reified V : Value, R> extractValue(value: Value, downstream: (V) -> R): R {
    return when (value) {
        is V -> downstream(value)
        else -> throw QueryPayloadExtractionException(
            "Expected `${V::class.qualifiedName}`, but got `${value::class.qualifiedName}`"
        )
    }
}
