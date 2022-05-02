package jp.co.soramitsu.iroha2

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.module.SimpleModule
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.HttpResponseValidator
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.websocket.ClientWebSocketSession
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.webSocket
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readBytes
import io.ktor.http.cio.websocket.send
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.events.Event
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventFilter.Pipeline
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventPublisherMessage
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventSubscriberMessage
import jp.co.soramitsu.iroha2.generated.datamodel.events.VersionedEventPublisherMessage
import jp.co.soramitsu.iroha2.generated.datamodel.events.VersionedEventSubscriberMessage
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EntityType.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Status
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedQueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.BlockRejectionReason
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.RejectionReason
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionRejectionReason
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.query.QueryAndExtractor
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.future.future
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URL
import java.time.Duration
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.CoroutineContext
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EventFilter as Filter

@Suppress("unused")
open class Iroha2Client(
    open var peerUrl: URL,
    open val log: Boolean = false,
    open val eventReadTimeoutInMills: Long = 250,
    open val eventReadMaxAttempts: Int = 10,
    override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()
) : AutoCloseable, CoroutineScope {

    companion object {
        const val TRANSACTION_ENDPOINT = "/transaction"
        const val QUERY_ENDPOINT = "/query"
        const val WS_ENDPOINT = "/events"
        const val HEALTH_ENDPOINT = "/health"
        const val STATUS_ENDPOINT = "/status"
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
            install(JsonFeature) {
                this.serializer = JacksonSerializer {
                    registerModule(
                        SimpleModule().apply {
                            addDeserializer(Duration::class.java, DurationDeserializer)
                        }
                    )
                }
            }
            HttpResponseValidator {
                handleResponseException { exception ->
                    throw IrohaClientException(cause = exception)
                }
            }
        }
    }

    /**
     * Sends request to Iroha2 and extract payload.
     * {@see Extractors}
     */
    suspend fun <T> sendQuery(queryAndExtractor: QueryAndExtractor<T>): T {
        logger.debug("Sending query")
        val response: HttpResponse = client.post("$peerUrl$QUERY_ENDPOINT") {
            this.body = VersionedSignedQueryRequest.encode(queryAndExtractor.query)
        }
        return response.receive<ByteArray>()
            .let { VersionedQueryResult.decode(it) }
            .let { queryAndExtractor.resultExtractor.extract(it) }
    }

    fun <T> sendQueryAsync(
        queryAndExtractor: QueryAndExtractor<T>
    ): CompletableFuture<T> = future {
        sendQuery(queryAndExtractor)
    }

    /**
     * Sends transaction to Iroha peer
     *
     * The method only sends transaction to peer and do not await it final committing status. It means when peer
     * response with 2xx status code the peer only accepted transaction and the transaction passed stateless
     * validation. Further, state of the transaction is not tracked.
     */
    suspend fun fireAndForget(transaction: TransactionBuilder.() -> VersionedTransaction): ByteArray {
        val signedTransaction = transaction(TransactionBuilder.builder())
        val hash = signedTransaction.hash()
        logger.debug("Sending transaction with hash {}", hash.toHex())
        val response: HttpResponse = client.post("$peerUrl$TRANSACTION_ENDPOINT") {
            body = VersionedTransaction.encode(signedTransaction)
        }
        response.receive<Unit>()
        return hash
    }

    /**
     * Sends transaction to Iroha peer and wait until it will be committed or rejected.
     */
    suspend fun sendTransaction(
        transaction: TransactionBuilder.() -> VersionedTransaction
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

    /**
     * [for Java]
     */
    fun sendTransactionAsync(
        transaction: VersionedTransaction
    ): CompletableFuture<ByteArray> = runBlocking {
        sendTransaction { transaction }.asCompletableFuture()
    }

    fun subscribeToTransactionStatus(hash: ByteArray) = subscribeToTransactionStatus(hash, null)

    /**
     * @param hash - Signed transaction hash
     * @param afterSubscription - Expression that is invoked after subscription
     */
    private fun subscribeToTransactionStatus(
        hash: ByteArray,
        afterSubscription: (() -> Unit)? = null
    ): CompletableDeferred<ByteArray> {
        val hexHash = hash.toHex()
        logger.debug("Creating subscription to transaction status: {}", hexHash)

        val subscriptionRequest = eventSubscriberMessageOf(hash)
        val payload = VersionedEventSubscriberMessage.encode(subscriptionRequest)
        val result: CompletableDeferred<ByteArray> = CompletableDeferred()

        launch {
            client.webSocket( // todo creates for each tx
                host = peerUrl.host,
                port = peerUrl.port,
                path = WS_ENDPOINT
            ) {
                logger.debug("WebSocket opened")
                send(payload)
                readMessage<EventPublisherMessage.SubscriptionAccepted>(incoming.receive())

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
                    } finally {
                        accepted(this)
                    }

                    delay(eventReadTimeoutInMills)
                }
                logger.debug("WebSocket is closing")
                this.close()
                logger.debug("WebSocket closed")
            }
        }
        return result
    }

    private fun pipelineEventProcess(
        eventPublisherMessage: EventPublisherMessage.Event,
        hash: ByteArray,
        hexHash: String
    ): ByteArray? {
        when (val event = eventPublisherMessage.event) {
            is Event.Pipeline -> {
                val eventInner = event.event
                if (eventInner.entityType is Transaction && hash.contentEquals(eventInner.hash.array)) {
                    when (val status = eventInner.status) {
                        is Status.Committed -> {
                            logger.debug("Transaction {} committed", hexHash)
                            return hash
                        }
                        is Status.Rejected -> {
                            val reason = status.rejectionReason.message()
                            logger.error("Transaction {} was rejected by reason: `{}`", hexHash, reason)
                            throw TransactionRejectedException("Transaction rejected with reason '$reason'")
                        }
                        is Status.Validating -> {
                            logger.debug("Transaction {} is validating", hexHash)
                        }
                    }
                }
                return null
            }
            else -> throw WebSocketProtocolException(
                "Expected message with type ${Event.Pipeline::class.qualifiedName}," +
                    " but was ${event::class.qualifiedName}"
            )
        }
    }

    /**
     * Sends message to peer event was accepted
     */
    private suspend fun accepted(webSocket: ClientWebSocketSession) {
        val eventReceived = VersionedEventSubscriberMessage.V1(
            EventSubscriberMessage.EventReceived()
        )
        webSocket.send(VersionedEventSubscriberMessage.encode(eventReceived))
    }

    /**
     * Extract rejection reason
     */
    private fun RejectionReason.message(): String {
        return when (this) {
            is RejectionReason.Block -> when (this.blockRejectionReason) {
                is BlockRejectionReason.ConsensusBlockRejection -> "Block was rejected during consensus"
            }
            is RejectionReason.Transaction -> when (val reason = this.transactionRejectionReason) {
                is TransactionRejectionReason.InstructionExecution -> {
                    val details = reason.instructionExecutionFail
                    "Failed: `${details.reason}` during execution of instruction: ${details.instruction::class.qualifiedName}"
                }
                is TransactionRejectionReason.NotPermitted -> reason.notPermittedFail.reason
                is TransactionRejectionReason.UnexpectedGenesisAccountSignature ->
                    "Genesis account can sign only transactions in the genesis block"
                is TransactionRejectionReason.UnsatisfiedSignatureCondition ->
                    reason.unsatisfiedSignatureConditionFail.reason
                is TransactionRejectionReason.WasmExecution -> reason.wasmExecutionFail.reason
                is TransactionRejectionReason.LimitCheck -> reason.transactionLimitError.string
            }
        }
    }

    /**
     * Tries to read message from the frame
     */
    private inline fun <reified T : EventPublisherMessage> readMessage(frame: Frame): T {
        return when (frame) {
            is Frame.Binary -> {
                when (val versionedMessage = frame.readBytes().let { VersionedEventPublisherMessage.decode(it) }) {
                    is VersionedEventPublisherMessage.V1 -> {
                        val actualMessage = versionedMessage.eventPublisherMessage
                        actualMessage as? T
                            ?: throw WebSocketProtocolException(
                                "Expected `${T::class.qualifiedName}`, but was ${actualMessage::class.qualifiedName}"
                            )
                    }
                    else -> throw WebSocketProtocolException(
                        "Expected `${VersionedEventSubscriberMessage.V1::class.qualifiedName}`, but was `${versionedMessage::class.qualifiedName}`"
                    )
                }
            }
            else -> throw WebSocketProtocolException(
                "Expected server will `${Frame.Binary::class.qualifiedName}` frame, but was `${frame::class.qualifiedName}`"
            )
        }
    }

    override fun close() = client.close()

    object DurationDeserializer : JsonDeserializer<Duration>() {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Duration {
            val pairs: Map<String, Long> =
                p.readValueAs(object : TypeReference<Map<String, Long>>() {})
            val seconds = pairs["secs"] ?: throw JsonMappingException.from(
                p, "Expected `secs` item for duration deserialization"
            )
            val nanos = pairs["nanos"] ?: throw JsonMappingException.from(
                p, "Expected `nanos` item for duration deserialization"
            )
            return Duration.ofSeconds(seconds, nanos)
        }
    }

    private fun eventSubscriberMessageOf(hash: ByteArray): VersionedEventSubscriberMessage.V1 {
        return VersionedEventSubscriberMessage.V1(
            EventSubscriberMessage.SubscriptionRequest(
                Pipeline(
                    Filter(Transaction(), Hash(hash))
                )
            )
        )
    }
}
