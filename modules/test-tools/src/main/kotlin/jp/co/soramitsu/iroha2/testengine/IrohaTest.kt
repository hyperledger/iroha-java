package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.IrohaSdkException
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder
import kotlinx.coroutines.time.withTimeout
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.testcontainers.containers.Network
import java.security.KeyPair
import java.time.Duration

/**
 * Iroha2 Test
 */
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(IrohaRunnerExtension::class)
@Timeout(120)
abstract class IrohaTest<T : Iroha2Client>(
    val txTimeout: Duration = Duration.ofSeconds(10),
    val network: Network = Network.newNetwork(),
    private val testAccount: AccountId? = null,
    private val testKeyPair: KeyPair? = null
) {
    lateinit var client: T
    lateinit var containers: List<IrohaContainer>

    suspend fun Iroha2Client.tx(
        account: AccountId? = null,
        keyPair: KeyPair? = null,
        builder: TransactionBuilder.() -> Unit = {}
    ) {
        val finalAccountId = account ?: testAccount ?: throw IrohaSdkException("Test account wasn't set")
        val finalKeyPair = keyPair ?: testKeyPair ?: throw IrohaSdkException("Test account key pair wasn't set")

        this.sendTransaction {
            account(finalAccountId)
            builder(this)
            buildSigned(finalKeyPair)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }
}
