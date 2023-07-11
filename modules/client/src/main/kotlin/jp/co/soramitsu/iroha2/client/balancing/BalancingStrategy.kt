package jp.co.soramitsu.iroha2.client.balancing

import java.net.URL

interface BalancingStrategy {
    suspend fun getTelemetryUrl(): URL

    suspend fun getApiUrl(): URL
}
