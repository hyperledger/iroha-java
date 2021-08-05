package jp.co.soramitsu.schema.extensions

inline fun <reified T : Exception, R> ensureExceptionType(
    creator: (Exception) -> T,
    block: () -> R
): R {
    return try {
        block()
    } catch (e: Exception) {
        if (e is T) {
            throw e
        } else {
            throw creator(e)
        }
    }
}