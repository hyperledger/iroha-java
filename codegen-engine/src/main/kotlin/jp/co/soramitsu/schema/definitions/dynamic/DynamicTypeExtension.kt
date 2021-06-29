package jp.co.soramitsu.schema.definitions.dynamic

import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.TypeReference

typealias TypeProvider = (typeDef: String) -> TypeReference

interface DynamicTypeExtension {

    fun createType(name: String, typeDef: String, typeProvider: TypeProvider): Type<*>?
}
