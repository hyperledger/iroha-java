package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.MetadataValueBox
import jp.co.soramitsu.iroha2.generated.Mintable
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.PublicKey
import kotlinx.coroutines.withTimeout
import java.security.KeyPair
import java.util.UUID

class SendTransaction(
    private val client: AdminIroha2Client,
    private val admin: AccountId,
    private val keyPair: KeyPair,
    private val chainUuid: UUID,
    private val timeout: Long = 10000,
) {

    suspend fun registerDomain(
        id: String,
        metadata: Map<Name, MetadataValueBox> = mapOf(),
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            chainId(chainUuid)
            registerDomain(id.asDomainId(), metadata)
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun registerAccount(
        id: String,
        signatories: List<PublicKey>,
        metadata: Map<Name, MetadataValueBox> = mapOf(),
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            chainId(chainUuid)
            registerAccount(id.asAccountId(), signatories, Metadata(metadata))
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun registerAssetDefinition(
        id: String,
        type: AssetValueType = AssetValueType.Store(),
        metadata: Map<Name, MetadataValueBox> = mapOf(),
        mintable: Mintable = Mintable.Infinitely(),
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            chainId(chainUuid)
            registerAssetDefinition(id.asAssetDefinitionId(), type, Metadata(metadata), mintable)
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
            chainId(chainUuid)
            registerAsset(id.asAssetId(), value)
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
            chainId(chainUuid)
            transferAsset(from.asAssetId(), value, to.asAccountId())
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }

    suspend fun burnAssets(
        assetId: String,
        value: Int,
        admin: AccountId = this.admin,
        keyPair: KeyPair = this.keyPair,
    ) {
        client.sendTransaction {
            account(admin)
            chainId(chainUuid)
            burnAsset(assetId.asAssetId(), value)
            buildSigned(keyPair)
        }.also {
            withTimeout(timeout) { it.await() }
        }
    }
}
