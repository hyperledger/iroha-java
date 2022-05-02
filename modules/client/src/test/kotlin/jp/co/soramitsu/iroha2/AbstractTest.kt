package jp.co.soramitsu.iroha2

import java.time.Duration

abstract class AbstractTest {
    val txTimeout: Duration = Duration.ofSeconds(10)
}
