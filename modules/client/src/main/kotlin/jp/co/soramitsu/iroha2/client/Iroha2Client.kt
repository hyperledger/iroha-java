@file:Suppress("UNCHECKED_CAST")

package jp.co.soramitsu.iroha2.client

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.module.SimpleModule
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.jackson.jackson
import io.ktor.websocket.Frame
import io.ktor.websocket.readBytes
import jp.co.soramitsu.iroha2.IrohaClientException
import jp.co.soramitsu.iroha2.TransactionRejectedException
import jp.co.soramitsu.iroha2.WebSocketProtocolException
import jp.co.soramitsu.iroha2.cast
import jp.co.soramitsu.iroha2.client.balancing.RoundRobinStrategy
import jp.co.soramitsu.iroha2.extract
import jp.co.soramitsu.iroha2.generated.BlockRejectionReason
import jp.co.soramitsu.iroha2.generated.BlockSubscriptionRequest
import jp.co.soramitsu.iroha2.generated.Event
import jp.co.soramitsu.iroha2.generated.EventMessage
import jp.co.soramitsu.iroha2.generated.EventSubscriptionRequest
import jp.co.soramitsu.iroha2.generated.Pagination
import jp.co.soramitsu.iroha2.generated.PipelineEntityKind
import jp.co.soramitsu.iroha2.generated.PipelineRejectionReason
import jp.co.soramitsu.iroha2.generated.PipelineStatus
import jp.co.soramitsu.iroha2.generated.Sorting
import jp.co.soramitsu.iroha2.generated.TransactionRejectionReason
import jp.co.soramitsu.iroha2.generated.VersionedBlockMessage
import jp.co.soramitsu.iroha2.generated.VersionedBlockSubscriptionRequest
import jp.co.soramitsu.iroha2.generated.VersionedEventMessage
import jp.co.soramitsu.iroha2.generated.VersionedEventSubscriptionRequest
import jp.co.soramitsu.iroha2.generated.VersionedPaginatedQueryResult
import jp.co.soramitsu.iroha2.generated.VersionedSignedQuery
import jp.co.soramitsu.iroha2.generated.VersionedSignedTransaction
import jp.co.soramitsu.iroha2.hash
import jp.co.soramitsu.iroha2.model.IrohaUrls
import jp.co.soramitsu.iroha2.model.Page
import jp.co.soramitsu.iroha2.query.QueryAndExtractor
import jp.co.soramitsu.iroha2.toFrame
import jp.co.soramitsu.iroha2.toHex
import jp.co.soramitsu.iroha2.transaction.Filters
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigInteger
import java.net.URL
import java.time.Duration
import kotlin.coroutines.CoroutineContext

/**
 * Iroha2 Client
 *
 * @param credentials <username>:<password>
 */
