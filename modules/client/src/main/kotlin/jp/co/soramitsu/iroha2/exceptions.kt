package jp.co.soramitsu.iroha2

/**
 * Thrown if query payload can not be extracted
 */
class QueryPayloadExtractionException(message: String? = null, cause: Throwable? = null) : IrohaSdkException(message, cause)

/**
 * Thrown if there is some unexpected state during WebSocket interaction
 */
class WebSocketProtocolException(message: String? = null, cause: Throwable? = null) : IrohaSdkException(message, cause)

/**
 * Thrown if peer is not available or status code not 2xx
 */
class IrohaClientException(message: String? = null, cause: Throwable? = null) : IrohaSdkException(message, cause)

/**
 * Thrown if transaction was rejected by peer
 */
class TransactionRejectedException(message: String? = null, cause: Throwable? = null) : IrohaSdkException(message, cause)

/**
 * Thrown if there is an exception related to cryptography
 */
class CryptoException(message: String? = null, cause: Throwable? = null) : IrohaSdkException(message, cause)

/**
 * Thrown if there is an exception during hex encoding/encoding
 */
class HexCodecException(message: String? = null, cause: Throwable? = null) : IrohaSdkException(message, cause)
