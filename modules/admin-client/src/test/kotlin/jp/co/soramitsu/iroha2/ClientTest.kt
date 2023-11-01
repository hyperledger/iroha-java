package jp.co.soramitsu.iroha2

import io.ktor.http.HttpStatusCode
import jp.co.soramitsu.iroha2.testengine.IrohaTest
import jp.co.soramitsu.iroha2.testengine.WithIroha
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Timeout(120)
class ClientTest : IrohaTest<AdminIroha2Client>() {

    @Test
    @WithIroha
    fun health(): Unit = runBlocking {
        val health = client.health()
        assert(health == HttpStatusCode.OK.value)
    }

    @Test
    @WithIroha
    fun status(): Unit = runBlocking {
        val status = client.status()
        assertEquals(1, status.blocks)
    }

    @Test
    @WithIroha
    fun metrics(): Unit = runBlocking {
        val metrics = client.metrics()
        assert(metrics.isNotEmpty())
    }

    @Test
    @WithIroha
    @Disabled
    fun getConfigValues(): Unit = runBlocking {
        val configs = client.getConfigs()
        assert(configs.containsKey("GENESIS"))
    }

    @Test
    @WithIroha
    @Disabled
    fun describeConfig(): Unit = runBlocking {
        val docsConfig = client.describeConfig("genesis", "account_private_key")
        assert(docsConfig.isNotEmpty())

        // must throw ex if config's property name not provided
        assertFailsWith<IrohaClientException> {
            client.describeConfig()
        }
    }

    @Test
    @WithIroha(amount = PEER_AMOUNT)
    fun `round-robin load balancing test`(): Unit = runBlocking {
        val urls = mutableSetOf<String>()
        repeat(PEER_AMOUNT * 2 + 1) {
            val config = client.getConfigs()
            urls.add(config["TORII"]!!.cast<Map<String, String>>()["API_URL"]!!)
        }

        assertEquals(PEER_AMOUNT, urls.size)
    }

    companion object {
        private const val PEER_AMOUNT = 4
    }
}
