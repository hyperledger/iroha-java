package jp.co.soramitsu.iroha2

import io.ktor.websocket.Frame
import jp.co.soramitsu.iroha2.generated.* // ktlint-disable no-wildcard-imports
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder
import net.i2p.crypto.eddsa.EdDSAEngine
import org.bouncycastle.jcajce.provider.digest.Blake2b
import org.bouncycastle.util.encoders.Hex
import java.math.BigDecimal
import java.math.BigInteger
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
    AccountId(parts[1].asDomainId(), publicKeyFromHex(parts[0]).toIrohaPublicKey())
} ?: throw IllegalArgumentException("Incorrect account ID: $this")

fun String.asAssetDefinitionId() = this.split(ASSET_ID_DELIMITER).takeIf {
    it.size == 2
}?.let { parts ->
    AssetDefinitionId(parts[1].asDomainId(), parts[0].asName())
} ?: throw IllegalArgumentException("Incorrect asset definition ID: $this")

fun String.asAssetId() = this.split(ASSET_ID_DELIMITER).takeIf {
    it.size == 3
}?.let { parts ->
    parts[2].asAccountId().let { accountId ->
        val domainId = parts[1].takeIf { it.isNotBlank() }?.asDomainId()

        AssetId(
            AssetDefinitionId(
                domainId ?: accountId.domain,
                parts[0].asName(),
            ),
            accountId,
        )
    }
} ?: throw IllegalArgumentException("Incorrect asset ID: $this")

fun String.asDomainId() = DomainId(Name(this))

fun String.asRoleId() = RoleId(Name(this))

fun String.asName() = Name(this)

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

fun <T> ByteArray.asHashOf() = HashOf<T>(this.toIrohaHash())

fun ByteArray.hash(): ByteArray {
    val bytes = Blake2b.Blake2b256().digest(this)
    bytes[bytes.size - 1] = bytes[bytes.size - 1] or 1
    return bytes
}

/**
 * Hash the given versioned transaction. Maintains only `VersionedTransaction.V1`
 */
fun SignedTransaction.hash() = SignedTransaction.encode(this).hash()

/**
 * Cast to another type
 */
inline fun <reified B> Any.cast(): B {
    return this as? B
        ?: throw ClassCastException("Could not cast `${this::class.qualifiedName}` to `${B::class.qualifiedName}`")
}

fun AssetId.asString() = this.definition.asString() + ASSET_ID_DELIMITER + this.account.asString()

fun AssetId.asJsonString() = "{\"${AssetId::class.java.simpleName.toSnakeCase()}\": " +
    "\"${this.definition.asString() + ASSET_ID_DELIMITER + this.account.asString()}\"}"

fun AssetDefinitionId.asString() = this.name.string + ASSET_ID_DELIMITER + this.domain.name.string

fun AssetDefinitionId.asJsonString() = "{\"${AssetDefinitionId::class.java.simpleName.toSnakeCase()}\": " +
    "\"${this.name.string + ASSET_ID_DELIMITER + this.domain.name.string}\"}"

fun AccountId.asString() = this.signatory.toString() + ACCOUNT_ID_DELIMITER + this.domain.name.string

fun AccountId.asJsonString() = "{\"${AccountId::class.java.simpleName.toSnakeCase()}\": " +
    "\"${this.signatory.toString() + ACCOUNT_ID_DELIMITER + this.domain.name.string}\"}"

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

fun TriggerId.asString() = this.name.string

fun Metadata.merge(extra: Metadata) = Metadata(
    this.sortedMapOfName.toMutableMap().also { it.putAll(extra.sortedMapOfName) },
)

fun InstructionBox.Register.extractIdentifiableBox() = runCatching {
    when (val discriminant = this.registerBox.discriminant()) {
        0 -> this.registerBox.cast<Peer>() as IdentifiableBox
        1 -> this.registerBox.cast<Domain>() as IdentifiableBox
        2 -> this.registerBox.cast<Account>() as IdentifiableBox
        3 -> this.registerBox.cast<AssetDefinition>() as IdentifiableBox
        4 -> this.registerBox.cast<Asset>() as IdentifiableBox
        5 -> this.registerBox.cast<Role>() as IdentifiableBox
        6 -> this.registerBox.cast<Trigger>() as IdentifiableBox
        else -> null
    }
}.getOrNull()

