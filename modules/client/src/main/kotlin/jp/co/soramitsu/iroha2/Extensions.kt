package jp.co.soramitsu.iroha2

import io.ktor.websocket.Frame
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.Algorithm
import jp.co.soramitsu.iroha2.generated.Asset
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.BlockMessage
import jp.co.soramitsu.iroha2.generated.BlockPayload
import jp.co.soramitsu.iroha2.generated.BlockSubscriptionRequest
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.Executable
import jp.co.soramitsu.iroha2.generated.ExecutionTime
import jp.co.soramitsu.iroha2.generated.Expression
import jp.co.soramitsu.iroha2.generated.FilterBox
import jp.co.soramitsu.iroha2.generated.FilterOptOfAccountId
import jp.co.soramitsu.iroha2.generated.FindError
import jp.co.soramitsu.iroha2.generated.Fixed
import jp.co.soramitsu.iroha2.generated.Hash
import jp.co.soramitsu.iroha2.generated.HashOf
import jp.co.soramitsu.iroha2.generated.HashValue
import jp.co.soramitsu.iroha2.generated.IdBox
import jp.co.soramitsu.iroha2.generated.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.InstructionExpr
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.NonZeroOfu64
import jp.co.soramitsu.iroha2.generated.NumericValue
import jp.co.soramitsu.iroha2.generated.Parameter
import jp.co.soramitsu.iroha2.generated.ParameterId
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.RegistrableBox
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.Signature
import jp.co.soramitsu.iroha2.generated.SignatureCheckCondition
import jp.co.soramitsu.iroha2.generated.SignatureOf
import jp.co.soramitsu.iroha2.generated.SignaturesOfOfTransactionPayload
import jp.co.soramitsu.iroha2.generated.SignedBlock
import jp.co.soramitsu.iroha2.generated.SignedBlockV1
import jp.co.soramitsu.iroha2.generated.SignedTransaction
import jp.co.soramitsu.iroha2.generated.SignedTransactionV1
import jp.co.soramitsu.iroha2.generated.SocketAddr
import jp.co.soramitsu.iroha2.generated.SocketAddrHost
import jp.co.soramitsu.iroha2.generated.StringWithJson
import jp.co.soramitsu.iroha2.generated.TransactionPayload
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.TriggerOfTriggeringFilterBox
import jp.co.soramitsu.iroha2.generated.TriggeringFilterBox
import jp.co.soramitsu.iroha2.generated.Value
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
import java.util.Locale
import java.util.StringTokenizer
import kotlin.experimental.or
import jp.co.soramitsu.iroha2.generated.PublicKey as IrohaPublicKey

fun BlockSubscriptionRequest.Companion.of(from: Long) = BlockSubscriptionRequest(NonZeroOfu64(BigInteger.valueOf(from)))

fun <T> Signature.asSignatureOf() = SignatureOf<T>(this)

fun String.asAccountId() = this.split(ACCOUNT_ID_DELIMITER).takeIf {
    it.size == 2
}?.let { parts ->
    AccountId(parts[1].asDomainId(), parts[0].asName())
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
                domainId ?: accountId.domainId,
            ),
            accountId,
        )
    }
} ?: throw IllegalArgumentException("Incorrect asset ID: $this")

fun String.asDomainId() = DomainId(Name(this))

fun String.asRoleId() = RoleId(Name(this))

fun String.asParameter() = this.split(PARAMETER_DELIMITER).takeIf {
    it.size == 2
}?.let { parts ->
    Parameter(
        ParameterId(Name(parts[0])),
        parts[1].asValue(),
    )
}

fun String.asName() = Name(this)

fun String.asValue() = Value.String(this)

fun Int.asValue() = Value.Numeric(NumericValue.U32(this.toLong()))

fun Double.asValue() = Value.Numeric(NumericValue.Fixed(Fixed(this.toBigDecimal())))

fun BigDecimal.asValue() = Value.Numeric(NumericValue.Fixed(Fixed(this)))

fun Long.asValue() = Value.Numeric(NumericValue.U64(BigInteger.valueOf(this)))

fun BigInteger.asValue() = Value.Numeric(NumericValue.U128(this))

fun Boolean.asValue() = Value.Bool(this)

fun AccountId.asValue() = Value.Id(IdBox.AccountId(this))

fun AssetId.asValue() = Value.Id(IdBox.AssetId(this))

fun PermissionToken.asValue() = Value.PermissionToken(this)

fun AssetDefinitionId.asValue() = Value.Id(IdBox.AssetDefinitionId(this))

