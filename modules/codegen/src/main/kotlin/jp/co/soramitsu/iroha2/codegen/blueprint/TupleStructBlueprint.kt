package jp.co.soramitsu.iroha2.codegen.blueprint

import jp.co.soramitsu.iroha2.codegen.defineClassName
import jp.co.soramitsu.iroha2.codegen.resolveKotlinType
import jp.co.soramitsu.iroha2.type.ArrayType
import jp.co.soramitsu.iroha2.type.TupleStructType
import jp.co.soramitsu.iroha2.type.Type

/**
 * Blueprint for [TupleStructType]
 */
class TupleStructBlueprint(type: TupleStructType) : TypeBasedBlueprint<TupleStructType>(type) {
    override fun resolveProperties(type: TupleStructType): List<Property> {
        return type.types
            .map { it.requireValue() }
            .map {
                Property(
                    createPropName(it),
                    resolveKotlinType(it),
                    it
                )
            }
    }

    private fun createPropName(type: Type): String {
        return when (type) {
            is ArrayType -> "array"
            else -> defineClassName(type.name).replaceFirstChar { it.lowercase() }
        }
    }
}
