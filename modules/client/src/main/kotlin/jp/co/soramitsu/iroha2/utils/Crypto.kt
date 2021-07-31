package jp.co.soramitsu.iroha2.utils

import jp.co.soramitsu.iroha2.utils.DigestFunction.Ed25519
import net.i2p.crypto.eddsa.KeyPairGenerator
import java.security.KeyPair
import java.security.PublicKey
import java.security.SecureRandom
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey as IrohaPublicKey

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