fun Iterable<InstructionBox>.extractIdentifiableBoxes() = this.asSequence()
    .filterIsInstance<InstructionBox.Register>()
    .map { it.extractIdentifiableBox() }.filter { it != null }.map { it!! }.toList()

fun IdBox.extractId(): Any = when (this) {
    is IdBox.RoleId -> this.roleId
    is IdBox.AccountId -> this.accountId
    is IdBox.AssetId -> this.assetId
    is IdBox.AssetDefinitionId -> this.assetDefinitionId
    is IdBox.DomainId -> this.domainId
    is IdBox.TriggerId -> this.triggerId
    is IdBox.PeerId -> this.peerId
    is IdBox.PermissionId -> this.permissionId
    is IdBox.ParameterId -> this.parameterId
}

fun InstructionBox.extractAccount() = this
    .cast<InstructionBox.Register>()
    .registerBox
    .cast<RegisterBox.Account>()
    .registerOfAccount.`object`

fun InstructionBox.Register.extractAccount() = this
    .registerBox
    .cast<RegisterBox.Account>()
    .registerOfAccount.`object`

fun InstructionBox.Register.extractDomain() = this
    .cast<InstructionBox.Register>()
    .registerBox
    .cast<RegisterBox.Domain>()
    .registerOfDomain.`object`

fun InstructionBox.Register.extractAssetDefinition() = this
    .cast<InstructionBox.Register>()
    .registerBox
    .cast<RegisterBox.AssetDefinition>()
    .registerOfAssetDefinition.`object`

inline fun <reified I : InstructionBox> SignedTransaction.extractInstruction(): I = this
    .cast<SignedTransaction.V1>()
    .extractInstruction<I>()

inline fun <reified I : InstructionBox> SignedTransaction.V1.extractInstruction() = this
    .extractInstructionVec<I>()
    .first().cast<I>()

inline fun <reified I : InstructionBox> SignedTransaction.V1.extractInstructions() = this
    .extractInstructionVec<I>()
    .cast<List<I>>()

inline fun <reified I : InstructionBox> SignedTransaction.V1.extractInstructionVec() = this
    .signedTransactionV1.payload.instructions
    .cast<Executable.Instructions>()
    .vec.filterIsInstance<I>()

fun InstructionBox.SetKeyValue.extractDomainId() = this
    .cast<InstructionBox.SetKeyValue>()
    .setKeyValueBox
    .cast<SetKeyValueBox.Domain>()
    .setKeyValueOfDomain
    .`object`

fun InstructionBox.Grant.extractValuePermissionToken() = this
    .cast<InstructionBox.Grant>()
    .grantBox
    .cast<GrantBox.Permission>()
    .grantOfPermissionAndAccount
    .`object`

fun TriggeringEventFilterBox.extractSchedule() = this
    .cast<TriggeringEventFilterBox.Time>()
    .timeEventFilter
    .executionTime
    .cast<ExecutionTime.Schedule>()
    .schedule

fun EventFilterBox.extractSchedule() = this
    .cast<EventFilterBox.Time>()
    .timeEventFilter.executionTime
    .cast<ExecutionTime.Schedule>().schedule

fun BlockMessage.extractBlock() = this.signedBlock.cast<SignedBlock.V1>().signedBlockV1.payload

fun BlockPayload.height() = this.header.height

fun Asset.metadata() = this.value.cast<AssetValue.Store>().metadata.sortedMapOfName

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
    is FindError.Permission -> this.permissionId.name.string
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

fun Number.asNumeric() = when (this) {
    is Int -> this.asNumeric()
    is Long -> this.asNumeric()
    is BigInteger -> this.asNumeric()
    is Double -> this.asNumeric()
    is BigDecimal -> this.asNumeric()
    else -> throw IrohaSdkException("Unexpected type to extract numeric ${this::class}")
}

