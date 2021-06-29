package jp.co.soramitsu.schema.definitions.types.composite

import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.TypeReference
import jp.co.soramitsu.schema.definitions.types.skipAliasesOrNull

abstract class WrapperType<I>(name: String, val typeReference: TypeReference) : Type<I>(name) {

    val innerType: Type<*>?
        get() = typeReference.value

    override val isFullyResolved: Boolean
        get() = typeReference.isResolved()

    inline fun <reified R> innerType(): R? {
        return typeReference.skipAliasesOrNull()?.value as? R?
    }
}