package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.*
import jp.co.soramitsu.iroha2.codegen.*
import jp.co.soramitsu.iroha2.parse.Types
import jp.co.soramitsu.iroha2.type.*
import java.nio.file.Paths

//todo all enum variants must imoplement discrimant behaviour
//todo versions of dependencies
//todo move modules to folder
object GeneratorEntryPoint {
    fun generate(types: Types) {
        types.values.mapNotNull {
            when(it) {
                is StructType -> StructBlueprint(it)
                is EnumType -> EnumBlueprint(it)
                is TupleStructType -> TupleStructBlueprint(it)
                else -> null
            }
        }.forEach {
            val typeSpec = when(it) {
                is StructBlueprint -> StructGenerator.generate(it)
                is EnumBlueprint -> EnumGenerator.generate(it)
                is TupleStructBlueprint -> TupleStructGenerator.generate(it)
                else -> throw RuntimeException("Unexpected blueprint type: ${it::class}")
            }
            FileSpec.builder(it.packageName, it.className)
                .addType(typeSpec)
                .addComment("\nAuto-generated file. DO NOT EDIT!\n")
                .build()
                .writeTo(Paths.get("client/src/main/kotlin"))
        }
    }
}


//todo rename
fun foo(typeName: TypeName): String {
    return when (typeName) {
        is ClassName -> typeName.simpleName
        is ParameterizedTypeName -> foo(typeName.typeArguments.first())
        else -> throw RuntimeException("Unexpected type: $typeName")
    }
}

fun resolveScaleReadImplementation(property: Property): CodeBlock {
    return when (property.original) {
        is StringType -> CodeBlock.of("reader.readString()")
        is BooleanType -> CodeBlock.of("reader.readBoolean()")
        is CompactType -> CodeBlock.of("reader.readCompactInt()")
        is OptionType -> CodeBlock.of("reader.readOptional(${foo(property.typeName)})")
        //todo move to companion
        is VecType -> CodeBlock.of(
            "reader.read(%T(${foo(property.typeName)}))",
            ClassName("io.emeraldpay.polkaj.scale.reader", "ListReader")
        )
        is SetType -> CodeBlock.of(
            "reader.read(%T(${foo(property.typeName)}))",
            ClassName("io.emeraldpay.polkaj.scale.reader", "ListReader")
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
        is VecType -> CodeBlock.of(
            "writer.write(%T, instance.${property.name})",
            ClassName("io.emeraldpay.polkaj.scale.writer", "ListWriter")
        )
        //todo create set reader and set writer?
        is SetType -> CodeBlock.of(
            "writer.write(%T, instance.${property.name}.toList())",
            ClassName("io.emeraldpay.polkaj.scale.writer", "ListWriter")
        )
        else -> CodeBlock.of("${foo(property.typeName)}.write(writer, instance.${property.name})")
    }
}
