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
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigInteger
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.CoroutineContext

open class BlockStreamSubscription private constructor(private val context: BlockStreamContext) : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    open val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val source: ConcurrentHashMap<UUID, BlockStreamStorage> = ConcurrentHashMap()
    private val running: AtomicBoolean = AtomicBoolean(false)
    private val stopped: AtomicBoolean = AtomicBoolean(false)

    private lateinit var runJob: Job

    init {
        subscribe(context.storages)
    }

    fun start(): BlockStreamSubscription {
        if (!running.getAndSet(true)) {
            runJob = run()
        }
        return this
    }

    fun subscribe(storage: BlockStreamStorage) = subscribe(listOf(storage))

    @Synchronized
    fun subscribe(storages: Iterable<BlockStreamStorage>) {
        logger.debug("Expanding subscription with ${storages.count()} storages")
        storages.forEach {
            source.putIfAbsent(it.id, it)?.run {
                logger.warn("Given id $id is already present in the subscription, not being added repeatedly")
            }
        }
        logger.debug("Block stream subscription has been expanded. Updated number of channels is ${source.size}")
    }

    fun <T> subscribeAndReceive(storage: BlockStreamStorage, collector: FlowCollector<T>) {
        subscribe(storage)
        receive(storage.id, collector)
    }

    fun <T> receive(actionId: UUID, collector: FlowCollector<T>) = launch {
        receive<T>(actionId).collect(collector)
    }

    fun <T> receive(actionId: UUID): Flow<T> {
        val storage = source[actionId] ?: run {
            logger.warn("Source flow ids: ${source.keys.joinToString(", ") { it.toString() }}")
            throw IrohaSdkException("Flow#$actionId not found")
        }
        return storage.channel.value.cast<Channel<T>>().receiveAsFlow().catch {
            storage.onFailure?.let { method -> method(it) }
        }
    }

    suspend fun stop() {
        if (!stopped.getAndSet(true)) {
            runJob.cancelAndJoin()
            destroy() // singleton instance of subscription
            logger.info("Unsubscribed from block stream, closing")
        }
        logger.warn("Block stream is already closed")
    }

    fun stopBlocking() = runBlocking { stop() }

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
                    logger.debug("Executing {} action", storage.id)
                    val result = storage.onBlock(block)
                    logger.debug("{} action result: {}", storage.id, result)
                    storage.channel.value.send(result)

                    if (storage.cancelIf?.let { it(block) } == true) {
                        logger.debug("Block stream channel#{} is closing", id)
                        storage.channel.value.close()
                        source.remove(storage.id)
                    }
                }
            }
        }
        context.onClose()
    }

    private fun MutableMap<*, BlockStreamStorage>.closeAndClear() {
        this.values.forEach { it.channel.value.close() }
        this.clear()
    }

    companion object : SingletonHolder<BlockStreamSubscription, BlockStreamContext>(::BlockStreamSubscription)
}
