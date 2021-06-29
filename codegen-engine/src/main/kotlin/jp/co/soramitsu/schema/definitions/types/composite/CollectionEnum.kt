package jp.co.soramitsu.schema.definitions.types.composite

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.scale.dataType.CollectionEnumType

class CollectionEnum(
    name: String,
    val elements: List<String>
) : Type<String>(name) {

    override fun decode(scaleCodecReader: ScaleCodecReader): String {
        return CollectionEnumType(elements).read(scaleCodecReader)
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, value: String) {
        CollectionEnumType(elements).write(scaleCodecWriter, value)
    }

    override fun isValidInstance(instance: Any?): Boolean {
        return instance in elements
    }

    operator fun get(key: Int): String = elements[key]

    override val isFullyResolved = true
}
