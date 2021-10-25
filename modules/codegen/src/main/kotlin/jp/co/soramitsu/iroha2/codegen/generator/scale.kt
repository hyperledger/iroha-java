package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeName
import jp.co.soramitsu.iroha2.codegen.resolveKotlinType
import jp.co.soramitsu.iroha2.type.ArrayType
import jp.co.soramitsu.iroha2.type.BooleanType
import jp.co.soramitsu.iroha2.type.CompactType
import jp.co.soramitsu.iroha2.type.CompositeType
import jp.co.soramitsu.iroha2.type.FixedPointType
import jp.co.soramitsu.iroha2.type.I64Type
import jp.co.soramitsu.iroha2.type.MapType
import jp.co.soramitsu.iroha2.type.OptionType
import jp.co.soramitsu.iroha2.type.SetType
import jp.co.soramitsu.iroha2.type.StringType
import jp.co.soramitsu.iroha2.type.Type
import jp.co.soramitsu.iroha2.type.U128Type
import jp.co.soramitsu.iroha2.type.U16Type
import jp.co.soramitsu.iroha2.type.U256Type
import jp.co.soramitsu.iroha2.type.U32Type
import jp.co.soramitsu.iroha2.type.U64Type
import jp.co.soramitsu.iroha2.type.U8Type
import jp.co.soramitsu.iroha2.type.VecType

val SCALE_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
val SCALE_CODEC_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
val SCALE_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleWriter")
val SCALE_CODEC_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecWriter")
val HASH_MAP_CREATER = MemberName("jp.co.soramitsu.iroha2", "hashMapWithSize")
val HASH_SET_CREATER = MemberName("jp.co.soramitsu.iroha2", "hashSetWithSize")
val BIT_64_WRITER = MemberName("jp.co.soramitsu.iroha2", "writeBit64")
val BIT_64_READER = MemberName("jp.co.soramitsu.iroha2", "readBit64")
val COMPACT_ULONG_WRITER = MemberName("io.emeraldpay.polkaj.scale.writer", "CompactULongWriter")
val COMPACT_BIG_INT_READER = MemberName("io.emeraldpay.polkaj.scale.reader", "CompactBigIntReader")
val OPTIONAL = ClassName("java.util", "Optional")
val SCALE_CODEC_EX_WRAPPER = ClassName("jp.co.soramitsu.iroha2", "wrapException")

fun resolveScaleReadImpl(type: Type): CodeBlock {
    return when (type) {
        is ArrayType -> CodeBlock.of("reader.readByteArray(%L)", type.size)
        is VecType -> {
            when (type.innerType.requireValue()) {
                is U8Type -> CodeBlock.of("reader.readByteArray()")
                else -> CodeBlock.of(
                    "List(reader.readCompactInt()) {%L}",
                    resolveScaleReadImpl(type.innerType.requireValue())
                )
            }
        }
        is SetType -> {
            CodeBlock.of(
                "%M(reader.readCompactInt()) {%L}",
                HASH_SET_CREATER,
                resolveScaleReadImpl(type.innerType.requireValue()),
            )
        }
        is MapType -> {
            CodeBlock.of(
                "%1M(reader.readCompactInt(), {%2L}, {%3L})",
                HASH_MAP_CREATER,
                resolveScaleReadImpl(type.key.requireValue()),
                resolveScaleReadImpl(type.value.requireValue())
            )
        }
        is U8Type -> CodeBlock.of("reader.readByte()")
        is U16Type -> CodeBlock.of("reader.readUint16()")
        is U32Type -> CodeBlock.of("reader.readUint32()")
        is U64Type -> CodeBlock.of("%1M(reader).toBigInteger()", BIT_64_READER)
        is U128Type -> CodeBlock.of("reader.readUint128()")
        is U256Type -> CodeBlock.of("BigInteger(reader.readUint256())")
        is I64Type -> CodeBlock.of("%1M(reader)", BIT_64_READER)
        is StringType -> CodeBlock.of("reader.readString()")
        is BooleanType -> CodeBlock.of("reader.readBoolean()")
        is CompositeType -> {
            val typeName = resolveKotlinType(type)
            val typeNameWithoutGenerics = withoutGenerics(typeName)
            if (type.generics.isEmpty()) {
                CodeBlock.of("%1T.read(reader)", typeNameWithoutGenerics)
            } else {
                CodeBlock.of("%1T.read(reader) as %2T", typeNameWithoutGenerics, typeName)
            }
        }
        is OptionType -> {
            CodeBlock.of("reader.readOptional(%T).orElse(null)", withoutGenerics(resolveKotlinType(type)))
        }
        is FixedPointType -> resolveScaleReadImpl(type.innerType.requireValue())
        is CompactType -> {
            return when (val innerType = type.innerType.requireValue()) {
                is U8Type -> CodeBlock.of("reader.readCompactInt().toShort()")
                is U16Type -> CodeBlock.of("reader.readCompactInt().toInt()")
                is U32Type -> CodeBlock.of("reader.readCompactInt().toLong()")
                is U64Type -> CodeBlock.of("reader.read(%M()).toBigInteger()", COMPACT_BIG_INT_READER)
                is U128Type, is U256Type -> CodeBlock.of("reader.read(%M())", COMPACT_BIG_INT_READER)
                else -> throw RuntimeException("Compact type implementation unresolved: $innerType")
            }
        }
        else -> throw RuntimeException("Unexpected type: $type")
    }
}

