package jp.co.soramitsu.iroha2.transaction

import jp.co.soramitsu.iroha2.IrohaClientException
import jp.co.soramitsu.iroha2.Permissions
import jp.co.soramitsu.iroha2.U32_MAX_VALUE
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asSignatureOf
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.Algorithm
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.Executable
import jp.co.soramitsu.iroha2.generated.FilterBox
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.IpfsPath
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.Mintable
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.NonZeroOfu32
import jp.co.soramitsu.iroha2.generated.NonZeroOfu64
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.PublicKey
import jp.co.soramitsu.iroha2.generated.Repeats
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.Signature
import jp.co.soramitsu.iroha2.generated.SignaturesOfOfTransactionPayload
import jp.co.soramitsu.iroha2.generated.SignedTransaction
import jp.co.soramitsu.iroha2.generated.TimeEventFilter
import jp.co.soramitsu.iroha2.generated.TransactionPayload
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.Value
import jp.co.soramitsu.iroha2.generated.VersionedSignedTransaction
import jp.co.soramitsu.iroha2.sign
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import java.math.BigDecimal
import java.math.BigInteger
import java.security.KeyPair
import java.time.Duration
import java.time.Instant
import kotlin.random.Random
import kotlin.random.nextLong

class TransactionBuilder(builder: TransactionBuilder.() -> Unit = {}) {

    var accountId: AccountId?
    val instructions: Lazy<ArrayList<InstructionBox>>
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

    fun instructions(vararg instructions: InstructionBox) = this.apply { this.instructions.value.addAll(instructions) }

    fun instructions(instructions: Iterable<InstructionBox>) =
        this.apply { this.instructions.value.addAll(instructions) }

    fun instruction(instruction: InstructionBox) = this.apply { this.instructions.value.add(instruction) }

    fun creationTime(creationTimeMillis: BigInteger) = this.apply { this.creationTimeMillis = creationTimeMillis }

    fun creationTime(creationTime: Instant) = this.apply { this.creationTime(creationTime.toEpochMilli()) }

    fun creationTime(creationTimeMillis: Long) = this.apply { this.creationTime(creationTimeMillis.toBigInteger()) }

    fun timeToLive(ttlMillis: BigInteger) = this.apply { this.timeToLiveMillis = ttlMillis }

    fun timeToLive(ttl: Duration) = this.apply { this.timeToLive(ttl.toMillis()) }

    fun timeToLive(ttlMillis: Long) = this.apply { this.timeToLive(ttlMillis.toBigInteger()) }

    fun buildSigned(vararg keyPairs: KeyPair): VersionedSignedTransaction {
        val payload = TransactionPayload(
            creationTimeMillis ?: fallbackCreationTime(),
            checkNotNull(accountId) { "Account Id of the sender is mandatory" },
            Executable.Instructions(instructions.value),
            NonZeroOfu64(timeToLiveMillis ?: DURATION_OF_24_HOURS_IN_MILLIS),
            NonZeroOfu32(nonce ?: throw IrohaClientException("Nonce must not be null")),
            metadata.value,
        )
        val encodedPayload = TransactionPayload.encode(payload)

        val signatures = keyPairs.map {
            Signature(
                it.public.toIrohaPublicKey(),
                it.private.sign(encodedPayload),
            ).asSignatureOf<TransactionPayload>()
        }.toList()

        return VersionedSignedTransaction.V1(
            SignedTransaction(SignaturesOfOfTransactionPayload(signatures), payload),
        )
    }

    @JvmOverloads
    fun registerTimeTrigger(
        triggerId: TriggerId,
        isi: List<InstructionBox>,
        repeats: Repeats,
        accountId: AccountId,
        filter: TimeEventFilter,
        metadata: Metadata = Metadata(mapOf()),
    ) = this.apply {
        instructions.value.add(
            Instructions.registerTimeTrigger(
                triggerId,
                isi,
                repeats,
                accountId,
                filter,
                metadata,
            ),
        )
    }

