package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeName
import jp.co.soramitsu.iroha2.codegen.Property
//import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.BOOLEAN_READER
//import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.BYTE_ARRAY_READER
//import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.LIST_READER
//import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.MAP_READER
//import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.OPTION_READER
//import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.SET_READER
//import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.STRING_READER
//import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.U128_READER
//import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.U32_READER
//import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.UBYTE_READER
import jp.co.soramitsu.iroha2.type.*

//todo replcae with real classes
val SCALE_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
val SCALE_CODEC_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
val SCALE_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleWriter")
val SCALE_CODEC_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecWriter")
val LIST_READER = ClassName("io.emeraldpay.polkaj.scale.reader", "ListReader")
val LIST_WRITER = ClassName("io.emeraldpay.polkaj.scale.writer", "ListWriter")
val U8_READER = ClassName("jp.co.soramitsu.iroha2.scale", "U8Reader")
val U8_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "U8Writer")
val U16_READER = ClassName("jp.co.soramitsu.iroha2.scale", "U16Reader")
val U16_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "U16Writer")
val U32_READER = ClassName("jp.co.soramitsu.iroha2.scale", "U32Reader")
val U32_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "U32Writer")
val U64_READER = ClassName("jp.co.soramitsu.iroha2.scale", "U64Reader")
val U64_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "U64Writer")
val U128_READER = ClassName("jp.co.soramitsu.iroha2.scale", "U128Reader")
val U128_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "U128Writer")
val U256_READER = ClassName("jp.co.soramitsu.iroha2.scale", "U256Reader")
val U256_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "U256Writer")
val STRING_READER = ClassName("jp.co.soramitsu.iroha2.scale", "StringReader")
val STRING_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "StringWriter")
val BOOL_READER = ClassName("jp.co.soramitsu.iroha2.scale", "BoolReader")
val BOOL_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "BoolWriter")
val BYTE_ARRAY_READER = ClassName("jp.co.soramitsu.iroha2.scale", "ByteArrayReader")
val BYTE_ARRAY_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "ByteArrayWriter")
val SET_READER = ClassName("jp.co.soramitsu.iroha2.scale", "SetReader")
val SET_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "SetWriter")
val OPTION_READER = ClassName("jp.co.soramitsu.iroha2.scale", "OptionReader")
val OPTION_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "OptionWriter")
val MAP_READER = ClassName("jp.co.soramitsu.iroha2.scale", "MapReader")
val MAP_WRITER = ClassName("jp.co.soramitsu.iroha2.scale", "MapWriter")

private fun getScaleReader(type: Type): TypeName {
    return when (type) {
        is StringType -> STRING_READER
        is ArrayType -> BYTE_ARRAY_READER
        is VecType -> LIST_READER
        is SetType -> SET_READER
        is U8Type -> U8_READER
        is U16Type -> U16_READER
        is U32Type -> U32_READER
        is U64Type -> U64_READER
        is U128Type -> U128_READER
        is U256Type -> U256_READER
        is BooleanType -> BOOL_READER
        is OptionType -> OPTION_READER
        is CompactType -> {
            when (type.innerType.requireValue()) {
                is U8Type -> U8_READER
                is U16Type -> U8_READER
                is U32Type -> U8_READER
                is U64Type -> U8_READER
                is U128Type -> U8_READER
                is U256Type -> U8_READER
                else -> throw RuntimeException("Scale reader implementation is not resolved for compact type: '${type}'")
            }
        }
        else -> throw RuntimeException("Scale reader implementation is not resolved for type: '${type}'")
    }
}

private fun barReader(innerType: Type, fallback: TypeName, index: Int = 0): TypeName {
    return when (innerType) {
        is CompositeType -> foo(fallback, index)
        else -> getScaleReader(innerType)
    }
}

private fun barWriter(innerType: Type, fallback: TypeName, index: Int = 0): TypeName {
    return when (innerType) {
        is CompositeType -> foo(fallback, index)
        else -> getScaleWriter(innerType)
    }
}


