package jp.co.soramitsu.iroha2.utils

import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.utils.DigestFunction.Ed25519
import net.i2p.crypto.eddsa.EdDSAEngine
import net.i2p.crypto.eddsa.EdDSAPrivateKey
import net.i2p.crypto.eddsa.EdDSAPublicKey
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveSpec
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec
import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec
import org.bouncycastle.jcajce.provider.digest.Blake2b
import org.bouncycastle.util.encoders.Hex
import java.security.KeyPair
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey as IrohaPublicKey

val DEFAULT_SPEC: EdDSANamedCurveSpec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519)

enum class DigestFunction(val hashFunName: String, val index: Int) {
    Ed25519("ed25519", 0xed)
}

// todo throw ex
fun generateKeyPair(spec: EdDSAParameterSpec = DEFAULT_SPEC): KeyPair {
    val seed = ByteArray(spec.curve.field.getb() / 8)
    SecureRandom().nextBytes(seed)

    val privKey = EdDSAPrivateKeySpec(seed, spec)
    val pubKey = EdDSAPublicKeySpec(privKey.a, spec)
    return KeyPair(
        PubKeyWrapper(pubKey),
        PrivateKeyWrapper(privKey)
    )
}

fun PublicKey.toIrohaPublicKey(): IrohaPublicKey {
    return IrohaPublicKey(Ed25519.hashFunName, this.encoded)
}

/**
 * Sign the message by given private key
 *
 * Note: the message must not be prehashed
 */
fun PrivateKey.sign(message: ByteArray): ByteArray {
    val sgr = EdDSAEngine(MessageDigest.getInstance(DEFAULT_SPEC.hashAlgorithm))
    sgr.initSign(this)
    sgr.update(message.hash())
    return sgr.sign()
}

/**
 * Verify the signature against the message and public key
 *
 * Note: the message must not be prehashed
 */
fun PublicKey.verify(signature: ByteArray, message: ByteArray): Boolean {
    val sgr = EdDSAEngine(MessageDigest.getInstance(DEFAULT_SPEC.hashAlgorithm))
    sgr.initVerify(this)
    sgr.update(message.hash())
    return sgr.verify(signature)
}

fun ByteArray.hash(): ByteArray = Blake2b.Blake2b256().digest(this)

fun keyPairFromHex(publicKeyHex: String, privateKeyHex: String, spec: EdDSAParameterSpec = DEFAULT_SPEC) =
    KeyPair(publicKeyFromHex(publicKeyHex, spec), privateKeyFromHex(privateKeyHex, spec))

fun privateKeyFromHex(privateKeyHex: String, spec: EdDSAParameterSpec = DEFAULT_SPEC) =
    EdDSAPrivateKey(EdDSAPrivateKeySpec(Hex.decodeStrict(privateKeyHex), spec))

fun publicKeyFromHex(publicKeyHex: String, spec: EdDSAParameterSpec = DEFAULT_SPEC) =
    EdDSAPublicKey(EdDSAPublicKeySpec(Hex.decodeStrict(publicKeyHex), spec))

// todo remove it
class PubKeyWrapper(pubKeySpec: EdDSAPublicKeySpec) : EdDSAPublicKey(pubKeySpec) {
    override fun getEncoded(): ByteArray = this.abyte
    override fun getFormat() = "RAW"
}

class PrivateKeyWrapper(privKeySpec: EdDSAPrivateKeySpec) : EdDSAPrivateKey(privKeySpec) {
    override fun getEncoded(): ByteArray = this.seed
    override fun getFormat() = "RAW"
}

fun VersionedTransaction.V1.hash(): ByteArray {
    val encoded = encode(Payload, this._VersionedTransactionV1.transaction.payload)
    return encoded.hash()
}

fun VersionedTransaction.hash() = when (this) {
    is VersionedTransaction.V1 -> this.hash()
}