fun resolveScaleWriteImpl(type: Type, propName: CodeBlock): CodeBlock {
    return when (type) {
        is ArrayType -> CodeBlock.of("writer.writeByteArray(%L)", propName)
        is VecType -> {
            when (type.innerType.requireValue()) {
                is U8Type -> CodeBlock.of("writer.writeAsList(%L)", propName)
                else -> CodeBlock.of(
                    "writer.writeCompact(%1L.size)\n" +
                        "%1L.forEach { value -> %2L }",
                    propName,
                    resolveScaleWriteImpl(type.innerType.requireValue(), CodeBlock.of("value"))
                )
            }
        }
        is SetType -> {
            CodeBlock.of(
                "writer.writeCompact(%1L.size)\n" +
                    "%1L.forEach { value -> %2L }",
                propName,
                resolveScaleWriteImpl(type.innerType.requireValue(), CodeBlock.of("value"))
            )
        }
        is MapType -> {
            CodeBlock.of(
                "writer.writeCompact(%1L.size)\n" +
                    "%1L.forEach { (key, value) ->  \n\t%2L\n\t%3L\n}",
                propName,
                resolveScaleWriteImpl(type.key.requireValue(), CodeBlock.of("key")),
                resolveScaleWriteImpl(type.value.requireValue(), CodeBlock.of("value"))
            )
        }
        is U8Type -> CodeBlock.of("writer.writeByte(%L.toByte())", propName)
        is U16Type -> CodeBlock.of("writer.writeUint16(%L.toInt())", propName)
        is U32Type -> CodeBlock.of("writer.writeUint32(%L)", propName)
        is U64Type -> CodeBlock.of("%1M(writer, %2L)", BIT_64_WRITER, propName)
        is U128Type -> CodeBlock.of("writer.writeUint128(%L)", propName)
        is U256Type -> CodeBlock.of("writer.writeUint256(%L.toByteArray())", propName)
        is I64Type -> CodeBlock.of("%1M(writer, %2L)", BIT_64_WRITER, propName)
        is StringType -> CodeBlock.of(
            "writer.writeAsList(%L.toByteArray(Charsets.UTF_8))",
            propName
        )
        is BooleanType -> CodeBlock.of(
            "if (%L) { writer.directWrite(1) } else { writer.directWrite(0) }",
            propName
        )
        is CompositeType -> CodeBlock.of(
            "%T.write(writer, %L)",
            withoutGenerics(resolveKotlinType(type)),
            propName
        )
        is OptionType -> {
            CodeBlock.of(
                "writer.writeOptional(%1T, %2T.ofNullable(%3L))",
                withoutGenerics(resolveKotlinType(type)),
                OPTIONAL,
                propName
            )
        }
        is FixedPointType -> resolveScaleWriteImpl(type.innerType.requireValue(), propName)
        is CompactType -> CodeBlock.of("writer.write(%M(), %L.toLong())", COMPACT_ULONG_WRITER, propName)
        else -> throw RuntimeException("Unexpected type: $type")
    }
}

fun withoutGenerics(typeName: TypeName): ClassName {
    return when (typeName) {
        is ClassName -> typeName.topLevelClassName()
        is ParameterizedTypeName -> typeName.rawType.topLevelClassName()
        else -> throw RuntimeException("Unexpected type name: $typeName")
    }
}