//todo consider all types
fun resolveScaleReadImplementation(property: Property): CodeBlock {
    return when (val type = property.original) {
        is ArrayType -> CodeBlock.of("%T.read(reader)", BYTE_ARRAY_READER)
        is CompositeType -> CodeBlock.of("${getRealClassName(property.typeName)}.read(reader)")
        is WrapperType -> {
            val innerValueReader = barReader(type.innerType.requireValue(), property.typeName)
            CodeBlock.of("%T(%T).read(reader)", getScaleReader(type), innerValueReader)
        }
        is MapType -> {
            val keyReader = barReader(type.key.requireValue(), property.typeName, 0)
            val valueReader = barReader(type.value.requireValue(), property.typeName, 1)
            CodeBlock.of("%T(%T, %T).read(reader)", MAP_READER, keyReader, valueReader)
        }
        else -> CodeBlock.of("%T.read(reader)", barReader(type, property.typeName))
    }
//        else -> throw RuntimeException("Scale reader implementation is not resolved for type: '${property.original::class}'")
}

fun resolveScaleWriteImplementation(property: Property): CodeBlock {
    return when (val type = property.original) {
        is ArrayType -> CodeBlock.of(
            "%T.write(writer, instance.${property.name})",
            BYTE_ARRAY_WRITER
        )
        is CompositeType -> CodeBlock.of("${getRealClassName(property.typeName)}.write(writer, instance.${property.name})")
        is WrapperType -> {
            val innerValueWriter = barWriter(type.innerType.requireValue(), property.typeName)
            CodeBlock.of(
                "%T(%T).write(writer, instance.${property.name})",
                getScaleWriter(type),
                innerValueWriter
            )
        }
        is MapType -> {
            val keyWriter = barWriter(type.key.requireValue(), property.typeName, 0)
            val valueWriter = barWriter(type.value.requireValue(), property.typeName, 1)
            CodeBlock.of(
                "%T(%T, %T).write(writer, instance.${property.name})",
                MAP_WRITER,
                keyWriter,
                valueWriter
            )
        }
        else -> CodeBlock.of("%T.write(writer, instance.${property.name})", barWriter(type, property.typeName))
    }
}

fun getScaleWriter(type: Type): TypeName {
    return when (type) {
        is StringType -> STRING_WRITER
        is ArrayType -> BYTE_ARRAY_WRITER
        is VecType -> LIST_WRITER
        is SetType -> SET_WRITER
        is U8Type -> U8_WRITER
        is U16Type -> U16_WRITER
        is U32Type -> U32_WRITER
        is U64Type -> U64_WRITER
        is U128Type -> U128_WRITER
        is U256Type -> U256_WRITER
        is BooleanType -> BOOL_WRITER
        is OptionType -> OPTION_WRITER
        is CompactType -> {
            when (type.innerType.requireValue()) {
                is U8Type -> U8_READER
                is U16Type -> U8_READER
                is U32Type -> U8_READER
                is U64Type -> U8_READER
                is U128Type -> U8_READER
                is U256Type -> U8_READER
                else -> throw RuntimeException("Scale reader implementation is not resolved for compact type: '${type}'")
            }
        }
        else -> throw RuntimeException("Scale writer implementation is not resolved for type: '${type}'")
    }
}

//todo remove???
private fun getRealClassName(typeName: TypeName): String {
    return when (typeName) {
        is ClassName -> typeName.simpleName
        is ParameterizedTypeName -> getRealClassName(typeName.typeArguments.first())
        else -> throw RuntimeException("Unexpected type: $typeName")
    }
}

private fun foo(typeName: TypeName, index: Int = 0): TypeName {
    return when (typeName) {
        is ClassName -> typeName
        is ParameterizedTypeName -> foo(typeName.typeArguments[index])
        else -> throw RuntimeException("Unexpected type: $typeName")
    }
}
