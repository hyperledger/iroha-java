package jp.co.soramitsu.iroha2.testcontainers

import java.util.function.Consumer
import jp.co.soramitsu.iroha2.generated.genesis.RawGenesisBlock
import jp.co.soramitsu.iroha2.testcontainers.genesis.Genesis
import org.slf4j.LoggerFactory.getLogger
import org.testcontainers.containers.Network
import org.testcontainers.containers.Network.newNetwork
import org.testcontainers.containers.output.OutputFrame
import org.testcontainers.containers.output.Slf4jLogConsumer

class IrohaConfig (
    var networkToJoin: Network = newNetwork(),
    var logConsumer: Consumer<OutputFrame> = Slf4jLogConsumer(getLogger(IrohaContainer::class.java)),
    var genesis: Genesis = Genesis(RawGenesisBlock(mutableListOf())),
    var shouldCloseNetwork: Boolean = true,
    var maxLogLevel: MaxLogLevel = MaxLogLevel.INFO
)

enum class MaxLogLevel {
    ERROR, WARN, INFO, DEBUG, TRACE
}