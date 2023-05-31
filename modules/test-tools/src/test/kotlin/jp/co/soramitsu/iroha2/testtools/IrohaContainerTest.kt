package jp.co.soramitsu.iroha2.testtools

import jp.co.soramitsu.iroha2.findFreePorts
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import kotlin.random.Random
import kotlin.test.assertEquals

@Timeout(60)
internal class IrohaContainerTest {

    @Test
    @Disabled
    // https://app.zenhub.com/workspaces/iroha-v2-60ddb820813b9100181fc060/issues/gh/hyperledger/iroha-java/338
    fun `findFreePorts returns unique free ports`(): Unit = runBlocking {
        val dList = mutableListOf<Deferred<List<Int>>>()
        repeat(10) {
            async {
                val all = mutableListOf<Int>()
                repeat(100) {
                    delay(Random.nextLong(10, 20))
                    all.addAll(findFreePorts(3, false))
                }
                all
            }.let { dList.add(it) }
        }

        val all = mutableListOf<Int>()
        dList.forEach { all.addAll(it.await()) }

        assertEquals(all.size, all.toSet().size)
    }
}
