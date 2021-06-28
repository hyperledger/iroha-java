package jp.co.soramitsu.schema

interface TypeDefinitionParser {
    fun parseBaseDefinitions(
        types: Map<String, Any>,
        typePreset: TypePreset,
        dynamicTypeResolver: DynamicTypeResolver = DynamicTypeResolver.defaultCompoundResolver()
    ): ParseResult
}

class ParseResult(
    val typePreset: TypePreset,
    val unknownTypes: List<String>
)
