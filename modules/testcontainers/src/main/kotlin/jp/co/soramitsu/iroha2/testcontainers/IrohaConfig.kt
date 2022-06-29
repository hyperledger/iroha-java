package jp.co.soramitsu.iroha2.testcontainers

import jp.co.soramitsu.iroha2.DEFAULT_API_PORT
import jp.co.soramitsu.iroha2.DEFAULT_P2P_PORT
import jp.co.soramitsu.iroha2.DEFAULT_TELEMETRY_PORT
import jp.co.soramitsu.iroha2.Genesis
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.datamodel.peer.PeerId
import org.slf4j.LoggerFactory.getLogger
import org.testcontainers.containers.Network
import org.testcontainers.containers.Network.newNetwork
import org.testcontainers.containers.output.OutputFrame
import org.testcontainers.containers.output.Slf4jLogConsumer
import java.security.KeyPair
import java.util.function.Consumer

/**
 * Iroha configuration
 */
class IrohaConfig(
    var networkToJoin: Network = newNetwork(),
    var logConsumer: Consumer<OutputFrame> = Slf4jLogConsumer(getLogger(IrohaContainer::class.java)),
    var genesis: Genesis = Genesis.getEmpty(),
    var imageTag: String = IrohaContainer.DEFAULT_IMAGE_TAG,
    var alias: String = IrohaContainer.NETWORK_ALIAS + DEFAULT_P2P_PORT,
    var keyPair: KeyPair = generateKeyPair(),
    var trustedPeers: List<PeerId> = listOf(),
    var ports: List<Int> = listOf(DEFAULT_P2P_PORT, DEFAULT_API_PORT, DEFAULT_TELEMETRY_PORT),
    var shouldCloseNetwork: Boolean = true,
    var waitStrategy: Boolean = true,
    var submitGenesis: Boolean = true
) {
    companion object {
        const val P2P_PORT_IDX = 0
        const val API_PORT_IDX = 1
        const val TELEMETRY_PORT_IDX = 2
    }
}
