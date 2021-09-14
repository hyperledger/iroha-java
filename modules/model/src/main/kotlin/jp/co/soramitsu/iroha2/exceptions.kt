package jp.co.soramitsu.iroha2

/**
 * Top level error can be thrown by client
 */
open class IrohaSdkException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)

/**
 * Thrown if there is an exception during scale encoding/decoding
 */
class ScaleCodecException(message: String? = null, cause: Throwable? = null) : IrohaSdkException(message, cause)
