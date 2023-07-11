package jp.co.soramitsu.iroha2.client.balancing

import jp.co.soramitsu.iroha2.model.IrohaUrls
import java.net.URL

open class WithoutBalancingStrategy(private val urls: List<IrohaUrls>) : BalancingStrategy {
    override suspend fun getTelemetryUrl(): URL = urls.first().telemetryUrl

    override suspend fun getApiUrl(): URL = urls.first().apiUrl
}
