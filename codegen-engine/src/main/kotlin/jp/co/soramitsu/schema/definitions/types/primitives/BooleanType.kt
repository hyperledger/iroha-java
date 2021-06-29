package jp.co.soramitsu.schema.definitions.types.primitives

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter

object BooleanType : Primitive<Boolean>("bool") {

    override fun decode(scaleCodecReader: ScaleCodecReader): Boolean {
        return scaleCodecReader.readBoolean()
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, value: Boolean) {
        scaleCodecWriter.write(ScaleCodecWriter.BOOL, value)
    }

    override fun isValidInstance(instance: Any?) = instance is Boolean
}
