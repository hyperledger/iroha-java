package jp.co.soramitsu.iroha2.type

import jp.co.soramitsu.iroha2.parse.TypeNest

class EnumType(name: String, val variants: List<Variant>) : Type(name) {
    class Variant(val name: String, val discriminant: Int, val type: TypeNest?)

    override fun notResolvedTypes() = variants.flatMap { it.type?.notResolvedTypes() ?: setOf() }.toSet()
}

class TupleStructType(name: String, val types: List<TypeNest>) : Type(name) {
    override fun notResolvedTypes() = types.flatMap(TypeNest::notResolvedTypes).toSet()
}

class StructType(name: String, val mapping: LinkedHashMap<String, TypeNest>) : Type(name) {
    override fun notResolvedTypes() = mapping.values.flatMap(TypeNest::notResolvedTypes).toSet()

}
