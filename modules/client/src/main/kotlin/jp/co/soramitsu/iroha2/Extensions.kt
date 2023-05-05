package jp.co.soramitsu.iroha2

import io.ktor.websocket.Frame
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.crypto.signature.Signature
import jp.co.soramitsu.iroha2.generated.crypto.signature.SignatureOf
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.NumericValue
import jp.co.soramitsu.iroha2.generated.datamodel.RegistrableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventsFilterBox
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.ExecutionTime
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.Token
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.TokenId
import jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Executable
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.SignedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedSignedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Trigger
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.generated.primitives.fixed.Fixed
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder
import net.i2p.crypto.eddsa.EdDSAEngine
import org.bouncycastle.jcajce.provider.digest.Blake2b
import org.bouncycastle.util.encoders.Hex
import java.math.BigDecimal
import java.math.BigInteger
import java.security.KeyPair
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.PublicKey
import kotlin.experimental.or

fun <T> Signature.asSignatureOf() = SignatureOf<T>(this)

fun String.asAccountId() = this.split(ACCOUNT_ID_DELIMITER).takeIf {
    it.size == 2
}?.let { parts ->
    AccountId(parts[0].asName(), parts[1].asDomainId())
} ?: throw IllegalArgumentException("Incorrect account ID: $this")

fun String.asAssetDefinitionId() = this.split(ASSET_ID_DELIMITER).takeIf {
    it.size == 2
}?.let { parts ->
    AssetDefinitionId(parts[0].asName(), parts[1].asDomainId())
} ?: throw IllegalArgumentException("Incorrect asset definition ID: $this")

fun String.asAssetId() = this.split(ASSET_ID_DELIMITER).takeIf {
    it.size == 3
}?.let { parts ->
    parts[2].asAccountId().let { accountId ->
        val domainId = parts[1].takeIf { it.isNotBlank() }?.asDomainId()
        AssetId(
            AssetDefinitionId(
                parts[0].asName(),
                domainId ?: accountId.domainId
            ),
            accountId
        )
    }
} ?: throw IllegalArgumentException("Incorrect asset ID: $this")

fun String.asDomainId() = DomainId(Name(this))

fun String.asName() = Name(this)

fun String.asValue() = Value.String(this)

fun Int.asValue() = Value.Numeric(NumericValue.U32(this.toLong()))

fun Long.asValue() = Value.Numeric(NumericValue.U128(BigInteger.valueOf(this)))

fun BigInteger.asValue() = Value.Numeric(NumericValue.U128(this))

fun BigDecimal.asValue() = Value.Numeric(NumericValue.Fixed(Fixed(this)))

fun Boolean.asValue() = Value.Bool(this)

fun AccountId.asValue() = Value.Id(IdBox.AccountId(this))

fun ByteArray.toFrame(fin: Boolean = true) = Frame.Binary(fin, this)

fun ByteArray.toHex(): String = try {
    Hex.toHexString(this)
} catch (ex: Exception) {
    throw HexCodecException("Cannot encode to hex string", ex)
}

fun String.fromHex(): ByteArray = try {
    Hex.decode(this)
} catch (ex: Exception) {
    throw HexCodecException("Cannot decode from hex string `$this`", ex)
}

/**
 * Convert a public key to an Iroha public key
 */
fun PublicKey.toIrohaPublicKey(): jp.co.soramitsu.iroha2.generated.crypto.PublicKey {
    return jp.co.soramitsu.iroha2.generated.crypto.PublicKey(DigestFunction.Ed25519.hashFunName, this.bytes())
}

/**
 * Sign the [message] using the given private key
 *
 * Note: the message must not be prehashed
 */
fun PrivateKey.sign(message: ByteArray): ByteArray = try {
    val sgr = EdDSAEngine(MessageDigest.getInstance(DEFAULT_SPEC.hashAlgorithm))
    sgr.initSign(this)
    sgr.update(message.hash())
    sgr.sign()
} catch (ex: Exception) {
    throw CryptoException("Cannot sign message", ex)
}

/**
 * Verify the [signature] against the [message] and the given public key
 *
 * Note: the message must not be prehashed
 */
fun PublicKey.verify(signature: ByteArray, message: ByteArray): Boolean = try {
    val sgr = EdDSAEngine(MessageDigest.getInstance(DEFAULT_SPEC.hashAlgorithm))
    sgr.initVerify(this)
    sgr.update(message.hash())
    sgr.verify(signature)
} catch (ex: Exception) {
    throw CryptoException("Cannot verify message", ex)
}

