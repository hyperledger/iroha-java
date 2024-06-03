package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.Account
import jp.co.soramitsu.iroha2.generated.Asset
import jp.co.soramitsu.iroha2.generated.AssetDefinition
import jp.co.soramitsu.iroha2.generated.BatchedResponseOfValue
import jp.co.soramitsu.iroha2.generated.BlockHeader
import jp.co.soramitsu.iroha2.generated.Domain
import jp.co.soramitsu.iroha2.generated.IdBox
import jp.co.soramitsu.iroha2.generated.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.NumericValue
import jp.co.soramitsu.iroha2.generated.Peer
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.PermissionTokenSchema
import jp.co.soramitsu.iroha2.generated.Role
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.SignedBlock
import jp.co.soramitsu.iroha2.generated.TransactionQueryOutput
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.TriggerOfTriggeringFilterBox
import jp.co.soramitsu.iroha2.generated.Value
import java.math.BigInteger

/**
 * Extractors are used by [QueryBuilder] to extract data from query results
 */
interface ResultExtractor<T> {
    fun extract(result: BatchedResponseOfValue): T
}

/**
 * @return the query result as it is
 */
object AsIs : ResultExtractor<BatchedResponseOfValue> {
    override fun extract(result: BatchedResponseOfValue): BatchedResponseOfValue = result
}

/**
 * Extract an asset from a query [result]
 */
object AssetExtractor : ResultExtractor<Asset> {
    override fun extract(result: BatchedResponseOfValue): Asset {
        return extractIdentifiable(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch, IdentifiableBox.Asset::asset)
    }
}

/**
 * Extract an asset definition from a query [result]
 */
object AssetDefinitionExtractor : ResultExtractor<AssetDefinition> {
    override fun extract(result: BatchedResponseOfValue): AssetDefinition {
        return extractIdentifiable(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch, IdentifiableBox.AssetDefinition::assetDefinition)
    }
}

/**
 * Extract an account from a query [result]
 */
object AccountExtractor : ResultExtractor<Account> {
    override fun extract(result: BatchedResponseOfValue): Account {
        return extractIdentifiable(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch, IdentifiableBox.Account::account)
    }
}

/**
 * Extract a list of accounts from a query [result]
 */
object AccountsExtractor : ResultExtractor<List<Account>> {
    override fun extract(result: BatchedResponseOfValue): List<Account> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractIdentifiable(it, IdentifiableBox.Account::account)
        }
    }
}

/**
 * Extract a list of assets from a query [result]
 */
object AssetsExtractor : ResultExtractor<List<Asset>> {
    override fun extract(result: BatchedResponseOfValue): List<Asset> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractIdentifiable(it, IdentifiableBox.Asset::asset)
        }
    }
}

/**
 * Extract a list of asset definitions from a query [result]
 */
object AssetDefinitionsExtractor : ResultExtractor<List<AssetDefinition>> {
    override fun extract(result: BatchedResponseOfValue): List<AssetDefinition> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractIdentifiable(it, IdentifiableBox.AssetDefinition::assetDefinition)
        }
    }
}

/**
 * Extract a domain from a query [result]
 */
object DomainExtractor : ResultExtractor<Domain> {
    override fun extract(result: BatchedResponseOfValue): Domain {
        return extractIdentifiable(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch, IdentifiableBox.Domain::domain)
    }
}

/**
 * Extract a list of domains from a query [result]
 */
object DomainsExtractor : ResultExtractor<List<Domain>> {
    override fun extract(result: BatchedResponseOfValue): List<Domain> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractIdentifiable(it, IdentifiableBox.Domain::domain)
        }
    }
}

/**
 * Extract a lost of peers from a query [result]
 */
object PeersExtractor : ResultExtractor<List<Peer>> {
    override fun extract(result: BatchedResponseOfValue): List<Peer> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractIdentifiable(it, IdentifiableBox.Peer::peer)
        }
    }
}

/**
 * Extract a trigger from a query [result]
 */
object TriggerBoxExtractor : ResultExtractor<TriggerOfTriggeringFilterBox> {
    override fun extract(result: BatchedResponseOfValue): TriggerOfTriggeringFilterBox {
        return extractIdentifiable(
            result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch,
            IdentifiableBox.Trigger::triggerOfTriggeringFilterBox,
        )
    }
}

/**
 * Extract a list of triggers from a query [result]
 */
object TriggerBoxesExtractor : ResultExtractor<List<TriggerOfTriggeringFilterBox>> {
    override fun extract(result: BatchedResponseOfValue): List<TriggerOfTriggeringFilterBox> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractIdentifiable(it, IdentifiableBox.Trigger::triggerOfTriggeringFilterBox)
        }
    }
}

/**
 * Extract a list of trigger IDs from a query [result]
 */
