package jp.co.soramitsu.iroha2.parse

import jp.co.soramitsu.iroha2.type.ArrayType
import jp.co.soramitsu.iroha2.type.BooleanType
import jp.co.soramitsu.iroha2.type.CompactType
import jp.co.soramitsu.iroha2.type.EnumType
import jp.co.soramitsu.iroha2.type.FixedPointType
import jp.co.soramitsu.iroha2.type.I128Type
import jp.co.soramitsu.iroha2.type.I16Type
import jp.co.soramitsu.iroha2.type.I256Type
import jp.co.soramitsu.iroha2.type.I32Type
import jp.co.soramitsu.iroha2.type.I64Type
import jp.co.soramitsu.iroha2.type.I8Type
import jp.co.soramitsu.iroha2.type.IntType
import jp.co.soramitsu.iroha2.type.IterableType
import jp.co.soramitsu.iroha2.type.MapType
import jp.co.soramitsu.iroha2.type.OptionType
import jp.co.soramitsu.iroha2.type.SetType
import jp.co.soramitsu.iroha2.type.StringType
import jp.co.soramitsu.iroha2.type.StructType
import jp.co.soramitsu.iroha2.type.TupleStructType
import jp.co.soramitsu.iroha2.type.Type
import jp.co.soramitsu.iroha2.type.U128Type
import jp.co.soramitsu.iroha2.type.U16Type
import jp.co.soramitsu.iroha2.type.U256Type
import jp.co.soramitsu.iroha2.type.U32Type
import jp.co.soramitsu.iroha2.type.U64Type
import jp.co.soramitsu.iroha2.type.U8Type
import jp.co.soramitsu.iroha2.type.UIntType
import jp.co.soramitsu.iroha2.type.VecType

/**
 * TypeResolver is used to resolve Iroha2 types using Iroha2 schema parser.
 */
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
        IntResolver,
        SetResolver,
        FixedPointResolver,
    )

    /**
     * Resolve the type based on a given [name].
     *
     * @param name The name to resolve
     * @param typeValue The type to try and resolve the [name] to
     */
    fun resolve(name: String, typeValue: Any?): Type? {
        val candidates = resolvers
            .asSequence()
            .mapNotNull { it.resolve(name, typeValue, schemaParser) }
            .toSet()
        return candidates.firstOrNull()
    }
}

/**
 * Basic resolver for all kinds of types.
 *
 * @param name The name to resolve
 * @param typeValue The type to try and resolve the [name] to
 * @param schemaParser The schema parser to use
 */
interface Resolver<T : Type> {
    fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): T?
}

/**
 * Resolver for [BooleanType]
 */
object BooleanResolver : Resolver<BooleanType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): BooleanType? {
        return if (name == "bool") {
            BooleanType
        } else null
    }
}

/**
 * Resolver for [MapType]
 */
object MapResolver : Resolver<MapType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): MapType? {
        if (!name.startsWith("Map<")) return null
        val wildcards = name.removePrefix("Map")
            .removeSurrounding("<", ">")
            .split(',')
            .map { it.trim() }
        if (wildcards.size != 2) return null

        return MapType(
            name,
            schemaParser.createAndGetNest(wildcards[0]),
            schemaParser.createAndGetNest(wildcards[1]),
            ((typeValue as? Map<*, *>)?.get("Map") as? Map<*, *>)
                ?.get("sorted_by_key") as? Boolean
                ?: false
        )
    }
}

/**
 * Basic resolver for wrapped types
 */
abstract class WrapperResolver<T : Type>(open val wrapperName: String) : Resolver<T> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): T? {
        if (!name.startsWith("$wrapperName<")) return null
        val innerTypeName = name.removeSurrounding("$wrapperName<", ">")
        val innerType = schemaParser.createAndGetNest(innerTypeName)
        return createWrapper(name, innerType)
    }

    /**
     * Create a wrapper type for [innerType]
     */
    abstract fun createWrapper(name: String, innerType: TypeNest, sorted: Boolean = false): T
}

/**
 * Basic resolver for iterable types
 */
abstract class SortedWrapperResolver<T : IterableType>(
    override val wrapperName: String
) : WrapperResolver<T>(wrapperName) {

    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): T? {
        return super.resolve(name, typeValue, schemaParser)?.also {
            it.sorted = getSortedProperty(typeValue)
        }
    }

    fun getSortedProperty(typeValue: Any?): Boolean {
        return (typeValue as? Map<*, *>)
            ?.let { typeValue[wrapperName] as? Map<*, *> }
            ?.get("sorted") as? Boolean
            ?: false
    }
}

/**
 * Resolver for [VecType]
 */
