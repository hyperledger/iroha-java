package jp.co.soramitsu.iroha2

import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.ZERO
import java.math.BigInteger
import java.math.RoundingMode.DOWN

//
// Minimal implementation of [fixnum](https://github.com/loyd/fixnum) in Kotlin
//

/**
 * Default scale used in Iroha2
 *
 * @see https://github.com/hyperledger/iroha/blob/53190dfb1478bfe67e12fecd91af3e9de7b6adc2/data_model/src/lib.rs#L1092
 */
const val DEFAULT_SCALE = 9

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

/**
 * Convert fixed point number to [BigDecimal]
 *
 * @see https://github.com/loyd/fixnum/blob/77860b04eb53a2e001b3b97fe3601833e18b01b9/src/lib.rs#L581
 */
fun BigInteger.fromFixedPoint(scale: Int = DEFAULT_SCALE): BigDecimal = try {
    BigDecimal(this)
        .divide(BigDecimal(POWERS_OF_10[scale]))
        .stripTrailingZeros()
} catch (ex: Exception) {
    throw FixedPointConversionException("Could not convert from fixed point", ex)
}

/**
 * Convert [BigDecimal] to fixed point number
 *
 * @see https://github.com/loyd/fixnum/blob/77860b04eb53a2e001b3b97fe3601833e18b01b9/src/lib.rs#L688
 */
fun BigDecimal.toFixedPoint(scale: Int = DEFAULT_SCALE): BigInteger = try {
    val thisZeroStripped = this.stripTrailingZeros().abs()
    if (thisZeroStripped.scale() > scale) {
        throw FixedPointConversionException(
            "Scale of the original floating point number is ${thisZeroStripped.scale()}" +
                " and it is greater than fixed point number scale $scale. Need to decrease scale of the original floating point"
        )
    }
    val coef = BigDecimal.valueOf(POWERS_OF_10[scale])
    // leaves only fractional part
    val fractional = thisZeroStripped.remainder(ONE).abs()
    if (fractional.equals(ZERO)) {
        thisZeroStripped.multiply(coef)
    } else {
        val fractionalScale = fractional.scale()
        val exp = BigDecimal(POWERS_OF_10[kotlin.math.max(0, fractionalScale)])
        // leaves only integral parts
        val integral = thisZeroStripped.setScale(0, DOWN)
        val finalIntegral = integral.multiply(coef)

        val finalFractional = coef.divide(exp)
            .multiply(fractional.scaleByPowerOfTen(fractionalScale))
        finalIntegral
            .add(finalFractional)
    }.multiply(BigDecimal(this.signum()))
        .toBigIntegerExact()
} catch (ex: Exception) {
    when (ex) {
        is FixedPointConversionException -> throw ex
        else -> throw FixedPointConversionException("Could not convert to fixed point number", ex)
    }
}
