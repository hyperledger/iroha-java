package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.WildcardTypeName
import jp.co.soramitsu.iroha2.codegen.blueprint.Blueprint
import jp.co.soramitsu.iroha2.type.CompositeType

abstract class AbstractGenerator<T : Blueprint<*>> {
    fun generate(blueprint: T): TypeSpec = pipelineClass(blueprint)

    open fun pipelineClass(blueprint: T): TypeSpec {
        val clazz = TypeSpec.classBuilder(ClassName(blueprint.packageName, blueprint.className))
        implGenerics(blueprint, clazz)
        implSuperClasses(blueprint, clazz)
        implClassModifiers(blueprint, clazz)
        implKDoc(blueprint, clazz)
        implConstructor(blueprint, clazz)
        implClassBody(blueprint, clazz)

        return clazz.build()
    }

    open fun implGenerics(blueprint: T, clazz: TypeSpec.Builder) {
        if (blueprint.source is CompositeType) {
            val generics = blueprint.source.generics
            for (i in generics.indices) {
                clazz.addTypeVariable(TypeVariableName.Companion.invoke("T$i"))
            }
        }
    }

    open fun implClassBody(blueprint: T, clazz: TypeSpec.Builder) {
        implFunctions(blueprint, clazz)
        implInnerClasses(blueprint, clazz)
        clazz.addType(implCompanions(blueprint, clazz).build())
    }

    open fun implCompanions(blueprint: T, clazz: TypeSpec.Builder): TypeSpec.Builder {
        val thisType = if (blueprint.source is CompositeType && blueprint.source.generics.isNotEmpty()) {
            ClassName(blueprint.packageName, blueprint.className)
                .parameterizedBy(WildcardTypeName.producerOf(ClassName("kotlin", "Any")))
        } else {
            ClassName(blueprint.packageName, blueprint.className)
        }
        return TypeSpec.companionObjectBuilder()
            .addSuperinterface(SCALE_READER.parameterizedBy(thisType))
            .addSuperinterface(SCALE_WRITER.parameterizedBy(thisType))
            .addFunction(
                FunSpec.builder("read")
                    .addParameter(ParameterSpec.builder("reader", SCALE_CODEC_READER).build())
                    .addCode(scaleReaderCode(blueprint))
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(thisType)
                    .build()
            )
            .addFunction(
                FunSpec.builder("write")
                    .addParameter(ParameterSpec.builder("writer", SCALE_CODEC_WRITER).build())
                    .addParameter(ParameterSpec.builder("instance", thisType).build())
                    .addCode(scaleWriterCode(blueprint))
                    .addModifiers(KModifier.OVERRIDE)
                    .build()
            )
    }

    open fun scaleReaderCode(blueprint: T): CodeBlock {
        var result = CodeBlock.builder().add("return ${blueprint.className}(\n").indent()
        val codeBlocks = blueprint.properties.map { resolveScaleReadImpl(it.original) }.toList()
        for (cb in codeBlocks) {
            result = result.add(cb).add(",\n")
        }
        return result.unindent().add(")").build()
    }

    open fun scaleWriterCode(blueprint: T): CodeBlock {
        var result = CodeBlock.builder().indent()
        val codeBlocks = blueprint.properties.map { resolveScaleWriteImpl(it.original, CodeBlock.of("instance.%N", it.name)) }
        for (cb in codeBlocks) {
            result = result.add(cb).add("\n")
        }
        return result.unindent().build()
    }

    open fun implFunctions(blueprint: T, clazz: TypeSpec.Builder) = Unit

    open fun implInnerClasses(blueprint: T, clazz: TypeSpec.Builder) = Unit

    open fun implClassModifiers(blueprint: T, clazz: TypeSpec.Builder) {
        if (blueprint.properties.isNotEmpty()) {
            clazz.addModifiers(KModifier.DATA)
        }
    }

    open fun implSuperClasses(blueprint: T, clazz: TypeSpec.Builder) = Unit

    open fun implConstructor(blueprint: T, clazz: TypeSpec.Builder) {
        if (blueprint.properties.isEmpty()) {
            return
        }
        val constructorBuilder = FunSpec.constructorBuilder()
        for ((name, type) in blueprint.properties) {
            constructorBuilder.addParameter(
                ParameterSpec.builder(name, type)
                    .build()
            )
            clazz.addProperty(
                PropertySpec.builder(name, type)
                    .initializer(name)
                    .build()
            )
        }
        clazz.primaryConstructor(constructorBuilder.build())
    }

    open fun implKDoc(blueprint: T, clazz: TypeSpec.Builder) {
        clazz.addKdoc(blueprint.className)
    }
}
