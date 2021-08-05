package jp.co.soramitsu.schema.definitions.types.composite

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.TypeReference
import jp.co.soramitsu.schema.definitions.types.skipAliases

@Suppress("UNCHECKED_CAST")
class Struct(
    name: String,
    val mapping: LinkedHashMap<String, TypeReference>
) : Type<Struct.Instance>(name) {

    class Instance(val mapping: Map<String, Any?>) {
        inline operator fun <reified R> get(key: String): R? = mapping[key] as? R
    }

    override fun decode(scaleCodecReader: ScaleCodecReader): Instance {
        val values = mapping.mapValues { (_, type) ->
            type.requireValue().decode(scaleCodecReader)
        }

        return Instance(values)
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, value: Instance) {
        mapping.forEach { (name, type) ->
            type.requireValue().encodeUnsafe(scaleCodecWriter, value[name])
        }
    }

    override fun isValidInstance(instance: Any?): Boolean {
        if (instance !is Instance) return false

        return mapping.all { (key, child) ->
            child.requireValue().isValidInstance(instance[key])
        }
    }

    inline operator fun <reified R : Type<*>> get(key: String): R? = mapping[key]?.value?.skipAliases() as? R

    override val isFullyResolved: Boolean
        get() = mapping.all { (_, ref) -> ref.isResolved() }
}
