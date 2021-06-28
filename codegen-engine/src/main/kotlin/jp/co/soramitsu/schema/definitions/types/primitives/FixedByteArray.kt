package jp.co.soramitsu.schema.definitions.types.primitives

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter

open class FixedByteArray(name: String, val length: Int) : Primitive<ByteArray>(name) {

    override fun decode(scaleCodecReader: ScaleCodecReader): ByteArray {
        return scaleCodecReader.readByteArray(length)
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, value: ByteArray) {
        return scaleCodecWriter.directWrite(value, 0, length)
    }

    override fun isValidInstance(instance: Any?): Boolean {
        return instance is ByteArray && instance.size == length
    }
}
