package jp.co.soramitsu.iroha2.client.balancing

import jp.co.soramitsu.iroha2.model.IrohaUrls
import java.net.URL

open class RoundRobinStrategy(private val urls: List<IrohaUrls>) : BalancingStrategy {

    private var lastRequestedPeerIdx: Int? = null

    // Round-robin load balancing
    override suspend fun getTelemetryUrl(): URL = getUrls().telemetryUrl

    // Round-robin load balancing
    override suspend fun getApiUrl(): URL = getUrls().apiUrl

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