fun DomainId.asValue() = Value.Id(IdBox.DomainId(this))

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
fun PublicKey.toIrohaPublicKey(): IrohaPublicKey {
    return IrohaPublicKey(Algorithm.Ed25519(), this.bytes())
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
fun SignedTransaction.V1.hash(): ByteArray {
    return this.signedTransactionV1
        .payload
        .let { TransactionPayload.encode(it) }
        .hash()
}

/**
 * Hash the given versioned transaction. Maintains only `VersionedTransaction.V1`
 */
fun SignedTransaction.hash() = when (this) {
    is SignedTransaction.V1 -> this.hash()
}

/**
 * Append signatures to a transaction. Maintains only `VersionedTransaction.V1`
 */
fun SignedTransaction.appendSignatures(vararg keypairs: KeyPair): SignedTransaction {
    return when (this) {
        is SignedTransaction.V1 -> {
            val encodedPayload = signedTransactionV1
                .payload
                .let { TransactionPayload.encode(it) }
            val signatures = keypairs.map {
                Signature(
                    it.public.toIrohaPublicKey(),
                    it.private.sign(encodedPayload),
                ).asSignatureOf<TransactionPayload>()
            }.toSet()

            SignedTransaction.V1(
                SignedTransactionV1(
                    signedTransactionV1.signatures.plus(signatures),
                    signedTransactionV1.payload,
                ),
            )
        }
    }
}

fun SignaturesOfOfTransactionPayload.plus(
    signatures: Set<SignatureOf<TransactionPayload>>,
) = SignaturesOfOfTransactionPayload(this.signatures.plus(signatures))

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
        is HashValue -> Value.Hash(this)
        is HashOf<*> -> Value.Hash(HashValue.Transaction(HashOf(this.hash)))
        is Name -> Value.Name(this)
        is PermissionToken -> Value.PermissionToken(this)
        is IdentifiableBox -> Value.Identifiable(this)
        is RegistrableBox -> Value.Identifiable(this.toIdentifiableBox())
        is Parameter -> Value.Identifiable(IdentifiableBox.Parameter(this))
        is SignatureCheckCondition -> Value.SignatureCheckCondition(this)
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
    is RegistrableBox.Trigger -> IdentifiableBox.Trigger(this.triggerOfTriggeringFilterBox)
}

inline fun <reified T> T.asValue() = when (this) {
    is String -> this.asValue()
    is Long -> this.asValue()
    is Int -> this.asValue()
    is Double -> this.asValue()
    is BigInteger -> this.asValue()
    is BigDecimal -> this.asValue()
    is Boolean -> this.asValue()
    is AccountId -> this.asValue()
    is AssetDefinitionId -> this.asValue()
    is AssetId -> this.asValue()
    is PermissionToken -> this.asValue()
    else -> throw RuntimeException("Unsupported type ${T::class}")
}

fun AssetId.asString() = this.definitionId.asString() + ASSET_ID_DELIMITER + this.accountId.asString()

fun AssetId.asJsonString() = "{\"${AssetId::class.java.simpleName.toSnakeCase()}\": " +
    "\"${this.definitionId.asString() + ASSET_ID_DELIMITER + this.accountId.asString()}\"}"

fun AssetDefinitionId.asString() = this.name.string + ASSET_ID_DELIMITER + this.domainId.name.string

fun AssetDefinitionId.asJsonString() = "{\"${AssetDefinitionId::class.java.simpleName.toSnakeCase()}\": " +
    "\"${this.name.string + ASSET_ID_DELIMITER + this.domainId.name.string}\"}"

fun AccountId.asString() = this.name.string + ACCOUNT_ID_DELIMITER + this.domainId.name.string

fun AccountId.asJsonString() = "{\"${AccountId::class.java.simpleName.toSnakeCase()}\": " +
    "\"${this.name.string + ACCOUNT_ID_DELIMITER + this.domainId.name.string}\"}"

fun DomainId.asString() = this.name.string
fun DomainId.asJsonString() = "{\"${DomainId::class.java.simpleName.toSnakeCase()}\": " +
    "\"${this.name.string}\"}"

fun RoleId.asString() = this.name.string

fun RoleId.asJsonString() = "{\"${RoleId::class.java.simpleName.toSnakeCase()}\": \"${this.name.string}\"}"

fun SocketAddr.asString() = when (this) {
    is SocketAddr.Host -> this.socketAddrHost.let { "${it.host}:${it.port}" }
    is SocketAddr.Ipv4 -> this.socketAddrV4.let { "${it.ip}:${it.port}" }
    is SocketAddr.Ipv6 -> this.socketAddrV6.let { "${it.ip}:${it.port}" }
}

fun TriggerId.asString() = when (this.domainId) {
    null -> this.name.string
    else -> this.name.string + TRIGGER_ID_DELIMITER + this.domainId!!.name.string
}

