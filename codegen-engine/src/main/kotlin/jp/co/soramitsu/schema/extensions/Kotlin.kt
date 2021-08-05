package jp.co.soramitsu.schema.extensions

import java.math.BigInteger
import java.nio.ByteOrder

inline fun <T, R> Iterable<T>.tryFindNonNull(transform: (T) -> R?): R? {
    for (item in this) {
        val transformed = transform(item)

        if (transformed != null) return transformed
    }

    return null
}

fun ByteArray.toBigInteger(byteOrder: ByteOrder = ByteOrder.BIG_ENDIAN): BigInteger {
    val preprocessed = if (byteOrder == ByteOrder.LITTLE_ENDIAN) reversedArray() else this

    return BigInteger(preprocessed)
}