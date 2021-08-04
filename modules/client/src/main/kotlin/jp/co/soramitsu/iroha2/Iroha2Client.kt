package jp.co.soramitsu.iroha2

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.ws
import io.ktor.client.request.request
import io.ktor.client.statement.HttpStatement
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.DefaultWebSocketSession
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readBytes
import io.ktor.http.cio.websocket.readText
import io.ktor.http.content.ByteArrayContent
import io.ktor.util.hex
import jp.co.soramitsu.iroha2.generated.crypto.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.events.Event
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventFilter.Pipeline
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventSocketMessage
import jp.co.soramitsu.iroha2.generated.datamodel.events.SubscriptionRequest
import jp.co.soramitsu.iroha2.generated.datamodel.events.VersionedEventSocketMessage
import jp.co.soramitsu.iroha2.generated.datamodel.events._VersionedEventSocketMessageV1
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EntityType.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.Status
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedQueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.utils.decode
import jp.co.soramitsu.iroha2.utils.encode
import jp.co.soramitsu.iroha2.utils.hash
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.io.Closeable
import java.net.URL
import java.util.concurrent.CompletableFuture
import kotlin.reflect.KClass
import jp.co.soramitsu.iroha2.generated.datamodel.events.pipeline.EventFilter as Filter

class Iroha2Client(private val peerUrl: URL) : Closeable {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val client = lazy {
        HttpClient(OkHttp) {
            install(WebSockets)
        }
    }

    fun sendTransaction(transaction: TransactionBuilder.() -> VersionedTransaction): ByteArray {
        val signedTransaction = transaction(TransactionBuilder.builder())
        val hash = signedTransaction.hash()
        logger.debug("Sending transaction with hash ${hex(hash)}")
        val encoded = encode(VersionedTransaction, signedTransaction)
        runBlocking {
            interactWithPeerAsync(HttpMethod.Post, encoded).await()
        }
        return hash
    }

    fun sendTransactionAsync(transaction: TransactionBuilder.() -> VersionedTransaction): CompletableFuture<ByteArray> {
        val signedTransaction = transaction(TransactionBuilder.builder())
        val result = subscribeToTransactionStatus(signedTransaction.hash())
        sendTransaction { signedTransaction }
        return result
    }

    fun sendQuery(query: VersionedSignedQueryRequest): VersionedQueryResult {
        val encoded = encode(VersionedSignedQueryRequest, query)
        val response = runBlocking {
            interactWithPeerAsync(HttpMethod.Post, encoded).await()
        }
        return decode(VersionedQueryResult, response)
    }

    fun subscribeToTransactionStatus(hash: ByteArray): CompletableFuture<ByteArray> {
        val hexHash = hex(hash)
        logger.debug("Creating subscription to transaction status: $hexHash")
        val subscriptionRequest = VersionedEventSocketMessage.V1(
            _VersionedEventSocketMessageV1(
                EventSocketMessage.SubscriptionRequest(SubscriptionRequest(Pipeline(Filter(Transaction(), Hash(hash)))))
            )
        )
        val payload = encode(VersionedEventSocketMessage, subscriptionRequest)
        val result: CompletableFuture<ByteArray> = CompletableFuture()
        GlobalScope.async {
            client.value.ws(
                host = peerUrl.host,
                port = peerUrl.port,
                path = WS_ENDPOINT
            ) {
                logger.debug("Sending subscription request for transaction $hexHash")
                send(Frame.Binary(true, payload))
                tryReadEventSocketMessage(this, EventSocketMessage.SubscriptionAccepted::class)
                logger.debug("Received subscription request accepted message, awaiting events")
                while (true) {
                    val event = tryReadEventSocketMessage(this, EventSocketMessage.Event::class)
                    when (event.event) {
                        is Event.Pipeline -> {
                            val event3 = event.event.event
                            if (event3.entityType is Transaction && hash.contentEquals(event3.hash.array)) {
                                when (event3.status) {
                                    is Status.Committed -> {
                                        logger.debug("Transaction $hexHash committed")
                                        result.complete(hash)
                                        break
                                    }
                                    is Status.Rejected -> {
                                        logger.debug("Transaction $hexHash was rejected by reason: ${event3.status.rejectionReason}")
                                        result.completeExceptionally(RuntimeException("Transaction rejected"))
                                        break
                                    }
                                    is Status.Validating -> {
                                        logger.debug("Transaction $hexHash is validating")
                                    }
                                }
                            }
                        }
                        else -> result.completeExceptionally(java.lang.RuntimeException("Expected message with type ${Event.Pipeline::class.qualifiedName} but got ${event.event::class.qualifiedName}"))
                    }
                    ack(this) //send message event was received
                }
                close()
            }
        }
        return result
    }

    private fun interactWithPeerAsync(httpMethod: HttpMethod, payload: ByteArray): Deferred<ByteArray> {
        return GlobalScope.async {
            val statement : HttpStatement = client.value.request("$peerUrl$INSTRUCTION_ENDPOINT") {
                method = httpMethod
                body = ByteArrayContent(payload)
            }
            statement.receive()
        }
    }

    override fun close() = this.client.value.close()

    companion object {
        const val INSTRUCTION_ENDPOINT = "/instruction"
        const val QUERY_ENDPOINT = "/query"
        const val WS_ENDPOINT = "/events"
    }
}

private suspend fun readBinary(wsSession: DefaultWebSocketSession): ByteArray {
    return when (val frame = wsSession.incoming.receive()) {
        is Frame.Binary -> frame.readBytes()
        else -> throw RuntimeException("Expected frame with binary, but received with kind '${frame::class.simpleName}'")
    }
}

private suspend fun readText(wsSession: DefaultWebSocketSession): String {
    return when (val frame = wsSession.incoming.receive()) {
        is Frame.Text -> frame.readText()
        else -> throw RuntimeException("Expected frame with text, but received with kind '${frame::class.simpleName}'")
    }
}

private suspend fun <T : EventSocketMessage> tryReadEventSocketMessage(
    wsSession: DefaultWebSocketSession,
    targetClass: KClass<T>
): T {
    val versionedMessage = decode(VersionedEventSocketMessage, readBinary(wsSession))
    if (versionedMessage is VersionedEventSocketMessage.V1) {
        val eventSocketMessage = versionedMessage._VersionedEventSocketMessageV1.eventSocketMessage
        if (targetClass.isInstance(eventSocketMessage)) {
            return eventSocketMessage as T
        } else {
            throw RuntimeException("Expected '${targetClass.qualifiedName}', but got '${eventSocketMessage::class.qualifiedName}'")
        }
    } else {
        throw RuntimeException("Expected '${VersionedEventSocketMessage.V1::class.qualifiedName}', but got '${versionedMessage::class.qualifiedName}'")
    }
}

private suspend fun ack(wsSession: DefaultWebSocketSession) {
    val eventReceived = VersionedEventSocketMessage.V1(
        _VersionedEventSocketMessageV1(
            EventSocketMessage.EventReceived()
        )
    )
    wsSession.send(Frame.Binary(true, encode(VersionedEventSocketMessage, eventReceived)))
}
