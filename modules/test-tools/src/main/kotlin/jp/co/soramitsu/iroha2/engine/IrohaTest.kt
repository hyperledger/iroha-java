package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.testcontainers.IrohaContainer
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.time.Duration

/**
 * Iroha2 Test
 */
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(IrohaRunnerExtension::class)
@Timeout(40)
abstract class IrohaTest<T : Iroha2Client>(
    val txTimeout: Duration = Duration.ofSeconds(10)
) {
    lateinit var client: T
    lateinit var containers: List<IrohaContainer>
}
