package jp.co.soramitsu.schema.definitions.types.composite

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.TypeReference
import jp.co.soramitsu.schema.definitions.types.skipAliasesOrNull

class Tuple(name: String, val typeReferences: List<TypeReference>) : Type<List<*>>(name) {

    override fun decode(scaleCodecReader: ScaleCodecReader): List<*> {
        return typeReferences.map { it.requireValue().decode(scaleCodecReader) }
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, value: List<*>) {
        typeReferences.zip(value).onEach { (type, value) ->
            type.requireValue().encodeUnsafe(scaleCodecWriter, value)
        }
    }

    override fun isValidInstance(instance: Any?): Boolean {
        if (instance !is List<*>) return false

        val zipped = typeReferences.zip(instance)

        return zipped.size == typeReferences.size && zipped.all { (type, possibleValue) ->
            type.requireValue().isValidInstance(possibleValue)
        }
    }

    operator fun get(index: Int): Type<*>? = typeReferences[index].skipAliasesOrNull()?.value

    inline operator fun <reified R> get(index: Int): R? = get(index) as? R

    override val isFullyResolved: Boolean
        get() = typeReferences.all { it.isResolved() }
}
