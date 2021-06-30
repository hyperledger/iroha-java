package jp.co.soramitsu.schema.codegen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import io.emeraldpay.polkaj.scale.reader.UnionReader
import jp.co.soramitsu.schema.StringType
import jp.co.soramitsu.schema.TypePreset
import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.composite.*
import jp.co.soramitsu.schema.definitions.types.primitives.*
import java.nio.file.Paths
import java.util.*


object CodeGenerator {

    fun generate(typePreset: TypePreset) {
        typePreset.map { (_, typeRef) ->
            when (val type = typeRef.value) {
                is Struct -> generateRegularStruct(type)
                is EnumType -> generateEnum(type)
                is TupleStruct -> generateTupleStruct(type)
                else -> null
            }
        }
            .filterNotNull()
            .map { it.addComment("Do not change. Autogenerated code") }
            .map(FileSpec.Builder::build)
            .forEach { it.writeTo(Paths.get("codegen-engine/src/main/kotlin")) }
    }

    private fun generateClassSkeleton(type: Type<*>, className: String): TypeSpec.Builder {
        val clazz = TypeSpec.classBuilder(className)
            .addModifiers(KModifier.PUBLIC)

        return clazz
    }

    private fun generateRegularStruct(type: Struct): FileSpec.Builder? {
        val (className, packageName, _) = defineFullClassNames(type.name)

        val clazz = generateClassSkeleton(type, className)
            .addKdoc("$className\n\n")
            .addKdoc("Generated from '${type.name}' regular structure")

        implementScaleCodec(
            clazz,
            className,
            packageName,
            implScaleReaderForStructs(type, className),
            implScaleWriterForStructs(type, className)
        )

        val constructorBuilder = FunSpec.constructorBuilder()

        for ((name, typeRef) in type.mapping) {
            val normalizedName = convertToCamelCase(name)
            val kotlinType = resolveKotlinType(typeRef.value!!)

            constructorBuilder.addParameter(
                ParameterSpec.builder(normalizedName, kotlinType)
                    .build()
            )

            clazz.addProperty(
                PropertySpec.builder(normalizedName, kotlinType, KModifier.PRIVATE)
                    .initializer(normalizedName)
                    .build()
            )
        }

        if (constructorBuilder.parameters.isNotEmpty()) {
            clazz.primaryConstructor(constructorBuilder.build())
        }

        return FileSpec.builder("jp.co.soramitsu.schema.generated.$packageName", className)
            .addType(clazz.build())
    }

    private fun implScaleWriterForStructs(type: Struct, structName: String): CodeBlock {
        val code = StringJoiner("\n")
        for ((propertyName, typeRef) in type.mapping) {
            code.add(fooWrite(typeRef.value!!, convertToCamelCase(propertyName)))
        }
        return CodeBlock.of("$code")
    }

    private fun fooRead(type: Type<*>): String {
        return when (type) {
            is StringType -> "reader.readString()"
            is BooleanType -> "reader.readBoolean()"
            is Option -> "reader.readOptional()"
            is Compact -> "reader.readCompactInt()"
            is UIntType -> "reader.readLong().toInt()"
            is FixedByteArray, is DynamicByteArray -> "reader.readByteArray()"
            else -> {
                val kotlinType = resolveKotlinType(type)
                "$kotlinType.READER.read(reader)"
            }
        }
    }

    private fun fooWrite(type: Type<*>, propertyName: String): String {
        return when (type) {
            is StringType -> "writer.writeString(instance.$propertyName)"
            is BooleanType -> "writer.writeBoolean(instance.$propertyName)"
            is Option -> "writer.writeOptional(instance.$propertyName)"
            is Compact -> "writer.writeCompactInt(instance.$propertyName)"
            is UIntType -> "writer.writeLong(instance.$propertyName)"
            is FixedByteArray, is DynamicByteArray -> "writer.writeByteArray(instance.$propertyName)"
            else -> {
                val kotlinType = resolveKotlinType(type)
                "${kotlinType}.WRITER.write(writer)"
            }
        }
    }

    private fun implScaleReaderForStructs(type: Struct, structName: String): CodeBlock {
        val code = StringJoiner(", ")
        for ((_, typeRef) in type.mapping) {
            code.add(fooRead(typeRef.value!!))
        }
        return CodeBlock.of("return $structName($code)")
    }

    private fun implScaleWriterForTupleStructs(type: TupleStruct, structName: String): CodeBlock {
        val code = StringJoiner("\n")
        for (typeRef in type.types) {
            val propertyName = createTupleStructName(typeRef.value!!)
            code.add(fooWrite(typeRef.value!!, convertToCamelCase(propertyName)))
        }
        return CodeBlock.of("$code")
    }

