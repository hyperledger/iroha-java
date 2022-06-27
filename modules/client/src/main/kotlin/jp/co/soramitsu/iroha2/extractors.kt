package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
import jp.co.soramitsu.iroha2.generated.datamodel.blockvalue.BlockValue
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain
import jp.co.soramitsu.iroha2.generated.datamodel.peer.Peer
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import jp.co.soramitsu.iroha2.generated.datamodel.query.PaginatedQueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedPaginatedQueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.role.Role
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionValue
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Trigger
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import java.math.BigInteger

/**
 * Extractors are used by [QueryBuilder] to extract data from query results
 */
interface ResultExtractor<T> {
    fun extract(result: VersionedPaginatedQueryResult): Page<T> {
        return when (result) {
            is VersionedPaginatedQueryResult.V1 -> {
                val data = extract(result.paginatedQueryResult)
                Page(
                    data,
                    result.paginatedQueryResult.pagination,
                    result.paginatedQueryResult.total
                )
            }
        }
    }

    fun extract(result: PaginatedQueryResult): T
}

/**
 * @return the query result as it is
 */
object AsIs : ResultExtractor<PaginatedQueryResult> {
    override fun extract(result: PaginatedQueryResult) = result
}

/**
 * Extract an asset from a query [result]
 */
object AssetExtractor : ResultExtractor<Asset> {
    override fun extract(result: PaginatedQueryResult): Asset {
        return extractIdentifiable(result.result.value, IdentifiableBox.Asset::asset)
    }
}

/**
 * Extract an asset definition from a query [result]
 */
object AssetDefinitionExtractor : ResultExtractor<AssetDefinition> {
    override fun extract(result: PaginatedQueryResult): AssetDefinition {
        return extractIdentifiable(result.result.value, IdentifiableBox.AssetDefinition::assetDefinition)
    }
}

/**
 * Extract an account from a query [result]
 */
object AccountExtractor : ResultExtractor<Account> {
    override fun extract(result: PaginatedQueryResult): Account {
        return extractIdentifiable(result.result.value, IdentifiableBox.Account::account)
    }
}

/**
 * Extract a list of accounts from a query [result]
 */
object AccountsExtractor : ResultExtractor<List<Account>> {
    override fun extract(result: PaginatedQueryResult): List<Account> {
        return extractVec(result.result.value) {
            extractIdentifiable(it, IdentifiableBox.Account::account)
        }
    }
}

/**
 * Extract a list of assets from a query [result]
 */
object AssetsExtractor : ResultExtractor<List<Asset>> {
    override fun extract(result: PaginatedQueryResult): List<Asset> {
        return extractVec(result.result.value) {
            extractIdentifiable(it, IdentifiableBox.Asset::asset)
        }
    }
}

/**
 * Extract a list of asset definitions from a query [result]
 */
object AssetDefinitionsExtractor : ResultExtractor<List<AssetDefinition>> {
    override fun extract(result: PaginatedQueryResult): List<AssetDefinition> {
        return extractVec(result.result.value) {
            extractIdentifiable(it, IdentifiableBox.AssetDefinition::assetDefinition)
        }
    }
}

/**
 * Extract a domain from a query [result]
 */
object DomainExtractor : ResultExtractor<Domain> {
    override fun extract(result: PaginatedQueryResult): Domain {
        return extractIdentifiable(result.result.value, IdentifiableBox.Domain::domain)
    }
}

/**
 * Extract a list of domains from a query [result]
 */
object DomainsExtractor : ResultExtractor<List<Domain>> {
    override fun extract(result: PaginatedQueryResult): List<Domain> {
        return extractVec(result.result.value) {
            extractIdentifiable(it, IdentifiableBox.Domain::domain)
        }
    }
}

/**
 * Extract a lost of peers from a query [result]
 */
object PeersExtractor : ResultExtractor<List<Peer>> {
    override fun extract(result: PaginatedQueryResult): List<Peer> {
        return extractVec(result.result.value) {
            extractIdentifiable(it, IdentifiableBox.Peer::peer)
        }
    }
}

/**
 * Extract a trigger from a query [result]
 */
object TriggerExtractor : ResultExtractor<Trigger<*>> {
    override fun extract(result: PaginatedQueryResult): Trigger<*> {
        return extractIdentifiable(result.result.value, IdentifiableBox.Trigger::trigger)
    }
}

/**
 * Extract a list of triggers from a query [result]
 */