object VectorResolver : SortedWrapperResolver<VecType>("Vec") {
    override fun createWrapper(
        name: String,
        innerType: TypeNest,
        sorted: Boolean
    ) = VecType(name, innerType, sorted)

    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): VecType? {
        return when (
            name.startsWith(wrapperName) ||
                (typeValue as? Map<*, *>)?.get(wrapperName) != null
        ) {
            true -> createWrapper(
                name,
                extractGeneric(name, schemaParser).first(),
                getSortedProperty(typeValue)
            )
            false -> null
        }
    }
}

/**
 * Resolver for [SetType]
 */
object SetResolver : WrapperResolver<SetType>("BTreeSet") {
    override fun createWrapper(
        name: String,
        innerType: TypeNest,
        sorted: Boolean
    ) = SetType(name, innerType, sorted)
}

/**
 * Resolver for [ArrayType]
 */
object ArrayResolver : Resolver<ArrayType> {

    private val REGEX by lazy { "\\[(\\S+); (\\d+)\\]".toRegex() }

    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): ArrayType? {
        if (!name.startsWith("[")) return null
        val groups = REGEX.find(name)?.groupValues ?: return null
        return ArrayType(name, schemaParser.createAndGetNest(groups[1]), groups[2].toInt())
    }
}

/**
 * Resolver for [OptionType]
 */
object OptionResolver : WrapperResolver<OptionType>("Option") {
    override fun createWrapper(
        name: String,
        innerType: TypeNest,
        sorted: Boolean
    ) = OptionType(name, innerType)
}

/**
 * Resolver for [CompactType]
 */
object CompactResolver : WrapperResolver<CompactType>("Compact") {
    override fun createWrapper(
        name: String,
        innerType: TypeNest,
        sorted: Boolean
    ) = CompactType(name, innerType)
}

/**
 * Resolver for [EnumType]
 */
object EnumResolver : Resolver<EnumType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): EnumType? {
        return if (typeValue is Map<*, *> && typeValue["Enum"] != null) {
            val components = (typeValue["Enum"] as Map<String, List<Map<String, Any>>>)["variants"]
                ?: return null
            val generics = extractGeneric(name, schemaParser)
            val variants = components.map {
                val variantProperty = it["ty"] as String?
                EnumType.Variant(
                    it["name"]!! as String,
                    it["discriminant"]!! as Int,
                    variantProperty?.let(schemaParser::createAndGetNest)
                )
            }
            EnumType(name, generics, variants)
        } else null
    }
}

/**
 * Resolver for [TupleStructType]
 */
object TupleStructResolver : Resolver<TupleStructType> {
    override fun resolve(
        name: String,
        typeValue: Any?,
        schemaParser: SchemaParser
    ): TupleStructType? {
        return if (typeValue is Map<*, *> && typeValue["Tuple"] != null) {
            val components = (typeValue["Tuple"] as Map<String, List<String>>)["types"]!!
            val children = components.map(schemaParser::createAndGetNest)
            val generics = extractGeneric(name, schemaParser)
            TupleStructType(name, generics, children)
        } else null
    }
}

/**
 * Resolver for [StructType]
 */
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

/**
 * Resolver for [StringType]
 */
object StringResolver : Resolver<StringType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): StringType? {
        return if (name.endsWith("String")) {
            StringType
        } else null
    }
}

/**
 * Resolver for [UIntType]
 */
object UIntResolver : Resolver<UIntType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): UIntType? {
        return when (name) {
            "AtomicU32Wrapper" -> U32Type
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

/**
 * Resolver for [IntType]
 */
object IntResolver : Resolver<IntType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): IntType? {
        return when (name) {
            "i8" -> I8Type
            "i16" -> I16Type
            "i32" -> I32Type
            "i64" -> I64Type
            "i128" -> I128Type
            "i256" -> I256Type
            else -> null
        }
    }
}

/**
 * Resolver for [FixedPointType]
 */
object FixedPointResolver : Resolver<FixedPointType> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): FixedPointType? {
        return if (name.startsWith("FixedPoint<") && typeValue is Map<*, *>) {
            val members = (typeValue["FixedPoint"] as? Map<String, Any>)!!
            val base = schemaParser.createAndGetNest(members["base"]!! as String)
            val decimalPlaces = members["decimal_places"]!! as Int
            FixedPointType(name, base, decimalPlaces)
        } else null
    }
}

/**
 * `TypeNest` contains [the name of the type][name] and [the type it resolves to][value]
 */
data class TypeNest(val name: String, var value: Type?) {

    private var resolutionInProgress: Boolean = false

    fun requireValue() = value ?: throw IllegalArgumentException("Type is not resolved: $name")

    fun notResolvedTypes(): Set<String> {
        if (resolutionInProgress) {
            return setOf()
        }
        resolutionInProgress = true
        val resolutionResult = value?.notResolvedTypes() ?: setOf()
        resolutionInProgress = false
        return resolutionResult
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TypeNest) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
