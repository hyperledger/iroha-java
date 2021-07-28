package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.*
import jp.co.soramitsu.iroha2.codegen.resolveKotlinType
import jp.co.soramitsu.iroha2.type.*

val SCALE_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
val SCALE_CODEC_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
val SCALE_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleWriter")
val SCALE_CODEC_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecWriter")
val HASH_MAP_CREATER = MemberName("jp.co.soramitsu.iroha2.utils", "hashMapWithSize")
val HASH_SET_CREATER = MemberName("jp.co.soramitsu.iroha2.utils", "hashSetWithSize")

fun resolveScaleReadImpl(type: Type): CodeBlock {
    return when (type) {
        is ArrayType -> CodeBlock.of("reader.readByteArray()")
        is VecType -> {
            CodeBlock.of("MutableList(reader.readCompactInt()) {%L}",
                resolveScaleReadImpl(type.innerType.requireValue()))
        }
        is SetType -> {
            CodeBlock.of(
                "%M(reader.readCompactInt()) {%L}",
                HASH_SET_CREATER,
                resolveScaleReadImpl(type.innerType.requireValue()),
            )
        }
        is MapType -> {
            CodeBlock.of("%1M(reader.readCompactInt(), {%2L}, {%3L})",
                HASH_MAP_CREATER,
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
        is CompositeType -> {
            val className = resolveKotlinType(type)
            val classNameWithoutGeneric = foo(className)
            CodeBlock.of("%1T.read(reader) as %2T", classNameWithoutGeneric, className)
        }
        is OptionType -> CodeBlock.of("reader.readOptional(%L).orElse(null)",
            resolveScaleReadImpl(type.innerType.requireValue()))
        is CompactType -> CodeBlock.of("reader.readCompactInt()")
        else -> throw RuntimeException("Unexpected type: $type")
    }
}

fun resolveScaleWriteImpl(type: Type, propName: String): CodeBlock {
    return when (type) {
        is ArrayType -> CodeBlock.of("writer.writeByteArray(%L)", propName)
        is VecType -> {
            CodeBlock.of(
                "writer.writeCompact(%1L.size)\n" +
                "%1L.forEach { value -> %2L }",
                propName,
                resolveScaleWriteImpl(type.innerType.requireValue(), "value")
            )
        }
        is SetType -> {
            CodeBlock.of(
                "writer.writeCompact(%1L.size)\n" +
                "%1L.forEach { value -> %2L }",
                propName,
                resolveScaleWriteImpl(type.innerType.requireValue(), "value")
            )
        }
        is MapType -> {
            CodeBlock.of(
                "writer.writeCompact(%1L.size)\n" +
                "%1L.forEach { (key, value) ->  \n\t%2L\n\t%3L\n}",
                propName,
                resolveScaleWriteImpl(type.key.requireValue(), "key"),
                resolveScaleWriteImpl(type.value.requireValue(), "value")
            )
        }
        is U8Type -> CodeBlock.of("writer.writeByte(%L.toByte())", propName)
        is U16Type -> CodeBlock.of("writer.writeUint16(%L.toInt())", propName)
        is U32Type -> CodeBlock.of("writer.writeUint32(%L.toInt())", propName)
        is U64Type -> CodeBlock.of("writer.writeUint128(BigInteger.valueOf(%L.toLong()))", propName)
        is U128Type -> CodeBlock.of("writer.writeUint128(%L)", propName)
        is U256Type -> CodeBlock.of("writer.writeUint256(%L.toByteArray())", propName)
        is StringType -> CodeBlock.of("writer.writeAsList(%L.toByteArray(Charsets.UTF_8))",
            propName)
        is BooleanType -> CodeBlock.of("if (%L) { writer.directWrite(1) } else { writer.directWrite(0) }",
            propName)
        is CompositeType -> CodeBlock.of("%T.write(writer, %L)", foo(resolveKotlinType(type)).topLevelClassName(), propName)
        is OptionType -> CodeBlock.of("writer.writeOptional(%1L), Optional.ofNullable(%2L))",
            resolveScaleWriteImpl(type.innerType.requireValue(), propName),
            propName)
        is CompactType -> CodeBlock.of("writer.writeCompact(%L)", propName)
        else -> throw RuntimeException("Unexpected type: $type")
    }
}

fun foo(typeName: TypeName): ClassName {
    return when (typeName) {
        is ClassName -> typeName.topLevelClassName()
        is ParameterizedTypeName -> typeName.rawType.topLevelClassName()
        else -> throw RuntimeException("Unexpected type name: $typeName")
    }
}
