package jp.co.soramitsu.iroha2.utils

//Copied from Google Guava library(com.google.common.collect.Maps)

/**
 * The largest power of two that can be represented as an `int`.
 */
const val MAX_POWER_OF_TWO = 1 shl Integer.SIZE - 2

inline fun <T> hashSetWithSize(size: Int, action: () -> T): MutableSet<T> {
    val set = HashSet<T>(mapCapacity(size))
    for (index in 0 until size) {
        set.add(action())
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
