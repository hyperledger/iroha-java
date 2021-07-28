package jp.co.soramitsu.iroha2.type

import jp.co.soramitsu.iroha2.parse.TypeNest

sealed class Type(val name: String) {
    open fun notResolvedTypes() : Set<String> = setOf()
}

object BooleanType : Type("bool")

class MapType(name: String, val key: TypeNest, val value: TypeNest) : Type(name) {
    override fun notResolvedTypes(): Set<String> {
        val result = mutableSetOf<String>()
        if (key.value == null) {
            result.add(key.name)
        } else if (value.value == null) {
            result.add(value.name)
        }
        return result
    }
}

object StringType : Type("String")
