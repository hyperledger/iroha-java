package jp.co.soramitsu.iroha2.parse

import jp.co.soramitsu.iroha2.Schema
import jp.co.soramitsu.iroha2.type.Type

typealias Types = Map<String, Type?>

object SchemaParser {

    private val registry = HashMap<String, TypeNest>()
    private val resolver = TypeResolver(this)

    fun parse(schema: Schema): Types {
        val preprocessed = schema
            .map { (name, typeValue) -> createAndGet(name, typeValue) }
            .toSet()
        return preprocessed
            .associateBy(TypeNest::name) {it.requireValue()}
    }

    fun getOrCreate(name : String): TypeNest {
        return registry.getOrPut(name) { TypeNest(name, null) }
    }

    private fun createAndGet(name: String, typeValue: Any) : TypeNest {
        return resolver.resolve(name, typeValue).also { registry[name] = it }
    }
}
