package jp.co.soramitsu.iroha2.type

import jp.co.soramitsu.iroha2.parse.TypeNest

abstract class CompositeType(override val name: String, open val generics: List<TypeNest>) : Type(name)

data class EnumType(
    override val name: String,
    override val generics: List<TypeNest>,
    val variants: List<Variant>
) : CompositeType(name, generics) {

    data class Variant(val name: String, val discriminant: Int, val type: TypeNest?, var generics: List<TypeNest> = listOf())

    private var resolutionInProgress: Boolean = false

    override fun notResolvedTypes(): Set<String> {
        if (resolutionInProgress) {
            return setOf()
        }
        resolutionInProgress = true
        val result = generics.union(variants.mapNotNull { it.type })
            .flatMap { it.value?.notResolvedTypes() ?: setOf(it.name) }
            .toSet()
        resolutionInProgress = false
        return result
    }
}

data class TupleStructType(
    override val name: String,
    override val generics: List<TypeNest>,
    val types: List<TypeNest>
) : CompositeType(name, generics) {
    override fun notResolvedTypes(): Set<String> {
        return types.union(generics).flatMap {
            it.value?.notResolvedTypes() ?: setOf(it.name)
        }.toSet()
    }
}

data class StructType(
    override val name: String,
    override val generics: List<TypeNest>,
    val mapping: LinkedHashMap<String, TypeNest>
) : CompositeType(name, generics) {
    override fun notResolvedTypes(): Set<String> {
        return mapping.values.union(generics).flatMap {
            it.value?.let { setOf() } ?: setOf(it.name)
        }.toSet()
    }
}
