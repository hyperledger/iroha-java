package jp.co.soramitsu.iroha2

import java.math.BigDecimal
import java.math.BigInteger

//
// Minimal implementation o [fixnum](https://github.com/loyd/fixnum) in Kotlin
//

val POWERS_OF_10: Array<Long> by lazy {
    arrayOf(
        1,
        10,
        100,
        1_000,
        10_000,
        100_000,
        1_000_000,
        10_000_000,
        100_000_000,
        1_000_000_000,
        10_000_000_000,
        100_000_000_000,
        1_000_000_000_000,
        10_000_000_000_000,
        100_000_000_000_000,
        1_000_000_000_000_000,
        10_000_000_000_000_000,
        100_000_000_000_000_000,
        1_000_000_000_000_000_000,
    )
}

fun BigInteger.fromFixedPoint(precision: Int = 9): BigDecimal {
    val coef = BigDecimal.valueOf(POWERS_OF_10[precision])
    val thisBigDecimal = this.toBigDecimal()
    val integral = thisBigDecimal.divide(coef)
    val fractional = thisBigDecimal.remainder(coef).divide(coef)
    return integral.add(fractional)
}

fun BigDecimal.toFixedPoint(precision: Long = 9): BigInteger {
    val finalIntegral = BigInteger.ONE
    return finalIntegral
}
