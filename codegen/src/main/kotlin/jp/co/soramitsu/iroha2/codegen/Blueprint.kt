package jp.co.soramitsu.iroha2.codegen

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import jp.co.soramitsu.iroha2.type.*
import java.math.BigInteger
import java.util.*
import kotlin.reflect.KClass

@ExperimentalUnsignedTypes
class StructBlueprint(val type: StructType) {
    val className: String = defineClassName(type)
    val packageName: String = definePackageName(className, type)
    val properties = getProperties(type)

    private fun getProperties(type: StructType): List<Property> {
        return type.mapping
            .map { (name, type) ->
                Property(
                    normalizeName(name),
                    resolveKotlinType(type.value!!)
                )
            }
    }

    private fun defineClassName(type: Type) = type.name.substringBefore('<')
        .substringAfterLast("::")

    private fun definePackageName(className: String, type: Type): String {
        return "jp.co.soramitsu.iroha2.generated." + type.name.substringBeforeLast(className)
            .removeSuffix("::")
            .removePrefix("iroha")
            .replace("::", ".")
            .replace("_", "")
    }

    private fun resolveKotlinType(type: Type): TypeName {
        return when (type) {
            is CompositeType -> {
                val propClassName = defineClassName(type)
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

    private fun normalizeName(target: String): String {
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

    override fun toString(): String {
        return "StructBlueprint(className='$className', packageName='$packageName', properties=$properties)"
    }

    companion object {
        val builtinKotlinTypes = mapOf<KClass<*>, TypeName>(
            StringType::class to String::class.asTypeName(),
            BooleanType::class to Boolean::class.asTypeName(),
            U8Type::class to UByte::class.asTypeName(),
            U16Type::class to UShort::class.asTypeName(),
            U32Type::class to UInt::class.asTypeName(),
            CompactType::class to UInt::class.asTypeName(),
            U64Type::class to ULong::class.asTypeName(),
            U128Type::class to BigInteger::class.asTypeName(),
            U256Type::class to BigInteger::class.asTypeName(),
            VecType::class to ClassName("kotlin.collections", "MutableList"),
            SetType::class to ClassName("kotlin.collections", "MutableSet"),
            MapType::class to ClassName("kotlin.collections", "MutableMap"),
        )
    }

}


data class Property(val normalizedPropName: String, val propTypeName: TypeName)

//todo move to helpers
