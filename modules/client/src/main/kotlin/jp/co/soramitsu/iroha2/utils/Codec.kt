package jp.co.soramitsu.iroha2.utils

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import org.bouncycastle.util.encoders.Hex
import java.io.ByteArrayOutputStream

// todo get rid of providing `ScaleWriter`
fun <T> T.encode(writer: ScaleWriter<T>): ByteArray {
    // resource is freed inside `ScaleCodecWriter`
    val buffer = ByteArrayOutputStream()
    return ScaleCodecWriter(buffer)
        .use {
            writer.write(it, this)
            buffer.toByteArray()
        }
}

// todo get rid of providing `reader`
fun <T> ByteArray.decode(reader: ScaleReader<T>): T = ScaleCodecReader(this).read(reader)

fun ByteArray.hex(): String = Hex.toHexString(this)

fun String.hex(): ByteArray = Hex.decode(this)
