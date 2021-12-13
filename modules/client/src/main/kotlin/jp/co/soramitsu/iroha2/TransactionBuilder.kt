package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.crypto.signature.Signature
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
import java.math.BigDecimal
import java.math.BigInteger
import java.security.KeyPair
import java.time.Duration
import java.time.Instant
import kotlin.random.Random
import kotlin.random.nextLong
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

class TransactionBuilder(builder: TransactionBuilder.() -> Unit = {}) {

    var accountId: AccountId?
    val instructions: Lazy<ArrayList<Instruction>>
    var creationTimeMillis: BigInteger?
    var timeToLiveMillis: BigInteger?
    var nonce: Long?
    var metadata: Lazy<HashMap<String, Value>>

    init {
        accountId = null
        instructions = lazy { ArrayList() }
        creationTimeMillis = null
        timeToLiveMillis = null
        nonce = Random.nextLong(0..U32_MAX_VALUE) // UInt32 max value
        metadata = lazy { HashMap() }
        builder(this)
    }

    fun account(accountId: AccountId) = this.apply { this.accountId = accountId }

    fun account(accountName: String, domain: String) = this.account(AccountId(accountName, domain))

    fun instructions(vararg instructions: Instruction) = this.apply { this.instructions.value.addAll(instructions) }

    fun instructions(instructions: Iterable<Instruction>) = this.apply { this.instructions.value.addAll(instructions) }

    fun instruction(instruction: Instruction) = this.apply { this.instructions.value.add(instruction) }

    fun creationTime(creationTimeMillis: BigInteger) = this.apply { this.creationTimeMillis = creationTimeMillis }

    fun creationTime(creationTime: Instant) = this.apply { this.creationTime(creationTime.toEpochMilli()) }

    fun creationTime(creationTimeMillis: Long) = this.apply { this.creationTime(creationTimeMillis.toBigInteger()) }

    fun timeToLive(ttlMillis: BigInteger) = this.apply { this.timeToLiveMillis = ttlMillis }

    fun timeToLive(ttl: Duration) = this.apply { this.timeToLive(ttl.toMillis()) }

    fun timeToLive(ttlMillis: Long) = this.apply { this.timeToLive(ttlMillis.toBigInteger()) }

    fun buildSigned(vararg keyPairs: KeyPair): VersionedTransaction {
        val payload = Payload(
            checkNotNull(accountId) { "Account Id of the sender is mandatory" },
            instructions.value,
            creationTimeMillis ?: fallbackCreationTime(),
            timeToLiveMillis ?: DURATION_OF_24_HOURS_IN_MILLIS,
            nonce,
            metadata.value
        )
        val encodedPayload = Payload.encode(payload)

        val signatures = keyPairs.map {
            Signature(
                it.public.toIrohaPublicKey(),
                it.private.sign(encodedPayload)
            )
        }.toSet()

        return VersionedTransaction.V1(
            _VersionedTransactionV1(
                Transaction(payload, signatures)
            )
        )
    }

    fun registerAccount(
        id: AccountId,
        signatories: List<PublicKey>,
        metadata: Metadata = Metadata(mapOf())
    ) = this.apply { instructions.value.add(Instructions.registerAccount(id, signatories, metadata)) }

    fun registerAsset(
        id: DefinitionId,
        assetValueType: AssetValueType
    ) = this.apply { instructions.value.add(Instructions.registerAsset(id, assetValueType)) }

    fun setKeyValue(
        assetId: AssetId,
        key: String,
        value: Value
    ) = this.apply { instructions.value.add(Instructions.setKeyValue(assetId, key, value)) }

    fun removeKeyValue(
        assetId: AssetId,
        key: String,
    ) = this.apply { instructions.value.add(Instructions.removeKeyValue(assetId, key)) }

    fun mintAsset(
        assetId: AssetId,
        quantity: Long
    ) = this.apply { instructions.value.add(Instructions.mintAsset(assetId, quantity)) }

    fun mintAsset(
        assetId: AssetId,
        quantity: BigDecimal
    ) = this.apply { instructions.value.add(Instructions.mintAsset(assetId, quantity)) }

    fun registerDomain(
        domainName: String,
        accounts: Map<AccountId, Account> = mapOf(),
        assetDefinitions: Map<DefinitionId, AssetDefinitionEntry> = mapOf()
    ) = this.apply { instructions.value.add(Instructions.registerDomain(domainName, accounts, assetDefinitions)) }

    fun registerPeer(
        address: String,
        payload: ByteArray,
        digestFunction: String = DigestFunction.Ed25519.hashFunName
    ) = this.apply { instructions.value.add(Instructions.registerPeer(address, payload, digestFunction)) }

    fun unregisterPeer(
        address: String,
        payload: ByteArray,
        digestFunction: String = DigestFunction.Ed25519.hashFunName
    ) = this.apply { instructions.value.add(Instructions.unregisterPeer(address, payload, digestFunction)) }

    fun grantSetKeyValueAsset(assetId: AssetId, target: AccountId) =
        this.apply { instructions.value.add(Instructions.grantSetKeyValueAsset(assetId, target)) }

    fun grantMintUserAssetsDefinition(assetDefinitionId: DefinitionId, target: AccountId) =
        this.apply { instructions.value.add(Instructions.grantMintUserAssetsDefinition(assetDefinitionId, target)) }

    fun grantBurnAssetWithDefinitionId(assetDefinitionId: DefinitionId, target: AccountId) =
        this.apply { instructions.value.add(Instructions.grantBurnAssetWithDefinitionId(assetDefinitionId, target)) }

    fun burnAsset(assetId: AssetId, value: Long) = this.apply {
        instructions.value.add(Instructions.burnAsset(assetId, value))
    }

    fun burnAsset(assetId: AssetId, value: BigDecimal) = this.apply {
        instructions.value.add(Instructions.burnAsset(assetId, value))
    }

    fun burnPublicKey(accountId: AccountId, pubKey: PublicKey) = this.apply {
        instructions.value.add(Instructions.burnPublicKey(accountId, pubKey))
    }

    fun removePublicKey(accountId: AccountId, pubKey: PublicKey) = burnPublicKey(accountId, pubKey)

    fun transferAsset(sourceId: AssetId, value: Long, destinationId: AssetId) = this.apply {
        instructions.value.add(Instructions.transferAsset(sourceId, value, destinationId))
    }

    fun `if`(condition: Boolean, then: Instruction, otherwise: Instruction) = this.apply {
        instructions.value.add(Instructions.`if`(condition, then, otherwise))
    }

    fun pair(left: Instruction, right: Instruction) = this.apply {
        instructions.value.add(Instructions.pair(left, right))
    }

    fun sequence(vararg instructions: Instruction) = this.apply {
        this.instructions.value.add(Instructions.sequence(instructions.toList()))
    }

    fun fail(message: String) = this.apply {
        this.instructions.value.add(Instructions.fail(message))
    }

    private fun fallbackCreationTime() = System.currentTimeMillis().toBigInteger()

    companion object {
        fun builder() = TransactionBuilder()

        val DURATION_OF_24_HOURS_IN_MILLIS = Duration.ofHours(24).toMillis().toBigInteger()
    }
}