    @JvmOverloads
    fun registerExecutableTrigger(
        triggerId: TriggerId,
        isi: List<InstructionBox>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata = Metadata(mapOf()),
    ) = this.apply {
        instructions.value.add(
            Instructions.registerExecutableTrigger(
                triggerId,
                isi,
                repeats,
                accountId,
                metadata,
            ),
        )
    }

    @JvmOverloads
    fun registerEventTrigger(
        triggerId: TriggerId,
        isi: List<InstructionBox>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata = Metadata(mapOf()),
        filter: FilterBox,
    ) = this.apply {
        instructions.value.add(
            Instructions.registerEventTrigger(
                triggerId,
                isi,
                repeats,
                accountId,
                metadata,
                filter,
            ),
        )
    }

    @JvmOverloads
    fun registerWasmTrigger(
        triggerId: TriggerId,
        wasm: ByteArray,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata = Metadata(mapOf()),
        filter: FilterBox,
    ) = this.apply {
        instructions.value.add(
            Instructions.registerWasmTrigger(
                triggerId,
                wasm,
                repeats,
                accountId,
                metadata,
                filter,
            ),
        )
    }

    @JvmOverloads
    fun registerPreCommitTrigger(
        triggerId: TriggerId,
        isi: List<InstructionBox>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata = Metadata(mapOf()),
    ) = this.apply {
        instructions.value.add(
            Instructions.registerPreCommitTrigger(
                triggerId,
                isi,
                repeats,
                accountId,
                metadata,
            ),
        )
    }

    fun unregisterAsset(id: AssetId) = this.apply {
        instructions.value.add(Instructions.unregisterAsset(id))
    }

    fun unregisterTrigger(id: TriggerId) = this.apply {
        instructions.value.add(
            Instructions.unregisterTrigger(id),
        )
    }

    fun unregisterTrigger(triggerName: String, domainId: DomainId? = null) = this.apply {
        instructions.value.add(
            Instructions.unregisterTrigger(triggerName, domainId),
        )
    }

    fun unregisterAccount(id: AccountId) = this.apply {
        instructions.value.add(
            Instructions.unregisterAccount(id),
        )
    }

    fun unregisterDomain(id: DomainId) = this.apply {
        instructions.value.add(
            Instructions.unregisterDomain(id),
        )
    }

    fun grantRole(
        roleId: RoleId,
        accountId: AccountId,
    ) = this.apply { instructions.value.add(Instructions.grantRole(roleId, accountId)) }

    fun registerRole(
        id: RoleId,
        vararg tokens: PermissionToken,
    ) = this.apply { instructions.value.add(Instructions.registerRole(id, *tokens)) }

    @JvmOverloads
    fun registerAccount(
        id: AccountId,
        signatories: List<PublicKey>,
        metadata: Metadata = Metadata(mapOf()),
    ) = this.apply { instructions.value.add(Instructions.registerAccount(id, signatories, metadata)) }

    @JvmOverloads
    fun registerAssetDefinition(
        id: AssetDefinitionId,
        assetValueType: AssetValueType,
        metadata: Metadata = Metadata(mapOf()),
        mintable: Mintable = Mintable.Infinitely(),
    ) = this.apply {
        instructions.value.add(
            Instructions.registerAssetDefinition(id, assetValueType, metadata, mintable),
        )
    }

    fun registerAsset(
        id: AssetId,
        assetValue: AssetValue,
    ) = this.apply { instructions.value.add(Instructions.registerAsset(id, assetValue)) }

    fun setKeyValue(
        assetId: AssetId,
        key: String,
        value: Value,
    ) = this.apply { instructions.value.add(Instructions.setKeyValue(assetId, key.asName(), value)) }

    fun setKeyValue(
        assetId: AssetId,
        key: Name,
        value: Value,
    ) = this.apply { instructions.value.add(Instructions.setKeyValue(assetId, key, value)) }

