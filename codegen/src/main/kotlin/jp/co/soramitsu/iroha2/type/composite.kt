package jp.co.soramitsu.iroha2.type

import jp.co.soramitsu.iroha2.parse.TypeNest

abstract class CompositeType(name: String, val generics: List<TypeNest>) : Type(name)

class EnumType(name: String, generics: List<TypeNest>, val variants: List<Variant>) :
    CompositeType(name, generics) {
    class Variant(val name: String, val discriminant: Int, val type: TypeNest?)

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

class TupleStructType(name: String, generics: List<TypeNest>, val types: List<TypeNest>) :
    CompositeType(name, generics) {
    override fun notResolvedTypes(): Set<String> {
        return types.union(generics)
            .flatMap { it.value?.notResolvedTypes() ?: setOf(it.name) }.toSet()
    }
}

class StructType(
    name: String,
    generics: List<TypeNest>,
    val mapping: LinkedHashMap<String, TypeNest>
) : CompositeType(name, generics) {
    override fun notResolvedTypes(): Set<String> {
        return mapping.values.union(generics)
            .flatMap { it.value?.notResolvedTypes() ?: setOf(it.name) }.toSet()
    }
}
