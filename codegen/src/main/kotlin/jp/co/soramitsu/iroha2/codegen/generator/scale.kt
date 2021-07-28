package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.*
import jp.co.soramitsu.iroha2.codegen.blueprint.Property
import jp.co.soramitsu.iroha2.codegen.resolveKotlinType
import jp.co.soramitsu.iroha2.type.*


fun resolveScaleReadImpl(type: Type) : CodeBlock {
    return when (type) {
        is ArrayType -> CodeBlock.of("reader.readByteArray()")
        is VecType -> {
            CodeBlock.of("MutableList(reader.readCompactInt()) {%L}",
                resolveScaleReadImpl(type.innerType.requireValue()))
        }
        is SetType -> {
            CodeBlock.of(
                "%M(reader.readCompactInt()) {%L}",
                MemberName("jp.co.soramitsu.iroha2.utils", "hashSetWithSize"),
                resolveScaleReadImpl(type.innerType.requireValue()),
            )
        }
        is MapType -> {
            CodeBlock.of("%1M(reader.readCompactInt(), {%2L}, {%3L})",
                MemberName("jp.co.soramitsu.iroha2.utils", "hashMapWithSize"),
                resolveScaleReadImpl(type.key.requireValue()),
                resolveScaleReadImpl(type.value.requireValue())
            )
        }
        is U8Type -> CodeBlock.of("reader.readByte().toUByte()")
        is U16Type -> CodeBlock.of("reader.readUint16().toUShort()")
        is U32Type -> CodeBlock.of("reader.readUint32().toUInt()")
        is U64Type -> CodeBlock.of("reader.readUint128().toLong().toULong()")
        is U128Type -> CodeBlock.of("reader.readUint128()")
        is U256Type -> CodeBlock.of("BigInteger(reader.readUint256())")
        is StringType -> CodeBlock.of("reader.readString()")
        is BooleanType -> CodeBlock.of("reader.readBoolean()")
        is CompositeType -> CodeBlock.of("%T.read(reader)", resolveKotlinType(type))
        is OptionType -> CodeBlock.of("reader.readOptional(%L).orElse(null)", resolveScaleReadImpl(type.innerType.requireValue()))
        is CompactType -> CodeBlock.of("reader.readCompactInt()")
        else -> throw java.lang.RuntimeException("Unexpected type: $type")
    }
}

fun resolveScaleWriteImpl(type: Type, propName: String): CodeBlock {
    return when (type) {
        is ArrayType -> CodeBlock.of("writer.writeByteArray(instance.%L)", propName)
        is VecType -> {
            CodeBlock.of(
                "writer.writeCompact(instance.%1L.size)\n" +
                "repeat(instance.%1L.size) { %2L }",
                propName,
                resolveScaleWriteImpl(type.innerType.requireValue(), "it"))
        }
        //        is SetType -> {
//            CodeBlock.of(
//                "%M(reader.readCompactInt()) {%L}",
//                MemberName("jp.co.soramitsu.iroha2.utils", "hashSetWithSize"),
//                resolveScaleReadImpl(type.innerType.requireValue()),
//            )
//        }
//        is MapType -> {
//            CodeBlock.of("%1M(reader.readCompactInt(), {%2L}, {%3L})",
//                MemberName("jp.co.soramitsu.iroha2.utils", "hashMapWithSize"),
//                resolveScaleReadImpl(type.key.requireValue()),
//                resolveScaleReadImpl(type.value.requireValue())
//            )
//        }
        is U8Type -> CodeBlock.of("writer.writeByte(instance.%L.toByte())", propName)
        is U16Type -> CodeBlock.of("writer.writeUint16(instance.%L.toInt())", propName)
        is U32Type -> CodeBlock.of("writer.writeUint32(instance.%L.toInt())", propName)
        is U64Type -> CodeBlock.of("writer.writeUint128(BigInteger.valueOf(instance.%L.toLong()))", propName)
        is U128Type -> CodeBlock.of("writer.writeUint128(instance.%L)", propName)
        is U256Type -> CodeBlock.of("writer.writeUint256(instance.%L.toByteArray())", propName)
        is StringType -> CodeBlock.of("writer.writeAsList(instance.%L.toByteArray(Charsets.UTF_8))", propName)
        is BooleanType -> CodeBlock.of("if (instance.%L) { writer.directWrite(1) } else { writer.directWrite(0) }", propName)

//        is CompositeType -> CodeBlock.of("${resolveKotlinType(type)}.read(reader)")
//        is OptionType -> CodeBlock.of("reader.readOptional(%L).orElse(null", resolveScaleReadImpl(type.innerType.requireValue()))
//        is CompactType -> CodeBlock.of("reader.readCompactInt()")
//        else -> throw java.lang.RuntimeException("Unexpected type: $type")

//        is ArrayType -> CodeBlock.of(
//            "%T.write(writer, instance.${property.name})",
//            BYTE_ARRAY_WRITER
//        )
//        is CompositeType -> CodeBlock.of("${getRealClassName(property.typeName)}.write(writer, instance.${property.name})")
//        is WrapperType -> {
//            val innerValueWriter = barWriter(type.innerType.requireValue(), property.typeName)
//            CodeBlock.of(
//                "%T(%T).write(writer, instance.${property.name})",
//                getScaleWriter(type),
//                innerValueWriter
//            )
//        }
//        is MapType -> {
//            val keyWriter = barWriter(type.key.requireValue(), property.typeName, 0)
//            val valueWriter = barWriter(type.value.requireValue(), property.typeName, 1)
//            CodeBlock.of(
//                "%T(%T, %T).write(writer, instance.${property.name})",
//                MAP_WRITER,
//                keyWriter,
//                valueWriter
//            )
//        }
//        else -> CodeBlock.of("%T.write(writer, instance.${property.name})", barWriter(type, property.typeName))
        else -> CodeBlock.of("")
    }
}

