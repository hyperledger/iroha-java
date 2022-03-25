package jp.co.soramitsu.iroha2.testcontainers

import jp.co.soramitsu.iroha2.DEFAULT_API_PORT
import jp.co.soramitsu.iroha2.DEFAULT_P2P_PORT
import jp.co.soramitsu.iroha2.DEFAULT_TELEMETRY_PORT
import jp.co.soramitsu.iroha2.Iroha2Client.Companion.STATUS_ENDPOINT
import jp.co.soramitsu.iroha2.JSON_SERDE
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy
import org.testcontainers.images.PullPolicy
import org.testcontainers.utility.DockerImageName
import org.testcontainers.utility.MountableFile
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
            .withEnv(ENV_IROHA2_GENESIS_PATH.first, ENV_IROHA2_GENESIS_PATH.second)
            .withEnv(ENV_IROHA2_CONFIG_PATH.first, ENV_IROHA2_CONFIG_PATH.second)
            .withExposedPorts(DEFAULT_API_PORT, DEFAULT_P2P_PORT, DEFAULT_TELEMETRY_PORT)
            .withNetworkAliases(NETWORK_ALIAS)
            .withLogConsumer(config.logConsumer)
            .withCopyFileToContainer(
                forHostPath(config.genesis.writeToFile(genesisFileLocation.value)),
                DEFAULT_GENESIS_FILE_NAME
            )
            .withCopyFileToContainer(
                MountableFile.forClasspathResource(DEFAULT_CONFIG_FILE_NAME),
                DEFAULT_CONFIG_FILE_NAME
            )
            .withCommand(PEER_START_COMMAND)
            .withImagePullPolicy(PullPolicy.ageBased(Duration.ofMinutes(10)))
            .waitingFor(
                // await genesis was applied and seen in status
                HttpWaitStrategy()
                    .forStatusCode(200)
                    .forPort(DEFAULT_TELEMETRY_PORT)
                    .forPath(STATUS_ENDPOINT)
                    .forResponsePredicate { JSON_SERDE.readTree(it).get("blocks")?.doubleValue()?.equals(1.0) ?: false }
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

    fun getApiUrl(): URL = URL("http", containerIpAddress, this.getMappedPort(DEFAULT_API_PORT), "")

    fun getTelemetryUrl(): URL = URL("http", containerIpAddress, this.getMappedPort(DEFAULT_TELEMETRY_PORT), "")

    companion object {
        const val NETWORK_ALIAS = "iroha"
        const val DEFAULT_IMAGE_TAG = "dev"
        const val IMAGE_NAME = "hyperledger/iroha2"
        const val DEFAULT_GENESIS_FILE_NAME = "genesis.json"
        const val DEFAULT_CONFIG_FILE_NAME = "config.json"
        const val PEER_START_COMMAND = "./iroha --submit-genesis"

        val ENV_IROHA2_GENESIS_PATH = "IROHA2_GENESIS_PATH" to DEFAULT_GENESIS_FILE_NAME
        val ENV_IROHA2_CONFIG_PATH = "IROHA2_CONFIG_PATH" to DEFAULT_CONFIG_FILE_NAME

        val CONTAINER_STARTUP_TIMEOUT: Duration = Duration.ofSeconds(60)
    }
}
