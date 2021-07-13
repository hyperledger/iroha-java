package jp.co.soramitsu.iroha2.codegen

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import jp.co.soramitsu.iroha2.type.*
import java.math.BigInteger
import java.util.*

class StructBlueprint(val type: StructType) {

    //todo move in helpers
    val className: String = defineClassName(type)
    val packageName: String = definePackageName(className)
    val properties = getProperties(type)

    private fun getProperties(type: StructType): List<Property> {
        return type.mapping
            .map { (name, type) ->
                Property(
                    convertToCamelCase(name),
                    resolveKotlinType(type.value!!, className, packageName)
                )
            }
    }

    private fun defineClassName(type: Type) = type.name.substringBefore('<')
        .substringAfterLast("::")

    private fun definePackageName(className: String): String {
        return "jp.co.soramitsu.iroha2.generated." + type.name.substringBeforeLast(className)
            .removeSuffix("::")
            .removePrefix("iroha")
            .replace("::", ".")
            .replace("_", "")
    }

    private fun resolveKotlinType(type: Type, className: String, packageName: String): TypeName {
        return when (type) {
            is StringType -> String::class.java.asClassName()
            is BooleanType -> Boolean::class.java.asTypeName()
            is VecType -> {
                List::class.java.asClassName()
                    .parameterizedBy(
                        resolveKotlinType(
                            type.innerType.requireValue(),
                            className,
                            packageName
                        )
                    )
            }
            is SetType -> {
                Set::class.java.asClassName()
                    .parameterizedBy(
                        resolveKotlinType(
                            type.innerType.requireValue(),
                            className,
                            packageName
                        )
                    )
            }
            is OptionType -> resolveKotlinType(
                type.innerType.requireValue(),
                className,
                packageName
            ).copy(nullable = true)
            is U8Type -> UByte::class.java.asClassName()
            is U16Type -> UShort::class.java.asClassName()
            is U32Type, is CompactType -> UInt::class.java.asClassName()
            is U64Type -> ULong::class.java.asClassName()
            is U128Type, is U256Type -> BigInteger::class.java.asClassName()
            is MapType -> Map::class.java.asClassName()
                .parameterizedBy(
                    resolveKotlinType(type.key.requireValue(), className, packageName),
                    resolveKotlinType(
                        type.value.requireValue(),
                        className,
                        packageName
                    )
                )
            is CompositeType -> {
                val propClassName = defineClassName(type)
                val propPackageName = definePackageName(propClassName)
                val clazz = ClassName(propPackageName, propClassName)
                if (type.generics.isEmpty()) {
                    clazz
                } else {
                    clazz.parameterizedBy(type.generics.map {
                        resolveKotlinType(
                            it.requireValue(),
                            propClassName,
                            propPackageName
                        )
                    })
                }
            }
            else -> throw RuntimeException("unexpected type: $type")
        }
    }
}


data class Property(val normalizedPropName: String, val propTypeName: TypeName)

//todo move to helpers
private fun convertToCamelCase(target: String): String {
    val tokenizer = StringTokenizer(target, "_")
    return if (tokenizer.hasMoreTokens()) {
        val resultBuilder = StringBuilder(tokenizer.nextToken())
        for (token in tokenizer) {
            resultBuilder.append((token as String).capitalize(Locale.getDefault()))
        }
        resultBuilder.toString()
    } else {
        target
    }
}

//todo move to hel[er
