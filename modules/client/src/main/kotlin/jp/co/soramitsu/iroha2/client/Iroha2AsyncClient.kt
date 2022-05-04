package jp.co.soramitsu.iroha2.client

import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.query.QueryAndExtractor
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.future.future
import kotlinx.coroutines.runBlocking
import java.net.URL
import java.util.concurrent.CompletableFuture

/**
 * Extension of [Iroha2Client] for usage in Java
 */
@Suppress("unused")
class Iroha2AsyncClient @JvmOverloads constructor(
    peerUrl: URL,
    log: Boolean = false,
    eventReadTimeoutInMills: Long = 250,
    eventReadMaxAttempts: Int = 10
) : Iroha2Client(peerUrl, log, eventReadTimeoutInMills, eventReadMaxAttempts) {

    @JvmOverloads
    constructor(
        peerUrl: String,
        log: Boolean = false,
        eventReadTimeoutInMills: Long = 250,
        eventReadMaxAttempts: Int = 10
    ) : this(URL(peerUrl), log, eventReadTimeoutInMills, eventReadMaxAttempts)

    fun <T> sendQueryAsync(
        queryAndExtractor: QueryAndExtractor<T>
    ): CompletableFuture<T> = future {
        sendQuery(queryAndExtractor)
    }

    fun sendTransactionAsync(
        transaction: VersionedTransaction
    ): CompletableFuture<ByteArray> = runBlocking {
        sendTransaction { transaction }.asCompletableFuture()
    }

    fun fireAndForgetAsync(
        transaction: VersionedTransaction
    ): CompletableFuture<ByteArray> = future {
        fireAndForget { transaction }
    }

    fun subscribeToTransactionStatusAsync(hash: ByteArray) = subscribeToTransactionStatus(hash).asCompletableFuture()
}
