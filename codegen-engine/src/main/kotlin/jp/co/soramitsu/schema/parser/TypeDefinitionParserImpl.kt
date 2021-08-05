package jp.co.soramitsu.schema.parser

import jp.co.soramitsu.schema.DynamicTypeResolver
import jp.co.soramitsu.schema.ParseResult
import jp.co.soramitsu.schema.TypeDefinitionParser
import jp.co.soramitsu.schema.TypePreset
import jp.co.soramitsu.schema.TypePresetBuilder
import jp.co.soramitsu.schema.create
import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.TypeReference
import jp.co.soramitsu.schema.definitions.types.composite.EnumType
import jp.co.soramitsu.schema.definitions.types.composite.Struct
import jp.co.soramitsu.schema.definitions.types.composite.TupleStruct
import jp.co.soramitsu.schema.definitions.types.primitives.FixedByteArray
import jp.co.soramitsu.schema.newBuilder
import jp.co.soramitsu.schema.type

object TypeDefinitionParserImpl : TypeDefinitionParser {

    private class Params(
        val typeResolver: DynamicTypeResolver,
        val typesBuilder: TypePresetBuilder
    )

    override fun parseBaseDefinitions(
        types: Map<String, Any>,
        typePreset: TypePreset,
        dynamicTypeResolver: DynamicTypeResolver
    ): ParseResult {
        val builder = typePreset.newBuilder()
        val parsingParams = Params(dynamicTypeResolver, builder)

        for (name in types.keys) {
            val typeValue = types[name]
            val type = parseType(parsingParams, name, typeValue) ?: continue

            builder.type(type)
        }
        val unknownTypes = builder.entries
            .mapNotNull { (name, typeRef) -> if (!typeRef.isResolved()) name else null }

        return ParseResult(builder, unknownTypes)
    }

    private fun parseType(parsingParams: Params, name: String, typeValue: Any?): Type<*>? {
        return when (typeValue) {
            is String -> {
                return resolveTypeAllWaysOrCreate(parsingParams, name, typeValue).value
            }
            is Map<*, *> -> {
                when {
                    typeValue["Map"] != null || typeValue["Vec"] != null || typeValue["Option"] != null || typeValue["Int"] != null  -> {
                        return resolveTypeAllWaysOrCreate(parsingParams, name, name).value
                    }
                    typeValue["Struct"] != null -> {
                        val components =
                            (typeValue["Struct"] as Map<String, List<Map<String, String>>>)["declarations"]!!
                        val children = parseTypeMapping(parsingParams, components)
                        Struct(name, children)
                    }
                    typeValue["TupleStruct"] != null -> {
                        val components = (typeValue["TupleStruct"] as Map<String, List<String>>)["types"]!!
                        val children = components.map { resolveTypeAllWaysOrCreate(parsingParams, it) }
                        TupleStruct(name, children)
                    }
                    typeValue["Enum"] != null -> {
                        val components = (typeValue["Enum"] as Map<String, List<Map<String, Any>>>)["variants"]!!
                        val variants = components.map {
                            val variantProperty = it["ty"] as String?
                            EnumType.Variant(
                                it["name"]!! as String,
                                (it["discriminant"]!! as Double).toInt(),
                                if (variantProperty != null) {resolveTypeAllWaysOrCreate(parsingParams, variantProperty, variantProperty)} else {null}
                            )
                        }
                        EnumType(name, variants)
                    } typeValue["Array"] != null -> {
                        val len = ((typeValue["Array"] as Map<String, Any>)["len"]!! as Double).toInt()
                        return FixedByteArray(name, len)
                    }
                    else -> {
                        throw RuntimeException("Unexpected type $typeValue")
                    }
                }
            }
            else -> null
        }
    }

    private fun resolveDynamicType(
        parsingParams: Params,
        name: String,
        typeDef: String
    ): Type<*>? {
        return parsingParams.typeResolver.createDynamicType(name, typeDef) {
            resolveTypeAllWaysOrCreate(parsingParams, it)
        }
    }

    private fun resolveTypeAllWaysOrCreate(
        parsingParams: Params,
        typeDef: String,
        name: String = typeDef
    ): TypeReference {
        return parsingParams.typesBuilder[typeDef]
            ?: resolveDynamicType(parsingParams, name, typeDef)?.let(::TypeReference)
            ?: parsingParams.typesBuilder.create(typeDef)
    }

    private fun parseTypeMapping(
        parsingParams: Params,
        typeMapping: List<Map<String, String>>
    ): LinkedHashMap<String, TypeReference> {
        val children = LinkedHashMap<String, TypeReference>()

        for (singleMapping in typeMapping) {
            val fieldName = singleMapping["name"]!!
            val fieldType = singleMapping["ty"]!!
            children[fieldName] = resolveTypeAllWaysOrCreate(parsingParams, fieldType, fieldName)
        }
        return children
    }
}
