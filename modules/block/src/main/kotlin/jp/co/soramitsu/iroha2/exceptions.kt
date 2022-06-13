package jp.co.soramitsu.iroha2

/**
 * Throw if there is an issue with deserialization.
 * 
 * @param message The explanation of the issue
 */
class DeserializationException(message: String) : RuntimeException(message)

/**
 * Throw if there is an issue with serialization
 * 
 * @param message The explanation of the issue
 */
class SerializationException(message: String) : RuntimeException(message)
