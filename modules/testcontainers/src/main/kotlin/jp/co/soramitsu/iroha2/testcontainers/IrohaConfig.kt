package jp.co.soramitsu.iroha2.testcontainers

import jp.co.soramitsu.iroha2.Genesis
import org.slf4j.LoggerFactory.getLogger
import org.testcontainers.containers.Network
import org.testcontainers.containers.Network.newNetwork
import org.testcontainers.containers.output.OutputFrame
import org.testcontainers.containers.output.Slf4jLogConsumer
import java.util.function.Consumer

/**
 * Iroha configuration
 */
class IrohaConfig(
    var networkToJoin: Network = newNetwork(),
    var logConsumer: Consumer<OutputFrame> = Slf4jLogConsumer(getLogger(IrohaContainer::class.java)),
    var genesis: Genesis = Genesis.getEmpty(),
    var shouldCloseNetwork: Boolean = true,
    var imageTag: String = IrohaContainer.DEFAULT_IMAGE_TAG
)
