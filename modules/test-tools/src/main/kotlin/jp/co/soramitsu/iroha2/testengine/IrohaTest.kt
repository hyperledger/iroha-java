package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.AccountId
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
    val txTimeout: Duration = Duration.ofSeconds(30),
    val network: Network = Network.newNetwork(),
) {
    lateinit var client: T
    lateinit var containers: List<IrohaContainer>
    lateinit var account: AccountId
    lateinit var keyPair: KeyPair

    suspend fun Iroha2Client.tx(
        account: AccountId? = null,
        keyPair: KeyPair? = null,
        builder: TransactionBuilder.() -> Unit = {},
    ) {
        val finalAccountId = account ?: this@IrohaTest.account
        val finalKeyPair = keyPair ?: this@IrohaTest.keyPair

        this.sendTransaction {
            account(finalAccountId!!)
            builder(this)
            buildSigned(finalKeyPair!!)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }
}
