package jp.co.soramitsu.iroha2.parse

import jp.co.soramitsu.iroha2.Schema

typealias Types = Map<String, Type?>

object SchemaParser {

    private val registry = HashMap<String, TypeNest>()
    private val resolver = TypeResolver(this)

    fun parse(schema: Schema): Types {
        val preprocessed = schema
            .map { (name, typeValue) -> createAndGet(name, typeValue) }
            .toSet()
        preprocessed.forEach (::println)
        return preprocessed
            .onEach (::println)
            .associateBy(TypeNest::name, TypeNest::value)
    }


    fun getOrCreate(name : String): TypeNest {
        return registry.getOrPut(name) { TypeNest(name, null) }
    }

    private fun createAndGet(name: String, typeValue: Any) : TypeNest {
        return resolver.resolve(name, typeValue).also { registry[name] = it }
    }
}
