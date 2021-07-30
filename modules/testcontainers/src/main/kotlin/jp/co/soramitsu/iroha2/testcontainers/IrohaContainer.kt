package jp.co.soramitsu.iroha2.testcontainers

import jp.co.soramitsu.iroha2.Instructions
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import org.slf4j.LoggerFactory.getLogger
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.Network
import org.testcontainers.containers.Network.newNetwork
import org.testcontainers.containers.output.OutputFrame
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy
import org.testcontainers.utility.DockerImageName
import org.testcontainers.utility.MountableFile.forClasspathResource
import java.nio.file.Paths
import java.time.Duration
import java.util.function.Consumer

fun main() {
    val genesis = GenesisBuilder.builder()
        .withInstructions(
            Instructions.registerDomain("wonderland", )
        )
        .build()
    genesis.writeToFile(Paths.get("./1"))
}

class IrohaContainer(
    private val networkToJoin: Network = newNetwork(),
    private val logConsumer: Consumer<OutputFrame>? = Slf4jLogConsumer(getLogger(IrohaContainer::class.java)),
    imageTag: String = DEFAULT_IMAGE_TAG,
) : GenericContainer<IrohaContainer>(DockerImageName.parse("$IMAGE_NAME:$imageTag")) {

    private var shouldCloseNetwork = true

    override fun start() {

        withNetwork(networkToJoin)
            .withEnv(ENV_SUMERAGI_MAX_FAULTY_PEERS.first, ENV_SUMERAGI_MAX_FAULTY_PEERS.second)
            .withEnv(ENV_TORII_P2P_URL.first, ENV_TORII_P2P_URL.second)
            .withEnv(ENV_TORII_API_URL.first, ENV_TORII_API_URL.second)
            .withEnv(ENV_IROHA_PUBLIC_KEY.first, ENV_IROHA_PUBLIC_KEY.second)
            .withEnv(ENV_IROHA_ROOT_PUBLIC_KEY.first, ENV_IROHA_ROOT_PUBLIC_KEY.second)
            .withEnv(ENV_IROHA_PRIVATE_KEY.first, ENV_IROHA_PRIVATE_KEY.second)
            .withEnv(ENV_SUMERAGI_TRUSTED_PEERS.first, ENV_SUMERAGI_TRUSTED_PEERS.second)
            .withExposedPorts(API_PORT, P2P_PORT)
            .withNetworkAliases(NETWORK_ALIAS)
            .withLogConsumer(logConsumer)
            .withCopyFileToContainer(forClasspathResource(DEFAULT_GENESIS_FILE_NAME), ".")
            .withCommand(PEER_START_COMMAND)
            .waitingFor(
                HttpWaitStrategy()
                    .forStatusCode(200)
                    .forPort(API_PORT)
                    .forPath(HEALTHCHECK)
                    .withStartupTimeout(CONTAINER_STARTUP_TIMEOUT)
            )
        super.start()
    }

    override fun stop() {
        super.stop()
        if (shouldCloseNetwork) {
            network.close()
        }
    }

    fun preserveNetwork(): IrohaContainer {
        shouldCloseNetwork = false
        return this
    }

    companion object {
        const val IROHA_ROOT_PUBLIC_KEY =
            "ed01207233bfc89dcbd68c19fde6ce6158225298ec1131b6a130d1aeb454c1ab5183c0"
        const val P2P_PORT = 1337
        const val API_PORT = 8080
        const val NETWORK_ALIAS = "iroha"
        const val P2P_URL = "$NETWORK_ALIAS:$P2P_PORT"
        const val HEALTHCHECK = "/health"
        val ENV_SUMERAGI_MAX_FAULTY_PEERS = "SUMERAGI_MAX_FAULTY_PEERS" to "0"
        val ENV_TORII_P2P_URL = "TORII_P2P_URL" to P2P_URL
        val ENV_TORII_API_URL = "TORII_API_URL" to "$NETWORK_ALIAS:$API_PORT"
        val ENV_IROHA_PUBLIC_KEY = "IROHA_PUBLIC_KEY" to IROHA_ROOT_PUBLIC_KEY
        val ENV_IROHA_ROOT_PUBLIC_KEY = "IROHA_ROOT_PUBLIC_KEY" to IROHA_ROOT_PUBLIC_KEY
        val ENV_IROHA_PRIVATE_KEY =
            "IROHA_PRIVATE_KEY" to """{"digest_function": "ed25519", "payload": "9ac47abf59b356e0bd7dcbbbb4dec080e302156a48ca907e47cb6aea1d32719e7233bfc89dcbd68c19fde6ce6158225298ec1131b6a130d1aeb454c1ab5183c0"}"""
        val ENV_SUMERAGI_TRUSTED_PEERS =
            "SUMERAGI_TRUSTED_PEERS" to """[{"address":"$P2P_URL", "public_key": "$IROHA_ROOT_PUBLIC_KEY"}]"""
        const val DEFAULT_IMAGE_TAG = "develop"

        //todo change image name as soon as official image will be created
        const val IMAGE_NAME = "rkharisov/iroha"
        const val DEFAULT_GENESIS_FILE_NAME = "genesis.json"
        const val PEER_START_COMMAND = "./iroha --genesis $DEFAULT_GENESIS_FILE_NAME"
        val CONTAINER_STARTUP_TIMEOUT: Duration = Duration.ofSeconds(60)
    }
}
