package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.engine.DefaultGenesis
import jp.co.soramitsu.iroha2.engine.IrohaTest
import jp.co.soramitsu.iroha2.engine.WithIroha
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.peer.PeerId
import jp.co.soramitsu.iroha2.query.QueryBuilder
import jp.co.soramitsu.iroha2.testcontainers.IrohaConfig
import jp.co.soramitsu.iroha2.testcontainers.IrohaContainer
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.withTimeout
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.security.KeyPair
import kotlin.reflect.full.createInstance
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PeerTest : IrohaTest<Iroha2Client>() {

    companion object {
        private const val PEER_AMOUNT = 3
    }

    @Test
    @WithIroha(DefaultGenesis::class, amount = PEER_AMOUNT)
    fun `register peer`(): Unit = runBlocking {
        val ports = findFreePorts(3)
        val p2pPort = ports[IrohaConfig.P2P_PORT_IDX]
        val alias = "iroha$p2pPort"
        val address = "$alias:$p2pPort"
        val keyPair = generateKeyPair()
        val payload = keyPair.public.bytes()

        startNewContainer(keyPair, address, alias, payload, ports).use {
            registerPeer(address, payload)
            assertTrue(isPeerAvailable(address, payload))
        }
    }

    @Test
    @WithIroha(DefaultGenesis::class, amount = 2) // TODO: should work with amount >= 3
    fun `unregister peer`(): Unit = runBlocking {
        val ports = findFreePorts(3)
        val p2pPort = ports[IrohaConfig.P2P_PORT_IDX]
        val alias = "iroha$p2pPort"
        val address = "$alias:$p2pPort"
        val keyPair = generateKeyPair()
        val payload = keyPair.public.bytes()

        startNewContainer(keyPair, address, alias, payload, ports).use {
            registerPeer(address, payload)
            assertTrue(isPeerAvailable(address, payload))

            delay(1000)

            unregisterPeer(address, payload)
            assertFalse(isPeerAvailable(address, payload))
        }
    }

    @Disabled // https://github.com/hyperledger/iroha/issues/2385
    @Test
    @WithIroha(DefaultGenesis::class, amount = PEER_AMOUNT)
    fun `registered peer should return consistent data`(): Unit = runBlocking {
        val ports = findFreePorts(3)
        val p2pPort = ports[IrohaConfig.P2P_PORT_IDX]
        val alias = "iroha$p2pPort"
        val address = "$alias:$p2pPort"
        val keyPair = generateKeyPair()
        val payload = keyPair.public.bytes()

        startNewContainer(keyPair, address, alias, payload, ports).use { container ->
            registerPeer(address, payload)

            val peersCount = QueryBuilder.findAllPeers()
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR)
                .let { client.sendQuery(it) }
                .size
            QueryBuilder.findAllPeers()
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR)
                .let { Iroha2Client(container.getApiUrl()).sendQuery(it) }
                .also { peers -> assertEquals(peers.size, peersCount) }
        }
    }

    private fun startNewContainer(
        keyPair: KeyPair,
        address: String,
        alias: String,
        payload: ByteArray,
        ports: List<Int>
    ): IrohaContainer {
        return IrohaContainer {
            this.waitStrategy = false
            this.keyPair = keyPair
            this.ports = ports
            this.alias = alias
            this.networkToJoin = containers.first().network
            this.genesis = DefaultGenesis::class.createInstance()
            this.trustedPeers = containers
                .map { it.extractPeerId() }
                .plus(PeerId(address, PublicKey(DigestFunction.Ed25519.hashFunName, payload)))
        }.also { it.start() }
    }

    private suspend fun isPeerAvailable(
        address: String,
        payload: ByteArray,
        keyPair: KeyPair = ALICE_KEYPAIR
    ): Boolean {
        return QueryBuilder.findAllPeers()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(keyPair)
            .let { query ->
                client.sendQuery(query)
            }.any { peer ->
                peer.id.address == address && peer.id.publicKey.payload.contentEquals(payload)
            }
    }

    private suspend fun unregisterPeer(
        address: String,
        payload: ByteArray,
        keyPair: KeyPair = ALICE_KEYPAIR
    ) {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            unregisterPeer(address, payload)
            buildSigned(keyPair)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }

    private suspend fun registerPeer(
        address: String,
        payload: ByteArray,
        keyPair: KeyPair = ALICE_KEYPAIR,
    ) {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerPeer(address, payload)
            buildSigned(keyPair)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }

    private fun IrohaContainer.extractPeerId() = PeerId(
        this.getP2pUrl().toString(),
        this.config.keyPair.public.toIrohaPublicKey()
    )
}
