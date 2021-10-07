package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionEntry
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction._VersionedTransactionV1
import java.security.KeyPair
import java.time.Duration
import java.time.Instant
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

class TransactionBuilder(builder: TransactionBuilder.() -> Unit = {}) {

    var accountId: AccountId?
    val instructions: Lazy<ArrayList<Instruction>>
    var creationTimeMillis: ULong?
    var timeToLiveMillis: ULong?
    var metadata: Lazy<HashMap<String, Value>>

    init {
        accountId = null
        instructions = lazy { ArrayList() }
        creationTimeMillis = null
        timeToLiveMillis = null
        metadata = lazy { HashMap() }
        builder(this)
    }

    fun account(accountId: AccountId) = this.apply { this.accountId = accountId }

    fun account(accountName: String, domain: String) = this.account(AccountId(accountName, domain))

    fun instructions(vararg instructions: Instruction) = this.apply { this.instructions.value.addAll(instructions) }

    fun instructions(instructions: Iterable<Instruction>) = this.apply { this.instructions.value.addAll(instructions) }

    fun instruction(instruction: Instruction) = this.apply { this.instructions.value.add(instruction) }

    fun creationTime(creationTimeMillis: ULong) = this.apply { this.creationTimeMillis = creationTimeMillis }

    fun creationTime(creationTime: Instant) = this.apply { this.creationTime(creationTime.toEpochMilli()) }

    fun creationTime(creationTimeMillis: Long) = this.apply { this.creationTime(creationTimeMillis.toULong()) }

    fun timeToLive(ttlMillis: ULong) = this.apply { this.timeToLiveMillis = ttlMillis }

    fun timeToLive(ttl: Duration) = this.apply { this.timeToLive(ttl.toMillis()) }

    fun timeToLive(ttlMillis: Long) = this.apply { this.timeToLive(ttlMillis.toULong()) }

    fun buildSigned(vararg keyPairs: KeyPair): VersionedTransaction {
        val payload = Payload(
            checkNotNull(accountId) { "Account Id of the sender is mandatory" },
            instructions.value,
            creationTimeMillis ?: fallbackCreationTime(),
            timeToLiveMillis ?: DURATION_OF_24_HOURS_IN_MILLIS,
            metadata.value
        )
        val encodedPayload = payload.encode(Payload)

        val signatures = keyPairs.map {
            Signature(
                it.public.toIrohaPublicKey(),
                it.private.sign(encodedPayload)
            )
        }.toMutableList()

        return VersionedTransaction.V1(
            _VersionedTransactionV1(
                Transaction(
                    payload,
                    signatures
                )
            )
        )
    }

    fun registerAccount(
        id: AccountId,
        signatories: MutableList<PublicKey>,
        metadata: Metadata = Metadata(mutableMapOf())
    ) = this.apply { instructions.value.add(Instructions.registerAccount(id, signatories, metadata)) }

    fun registerAsset(
        id: DefinitionId,
        assetValueType: AssetValueType
    ) = this.apply { instructions.value.add(Instructions.registerAsset(id, assetValueType)) }

    fun storeAsset(
        assetId: AssetId,
        key: String,
        value: Value
    ) = this.apply { instructions.value.add(Instructions.storeAsset(assetId, key, value)) }

    fun mintAsset(
        assetId: AssetId,
        quantity: UInt
    ) = this.apply { instructions.value.add(Instructions.mintAsset(assetId, quantity)) }

    fun registerDomain(
        domainName: String,
        accounts: MutableMap<AccountId, Account> = mutableMapOf(),
        assetDefinitions: MutableMap<DefinitionId, AssetDefinitionEntry> = mutableMapOf()
    ) = this.apply { instructions.value.add(Instructions.registerDomain(domainName, accounts, assetDefinitions)) }

    fun grantSetKeyValueAsset(assetId: AssetId, target: AccountId) =
        this.apply { instructions.value.add(Instructions.grantSetKeyValueAsset(assetId, target)) }

    fun grantMintUserAssetsDefinition(assetDefinitionId: DefinitionId, target: AccountId) =
        this.apply { instructions.value.add(Instructions.grantMintUserAssetsDefinition(assetDefinitionId, target)) }

    fun grantBurnAssetWithDefinitionId(assetDefinitionId: DefinitionId, target: AccountId) =
        this.apply { instructions.value.add(Instructions.grantBurnAssetWithDefinitionId(assetDefinitionId, target)) }

    fun burnAsset(assetId: AssetId, value: UInt) = this.apply {
        instructions.value.add(Instructions.burnAsset(assetId, value))
    }

    fun burnPublicKey(accountId: AccountId, pubKey: PublicKey) = this.apply {
        instructions.value.add(Instructions.burnPublicKey(accountId, pubKey))
    }

    fun removePublicKey(accountId: AccountId, pubKey: PublicKey) = burnPublicKey(accountId, pubKey)

    fun transferAsset(sourceId: AssetId, value: UInt, destinationId: AssetId) = this.apply {
        instructions.value.add(Instructions.transferAsset(sourceId, value, destinationId))
    }

    fun doIf(condition: Boolean, then: Instruction, otherwise: Instruction) = this.apply {
        instructions.value.add(Instructions.doIf(condition, then, otherwise))
    }

    private fun fallbackCreationTime() = System.currentTimeMillis().toULong()

    companion object {
        fun builder() = TransactionBuilder()

        val DURATION_OF_24_HOURS_IN_MILLIS = Duration.ofHours(24).toMillis().toULong()
    }
}
