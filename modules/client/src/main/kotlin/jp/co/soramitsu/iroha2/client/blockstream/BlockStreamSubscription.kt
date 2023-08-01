package jp.co.soramitsu.iroha2.client.blockstream

import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.close
import io.ktor.websocket.readBytes
import jp.co.soramitsu.iroha2.IrohaSdkException
import jp.co.soramitsu.iroha2.SingletonHolder
import jp.co.soramitsu.iroha2.cast
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.BlockSubscriptionRequest
import jp.co.soramitsu.iroha2.generated.VersionedBlockMessage
import jp.co.soramitsu.iroha2.generated.VersionedBlockSubscriptionRequest
import jp.co.soramitsu.iroha2.toFrame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.slf4j.LoggerFactory
import java.math.BigInteger
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.CoroutineContext

class BlockStreamSubscription private constructor(private val context: BlockStreamContext) : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    private val logger = LoggerFactory.getLogger(javaClass)
    private val mutex = Mutex()

    private val source: ConcurrentHashMap<UUID, BlockStreamStorage> = ConcurrentHashMap()
    private var stopped: AtomicBoolean = AtomicBoolean(false)
    private var initialized: AtomicBoolean = AtomicBoolean(false)

    suspend fun subscribe(): Pair<UUID, BlockStreamSubscription> = mutex.withLock {
        return when (initialized.get()) {
            false -> {
                initialized.set(true)
                subscribe(
                    context.storage.closeIf,
                    context.storage.onFailure,
                    context.storage.onBlock,
                ) to getInstance(context).also {
                    run()
                }
            }
            true -> source.keys().toList().first() to getInstance(context)
        }
    }

    @JvmOverloads
    suspend fun subscribe(
        closeIf: (suspend (block: VersionedBlockMessage) -> Boolean)? = null,
        onFailure: (suspend (t: Throwable) -> Unit)? = null,
        onBlock: (block: VersionedBlockMessage) -> Any,
    ): UUID = mutex.withLock {
        val storage = BlockStreamStorage(onBlock, closeIf, onFailure ?: context.storage.onFailure)
        storage.channel = Channel()
        source[storage.getId()] = storage
        logger.debug("Block stream subscription has been expanded. Number of channels is ${source.size}")

        return storage.getId()
    }

    @JvmOverloads
    suspend fun <T> subscribeAndReceive(
        closeIf: (suspend (block: VersionedBlockMessage) -> Boolean)? = null,
        onFailure: (suspend (t: Throwable) -> Unit)? = null,
        onBlock: (block: VersionedBlockMessage) -> Any,
        collector: FlowCollector<T>,
    ) = subscribe(closeIf, onFailure, onBlock).let { id ->
        id to receive(id, collector)
    }

    fun <T> receive(actionId: UUID, collector: FlowCollector<T>) = launch {
        receiveBlocking<T>(actionId).collect(collector)
    }

    fun <T> receiveBlocking(actionId: UUID): Flow<T> {
        val storage = source[actionId] ?: throw IrohaSdkException("Flow#$actionId not found")
        return storage.channel.cast<Channel<T>>().receiveAsFlow().catch { storage.onFailure(it) }
    }

    fun unsubscribe() {
        if (stopped.getAndSet(true)) {
            destroy() // singleton instance of subscription
            logger.info("Unsubscribed from block stream")
        }
    }

    private fun run() = launch {
        val request = VersionedBlockSubscriptionRequest.V1(BlockSubscriptionRequest(BigInteger.valueOf(context.from)))

        context.client.webSocket(
            host = context.apiUrl.host,
            port = context.apiUrl.port,
            path = Iroha2Client.WS_ENDPOINT_BLOCK_STREAM,
        ) {
            logger.debug("WebSocket opened")
            send(VersionedBlockSubscriptionRequest.encode(request).toFrame())

            for (frame in incoming) {
                if (stopped.get()) {
                    this.close()
                    source.closeAndClear()
                    return@webSocket
                }
                logger.debug("Received frame: {}", frame)

                val block = VersionedBlockMessage.decode(frame.readBytes())
                source.forEach { (id, storage) ->
                    val result = storage.onBlock(block)
                    logger.debug("{} action result: {}", storage.getId(), result)
                    storage.channel.send(result)

                    if (storage.closeIf?.let { it(block) } == true) {
                        logger.debug("Block stream channel#{} is closing", id)
                        storage.channel.close()
                        source.remove(storage.getId())
                    }
                }
            }
        }
        context.onClose()
    }

    private fun MutableMap<*, BlockStreamStorage>.closeAndClear() {
        this.values.forEach { it.channel.close() }
        this.clear()
    }

    companion object : SingletonHolder<BlockStreamSubscription, BlockStreamContext>(::BlockStreamSubscription)
}
