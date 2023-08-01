package jp.co.soramitsu.iroha2.client.blockstream

import jp.co.soramitsu.iroha2.generated.VersionedBlockMessage
import kotlinx.coroutines.channels.Channel
import java.lang.IllegalArgumentException
import java.util.UUID

data class BlockStreamStorage(
    val onBlock: (block: VersionedBlockMessage) -> Any,
    val cancelIf: (suspend (block: VersionedBlockMessage) -> Boolean)? = null,
    val onFailure: (suspend (t: Throwable) -> Unit)? = null,
) {
    val channel = lazy { Channel<Any>() }

    val id: UUID = UUID.randomUUID()
}
