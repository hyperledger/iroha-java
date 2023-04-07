package jp.co.soramitsu.iroha2

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.IOException
import java.net.ServerSocket

val mutex = Mutex()

suspend fun findFreePorts(
    amount: Int,
    lock: Boolean = true
): List<Int> {
    fun find() = (0 until amount).map {
        try {
            val socket = ServerSocket(0)
            socket.soTimeout = 300 // seconds
            socket.use { it.localPort }
        } catch (e: IOException) {
            throw e
        }
    }

    return when (lock) {
        true -> mutex.withLock { find() }
        false -> find()
    }
}
