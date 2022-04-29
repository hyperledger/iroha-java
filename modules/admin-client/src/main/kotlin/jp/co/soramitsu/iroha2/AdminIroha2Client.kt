package jp.co.soramitsu.iroha2

import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.future.future
import java.net.URL

/**
 * Enhancement of [Iroha2Client] for peer's monitoring and configuration support
 */
@Suppress("unused")
open class AdminIroha2Client(
    peerUrl: URL,
    open var telemetryUrl: URL = URL(
        peerUrl.protocol,
        peerUrl.host,
        DEFAULT_TELEMETRY_PORT,
        peerUrl.file
    ),
    log: Boolean = false
) : Iroha2Client(peerUrl, log) {

    /**
     * Sends metrics request
     */
    suspend fun metrics(): String {
        return client
            .get<HttpResponse>("$telemetryUrl$METRICS_ENDPOINT")
            .receive()
    }

    /**
     * Sends health check request
     */
    suspend fun health(): Int {
        return client
            .get<HttpResponse>("$peerUrl$HEALTH_ENDPOINT")
            .status.value
    }

    /**
     * Sends status check request
     */
    suspend fun status(): PeerStatus {
        return client
            .get<HttpResponse>("$telemetryUrl$STATUS_ENDPOINT")
            .receive()
    }

    /**
     * Request current configuration of the peer
     */
    suspend fun getConfigs(): Map<String, *> {
        return config(ConfigurationFieldType.Value)
    }

    /**
     * Request description of some configuration property
     */
    suspend fun describeConfig(fieldValue: Collection<String>): String {
        if (fieldValue.isEmpty()) {
            throw IrohaClientException("At least one config property name must be provided")
        }
        return config(mapOf(ConfigurationFieldType.Docs to fieldValue))
    }

    suspend fun describeConfig(vararg fieldValue: String): String = describeConfig(fieldValue.asList())

    fun healthAsync() = scope.future { health() }

    fun statusAsync() = scope.future { status() }

    fun metricsAsync() = scope.future { metrics() }

    fun getConfigsAsync() = scope.future { getConfigs() }

    fun describeConfigAsync(fieldValue: Collection<String>) = scope.future { describeConfig(fieldValue) }

    private suspend inline fun <reified T, B> config(body: B): T {
        val response: HttpResponse = client.get("$peerUrl$CONFIGURATION_ENDPOINT") {
            contentType(ContentType.Application.Json)
            this.body = body!!
        }
        return response.receive()
    }

    enum class ConfigurationFieldType { Value, Docs }
}
