package jp.co.soramitsu.iroha2.utils

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import java.io.ByteArrayOutputStream
import java.lang.Long.BYTES

fun <T> encode(writer: ScaleWriter<T>, instance: T): ByteArray {
    //resource is freed inside `ScaleCodecWriter`
    val buffer = ByteArrayOutputStream()
    return ScaleCodecWriter(buffer)
        .use {
            writer.write(it, instance)
            buffer.toByteArray()
        }
}

fun <T> decode(reader: ScaleReader<T>, source: ByteArray): T = ScaleCodecReader(source).read(reader)

fun writeUint64(writer: ScaleCodecWriter, value: Long) {
    require(value >= 0) { "Negative values are not supported: $value" }
    writer.directWrite(longAsBytes(value and 255), 0, BYTES)
    writer.directWrite(longAsBytes(value shr 8 and 255), 0, BYTES)
    writer.directWrite(longAsBytes(value shr 16 and 255), 0, BYTES)
    writer.directWrite(longAsBytes(value shr 24 and 255), 0, BYTES)
    writer.directWrite(longAsBytes(value shr 32 and 255), 0, BYTES)
}

fun longAsBytes(value: Long) : ByteArray {
    var mutableValue = value
    val result = ByteArray(BYTES)
    for (i in BYTES - 1 downTo 0) {
        result[i] = (mutableValue and 0xFF).toByte()
        mutableValue = mutableValue shr java.lang.Byte.SIZE
    }
    return result
}
