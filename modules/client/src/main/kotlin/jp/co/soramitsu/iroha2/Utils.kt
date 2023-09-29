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

data class PortToSocket(
    val port: Int,
    val socket: ServerSocket,
)

suspend fun findFreePorts(
    amount: Int,
    lock: Boolean = true,
    retries: Int = 3,
): List<PortToSocket> {
    fun find(): PortToSocket {
        val busyPorts = dockerClient.listContainersCmd().exec().map { container ->
            container.ports.map { p -> p.publicPort }
        }.flatten()

        var socket: ServerSocket? = null
        var retriesLeft = retries

        while (retriesLeft > 0) {
            try {
                socket = ServerSocket(0)
                while (socket?.localPort in busyPorts || socket?.localPort in taken) {
                    socket = ServerSocket(0)
                }
                return PortToSocket(socket!!.localPort, socket).also { taken.add(it.port) }
            } catch (e: IOException) {
                // Port not available
            } finally {
                socket?.close() // To let docker take it
            }
            retriesLeft--
        }

        throw IrohaSdkException("Could not find a free port")
    }

    return when (lock) {
        true -> mutex.withLock { (0 until amount).map { find() } }
        false -> (0 until amount).map { find() }
    }
}
