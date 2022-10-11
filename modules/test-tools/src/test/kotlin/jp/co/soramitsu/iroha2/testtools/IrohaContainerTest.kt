package jp.co.soramitsu.iroha2.testtools

import jp.co.soramitsu.iroha2.testengine.IrohaContainer
import org.junit.jupiter.api.Timeout
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Timeout(30)
internal class IrohaContainerTest {

    @Test
    fun `check container can run and stop`() {
        val sut = IrohaContainer()

        assertFalse { sut.isCreated }
        assertFalse { sut.isRunning }

        sut.start()

        assertTrue { sut.isCreated }
        assertTrue { sut.isRunning }

        sut.stop()

        assertFalse { sut.isCreated }
        assertFalse { sut.isRunning }
    }
}