fun Parameter.asString() = this.id.name.string + PARAMETER_DELIMITER + this.`val`.cast<Value.String>().string

fun Metadata.merge(extra: Metadata) = Metadata(
    this.map.toMutableMap().also { it.putAll(extra.map) },
)

fun InstructionExpr.Register.extractIdentifiableBox() = runCatching {
    this.registerExpr.`object`.expression
        .cast<Expression.Raw>().value
        .cast<Value.Identifiable>().identifiableBox
}.getOrNull()

fun Iterable<InstructionExpr>.extractIdentifiableBoxes() = this.asSequence()
    .filterIsInstance<InstructionExpr.Register>()
    .map { it.registerExpr.`object`.expression }
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
    is IdBox.PermissionTokenId -> this.name
    is IdBox.ParameterId -> this.parameterId
}

fun InstructionExpr.extractAccount() = this
    .cast<InstructionExpr.Register>()
    .registerExpr.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.Identifiable>().identifiableBox
    .cast<IdentifiableBox.NewAccount>().newAccount

fun InstructionExpr.Register.extractAccount() = this
    .registerExpr.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.Identifiable>().identifiableBox
    .cast<IdentifiableBox.NewAccount>().newAccount

fun InstructionExpr.Register.extractDomain() = this
    .registerExpr.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.Identifiable>().identifiableBox
    .cast<IdentifiableBox.NewDomain>().newDomain

fun InstructionExpr.Register.extractAssetDefinition() = this
    .registerExpr.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.Identifiable>().identifiableBox
    .cast<IdentifiableBox.NewAssetDefinition>().newAssetDefinition

fun InstructionExpr.SetKeyValue.extractKey() = this
    .setKeyValueExpr.key.expression
    .cast<Expression.Raw>().value
    .cast<Value.Name>().name
    .string

fun InstructionExpr.SetKeyValue.extractAccountId() = this.setKeyValueExpr.objectId.extractAccountId()

fun InstructionExpr.Unregister.extractAccountId() = this.unregisterExpr.objectId.extractAccountId()

fun InstructionExpr.Unregister.extractDomainId() = this.unregisterExpr.objectId.extractDomainId()

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

fun InstructionExpr.Mint.extractPublicKey() = this
    .mintExpr.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.PublicKey>().publicKey
    .payload.toHex()

fun <T> EvaluatesTo<T>.extractNewAssetDefinition() = this
    .expression
    .cast<Expression.Raw>().value
    .cast<Value.Identifiable>().identifiableBox
    .cast<IdentifiableBox.NewAssetDefinition>().newAssetDefinition

inline fun <reified I : InstructionExpr> SignedTransaction.extractInstruction() = this
    .cast<SignedTransaction.V1>()
    .extractInstruction<I>()

inline fun <reified I : InstructionExpr> SignedTransaction.V1.extractInstruction() = this
    .extractInstructionVec<I>()
    .first().cast<I>()

inline fun <reified I : InstructionExpr> SignedTransaction.V1.extractInstructions() = this
    .extractInstructionVec<I>()
    .cast<List<I>>()

inline fun <reified I : InstructionExpr> SignedTransaction.V1.extractInstructionVec() = this
    .signedTransactionV1.payload.instructions
    .cast<Executable.Instructions>()
    .vec.filterIsInstance<I>()

fun InstructionExpr.Register.extractNewDomainMetadata() = this
    .registerExpr.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.Identifiable>().identifiableBox
    .cast<IdentifiableBox.NewDomain>().newDomain.metadata

fun InstructionExpr.SetKeyValue.extractDomainId() = this
    .setKeyValueExpr.objectId.expression
    .cast<Expression.Raw>().value
    .cast<Value.Id>().idBox
    .cast<IdBox.DomainId>().domainId

fun InstructionExpr.SetKeyValue.key() = this
    .setKeyValueExpr.key.expression
    .cast<Expression.Raw>().value
    .cast<Value.Name>().name.string

fun InstructionExpr.SetKeyValue.extractValueString() = this
    .setKeyValueExpr.value.expression
    .cast<Expression.Raw>().value
    .cast<Value.String>().string

fun InstructionExpr.SetKeyValue.extractValueU32() = this.setKeyValueExpr.value.extractValueU32()

fun InstructionExpr.SetKeyValue.extractValueU128() = this
    .setKeyValueExpr.value.expression
    .cast<Expression.Raw>().value
    .getValue<Value.Numeric>().numericValue
    .getValue<BigInteger>()

fun InstructionExpr.SetKeyValue.extractValueBoolean() = this
    .setKeyValueExpr.value.expression
    .cast<Expression.Raw>().value
    .cast<Value.Bool>().bool

