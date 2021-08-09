package jp.co.soramitsu.iroha2.utils

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import io.emeraldpay.polkaj.scale.reader.UInt128Reader
import org.bouncycastle.util.encoders.Hex
import java.io.ByteArrayOutputStream
import java.lang.Long.BYTES
import java.math.BigInteger

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
    val array  = BigInteger.valueOf(value).toByteArray()
    var pos = 0
    if (array[0].toInt() == 0) {
        ++pos
    }

    val len = array.size - pos
    if (len > 8) {
        throw IllegalArgumentException("Value is to big for 64 bits. Has: " + len * 8 + " bits")
    } else {
        val encoded = ByteArray(8)
        System.arraycopy(array, pos, encoded, encoded.size - len, len)
        UInt128Reader.reverse(encoded)
        writer.directWrite(encoded, 0, 8)
    }
}

fun ByteArray.hex() : String = Hex.toHexString(this)

fun String.hex() : ByteArray = Hex.decode(this)
