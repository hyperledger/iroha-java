package jp.co.soramitsu.iroha2.client.balancing

import jp.co.soramitsu.iroha2.model.IrohaUrls
import java.net.URL

/**
 * Round-robin load balancing strategy
 */
open class RoundRobinStrategy(private val urls: List<IrohaUrls>) : BalancingStrategy {

    private var lastRequestedPeerIdx: Int? = null

    override suspend fun getTelemetryUrl(): URL = getUrls().telemetryUrl

    override suspend fun getApiUrl(): URL = getUrls().apiUrl

    override suspend fun getPeerUrl(): URL = getUrls().peerUrl

    private fun getUrls() = when (lastRequestedPeerIdx) {
        null -> urls.first().also { lastRequestedPeerIdx = 0 }
        else -> {
            lastRequestedPeerIdx = when (lastRequestedPeerIdx) {
                urls.size - 1 -> 0
                else -> lastRequestedPeerIdx!! + 1
            }
            urls[lastRequestedPeerIdx!!]
        }
    }
}
