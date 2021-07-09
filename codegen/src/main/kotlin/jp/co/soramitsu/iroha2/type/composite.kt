package jp.co.soramitsu.iroha2.type

import jp.co.soramitsu.iroha2.parse.TypeNest

class EnumType(name: String, val variants: List<Variant>) : Type(name) {
    class Variant(val name: String, val discriminant: Int, val type: TypeNest?)

    override fun notResolvedTypes() = variants.flatMap {
        if(it.type != null && it.type.value == null) {
            setOf(it.type.name)
        } else setOf()
    }.toSet()
}

class TupleStructType(name: String, val types: List<TypeNest>) : Type(name) {
    override fun notResolvedTypes() = types.flatMap {
        if (it.value == null) {
            setOf(it.name)
        } else {
            setOf()
        }
    }.toSet()
}

class StructType(name: String, val mapping: LinkedHashMap<String, TypeNest>) : Type(name) {
    override fun notResolvedTypes() = mapping.values.flatMap{
        if (it.value == null) {
            setOf(it.name)
        } else {
            setOf()
        }
    }.toSet()
}