fun InstructionExpr.Grant.extractValuePermissionToken() = this
    .grantExpr.`object`.expression
    .cast<Expression.Raw>().value
    .cast<Value.PermissionToken>().permissionToken

fun InstructionExpr.Burn.extractValueU32() = this.burnExpr.`object`.extractValueU32()

fun EvaluatesTo<Value>.extractValueU32() = this
    .expression
    .cast<Expression.Raw>().value
    .getValue<Value.Numeric>().numericValue
    .getValue<Long>()

fun TriggerOfTriggeringFilterBox.extractSchedule() = this.action.filter.extractSchedule()

fun TriggeringFilterBox.extractSchedule() = this
    .cast<TriggeringFilterBox.Time>()
    .timeEventFilter.executionTime
    .cast<ExecutionTime.Schedule>().schedule

fun FilterBox.extractSchedule() = this
    .cast<FilterBox.Time>()
    .timeEventFilter.executionTime
    .cast<ExecutionTime.Schedule>().schedule

fun BlockMessage.extractBlock() = this
    .cast<BlockMessage>().signedBlock.cast<SignedBlock.V1>().signedBlockV1
    .extractBlock()

fun SignedBlockV1.extractBlock() = this.cast<SignedBlockV1>().payload

fun BlockPayload.height() = this.header.height

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
    ?.cast<NumericValue.Fixed>()?.fixed?.fixedPointOfI64

fun Map<Name, Value>.getNameValue(key: String) = this[key.asName()]?.cast<Value.Name>()?.name

inline fun <reified T> NumericValue.getValue() = when (this) {
    is NumericValue.U32 -> this.u32.cast()
    is NumericValue.U64 -> this.u64.cast()
    is NumericValue.U128 -> this.u128.cast()
    is NumericValue.Fixed -> this.fixed.fixedPointOfI64.cast<T>()
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

fun String.toSocketAddr() = this.split(":").let { parts ->
    if (parts.size != 2) throw IrohaSdkException("Incorrect address")

    SocketAddr.Host(SocketAddrHost(parts.first(), parts.last().toInt()))
}

fun String.replace(oldValue: String) = this.replace(oldValue, "")

fun String.replace(regex: Regex) = this.replace(regex, "")

fun FindError.extract() = when (this) {
    is FindError.Account -> this.accountId.asString()
    is FindError.Asset -> this.assetId.asString()
    is FindError.AssetDefinition -> this.assetDefinitionId.asString()
    is FindError.Domain -> this.domainId.asString()
    is FindError.Role -> this.roleId.asString()
    is FindError.Block -> this.hashOf.hash.arrayOfU8.toHex()
    is FindError.MetadataKey -> this.name.string
    is FindError.Parameter -> this.parameterId.name.string
    is FindError.Peer -> this.peerId.address.toString()
    is FindError.PermissionToken -> this.name.string
    is FindError.PublicKey -> this.publicKey.payload.toString()
    is FindError.Trigger -> this.triggerId.asString()
    is FindError.Transaction -> this.hashOf.hash.arrayOfU8.toHex()
}

fun String.toCamelCase(name: String): String {
    val tokenizer = StringTokenizer(name, "_")
    return if (tokenizer.hasMoreTokens()) {
        val resultBuilder = StringBuilder(tokenizer.nextToken())
        for (token in tokenizer) {
            resultBuilder.append((token as String).replaceFirstChar(Char::uppercase))
        }
        resultBuilder.toString()
    } else {
        name
    }
}

fun String.toCamelCase() = this.lowercase(Locale.getDefault())
    .split(" ", "_")
    .withIndex()
    .joinToString("") { value ->
        when (value.index) {
            0 -> value.value
            else -> value.value.replaceFirstChar {
                when (it.isLowerCase()) {
                    true -> it.titlecase(Locale.getDefault())
                    else -> it.toString()
                }
            }
        }
    }

fun String.toSnakeCase() = this
    .split("(?<=.)(?=\\p{Lu})|\\s".toRegex())
    .joinToString("_")
    .lowercase(Locale.getDefault())

fun String.asStringWithJson() = StringWithJson(this)

fun AssetId.asStringWithJson() = this.asJsonString().asStringWithJson()

fun AccountId.asStringWithJson() = this.asJsonString().asStringWithJson()

fun RoleId.asStringWithJson() = this.asJsonString().asStringWithJson()

fun FilterOptOfAccountId.asString() = when (this) {
    is FilterOptOfAccountId.BySome -> this.accountId.asString()
    is FilterOptOfAccountId.AcceptAll -> this::class.simpleName
}

fun FilterOptOfAccountId.asAccountIdOrNull() = when (this) {
    is FilterOptOfAccountId.BySome -> this.accountId
    is FilterOptOfAccountId.AcceptAll -> null
}
