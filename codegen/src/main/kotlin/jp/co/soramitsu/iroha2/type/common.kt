package jp.co.soramitsu.iroha2.type

import jp.co.soramitsu.iroha2.parse.TypeNest

abstract class Type(val name: String)

object BooleanType : Type("bool")

class MapType(name: String, key: TypeNest, value: TypeNest) : Type(name)

class EnumType(name: String, val variants: List<Variant>) : Type(name) {
    class Variant(val name: String, val discriminant: Int, val type: TypeNest?)
}

class TupleStructType(name: String, val types: List<TypeNest>) : Type(name)

class StructType(name: String, val mapping: LinkedHashMap<String, TypeNest>) : Type(name)

object StringType : Type("String")
