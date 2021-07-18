package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeName
import jp.co.soramitsu.iroha2.codegen.Property
import jp.co.soramitsu.iroha2.type.*

class ScaleConstants {
    companion object {
        val SCALE_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
        val SCALE_CODEC_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
        val SCALE_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleWriter")
        val SCALE_CODEC_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecWriter")
        val LIST_READER = ClassName("io.emeraldpay.polkaj.scale.reader", "ListReader")
        val LIST_WRITER = ClassName("io.emeraldpay.polkaj.scale.writer", "ListWriter")
        val UBYTE_READER = ClassName("jp.co.soramitsu.iroha2.scale", "UByteReader")
    }
}

fun resolveScaleReadImplementation(property: Property): CodeBlock {
    return when (property.original) {
        is StringType -> CodeBlock.of("reader.readString()")
        is BooleanType -> CodeBlock.of("reader.readBoolean()")
        is CompactType -> CodeBlock.of("reader.readCompactInt()")
        is OptionType -> CodeBlock.of("reader.readOptional(${getRealClassName(property.typeName)})")
        is ArrayType -> {
            when (property.original.innerType.requireValue()) {
                is U8Type -> CodeBlock.of("reader.readByteArray()")
                else -> CodeBlock.of("")
            }
        }
        is VecType -> CodeBlock.of("reader.read(%T(${getRealClassName(property.typeName)}))",
            ScaleConstants.LIST_READER
        )
        is SetType -> CodeBlock.of("reader.read(%T(${getRealClassName(property.typeName)}))",
            ScaleConstants.LIST_READER
        )
        is CompositeType -> CodeBlock.of("${getRealClassName(property.typeName)}.read(reader)")
        else -> CodeBlock.of("${getRealClassName(property.typeName)}.read(reader)")
//        else -> throw RuntimeException("Scale reader implementation is not resolved for type: '${property.original::class}'")
    }
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
            ScaleConstants.LIST_WRITER
        )
        //todo create set reader and set writer?
        is SetType -> CodeBlock.of("writer.write(%T(${getRealClassName(property.typeName)}), instance.${property.name}.toList())",
            ScaleConstants.LIST_WRITER
        )
        else -> CodeBlock.of("${getRealClassName(property.typeName)}.write(writer, instance.${property.name})")
    }
}


private fun getRealClassName(typeName: TypeName): String {
    return when (typeName) {
        is ClassName -> typeName.simpleName
        is ParameterizedTypeName -> getRealClassName(typeName.typeArguments.first())
        else -> throw RuntimeException("Unexpected type: $typeName")
    }
}
