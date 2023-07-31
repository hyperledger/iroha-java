package jp.co.soramitsu.iroha2.client.blockstream

import io.ktor.client.HttpClient
import java.net.URL

data class BlockStreamContext(
    val apiUrl: URL,
    val client: HttpClient,
    val from: Long = 1,
    val storage: BlockStreamStorage,
    val onClose: () -> Unit,
)