object TriggersExtractor : ResultExtractor<List<Trigger<*>>> {
    override fun extract(result: PaginatedQueryResult): List<Trigger<*>> {
        return extractVec(result.result.value) {
            extractIdentifiable(it, IdentifiableBox.Trigger::trigger)
        }
    }
}

/**
 * Extract a list of trigger IDs from a query [result]
 */
object TriggerIdsExtractor : ResultExtractor<List<TriggerId>> {
    override fun extract(result: PaginatedQueryResult): List<TriggerId> {
        return extractVec(result.result.value) {
            extractValue(it, Value.Id::idBox).cast<IdBox.TriggerId>().triggerId
        }
    }
}

/**
 * Extract a list of permission tokens from a query [result]
 */
object PermissionTokensExtractor : ResultExtractor<List<PermissionToken>> {
    override fun extract(result: PaginatedQueryResult): List<PermissionToken> {
        return extractVec(result.result.value) {
            extractValue(it, Value.PermissionToken::permissionToken)
        }
    }
}

/**
 * Extract a list of transaction values from a query [result]
 */
object TransactionValuesExtractor : ResultExtractor<List<TransactionValue>> {
    override fun extract(result: PaginatedQueryResult): List<TransactionValue> {
        return extractVec(result.result.value) {
            extractValue(it, Value.TransactionValue::transactionValue)
        }
    }
}

/**
 * Extract a transaction value from a query [result]
 */
object TransactionValueExtractor : ResultExtractor<TransactionValue> {
    override fun extract(result: PaginatedQueryResult): TransactionValue {
        return extractValue(result.result.value, Value.TransactionValue::transactionValue)
    }
}

object TransactionsValueExtractor : ResultExtractor<List<TransactionValue>> {
    override fun extract(result: PaginatedQueryResult): List<TransactionValue> {
        return extractVec(result.result.value) {
            extractValue(it, Value.TransactionValue::transactionValue)
        }
    }
}

object BlocksValueExtractor : ResultExtractor<List<BlockValue>> {
    override fun extract(result: PaginatedQueryResult): List<BlockValue> {
        return extractVec(result.result.value) {
            extractValue(it, Value.Block::blockValue)
        }
    }
}

/**
 * Extract `Value.U32` from a query [result]
 */
object U32Extractor : ResultExtractor<Long> {
    override fun extract(result: PaginatedQueryResult): Long {
        return extractValue(result.result.value, Value.U32::u32)
    }
}

/**
 * Extract `Value.U128` from a query [result]
 */
object U128Extractor : ResultExtractor<BigInteger> {
    override fun extract(result: PaginatedQueryResult): BigInteger {
        return extractValue(result.result.value, Value.U128::u128)
    }
}

/**
 * Extract `Value` from a query [result]
 */
object ValueExtractor : ResultExtractor<Value> {
    override fun extract(result: PaginatedQueryResult): Value {
        return result.result.value
    }
}

/**
 * Extract a list of roles from a query [result]
 */
object RolesExtractor : ResultExtractor<List<Role>> {
    override fun extract(result: PaginatedQueryResult): List<Role> {
        return extractVec(result.result.value) {
            extractValue(it, IdentifiableBox.Role::role)
        }
    }
}

/**
 * Extract one of the [IdentifiableBox] objects from value
 *
 * @param downstream Type to extract
 */
inline fun <reified I : Value, R> extractIdentifiable(value: Value, downstream: (I) -> R): R {
    return when (value) {
        is Value.Identifiable -> when (val box = value.identifiableBox) {
            is I -> downstream(box)
            else -> throw QueryPayloadExtractionException(
                "Expected `${I::class.qualifiedName}`, but got `${box::class.qualifiedName}`"
            )
        }
        else -> throw QueryPayloadExtractionException(
            "Expected `${Value.Identifiable::class.qualifiedName}`, but got `${value::class.qualifiedName}`"
        )
    }
}

/**
 * Extract collection from `Value.Vec`
 *
 * @param downstream Type to extract
 */
inline fun <reified R> extractVec(value: Value, downstream: (Value) -> R): List<R> {
    when (value) {
        is Value.Vec -> {
            return value.vec.map { downstream(it) }
        }
        else -> throw QueryPayloadExtractionException(
            "Expected `${Value.Vec::class.qualifiedName}`, but got `${value::class.qualifiedName}`"
        )
    }
}

/**
 * Extract value
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