fun ByteArray.toIrohaHash(): Hash {
    if (this.size != 32) throw IrohaSdkException("Hash byte array size must be 32")

    this[31] = this[31] or 1
    return Hash(this)
}

fun ByteArray.hash(): ByteArray {
    val bytes = Blake2b.Blake2b256().digest(this)
    bytes[bytes.size - 1] = bytes[bytes.size - 1] or 1
    return bytes
}

/**
 * Hash the given versioned transaction (`VersionedTransaction.V1`)
 */
fun VersionedSignedTransaction.V1.hash(): ByteArray {
    return this.signedTransaction
        .payload
        .let { Payload.encode(it) }
        .hash()
}

/**
 * Hash the given versioned transaction. Maintains only `VersionedTransaction.V1`
 */
fun VersionedSignedTransaction.hash() = when (this) {
    is VersionedSignedTransaction.V1 -> this.hash()
}

/**
 * Append signatures to a transaction. Maintains only `VersionedTransaction.V1`
 */
fun VersionedSignedTransaction.appendSignatures(vararg keypairs: KeyPair): VersionedSignedTransaction {
    return when (this) {
        is VersionedSignedTransaction.V1 -> {
            val encodedPayload = signedTransaction
                .payload
                .let { Payload.encode(it) }
            val signatures = keypairs.map {
                Signature(
                    it.public.toIrohaPublicKey(),
                    it.private.sign(encodedPayload)
                ).asSignatureOf<Payload>()
            }.toSet()

            VersionedSignedTransaction.V1(
                SignedTransaction(
                    signedTransaction.payload,
                    signedTransaction.signatures.plus(signatures)
                )
            )
        }
    }
}

/**
 * Cast to another type
 */
inline fun <reified B> Any.cast(): B {
    return this as? B
        ?: throw ClassCastException("Could not cast `${this::class.qualifiedName}` to `${B::class.qualifiedName}`")
}

/**
 * Wrap an object in `EvaluatesTo`
 */
inline fun <reified T> T.evaluatesTo(): EvaluatesTo<T> {
    return when (this) {
        is String -> Value.String(this)
        is Boolean -> Value.Bool(this)
        is AssetId -> Value.Id(IdBox.AssetId(this))
        is AssetDefinitionId -> Value.Id(IdBox.AssetDefinitionId(this))
        is AccountId -> Value.Id(IdBox.AccountId(this))
        is DomainId -> Value.Id(IdBox.DomainId(this))
        is RoleId -> Value.Id(IdBox.RoleId(this))
        is TriggerId -> Value.Id(IdBox.TriggerId(this))
        is IdBox -> Value.Id(this)
        is Hash -> Value.Hash(this)
        is Name -> Value.Name(this)
        is Token -> Value.PermissionToken(this)
        is IdentifiableBox -> Value.Identifiable(this)
        is RegistrableBox -> Value.Identifiable(this.toIdentifiableBox())
        is Value -> this
        else -> throw IllegalArgumentException("Unsupported value type `${T::class.qualifiedName}`")
    }.let { value ->
        EvaluatesTo(Expression.Raw(value))
    }
}

fun AccountId.toValueId() = Value.Id(IdBox.AccountId(this))

fun AssetId.toValueId() = Value.Id(IdBox.AssetId(this))

fun AssetDefinitionId.toValueId() = Value.Id(IdBox.AssetDefinitionId(this))

fun RegistrableBox.toIdentifiableBox() = when (this) {
    is RegistrableBox.Account -> IdentifiableBox.NewAccount(this.newAccount)
    is RegistrableBox.Peer -> IdentifiableBox.Peer(this.peer)
    is RegistrableBox.Asset -> IdentifiableBox.Asset(this.asset)
    is RegistrableBox.AssetDefinition -> IdentifiableBox.NewAssetDefinition(this.newAssetDefinition)
    is RegistrableBox.Role -> IdentifiableBox.NewRole(this.newRole)
    is RegistrableBox.Domain -> IdentifiableBox.NewDomain(this.newDomain)
    is RegistrableBox.Trigger -> IdentifiableBox.Trigger(this.trigger)
    is RegistrableBox.PermissionTokenDefinition -> IdentifiableBox.PermissionTokenDefinition(
        this.definition
    )

    is RegistrableBox.Validator -> IdentifiableBox.Validator(this.validator)
}

fun <T> T.asValue() = when (this) {
    is String -> this.asValue()
    is Long -> this.asValue()
    is Int -> this.asValue()
    is BigInteger -> this.asValue()
    is BigDecimal -> this.asValue()
    is Boolean -> this.asValue()
    is AccountId -> this.asValue()
    else -> throw RuntimeException("Unsupported type")
}

