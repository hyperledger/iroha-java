package jp.co.soramitsu.sample

import jp.co.soramitsu.iroha2.AdminIroha2Client
import jp.co.soramitsu.iroha2.asAccountId
import jp.co.soramitsu.iroha2.asAssetDefinitionId
import jp.co.soramitsu.iroha2.asAssetId
import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Mintable
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.PredicateBox
import jp.co.soramitsu.iroha2.query.QueryBuilder
import kotlinx.coroutines.withTimeout
import java.net.URL
import java.security.KeyPair

class Helper(
    peerUrl: String,
    telemetryUrl: String,
    private val admin: AccountId,
    private val keyPair: KeyPair,
    private val timeout: Long = 10000
) {

    private val client = AdminIroha2Client(URL(peerUrl), URL(telemetryUrl), log = true)

    suspend fun registerDomain(id: String, metadata: Map<Name, Value> = mapOf()) {
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
        metadata: Map<Name, Value> = mapOf()
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
        mintable: Mintable = Mintable.Infinitely()
    ) {
        client.sendTransaction {
            account(admin)
            this.registerAssetDefinition(id.asAssetDefinitionId(), type, Metadata(metadata), mintable)
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun registerAsset(id: String, value: AssetValue) {
        client.sendTransaction {
            account(admin)
            this.registerAsset(id.asAssetId(), value)
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun findAllDomains(queryFilter: PredicateBox? = null) = QueryBuilder
        .findAllDomains(queryFilter)
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }

    suspend fun findAllAccounts(queryFilter: PredicateBox? = null) = QueryBuilder
        .findAllAccounts(queryFilter)
        .account(admin)
        .buildSigned(keyPair)
        .let { client.sendQuery(it) }

    suspend fun findAllAssets(queryFilter: PredicateBox? = null) = QueryBuilder
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
}
