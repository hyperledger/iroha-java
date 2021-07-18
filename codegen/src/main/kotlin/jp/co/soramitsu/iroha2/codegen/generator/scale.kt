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

//class ScaleConstants {
//    companion object {
        //todo replcae with real classes
        val SCALE_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
        val SCALE_CODEC_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
        val SCALE_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleWriter")
        val SCALE_CODEC_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecWriter")
        val LIST_READER = ClassName("io.emeraldpay.polkaj.scale.reader", "ListReader")
        val LIST_WRITER = ClassName("io.emeraldpay.polkaj.scale.writer", "ListWriter")
        val UBYTE_READER = ClassName("jp.co.soramitsu.iroha2.scale", "UByteReader")
        val STRING_READER = ClassName("jp.co.soramitsu.iroha2.scale", "StringReader")
        val BOOLEAN_READER = ClassName("jp.co.soramitsu.iroha2.scale", "BooleanReader")
        val BYTE_ARRAY_READER = ClassName("jp.co.soramitsu.iroha2.scale", "ByteArrayReader")
        val U32_READER = ClassName("jp.co.soramitsu.iroha2.scale", "UInt32Reader")
        val U128_READER = ClassName("jp.co.soramitsu.iroha2.scale", "UInt128Reader")
        val SET_READER = ClassName("jp.co.soramitsu.iroha2.scale", "SetReader")
        val OPTION_READER = ClassName("jp.co.soramitsu.iroha2.scale", "OptionReader")
        val MAP_READER = ClassName("jp.co.soramitsu.iroha2.scale", "MapReader")
//    }
//}

private fun getScaleReader(type: Type) : TypeName {
    return when (type) {
        is StringType -> STRING_READER
        is ArrayType -> BYTE_ARRAY_READER
        is VecType -> LIST_READER
        is SetType -> SET_READER
        is U8Type -> UBYTE_READER
        is U16Type -> UBYTE_READER
        is U32Type -> U32_READER
        is U64Type -> UBYTE_READER
        is U128Type -> U128_READER
        is U256Type -> UBYTE_READER
        is BooleanType -> BOOLEAN_READER
        is OptionType -> OPTION_READER
        is CompactType -> {
            when(type.innerType.requireValue()) {
                is U8Type -> UBYTE_READER
                is U16Type -> UBYTE_READER
                is U32Type -> UBYTE_READER
                is U64Type -> UBYTE_READER
                is U128Type -> UBYTE_READER
                is U256Type -> UBYTE_READER
                else -> throw RuntimeException("Scale reader implementation is not resolved for compact type: '${type}'")
            }
        }
        else -> throw RuntimeException("Scale reader implementation is not resolved for type: '${type}'")
    }
}

private fun bar(innerType: Type, fallback: TypeName, index: Int = 0): TypeName {
    return when (innerType) {
        is CompositeType -> foo(fallback, index)
        else -> getScaleReader(innerType)
    }
}

//todo consider all types
fun resolveScaleReadImplementation(property: Property): CodeBlock {
    return when (val type = property.original) {
        is ArrayType -> CodeBlock.of("%T.read(reader)", BYTE_ARRAY_READER)
        is CompositeType -> CodeBlock.of("${getRealClassName(property.typeName)}.read(reader)")
        is WrapperType -> {
            val innerValueReader = bar(type.innerType.requireValue(), property.typeName)
            CodeBlock.of("%T(%T).read(reader)", getScaleReader(type), innerValueReader)
        }
        is MapType -> {
            val keyReader = bar(type.key.requireValue(), property.typeName, 0)
            val valueReader = bar(type.value.requireValue(), property.typeName, 1)
            CodeBlock.of("%T(%T, %T).read(reader)", MAP_READER, keyReader, valueReader)
        }
        else -> CodeBlock.of("%T.read(reader)", getScaleReader(type))
    }
//        is StringType -> CodeBlock.of("%T.read(reader)", STRING_ READER)
//        is BooleanType -> CodeBlock.of("%T.read(reader)", BOOLEAN_READER)
//        is CompactType -> CodeBlock.of("reader.readCompactInt()")
//        is OptionType -> CodeBlock.of("reader.readOptional(${getRealClassName(property.typeName)})")
//        is U8Type -> CodeBlock.of("%T.read(reader)", UBYTE_READER)
//        is ArrayType -> {
//            when (type.innerType.requireValue()) {
//                is U8Type -> CodeBlock.of("%T.read(reader)", BYTE_ARRAY_READER)
//                else -> CodeBlock.of("")
//            }
//        }
//        is VecType -> {
//            CodeBlock.of("%T(%S).read(reader)", LIST_READER, resolveScaleReadImplementation(type.innerType.requireValue()))
//        }
//        is SetType -> CodeBlock.of("reader.read(%T(${getRealClassName(property.typeName)}))",
//            LIST_READER
//        )
//        is CompositeType -> CodeBlock.of("${getRealClassName(property.typeName)}.read(reader)")
//        else -> CodeBlock.of("${getRealClassName(property.typeName)}.read(reader)")
//        else -> CodeBlock.of("//todo not implemented")
//        else -> throw RuntimeException("Scale reader implementation is not resolved for type: '${property.original::class}'")
//    }
}

fun resolveScaleWriteImplementation(property: Property): CodeBlock {
    return when (property.original) {
        is StringType -> CodeBlock.of("writer.writeAsList(instance.${property.name}.encodeToByteArray())")
        is BooleanType -> CodeBlock.of("writer.writeByte(if (instance.${property.name}) {1} else {0})")
        is CompactType -> CodeBlock.of("writer.writeCompact(instance.${property.name})")
        is OptionType -> CodeBlock.of("writer.writeOptional(${getRealClassName(property.typeName)}, instance.${property.name})")
        is ArrayType -> {
            when (property.original.innerType.requireValue()) {
                is U8Type -> CodeBlock.of("writer.writeByteArray(instance.${property.name})")
                else -> CodeBlock.of("//not implemented")
            }
        }
        is VecType -> CodeBlock.of("writer.write(%T(${getRealClassName(property.typeName)}), instance.${property.name})",
            LIST_WRITER
        )
        //todo create set reader and set writer?
        is SetType -> CodeBlock.of("writer.write(%T(${getRealClassName(property.typeName)}), instance.${property.name}.toList())",
            LIST_WRITER
        )
        else -> CodeBlock.of("${getRealClassName(property.typeName)}.write(writer, instance.${property.name})")
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