fun AssetId.asString() = this.definitionId.asString() + ASSET_ID_DELIMITER + this.accountId.asString()

fun AssetDefinitionId.asString() = this.name.string + ASSET_ID_DELIMITER + this.domainId.name.string

fun AccountId.asString() = this.name.string + ACCOUNT_ID_DELIMITER + this.domainId.name.string

fun DomainId.asString() = this.name.string

fun TokenId.asString() = this.name.string

fun RoleId.asString() = this.name.string

fun TriggerId.asString() = when (this.domainId) {
    null -> this.name.string
    else -> this.name.string + TRIGGER_ID_DELIMITER + this.domainId!!.name.string
}

fun Metadata.merge(extra: Metadata) = Metadata(
    this.map.toMutableMap().also { it.putAll(extra.map) }
)

fun Instruction.Register.extractIdentifiableBox() = runCatching {
    this.registerBox.`object`.expression
        .cast<Expression.Raw>().value
        .cast<Value.Identifiable>().identifiableBox
}.getOrNull()

fun Iterable<Instruction>.extractIdentifiableBoxes() = this.asSequence()
    .filterIsInstance<Instruction.Register>()
    .map { it.registerBox.`object`.expression }
    .filterIsInstance<Expression.Raw>()
    .map { it.value }
    .filterIsInstance<Value.Identifiable>()
    .map { it.identifiableBox }.toList()

fun IdBox.extractId(): Any = when (this) {
    is IdBox.RoleId -> this.roleId
    is IdBox.AccountId -> this.accountId
    is IdBox.AssetId -> this.assetId
    is IdBox.AssetDefinitionId -> this.assetDefinitionId
    is IdBox.DomainId -> this.domainId
    is IdBox.TriggerId -> this.triggerId
    is IdBox.PeerId -> this.peerId
    is IdBox.PermissionTokenDefinitionId -> this.tokenId
    is IdBox.ParameterId -> this.id
    is IdBox.ValidatorId -> this.validatorId
}

fun Instruction.Register.extractAccount() = this
    .registerBox.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.Identifiable>().identifiableBox
    .cast<IdentifiableBox.NewAccount>().newAccount

fun Instruction.Register.extractDomain() = this
    .registerBox.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.Identifiable>().identifiableBox
    .cast<IdentifiableBox.NewDomain>().newDomain

fun Instruction.Register.extractAssetDefinition() = this
    .registerBox.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.Identifiable>().identifiableBox
    .cast<IdentifiableBox.NewAssetDefinition>().newAssetDefinition

fun Instruction.SetKeyValue.extractKey() = this
    .setKeyValueBox.key.expression
    .cast<Expression.Raw>().value
    .cast<Value.Name>().name
    .string

fun Instruction.SetKeyValue.extractAccountId() = this.setKeyValueBox.objectId.extractAccountId()

fun Instruction.Unregister.extractAccountId() = this.unregisterBox.objectId.extractAccountId()

fun Instruction.Unregister.extractDomainId() = this.unregisterBox.objectId.extractDomainId()

fun <T> EvaluatesTo<T>.extractAssetId() = this
    .expression
    .cast<Expression.Raw>().value
    .cast<Value.Id>().idBox
    .cast<IdBox.AssetId>().assetId

fun <T> EvaluatesTo<T>.extractAccountId() = this
    .expression
    .cast<Expression.Raw>().value
    .cast<Value.Id>().idBox
    .cast<IdBox.AccountId>().accountId

fun <T> EvaluatesTo<T>.extractDomainId() = this
    .expression
    .cast<Expression.Raw>().value
    .cast<Value.Id>().idBox
    .cast<IdBox.DomainId>().domainId

fun Instruction.Mint.extractPublicKey() = this
    .mintBox.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.PublicKey>().publicKey
    .payload.toHex()

inline fun <reified I : Instruction> VersionedSignedTransaction.V1.extractInstruction() = this
    .extractInstructionVec<I>()
    .first().cast<I>()

inline fun <reified I : Instruction> VersionedSignedTransaction.V1.extractInstructions() = this
    .extractInstructionVec<I>()
    .cast<List<I>>()

inline fun <reified I : Instruction> VersionedSignedTransaction.V1.extractInstructionVec() = this
    .signedTransaction.payload.instructions
    .cast<Executable.Instructions>()
    .vec.filterIsInstance<I>()

fun Instruction.Register.extractNewDomainMetadata() = this
    .registerBox.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.Identifiable>().identifiableBox
    .cast<IdentifiableBox.NewDomain>().newDomain.metadata

