package jp.co.soramitsu.iroha2.codegen

import com.squareup.kotlinpoet.*
import jp.co.soramitsu.iroha2.parse.Types
import jp.co.soramitsu.iroha2.type.*
import java.nio.file.Paths
import java.util.*

object CodeGenerator {
    fun generate(types: Types) {
        for ((_, type) in types) {
            when (type) {
                is StructType -> StructGenerator.generate(StructBlueprint(type))
                is TupleStructType -> TupleStructGenerator.generate(TupleStructBlueprint(type))
            }
        }
    }
}


//todo make interface
abstract class AbstractGenerator<T : Blueprint<*>> : ScaleCodecGenerator {
    fun generate(blueprint: T) {
        println(blueprint)
        pipelineClass(blueprint)
    }

    private fun pipelineClass(blueprint: T) {
        val clazz = TypeSpec.classBuilder(blueprint.className)
        implClassModifiers(blueprint, clazz)
        implKDoc(blueprint, clazz)
        implConstructor(blueprint, clazz)
        implBody(blueprint, clazz)

        val file = FileSpec.builder(blueprint.packageName, blueprint.className)
            .addType(clazz.build())
        implFileComments(file)
        writeToFIle(file)
    }

    open fun implClassModifiers(blueprint: T, clazz: TypeSpec.Builder): TypeSpec.Builder {
        return clazz.addModifiers(KModifier.PUBLIC)
    }

    open fun implConstructor(blueprint: T, clazz: TypeSpec.Builder): TypeSpec.Builder {
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
        return clazz.primaryConstructor(constructorBuilder.build())
    }

    open fun implBody(blueprint: T, clazz: TypeSpec.Builder): TypeSpec.Builder {
        implCodec(blueprint, clazz)
        implFunctions(blueprint, clazz)
        implSealedClasses(blueprint, clazz)
        return clazz
    }

    open fun implSealedClasses(blueprint: T, clazz: TypeSpec.Builder): TypeSpec.Builder {
        return clazz
    }

    open fun implFunctions(blueprint: T, clazz: TypeSpec.Builder): TypeSpec.Builder {
        return clazz
    }

    override fun implScaleReaderCode(blueprint: Blueprint<*>): CodeBlock {
//        val code = StringJoiner(", ")
//        for ((_, typeRef) in blueprint.type.mapping) {
//            code.add(resolveReadImplementationByType(typeRef.value!!))
//        }
//        return CodeBlock.of("return ${blueprint.className}($code)")
        return CodeBlock.of("foo")
    }

    override fun implScaleWriterCode(blueprint: Blueprint<*>): CodeBlock {
        TODO("Not yet implemented")
    }

    override fun implCodec(blueprint: Blueprint<*>, clazz: TypeSpec.Builder): TypeSpec.Builder {
        return clazz
//        return clazz.addType(
//            TypeSpec.companionObjectBuilder()
//                .addSuperinterface(
//                    ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
//                        .parameterizedBy(
//                            ClassName(
//                                blueprint.packageName,
//                                blueprint.className
//                            )
//                        )
//                )
//                .addSuperinterface(
//                    ClassName("io.emeraldpay.polkaj.scale", "ScaleWriter")
//                        .parameterizedBy(
//                            ClassName(
//                                blueprint.packageName,
//                                blueprint.className
//                            )
//                        )
//                )
//                .addFunction(
//                    FunSpec.builder("read")
//                        .addParameter(
//                            ParameterSpec
//                                .builder(
//                                    "reader",
//                                    ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
//                                )
//                                .build()
//                        )
//                        .addCode(this.implScaleReaderCode(blueprint))
//                        .addModifiers(KModifier.OVERRIDE)
//                        .returns(
//                            ClassName(
//                                blueprint.packageName,
//                                blueprint.className
//                            )
//                        )
//                        .build()
//                )
//                .addFunction(
//                    FunSpec.builder("write")
//                        .addParameter(
//                            ParameterSpec
//                                .builder(
//                                    "writer",
//                                    ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecWriter")
//                                )
//                                .build()
//                        )
//                        .addParameter(
//                            ParameterSpec
//                                .builder(
//                                    "instance",
//                                    ClassName(
//                                        blueprint.packageName,
//                                        blueprint.className
//                                    )
//                                )
//                                .build()
//                        )
////                        .addCode(implScaleWriterCode(blueprint))
//                        .addModifiers(KModifier.OVERRIDE)
//                        .build()
//                )
//                .build()
//        )
    }

    open fun implKDoc(blueprint: T, clazz: TypeSpec.Builder): TypeSpec.Builder {
        return clazz.addKdoc(blueprint.className)
    }

    open fun implFileComments(file: FileSpec.Builder): FileSpec.Builder {
        return file.addComment("\nAuto-generated file. DO NOT EDIT!\n")
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
