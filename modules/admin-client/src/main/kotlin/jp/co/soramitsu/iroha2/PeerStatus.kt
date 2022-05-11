package jp.co.soramitsu.iroha2

import java.time.Duration

data class PeerStatus(
    val peers: Int,
    val blocks: Long,
    val txs_accepted: Long,
    val txs_rejected: Long,
    val uptime: Duration,
    val view_changes: Long
)
