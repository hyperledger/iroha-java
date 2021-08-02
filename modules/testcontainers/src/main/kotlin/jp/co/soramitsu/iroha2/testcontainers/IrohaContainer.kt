package jp.co.soramitsu.iroha2.testcontainers

import jp.co.soramitsu.iroha2.Instructions
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.genesis.RawGenesisBlock
import jp.co.soramitsu.iroha2.utils.generateKeyPair
import jp.co.soramitsu.iroha2.utils.toIrohaPublicKey
import org.slf4j.LoggerFactory.getLogger
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.Network
import org.testcontainers.containers.Network.newNetwork
import org.testcontainers.containers.output.OutputFrame
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy
import org.testcontainers.images.PullPolicy
import org.testcontainers.utility.DockerImageName
import org.testcontainers.utility.MountableFile.forHostPath
import java.io.IOException
import java.net.URL
import java.nio.file.Files
import java.time.Duration
import java.util.UUID.randomUUID
import java.util.function.Consumer
import kotlin.io.path.absolute
import kotlin.io.path.createTempFile

class IrohaContainer(
    private val networkToJoin: Network = newNetwork(),
    private val logConsumer: Consumer<OutputFrame>? = Slf4jLogConsumer(getLogger(IrohaContainer::class.java)),
    imageTag: String = DEFAULT_IMAGE_TAG,
    private val genesis: Genesis = defaultGenesis(),
    private val shouldCloseNetwork: Boolean = true
) : GenericContainer<IrohaContainer>(DockerImageName.parse("$IMAGE_NAME:$imageTag")) {

    private val genesisFileLocation = lazy { createTempFile("genesis-", randomUUID().toString()) }

    override fun start() {
        logger().debug("Starting Iroha container")
        if (logger().isDebugEnabled) {
            val genesisAsJson = genesis.asJson()
            logger().debug("Serialized genesis block: {}", genesisAsJson)
        }
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
            .withCopyFileToContainer(
                forHostPath(genesis.writeToFile(genesisFileLocation.value)),
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
        super.start()
        logger().debug("Iroha container started")
    }

    override fun stop() {
        logger().debug("Stopping Iroha container")
        super.stop()
        if (shouldCloseNetwork) {
            network.close()
        }
        try {
            Files.deleteIfExists(genesisFileLocation.value)
        } catch (ex: IOException) {
            logger().warn("Could not remove temporary genesis file '${genesisFileLocation.value.absolute()}', error: $ex")
        }
        logger().debug("Iroha container stopped")
    }

    fun getApiUrl() : URL = URL("http", containerIpAddress, this.getMappedPort(API_PORT), "")

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
        const val DEFAULT_IMAGE_TAG = "iroha2-dev"

        //todo change image name as soon as official image will be created
        const val IMAGE_NAME = "iroha1/iroha"
        const val DEFAULT_GENESIS_FILE_NAME = "genesis.json"
        const val PEER_START_COMMAND = "./iroha_cli --genesis $DEFAULT_GENESIS_FILE_NAME"
        val CONTAINER_STARTUP_TIMEOUT: Duration = Duration.ofSeconds(60)
    }
}

private fun defaultGenesis(): Genesis {
    val accountId = Id("alice", "wonderland")
    return Genesis(
        RawGenesisBlock(
            mutableListOf(
                GenesisTransaction(
                    mutableListOf(
                        Instructions.registerDomain("wonderland", mutableMapOf(), mutableMapOf()),
                        Instructions.registerAccount(
                            accountId,
                            mutableListOf(generateKeyPair().public.toIrohaPublicKey()),
                        ),
                        Instructions.registerAsset(
                            "val",
                            "wonderland",
                            AssetValueType.Quantity()
                        )
                    )
                )
            )
        )
    )
}
