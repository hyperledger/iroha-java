package jp.co.soramitsu.iroha2.testcontainers

import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import java.util.UUID.randomUUID
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy
import org.testcontainers.images.PullPolicy
import org.testcontainers.utility.DockerImageName
import org.testcontainers.utility.MountableFile.forHostPath
import kotlin.io.path.absolute
import kotlin.io.path.createTempFile

open class IrohaContainer(
    imageTag: String = DEFAULT_IMAGE_TAG,
    config: IrohaConfig.() -> Unit = {}
) : GenericContainer<IrohaContainer>(DockerImageName.parse("$IMAGE_NAME:${imageTag}")) {

    private val config = IrohaConfig().apply(config)

    private val genesisFileLocation: Lazy<Path> = lazy { createTempFile("genesis-", randomUUID().toString()) }

    init {
        this.withNetwork(this.config.networkToJoin)
            .withEnv(ENV_SUMERAGI_MAX_FAULTY_PEERS.first, ENV_SUMERAGI_MAX_FAULTY_PEERS.second)
            .withEnv(ENV_TORII_P2P_URL.first, ENV_TORII_P2P_URL.second)
            .withEnv(ENV_TORII_API_URL.first, ENV_TORII_API_URL.second)
            .withEnv(ENV_IROHA_PUBLIC_KEY.first, ENV_IROHA_PUBLIC_KEY.second)
            .withEnv(ENV_IROHA_ROOT_PUBLIC_KEY.first, ENV_IROHA_ROOT_PUBLIC_KEY.second)
            .withEnv(ENV_IROHA_PRIVATE_KEY.first, ENV_IROHA_PRIVATE_KEY.second)
            .withEnv(ENV_SUMERAGI_TRUSTED_PEERS.first, ENV_SUMERAGI_TRUSTED_PEERS.second)
            .withEnv("MAX_LOG_LEVEL", this.config.maxLogLevel.name)
            .withExposedPorts(API_PORT, P2P_PORT)
            .withNetworkAliases(NETWORK_ALIAS)
            .withLogConsumer(this.config.logConsumer)
            .withCopyFileToContainer(
                forHostPath(this.config.genesis.writeToFile(genesisFileLocation.value)),
                DEFAULT_GENESIS_FILE_NAME
            )
            .withCommand(PEER_START_COMMAND)
            .withImagePullPolicy(PullPolicy.ageBased(Duration.ofMinutes(10)))
            .waitingFor(
                HttpWaitStrategy()
                    .forStatusCode(200)
                    .forPort(API_PORT)
                    .forPath(HEALTHCHECK)
                    .withStartupTimeout(CONTAINER_STARTUP_TIMEOUT)
            )
    }

    override fun start() {
        logger().debug("Starting Iroha container")
        if (logger().isDebugEnabled) {
            val genesisAsJson = config.genesis.asJson()
            logger().debug("Serialized genesis block: {}", genesisAsJson)
        }
        super.start()
        logger().debug("Iroha container started")
    }

    override fun stop() {
        logger().debug("Stopping Iroha container")
        super.stop()
        if (config.shouldCloseNetwork) {
            network.close()
        }
        try {
            Files.deleteIfExists(genesisFileLocation.value)
        } catch (ex: IOException) {
            logger().warn("Could not remove temporary genesis file '${genesisFileLocation.value.absolute()}', error: $ex")
        }
        logger().debug("Iroha container stopped")
    }

    fun getApiUrl(): URL = URL("http", containerIpAddress, this.getMappedPort(API_PORT), "")

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

        const val DEFAULT_IMAGE_TAG = "dev"
        const val IMAGE_NAME = "hyperledger/iroha2"
        const val DEFAULT_GENESIS_FILE_NAME = "genesis.json"
        const val PEER_START_COMMAND = "./iroha --submit-genesis --genesis-path $DEFAULT_GENESIS_FILE_NAME"
        val CONTAINER_STARTUP_TIMEOUT: Duration = Duration.ofSeconds(60)
    }
}
