package jp.co.soramitsu.iroha2.codec

import java.math.BigInteger

enum class IntMax(val max: BigInteger) {
    U64(BigInteger.TWO.pow(64) - BigInteger.ONE),
    U128(BigInteger.TWO.pow(128) - BigInteger.ONE),
    U256(BigInteger.TWO.pow(256) - BigInteger.ONE),
    I128(BigInteger.TWO.pow(127) - BigInteger.ONE),
    I256(BigInteger.TWO.pow(255) - BigInteger.ONE), ;

    companion object {
        fun uintMaxValue(bit: Int): BigInteger {
            return when (bit) {
                64 -> U64
                128 -> U128
                256 -> U256
                else -> throw IllegalArgumentException("Unsupported type $bit")
            }.max
        }

        fun intMaxValue(bit: Int): BigInteger {
            return when (bit) {
                128 -> I128
                256 -> I256
                else -> throw IllegalArgumentException("Unsupported type $bit")
            }.max
        }
    }
}

enum class IntMin(val min: BigInteger) {
    I128(-BigInteger.TWO.pow(127)),
    I256(-BigInteger.TWO.pow(255));

    companion object {
        fun intMinValue(bit: Int): BigInteger {
            return when (bit) {
                128 -> I128
                256 -> I256
                else -> throw IllegalArgumentException("Unsupported type $bit")
            }.min
        }
    }
}
