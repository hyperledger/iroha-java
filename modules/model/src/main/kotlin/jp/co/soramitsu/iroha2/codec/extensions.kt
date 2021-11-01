package jp.co.soramitsu.iroha2.codec

import java.io.ByteArrayOutputStream

// TODO: get rid of providing `reader`
fun <T> ByteArray.decode(reader: ScaleReader<T>): T = ScaleCodecReader(this).read(reader)

// TODO: get rid of providing `writer`
fun <T> T.encode(writer: ScaleWriter<T>): ByteArray {
    // resource is freed inside `ScaleCodecWriter`
    val buffer = ByteArrayOutputStream()
    return ScaleCodecWriter(buffer)
        .use {
            writer.write(it, this)
            buffer.toByteArray()
        }
}

// TODO: regenerate models
// TODO: all in kotlin
// TODO: use when
