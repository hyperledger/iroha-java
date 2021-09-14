package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryResult

interface ResultExtractor<T> {
    fun extract(result: QueryResult): T
}

object AsIs : ResultExtractor<QueryResult> {
    override fun extract(result: QueryResult) = result
}

object AssetExtractor : ResultExtractor<Asset> {
    override fun extract(result: QueryResult): Asset {
        return extractAsset(result.value)
    }
}

// todo give proper description for the errors
object AssetsExtractor : ResultExtractor<List<Asset>> {
    override fun extract(result: QueryResult): MutableList<Asset> {
        val assets = mutableListOf<Asset>()
        when (val value = result.value) {
            is Value.Vec -> {
                for (element in value.vec) { assets.add(extractAsset(element)) }
            }
            else -> throw QueryPayloadExtractionException()
        }
        return assets
    }
}

object AccountExtractor : ResultExtractor<Account> {
    override fun extract(result: QueryResult): Account {
        return when (val value = result.value) {
            is Value.Identifiable -> when (val box = value.identifiableBox) {
                is IdentifiableBox.Account -> box.account
                else -> throw QueryPayloadExtractionException()
            }
            else -> throw QueryPayloadExtractionException()
        }
    }
}

fun extractAsset(value: Value) =
    when (value) {
        is Value.Identifiable -> when (val box = value.identifiableBox) {
            is IdentifiableBox.Asset -> box.asset
            else -> throw QueryPayloadExtractionException()
        }
        else -> throw QueryPayloadExtractionException()
    }
