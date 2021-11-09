package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import java.math.BigInteger

// Copied from Google Guava library(com.google.common.collect.Maps)

/**
 * The largest power of two that can be represented as an `int`.
 */
const val MAX_POWER_OF_TWO = 1 shl Integer.SIZE - 2

const val U32_MAX_VALUE = (2L shl 31) - 1

inline fun <T> hashSetWithSize(size: Int, supplier: () -> T): MutableSet<T> {
    val set = HashSet<T>(mapCapacity(size))
    for (index in 0 until size) {
        set.add(supplier())
    }
    return set
}

inline fun <K, V> hashMapWithSize(size: Int, key: () -> K, value: () -> V): MutableMap<K, V> {
    val map = HashMap<K, V>(mapCapacity(size))
    for (index in 0 until size) {
        map[key()] = value()
    }
    return map
}

fun mapCapacity(expectedSize: Int): Int {
    return when {
        expectedSize < 0 -> throw IllegalArgumentException("Expected size cannot be negative but was: $expectedSize")
        expectedSize < 3 -> expectedSize + 1
        expectedSize < MAX_POWER_OF_TWO -> (expectedSize.toFloat() / 0.75f + 1.0f).toInt()
        else -> Int.MAX_VALUE
    }
}

fun writeBit64(writer: ScaleCodecWriter, value: Long) = writeBit64(writer, value.toBigInteger())

fun writeBit64(writer: ScaleCodecWriter, value: BigInteger) {
    require(value >= BigInteger.ZERO) { "Negative values are not supported: $value" }
    val array = value.toByteArray()
    var pos = 0
    if (array[0].toInt() == 0) {
        ++pos
    }

    val len = array.size - pos
    if (len > 8) {
        throw IllegalArgumentException("Value is too big for 64 bits. Has: " + len * 8 + " bits")
    } else {
        val encoded = ByteArray(8)
        System.arraycopy(array, pos, encoded, encoded.size - len, len)
        reverse(encoded)
        writer.directWrite(encoded, 0, 8)
    }
}

fun readBit64(reader: ScaleCodecReader): Long {
    var result = 0L
    result += reader.readUByte().toLong()
    result += reader.readUByte().toLong() shl 8
    result += reader.readUByte().toLong() shl 16
    result += reader.readUByte().toLong() shl 24
    result += reader.readUByte().toLong() shl 32
    result += reader.readUByte().toLong() shl 40
    result += reader.readUByte().toLong() shl 48
    result += reader.readUByte().toLong() shl 56
    return result
}

fun wrapException(ex: Exception): Exception {
    return when (ex) {
        is ScaleCodecException -> ex
        else -> ScaleCodecException(cause = ex)
    }
}

fun reverse(value: ByteArray) {
    for (i in 0 until value.size / 2) {
        val other = value.size - i - 1
        val tmp = value[other]
        value[other] = value[i]
        value[i] = tmp
    }
}
