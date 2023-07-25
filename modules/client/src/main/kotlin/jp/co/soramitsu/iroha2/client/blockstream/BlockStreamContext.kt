package jp.co.soramitsu.iroha2.client.blockstream

import io.ktor.client.HttpClient
import jp.co.soramitsu.iroha2.generated.VersionedBlockMessage
import java.net.URL

data class BlockStreamContext(
    val apiUrl: URL,
    val client: HttpClient,
    val from: Long = 1,
    val count: Int? = null,
    val onBlock: suspend (block: VersionedBlockMessage) -> Any,
    val onFailure: suspend (t: Throwable) -> Unit,
    val closeIf: suspend (block: VersionedBlockMessage) -> Boolean,
)
