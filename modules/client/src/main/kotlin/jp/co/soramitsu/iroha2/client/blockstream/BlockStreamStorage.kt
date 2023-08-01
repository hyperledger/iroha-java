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

class BlockStreamStorageBuilder(builder: BlockStreamStorageBuilder.() -> Unit = {}) {

    private var onBlock: ((block: VersionedBlockMessage) -> Any)? = null
    private var cancelIf: (suspend (block: VersionedBlockMessage) -> Boolean)? = null
    private var onFailure: (suspend (t: Throwable) -> Unit)? = null

    init {
        builder(this)
    }

    fun build(): BlockStreamStorage {
        if (onBlock == null) {
            throw IllegalArgumentException("Field onBlock must be initialized")
        }
        return BlockStreamStorage(
            onBlock!!,
            cancelIf,
            onFailure,
        )
    }

    fun onBlock(onBlock: (block: VersionedBlockMessage) -> Any) {
        this.apply {
            this.onBlock = onBlock
        }
    }

    fun cancelIf(cancelIf: (suspend (block: VersionedBlockMessage) -> Boolean)? = null) {
        this.apply {
            this.cancelIf = cancelIf
        }
    }

    fun onFailure(onFailure: (suspend (t: Throwable) -> Unit)? = null) {
        this.apply {
            this.onFailure = onFailure
        }
    }
}
