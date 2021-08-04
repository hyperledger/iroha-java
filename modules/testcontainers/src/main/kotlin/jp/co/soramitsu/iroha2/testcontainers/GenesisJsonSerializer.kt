package jp.co.soramitsu.iroha2.testcontainers

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import io.ipfs.multihash.Multihash
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.utils.DigestFunction.Ed25519
import org.bouncycastle.util.encoders.Hex
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type
import kotlin.reflect.full.memberProperties
import kotlin.reflect.javaType

object GenesisJsonSerializer {

    private val gson = lazy {
        GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Instruction::class.java, EnumerationSerializer)
            .registerTypeAdapter(Expression::class.java, EnumerationSerializer)
            .registerTypeAdapter(IdentifiableBox::class.java, EnumerationSerializer)
            .registerTypeAdapter(Value::class.java, EnumerationSerializer)
            .registerTypeAdapter(AssetValueType::class.java, AssetValueTypeSerializer)
            .registerTypeAdapter(EvaluatesTo::class.java, EvaluatesToSerializer)
            .registerTypeAdapter(Metadata::class.java, MetadataSerializer)
            .registerTypeAdapter(PublicKey::class.java, PublicKeySerializer)
            .create()
    }

    fun asJson(genesis: Genesis) = gson.value.toJson(genesis.genesisBlock)
}

object EnumerationSerializer : JsonSerializer<Any> {
    @OptIn(ExperimentalStdlibApi::class)
    override fun serialize(src: Any, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()
        val memberProperties = src::class.memberProperties
        if (memberProperties.size != 1) {
            throw RuntimeException("Expected enum which accept exactly 1 member as tuple")
        }
        val inner = memberProperties.first()
        jsonObject.add(src::class.simpleName, context.serialize(inner.call(src), inner.returnType.javaType))
        return jsonObject
    }
}

object EvaluatesToSerializer : JsonSerializer<EvaluatesTo<*>> {
    override fun serialize(src: EvaluatesTo<*>, typeOfSrc: Type, context: JsonSerializationContext): JsonElement =
        EnumerationSerializer.serialize(src.expression, null, context)
}

object MetadataSerializer : JsonSerializer<Metadata> {
    override fun serialize(src: Metadata, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        return context.serialize(src.map)
    }
}

object PublicKeySerializer : JsonSerializer<PublicKey> {
    override fun serialize(src: PublicKey, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val res = ByteArrayOutputStream()
        Multihash.putUvarint(res, Ed25519.index.toLong())
        Multihash.putUvarint(res, src.payload.size.toLong())
        res.write(src.payload)
        //todo replace to proper hex encoder
        return context.serialize(String(Hex.encode(res.toByteArray())))
    }
}

object AssetValueTypeSerializer : JsonSerializer<AssetValueType> {
    override fun serialize(src: AssetValueType, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src::class.java.simpleName)
    }
}
