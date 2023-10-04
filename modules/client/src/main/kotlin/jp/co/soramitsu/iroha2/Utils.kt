package jp.co.soramitsu.iroha2

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.core.DockerClientBuilder
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.IOException
import java.net.ServerSocket

val mutex = Mutex()
val taken = mutableSetOf<Int>()
val dockerClient: DockerClient = DockerClientBuilder.getInstance().build()

suspend fun findFreePorts(
    amount: Int,
    lock: Boolean = true,
): List<ServerSocket> {
    fun find(): ServerSocket {
        val busyPorts = dockerClient.listContainersCmd().exec().map { container ->
            container.ports.map { p -> p.publicPort }
        }.flatten()

        var socket: ServerSocket? = null
        try {
            socket = ServerSocket(0)
            val localPort = socket.localPort
            while (localPort in busyPorts || localPort in taken) {
                socket = ServerSocket(0)
            }
            return socket!!.also {
                it.reuseAddress = true
                taken.add(it.localPort)
            }
        } catch (e: IOException) {
            // Port not available
        } finally {
            socket?.close()
        }

        throw IrohaSdkException("Could not find a free port")
    }

    return when (lock) {
        true -> mutex.withLock { (0 until amount).map { find() } }
        false -> (0 until amount).map { find() }
    }
}
