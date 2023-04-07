package jp.co.soramitsu.iroha2.client

import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedSignedTransaction
import jp.co.soramitsu.iroha2.query.QueryAndExtractor
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.future.future
import kotlinx.coroutines.runBlocking
import java.net.URL
import java.util.concurrent.CompletableFuture

/**
 * Extension of [Iroha2Client] for Java
 */
@Suppress("unused")
class Iroha2AsyncClient @JvmOverloads constructor(
    peerUrl: URL,
    log: Boolean = false,
    credentials: String? = null,
    eventReadTimeoutInMills: Long = 250,
    eventReadMaxAttempts: Int = 10
) : Iroha2Client(peerUrl, log, credentials, eventReadTimeoutInMills, eventReadMaxAttempts) {

    @JvmOverloads
    constructor(
        peerUrl: String,
        log: Boolean = false,
        credentials: String? = null,
        eventReadTimeoutInMills: Long = 250,
        eventReadMaxAttempts: Int = 10
    ) : this(URL(peerUrl), log, credentials, eventReadTimeoutInMills, eventReadMaxAttempts)

    /**
     * Send a request to Iroha2 and extract payload.
     * {@see Extractors}
     */
    fun <T> sendQueryAsync(
        queryAndExtractor: QueryAndExtractor<T>
    ): CompletableFuture<T> = future {
        sendQuery(queryAndExtractor)
    }

    /**
     * Send a transaction to an Iroha peer and wait until it is committed or rejected.
     */
    fun sendTransactionAsync(
        transaction: VersionedSignedTransaction
    ): CompletableFuture<ByteArray> = runBlocking {
        sendTransaction { transaction }.asCompletableFuture()
    }

    /**
     * Send a transaction to an Iroha peer without waiting for the final transaction status (committed or rejected).
     *
     * With this method, the state of the transaction is not tracked after the peer responses with 2xx status code,
     * which means that the peer accepted the transaction and the transaction passed the stateless validation.
     */
    fun fireAndForgetAsync(
        transaction: VersionedSignedTransaction
    ): CompletableFuture<ByteArray> = future {
        fireAndForget { transaction }
    }

    /**
     * Subscribe to track the transaction status
     */
    fun subscribeToTransactionStatusAsync(hash: ByteArray) = subscribeToTransactionStatus(hash).asCompletableFuture()
}
