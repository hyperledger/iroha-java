package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.AdminIroha2AsyncClient
import jp.co.soramitsu.iroha2.AdminIroha2Client
import jp.co.soramitsu.iroha2.Genesis.Companion.toSingle
import jp.co.soramitsu.iroha2.cast
import jp.co.soramitsu.iroha2.client.Iroha2AsyncClient
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.findFreePorts
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.datamodel.peer.PeerId
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.InvocationInterceptor
import org.junit.jupiter.api.extension.ReflectiveInvocationContext
import org.testcontainers.containers.Network
import java.lang.reflect.Method
import java.security.KeyPair
import java.util.Collections
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties

/**
 * Runner for Iroha2 Docker containers
 */
class IrohaRunnerExtension : InvocationInterceptor {

    override fun interceptTestMethod(
        invocation: InvocationInterceptor.Invocation<Void>,
        invocationContext: ReflectiveInvocationContext<Method>,
        extensionContext: ExtensionContext
    ) = runBlocking {
        // init container and client if annotation was passed on test method
        val resources = initIfRequested(invocationContext)
        try {
            // invoke actual test method
            super.interceptTestMethod(invocation, invocationContext, extensionContext)
        } finally {
            // stop container and client if they were created
            resources.forEach { it.close() }
        }
    }

    private suspend fun initIfRequested(
        invocationContext: ReflectiveInvocationContext<Method>
    ): List<AutoCloseable> = coroutineScope {
        val withIroha = invocationContext
            .executable.declaredAnnotations
            .filterIsInstance<WithIroha>()
            .firstOrNull() ?: return@coroutineScope emptyList()

        val network = invocationContext.target.get().cast<IrohaTest<Iroha2Client>>().network
        val utilizedResources = mutableListOf<AutoCloseable>()

        // start containers
        val containers = createContainers(withIroha, network)
        utilizedResources.addAll(containers)

        val testClassInstance = invocationContext.target.get()
        val properties = testClassInstance::class.memberProperties

        // inject `List<IrohaContainer>` if it is declared in test class
        setPropertyValue(properties, testClassInstance) { containers }

        // inject `Iroha2Client` if it is declared in test class
        setPropertyValue(properties, testClassInstance) {
            Iroha2Client(containers.first().getApiUrl(), log = true)
                .also { utilizedResources.add(it) }
        }

        // inject `AdminIroha2Client` if it is declared in test class
        setPropertyValue(properties, testClassInstance) {
            AdminIroha2Client(
                containers.first().getApiUrl(),
                containers.first().getTelemetryUrl(),
                log = true
            ).also { utilizedResources.add(it) }
        }

        // inject `Iroha2AsyncClient` if it is declared in test class
        setPropertyValue(properties, testClassInstance) {
            Iroha2AsyncClient(containers.first().getApiUrl(), log = true)
                .also { utilizedResources.add(it) }
        }

        // inject `AdminIroha2AsyncClient` if it is declared in test class
        setPropertyValue(properties, testClassInstance) {
            AdminIroha2AsyncClient(
                containers.first().getApiUrl(),
                containers.first().getTelemetryUrl(),
                log = true
            ).also { utilizedResources.add(it) }
        }

        utilizedResources
    }

    private inline fun <reified V : Any> setPropertyValue(
        declaredProperties: Collection<KProperty1<out Any, *>>,
        testClassInstance: Any,
        valueToSet: () -> V
    ) {
        declaredProperties
            .filter { it.returnType.classifier == V::class }
            .filterIsInstance<KMutableProperty1<out Any, V>>()
            .also { check(it.size <= 1) { "Found more than one property with type `${V::class.qualifiedName}` in test class `${testClassInstance::class::qualifiedName}`" } }
            .firstOrNull()
            ?.setter
            ?.call(testClassInstance, valueToSet())
    }

    private suspend fun createContainers(
        withIroha: WithIroha,
        network: Network
    ): List<IrohaContainer> = coroutineScope {
        val keyPairs = mutableListOf<KeyPair>()
        val portsList = mutableListOf<List<Int>>()
        repeat(withIroha.amount) {
            keyPairs.add(generateKeyPair())
            portsList.add(findFreePorts(3)) // P2P + API + TELEMETRY
        }

        val peerIds = keyPairs.mapIndexed { i: Int, kp: KeyPair ->
            val p2pPort = portsList[i][IrohaConfig.P2P_PORT_IDX]
            kp.toPeerId(IrohaContainer.NETWORK_ALIAS + p2pPort, p2pPort)
        }

        val deferredSet = mutableSetOf<Deferred<*>>()
        val containers = Collections.synchronizedList(ArrayList<IrohaContainer>(withIroha.amount))
        repeat(withIroha.amount) { n ->
            async(Dispatchers.IO) {
                val p2pPort = portsList[n][IrohaConfig.P2P_PORT_IDX]
                val container = IrohaContainer {
                    networkToJoin = network
                    genesis = withIroha.sources.map { it.createInstance() }.toSingle()
                    alias = IrohaContainer.NETWORK_ALIAS + p2pPort
                    keyPair = keyPairs[n]
                    trustedPeers = peerIds
                    ports = portsList[n]
                    // only first peer should have --submit-genesis in peer start command
                    submitGenesis = n == 0
                }
                container.start()
                containers.add(container)
            }.let { deferredSet.add(it) }
        }

        deferredSet.forEach { it.await() }

        containers
    }

    private fun KeyPair.toPeerId(host: String, port: Int) = PeerId(
        "$host:$port",
        this.public.toIrohaPublicKey()
    )
}
