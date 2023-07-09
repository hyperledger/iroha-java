package jp.co.soramitsu.sample

import jp.co.soramitsu.iroha2.AdminIroha2Client
import jp.co.soramitsu.iroha2.IdKey
import jp.co.soramitsu.iroha2.Permissions
import jp.co.soramitsu.iroha2.asAccountId
import jp.co.soramitsu.iroha2.asAssetDefinitionId
import jp.co.soramitsu.iroha2.asAssetId
import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.cast
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Mintable
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.GenericValuePredicateBox
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.value.ValuePredicate
import jp.co.soramitsu.iroha2.query.QueryBuilder
import kotlinx.coroutines.withTimeout
import java.net.URL
import java.security.KeyPair

class Helper(
    peerUrl: String,
    telemetryUrl: String,
    private val admin: AccountId,
    private val keyPair: KeyPair,
    private val timeout: Long = 10000,
) {

    private val client = AdminIroha2Client(URL(peerUrl), URL(telemetryUrl), log = true)

    suspend fun registerDomain(
        id: String,
        metadata: Map<Name, Value> = mapOf(),
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            this.registerDomain(id.asDomainId(), metadata)
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun registerAccount(
        id: String,
        signatories: List<PublicKey>,
        metadata: Map<Name, Value> = mapOf(),
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            this.registerAccount(id.asAccountId(), signatories, Metadata(metadata))
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun registerAssetDefinition(
        id: String,
        type: AssetValueType = AssetValueType.Store(),
        metadata: Map<Name, Value> = mapOf(),
        mintable: Mintable = Mintable.Infinitely(),
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            this.registerAssetDefinition(id.asAssetDefinitionId(), type, Metadata(metadata), mintable)
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun registerAsset(
        id: String,
        value: AssetValue,
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            this.registerAsset(id.asAssetId(), value)
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun transferAsset(
        from: String,
        value: Int,
        to: String,
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            this.transferAsset(from.asAssetId(), value, to.asAssetId())
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun unregisterDomain(
        id: String,
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            unregisterDomain(id.asDomainId())
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun unregisterAccount(
        id: String,
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            unregisterAccount(id.asAccountId())
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun unregisterAsset(
        id: String,
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            unregisterAsset(id.asAssetId())
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun grantBurnAssets(
        assetId: String,
        target: AccountId,
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            registerPermissionToken(Permissions.CanBurnUserAssetsToken.type, IdKey.AssetId)
            grantBurnAssets(assetId.asAssetId(), target)
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun grantTransferUserAsset(
        assetId: String,
        target: AccountId,
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            registerPermissionToken(Permissions.CanTransferUserAssetsToken.type, IdKey.AssetId)
            grantTransferUserAsset(assetId.asAssetId(), target)
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun getAccountAmount(accountId: String, assetId: String): Long {
        return QueryBuilder.findAccountById(accountId.asAccountId())
            .account(admin)
            .buildSigned(keyPair)
            .let { query ->
                client.sendQuery(query).assets[assetId.asAssetId()]?.value
            }.let { value ->
                value?.cast<AssetValue.Quantity>()?.u32
            } ?: throw RuntimeException("NOT FOUND")
    }

    suspend fun findAllDomains(queryFilter: GenericValuePredicateBox<ValuePredicate>? = null) = QueryBuilder
        .findAllDomains(queryFilter)
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }

    suspend fun findAllAccounts(queryFilter: GenericValuePredicateBox<ValuePredicate>? = null) = QueryBuilder
        .findAllAccounts(queryFilter)
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }

    suspend fun findAllAssets(queryFilter: GenericValuePredicateBox<ValuePredicate>? = null) = QueryBuilder
        .findAllAssets(queryFilter)
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }

    suspend fun findDomainById(id: String) = QueryBuilder
        .findDomainById(id.asDomainId())
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }

    suspend fun findAccountById(id: String) = QueryBuilder
        .findAccountById(id.asAccountId())
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }

    suspend fun findAssetById(id: String) = QueryBuilder
        .findAssetById(id.asAssetId())
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }

    suspend fun findAllTransactions() = QueryBuilder
        .findAllTransactions()
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }
}
