package jp.co.soramitsu.iroha2

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.ws
import io.ktor.client.request.request
import io.ktor.client.statement.HttpStatement
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.send
import jp.co.soramitsu.iroha2.generated.datamodel.events.SubscriptionRequest
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedQueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.query.VersionedSignedQueryRequest
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.bouncycastle.jcajce.provider.digest.Blake2b.Blake2b256
import java.io.ByteArrayOutputStream
import java.net.URL

class Iroha2Client(private val peerUrl: URL) {

     private val client = lazy {
         HttpClient(CIO) {
             install(WebSockets)
         }
     }

    fun sendInstruction(instruction: VersionedTransaction) : ByteArray {
        val encoded = encode(VersionedTransaction, instruction)
        runBlocking {
            interactWithPeerAsync(HttpMethod.Post, encoded).await()
        }
        return hash(encoded)
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
                send(encoded)
            }
        }

    }

    private fun<T> encode(writer: ScaleWriter<T>, instance: T) : ByteArray {
        //resource closed inside `ScaleCodecWriter`
        val buffer = ByteArrayOutputStream()
        return ScaleCodecWriter(buffer)
            .use {
                writer.write(it, instance)
                buffer.toByteArray()
            }
    }

    private fun<T> decode(reader: ScaleReader<T>, source: ByteArray) = ScaleCodecReader(source).read(reader)

    private fun interactWithPeerAsync(httpMethod: HttpMethod, payload: ByteArray) : Deferred<ByteArray> {
        return GlobalScope.async {
            val response : HttpStatement = client.value.request(peerUrl) {
                method = httpMethod
                body = payload
            }
            response.receive()
        }

    }

    private fun hash(target: ByteArray) : ByteArray = Blake2b256().digest(target)

}