@Suppress("unused")
open class Iroha2Client(
    open val urls: List<IrohaUrls>,
    open val log: Boolean = false,
    open val credentials: String? = null,
    open val eventReadTimeoutInMills: Long = 250,
    open val eventReadMaxAttempts: Int = 10,
    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob(),
) : AutoCloseable, CoroutineScope, RoundRobinStrategy(urls) {

    constructor(
        url: IrohaUrls,
        log: Boolean = false,
        credentials: String? = null,
        eventReadTimeoutInMills: Long = 250,
        eventReadMaxAttempts: Int = 10,
    ) : this(mutableListOf(url), log, credentials, eventReadTimeoutInMills, eventReadMaxAttempts)

    constructor(
        apiUrl: URL,
        telemetryUrl: URL,
        peerUrl: URL,
        log: Boolean = false,
        credentials: String? = null,
        eventReadTimeoutInMills: Long = 250,
        eventReadMaxAttempts: Int = 10,
    ) : this(IrohaUrls(apiUrl, telemetryUrl, peerUrl), log, credentials, eventReadTimeoutInMills, eventReadMaxAttempts)

    constructor(
        apiUrl: String,
        telemetryUrl: String,
        peerUrl: String,
        log: Boolean = true,
        credentials: String? = null,
        eventReadTimeoutInMills: Long = 250,
        eventReadMaxAttempts: Int = 10,
    ) : this(
        URL(apiUrl),
        URL(telemetryUrl),
        URL(peerUrl),
        log,
        credentials,
        eventReadTimeoutInMills,
        eventReadMaxAttempts,
    )

    companion object {
        const val TRANSACTION_ENDPOINT = "/transaction"
        const val QUERY_ENDPOINT = "/query"
        const val WS_ENDPOINT = "/events"
        const val WS_ENDPOINT_BLOCK_STREAM = "/block/stream"
        const val HEALTH_ENDPOINT = "/health"
        const val STATUS_ENDPOINT = "/status"
        const val SCHEMA_ENDPOINT = "/schema"
        const val METRICS_ENDPOINT = "/metrics"
        const val CONFIGURATION_ENDPOINT = "/configuration"
    }

    open val logger: Logger = LoggerFactory.getLogger(javaClass)

    open val client by lazy {
        HttpClient(CIO) {
            expectSuccess = true
            if (log) {
                install(Logging)
            }
            install(WebSockets)
            install(ContentNegotiation) {
                jackson {
                    registerModule(
                        SimpleModule().apply {
                            addDeserializer(Duration::class.java, DurationDeserializer)
                        },
                    )
                }
            }
            credentials?.split(":")?.takeIf { it.size == 2 }?.let { pair ->
                install(Auth) {
                    basic {
                        credentials {
                            BasicAuthCredentials(pair[0], pair[1])
                        }
                    }
                }
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    val status = exception
                        .takeIf { it is ClientRequestException }
                        ?.cast<ClientRequestException>()
                        ?.response?.status
                    throw IrohaClientException(cause = exception, status = status)
                }
            }
        }
    }

    /**
     * Send a request to Iroha2 and extract payload.
     */
    suspend fun <T> sendQuery(queryAndExtractor: QueryAndExtractor<T>): T {
        val page = sendQuery(queryAndExtractor, null)
        return page.data
    }

    /**
     * Send a request to Iroha2 and extract paginated payload
     */
    suspend fun <T> sendQuery(
        queryAndExtractor: QueryAndExtractor<T>,
        page: Pagination? = null,
        sorting: Sorting? = null,
    ): Page<T> {
        logger.debug("Sending query")
        val response: HttpResponse = client.post("${getApiUrl()}$QUERY_ENDPOINT") {
            setBody(VersionedSignedQuery.encode(queryAndExtractor.query))
            page?.also {
                parameter("start", it.start)
                parameter("limit", it.limit)
            }
            sorting?.also {
                parameter("sort_by_metadata_key", it.sortByMetadataKey?.string)
            }
        }
        return response.body<ByteArray>()
            .let { VersionedPaginatedQueryResult.decode(it) }
            .let { queryAndExtractor.resultExtractor.extract(it) }
    }

    /**
     * Send a transaction to an Iroha peer without waiting for the final transaction status (committed or rejected).
     *
     * With this method, the state of the transaction is not tracked after the peer responses with 2xx status code,
     * which means that the peer accepted the transaction and the transaction passed the stateless validation.
     */
    suspend fun fireAndForget(transaction: TransactionBuilder.() -> VersionedSignedTransaction): ByteArray {
        val signedTransaction = transaction(TransactionBuilder.builder())
        val hash = signedTransaction.hash()
        logger.debug("Sending transaction with hash {}", hash.toHex())
        val response: HttpResponse = client.post("${getApiUrl()}$TRANSACTION_ENDPOINT") {
            setBody(VersionedSignedTransaction.encode(signedTransaction))
        }
        response.body<Unit>()
        return hash
    }

    /**
     * Send a transaction to an Iroha peer and wait until it is committed or rejected.
     */
    suspend fun sendTransaction(
        transaction: TransactionBuilder.() -> VersionedSignedTransaction,
    ): CompletableDeferred<ByteArray> = coroutineScope {
        val signedTransaction = transaction(TransactionBuilder())

        val lock = Mutex(locked = true)
        subscribeToTransactionStatus(signedTransaction.hash()) {
            lock.unlock() // 2. unlock after subscription
        }.also {
            lock.lock() // 1. waiting for unlock
            fireAndForget { signedTransaction }
        }
    }

    fun subscribeToBlockStream(
        from: Long = 1,
        count: Int,
    ): BlockStreamSubscription = subscribeToBlockStream(from, count, { block -> block })

    /**
     * Subscribe to block streaming
     * @param from - block number to start from
     * @param count - how many blocks to get before closing the channel
     * @param onBlock - code which will be invoked after a new block received
     * @param onFailure - code which will be invoked on exception throwing
     * @param closeIf - if the condition returns true then the channel will be closed
     */
    @JvmOverloads
    fun subscribeToBlockStream(
        from: Long = 1,
        count: Int? = null,
        onBlock: suspend (block: VersionedBlockMessage) -> Any,
        onFailure: suspend (t: Throwable) -> Unit = { throwable ->
            logger.error("Block stream was closed with an exception: {}", throwable.message)
        },
        closeIf: suspend (block: VersionedBlockMessage) -> Boolean = { false },
    ): BlockStreamSubscription {
        val context = BlockStreamContext(getApiUrl(), client, from, count, onBlock, onFailure, closeIf)
        return BlockStreamSubscription.getInstance(context).consume()
    }

    /**
     * Subscribe to track the transaction status
     */
    fun subscribeToTransactionStatus(hash: ByteArray) = subscribeToTransactionStatus(hash, null)

    /**
     * @param hash - Signed transaction hash
     * @param afterSubscription - Expression that is invoked after subscription
     */
    private fun subscribeToTransactionStatus(
        hash: ByteArray,
        afterSubscription: (() -> Unit)? = null,
    ): CompletableDeferred<ByteArray> {
        val hexHash = hash.toHex()
        logger.debug("Creating subscription to transaction status: {}", hexHash)

        val subscriptionRequest = eventSubscriberMessageOf(hash)
        val payload = VersionedEventSubscriptionRequest.encode(subscriptionRequest)
        val result: CompletableDeferred<ByteArray> = CompletableDeferred()
        val apiUrl = getApiUrl()

        launch {
            client.webSocket(
                host = apiUrl.host,
                port = apiUrl.port,
                path = WS_ENDPOINT,
            ) {
                logger.debug("WebSocket opened")
                send(payload.toFrame())

                afterSubscription?.invoke()
                logger.debug("Subscription was accepted by peer")

                for (i in 1..eventReadMaxAttempts) {
                    try {
                        val processed = pipelineEventProcess(readMessage(incoming.receive()), hash, hexHash)
                        if (processed != null) {
                            result.complete(processed)
                            break
                        }
                    } catch (e: TransactionRejectedException) {
                        result.completeExceptionally(e)
                        break
                    }
                    delay(eventReadTimeoutInMills)
                }
            }
        }
        return result
    }

    private fun pipelineEventProcess(
        eventPublisherMessage: EventMessage,
        hash: ByteArray,
        hexHash: String,
    ): ByteArray? {
        when (val event = eventPublisherMessage.event) {
            is Event.Pipeline -> {
                val eventInner = event.pipelineEvent
                if (eventInner.entityKind is PipelineEntityKind.Transaction && hash.contentEquals(eventInner.hash.arrayOfU8)) {
                    when (val status = eventInner.status) {
                        is PipelineStatus.Committed -> {
                            logger.debug("Transaction {} committed", hexHash)
                            return hash
                        }

                        is PipelineStatus.Rejected -> {
                            val reason = status.pipelineRejectionReason.message()
                            logger.error("Transaction {} was rejected by reason: `{}`", hexHash, reason)
                            throw TransactionRejectedException("Transaction rejected with reason '$reason'")
                        }

                        is PipelineStatus.Validating -> logger.debug("Transaction {} is validating", hexHash)
                    }
                }
                return null
            }

            else -> throw WebSocketProtocolException(
                "Expected message with type ${Event.Pipeline::class.qualifiedName}, " +
                    "but was ${event::class.qualifiedName}",
            )
        }
    }

    /**
     * Extract the rejection reason
     */
    private fun PipelineRejectionReason.message(): String = when (this) {
        is PipelineRejectionReason.Block -> when (this.blockRejectionReason) {
            is BlockRejectionReason.ConsensusBlockRejection -> "Block was rejected during consensus"
        }

        is PipelineRejectionReason.Transaction -> when (val reason = this.transactionRejectionReason) {
            is TransactionRejectionReason.InstructionExecution -> {
                val details = reason.instructionExecutionFail
                "Failed: `${details.reason}` during execution of instruction: ${details.instruction::class.qualifiedName}"
            }

            is TransactionRejectionReason.UnexpectedGenesisAccountSignature ->
                "Genesis account can sign only transactions in the genesis block"

            is TransactionRejectionReason.UnsatisfiedSignatureCondition ->
                reason.unsatisfiedSignatureConditionFail.reason

            is TransactionRejectionReason.WasmExecution -> reason.wasmExecutionFail.reason
            is TransactionRejectionReason.LimitCheck -> reason.transactionLimitError.reason
            is TransactionRejectionReason.Expired -> reason.transactionExpired.timeToLiveMs.toString()
            is TransactionRejectionReason.AccountDoesNotExist -> reason.findError.extract()
            is TransactionRejectionReason.Validation -> reason.validationFail.toString()
        }
    }

    /**
     * Read the message from the frame
     */
    private fun readMessage(frame: Frame): EventMessage = when (frame) {
        is Frame.Binary -> {
            when (val versionedMessage = frame.readBytes().let { VersionedEventMessage.decode(it) }) {
                is VersionedEventMessage.V1 -> versionedMessage.eventMessage
                else -> throw WebSocketProtocolException(
                    "Expected `${VersionedEventSubscriptionRequest.V1::class.qualifiedName}`, but was `${versionedMessage::class.qualifiedName}`",
                )
            }
        }

        else -> throw WebSocketProtocolException(
            "Expected server will `${Frame.Binary::class.qualifiedName}` frame, but was `${frame::class.qualifiedName}`",
        )
    }

    private fun eventSubscriberMessageOf(
        hash: ByteArray,
        entityKind: PipelineEntityKind = PipelineEntityKind.Transaction(),
    ) = VersionedEventSubscriptionRequest.V1(
        EventSubscriptionRequest(
            Filters.pipeline(entityKind, null, hash),
        ),
    )

    object DurationDeserializer : JsonDeserializer<Duration>() {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Duration {
            val pairs: Map<String, Long> =
                p.readValueAs(object : TypeReference<Map<String, Long>>() {})
            val seconds = pairs["secs"] ?: throw JsonMappingException.from(
                p,
                "Expected `secs` item for duration deserialization",
            )
            val nanos = pairs["nanos"] ?: throw JsonMappingException.from(
                p,
                "Expected `nanos` item for duration deserialization",
            )
            return Duration.ofSeconds(seconds, nanos)
        }
    }

    override fun close() = client.close()
}

