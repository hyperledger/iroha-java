package jp.co.soramitsu.schema.definitions.types.errors

class EncodeDecodeException(
    message: String? = null,
    cause: Exception? = null
) : Exception(message) {

    constructor(cause: Exception) : this(
        message = cause.message,
        cause = cause
    )
}