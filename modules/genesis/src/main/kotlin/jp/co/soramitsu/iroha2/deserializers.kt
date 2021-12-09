package jp.co.soramitsu.iroha2

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.KeyDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.google.crypto.tink.subtle.Hex
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import kotlin.reflect.full.primaryConstructor
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

private val mapper = ObjectMapper().also { mapper ->
    val module = SimpleModule()
    module.addDeserializer(Instruction::class.java, InstructionDeserializer())
    module.addDeserializer(Expression::class.java, ExpressionDeserializer())
    module.addDeserializer(Value::class.java, ValueDeserializer())
    module.addDeserializer(IdentifiableBox::class.java, IdentifiableBoxDeserializer())
    module.addDeserializer(PublicKey::class.java, PublicKeyDeserializer())
    module.addKeyDeserializer(DefinitionId::class.java, DefinitionIdDeserializer())
    module.addKeyDeserializer(AccountId::class.java, AccountIdDeserializer())
    module.addKeyDeserializer(AssetId::class.java, AssetIdDeserializer())
    mapper.registerModule(module)

    mapper.registerModule(
        KotlinModule.Builder()
            .configure(KotlinFeature.NullToEmptyCollection, true)
            .configure(KotlinFeature.NullToEmptyMap, true)
            .build()
    )

    mapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
}

private inline fun <reified T : ModelEnum> sealedDeserialize(p: JsonParser, mapper: ObjectMapper): T {
    val node = p.readValueAsTree<JsonNode>().fields().next()
    val param = node.key

    val subtype = T::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName == param
    } ?: throw RuntimeException("Class with constructor($param) not found")

    val argTypeName = subtype.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw RuntimeException("Subtype parameter not found by $param")

    var toConvert = node.value
    if (T::class.java.isAssignableFrom(Instruction::class.java)) {
        val childNode = mapper.createObjectNode()
        childNode.set<ObjectNode>("expression", node.value.get("object"))
        val jsonNode: ObjectNode = node.value.deepCopy()
        jsonNode.set<ObjectNode>("object", childNode)

        toConvert = jsonNode
    }

    val arg = mapper.convertValue(toConvert, Class.forName(argTypeName))
    return subtype.primaryConstructor?.call(arg) as T
}

class InstructionDeserializer : JsonDeserializer<Instruction>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Instruction {
        return sealedDeserialize(p, mapper)
    }
}

class ExpressionDeserializer : JsonDeserializer<Expression>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Expression {
        return sealedDeserialize(p, mapper)
    }
}

class ValueDeserializer : JsonDeserializer<Value>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Value {
        return sealedDeserialize(p, mapper)
    }
}

class IdentifiableBoxDeserializer : JsonDeserializer<IdentifiableBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): IdentifiableBox {
        return sealedDeserialize(p, mapper)
    }
}

class PublicKeyDeserializer : JsonDeserializer<PublicKey>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): PublicKey {
        val key = p.readValueAs(String::class.java)
        return PublicKey("ed25519", Hex.decode(key))
    }
}

class DefinitionIdDeserializer : KeyDeserializer() {
    override fun deserializeKey(key: String?, ctxt: DeserializationContext?): DefinitionId? {
        return mapper.readValue(key, DefinitionId::class.java)
    }
}

class AccountIdDeserializer : KeyDeserializer() {
    override fun deserializeKey(key: String?, ctxt: DeserializationContext?): AccountId? {
        return mapper.readValue(key, AccountId::class.java)
    }
}

class AssetIdDeserializer : KeyDeserializer() {
    override fun deserializeKey(key: String?, ctxt: DeserializationContext?): AssetId? {
        return mapper.readValue(key, AssetId::class.java)
    }
}
