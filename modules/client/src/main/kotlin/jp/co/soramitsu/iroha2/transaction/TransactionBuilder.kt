package jp.co.soramitsu.iroha2.transaction

import jp.co.soramitsu.iroha2.DigestFunction
import jp.co.soramitsu.iroha2.U32_MAX_VALUE
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asSignatureOf
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.crypto.signature.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Mintable
import jp.co.soramitsu.iroha2.generated.datamodel.domain.IpfsPath
import jp.co.soramitsu.iroha2.generated.datamodel.events.FilterBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Executable
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.action.Repeats
import jp.co.soramitsu.iroha2.sign
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import java.math.BigDecimal
import java.math.BigInteger
import java.security.KeyPair
import java.time.Duration
import java.time.Instant
import kotlin.random.Random
import kotlin.random.nextLong
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id as DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.EventFilter as TimeEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.role.Id as RoleId
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id as TriggerId

class TransactionBuilder(builder: TransactionBuilder.() -> Unit = {}) {

    var accountId: AccountId?
    val instructions: Lazy<ArrayList<Instruction>>
    var creationTimeMillis: BigInteger?
    var timeToLiveMillis: BigInteger?
    var nonce: Long?
    var metadata: Lazy<HashMap<Name, Value>>

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

    fun account(accountName: Name, domainId: DomainId) = this.account(AccountId(accountName, domainId))

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
            Executable.Instructions(instructions.value),
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
            ).asSignatureOf<Payload>()
        }.toList()

        return VersionedTransaction.V1(
            Transaction(payload, signatures)
        )
    }

    @JvmOverloads
    fun registerTimeTrigger(
        triggerId: TriggerId,
        isi: List<Instruction>,
        repeats: Repeats,
        accountId: AccountId,
        filter: TimeEventFilter,
        metadata: Metadata = Metadata(mapOf())
    ) = this.apply {
        instructions.value.add(
            Instructions.registerTimeTrigger(
                triggerId,
                isi,
                repeats,
                accountId,
                filter,
                metadata
            )
        )
    }

    @JvmOverloads
    fun registerExecutableTrigger(
        triggerId: TriggerId,
        isi: List<Instruction>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata = Metadata(mapOf())
    ) = this.apply {
        instructions.value.add(
            Instructions.registerExecutableTrigger(
                triggerId,
                isi,
                repeats,
                accountId,
                metadata
            )
        )
    }

    @JvmOverloads
    fun registerEventTrigger(
        triggerId: TriggerId,
        isi: List<Instruction>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata = Metadata(mapOf()),
        filter: FilterBox
    ) = this.apply {
        instructions.value.add(
            Instructions.registerEventTrigger(
                triggerId,
                isi,
                repeats,
                accountId,
                metadata,
                filter
            )
        )
    }

    @JvmOverloads
    fun registerWasmTrigger(
        triggerId: TriggerId,
        wasm: ByteArray,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata = Metadata(mapOf()),
        filter: FilterBox
    ) = this.apply {
        instructions.value.add(
            Instructions.registerWasmTrigger(
                triggerId,
                wasm,
                repeats,
                accountId,
                metadata,
                filter
            )
        )
    }

    @JvmOverloads
    fun registerPreCommitTrigger(
        triggerId: TriggerId,
        isi: List<Instruction>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata = Metadata(mapOf())
    ) = this.apply {
        instructions.value.add(
            Instructions.registerPreCommitTrigger(
                triggerId,
                isi,
                repeats,
                accountId,
                metadata
            )
        )
    }

    @JvmOverloads
    fun unregisterTrigger(
        triggerName: String,
    ) = this.apply {
        instructions.value.add(
            Instructions.unregisterTrigger(
                triggerName
            )
        )
    }

    fun grantRole(
        roleId: RoleId,
        accountId: AccountId
    ) = this.apply { instructions.value.add(Instructions.grantRole(roleId, accountId)) }

    fun registerRole(
        id: RoleId,
        vararg tokens: PermissionToken
    ) = this.apply { instructions.value.add(Instructions.registerRole(id, *tokens)) }

    @JvmOverloads
    fun registerAccount(
        id: AccountId,
        signatories: List<PublicKey>,
        metadata: Metadata = Metadata(mapOf())
    ) = this.apply { instructions.value.add(Instructions.registerAccount(id, signatories, metadata)) }

    @JvmOverloads
    fun registerAsset(
        id: DefinitionId,
        assetValueType: AssetValueType,
        metadata: Metadata = Metadata(mapOf()),
        mintable: Mintable = Mintable.Infinitely()
    ) = this.apply { instructions.value.add(Instructions.registerAsset(id, assetValueType, metadata, mintable)) }

    fun setKeyValue(
        assetId: AssetId,
        key: String,
        value: Value
    ) = this.apply { instructions.value.add(Instructions.setKeyValue(assetId, key.asName(), value)) }

    fun setKeyValue(
        assetId: AssetId,
        key: Name,
        value: Value
    ) = this.apply { instructions.value.add(Instructions.setKeyValue(assetId, key, value)) }

    fun setKeyValue(
        accountId: AccountId,
        key: Name,
        value: Value
    ) = this.apply { instructions.value.add(Instructions.setKeyValue(accountId, key, value)) }

    fun removeKeyValue(
        assetId: AssetId,
        key: Name,
    ) = this.apply { instructions.value.add(Instructions.removeKeyValue(assetId, key)) }

    fun removeKeyValue(
        assetId: AssetId,
        key: String
    ) = removeKeyValue(assetId, key.asName())

    fun executeTrigger(
        triggerId: TriggerId
    ) = this.apply { instructions.value.add(Instructions.executeTrigger(triggerId)) }

    fun mintAsset(
        assetId: AssetId,
        quantity: Long
    ) = this.apply { instructions.value.add(Instructions.mintAsset(assetId, quantity)) }

    fun mintAsset(
        assetId: AssetId,
        quantity: BigDecimal
    ) = this.apply { instructions.value.add(Instructions.mintAsset(assetId, quantity)) }

    @JvmOverloads
    fun registerDomain(
        domainId: DomainId,
        metadata: Map<Name, Value> = mapOf(),
        logo: IpfsPath? = null
    ) = this.apply {
        instructions.value.add(
            Instructions.registerDomain(
                domainId,
                metadata,
                logo
            )
        )
    }

    @JvmOverloads
    fun registerPeer(
        address: String,
        payload: ByteArray,
        digestFunction: String = DigestFunction.Ed25519.hashFunName
    ) = this.apply { instructions.value.add(Instructions.registerPeer(address, payload, digestFunction)) }

    @JvmOverloads
    fun unregisterPeer(
        address: String,
        payload: ByteArray,
        digestFunction: String = DigestFunction.Ed25519.hashFunName
    ) = this.apply { instructions.value.add(Instructions.unregisterPeer(address, payload, digestFunction)) }

    fun grantSetKeyValueAsset(assetId: AssetId, target: AccountId) =
        this.apply { instructions.value.add(Instructions.grantSetKeyValueAsset(assetId, target)) }

    fun grantSetKeyValueAccount(accountId: AccountId, target: AccountId) =
        this.apply { instructions.value.add(Instructions.grantSetKeyValueMetadata(accountId, target)) }

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

    fun mintPublicKey(accountId: AccountId, pubKey: PublicKey) = this.apply {
        instructions.value.add(Instructions.mintPublicKey(accountId, pubKey))
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
