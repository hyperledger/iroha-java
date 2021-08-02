package jp.co.soramitsu.iroha2.utils

import jp.co.soramitsu.iroha2.utils.DigestFunction.Ed25519
import net.i2p.crypto.eddsa.EdDSAEngine
import net.i2p.crypto.eddsa.KeyPairGenerator
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable
import org.bouncycastle.jcajce.provider.digest.Blake2b
import java.security.KeyPair
import java.security.MessageDigest
import java.security.PublicKey
import java.security.SecureRandom
import java.security.Signature
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey as IrohaPublicKey

val SPEC = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519)

enum class DigestFunction(val hashFunName: String, val index: Int) {
    Ed25519("ed25519", 0xed)
}

val keyPairGenerator = lazy {
    val keyPairGenerator = KeyPairGenerator()
    keyPairGenerator.initialize(256, SecureRandom())
    keyPairGenerator
}

fun generateKeyPair(): KeyPair = keyPairGenerator.value.generateKeyPair()

fun PublicKey.toIrohaPublicKey() : IrohaPublicKey {
   return IrohaPublicKey(Ed25519.hashFunName, this.encoded.map { it.toUByte() }.toMutableList())
}

fun sign(message: ByteArray, keyPair: KeyPair) : ByteArray {
    val spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519)
    val sgr: Signature = EdDSAEngine(
        MessageDigest.getInstance(SPEC.hashAlgorithm)
    )
    sgr.initSign(keyPair.private)
    sgr.update(message)
    return sgr.sign()
}

fun hash(target: ByteArray) : ByteArray = Blake2b.Blake2b256().digest(target)
