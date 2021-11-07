package jp.co.soramitsu.iroha2

import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.BigInteger

class Foo {

    @Test
    fun test() {
        println(BigInteger.valueOf(10000000).fromFixedPoint())
    }
}