fun Instruction.SetKeyValue.extractDomainId() = this
    .setKeyValueBox.objectId.expression
    .cast<Expression.Raw>().value
    .cast<Value.Id>().idBox
    .cast<IdBox.DomainId>().domainId

fun Instruction.SetKeyValue.key() = this
    .setKeyValueBox.key.expression
    .cast<Expression.Raw>().value
    .cast<Value.Name>().name.string

fun Instruction.SetKeyValue.extractValueString() = this
    .setKeyValueBox.value.expression
    .cast<Expression.Raw>().value
    .cast<Value.String>().string

fun Instruction.SetKeyValue.extractValueU32() = this.setKeyValueBox.value.extractValueU32()

fun Instruction.SetKeyValue.extractValueU128() = this
    .setKeyValueBox.value.expression
    .cast<Expression.Raw>().value
    .getValue<Value.Numeric>().numericValue
    .getValue<BigInteger>()

fun Instruction.SetKeyValue.extractValueBoolean() = this
    .setKeyValueBox.value.expression
    .cast<Expression.Raw>().value
    .cast<Value.Bool>().bool

fun Instruction.Grant.extractValuePermissionToken() = this
    .grantBox.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.PermissionToken>().token

fun Instruction.Burn.extractValueU32() = this.burnBox.`object`.extractValueU32()

fun EvaluatesTo<Value>.extractValueU32() = this
    .expression
    .cast<Expression.Raw>().value
    .getValue<Value.Numeric>().numericValue
    .getValue<Long>()

fun Trigger<*>.extractIsi() = this.action.executable.cast<Executable.Instructions>().vec

fun Trigger<*>.extractSchedule() = this.action.filter
    .cast<EventsFilterBox.Time>()
    .timeEventFilter.executionTime
    .cast<ExecutionTime.Schedule>().schedule

fun Metadata.getStringValue(key: String) = this.map.getStringValue(key)

fun Metadata.getBooleanValue(key: String) = this.map.getBooleanValue(key)

fun Metadata.getNameValue(key: String) = this.map.getNameValue(key)

fun Metadata.getFixedValue(key: String) = this.map.getFixedValue(key)

fun Map<Name, Value>.getStringValue(key: String) = this[key.asName()]?.cast<Value.String>()?.string

fun Map<Name, Value>.getBooleanValue(key: String) = this[key.asName()]?.cast<Value.Bool>()?.bool

fun Map<Name, Value>.getU32Value(key: String) = this[key.asName()]
    ?.cast<Value.Numeric>()?.numericValue
    ?.cast<NumericValue.U32>()?.u32

fun Map<Name, Value>.getU64Value(key: String) = this[key.asName()]
    ?.cast<Value.Numeric>()?.numericValue
    ?.cast<NumericValue.U64>()?.u64

fun Map<Name, Value>.getU128Value(key: String) = this[key.asName()]
    ?.cast<Value.Numeric>()?.numericValue
    ?.cast<NumericValue.U128>()?.u128

fun Map<Name, Value>.getFixedValue(key: String) = this[key.asName()]
    ?.cast<Value.Numeric>()?.numericValue
    ?.cast<NumericValue.Fixed>()?.fixed?.fixedPoint

fun Map<Name, Value>.getNameValue(key: String) = this[key.asName()]?.cast<Value.Name>()?.name

inline fun <reified T> NumericValue.getValue() = when (this) {
    is NumericValue.U32 -> this.u32.cast()
    is NumericValue.U64 -> this.u64.cast()
    is NumericValue.U128 -> this.u128.cast()
    is NumericValue.Fixed -> this.fixed.fixedPoint.cast<T>()
}

inline fun <reified T> Value.getValue() = when (this) {
    is Value.Numeric -> this.numericValue.cast()
    is Value.Bool -> this.bool.cast()
    is Value.String -> this.string.cast()
    is Value.Name -> this.name.string.cast<T>()
    else -> throw IllegalArgumentException("Value type is not supported")
}

inline fun <reified T> Map<Name, Value>.extract(key: String) = when (T::class) {
    Int::class -> this.getU32Value(key)?.toInt()
    BigInteger::class -> this.getU128Value(key)
    String::class -> this.getStringValue(key)
    Boolean::class -> this.getBooleanValue(key)
    BigDecimal::class -> this.getFixedValue(key)
    else -> throw RuntimeException("Unknown type ${T::class}")
} as T?

inline fun <reified T> Metadata.extract(key: String) = this.map.extract<T>(key)

fun Asset.metadata() = this.value.cast<AssetValue.Store>().metadata.map

fun TransactionBuilder.merge(other: TransactionBuilder) = this.instructions.value.addAll(other.instructions.value)
