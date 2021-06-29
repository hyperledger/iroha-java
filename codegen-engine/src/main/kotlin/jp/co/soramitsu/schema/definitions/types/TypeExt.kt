package jp.co.soramitsu.schema.definitions.types

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import jp.co.soramitsu.schema.definitions.types.composite.Alias
import jp.co.soramitsu.schema.definitions.types.errors.EncodeDecodeException
import jp.co.soramitsu.schema.extensions.ensureExceptionType
import jp.co.soramitsu.schema.extensions.fromHex
import jp.co.soramitsu.schema.extensions.toHexString
import java.io.ByteArrayOutputStream

/**
 * @throws CyclicAliasingException
 */
fun Type<*>.skipAliases(): Type<*>? {
    if (this !is Alias) return this

    return aliasedReference.skipAliasesOrNull()?.value
}

fun Type<*>?.isFullyResolved() = this?.isFullyResolved ?: false

/**
 * @throws EncodeDecodeException
 */
fun <I> Type<I>.fromByteArray(byteArray: ByteArray): I {
    val reader = ScaleCodecReader(byteArray)

    return ensureUnifiedException { decode(reader) }
}

/**
 * @throws EncodeDecodeException
 */
fun <I> Type<I>.fromHex(hex: String): I {
    return ensureUnifiedException { fromByteArray(hex.fromHex()) }
}

fun <I> Type<I>.fromByteArrayOrNull(byteArray: ByteArray): I? {
    return runCatching { fromByteArray(byteArray) }.getOrNull()
}

fun <I> Type<I>.fromHexOrNull(hex: String): I? {
    return runCatching { fromHex(hex) }.getOrNull()
}

/**
 * @throws EncodeDecodeException
 */
fun <I> Type<I>.toByteArray(value: I): ByteArray {
    return ensureUnifiedException {
        useScaleWriter { encode(this, value) }
    }
}

/**
 * Type-unsafe version of [toByteArray]
 *
 * @throws EncodeDecodeException
 */
fun Type<*>.bytes(value: Any?): ByteArray {
    return ensureUnifiedException {
        useScaleWriter { encodeUnsafe(this, value) }
    }
}

fun <I> Type<I>.toByteArrayOrNull(value: I): ByteArray? {
    return runCatching { toByteArray(value) }.getOrNull()
}

fun Type<*>.bytesOrNull(value: Any?): ByteArray? {
    return runCatching { bytes(value) }.getOrNull()
}

/**
 * @throws EncodeDecodeException
 */
fun <I> Type<I>.toHex(value: I) =
    toByteArray(value).toHexString(withPrefix = true)

fun <I> Type<I>.toHexOrNull(value: I) =
    toByteArrayOrNull(value)?.toHexString(withPrefix = true)

fun useScaleWriter(use: ScaleCodecWriter.() -> Unit): ByteArray {
    val stream = ByteArrayOutputStream()
    val writer = ScaleCodecWriter(stream)

    writer.use()

    return stream.toByteArray()
}

private inline fun <R> ensureUnifiedException(block: () -> R): R {
    return ensureExceptionType(::EncodeDecodeException, block)
}
