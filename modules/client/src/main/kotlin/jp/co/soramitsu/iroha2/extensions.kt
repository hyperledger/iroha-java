package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.generated.schema.irohacrypto.signature.SignatureOf
import net.i2p.crypto.eddsa.EdDSAEngine
import org.bouncycastle.jcajce.provider.digest.Blake2b
import org.bouncycastle.util.encoders.Hex
import java.security.KeyPair
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id as DomainId

fun String.asDomainId() = DomainId(Name(this))

fun String.asName() = Name(this)

fun String.asValue() = Value.String(this)

fun Int.asValue() = this.toLong().asValue()

fun Long.asValue() = Value.U32(this)

fun Boolean.asValue() = Value.Bool(this)

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

fun PublicKey.toIrohaPublicKey(): jp.co.soramitsu.iroha2.generated.crypto.PublicKey {
    return jp.co.soramitsu.iroha2.generated.crypto.PublicKey(DigestFunction.Ed25519.hashFunName, this.bytes())
}

/**
 * Sign the message by given private key
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
 * Verify the signature against the message and public key
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

fun ByteArray.hash(): ByteArray = Blake2b.Blake2b256().digest(this)

fun VersionedTransaction.V1.hash(): ByteArray {
    return this.transaction
        .payload
        .let { Payload.encode(it) }
        .hash()
}

fun VersionedTransaction.hash() = when (this) {
    is VersionedTransaction.V1 -> this.hash()
}

/**
 * Append signatures to transaction. Maintains only VersionedTransaction.V1
 */
fun VersionedTransaction.appendSignatures(vararg keypairs: KeyPair): VersionedTransaction {
    return when (this) {
        is VersionedTransaction.V1 -> {
            val encodedPayload = transaction
                .payload
                .let { Payload.encode(it) }
            val signatures = keypairs.map {
                SignatureOf<Payload>(
                    it.public.toIrohaPublicKey(),
                    it.private.sign(encodedPayload)
                )
            }.toSet()

            VersionedTransaction.V1(
                Transaction(
                    transaction.payload,
                    transaction.signatures.plus(signatures)
                )
            )
        }
    }
}

inline fun <reified B> Any.cast(): B {
    return this as? B
        ?: throw ClassCastException("Could not cast `${this::class.qualifiedName}` to `${B::class.qualifiedName}`")
}

/**
 * Wrap object in EvaluatesTo
 */
inline fun <reified T> T.evaluatesTo(): EvaluatesTo<T> {
    return when (this) {
        is String -> Value.String(this)
        is Boolean -> Value.Bool(this)
        is AssetId -> Value.Id(IdBox.AssetId(this))
        is DefinitionId -> Value.Id(IdBox.AssetDefinitionId(this))
        is AccountId -> Value.Id(IdBox.AccountId(this))
        is DomainId -> Value.Id(IdBox.DomainId(this))
        is IdBox -> Value.Id(this)
        is Hash -> Value.Hash(this)
        is Name -> Value.Name(this)
        is Value -> this
        else -> throw IllegalArgumentException("Unsupported value type `${T::class.qualifiedName}`")
    }.let { value ->
        EvaluatesTo(Expression.Raw(value))
    }
}
