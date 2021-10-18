package jp.co.soramitsu.iroha2.testcontainers.genesis

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import io.ipfs.multihash.Multihash
import jp.co.soramitsu.iroha2.DigestFunction.Ed25519
import jp.co.soramitsu.iroha2.GsonSerializable
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.toHex
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type
import kotlin.reflect.full.memberProperties
import kotlin.reflect.javaType

object GenesisJsonSerializer {

    private val gson = lazy {
        GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeHierarchyAdapter(GsonSerializable::class.java, GenericSerializer)
            .registerTypeAdapter(AssetValueType::class.java, PrimitiveSerializer)
            .registerTypeAdapter(PublicKey::class.java, PublicKeySerializer)
            .registerTypeAdapter(UInt::class.java, UIntSerializer)
            .create()
    }

    fun asJson(genesis: Genesis): String {
        return gson.value.toJson(genesis.genesisBlock)
    }
}

object GenericSerializer : JsonSerializer<Any> {
    override fun serialize(src: Any, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        return when (src) {
            is EvaluatesTo<*> -> serialize(src.expression, null, context)
            is Metadata -> JsonObject().also { serialized ->
                src.map.forEach { (key, value) ->
                    serialized.add(key, serialize(value, null, context))
                }
            }
            else -> default(src, context)
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun default(src: Any, context: JsonSerializationContext): JsonElement {
        val serialized = JsonObject()

        val memberProperties = src::class.memberProperties
        if (memberProperties.size != 1) {
            throw RuntimeException("Expected enum which accept exactly 1 member as tuple")
        }
        val innerProp = memberProperties.first()
        val innerPropVal = when (val actual = innerProp.call(src)) {
            is UInt -> actual.toLong() // cannot cast UInt to Number
            else -> actual
        }
        serialized.add(src::class.simpleName, context.serialize(innerPropVal, innerProp.returnType.javaType))
        return serialized
    }
}

object MetadataSerializer : JsonSerializer<Metadata> {
    override fun serialize(src: Metadata, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()
        src.map.forEach { (key, value) ->
            jsonObject.add(key, GenericSerializer.serialize(value, null, context))
        }
        return jsonObject
    }
}

object PublicKeySerializer : JsonSerializer<PublicKey> {
    override fun serialize(src: PublicKey, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val res = ByteArrayOutputStream()
        Multihash.putUvarint(res, Ed25519.index.toLong())
        Multihash.putUvarint(res, src.payload.size.toLong())
        res.write(src.payload)
        return context.serialize(res.toByteArray().toHex())
    }
}

object PrimitiveSerializer : JsonSerializer<AssetValueType> {
    override fun serialize(src: AssetValueType, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src::class.java.simpleName)
    }
}

object UIntSerializer : JsonSerializer<UInt> {
    override fun serialize(src: UInt, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return context.serialize(src.toLong())
    }
}
