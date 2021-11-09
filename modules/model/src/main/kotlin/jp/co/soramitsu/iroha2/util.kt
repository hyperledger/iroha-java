package jp.co.soramitsu.iroha2

/**
 * The largest power of two that can be represented as an `int`.
 */
const val MAX_POWER_OF_TWO = 1 shl Integer.SIZE - 2

/**
 * Max unsigned zero-based value what can be represented by 32 bit
 O*/
const val U32_MAX_VALUE = (1L shl 32) - 1

fun wrapException(ex: Exception): Exception {
    return when (ex) {
        is ScaleCodecException -> ex
        else -> ScaleCodecException(cause = ex)
    }
}
