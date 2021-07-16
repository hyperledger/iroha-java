package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import jp.co.soramitsu.iroha2.codegen.Blueprint
import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.SCALE_CODEC_READER
import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.SCALE_CODEC_WRITER
import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.SCALE_READER
import jp.co.soramitsu.iroha2.codegen.generator.ScaleConstants.Companion.SCALE_WRITER

abstract class AbstractGenerator<T : Blueprint<*>> {
    fun generate(blueprint: T) : TypeSpec = pipelineClass(blueprint)

    open fun pipelineClass(blueprint: T) : TypeSpec {
        val clazz = TypeSpec.classBuilder(blueprint.className)
        implClassModifiers(blueprint, clazz)
        implKDoc(blueprint, clazz)
        implConstructor(blueprint, clazz)
        implClassBody(blueprint, clazz)

        return clazz.build()
    }

    open fun implClassBody(blueprint: T, clazz: TypeSpec.Builder) {
        implFunctions(blueprint, clazz)
        implInnerClasses(blueprint, clazz)
        //todo also change as below
        clazz.addType(implCompanions(blueprint, clazz).build())
    }

    open fun implCompanions(blueprint: T, clazz: TypeSpec.Builder): TypeSpec.Builder {
        val thisType = ClassName(blueprint.packageName, blueprint.className)
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

    //todo move to separate interface
    open fun scaleReaderCode(blueprint: T): CodeBlock {
        val code = blueprint.properties
                .joinToString(",\n") { resolveScaleReadImplementation(it).toString() }

        return CodeBlock.of("return ${blueprint.className}($code)")
    }

    open fun scaleWriterCode(blueprint: T): CodeBlock {
        val code = blueprint.properties.joinToString ("\n") { resolveScaleWriteImplementation(it).toString()}
        return CodeBlock.of(code)
    }

    open fun implFunctions(blueprint: T, clazz: TypeSpec.Builder) = Unit

    open fun implInnerClasses(blueprint: T, clazz: TypeSpec.Builder) = Unit

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

}
