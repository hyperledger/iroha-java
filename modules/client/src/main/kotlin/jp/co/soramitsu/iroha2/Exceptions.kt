package jp.co.soramitsu.iroha2

open class IrohaException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)

class QueryPayloadExtractionException(message: String? = null, cause: Throwable? = null) : IrohaException(message, cause)
class IrohaClientException(message: String? = null, cause: Throwable? = null) : IrohaException(message, cause)
