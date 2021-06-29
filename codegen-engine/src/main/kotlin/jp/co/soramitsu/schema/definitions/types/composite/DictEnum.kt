package jp.co.soramitsu.schema.definitions.types.composite

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.TypeReference
import jp.co.soramitsu.schema.definitions.types.errors.EncodeDecodeException
import jp.co.soramitsu.schema.definitions.types.skipAliasesOrNull

open class DictEnum(
    name: String,
    val elements: List<Entry<TypeReference>>
) : Type<DictEnum.Entry<Any?>>(name) {

    class Entry<out T>(val name: String, val value: T)

    override fun decode(scaleCodecReader: ScaleCodecReader): Entry<Any?> {
        val typeIndex = scaleCodecReader.readByte()
        val entry = elements[typeIndex.toInt()]

        val decoded = entry.value.requireValue().decode(scaleCodecReader)

        return Entry(entry.name, decoded)
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, value: Entry<Any?>) {
        val index = elements.indexOfFirst { it.name == value.name }

        if (index == -1) elementNotFound(value.name)

        val type = elements[index].value.requireValue()

        scaleCodecWriter.writeByte(index)
        type.encodeUnsafe(scaleCodecWriter, value.value)
    }

    override fun isValidInstance(instance: Any?): Boolean {
        if (instance !is Entry<*>) return false

        val elementEntry = elements.find { it.name == instance.name } ?: return false

        return elementEntry.value.requireValue().isValidInstance(instance.value)
    }

    operator fun get(name: String): Type<*>? {
        return elements.find { it.name == name }?.value?.skipAliasesOrNull()?.value
    }

    override val isFullyResolved: Boolean
        get() = elements.all { it.value.isResolved() }

    protected fun elementNotFound(name: String) {
        throw EncodeDecodeException("No $name in ${elements.map(Entry<*>::name)}")
    }
}
