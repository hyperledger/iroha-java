package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import jp.co.soramitsu.iroha2.codegen.Blueprint
import java.nio.file.Paths

abstract class AbstractGenerator<T : Blueprint<*>> {
    fun generate(blueprint: T) {
        pipelineClass(blueprint)
    }

    fun pipelineClass(blueprint: T) {
        val clazz = TypeSpec.classBuilder(blueprint.className)
        implClassModifiers(blueprint, clazz)
        implKDoc(blueprint, clazz)
        implConstructor(blueprint, clazz)
        implClassBody(blueprint, clazz);

        val file = FileSpec.builder(blueprint.packageName, blueprint.className)
            .addType(clazz.build())
        implFileComments(file)
        writeToFIle(file)
    }

    open fun implClassBody(blueprint: T, clazz: TypeSpec.Builder) {
        implFunctions(blueprint, clazz)
        implInnerMembers(blueprint, clazz)
        //todo also change as below
        clazz.addType(implCompanions(blueprint, clazz).build())
    }

    open fun implCompanions(blueprint: T, clazz: TypeSpec.Builder): TypeSpec.Builder {
        val thisType = ClassName(blueprint.packageName, blueprint.className)
        //todo move in companion?
        val scaleReader = ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
        val scaleCodecReader = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
        val scaleWriter = ClassName("io.emeraldpay.polkaj.scale", "ScaleWriter")
        val scaleCodecWriter = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecWriter")
        return TypeSpec.companionObjectBuilder()
            .addSuperinterface(scaleReader.parameterizedBy(thisType))
            .addSuperinterface(scaleWriter.parameterizedBy(thisType))
            .addFunction(
                FunSpec.builder("read")
                    .addParameter(ParameterSpec.builder("reader", scaleCodecReader).build())
                    .addCode(scaleReaderCode(blueprint))
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(thisType)
                    .build()
            )
            .addFunction(
                FunSpec.builder("write")
                    .addParameter(ParameterSpec.builder("writer", scaleCodecWriter).build())
                    .addParameter(ParameterSpec.builder("instance", thisType).build())
                    .addCode(scaleWriterCode(blueprint))
                    .addModifiers(KModifier.OVERRIDE)
                    .build()
            )
    }

    //todo move to separate interface
    open fun scaleReaderCode(blueprint: T): CodeBlock {
        val code =
            blueprint.properties.joinToString(",\n") { resolveScaleReadImplementation(it).toString() }

        return CodeBlock.of("return ${blueprint.className}($code)")
    }

    open fun scaleWriterCode(blueprint: T): CodeBlock {
        val code = blueprint.properties.joinToString ("\n") { resolveScaleWriteImplementation(it).toString()}
        return CodeBlock.of(code)
    }

    open fun implFunctions(blueprint: T, clazz: TypeSpec.Builder) = Unit

    open fun implInnerMembers(blueprint: T, clazz: TypeSpec.Builder) = Unit

    open fun implClassModifiers(blueprint: T, clazz: TypeSpec.Builder) {
        clazz.addModifiers(KModifier.PUBLIC)
    }

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


    open fun implFileComments(file: FileSpec.Builder) {
        file.addComment("\nAuto-generated file. DO NOT EDIT!\n")
    }

    open fun writeToFIle(file: FileSpec.Builder) {
        file.build().writeTo(Paths.get("client/src/main/kotlin"))
    }
}
