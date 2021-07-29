package jp.co.soramitsu.iroha2.parse

import jp.co.soramitsu.iroha2.type.*
import kotlin.text.toInt

//todo replace to extractRegex
class TypeResolver(private val schemaParser: SchemaParser) {

    private val resolvers = listOf<Resolver<*>>(
        BooleanResolver,
        MapResolver,
        OptionResolver,
        VectorResolver,
        ArrayResolver,
        EnumResolver,
        TupleStructResolver,
        StructResolver,
        StringResolver,
        CompactResolver,
        UIntResolver,
        SetResolver
    )

    fun resolve(name: String, typeValue: Any?): Type? {
        val candidates = resolvers
            .asSequence()
            .mapNotNull { it.resolve(name, typeValue, schemaParser) }
            .toSet()
        return candidates.firstOrNull()
    }
}

interface Resolver<T : Type> {
    fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): T?
}

object BooleanResolver : Resolver<BooleanType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): BooleanType? {
        return if (name == "bool") {
            BooleanType
        } else null
    }
}

object MapResolver : Resolver<MapType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): MapType? {
        if (!name.startsWith("BTreeMap<")) return null
        val wildcards = name.removePrefix("BTreeMap")
            .removeSurrounding("<", ">")
            .split(',')
            .map { it.trim() }
        if (wildcards.size != 2) return null
        return MapType(
            name,
            schemaParser.createAndGetNest(wildcards[0]),
            schemaParser.createAndGetNest(wildcards[1])
        )
    }
}

abstract class WrapperResolver<T : Type>( val wrapperName: String) : Resolver<T> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): T? {
        if (!name.startsWith("$wrapperName<")) return null
        val innerTypeName = name.removeSurrounding("$wrapperName<", ">")
        val innerType = schemaParser.createAndGetNest(innerTypeName)
        return createWrapper(name, innerType)
    }

    abstract fun createWrapper(name: String, innerType: TypeNest): T

}

object OptionResolver : WrapperResolver<OptionType>("Option") {
    override fun createWrapper(name: String, innerType: TypeNest) = OptionType(name, innerType)
}

object VectorResolver : WrapperResolver<VecType>("Vec") {
    override fun createWrapper(name: String, innerType: TypeNest) = VecType(name, innerType)

    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): VecType? {
        if (!name.startsWith(wrapperName) && !name.startsWith("alloc::vec::Vec")) return null
        val innerType = extractGeneric(name, schemaParser)
        return createWrapper(name, innerType.first())
    }
}

object SetResolver : WrapperResolver<SetType>("BTreeSet") {
    override fun createWrapper(name: String, innerType: TypeNest) = SetType(name, innerType)
}

object ArrayResolver : Resolver<ArrayType> {

    private val REGEX by lazy { "\\[(\\S+); (\\d+)\\]".toRegex() }

    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): ArrayType? {
        if (!name.startsWith("[")) return null
        val groups = REGEX.find(name)?.groupValues ?: return null
        return ArrayType(name, schemaParser.createAndGetNest(groups[1]), groups[2].toInt())
    }

}

object EnumResolver : Resolver<EnumType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): EnumType? {
        return if (typeValue is Map<*, *> && typeValue["Enum"] != null) {
            val components = (typeValue["Enum"] as Map<String, List<Map<String, Any>>>)["variants"]
                ?: return null
            val variants = components.map {
                val variantProperty = it["ty"] as String?
                EnumType.Variant(
                    it["name"]!! as String,
                    (it["discriminant"]!! as Double).toInt(),
                    variantProperty?.let(schemaParser::createAndGetNest)
                )
            }
            val generics = extractGeneric(name, schemaParser)
            EnumType(name, generics, variants)
        } else null
    }
}

object TupleStructResolver : Resolver<TupleStructType> {
    override fun resolve(
        name: String,
        typeValue: Any?,
        schemaParser: SchemaParser
    ): TupleStructType? {
        return if (typeValue is Map<*, *> && typeValue["TupleStruct"] != null) {
            val components = (typeValue["TupleStruct"] as Map<String, List<String>>)["types"]!!
            val children = components.map(schemaParser::createAndGetNest)
            val generics = extractGeneric(name, schemaParser)
            TupleStructType(name, generics, children)
        } else null
    }
}

object StructResolver : Resolver<StructType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): StructType? {
        return if (typeValue is Map<*, *> && typeValue["Struct"] != null) {
            val components =
                (typeValue["Struct"] as Map<String, List<Map<String, String>>>)["declarations"]!!
            val children = LinkedHashMap<String, TypeNest>()
            for (singleMapping in components) {
                val fieldName = singleMapping["name"]!!
                val fieldType = singleMapping["ty"]!!
                children[fieldName] = schemaParser.createAndGetNest(fieldType)
            }
            val generics = extractGeneric(name, schemaParser)
            StructType(name, generics, children)
        } else null
    }
}

object StringResolver : Resolver<StringType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): StringType? {
        return if (name.endsWith("String")) {
            StringType
        } else null
    }
}

object CompactResolver : WrapperResolver<CompactType>("iroha_schema::Compact") {
    override fun createWrapper(name: String, innerType: TypeNest) = CompactType(name, innerType)
}

object UIntResolver : Resolver<UIntType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): UIntType? {
        return when (name) {
            "u8" -> U8Type
            "u16" -> U16Type
            "u32" -> U32Type
            "u64" -> U64Type
            "u128" -> U128Type
            "u256" -> U256Type
            else -> null
        }
    }
}

data class TypeNest(val name: String, var value: Type?) {

    private var resolutionInProgress: Boolean = false

    fun requireValue() = value ?: throw IllegalArgumentException("Type is not resolved: $name")

    fun notResolvedTypes() : Set<String> {
        if (resolutionInProgress) {
            return setOf()
        }
        resolutionInProgress = true
        val resolutionResult = value?.notResolvedTypes() ?: setOf()
        resolutionInProgress = false
        return resolutionResult
    }

    override fun toString(): String {
        return "TypeNest(name='$name', value=$value)"
    }
}
