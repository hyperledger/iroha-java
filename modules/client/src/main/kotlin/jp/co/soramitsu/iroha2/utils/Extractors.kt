package jp.co.soramitsu.iroha2.utils

import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryResult

fun asIs(queryResult: QueryResult): QueryResult = queryResult

fun assetExtractor(result: QueryResult): Asset {
    return when (result.value) {
        is Value.Identifiable -> when (result.value.identifiableBox) {
            is IdentifiableBox.Asset -> result.value.identifiableBox.asset
            else -> throw QueryPayloadExtractionException()
        }
        else -> throw QueryPayloadExtractionException()
    }
}

fun accountExtractor(result: QueryResult): Account {
    return when (result.value) {
        is Value.Identifiable -> when (result.value.identifiableBox) {
            is IdentifiableBox.Account -> result.value.identifiableBox.account
            else -> throw QueryPayloadExtractionException()
        }
        else -> throw QueryPayloadExtractionException()
    }
}

class QueryPayloadExtractionException : RuntimeException()
