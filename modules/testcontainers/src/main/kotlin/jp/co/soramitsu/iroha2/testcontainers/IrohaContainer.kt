package jp.co.soramitsu.iroha2.testcontainers

import jp.co.soramitsu.iroha2.GSON
import jp.co.soramitsu.iroha2.Iroha2Client.Companion.STATUS_ENDPOINT
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy
import org.testcontainers.images.PullPolicy
import org.testcontainers.utility.DockerImageName
import org.testcontainers.utility.MountableFile.forHostPath
import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.time.Duration
import java.util.UUID.randomUUID
import kotlin.io.path.absolute
import kotlin.io.path.createTempFile

open class IrohaContainer : GenericContainer<IrohaContainer> {

    constructor(config: IrohaConfig.() -> Unit = {}) : this(IrohaConfig().apply(config))

    constructor(config: IrohaConfig) : super(
        DockerImageName.parse("$IMAGE_NAME:${config.imageTag}")
    ) {
        this.config = config
        this.withNetwork(config.networkToJoin)
            .withEnv(ENV_SUMERAGI_MAX_FAULTY_PEERS.first, ENV_SUMERAGI_MAX_FAULTY_PEERS.second)
            .withEnv(ENV_TORII_P2P_ADDR.first, ENV_TORII_P2P_ADDR.second)
            .withEnv(ENV_TORII_API_URL.first, ENV_TORII_API_URL.second)
            .withEnv(ENV_TORII_STATUS_URL.first, ENV_TORII_STATUS_URL.second)
            .withEnv(ENV_IROHA_PUBLIC_KEY.first, ENV_IROHA_PUBLIC_KEY.second)
            .withEnv(ENV_IROHA_ROOT_PUBLIC_KEY.first, ENV_IROHA_ROOT_PUBLIC_KEY.second)
            .withEnv(ENV_IROHA_PRIVATE_KEY.first, ENV_IROHA_PRIVATE_KEY.second)
            .withEnv(ENV_SUMERAGI_TRUSTED_PEERS.first, ENV_SUMERAGI_TRUSTED_PEERS.second)
            .withEnv(ENV_MAX_LOG_LEVEL, config.maxLogLevel.name)
            .withExposedPorts(API_PORT, P2P_PORT, STATUS_PORT)
            .withNetworkAliases(NETWORK_ALIAS)
            .withLogConsumer(config.logConsumer)
            .withCopyFileToContainer(
                forHostPath(config.genesis.writeToFile(genesisFileLocation.value)),
                DEFAULT_GENESIS_FILE_NAME
            )
            .withCommand(PEER_START_COMMAND)
            .withImagePullPolicy(PullPolicy.ageBased(Duration.ofMinutes(10)))
            .waitingFor(
                // await genesis was applied and seen in status
                HttpWaitStrategy()
                    .forStatusCode(200)
                    .forPort(STATUS_PORT)
                    .forPath(STATUS_ENDPOINT)
                    .forResponsePredicate { GSON.fromJson(it, Map::class.java)["blocks"]?.equals(1.0) ?: false }
                    .withStartupTimeout(CONTAINER_STARTUP_TIMEOUT)
            )
    }

    private val config: IrohaConfig

    private val genesisFileLocation: Lazy<Path> = lazy {
        createTempFile("genesis-", randomUUID().toString())
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
        const val STATUS_PORT = 8180
        const val NETWORK_ALIAS = "iroha"
        const val P2P_URL = "$NETWORK_ALIAS:$P2P_PORT"

        val ENV_SUMERAGI_MAX_FAULTY_PEERS = "SUMERAGI_MAX_FAULTY_PEERS" to "0"
        val ENV_TORII_P2P_ADDR = "TORII_P2P_ADDR" to P2P_URL
        val ENV_TORII_API_URL = "TORII_API_URL" to "$NETWORK_ALIAS:$API_PORT"
        val ENV_TORII_STATUS_URL = "TORII_STATUS_URL" to "$NETWORK_ALIAS:$STATUS_PORT"
        val ENV_IROHA_PUBLIC_KEY = "IROHA_PUBLIC_KEY" to IROHA_ROOT_PUBLIC_KEY
        val ENV_IROHA_ROOT_PUBLIC_KEY = "IROHA_ROOT_PUBLIC_KEY" to IROHA_ROOT_PUBLIC_KEY
        val ENV_IROHA_PRIVATE_KEY =
            "IROHA_PRIVATE_KEY" to """{"digest_function": "ed25519", "payload": "9ac47abf59b356e0bd7dcbbbb4dec080e302156a48ca907e47cb6aea1d32719e7233bfc89dcbd68c19fde6ce6158225298ec1131b6a130d1aeb454c1ab5183c0"}"""
        val ENV_SUMERAGI_TRUSTED_PEERS =
            "SUMERAGI_TRUSTED_PEERS" to """[{"address":"$P2P_URL", "public_key": "$IROHA_ROOT_PUBLIC_KEY"}]"""
        val ENV_MAX_LOG_LEVEL = "MAX_LOG_LEVEL"

        const val DEFAULT_IMAGE_TAG = "dev-nightly-ac05878aa51eb50ec3b6d5ba8ea7fed8e15c60d5"
        const val IMAGE_NAME = "hyperledger/iroha2"
        const val DEFAULT_GENESIS_FILE_NAME = "genesis.json"
        const val PEER_START_COMMAND = "./iroha --submit-genesis --genesis-path $DEFAULT_GENESIS_FILE_NAME"
        val CONTAINER_STARTUP_TIMEOUT: Duration = Duration.ofSeconds(60)
    }
}
