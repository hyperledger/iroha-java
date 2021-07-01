package jp.co.soramitsu.schema.definitions.types.composite

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.TypeReference

@Suppress("UNCHECKED_CAST")
class EnumType(
    name: String,
    val variants: List<Variant>
) : Type<EnumType>(name) {

    data class Variant(val name: String, val discriminant: Int, val type: TypeReference?)


    override fun decode(scaleCodecReader: ScaleCodecReader): EnumType {
        TODO("Not yet implemented")
    }

    override fun isValidInstance(instance: Any?): Boolean {
        if (instance !is EnumType) return false

        return variants.all { it.type?.value?.isValidInstance(instance) ?: true }
    }

    override val isFullyResolved: Boolean
        get() = variants.all { it.type?.isResolved()?: true }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, value: EnumType) {
        TODO("Not yet implemented")
    }
}
