package jp.co.soramitsu.iroha2.testcontainers

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import java.lang.reflect.Type
import kotlin.reflect.full.memberProperties
import kotlin.reflect.javaType

object GenesisJsonSerializer {

    private val gson = lazy {
        GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Instruction::class.java, EnumerationSerializer)
            .registerTypeAdapter(Expression::class.java, EnumerationSerializer)
            .registerTypeAdapter(IdentifiableBox::class.java, EnumerationSerializer)
            .registerTypeAdapter(Value::class.java, EnumerationSerializer)
            .create()
    }

    fun asJson(genesis: Genesis) = gson.value.toJson(genesis.genesisBlock)
}


object EnumerationSerializer : JsonSerializer<Any> {
    @OptIn(ExperimentalStdlibApi::class)
    override fun serialize(src: Any, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()
        val memberProperties = src::class.memberProperties
        if (memberProperties.size != 1) {
            throw RuntimeException("foo")
        }
        val inner = memberProperties.first()
        jsonObject.add(src::class.simpleName, context.serialize(inner.call(src), inner.returnType.javaType))
        return jsonObject
    }
}
