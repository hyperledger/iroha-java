package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.testcontainers.IrohaContainer
import jp.co.soramitsu.iroha2.utils.generateKeyPair
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@Execution(ExecutionMode.CONCURRENT)
@Timeout(1)
class InstructionsTest {

    lateinit var client: Iroha2Client
    lateinit var irohaContainer: IrohaContainer
    private val testKeyPair = generateKeyPair();

    @BeforeEach
    fun init() {
        irohaContainer = IrohaContainer()
        irohaContainer.start()
        client = Iroha2Client(irohaContainer.getApiUrl())
    }

    @AfterEach
    fun tearDown() {
        irohaContainer.stop()
        client.close()
    }

    @Test
    fun `register instruction committed`() {
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            this.buildSigned(testKeyPair)
        }
    }
}