private fun getRealClassName(typeName: TypeName): String {
    return when (typeName) {
        is ClassName -> typeName.simpleName
        is ParameterizedTypeName -> getRealClassName(typeName.typeArguments.first())
        else -> throw RuntimeException("Unexpected type: $typeName")
    }
}

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

//todo remove me
//private fun getScaleReader(type: Type): TypeName {
//    return when (type) {
//        is StringType -> STRING_READER
//        is ArrayType -> BYTE_ARRAY_READER
//        is VecType -> LIST_READER
//        is SetType -> SET_READER
//        is U8Type -> U8_READER
//        is U16Type -> U16_READER
//        is U32Type -> U32_READER
//        is U64Type -> U64_READER
//        is U128Type -> U128_READER
//        is U256Type -> U256_READER
//        is BooleanType -> BOOL_READER
//        is OptionType -> OPTION_READER
//        is CompactType -> {
//            when (type.innerType.requireValue()) {
//                is U8Type -> U8_READER
//                is U16Type -> U8_READER
//                is U32Type -> U8_READER
//                is U64Type -> U8_READER
//                is U128Type -> U8_READER
//                is U256Type -> U8_READER
//                else -> throw RuntimeException("Scale reader implementation is not resolved for compact type: '${type}'")
//            }
//        }
//        else -> throw RuntimeException("Scale reader implementation is not resolved for type: '${type}'")
//    }
//}

//todo remove me
//private fun barReader(innerType: Type, fallback: TypeName, index: Int = 0): TypeName {
//    return when (innerType) {
//        is CompositeType -> foo(fallback, index)
//        else -> getScaleReader(innerType)
//    }
//}

//todo remove me

//private fun barWriter(innerType: Type, fallback: TypeName, index: Int = 0): TypeName {
//    return when (innerType) {
//        is CompositeType -> foo(fallback, index)
//        else -> getScaleWriter(innerType)
//    }
//}

//private fun renameMeLater(innerType: Type, fallback: TypeName, index: Int = 0): TypeName {
//    return when (innerType) {
//        is CompositeType -> foo(fallback, index)
//        else -> getScaleReader(innerType)
//    }
//}

//todo consider all types

//
//fun getScaleWriter(type: Type): TypeName {
//    return when (type) {
//        is StringType -> STRING_WRITER
//        is ArrayType -> BYTE_ARRAY_WRITER
//        is VecType -> LIST_WRITER
//        is SetType -> SET_WRITER
//        is U8Type -> U8_WRITER
//        is U16Type -> U16_WRITER
//        is U32Type -> U32_WRITER
//        is U64Type -> U64_WRITER
//        is U128Type -> U128_WRITER
//        is U256Type -> U256_WRITER
//        is BooleanType -> BOOL_WRITER
//        is OptionType -> OPTION_WRITER
//        is CompactType -> {
//            when (type.innerType.requireValue()) {
//                is U8Type -> U8_READER
//                is U16Type -> U8_READER
//                is U32Type -> U8_READER
//                is U64Type -> U8_READER
//                is U128Type -> U8_READER
//                is U256Type -> U8_READER
//                else -> throw RuntimeException("Scale reader implementation is not resolved for compact type: '${type}'")
//            }
//        }
//        else -> throw RuntimeException("Scale writer implementation is not resolved for type: '${type}'")
//    }
//}
//
////todo remove???

////todo remove me
//private fun foo(typeName: TypeName, index: Int = 0): TypeName {
//    return when (typeName) {
//        is ClassName -> typeName
//        is ParameterizedTypeName -> foo(typeName.typeArguments[index])
//        else -> throw RuntimeException("Unexpected type: $typeName")
//    }
//}
