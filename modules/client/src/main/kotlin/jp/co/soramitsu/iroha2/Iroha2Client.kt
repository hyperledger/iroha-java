package jp.co.soramitsu.iroha2

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.ws
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.statement.HttpStatement
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders.Connection
import io.ktor.http.HttpHeaders.ContentLength
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import io.ktor.http.content.ByteArrayContent
import io.ktor.http.contentLength
import jp.co.soramitsu.iroha2.generated.datamodel.events.SubscriptionRequest
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
import java.io.Closeable
import java.net.URL

class Iroha2Client(private val peerUrl: URL) : Closeable {

     private val client = lazy {
         HttpClient(CIO) {
             install(WebSockets)
         }
     }

    fun sendTransaction(transaction: VersionedTransaction) : ByteArray {
        val encoded = encode(VersionedTransaction, transaction)
        runBlocking {
            interactWithPeerAsync(HttpMethod.Post, encoded).await()
        }
        return hash(encoded)
    }

    inline fun sendTransaction(transaction: TransactionBuilder.() -> VersionedTransaction) : ByteArray {
        return this.sendTransaction(transaction(TransactionBuilder.builder()))
    }

    fun sendQuery(query: VersionedSignedQueryRequest) : VersionedQueryResult {
        val encoded = encode(VersionedSignedQueryRequest, query)
        val response = runBlocking {
            interactWithPeerAsync(HttpMethod.Post, encoded).await()
        }
        return decode(VersionedQueryResult, response)
    }

    fun eventsStream(subscriptionRequest: SubscriptionRequest) {
        val encoded = encode(SubscriptionRequest, subscriptionRequest)
        GlobalScope.async {
            client.value.ws(peerUrl.toString(), {}) {
                //todo finish the interaction
            }
        }

    }


    private fun interactWithPeerAsync(httpMethod: HttpMethod, payload: ByteArray) : Deferred<ByteArray> {
        return GlobalScope.async {
            val response : HttpStatement = client.value.request("$peerUrl$INSTRUCTION_ENDPOINT") {
                method = httpMethod
                body = ByteArrayContent(payload)
            }
            response.receive()
        }

    }


    override fun close() = this.client.value.close()

    companion object {
        const val INSTRUCTION_ENDPOINT = "/instruction"
        const val QUERY_ENDPOINT = "/instruction"
    }
}
