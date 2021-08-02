package jp.co.soramitsu.iroha2

import com.github.dockerjava.zerodep.shaded.org.apache.commons.codec.binary.Hex
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Transaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction._VersionedTransactionV1
import jp.co.soramitsu.iroha2.testcontainers.IrohaContainer
import jp.co.soramitsu.iroha2.utils.encode
import jp.co.soramitsu.iroha2.utils.generateKeyPair
import jp.co.soramitsu.iroha2.utils.hash
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.time.Duration

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
        val transaction = VersionedTransaction.V1(
            _VersionedTransactionV1(
                Transaction(
                    signatures = mutableListOf(),
                    payload = Payload(
                        ALICE_ACCOUNT_ID,
                        mutableListOf(),
                        System.currentTimeMillis().toULong(),
                        10U,
                        mutableMapOf()
                    )
                )
            )
        )
        System.err.println(Hex.encodeHexString(hash(encode(VersionedTransaction, transaction))))
        client.sendTransaction(transaction)
    }
}
