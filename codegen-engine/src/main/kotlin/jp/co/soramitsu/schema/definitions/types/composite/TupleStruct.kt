package jp.co.soramitsu.schema.definitions.types.composite;

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.TypeReference

@Suppress("UNCHECKED_CAST")
public class TupleStruct(
    name: String,
    val types:List<TypeReference>
) : Type<TupleStruct.Instance>(name) {

    class Instance(val mapping: Map<String, Any?>) {
        inline operator fun <reified R> get(key: String): R? = mapping[key] as? R
    }

    override fun decode(scaleCodecReader: ScaleCodecReader): Instance {
        TODO("Not yet implemented")
    }

    override fun isValidInstance(instance: Any?): Boolean {
        if (instance !is Instance) return false

        return types.all { type ->
            type.requireValue().isValidInstance(instance)
        }
    }

    override val isFullyResolved: Boolean
        get() = types.all { it.isResolved() }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, value: Instance) {
        TODO("Not yet implemented")
    }
}
