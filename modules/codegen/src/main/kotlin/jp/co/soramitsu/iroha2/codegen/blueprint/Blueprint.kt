package jp.co.soramitsu.iroha2.codegen.blueprint

import com.squareup.kotlinpoet.TypeName
import jp.co.soramitsu.iroha2.type.Type

/**
 * Basic blueprint for all kind of types
 */
abstract class      Blueprint<T>(val source: T) {
    abstract val className: String
    abstract val packageName: String
    abstract val properties: List<Property>

    open fun resolveProperties(type: T): List<Property> = listOf()
}

data class Property(val name: String, val typeName: TypeName, val original: Type)