    fun setKeyValue(
        accountId: AccountId,
        key: Name,
        value: Value,
    ) = this.apply { instructions.value.add(Instructions.setKeyValue(accountId, key, value)) }

    fun setKeyValue(
        definitionId: AssetDefinitionId,
        key: Name,
        value: Value,
    ) = this.apply { instructions.value.add(Instructions.setKeyValue(definitionId, key, value)) }

    fun setKeyValue(
        domainId: DomainId,
        key: Name,
        value: Value,
    ) = this.apply { instructions.value.add(Instructions.setKeyValue(domainId, key, value)) }

    fun removeKeyValue(
        assetId: AssetId,
        key: Name,
    ) = this.apply { instructions.value.add(Instructions.removeKeyValue(assetId, key)) }

    fun removeKeyValue(
        assetId: AssetId,
        key: String,
    ) = removeKeyValue(assetId, key.asName())

    fun executeTrigger(
        triggerId: TriggerId,
    ) = this.apply { instructions.value.add(Instructions.executeTrigger(triggerId)) }

    fun mintAsset(
        assetId: AssetId,
        quantity: Int,
    ) = this.apply { instructions.value.add(Instructions.mintAsset(assetId, quantity)) }

    fun mintAsset(
        assetId: AssetId,
        quantity: BigDecimal,
    ) = this.apply { instructions.value.add(Instructions.mintAsset(assetId, quantity)) }

    @JvmOverloads
    fun registerDomain(
        domainId: DomainId,
        metadata: Map<Name, Value> = mapOf(),
        logo: IpfsPath? = null,
    ) = this.apply {
        instructions.value.add(
            Instructions.registerDomain(
                domainId,
                metadata,
                logo,
            ),
        )
    }

    @JvmOverloads
    fun registerPeer(
        address: String,
        payload: ByteArray,
        algorithm: Algorithm = Algorithm.Ed25519(),
    ) = this.apply { instructions.value.add(Instructions.registerPeer(address, payload, algorithm)) }

    @JvmOverloads
    fun unregisterPeer(
        address: String,
        payload: ByteArray,
        algorithm: Algorithm = Algorithm.Ed25519(),
    ) = this.apply { instructions.value.add(Instructions.unregisterPeer(address, payload, algorithm)) }

    fun grantPermissionToken(permission: Permissions, payload: ByteArray, target: AccountId) = this.apply {
        instructions.value.add(Instructions.grantPermissionToken(permission, payload, target))
    }

    fun burnAsset(assetId: AssetId, value: Int) = this.apply {
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

    fun transferAsset(sourceId: AssetId, value: Int, destinationId: AccountId) = this.apply {
        instructions.value.add(Instructions.transferAsset(sourceId, value, destinationId))
    }

    fun `if`(condition: Boolean, then: InstructionBox, otherwise: InstructionBox) = this.apply {
        instructions.value.add(Instructions.`if`(condition, then, otherwise))
    }

    fun pair(left: InstructionBox, right: InstructionBox) = this.apply {
        instructions.value.add(Instructions.pair(left, right))
    }

    fun sequence(vararg instructions: InstructionBox) = this.apply {
        this.instructions.value.add(Instructions.sequence(instructions.toList()))
    }

    fun fail(message: String) = this.apply {
        this.instructions.value.add(Instructions.fail(message))
    }

    fun revokeSetKeyValueAsset(assetId: AssetId, target: AccountId) =
        this.apply { instructions.value.add(Instructions.revokeSetKeyValueAsset(assetId, target)) }

    fun revokeRole(
        roleId: RoleId,
        accountId: AccountId,
    ) = this.apply { instructions.value.add(Instructions.revokeRole(roleId, accountId)) }

    private fun fallbackCreationTime() = System.currentTimeMillis().toBigInteger()

    companion object {
        fun builder() = TransactionBuilder()

        val DURATION_OF_24_HOURS_IN_MILLIS = Duration.ofHours(24).toMillis().toBigInteger()
    }
}
