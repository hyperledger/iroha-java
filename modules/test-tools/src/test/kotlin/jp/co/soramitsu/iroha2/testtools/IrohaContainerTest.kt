package jp.co.soramitsu.iroha2.testtools

import jp.co.soramitsu.iroha2.findFreePorts
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.net.ServerSocket
import kotlin.random.Random
import kotlin.test.assertEquals

@Timeout(60)
internal class IrohaContainerTest {

    @Test
    @Disabled
    fun `findFreePorts returns unique free ports`(): Unit = runBlocking {
        val dList = mutableListOf<Deferred<List<ServerSocket>>>()
        repeat(10) {
            async {
                val all = mutableListOf<ServerSocket>()
                repeat(100) {
                    delay(Random.nextLong(10, 20))
                    all.addAll(findFreePorts(3, false))
                }
                all
            }.let { dList.add(it) }
        }

        val all = mutableListOf<ServerSocket>()
        dList.forEach { all.addAll(it.await()) }

        assertEquals(all.size, all.toSet().size)
    }
}
