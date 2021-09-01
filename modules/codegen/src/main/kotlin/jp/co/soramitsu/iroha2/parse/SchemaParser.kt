package jp.co.soramitsu.iroha2.parse

import jp.co.soramitsu.iroha2.type.Type

typealias Types = Map<String, Type>
typealias Schema = Map<String, Any>

class SchemaParser {

    private val registry = HashMap<String, TypeNest>()
    private val resolver = TypeResolver(this)

    fun parse(schema: Schema): Types {
        val preprocessed = schema
            .map { (name, typeValue) -> createAndGetNest(name, typeValue) }
            .associateBy { it.name }
        val notResolvedTypes = preprocessed
            .flatMap { it.value.notResolvedTypes() }
            .toSet()
        if (notResolvedTypes.isNotEmpty()) {
            throw RuntimeException("Some types is not resolved: $notResolvedTypes")
        }
        return preprocessed.mapValues { it.value.requireValue() }
    }

    fun createAndGetNest(name: String, typeValue: Any? = null): TypeNest {
        return registry.getOrPut(name) { TypeNest(name, null) }
            .also {
                if (it.value == null) {
                    it.value = resolver.resolve(name, typeValue)
                }
            }
    }
}