data class BlockStreamContext(
    val apiUrl: URL,
    val client: HttpClient,
    val from: Long = 1,
    val count: Int? = null,
    val onBlock: suspend (block: VersionedBlockMessage) -> Any,
    val onFailure: suspend (t: Throwable) -> Unit,
    val closeIf: suspend (block: VersionedBlockMessage) -> Boolean,
)

data class BlockStreamSubscriptionStorage(
    val onBlock: suspend (block: VersionedBlockMessage) -> Any,
    val closeIf: (suspend (block: VersionedBlockMessage) -> Boolean)? = null,
    val onFailure: suspend (t: Throwable) -> Unit,
    val channel: Channel<Any>,
)

class BlockStreamSubscription private constructor(private val context: BlockStreamContext) : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()

    private val logger = LoggerFactory.getLogger(javaClass)

    private val source: MutableList<BlockStreamSubscriptionStorage> = mutableListOf()

    private var consumed: Boolean = false

    fun consume(): BlockStreamSubscription {
        if (!consumed) {
            expand(context.closeIf, context.onFailure, context.onBlock)
            launch { run() }
            consumed = true
        }
        return getInstance(context)
    }

    fun expand(
        closeIf: (suspend (block: VersionedBlockMessage) -> Boolean)? = null,
        onFailure: (suspend (t: Throwable) -> Unit)? = null,
        onBlock: suspend (block: VersionedBlockMessage) -> Any,
    ) = source.add(
        BlockStreamSubscriptionStorage(
            onBlock,
            closeIf,
            onFailure ?: context.onFailure,
            Channel(),
        ),
    ).also { logger.debug("Block stream subscription was expanded. Number of channels is ${source.size}") }

    // todo async
    // todo uuid
    fun <T> receive(actionIdx: Int = 0): Flow<T> {
        val storage = source[actionIdx]
        return storage.channel.cast<Channel<T>>().receiveAsFlow().catch { storage.onFailure(it) }
    }

    @OptIn(DelicateCoroutinesApi::class)
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
                logger.debug("Received frame: {}", frame)

                val block = VersionedBlockMessage.decode(frame.readBytes())
                source.forEachIndexed { index, storage ->
                    if (storage.channel.isClosedForSend) {
                        return@forEachIndexed
                    }
                    logger.info("TRYING SEND: $index")

                    val result = storage.onBlock(block)
                    storage.channel.send(result)
                    logger.info("RESULT: $result")

                    if (storage.closeIf?.let { it(block) } == true) {
                        logger.info("Block stream channel#$index is closing")
                        storage.channel.close() // todo memory leak
                    }
                }
                if (++counter == context.count) {
                    logger.info("Block stream channels are closing")
                    source.forEach { storage -> storage.channel.close() }
                }
            }
        }
    }

    companion object : SingletonHolder<BlockStreamSubscription, BlockStreamContext>(::BlockStreamSubscription)
}

open class SingletonHolder<out T : Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}
