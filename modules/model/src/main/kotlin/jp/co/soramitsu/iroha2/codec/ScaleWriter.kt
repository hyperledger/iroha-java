package jp.co.soramitsu.iroha2.codec

import java.io.ByteArrayOutputStream

interface ScaleWriter<T> {

    fun write(writer: ScaleCodecWriter, instance: T)

    fun encode(data: T): ByteArray {
        // resource is freed inside `ScaleCodecWriter`
        val buffer = ByteArrayOutputStream()
        val scaleCodecWriter = ScaleCodecWriter(buffer)
        this.write(scaleCodecWriter, data)
        return buffer.toByteArray()
    }
}