    private fun implScaleReaderForTupleStructs(type: TupleStruct, structName: String): CodeBlock {
        val code = StringJoiner(", ")
        for (typeRef in type.types) {
            code.add(fooRead(typeRef.value!!))
        }
        return CodeBlock.of("return $structName($code)")
    }

    private fun implScaleReaderForEnumVariant(
        variantName: String,
        innerPropertyName: String
    ): CodeBlock {
//        val code = CodeBlock.of(fooWrite(variant.type!!.value!!, innerPropertyName))
        return CodeBlock.builder()
//            .add(".directWrite(this.discriminant())\n")
//            .add("$code")
            .build()
    }

    private fun implScaleWriterForEnumVariant(
        variant: EnumType.Variant,
        innerPropertyName: String
    ): CodeBlock {
        val code = CodeBlock.of(fooWrite(variant.type!!.value!!, innerPropertyName))
        return CodeBlock.builder()
            .add("writer.directWrite(this.discriminant())\n")
            .add("$code")
            .build()
    }

    private fun generateTupleStruct(type: TupleStruct): FileSpec.Builder? {
        val (className, packageName, _) = defineFullClassNames(type.name)

        val clazz = generateClassSkeleton(type, className)
            .addKdoc("$className\n\n")
            .addKdoc("Generated from '${type.name}' tuple structure")

        implementScaleCodec(
            clazz,
            className,
            packageName,
            implScaleReaderForTupleStructs(type, className),
            implScaleWriterForTupleStructs(type, className)
        )

        val constructorBuilder = FunSpec.constructorBuilder()

        for (typeRef in type.types) {
            val normalizedName = createTupleStructName(typeRef.value!!)
            val kotlinType = resolveKotlinType(typeRef.value!!)

            constructorBuilder.addParameter(
                ParameterSpec.builder(normalizedName, kotlinType)
                    .build()
            )

            clazz.addProperty(
                PropertySpec.builder(normalizedName, kotlinType, KModifier.PRIVATE)
                    .initializer(normalizedName)
                    .build()
            )
        }

        if (constructorBuilder.parameters.isNotEmpty()) {
            clazz.primaryConstructor(constructorBuilder.build())
        }

        return FileSpec.builder("jp.co.soramitsu.schema.generated.$packageName", className)
            .addType(clazz.build())
    }

    private fun createTupleStructName(type: Type<*>): String {
        return when (type) {
            is FixedByteArray -> {
                "array"
            }
            else -> {
                val (propertyName, _) = defineFullClassNames(type.name)
                propertyName.decapitalize(Locale.getDefault())
            }
        }
    }

    private fun generateEnum(type: EnumType): FileSpec.Builder? {
        val (className, packageName, _) = defineFullClassNames(type.name)

        val clazz = generateClassSkeleton(type, className)
            .addModifiers(KModifier.ABSTRACT)
            .addKdoc("$className\n\n")
            .addKdoc("Generated from '${type.name}' enum")
            .addFunction(
                FunSpec.builder("discriminant")
                    .addModifiers(KModifier.ABSTRACT)
                    .returns(Int::class.java, CodeBlock.of("Discriminator of variant in enum"))
                    .build()
            )

        for (variant in type.variants) {

            val variantClass = TypeSpec.classBuilder(variant.name)
                .addModifiers(KModifier.PUBLIC)
                .superclass(ClassName("jp.co.soramitsu.schema.generated.$packageName", className))
                .addFunction(
                    FunSpec.builder("discriminant")
                        .addModifiers(KModifier.OVERRIDE)
                        .returns(Int::class.java)
                        .addCode("return %L", variant.discriminant)
                        .build()
                )
                .addKdoc("'${variant.name}' variant")

            if (variant.type != null) {
                val (variantPropertyName, _, _) = defineFullClassNames(variant.name)
                val normalizedName = convertToCamelCase(variantPropertyName)
                    .decapitalize(Locale.getDefault())
                val kotlinType = resolveKotlinType(variant.type.value!!)
                val constructorBuilder = FunSpec.constructorBuilder()
                    .addParameter(
                        ParameterSpec.builder(normalizedName, kotlinType)
                            .build()
                    )

                variantClass.addProperty(
                    PropertySpec.builder(normalizedName, kotlinType, KModifier.PRIVATE)
                        .initializer(normalizedName)
                        .build()
                )

                variantClass.primaryConstructor(constructorBuilder.build())
                implementScaleCodec(
                    variantClass,
                    variant.name,
                    "$packageName.$className",
                    implScaleReaderForEnumVariant(variant.name, normalizedName),
                    implScaleWriterForEnumVariant(variant, normalizedName)
                )
            }
            clazz.addType(variantClass.build())
        }

        return FileSpec.builder("jp.co.soramitsu.schema.generated.$packageName", className)
            .addType(clazz.build())
    }

