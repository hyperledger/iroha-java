package jp.co.soramitsu.iroha2.testcontainers

import jp.co.soramitsu.iroha2.Genesis
import jp.co.soramitsu.iroha2.generated.core.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import org.slf4j.LoggerFactory.getLogger
import org.testcontainers.containers.Network
import org.testcontainers.containers.Network.newNetwork
import org.testcontainers.containers.output.OutputFrame
import org.testcontainers.containers.output.Slf4jLogConsumer
import java.util.function.Consumer

class IrohaConfig(
    var networkToJoin: Network = newNetwork(),
    var logConsumer: Consumer<OutputFrame> = Slf4jLogConsumer(getLogger(IrohaContainer::class.java)),
    var genesis: Genesis = Genesis(RawGenesisBlock(listOf(GenesisTransaction(listOf())))),
    var shouldCloseNetwork: Boolean = true,
    var maxLogLevel: MaxLogLevel = MaxLogLevel.DEBUG,
    var imageTag: String = IrohaContainer.DEFAULT_IMAGE_TAG
)

enum class MaxLogLevel {
    ERROR, WARN, INFO, DEBUG, TRACE
}
