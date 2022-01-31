package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.engine.IrohaRunnerExtension
import jp.co.soramitsu.iroha2.engine.WithIroha
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(IrohaRunnerExtension::class)
@Timeout(30)
class ClientTest {

    lateinit var client: Iroha2Client

    @Test
    @WithIroha
    fun health(): Unit = runBlocking {
        val health = client.health()
        assert(health == 200)
    }

    @Test
    @WithIroha
    fun status(): Unit = runBlocking {
        val status = client.status()
        println(status["blocks"] == 1)
    }

    @Test
    @WithIroha
    fun metrics(): Unit = runBlocking {
        val metrics = client.metrics()
        assert(metrics.isNotEmpty())
    }

    @Test
    @WithIroha
    fun configure(): Unit = runBlocking {
        val valueConfig = client.configuration<Map<String, *>>()
        assert(valueConfig.containsKey("GENESIS"))

        val docsConfig = client.configuration<String>(ConfigurationFieldType.DOCS, listOf("genesis"))
        assert(docsConfig.isNotEmpty())
    }
}
