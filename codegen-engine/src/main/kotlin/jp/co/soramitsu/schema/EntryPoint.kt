package jp.co.soramitsu.schema

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import jp.co.soramitsu.schema.codegen.CodeGenerator
import jp.co.soramitsu.schema.definitions.dynamic.DynamicTypeExtension
import jp.co.soramitsu.schema.definitions.dynamic.TypeProvider
import jp.co.soramitsu.schema.definitions.dynamic.extentsions.OptionExtension
import jp.co.soramitsu.schema.definitions.dynamic.extentsions.TupleExtension
import jp.co.soramitsu.schema.definitions.dynamic.extentsions.VectorExtension
import jp.co.soramitsu.schema.definitions.dynamic.extentsions.WrapperExtension
import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.TypeReference
import jp.co.soramitsu.schema.definitions.types.composite.SetType
import jp.co.soramitsu.schema.definitions.types.composite.Vec
import jp.co.soramitsu.schema.definitions.types.composite.WrapperType
import jp.co.soramitsu.schema.definitions.types.primitives.*
import jp.co.soramitsu.schema.parser.TypeDefinitionParserImpl.parseBaseDefinitions
import java.io.InputStreamReader
import java.lang.Thread.currentThread

fun main() {
    val gson = Gson()
    val res = currentThread().contextClassLoader.getResourceAsStream("schema.json")!!
    val reader = JsonReader(InputStreamReader(res))

    val types = gson.fromJson<Map<String, Any>>(reader, Map::class.java)

    val typeResolver = DynamicTypeResolver(
        MapExtension,
        VectorExtension,
        SetExtension,
        OptionExtension,
        StringExtension,
        UIntExtension,
        CompactNumberExtension,
        BooleanExtension,
        TupleExtension,
//        EvaluatesToExtension,
    )
    val result = parseBaseDefinitions(types, createTypePresetBuilder(), typeResolver)

    if (result.unknownTypes.isNotEmpty()) {
        throw RuntimeException("${result.unknownTypes}")
    }

    CodeGenerator.generate(result.typePreset)
}

object MapExtension : DynamicTypeExtension {

    override fun createType(name: String, typeDef: String, typeProvider: TypeProvider): Type<*>? {
        if (!typeDef.startsWith("BTreeMap")) return null
        val withoutBrackets = typeDef.removePrefix("BTreeMap").removeSurrounding("<", ">")
        if (withoutBrackets.split(",").size != 2) return null
        val tuple = "($withoutBrackets)"
        val typeRef = typeProvider(tuple)
        return Vec("Vec<$tuple>", typeRef)
    }
}

object StringExtension : DynamicTypeExtension {
    override fun createType(name: String, typeDef: String, typeProvider: TypeProvider): Type<*>? {
        if (!typeDef.startsWith("String")) return null
        return StringType
    }
}

object BooleanExtension : DynamicTypeExtension {
    override fun createType(name: String, typeDef: String, typeProvider: TypeProvider): Type<*>? {
        return if (typeDef == "bool") {
            BooleanType
        } else null
    }
}

object  SetExtension : DynamicTypeExtension {
    override fun createType(name: String, typeDef: String, typeProvider: TypeProvider): Type<*>? {
        if (!typeDef.startsWith("BTreeSet")) return null
        val withoutBrackets = typeDef.removePrefix("BTreeSet").removeSurrounding("<", ">")
        val typeRef = typeProvider(withoutBrackets)
        return SetType(name, typeRef, LinkedHashMap())
    }
}

object UIntExtension : DynamicTypeExtension {
    override fun createType(name: String, typeDef: String, typeProvider: TypeProvider): Type<*>? {
        return when (typeDef) {
            "u8" -> u8
            "u16" -> u16
            "u32" -> u32
            "u64" -> u64
            "u128" -> u128
            "u256" -> u256
            else -> null
        }
    }
}

object CompactNumberExtension : DynamicTypeExtension {
    override fun createType(name: String, typeDef: String, typeProvider: TypeProvider): Type<*>? {
        if (!typeDef.startsWith("iroha_schema::Compact<")) {
            return null
        }
        val type = typeDef.substringAfter("iroha_schema::Compact").removeSurrounding("<", ">")
        return Compact(type)
    }
}

object StringType : Primitive<StringType>("String") {
    override fun decode(scaleCodecReader: ScaleCodecReader): StringType {
        TODO("Not yet implemented")
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, value: StringType) {
        TODO("Not yet implemented")
    }

    override fun isValidInstance(instance: Any?): Boolean = instance is StringType

}


class MapType(name: String) : Type<MapType>(name) {
    override val isFullyResolved: Boolean
        get() = TODO("Not yet implemented")

    override fun decode(scaleCodecReader: ScaleCodecReader): MapType {
        TODO("Not yet implemented")
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, value: MapType) {
        TODO("Not yet implemented")
    }

    override fun isValidInstance(instance: Any?): Boolean {
        TODO("Not yet implemented")
    }

}
