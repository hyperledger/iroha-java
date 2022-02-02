package jp.co.soramitsu.iroha2

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import java.io.InputStream
import java.net.URL
import org.hawkular.agent.prometheus.text.TextPrometheusMetricDataParser

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

    open var mapper: ObjectMapper = ObjectMapper()

    /**
     * Sends metrics request
     */
    suspend fun metrics(): Map<String, *> {
        val response: InputStream = client.value
            .get<HttpResponse>("$telemetryUrl$METRICS_ENDPOINT")
            .receive()

        return TextPrometheusMetricDataParser(response)
            .let { mapper.convertValue(it.parse(), Map::class.java) }
            .cast()
    }

    /**
     * Sends health check request
     */
    suspend fun health(): Int {
        return client.value
            .get<HttpResponse>("$peerUrl$HEALTH_ENDPOINT")
            .status.value
    }

    /**
     * Sends status check request
     */
    suspend fun status(): Map<String, *> {
        return client.value
            .get<HttpResponse>("$telemetryUrl$STATUS_ENDPOINT")
            .receive()
    }

    /**
     * Sends value configuration request
     */
    suspend fun valueConfig(fieldValue: Collection<String>? = null): Map<String, *> {
        return config(ConfigurationFieldType.VALUE, fieldValue)
    }

    /**
     * Sends docs configuration request
     */
    suspend fun docsConfig(fieldValue: Collection<String>? = null): String {
        return config(ConfigurationFieldType.DOCS, fieldValue)
    }

    private suspend inline fun <reified T> config(
        fieldName: ConfigurationFieldType,
        fieldValue: Collection<String>? = null
    ): T {
        val response: HttpResponse = client.value.get("$peerUrl$CONFIGURATION_ENDPOINT") {
            contentType(ContentType.Application.Json)
            body = mapOf(fieldName.fieldName to fieldValue)
        }
        return response.receive()
    }
}