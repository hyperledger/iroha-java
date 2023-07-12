package jp.co.soramitsu.iroha2.client.balancing

import java.net.URL

interface BalancingStrategy {
    fun getTelemetryUrl(): URL

    fun getApiUrl(): URL

    fun getPeerUrl(): URL
}
