package jp.co.soramitsu.iroha2.utils

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import java.io.ByteArrayOutputStream

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
