package jp.co.soramitsu.iroha2

import io.ktor.websocket.Frame
import jp.co.soramitsu.iroha2.generated.Account
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AccountMintBox
import jp.co.soramitsu.iroha2.generated.Algorithm
import jp.co.soramitsu.iroha2.generated.Asset
import jp.co.soramitsu.iroha2.generated.AssetDefinition
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.BlockMessage
import jp.co.soramitsu.iroha2.generated.BlockPayload
import jp.co.soramitsu.iroha2.generated.BlockSubscriptionRequest
import jp.co.soramitsu.iroha2.generated.Domain
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.EventEventFilterBox
import jp.co.soramitsu.iroha2.generated.Executable
import jp.co.soramitsu.iroha2.generated.ExecutionTime
import jp.co.soramitsu.iroha2.generated.FindError
import jp.co.soramitsu.iroha2.generated.GrantBox
import jp.co.soramitsu.iroha2.generated.Hash
import jp.co.soramitsu.iroha2.generated.IdBox
import jp.co.soramitsu.iroha2.generated.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.JsonString
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.MintBox
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.NonZeroOfu64
import jp.co.soramitsu.iroha2.generated.Numeric
import jp.co.soramitsu.iroha2.generated.Peer
import jp.co.soramitsu.iroha2.generated.RegisterBox
import jp.co.soramitsu.iroha2.generated.Role
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.SetKeyValueBox
import jp.co.soramitsu.iroha2.generated.Signature
import jp.co.soramitsu.iroha2.generated.SignatureOf
import jp.co.soramitsu.iroha2.generated.SignaturesOfOfTransactionPayload
import jp.co.soramitsu.iroha2.generated.SignedTransaction
import jp.co.soramitsu.iroha2.generated.SignedTransactionV1
import jp.co.soramitsu.iroha2.generated.SocketAddr
import jp.co.soramitsu.iroha2.generated.SocketAddrHost
import jp.co.soramitsu.iroha2.generated.TransactionPayload
import jp.co.soramitsu.iroha2.generated.Trigger
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox
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
    AssetDefinitionId(parts[1].asDomainId(), parts[0].asName())
} ?: throw IllegalArgumentException("Incorrect asset definition ID: $this")

fun String.asAssetId() = this.split(ASSET_ID_DELIMITER).takeIf {
    it.size == 3
}?.let { parts ->
    parts[2].asAccountId().let { accountId ->
        val domainId = parts[0].takeIf { it.isNotBlank() }?.asDomainId()
        AssetId(
            AssetDefinitionId(
                domainId ?: accountId.domainId,
                parts[1].asName(),
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
    is IdBox.PermissionTokenId -> this.name
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

fun InstructionBox.Mint.extractPublicKey() = this
    .cast<InstructionBox.Mint>()
    .mintBox
    .cast<MintBox.Account>()
    .accountMintBox
    .cast<AccountMintBox.PublicKey>()
    .mintOfPublicKeyAndAccount
    .`object`
    .payload.toHex()

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
    .objectId

fun InstructionBox.Grant.extractValuePermissionToken() = this
    .cast<InstructionBox.Grant>()
    .grantBox
    .cast<GrantBox.PermissionToken>()
    .grantOfPermissionTokenAndAccount
    .`object`

fun TriggeringEventEventFilterBox.extractSchedule() = this
    .cast<TriggeringEventEventFilterBox.Time>()
    .timeEventFilter
    .executionTime
    .cast<ExecutionTime.Schedule>()
    .schedule

fun EventEventFilterBox.extractSchedule() = this
    .cast<EventEventFilterBox.Time>()
    .timeEventFilter.executionTime
    .cast<ExecutionTime.Schedule>().schedule

fun BlockMessage.extractBlock() = this.signedBlock

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

fun String.asJsonString() = JsonString(this)

fun AssetId.asStringWithJson() = this.asJsonString().asJsonString()

fun AccountId.asStringWithJson() = this.asJsonString().asJsonString()

fun RoleId.asStringWithJson() = this.asJsonString().asJsonString()

fun Int.asNumeric() = Numeric(mantissa = this.toBigInteger(), scale = 0)

fun Long.asNumeric() = Numeric(mantissa = this.toBigInteger(), scale = 0)

fun BigInteger.asNumeric() = Numeric(mantissa = this, scale = 0)

fun BigDecimal.asNumeric() = Numeric(mantissa = this.unscaledValue(), scale = this.scale().toLong())