fun Int.asNumeric() = Numeric(mantissa = this.toBigInteger(), scale = 0)

fun Long.asNumeric() = Numeric(mantissa = this.toBigInteger(), scale = 0)

fun BigInteger.asNumeric() = Numeric(mantissa = this, scale = 0)

fun Double.asNumeric() = this.toBigDecimal().asNumeric()

fun BigDecimal.asNumeric() = Numeric(mantissa = this.unscaledValue(), scale = this.scale().toLong())

fun Numeric.asInt() = this.mantissa.toInt()

fun Numeric.asLong() = this.mantissa.toLong()

fun Numeric.asBigInteger() = this.mantissa

fun Numeric.asBigDecimal() = BigDecimal.valueOf(this.mantissa.toLong(), this.scale.toInt())

fun AssetValueType.Companion.numeric(scale: Long? = null) = AssetValueType.Numeric(NumericSpec(scale))

fun Boolean.asMetadataValueBox() = MetadataValueBox.Bool(this)

fun String.asMetadataValueBox() = MetadataValueBox.String(this)

fun Number.asMetadataValueBox() = MetadataValueBox.Numeric(this.asNumeric())

fun Double.asMetadataValueBox() = MetadataValueBox.Numeric(this.asNumeric())

fun Metadata.getStringValue(key: String) = this.sortedMapOfName.getStringValue(key)

fun Metadata.getBooleanValue(key: String) = this.sortedMapOfName.getBooleanValue(key)

fun Metadata.getNameValue(key: String) = this.sortedMapOfName.getNameValue(key)

fun Metadata.getFixedValue(key: String) = this.sortedMapOfName.getFixedValue(key)

fun Map<Name, MetadataValueBox>.getStringValue(key: String) = this[key.asName()]?.cast<MetadataValueBox.String>()?.string

fun Map<Name, MetadataValueBox>.getBooleanValue(key: String) = this[key.asName()]?.cast<MetadataValueBox.Bool>()?.bool

fun Map<Name, MetadataValueBox>.getU32Value(key: String) = this[key.asName()]
    ?.cast<MetadataValueBox.Numeric>()?.numeric?.asLong()

fun Map<Name, MetadataValueBox>.getU64Value(key: String) = this[key.asName()]
    ?.cast<MetadataValueBox.Numeric>()?.numeric?.asBigInteger()

fun Map<Name, MetadataValueBox>.getU128Value(key: String) = this[key.asName()]
    ?.cast<MetadataValueBox.Numeric>()?.numeric?.asBigInteger()

fun Map<Name, MetadataValueBox>.getFixedValue(key: String) = this[key.asName()]
    ?.cast<MetadataValueBox.Numeric>()?.numeric?.asBigDecimal()

fun Map<Name, MetadataValueBox>.getNameValue(key: String) = this[key.asName()]?.cast<MetadataValueBox.Name>()?.name

inline fun <reified T> MetadataValueBox.getValue() = when (this) {
    is MetadataValueBox.Numeric -> this.numeric.cast()
    is MetadataValueBox.Bool -> this.bool.cast()
    is MetadataValueBox.String -> this.string.cast()
    is MetadataValueBox.Name -> this.name.string.cast<T>()
    else -> throw IllegalArgumentException("Value type is not supported")
}

inline fun <reified T> Map<Name, MetadataValueBox>.extract(key: String) = when (T::class) {
    Int::class -> this.getU32Value(key)?.toInt()
    BigInteger::class -> this.getU128Value(key)
    String::class -> this.getStringValue(key)
    Boolean::class -> this.getBooleanValue(key)
    BigDecimal::class -> this.getFixedValue(key)
    else -> throw RuntimeException("Unknown type ${T::class}")
} as T?

inline fun <reified T> Metadata.extract(key: String) = this.sortedMapOfName.extract<T>(key)
