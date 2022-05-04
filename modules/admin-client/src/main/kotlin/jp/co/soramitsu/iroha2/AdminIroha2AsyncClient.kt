package jp.co.soramitsu.iroha2

import kotlinx.coroutines.future.future
import java.net.URL

/**
 * Extension of [AdminIroha2Client] for usage in Java
 */
@Suppress("unused")
class AdminIroha2AsyncClient @JvmOverloads constructor(
    peerUrl: URL,
    telemetryUrl: URL = URL(
        peerUrl.protocol,
        peerUrl.host,
        DEFAULT_TELEMETRY_PORT,
        peerUrl.file
    ),
    log: Boolean = false
) : AdminIroha2Client(peerUrl, telemetryUrl, log) {

    @JvmOverloads
    constructor(
        peerUrl: String,
        telemetryUrl: String,
        log: Boolean = false,
    ) : this(URL(peerUrl), URL(telemetryUrl), log)

    fun healthAsync() = future { health() }

    fun statusAsync() = future { status() }

    fun metricsAsync() = future { metrics() }

    fun getConfigsAsync() = future { getConfigs() }

    fun describeConfigAsync(fieldValue: Collection<String>) = future { describeConfig(fieldValue) }
}
