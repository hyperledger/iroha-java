package jp.co.soramitsu.iroha2

import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.HttpResponseValidator
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.websocket.ClientWebSocketSession
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.webSocket
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readBytes
import io.ktor.http.cio.websocket.send
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.events.Event
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventFilter.Pipeline
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventSocketMessage
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventSocketMessage.SubscriptionAccepted
import jp.co.soramitsu.iroha2.generated.datamodel.events.SubscriptionRequest
import jp.co.soramitsu.iroha2.generated.datamodel.events.VersionedEventSocketMessage
import jp.co.soramitsu.iroha2.generated.datamodel.events._VersionedEventSocketMessageV1
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.BlockRejectionReason
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EntityType.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.RejectionReason
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Status
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.TransactionRejectionReason
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedQueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URL
import java.util.concurrent.CompletableFuture
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EventFilter as Filter

open class Iroha2Client(
    open var peerUrl: URL,
    open val log: Boolean = false
) : AutoCloseable {

    open val logger: Logger = LoggerFactory.getLogger(javaClass)

    open val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    open val client = lazy {
        HttpClient(CIO) {
            expectSuccess = true
            if (log) {
                install(Logging)
            }
            install(WebSockets)
            HttpResponseValidator {
                handleResponseException { exception ->
                    throw IrohaClientException(cause = exception)
                }
            }
        }
    }

    /**
     * Returns current status of peer
     */
    suspend fun health(): Int {
        return client.value
            .get<HttpResponse>("$peerUrl$HEALTH_ENDPOINT")
            .status.value
    }

    /**
     * Sends transaction to Iroha peer
     *
     * The method only sends transaction to peer and do not await it final committing status. It means when peer
     * response with 2xx status code the peer only accepted transaction and the transaction passed stateless
     * validation. Further state of the transaction is not tracked.
     */
    suspend fun fireAndForget(transaction: TransactionBuilder.() -> VersionedTransaction): ByteArray {
        val signedTransaction = transaction(TransactionBuilder.builder())
        val hash = signedTransaction.hash()
        logger.debug("Sending transaction with hash {}", hash.toHex())
        val response: HttpResponse = client.value.post("$peerUrl$INSTRUCTION_ENDPOINT") {
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
    ): CompletableFuture<ByteArray> = coroutineScope {
        val signedTransaction = transaction(TransactionBuilder())

        val lock = Mutex(locked = true)
        subscribeToTransactionStatus(signedTransaction.hash()) {
            lock.unlock()
        }.also {
            lock.lock() // waiting for unlock
            fireAndForget { signedTransaction }
        }
    }

    /**
     * Sends request to Iroha2 and extract payload.
     * {@see Extractors}
     */
    suspend fun <T> sendQuery(queryAndExtractor: QueryAndExtractor<T>): T {
        logger.debug("Sending query")
        val response: HttpResponse = client.value.post("$peerUrl$QUERY_ENDPOINT") {
            this.body = VersionedSignedQueryRequest.encode(queryAndExtractor.query)
        }
        return response.receive<ByteArray>()
            .let { VersionedQueryResult.decode(it) }
            .let { queryAndExtractor.resultExtractor.extract(it) }
    }

    fun subscribeToTransactionStatus(hash: ByteArray) = subscribeToTransactionStatus(hash, null)

    /**
     * @param hash - Signed transaction hash
     * @param afterSubscription - Expression that is invoked after subscription
     */
    private fun subscribeToTransactionStatus(
        hash: ByteArray,
        afterSubscription: (() -> Unit)? = null
    ): CompletableFuture<ByteArray> {
        val hexHash = hash.toHex()
        logger.debug("Creating subscription to transaction status: {}", hexHash)
        val subscriptionRequest = VersionedEventSocketMessage.V1(
            _VersionedEventSocketMessageV1(
                EventSocketMessage.SubscriptionRequest(
                    SubscriptionRequest(
                        Pipeline(
                            Filter(Transaction(), Hash(hash))
                        )
                    )
                )
            )
        )
        val payload = VersionedEventSocketMessage.encode(subscriptionRequest)
        val result: CompletableFuture<ByteArray> = CompletableFuture()

        scope.launch {
            client.value.webSocket(
                host = peerUrl.host,
                port = peerUrl.port,
                path = WS_ENDPOINT
            ) {
                logger.debug("WebSocket opened")

                send(payload)
                tryReadMessage<SubscriptionAccepted>(incoming.receive())
                afterSubscription?.invoke()

                logger.debug("Subscription was accepted by peer")
                while (true) {
                    when (val event = tryReadMessage<EventSocketMessage.Event>(incoming.receive()).event) {
                        is Event.Pipeline -> {
                            val eventInner = event.event
                            if (eventInner.entityType is Transaction && hash.contentEquals(eventInner.hash.array)) {
                                when (val status = eventInner.status) {
                                    is Status.Committed -> {
                                        logger.debug("Transaction {} committed", hexHash)
                                        result.complete(hash)
                                        ack(this)
                                        break
                                    }
                                    is Status.Rejected -> {
                                        val rejectionReason = getRejectionReason(status.rejectionReason)
                                        logger.error(
                                            "Transaction {} was rejected by reason: `{}`",
                                            hexHash,
                                            rejectionReason
                                        )
                                        result.completeExceptionally(TransactionRejectedException("Transaction rejected with reason '$rejectionReason'"))
                                        ack(this)
                                        break
                                    }
                                    is Status.Validating -> {
                                        logger.debug("Transaction {} is validating", hexHash)
                                        ack(this)
                                    }
                                }
                            }
                        }
                        else -> result.completeExceptionally(
                            WebSocketProtocolException(
                                "Expected message with type ${Event.Pipeline::class.qualifiedName}, but was ${event::class.qualifiedName}"
                            )
                        )
                    }
                }
                logger.debug("WebSocket is closing")
                this.close()
                logger.debug("WebSocket closed")
            }
        }
        return result
    }

    /**
     * Sends message to peer event was accepted
     */
    private suspend fun ack(webSocket: ClientWebSocketSession) {
        val eventReceived = VersionedEventSocketMessage.V1(
            _VersionedEventSocketMessageV1(
                EventSocketMessage.EventReceived()
            )
        )
        webSocket.send(VersionedEventSocketMessage.encode(eventReceived))
    }

    /**
     * Extract rejection reason
     */
    private fun getRejectionReason(rejectionReason: RejectionReason): String {
        return when (rejectionReason) {
            is RejectionReason.Block -> when (rejectionReason.blockRejectionReason) {
                is BlockRejectionReason.ConsensusBlockRejection -> "Block was rejected during consensus"
            }
            is RejectionReason.Transaction -> when (val reason = rejectionReason.transactionRejectionReason) {
                is TransactionRejectionReason.InstructionExecution -> {
                    val details = reason.instructionExecutionFail
                    "Failed: `${details.reason}` during execution of instruction: ${details.instruction::class.qualifiedName}"
                }
                is TransactionRejectionReason.NotPermitted -> reason.notPermittedFail.reason
                is TransactionRejectionReason.SignatureVerification -> reason.signatureVerificationFail.reason
                is TransactionRejectionReason.UnexpectedGenesisAccountSignature -> "Genesis account can sign only transactions in the genesis block"
                is TransactionRejectionReason.UnsatisfiedSignatureCondition -> reason.unsatisfiedSignatureConditionFail.reason
            }
        }
    }

    /**
     * Tries to read message from the frame
     */
    private inline fun <reified T : EventSocketMessage> tryReadMessage(frame: Frame): T {
        return when (frame) {
            is Frame.Binary -> {
                when (val versionedMessage = frame.readBytes().let { VersionedEventSocketMessage.decode(it) }) {
                    is VersionedEventSocketMessage.V1 -> {
                        val actualMessage = versionedMessage._VersionedEventSocketMessageV1.eventSocketMessage
                        actualMessage as? T
                            ?: throw WebSocketProtocolException(
                                "Expected `${T::class.qualifiedName}`, but was ${actualMessage::class.qualifiedName}"
                            )
                    }
                    else -> throw WebSocketProtocolException(
                        "Expected `${VersionedEventSocketMessage.V1::class.qualifiedName}`, but was `${versionedMessage::class.qualifiedName}`"
                    )
                }
            }
            else -> throw WebSocketProtocolException(
                "Expected server will `${Frame.Binary::class.qualifiedName}` frame, but was `${frame::class.qualifiedName}`"
            )
        }
    }

    companion object {
        const val INSTRUCTION_ENDPOINT = "/transaction"
        const val QUERY_ENDPOINT = "/query"
        const val WS_ENDPOINT = "/events"
        const val HEALTH_ENDPOINT = "/health"
    }

    override fun close() = client.value.close()
}
