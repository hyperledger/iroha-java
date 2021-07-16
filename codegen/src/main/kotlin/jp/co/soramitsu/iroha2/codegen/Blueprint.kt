package jp.co.soramitsu.iroha2.codegen

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import jp.co.soramitsu.iroha2.type.*
import java.math.BigInteger
import java.util.*
import kotlin.reflect.KClass

class StructBlueprint(type: StructType) : Blueprint<StructType>(type){
    override fun resolveProperties(type: StructType): List<Property> {
        return type.mapping
            .map { (name, ty) ->
                Property(
                    resolvePropName(name),
                    resolveKotlinType(ty.requireValue()),
                    ty.requireValue()
                )
            }
    }
}

class TupleStructBlueprint(type: TupleStructType) : Blueprint<TupleStructType>(type){
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
            else -> defineClassName(type.name).decapitalize()
        }
    }
}

class EnumBlueprint(type: EnumType) : Blueprint<EnumType>(type) {
    val variants = resolveVariants()

    private fun resolveVariants(): List<EnumVariantBlueprint> {
        return type.variants.map {
            EnumVariantBlueprint(it.name, it.discriminant, resolveVariantInnerType(it))
        }
    }

    private fun resolveVariantInnerType(variant: EnumType.Variant): Property? {
        return variant.type?.requireValue()?.let {
            return Property(
                resolvePropName(variant.name).replaceFirstChar(Char::lowercase),
                resolveKotlinType(it),
                it
            )
        }

    }

}

@ExperimentalUnsignedTypes
abstract class Blueprint<T : CompositeType>(val type: T) {
    val className: String = defineClassName(type.name)
    val packageName: String = definePackageName(className, type)
    val properties = resolveProperties(type)

    open fun resolveProperties(type: T): List<Property> = listOf()

    protected fun defineClassName(typeName: String) = typeName.substringBefore('<')
        .substringAfterLast("::")

    protected fun definePackageName(className: String, type: Type): String {
        return "jp.co.soramitsu.iroha2.generated." + type.name.substringBeforeLast(className)
            .removeSuffix("::")
            .removePrefix("iroha")
            .replace("::", ".")
            .replace("_", "")
    }

    protected fun resolveKotlinType(type: Type): TypeName {
        return when (type) {
            is CompositeType -> {
                val propClassName = defineClassName(type.name)
                val propPackageName = definePackageName(propClassName, type)
                val clazz = ClassName(propPackageName, propClassName)
                if (type.generics.isEmpty()) {
                    clazz
                } else {
                    clazz.parameterizedBy(type.generics.map {
                        resolveKotlinType(
                            it.requireValue(),
                        )
                    })
                }
            }
            //must be before 'WrapperType'
            is OptionType -> resolveKotlinType(type.innerType.requireValue()).copy(nullable = true)
            is WrapperType -> {
                val wrapperType = builtinKotlinTypes[type::class]
                    ?: throw RuntimeException("unexpected type: $type")
                (wrapperType as ClassName).parameterizedBy(resolveKotlinType(type.innerType.requireValue()))
            }
            is MapType -> {
                val wrapperType = builtinKotlinTypes[type::class]
                    ?: throw RuntimeException("unexpected type: $type")
                (wrapperType as ClassName).parameterizedBy(
                    resolveKotlinType(type.key.requireValue()),
                    resolveKotlinType(type.value.requireValue())
                )
            }
            //only "primitive" types left"
            else -> builtinKotlinTypes[type::class]
                ?: throw RuntimeException("unexpected type: $type")
        }
    }

    override fun toString(): String {
        return "${this::class}(type=$type, className='$className', packageName='$packageName', properties=$properties)"
    }

    companion object {
        val builtinKotlinTypes = mapOf<KClass<*>, TypeName>(
            StringType::class to String::class.asTypeName(),
            BooleanType::class to Boolean::class.asTypeName(),
            U8Type::class to UByte::class.asTypeName(),
            U16Type::class to UShort::class.asTypeName(),
            U32Type::class to UInt::class.asTypeName(),
            U64Type::class to ULong::class.asTypeName(),
            U128Type::class to BigInteger::class.asTypeName(),
            U256Type::class to BigInteger::class.asTypeName(),
            CompactType::class to UInt::class.asTypeName(),
            VecType::class to ClassName("kotlin.collections", "MutableList"),
            SetType::class to ClassName("kotlin.collections", "MutableSet"),
            MapType::class to ClassName("kotlin.collections", "MutableMap"),
            ArrayType::class to Array::class.asTypeName()
        )
    }

}

data class Property(val name: String, val typeName: TypeName, val original: Type)

/**
 * Blueprint of the enum variant
 */
data class EnumVariantBlueprint(val discriminant: Int) : Blueprint<StructType>(StructType())

/**
 * Create property name by converting from snake case to camel case
 */
fun resolvePropName(
    name: String,
): String {
    val tokenizer = StringTokenizer(name, "_")
    return if (tokenizer.hasMoreTokens()) {
        val resultBuilder = StringBuilder(tokenizer.nextToken())
        for (token in tokenizer) {
            resultBuilder.append((token as String).replaceFirstChar(Char::uppercase))
        }
        resultBuilder.toString()
    } else {
        name
    }
}
