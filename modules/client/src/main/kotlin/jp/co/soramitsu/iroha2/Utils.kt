package jp.co.soramitsu.iroha2

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.core.DockerClientBuilder
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.net.ServerSocket

val mutex = Mutex()
val dockerClient: DockerClient = DockerClientBuilder.getInstance().build()

suspend fun findFreePorts(
    amount: Int,
    lock: Boolean = true
): List<Int> {
    fun find(): List<Int> {
        val busyPorts = dockerClient.listContainersCmd().exec().map { container ->
            container.ports.map { p -> p.publicPort }
        }.flatten()

        return (0 until amount).map {
            var socket = ServerSocket(0)
            while (socket.localPort in busyPorts) {
                socket = ServerSocket(0)
            }
            socket.soTimeout = 300 // seconds
            socket.use { it.localPort }
        }
    }

    return when (lock) {
        true -> mutex.withLock { find() }
        false -> find()
    }
}
