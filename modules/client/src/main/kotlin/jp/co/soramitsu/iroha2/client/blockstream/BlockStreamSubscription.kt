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
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.math.BigInteger
import java.util.UUID
import kotlin.coroutines.CoroutineContext

class BlockStreamSubscription private constructor(private val context: BlockStreamContext) : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()

    private val logger = LoggerFactory.getLogger(javaClass)

    private val source: MutableMap<UUID, BlockStreamStorage> = mutableMapOf()
    private var initialStorageId: UUID? = null
    private var stopped: Boolean = false
    private val jobs: MutableList<Job> = mutableListOf()

    fun subscribe(): Pair<UUID, BlockStreamSubscription> {
        if (initialStorageId == null) {
            initialStorageId = expand(context.closeIf, context.onFailure, context.onBlock)
            launch { run() }
        }
        logger.info("Subscribed to block stream")
        return initialStorageId!! to getInstance(context)
    }

    fun unsubscribe() {
        stopped = true
        jobs.forEach { it.cancel() }
        logger.info("Unsubscribed from block stream")
    }

    fun expand(
        closeIf: (suspend (block: VersionedBlockMessage) -> Boolean)? = null,
        onFailure: (suspend (t: Throwable) -> Unit)? = null,
        onBlock: suspend (block: VersionedBlockMessage) -> Any,
    ): UUID {
        val storage = BlockStreamStorage(onBlock, closeIf, onFailure ?: context.onFailure, Channel())
        source[storage.getId()] = storage
        logger.debug("Block stream subscription has been expanded. Number of channels is ${source.size}")

        return storage.getId()
    }

    fun <T> receive(actionId: UUID, collector: FlowCollector<T>) = launch {
        receiveBlocking<T>(actionId).collect(collector)
    }.also { jobs.add(it) }

    fun <T> receiveBlocking(actionId: UUID): Flow<T> {
        val storage = source[actionId] ?: throw IrohaSdkException("Flow#$actionId not found")
        return storage.channel.cast<Channel<T>>().receiveAsFlow().catch { storage.onFailure(it) }
    }

    private suspend fun run() {
        var counter = 0
        val request = VersionedBlockSubscriptionRequest.V1(BlockSubscriptionRequest(BigInteger.valueOf(context.from)))

        context.client.webSocket(
            host = context.apiUrl.host,
            port = context.apiUrl.port,
            path = Iroha2Client.WS_ENDPOINT_BLOCK_STREAM,
        ) {
            logger.debug("WebSocket opened")
            send(VersionedBlockSubscriptionRequest.encode(request).toFrame())

            for (frame in incoming) {
                if (stopped) {
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
                if (++counter == context.count) {
                    logger.debug("Block stream channels are closing")
                    source.closeAndClear()
                }
            }
        }
    }

    private fun MutableMap<*, BlockStreamStorage>.closeAndClear() {
        this.values.forEach { it.channel.close() }
        this.clear()
    }

    companion object : SingletonHolder<BlockStreamSubscription, BlockStreamContext>(::BlockStreamSubscription)
}
