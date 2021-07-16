package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.*
import jp.co.soramitsu.iroha2.codegen.EnumBlueprint
import jp.co.soramitsu.iroha2.codegen.Property
import jp.co.soramitsu.iroha2.codegen.StructBlueprint
import jp.co.soramitsu.iroha2.codegen.TupleStructBlueprint
import jp.co.soramitsu.iroha2.codegen.generator.EnumGenerator
import jp.co.soramitsu.iroha2.codegen.generator.StructGenerator
import jp.co.soramitsu.iroha2.codegen.generator.TupleStructGenerator
import jp.co.soramitsu.iroha2.parse.Types
import jp.co.soramitsu.iroha2.type.*

//todo all enum variants must imoplement discrimant behaviour
//todo versions of dependencies
//todo move modules to folder
public object GeneratorEntryPoint {
    fun generate(types: Types) {
        for ((_, type) in types) {
            when (type) {
                is StructType -> StructGenerator.generate(StructBlueprint(type))
                is TupleStructType -> TupleStructGenerator.generate(TupleStructBlueprint(type))
                is EnumType -> EnumGenerator.generate(EnumBlueprint(type))
            }
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
