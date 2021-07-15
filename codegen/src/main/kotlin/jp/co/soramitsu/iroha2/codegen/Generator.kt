package jp.co.soramitsu.iroha2.codegen

import com.squareup.kotlinpoet.*
import jp.co.soramitsu.iroha2.parse.Types
import jp.co.soramitsu.iroha2.type.*
import java.nio.file.Paths

object GeneratorEntryPoint {
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


abstract class AbstractGenerator<T : Blueprint<*>> {
    fun generate(blueprint: T) {
        pipelineClass(blueprint)
    }

    fun pipelineClass(blueprint: T) {
        val clazz = TypeSpec.classBuilder(blueprint.className)
        implClassModifiers(blueprint, clazz)
        implKDoc(blueprint, clazz)
        implConstructor(blueprint, clazz)

        implBody(blueprint, clazz);

        val file = FileSpec.builder(blueprint.packageName, blueprint.className)
            .addType(clazz.build())
        implFileComments(file)
        writeToFIle(file)
    }

    fun implBody(blueprint: T, clazz: TypeSpec.Builder) {
        implInnerMembers(blueprint, clazz);
    }

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

interface ScaleCodecGenerator {
    fun implCodec(blueprint: Blueprint<*>, clazz: TypeSpec.Builder): TypeSpec.Builder

    fun implScaleReaderCode(blueprint: Blueprint<*>): CodeBlock

    fun implScaleWriterCode(blueprint: Blueprint<*>): CodeBlock

    fun resolveReadImplementationByType(type: Type): String {
        return when (type) {
            is StringType -> "reader.readString()"
            is BooleanType -> "reader.readBoolean()"
            is OptionType -> {
                "OptionType"
//                val className = when (val kotlinType = resolveKotlinType(type.innerType!!)) {
//                    is ClassName -> kotlinType.canonicalName
//                    is ParameterizedTypeName -> kotlinType.rawType.canonicalName
//                    else -> throw RuntimeException("Unexpected type")
//                }
//                "reader.readOptional($className)"
            }
            is CompactType -> "reader.readCompactInt()"
            is UIntType -> "reader.readLong().toInt()"
            is VecType -> {
                "VecType"
//                when (type.innerType) {
//                    is Tuple -> {
//                        val tuple = type.innerType as Tuple
//                        val keyClassName = when (val kotlinType = resolveKotlinType(tuple.typeReferences.first().value!!)) {
//                            is ClassName -> kotlinType.canonicalName
//                            is ParameterizedTypeName -> kotlinType.rawType.canonicalName
//                            else -> throw RuntimeException("Unexpected type")
//                        }
//                        val valueClassName = when (val kotlinType = resolveKotlinType(tuple.typeReferences.last().value!!)) {
//                            is ClassName -> kotlinType.canonicalName
//                            is ParameterizedTypeName -> kotlinType.rawType.canonicalName
//                            else -> throw RuntimeException("Unexpected type")
//                        }
//                        "reader.read(jp.co.soramitsu.schema.codegen.MapReader($keyClassName, $valueClassName))"
//                    }
//                    else -> {
//                        when (val kotlinType = resolveKotlinType(type.innerType!!)) {
//                            is ClassName -> "reader.read(io.emeraldpay.polkaj.scale.reader.ListReader(${kotlinType.simpleName}))"
//                            is ParameterizedTypeName -> "reader.read(io.emeraldpay.polkaj.scale.reader.ListReader(${kotlinType.rawType.simpleName}))"
//                            else -> throw RuntimeException("Unexpected type")
//                        }
//                    }
//                }
            }
            is SetType -> {
                "setType"
//                when (val kotlinType = resolveKotlinType(type.innerType!!)) {
//                    is ClassName -> "reader.read(io.emeraldpay.polkaj.scale.reader.ListReader(${kotlinType.simpleName})).toSet()"
//                    is ParameterizedTypeName -> "reader.read(io.emeraldpay.polkaj.scale.reader.ListReader(${kotlinType.rawType.simpleName})).toSet()"
//                    else -> throw RuntimeException("Unexpected type")
//                }
            }
            is StructType -> {
                "structType"
            }
            is MapType -> {
                "structType"
            }
            is TupleStructType -> {
                "tupleStructType"
            }
            is EnumType -> {
                "enumType"
            }
            else -> throw RuntimeException("Unexpected type $type")
//                when (val kotlinType = resolveKotlinType(type)) {
//                    is ClassName -> "${kotlinType.canonicalName}.read(reader)"
//                    is ParameterizedTypeName -> "${kotlinType.rawType.canonicalName}.read(reader)"
//                    else -> throw RuntimeException("Unexpected type")
//                }
        }
    }
}

object StructGenerator : AbstractGenerator<StructBlueprint>() {
    override fun implKDoc(blueprint: StructBlueprint, clazz: TypeSpec.Builder) {
        super.implKDoc(blueprint, clazz)
        clazz.addKdoc("\n\nGenerated from '${blueprint.type.name}' regular structure")
    }
}


object TupleStructGenerator : AbstractGenerator<TupleStructBlueprint>() {
    override fun implKDoc(
        blueprint: TupleStructBlueprint,
        clazz: TypeSpec.Builder
    ) {
        super.implKDoc(blueprint, clazz)
        //todo fix generic names
        clazz.addKdoc("\n\nGenerated from '${blueprint.type.name}' tuple structure")
    }
}

object EnumGenerator : AbstractGenerator<EnumBlueprint>() {
    override fun implKDoc(blueprint: EnumBlueprint, clazz: TypeSpec.Builder) {
        super.implKDoc(blueprint, clazz)
        clazz.addKdoc("\n\nGenerated from '${blueprint.packageName}.${blueprint.className}' enum")
    }

    override fun implClassModifiers(blueprint: EnumBlueprint, clazz: TypeSpec.Builder) {
        super.implClassModifiers(blueprint, clazz)
        clazz.addModifiers(KModifier.SEALED)
    }

    // Class generated from Rust Enums no need to have constructor due they are not intended
    // to be instantiated
    override fun implConstructor(blueprint: EnumBlueprint, clazz: TypeSpec.Builder) = Unit

    override fun implInnerMembers(blueprint: EnumBlueprint, clazz: TypeSpec.Builder) {
        for (variant in blueprint.variants) {
            val variantClass = TypeSpec.classBuilder(variant.variantName)
                .superclass(ClassName(blueprint.packageName, blueprint.className))
                .addKdoc("'${variant.variantName}' variant")
            if (variant.innerValue != null) {
                val innerValue = variant.innerValue
                val constructorBuilder = FunSpec.constructorBuilder()
                    .addParameter(
                        ParameterSpec.builder(
                            innerValue.normalizedPropName,
                            innerValue.propTypeName
                        ).build()
                    )
                variantClass.addProperty(
                    PropertySpec.builder(
                        innerValue.normalizedPropName,
                        innerValue.propTypeName,
                        KModifier.PRIVATE
                    )
                        .initializer(innerValue.normalizedPropName)
                        .build()
                )
                variantClass.primaryConstructor(constructorBuilder.build())
            }
            clazz.addType(variantClass.build())
        }
    }

}
