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
    }
}

fun resolveScaleReadImplementation(property: Property): CodeBlock {
    return when (property.original) {
        is StringType -> CodeBlock.of("reader.readString()")
        is BooleanType -> CodeBlock.of("reader.readBoolean()")
        is CompactType -> CodeBlock.of("reader.readCompactInt()")
        is OptionType -> CodeBlock.of("reader.readOptional(${foo(property.typeName)})")
        //todo move to companion
        is VecType -> CodeBlock.of("reader.read(%T(${foo(property.typeName)}))",
            ScaleConstants.LIST_READER
        )
        is SetType -> CodeBlock.of("reader.read(%T(${foo(property.typeName)}))",
            ScaleConstants.LIST_READER
        )
        else -> CodeBlock.of("${foo(property.typeName)}.read(reader)")
    }
}

fun resolveScaleWriteImplementation(property: Property): CodeBlock {
    return when (property.original) {
        is StringType -> CodeBlock.of("writer.writeAsList(instance.${property.name}.encodeToByteArray())")
        is BooleanType -> CodeBlock.of("writer.writeByte(if (instance.${property.name}) {1} else {0})")
        is CompactType -> CodeBlock.of("writer.writeCompact(instance.${property.name})")
        is OptionType -> CodeBlock.of("writer.writeOptional(${foo(property.typeName)}, instance.${property.name})")
        is VecType -> CodeBlock.of("writer.write(%T, instance.${property.name})",
            ScaleConstants.LIST_WRITER
        )
        //todo create set reader and set writer?
        is SetType -> CodeBlock.of("writer.write(%T, instance.${property.name}.toList())",
            ScaleConstants.LIST_WRITER
        )
        else -> CodeBlock.of("${foo(property.typeName)}.write(writer, instance.${property.name})")
    }
}


//todo rename
private fun foo(typeName: TypeName): String {
    return when (typeName) {
        is ClassName -> typeName.simpleName
        is ParameterizedTypeName -> foo(typeName.typeArguments.first())
        else -> throw RuntimeException("Unexpected type: $typeName")
    }
}
