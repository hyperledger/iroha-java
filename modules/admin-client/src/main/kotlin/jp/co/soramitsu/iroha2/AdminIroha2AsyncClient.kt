package jp.co.soramitsu.iroha2

import kotlinx.coroutines.future.future
import java.net.URL

/**
 * Extension of [AdminIroha2Client] for Java. Functionality for monitoring peers and configuration support
 */
@Suppress("unused")
class AdminIroha2AsyncClient @JvmOverloads constructor(
    peerUrls: MutableList<URL>,
    log: Boolean = false,
    credentials: String? = null,
) : AdminIroha2Client(peerUrls, log, credentials) {

    /**
     * Send health check request
     */
    fun healthAsync() = future { health() }

    /**
     * Send status check request
     */
    fun statusAsync() = future { status() }

    /**
     * Send metrics request
     */
    fun metricsAsync() = future { metrics() }

    /**
     * Request current configuration of the peer
     */
    fun getConfigsAsync() = future { getConfigs() }

    /**
     * Request description of a configuration property
     */
    fun describeConfigAsync(fieldValue: Collection<String>) = future { describeConfig(fieldValue) }
}
