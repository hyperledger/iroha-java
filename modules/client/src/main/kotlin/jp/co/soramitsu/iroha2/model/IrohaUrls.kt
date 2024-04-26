package jp.co.soramitsu.iroha2.model

import java.net.URL

data class IrohaUrls(val apiUrl: URL, val telemetryUrl: URL, val peerUrl: URL) {
    constructor(
        apiUrl: String,
        telemetryUrl: String,
        peerUrl: String,
    ) : this(URL(apiUrl), URL(telemetryUrl), URL(peerUrl))
}