    private fun defineFullClassNames(typeFullName: String): Foo {
        //expected only one wildcard at most
        val wildcard = typeFullName.substringAfter('<', "")
            .substringBeforeLast('>', "")
        val className = typeFullName.substringBefore('<')
            .substringAfterLast("::")
        val packageName = typeFullName.substringBeforeLast(className)
            .removeSuffix("::")
            .removePrefix("iroha")
            .replace("::", ".")
            .replace("_", "")
        return Foo(className, packageName, wildcard)
    }

    private data class Foo(val className: String, val packageName: String, val wildcard: String)

    private fun convertToCamelCase(target: String): String {
        val tokenizer = StringTokenizer(target, "_")
        return if (tokenizer.hasMoreTokens()) {
            val resultBuilder = StringBuilder(tokenizer.nextToken())
            for (token in tokenizer) {
                resultBuilder.append((token as String).capitalize(Locale.getDefault()))
            }
            resultBuilder.toString()
        } else {
            target
        }
    }

    private fun resolveKotlinType(type: Type<*>): TypeName {
        return when (type) {
            is StringType -> ClassName("kotlin", "String")
            is BooleanType -> ClassName("kotlin", "Boolean")
            is EnumType, is TupleStruct, is Struct -> {
                val (className, packageName, _) = defineFullClassNames(type.name)
                ClassName("jp.co.soramitsu.schema.generated.$packageName", className)
            }
            is Vec -> {
                ClassName("kotlin.collections", "List")
                    .parameterizedBy(resolveKotlinType(type.innerType!!))
            }
            is SetType -> {
                ClassName("kotlin.collections", "Set")
                    .parameterizedBy(resolveKotlinType(type.innerType!!))
            }
            is Option -> {
                ClassName("java.util", "Optional")
                    .parameterizedBy(resolveKotlinType(type.innerType!!))
            }
            is Compact -> ClassName("kotlin", "Int")
            is UIntType -> ClassName("kotlin", "Int")
            is Tuple -> {
                ClassName("kotlin", "Pair")
                    .parameterizedBy(ClassName("kotlin", "String"), ClassName("kotlin", "String"))
            }
            is FixedByteArray, is DynamicByteArray -> {
                ClassName("kotlin", "ByteArray")
            }
            else -> throw RuntimeException("unexpected type: $type")
        }
    }

    private fun implementScaleCodec(
        clazz: TypeSpec.Builder,
        className: String,
        packageName: String,
        readerCode: CodeBlock,
        writerCode: CodeBlock,
    ) {
//        clazz.addSuperinterface(
//            ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
//                .parameterizedBy(
//                    ClassName(
//                        "jp.co.soramitsu.schema.generated.$packageName",
//                        className
//                    )
//                )
//        )
//        clazz.addSuperinterface(
//            ClassName("io.emeraldpay.polkaj.scale", "ScaleWriter")
//                .parameterizedBy(
//                    ClassName(
//                        "jp.co.soramitsu.schema.generated.$packageName",
//                        className
//                    )
//                )
//        )
//        clazz.addFunction(
//            FunSpec.builder("read")
//                .addParameter(
//                    ParameterSpec
//                        .builder(
//                            "reader",
//                            ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
//                        )
//                        .build()
//                )
//                .addCode(readerCode)
//                .addModifiers(KModifier.OVERRIDE)
//                .returns(ClassName("jp.co.soramitsu.schema.generated.$packageName", className))
//                .build()
//        )
        clazz.addType(
            TypeSpec.companionObjectBuilder("READER")
                .addSuperinterface(
                    ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
                        .parameterizedBy(
                            ClassName(
                                "jp.co.soramitsu.schema.generated.$packageName",
                                className
                            )
                        )
                )
                .addFunction(
                    FunSpec.builder("read")
                        .addParameter(
                            ParameterSpec
                                .builder(
                                    "reader",
                                    ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
                                )
                                .build()
                        )
                        .addCode(readerCode)
                        .addModifiers(KModifier.OVERRIDE)
                        .returns(
                            ClassName(
                                "jp.co.soramitsu.schema.generated.$packageName",
                                className
                            )
                        )
                        .build()
                )
                .build()
        )
//        clazz.addFunction(
//            FunSpec.builder("write")
//                .addParameter(
//                    ParameterSpec
//                        .builder(
//                            "writer",
//                            ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecWriter")
//                        )
//                        .build()
//                )
//                .addParameter(
//                    ParameterSpec
//                        .builder(
//                            "instance",
//                            ClassName("jp.co.soramitsu.schema.generated.$packageName", className)
//                        )
//                        .build()
//                )
//                .addCode(writerCode)
//                .addModifiers(KModifier.OVERRIDE)
//                .build()
//        );
    }

}
