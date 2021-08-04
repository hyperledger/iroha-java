package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testcontainers.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.testcontainers.IrohaContainer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@Execution(ExecutionMode.CONCURRENT)
// @Timeout(10)
class InstructionsTest {

    lateinit var client: Iroha2Client
    lateinit var irohaContainer: IrohaContainer

    @BeforeEach
    fun init() {
        irohaContainer = IrohaContainer()
        irohaContainer.start()
        client = Iroha2Client(irohaContainer.getApiUrl())
    }

    @AfterEach
    fun tearDown() {
        irohaContainer.stop()
    }

    @Test
    fun `register instruction committed`() {
        Assertions.assertDoesNotThrow {
            client.sendTransactionAsync {
                accountId = ALICE_ACCOUNT_ID
                buildSigned(ALICE_KEYPAIR)
            }.join()
        }

    }
}
