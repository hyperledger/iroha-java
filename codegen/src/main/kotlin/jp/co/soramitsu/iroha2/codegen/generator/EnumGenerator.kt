package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import jp.co.soramitsu.iroha2.codegen.EnumBlueprint

object EnumGenerator : AbstractGenerator<EnumBlueprint>() {
    override fun implKDoc(blueprint: EnumBlueprint, clazz: TypeSpec.Builder) {
        super.implKDoc(blueprint, clazz)
        clazz.addKdoc("\n\nGenerated from '${blueprint.type.name}' enum")
    }

    override fun implClassModifiers(blueprint: EnumBlueprint, clazz: TypeSpec.Builder) {
        super.implClassModifiers(blueprint, clazz)
        clazz.addModifiers(KModifier.SEALED)
    }

    // class generated from Rust Enums no need to have constructor due they are not intended
    // to be instantiated
    override fun implConstructor(blueprint: EnumBlueprint, clazz: TypeSpec.Builder) = Unit

    override fun implFunctions(blueprint: EnumBlueprint, clazz: TypeSpec.Builder) {
        clazz.addFunction(
            //todo move name??
            FunSpec.builder("discriminant")
                .addModifiers(KModifier.ABSTRACT)
                .returns(Int::class, CodeBlock.of("Discriminator of variant in enum"))
                .build()
        )
    }

    override fun implInnerMembers(blueprint: EnumBlueprint, clazz: TypeSpec.Builder) {
        //todo can variant proceed pipeline too?
        for (variant in blueprint.variants) {
            val variantClass = TypeSpec.classBuilder(variant.variantName)
                .superclass(ClassName(blueprint.packageName, blueprint.className))
                .addFunction(
                    FunSpec.builder("discriminant")
                        .addModifiers(KModifier.OVERRIDE)
                        .returns(Int::class)
                        .addCode("return DISCRIMINANT")
                        .build()
                )
                .addKdoc("'${variant.variantName}' variant")
            if (variant.innerValue != null) {
                val innerValue = variant.innerValue
                val constructorBuilder = FunSpec.constructorBuilder()
                    .addParameter(
                        ParameterSpec.builder(
                            innerValue.name,
                            innerValue.typeName
                        ).build()
                    )
                variantClass.addProperty(
                    PropertySpec.builder(
                        innerValue.name,
                        innerValue.typeName,
                        KModifier.PRIVATE
                    )
                        .initializer(innerValue.name)
                        .build()
                )
                variantClass.primaryConstructor(constructorBuilder.build())

                val thisType = ClassName(
                    "${blueprint.packageName}.${blueprint.className}",
                    variant.variantName
                )
                val scaleReader = ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
                val scaleCodecReader = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
                val scaleWriter = ClassName("io.emeraldpay.polkaj.scale", "ScaleWriter")
                val scaleCodecWriter = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecWriter")
                val companion = TypeSpec.companionObjectBuilder()
                    .addSuperinterface(scaleReader.parameterizedBy(thisType))
                    .addSuperinterface(scaleWriter.parameterizedBy(thisType))
                    .addFunction(
                        FunSpec.builder("read")
                            .addParameter(ParameterSpec.builder("reader", scaleCodecReader).build())
//                            .addCode(scaleReaderCode(blueprint))
                            .addModifiers(KModifier.OVERRIDE)
                            .returns(thisType)
                            .build()
                    )
                    .addFunction(
                        FunSpec.builder("write")
                            .addParameter(ParameterSpec.builder("writer", scaleCodecWriter).build())
                            .addParameter(ParameterSpec.builder("instance", thisType).build())
//                            .addCode(scaleWriterCode(blueprint))
                            .addModifiers(KModifier.OVERRIDE)
                            .build()
                    ).addProperty(
                        PropertySpec.builder("DISCRIMINANT", Int::class, KModifier.CONST)
                            .initializer("%L", variant.discriminant)
                            .build()
                    )
                variantClass.addType(companion.build())
            }
            clazz.addType(variantClass.build())
        }
    }

    override fun scaleReaderCode(blueprint: EnumBlueprint): CodeBlock {
        val codeBlock = CodeBlock.builder().add("return when(val discriminant = reader.readUByte()) {\n")
        val whenFlow = blueprint.variants.joinToString("\n") {
            CodeBlock.of("\t${it.discriminant} -> ${it.variantName}.read(reader)").toString()
        }
        codeBlock.add(whenFlow)
        codeBlock.add("\n\telse -> throw RuntimeException(\"Unresolved discriminant of the enum variant: \$discriminant\")")
        return codeBlock.add("}").build()
    }

    override fun scaleWriterCode(blueprint: EnumBlueprint): CodeBlock {
        val codeBlock = CodeBlock.builder().add("writer.directWrite(instance.discriminant())\n")
        codeBlock.add("when(val discriminant = instance.discriminant()) {\n")
        val whenFlow = blueprint.variants.joinToString("\n") {
            CodeBlock.of("\t${it.discriminant} -> ${it.variantName}.write(writer, instance as ${it.variantName})")
                .toString()
        }
        codeBlock.add(whenFlow)
        codeBlock.add("\n\telse -> throw RuntimeException(\"Unresolved discriminant of the enum variant: \$discriminant\")")
        return codeBlock.add("}").build()
    }
}
