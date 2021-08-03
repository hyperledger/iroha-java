package jp.co.soramitsu.iroha2.util

import jp.co.soramitsu.iroha2.utils.generateKeyPair
import jp.co.soramitsu.iroha2.utils.hash
import jp.co.soramitsu.iroha2.utils.keyPairFromHex
import jp.co.soramitsu.iroha2.utils.sign
import jp.co.soramitsu.iroha2.utils.verify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.bouncycastle.util.encoders.Hex
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CryptoTest {

    @Test
    fun `generating key pairs is thread safe`() {
        val iterations = 1000
        val futureResults = generateSequence { GlobalScope.async { generateKeyPair() } }
            .take(iterations).toSet()

        class ByteArrayWrapper(private val byteArray: ByteArray) {
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is ByteArrayWrapper) return false
                if (!byteArray.contentEquals(other.byteArray)) return false
                return true
            }

            override fun hashCode() = byteArray.contentHashCode()
        }

        runBlocking {
            assertEquals(
                iterations,
                futureResults.map { it.await().private.encoded }
                    .map { ByteArrayWrapper(it) }
                    .toSet().size
            )
        }
    }

    @Test
    fun `signature created and verified`() {
        val keyPair = generateKeyPair()
        val message = "Test message to sign.".toByteArray()
        val signature = sign(message, keyPair.private)

        assertTrue { verify(signature, keyPair.public, message) }
    }

    @Test
    fun `hash function returns expected result`() {
        val expected = "ba67336efd6a3df3a70eeb757860763036785c182ff4cf587541a0068d09f5b2"
        val input = "6920616d2064617461"
        assertEquals(expected, Hex.toHexString(hash(Hex.decode(input))))
    }

    @Test
    fun `keypair serialized to hex and deserialized back`() {
        val keyPair = generateKeyPair()
        val pubKey = Hex.toHexString(keyPair.public.encoded)
        val privKey = Hex.toHexString(keyPair.private.encoded)

        val restoredKeyPair = keyPairFromHex(pubKey, privKey)
        assertEquals(keyPair.private, restoredKeyPair.private)
        assertEquals(keyPair.public, restoredKeyPair.public)
    }

    // @Test
    // fun `transaction signature is same as expected`() {
    //     val keyPair = keyPairFromHex(
    //         publicKeyHex = "5b4dfe6632ee7e3627e9c2052339314a14ae8f565fd2558711caf6273c27cfe7",
    //         privateKeyHex = "0c7464df2a1fb6b0f78fb1f2c9c01a436c0ccd76be11a90d835080145159d928"
    //     )
    //     val expectedSignature = "f2139d3c91bb605edbc783cc900abdd5b81b687c10d2e2827b23e1c7626c3a15b3adaded97f65a2fdbde3839bda9a2b2ca889f18a8d9ac8b2a9cab9149a68d0a"
    //     val versionedTx = TransactionBuilder.builder()
    //         .account(ALICE_ACCOUNT_ID)
    //         .creationTime(0)
    //         .timeToLive(0)
    //         .buildSigned(keyPair) as VersionedTransaction.V1
    //
    //     val signatures = versionedTx._VersionedTransactionV1.transaction.signatures
    //     assertEquals(1, signatures.size)
    //     val signature = signatures.first()
    //     assertContentEquals(hex(expectedSignature), signature.signature.toUByteArray().toByteArray())
    // }
}
