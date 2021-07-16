package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
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

    override fun implInnerClasses(blueprint: EnumBlueprint, clazz: TypeSpec.Builder) {
        for (variant in blueprint.variants) {
            clazz.addType(EnumVariantGenerator.generate(variant))
        }
    }

    override fun scaleReaderCode(blueprint: EnumBlueprint): CodeBlock {
        val codeBlock = CodeBlock.builder().add("return when(val discriminant = reader.readUByte()) {\n")
        val whenFlow = blueprint.variants.joinToString("\n") {
            CodeBlock.of("\t${it.discriminant} -> ${it.className}.read(reader)").toString()
        }
        codeBlock.add(whenFlow)
        codeBlock.add("\n\telse -> throw RuntimeException(\"Unresolved discriminant of the enum variant: \$discriminant\")")
        return codeBlock.add("}").build()
    }

    override fun scaleWriterCode(blueprint: EnumBlueprint): CodeBlock {
        val codeBlock = CodeBlock.builder().add("writer.directWrite(instance.discriminant())\n")
        codeBlock.add("when(val discriminant = instance.discriminant()) {\n")
        val whenFlow = blueprint.variants.joinToString("\n") {
            CodeBlock.of("\t${it.discriminant} -> ${it.className}.write(writer, instance as ${it.className})")
                .toString()
        }
        codeBlock.add(whenFlow)
        codeBlock.add("\n\telse -> throw RuntimeException(\"Unresolved discriminant of the enum variant: \$discriminant\")")
        return codeBlock.add("}").build()
    }
}
