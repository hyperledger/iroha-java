package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.client.Iroha2Client
import org.junit.jupiter.api.extension.ExtendWith
import java.time.Duration

@ExtendWith(IrohaRunnerExtension::class)
abstract class IrohaTest<T : Iroha2Client>(
    val txTimeout: Duration = Duration.ofSeconds(10)
) {
    lateinit var client: T
}
