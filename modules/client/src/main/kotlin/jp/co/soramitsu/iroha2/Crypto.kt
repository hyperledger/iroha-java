package jp.co.soramitsu.iroha2

import net.i2p.crypto.eddsa.EdDSAPrivateKey
import net.i2p.crypto.eddsa.EdDSAPublicKey
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveSpec
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec
import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec
import org.bouncycastle.util.encoders.Hex
import java.security.Key
import java.security.KeyPair
import java.security.SecureRandom

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
        EdDSAPublicKey(pubKey),
        EdDSAPrivateKey(privKey)
    )
}

fun keyPairFromHex(publicKeyHex: String, privateKeyHex: String, spec: EdDSAParameterSpec = DEFAULT_SPEC) =
    KeyPair(publicKeyFromHex(publicKeyHex, spec), privateKeyFromHex(privateKeyHex, spec))

fun privateKeyFromHex(privateKeyHex: String, spec: EdDSAParameterSpec = DEFAULT_SPEC) =
    EdDSAPrivateKey(EdDSAPrivateKeySpec(Hex.decodeStrict(privateKeyHex), spec))

fun publicKeyFromHex(publicKeyHex: String, spec: EdDSAParameterSpec = DEFAULT_SPEC) =
    EdDSAPublicKey(EdDSAPublicKeySpec(Hex.decodeStrict(publicKeyHex), spec))

/**
 * Returns encoded representation of the key that may be different from `java.security.Key.getEncoded()`
 *
 * Motivation: this method returns encoded bytes of the ED keys same as in Iroha1 java-lib and logical replacement of
 * `java.security.Key.getEncoded()` for such keys.
 * By default, keys in `net.i2p.crypto:eddsa are encoded in X.509 format
 *
 * @see java.security.Key.getEncoded
 * @see java.security.Key.getFormat
 * @return bytes encoding of the key. Can be empty if encoding is not supported
 */
fun Key.bytes(): ByteArray {
    return when (this) {
        is EdDSAPublicKey -> abyte
        is EdDSAPrivateKey -> seed
        else -> this.encoded ?: ByteArray(0)
    }
}