object TriggerIdsExtractor : ResultExtractor<List<TriggerId>> {
    override fun extract(result: BatchedResponseOfValue): List<TriggerId> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractValue(it, Value.Id::idBox).cast<IdBox.TriggerId>().triggerId
        }
    }
}

/**
 * Extract a list of permission tokens from a query [result]
 */
object PermissionTokensExtractor : ResultExtractor<List<PermissionToken>> {
    override fun extract(result: BatchedResponseOfValue): List<PermissionToken> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractValue(it, Value.PermissionToken::permissionToken)
        }
    }
}

/**
 * Extract a permission token schema from a query [result]
 */
object PermissionTokenSchemaExtractor : ResultExtractor<PermissionTokenSchema> {
    override fun extract(result: BatchedResponseOfValue): PermissionTokenSchema {
        return extractValue(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch, Value.PermissionTokenSchema::permissionTokenSchema)
    }
}

/**
* Extract a list of transaction values from a query [result]
*/
object TransactionValuesExtractor : ResultExtractor<List<TransactionQueryOutput>> {
    override fun extract(result: BatchedResponseOfValue): List<TransactionQueryOutput> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractValue(it, Value.TransactionQueryOutput::transactionQueryOutput)
        }
    }
}

/**
* Extract a transaction value from a query [result]
*/
object TransactionValueExtractor : ResultExtractor<TransactionQueryOutput> {
    override fun extract(result: BatchedResponseOfValue): TransactionQueryOutput {
        return extractValue(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch, Value.TransactionQueryOutput::transactionQueryOutput)
    }
}

object BlocksValueExtractor : ResultExtractor<List<SignedBlock>> {
    override fun extract(result: BatchedResponseOfValue): List<SignedBlock> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractValue(it, Value.Block::signedBlock)
        }
    }
}

object BlockHeadersExtractor : ResultExtractor<List<BlockHeader>> {
    override fun extract(result: BatchedResponseOfValue): List<BlockHeader> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractValue(it, Value.BlockHeader::blockHeader)
        }
    }
}

object BlockHeaderExtractor : ResultExtractor<BlockHeader> {
    override fun extract(result: BatchedResponseOfValue): BlockHeader {
        return extractValue(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch, Value.BlockHeader::blockHeader)
    }
}

/**
 * Extract `Value.U32` from a query [result]
 */
object U32Extractor : ResultExtractor<Long> {
    override fun extract(result: BatchedResponseOfValue): Long {
        return extractValue(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) { v: Value ->
            v.cast<Value.Numeric>().numericValue.cast<NumericValue.U32>().u32
        }
    }
}

/**
 * Extract `Value.U64` from a query [result]
 */
object U64Extractor : ResultExtractor<BigInteger> {
    override fun extract(result: BatchedResponseOfValue): BigInteger {
        return extractValue(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) { v: Value ->
            v.cast<Value.Numeric>().numericValue.cast<NumericValue.U64>().u64
        }
    }
}

/**
 * Extract `Value.U128` from a query [result]
 */
object U128Extractor : ResultExtractor<BigInteger> {
    override fun extract(result: BatchedResponseOfValue): BigInteger {
        return extractValue(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) { v: Value ->
            v.cast<Value.Numeric>().numericValue.cast<NumericValue.U128>().u128
        }
    }
}

/**
 * Extract `Value` from a query [result]
 */
object ValueExtractor : ResultExtractor<Value> {
    override fun extract(result: BatchedResponseOfValue): Value {
        return result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch
    }
}

/**
 * Extract a list of roles from a query [result]
 */
object RolesExtractor : ResultExtractor<List<Role>> {
    override fun extract(result: BatchedResponseOfValue): List<Role> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractIdentifiable(it, IdentifiableBox.Role::role)
        }
    }
}

/**
 * Extract a role from a query [result]
 */
object RoleExtractor : ResultExtractor<Role> {
    override fun extract(result: BatchedResponseOfValue): Role {
        return extractIdentifiable(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch, IdentifiableBox.Role::role)
    }
}

/**
 * Extract a list of role IDs from a query [result]
 */
object RoleIdsExtractor : ResultExtractor<List<RoleId>> {
    override fun extract(result: BatchedResponseOfValue): List<RoleId> {
        return extractVec(result.cast<BatchedResponseOfValue.V1>().batchedResponseV1OfValue.batch) {
            extractValue(it, Value.Id::idBox).cast<IdBox.RoleId>().roleId
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
                "Expected `${I::class.qualifiedName}`, but got `${box::class.qualifiedName}`",
            )
        }

        else -> throw QueryPayloadExtractionException(
            "Expected `${Value.Identifiable::class.qualifiedName}`, but got `${value::class.qualifiedName}`",
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
            "Expected `${Value.Vec::class.qualifiedName}`, but got `${value::class.qualifiedName}`",
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
            "Expected `${V::class.qualifiedName}`, but got `${value::class.qualifiedName}`",
        )
    }
}
