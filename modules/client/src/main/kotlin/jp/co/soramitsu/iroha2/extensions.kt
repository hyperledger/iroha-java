package jp.co.soramitsu.iroha2

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.crypto.Signature
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import net.i2p.crypto.eddsa.EdDSAEngine
import org.bouncycastle.jcajce.provider.digest.Blake2b
import org.bouncycastle.util.encoders.Hex
import java.io.ByteArrayOutputStream
import java.security.KeyPair
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.PublicKey

fun String.asValue() = Value.String(this)

fun Int.asValue() = this.toUInt().asValue()

fun UInt.asValue() = Value.U32(this)

fun Boolean.asValue() = Value.Bool(this)

// todo get rid of providing `writer`
fun <T> T.encode(writer: ScaleWriter<T>): ByteArray {
    // resource is freed inside `ScaleCodecWriter`
    val buffer = ByteArrayOutputStream()
    return ScaleCodecWriter(buffer)
        .use {
            writer.write(it, this)
            buffer.toByteArray()
        }
}

// todo get rid of providing `reader`
fun <T> ByteArray.decode(reader: ScaleReader<T>): T = ScaleCodecReader(this).read(reader)

fun ByteArray.hex(): String = try {
    Hex.toHexString(this)
} catch (ex: Exception) {
    throw HexCodecException("Cannot encode to hex string", ex)
}

fun String.hex(): ByteArray = try {
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
    return this._VersionedTransactionV1
        .transaction
        .payload
        .encode(Payload)
        .hash()
}

fun VersionedTransaction.hash() = when (this) {
    is VersionedTransaction.V1 -> this.hash()
}

fun VersionedTransaction.appendSignatures(vararg keypairs: KeyPair): VersionedTransaction {
    when (this) {
        is VersionedTransaction.V1 -> {
            val encodedPayload = _VersionedTransactionV1.transaction.payload.encode(Payload)
            val signatures = keypairs.map {
                Signature(
                    it.public.toIrohaPublicKey(),
                    it.private.sign(encodedPayload)
                )
            }.toSet()
            _VersionedTransactionV1.transaction.signatures.addAll(signatures)
        }
    }
    return this
}

inline fun <reified B> Any.cast(): B {
    return this as? B
        ?: throw ClassCastException("Could not cast `${this::class.qualifiedName}` to `${B::class.qualifiedName}`")
}
