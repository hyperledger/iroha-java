package jp.co.soramitsu.iroha2.codegen

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import jp.co.soramitsu.iroha2.type.*
import java.math.BigInteger
import kotlin.reflect.KClass

fun resolveKotlinType(type: Type): TypeName {
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
        //must be before 'WrapperType'
        is ArrayType -> {
            when (type.innerType.requireValue()) {
                is U8Type -> ByteArray::class.asTypeName()
                else -> {
                    val wrapperType = lookUpInBuiltInTypes(type)
                    (wrapperType as ClassName).parameterizedBy(resolveKotlinType(type.innerType.requireValue()))
                }
            }
        }
        is CompactType -> {
            resolveKotlinType(type.innerType.requireValue())
        }
        is WrapperType -> {
            //special case for vector of bytes
            if (type is VecType && type.innerType.requireValue() is U8Type) {
                return ByteArray::class.asTypeName()
            }
            val wrapperType = lookUpInBuiltInTypes(type)
            (wrapperType as ClassName).parameterizedBy(resolveKotlinType(type.innerType.requireValue()))
        }
        is MapType -> {
            val wrapperType = lookUpInBuiltInTypes(type)
            (wrapperType as ClassName).parameterizedBy(
                resolveKotlinType(type.key.requireValue()),
                resolveKotlinType(type.value.requireValue())
            )
        }
        //only "primitive" types left"
        else -> lookUpInBuiltInTypes(type)
    }
}

fun lookUpInBuiltInTypes(type: Type): TypeName = builtinKotlinTypes[type::class]
    ?: throw RuntimeException("unexpected type: $type")

fun definePackageName(className: String, type: Type): String {
    return "jp.co.soramitsu.iroha2.generated." + type.name.substringBeforeLast(className)
        .removeSuffix("::")
        .removePrefix("iroha")
        .replace("::", ".")
        .replace("_", "")
}

fun defineClassName(typeName: String) = typeName.substringBefore('<')
    .substringAfterLast("::")

val builtinKotlinTypes = mapOf<KClass<*>, TypeName>(
    StringType::class to String::class.asTypeName(),
    BooleanType::class to Boolean::class.asTypeName(),
    U8Type::class to UByte::class.asTypeName(),
    U16Type::class to UShort::class.asTypeName(),
    U32Type::class to UInt::class.asTypeName(),
    U64Type::class to ULong::class.asTypeName(),
    U128Type::class to BigInteger::class.asTypeName(),
    U256Type::class to BigInteger::class.asTypeName(),
    VecType::class to ClassName("kotlin.collections", "MutableList"),
    SetType::class to ClassName("kotlin.collections", "MutableSet"),
    MapType::class to ClassName("kotlin.collections", "MutableMap"),
    ArrayType::class to Array::class.asTypeName()
)
