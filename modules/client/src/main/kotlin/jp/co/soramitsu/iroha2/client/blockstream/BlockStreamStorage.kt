package jp.co.soramitsu.iroha2.client.blockstream

import jp.co.soramitsu.iroha2.generated.BlockMessage
import kotlinx.coroutines.channels.Channel
import java.util.UUID

data class BlockStreamStorage(
    val onBlock: (block: BlockMessage) -> Any,
    val cancelIf: (suspend (block: BlockMessage) -> Boolean)? = null,
    val onFailure: (suspend (t: Throwable) -> Unit)? = null,
) {
    val channel = lazy { Channel<Any>() }

    val id: UUID = UUID.randomUUID()
}
