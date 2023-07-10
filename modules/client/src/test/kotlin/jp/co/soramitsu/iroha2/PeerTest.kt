package jp.co.soramitsu.iroha2

import io.qameta.allure.Feature
import io.qameta.allure.Issue
import io.qameta.allure.Owner
import io.qameta.allure.Story
import jp.co.soramitsu.iroha2.annotations.Permission
import jp.co.soramitsu.iroha2.annotations.Sdk
import jp.co.soramitsu.iroha2.annotations.SdkTestId
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.PeerId
import jp.co.soramitsu.iroha2.generated.SocketAddr
import jp.co.soramitsu.iroha2.generated.SocketAddrHost
import jp.co.soramitsu.iroha2.query.QueryBuilder
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.AliceCanUnregisterAnyPeer
import jp.co.soramitsu.iroha2.testengine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.testengine.DefaultGenesis
import jp.co.soramitsu.iroha2.testengine.IrohaConfig
import jp.co.soramitsu.iroha2.testengine.IrohaContainer
import jp.co.soramitsu.iroha2.testengine.IrohaTest
import jp.co.soramitsu.iroha2.testengine.WithIroha
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.withTimeout
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.security.KeyPair
import java.time.Duration
import kotlin.reflect.full.createInstance
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Timeout(100)
@Owner("akostyuchenko")
@Sdk("Java/Kotlin")
@Feature("Peers")
@Issue("https://github.com/hyperledger/iroha/issues/2962")
class PeerTest : IrohaTest<AdminIroha2Client>() {

    companion object {
        private const val PEER_AMOUNT = 4
    }

    @Test
    @WithIroha([DefaultGenesis::class], amount = PEER_AMOUNT)
    @Story("Account registers a peer")
    @Permission("no_permission_required")
    @SdkTestId("register_peer")
    fun `register peer`(): Unit = runBlocking {
        val ports = findFreePorts(3)
        val p2pPort = ports[IrohaConfig.P2P_PORT_IDX]
        val alias = "iroha$p2pPort"
        val address = "$alias:$p2pPort"
        val keyPair = generateKeyPair()
        val payload = keyPair.public.bytes()

        startNewContainer(keyPair, alias, ports).use {
            registerPeer(address, payload)
            assertTrue(isPeerAvailable(address, payload))
        }
    }

    @Test
    @WithIroha([AliceCanUnregisterAnyPeer::class], amount = PEER_AMOUNT)
    @Story("Account unregisters a peer")
    @Permission("no_permission_required")
    @SdkTestId("unregister_peer")
    fun `unregister peer`(): Unit = runBlocking {
        val ports = findFreePorts(3)
        val p2pPort = ports[IrohaConfig.P2P_PORT_IDX]
        val alias = "iroha$p2pPort"
        val address = "$alias:$p2pPort"
        val keyPair = generateKeyPair()
        val payload = keyPair.public.bytes()

        startNewContainer(keyPair, alias, ports).use {
            registerPeer(address, payload)
            repeat(PEER_AMOUNT) { assertTrue(isPeerAvailable(address, payload)) }

            unregisterPeer(address, payload)
            repeat(PEER_AMOUNT) { assertFalse(isPeerAvailable(address, payload)) }
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class], amount = PEER_AMOUNT)
    fun `registered peer should return consistent data`(): Unit = runBlocking {
        val ports = findFreePorts(3)
        val p2pPort = ports[IrohaConfig.P2P_PORT_IDX]
        val alias = "iroha$p2pPort"
        val address = "$alias:$p2pPort"
        val keyPair = generateKeyPair()
        val payload = keyPair.public.bytes()

        startNewContainer(keyPair, alias, ports).use { container ->
            registerPeer(address, payload)
            assertTrue(isPeerAvailable(address, payload))

            delay(5000)

            val peersCount = QueryBuilder.findAllPeers()
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR)
                .let { client.sendQuery(it) }
                .size

            repeat(5) {
                runCatching {
                    QueryBuilder.findAllPeers()
                        .account(ALICE_ACCOUNT_ID)
                        .buildSigned(ALICE_KEYPAIR)
                        .let { Iroha2Client(mutableListOf(container.getApiUrl() to container.getTelemetryUrl())).sendQuery(it) }
                        .also { peers -> assertEquals(peers.size, peersCount) }
                        .also { return@repeat }
                }
                delay(2000)
            }
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class], amount = PEER_AMOUNT)
    fun `round-robin load balancing test`(): Unit = runBlocking {
        repeat(PEER_AMOUNT + 1) {
            assertEquals(findDomain(DEFAULT_DOMAIN_ID).id, DEFAULT_DOMAIN_ID)
        }
    }

    private fun startNewContainer(
        keyPair: KeyPair,
        alias: String,
        ports: List<Int>,
    ): IrohaContainer {
        return IrohaContainer {
            this.waitStrategy = false
            this.keyPair = keyPair
            this.ports = ports
            this.alias = alias
            this.networkToJoin = containers.first().network ?: throw IrohaSdkException("Container network not found")
            this.genesis = DefaultGenesis::class.createInstance()
            this.trustedPeers = containers.map { it.extractPeerId() }
        }.also { it.start() }
    }

    private suspend fun isPeerAvailable(
        address: String,
        payload: ByteArray,
        keyPair: KeyPair = ALICE_KEYPAIR,
    ): Boolean {
        return QueryBuilder.findAllPeers()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(keyPair)
            .let { query ->
                client.sendQuery(query)
            }.any { peer ->
                val peerAddr = peer.id.address.cast<SocketAddr.Host>().socketAddrHost
                "${peerAddr.host}:${peerAddr.port}" == address && peer.id.publicKey.payload.contentEquals(payload)
            }
    }

    private suspend fun unregisterPeer(
        address: String,
        payload: ByteArray,
        keyPair: KeyPair = ALICE_KEYPAIR,
    ) {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            unregisterPeer(address, payload)
            buildSigned(keyPair)
        }.also { d ->
            withTimeout(txTimeout.plus(Duration.ofSeconds(20))) { d.await() }
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
            withTimeout(txTimeout.plus(Duration.ofSeconds(20))) { d.await() }
        }
    }

    private fun IrohaContainer.extractPeerId() = PeerId(
        SocketAddr.Host(SocketAddrHost(this.getP2pUrl().host, this.getP2pUrl().port)),
        this.config.keyPair.public.toIrohaPublicKey(),
    )

    private suspend fun findDomain(id: DomainId = DEFAULT_DOMAIN_ID) = QueryBuilder
        .findDomainById(id)
        .account(super.account)
        .buildSigned(super.keyPair)
        .let { client.sendQuery(it) }
}
