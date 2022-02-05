package jp.co.soramitsu.iroha2

import java.time.Duration

class PeerStatus(val peers: Int, val blocks: Long, val txs: Long, val uptime: Duration)
