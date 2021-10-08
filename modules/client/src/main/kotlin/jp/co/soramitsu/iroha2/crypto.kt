package jp.co.soramitsu.iroha2

import net.i2p.crypto.eddsa.EdDSAPrivateKey
import net.i2p.crypto.eddsa.EdDSAPublicKey
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveSpec
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec
import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec
import java.security.Key
import java.security.KeyPair
import java.security.SecureRandom

val DEFAULT_SPEC: EdDSANamedCurveSpec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519)

enum class DigestFunction(val hashFunName: String, val index: Int) {
    Ed25519("ed25519", 0xed)
}

/**
 * Generate Ed25519 keypair
 *
 * @throws CryptoException
 */
fun generateKeyPair(spec: EdDSAParameterSpec = DEFAULT_SPEC): KeyPair {
    return try {
        val seed = ByteArray(spec.curve.field.getb() / 8)
        SecureRandom().nextBytes(seed)

        val privKey = EdDSAPrivateKeySpec(seed, spec)
        val pubKey = EdDSAPublicKeySpec(privKey.a, spec)
        KeyPair(
            EdDSAPublicKey(pubKey),
            EdDSAPrivateKey(privKey)
        )
    } catch (ex: Exception) {
        throw CryptoException("Cannot generate key pair", ex)
    }
}

/**
 * Creates ED25519 keypair key from hex of the public and private key
 *
 * @throws CryptoException if keypair cannot be created
 */
fun keyPairFromHex(publicKeyHex: String, privateKeyHex: String, spec: EdDSAParameterSpec = DEFAULT_SPEC) =
    KeyPair(publicKeyFromHex(publicKeyHex, spec), privateKeyFromHex(privateKeyHex, spec))

/**
 * Creates ED25519 private key from hex
 *
 * @throws CryptoException if key cannot be created from hex
 */
fun privateKeyFromHex(privateKeyHex: String, spec: EdDSAParameterSpec = DEFAULT_SPEC) =
    try {
        EdDSAPrivateKey(EdDSAPrivateKeySpec(privateKeyHex.fromHex(), spec))
    } catch (ex: Exception) {
        throw CryptoException("Cannot create private key from hex `$privateKeyHex`", ex)
    }

/**
 * Creates ED25519 public key from hex
 *
 * @throws CryptoException if key cannot be created from hex
 */
fun publicKeyFromHex(publicKeyHex: String, spec: EdDSAParameterSpec = DEFAULT_SPEC) =
    try {
        EdDSAPublicKey(EdDSAPublicKeySpec(publicKeyHex.fromHex(), spec))
    } catch (ex: Exception) {
        throw CryptoException("Cannot create public key from hex `$publicKeyHex`", ex)
    }

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
