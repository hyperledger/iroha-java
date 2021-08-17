package jp.co.soramitsu.iroha2

import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.content.ByteArrayContent
import jp.co.soramitsu.iroha2.generated.crypto.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.events.Event
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventFilter.Pipeline
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventSocketMessage
import jp.co.soramitsu.iroha2.generated.datamodel.events.SubscriptionRequest
import jp.co.soramitsu.iroha2.generated.datamodel.events.VersionedEventSocketMessage
import jp.co.soramitsu.iroha2.generated.datamodel.events._VersionedEventSocketMessageV1
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.BlockRejectionReason
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EntityType.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.RejectionReason
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Status
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.TransactionRejectionReason
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedQueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.utils.asIs
import jp.co.soramitsu.iroha2.utils.hash
import jp.co.soramitsu.iroha2.utils.hex
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import okio.ByteString.Companion.toByteString
import org.slf4j.LoggerFactory
import java.net.URL
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EventFilter as Filter

class Iroha2Client(private val peerUrl: URL) : AutoCloseable {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val client = lazy {
        OkHttpClient.Builder()
            .connectTimeout(0, TimeUnit.SECONDS)
            .build()
    }

    fun sendTransaction(transaction: TransactionBuilder.() -> VersionedTransaction): ByteArray {
        VersionedQueryResult
        val signedTransaction = transaction(TransactionBuilder.builder())
        val hash = signedTransaction.hash()
        logger.debug("Sending transaction with hash ${hash.hex()}")
        val encoded = encode(VersionedTransaction, signedTransaction)
        val request = Request.Builder()
            .url("$peerUrl$INSTRUCTION_ENDPOINT")
            .post(encoded.toRequestBody())
            .build()
        client.value.newCall(request)
            .execute()
            .use {
                check(it.isSuccessful) { "Response returned with status code ${it.code}" }
            }
        return hash
    }

    fun sendTransactionAsync(transaction: TransactionBuilder.() -> VersionedTransaction): CompletableFuture<ByteArray> {
        val signedTransaction = transaction(TransactionBuilder())
        val result = subscribeToTransactionStatus(signedTransaction.hash())
        sendTransaction { signedTransaction }
        return result
    }

    fun sendQuery(query: QueryBuilder.() -> VersionedSignedQueryRequest): QueryResult = sendQuery(::asIs, query)

    /**
     * Sends request to Iroha2 and extract payload.
     * {@see Extractors}
     */
    fun <T> sendQuery(extractor: (QueryResult) -> T, query: QueryBuilder.() -> VersionedSignedQueryRequest): T {
        logger.debug("Sending query")
        val signedQuery = query(QueryBuilder.builder())
        val encoded = encode(VersionedSignedQueryRequest, signedQuery)
        val client = HttpClient(CIO) {
        }

        val rawBody = runBlocking {
            val response: HttpResponse = client.request("$peerUrl$QUERY_ENDPOINT") {
                this.method = HttpMethod.Get
                this.body = ByteArrayContent(encoded)
            }
            check(response.status.value == 200) { "Response returned with status code ${response.status.value}" }
            response.receive<ByteArray>()
        }
        logger.debug("Received binary query: {}", rawBody.hex())
        return decode(QueryResult, rawBody).let(extractor)
    }

    fun subscribeToTransactionStatus(hash: ByteArray): CompletableFuture<ByteArray> {
        val hexHash = hash.hex()
        logger.debug("Creating subscription to transaction status: $hexHash")
        val subscriptionRequest = VersionedEventSocketMessage.V1(
            _VersionedEventSocketMessageV1(
                EventSocketMessage.SubscriptionRequest(SubscriptionRequest(Pipeline(Filter(Transaction(), Hash(hash)))))
            )
        )
        val payload = encode(VersionedEventSocketMessage, subscriptionRequest)
        val result: CompletableFuture<ByteArray> = CompletableFuture()
        val request = Request.Builder()
            .url("$peerUrl$WS_ENDPOINT")
            .get()
            .build()
        client.value.newWebSocket(
            request,
            object : WebSocketListener() {
                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    logger.debug("WebSocket closed")
                    super.onClosed(webSocket, code, reason)
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    logger.debug("WebSocket is closing")
                    super.onClosing(webSocket, code, reason)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    logger.error("WebSocket error", t)
                    result.completeExceptionally(t)
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    logger.debug("Received text message")
                }

                override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                    logger.debug("Received binary message: {}", bytes.hex())
                    when (val message = tryReadMessage(bytes.toByteArray())) {
                        is EventSocketMessage.SubscriptionAccepted -> {
                            logger.debug("Subscription was accepted by peer")
                        }
                        is EventSocketMessage.Event -> {
                            when (val event = message.event) {
                                is Event.Pipeline -> {
                                    val eventInner = event.event
                                    if (eventInner.entityType is Transaction && hash.contentEquals(eventInner.hash.array)) {
                                        when (val status = eventInner.status) {
                                            is Status.Committed -> {
                                                logger.debug("Transaction $hexHash committed")
                                                result.complete(hash)
                                                ack(webSocket, true)
                                                webSocket.close(1000, null)
                                            }
                                            is Status.Rejected -> {
                                                logger.error(
                                                    "Transaction $hexHash was rejected by reason: `{}`",
                                                    getRejectionReason(status.rejectionReason)
                                                )
                                                result.completeExceptionally(RuntimeException("Transaction rejected"))
                                                ack(webSocket, true)
                                            }
                                            is Status.Validating -> {
                                                logger.debug("Transaction $hexHash is validating")
                                                ack(webSocket, false)
                                            }
                                        }
                                    }
                                }
                                else -> result.completeExceptionally(RuntimeException("Expected message with type ${Event.Pipeline::class.qualifiedName} but got ${message.event::class.qualifiedName}"))
                            }
                        }
                    }
                }

                override fun onOpen(webSocket: WebSocket, response: Response) {
                    logger.debug("WebSocket opened")
                    webSocket.send(payload.toByteString())
                }
            }
        )
        return result
    }

    private fun ack(webSocket: WebSocket, isTerminalMessage: Boolean = false) {
        val eventReceived = VersionedEventSocketMessage.V1(
            _VersionedEventSocketMessageV1(
                EventSocketMessage.EventReceived()
            )
        )
        webSocket.send(encode(VersionedEventSocketMessage, eventReceived).toByteString())
        if (isTerminalMessage) {
            webSocket.close(1000, null)
        }
    }

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

    companion object {
        const val INSTRUCTION_ENDPOINT = "/instruction"
        const val QUERY_ENDPOINT = "/query"
        const val WS_ENDPOINT = "/events"
    }

    override fun close() {
        client.value.dispatcher.executorService.shutdown()
        client.value.connectionPool.evictAll()
    }
}

private fun tryReadMessage(message: ByteArray): EventSocketMessage {
    val versionedMessage = decode(VersionedEventSocketMessage, message)
    if (versionedMessage is VersionedEventSocketMessage.V1) {
        return versionedMessage._VersionedEventSocketMessageV1.eventSocketMessage
    } else {
        throw RuntimeException("Expected '${VersionedEventSocketMessage.V1::class.qualifiedName}', but got '${versionedMessage::class.qualifiedName}'")
    }
}
