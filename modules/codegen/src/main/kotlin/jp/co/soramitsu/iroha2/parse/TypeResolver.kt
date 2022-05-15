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

abstract class WrapperResolver<T : Type>(open val wrapperName: String) : Resolver<T> {
    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): T? {
        if (!name.startsWith("$wrapperName<")) return null
        val innerTypeName = name.removeSurrounding("$wrapperName<", ">")
        val innerType = schemaParser.createAndGetNest(innerTypeName)
        return createWrapper(name, innerType)
    }

    abstract fun createWrapper(name: String, innerType: TypeNest, sorted: Boolean = false): T
}

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

object SetResolver : WrapperResolver<SetType>("BTreeSet") {
    override fun createWrapper(
        name: String,
        innerType: TypeNest,
        sorted: Boolean
    ) = SetType(name, innerType, sorted)
}

object ArrayResolver : Resolver<ArrayType> {

    private val REGEX by lazy { "\\[(\\S+); (\\d+)\\]".toRegex() }

    override fun resolve(name: String, typeValue: Any?, schemaParser: SchemaParser): ArrayType? {
        if (!name.startsWith("[")) return null
        val groups = REGEX.find(name)?.groupValues ?: return null
        return ArrayType(name, schemaParser.createAndGetNest(groups[1]), groups[2].toInt())
    }
}

object OptionResolver : WrapperResolver<OptionType>("Option") {
    override fun createWrapper(
        name: String,
        innerType: TypeNest,
        sorted: Boolean
    ) = OptionType(name, innerType)
}

object CompactResolver : WrapperResolver<CompactType>("Compact") {
    override fun createWrapper(
        name: String,
        innerType: TypeNest,
        sorted: Boolean
    ) = CompactType(name, innerType)
}

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
