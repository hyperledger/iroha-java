package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.engine.IrohaTest
import jp.co.soramitsu.iroha2.engine.WithIroha
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Timeout(30)
class ClientTest : IrohaTest<AdminIroha2Client>() {

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
    fun getConfigValues(): Unit = runBlocking {
        val configs = client.getConfigs()
        assert(configs.containsKey("GENESIS"))
    }

    @Test
    @WithIroha
    fun describeConfig(): Unit = runBlocking {
        val docsConfig = client.describeConfig("genesis", "account_private_key")
        assert(docsConfig.isNotEmpty())

        // must throw ex if config's property name not provided
        assertFailsWith<IrohaClientException> {
            client.describeConfig()
        }
    }
}
