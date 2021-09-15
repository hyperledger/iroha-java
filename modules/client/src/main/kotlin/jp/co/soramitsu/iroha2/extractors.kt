package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryResult

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

inline fun <reified I : Value, reified R> extractIdentifiable(value: Value, downstream: (I) -> R): R {
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
