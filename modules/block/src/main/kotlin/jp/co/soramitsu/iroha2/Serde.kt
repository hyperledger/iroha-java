package jp.co.soramitsu.iroha2

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.KeyDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.ipfs.multihash.Multihash
import jp.co.soramitsu.iroha2.DigestFunction.Ed25519
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.Algorithm
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.BurnBox
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.Expression
import jp.co.soramitsu.iroha2.generated.GrantBox
import jp.co.soramitsu.iroha2.generated.IdBox
import jp.co.soramitsu.iroha2.generated.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.MintBox
import jp.co.soramitsu.iroha2.generated.Mintable
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.NumericValue
import jp.co.soramitsu.iroha2.generated.PermissionTokenId
import jp.co.soramitsu.iroha2.generated.PublicKey
import jp.co.soramitsu.iroha2.generated.RegisterBox
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.SetKeyValueBox
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.ValidatorMode
import jp.co.soramitsu.iroha2.generated.ValidatorPath
import jp.co.soramitsu.iroha2.generated.Value
import java.io.ByteArrayOutputStream
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * This JSON mapper is configured to serialise and deserialise `Genesis block` in a format compatible with Iroha 2 peer
 */
val JSON_SERDE by lazy {
    ObjectMapper().also { mapper ->
        val module = SimpleModule()

        // deserializers
        module.addDeserializer(InstructionBox::class.java, InstructionDeserializer)
        module.addDeserializer(Expression::class.java, ExpressionDeserializer)
        module.addDeserializer(Value::class.java, ValueDeserializer)
        module.addDeserializer(IdentifiableBox::class.java, IdentifiableBoxDeserializer)
        module.addDeserializer(PublicKey::class.java, PublicKeyDeserializer)
        module.addDeserializer(IdBox::class.java, IdBoxDeserializer)
        module.addDeserializer(AssetValueType::class.java, AssetValueTypeDeserializer)
        module.addDeserializer(Name::class.java, NameDeserializer)
        module.addDeserializer(Mintable::class.java, MintableDeserializer)
        module.addDeserializer(DomainId::class.java, DomainIdDeserializer)
        module.addDeserializer(AccountId::class.java, AccountIdDeserializer)
        module.addDeserializer(AssetDefinitionId::class.java, AssetDefinitionIdDeserializer)
        module.addDeserializer(AssetId::class.java, AssetIdDeserializer)
        module.addKeyDeserializer(AssetDefinitionId::class.java, AssetDefinitionIdKeyDeserializer)
        module.addKeyDeserializer(AccountId::class.java, AccountIdKeyDeserializer)
        module.addKeyDeserializer(AssetId::class.java, AssetIdKeyDeserializer)
        module.addKeyDeserializer(DomainId::class.java, DomainIdKeyDeserializer)

        // serializers
        module.addKeySerializer(Name::class.java, NameAsKeySerializer)
        module.addSerializer(DomainId::class.java, DomainIdSerializer)
        module.addSerializer(PermissionTokenId::class.java, TokenIdSerializer)
        module.addSerializer(AssetDefinitionId::class.java, AssetDefinitionIdSerializer)
        module.addSerializer(AccountId::class.java, AccountIdSerializer)
        module.addSerializer(AssetId::class.java, AssetIdSerializer)
        module.addSerializer(RoleId::class.java, RoleIdSerializer)
        module.addSerializer(TriggerId::class.java, TriggerIdSerializer)
        module.addSerializer(Name::class.java, NameSerializer)
        module.addSerializer(UInt::class.java, UIntSerializer)
        module.addSerializer(PublicKey::class.java, PublicKeySerializer)
        module.addSerializer(ModelEnum::class.java, EnumerationSerializer)
        module.addSerializer(EvaluatesTo::class.java, EvaluatesToSerializer)
        module.addSerializer(Metadata::class.java, MetadataSerializer)
        module.addSerializer(IdentifiableBox.NewRole::class.java, IdentifiableBoxNewRoleSerializer)
        module.addSerializer(ValidatorMode::class.java, ValidatorModeSerializer)

        mapper.registerModule(module)
        mapper.registerModule(
            KotlinModule.Builder()
                .configure(KotlinFeature.NullToEmptyCollection, true)
                .configure(KotlinFeature.NullToEmptyMap, true)
                .build()
        )

        mapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
    }
}

private inline fun <reified T : ModelEnum> sealedDeserialize(p: JsonParser, mapper: ObjectMapper): T {
    val node = p.readValueAsTree<JsonNode>().fields().next()
    val param = node.key

    val subtype = T::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName == param
    } ?: throw DeserializationException("Class with constructor($param) not found")

    val argTypeName = subtype.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by $param")

    val toConvert: JsonNode
    if (T::class.java.isAssignableFrom(InstructionBox::class.java)) {
        var jsonNode: ObjectNode = node.value.deepCopy()
        jsonNode.fields().forEach { field ->
            val key = field.key
            val child = mapper.createObjectNode()
            child.set<ObjectNode>("expression", node.value.get(key))
            val value: ObjectNode = jsonNode.deepCopy()
            value.set<ObjectNode>(key, child)

            jsonNode = value
        }

        toConvert = jsonNode
    } else {
        toConvert = node.value as JsonNode
    }

    val arg = mapper.convertValue(toConvert, argTypeName.asClass())
    return subtype.primaryConstructor?.call(arg) as T
}

/**
 * Deserializer for [Iroha Special Instructions][InstructionBox]
 */
object InstructionDeserializer : JsonDeserializer<InstructionBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): InstructionBox {
        return sealedDeserialize(p, JSON_SERDE)
    }
}

/**
 * Deserializer for [Expression]
 */
object ExpressionDeserializer : JsonDeserializer<Expression>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Expression {
        return sealedDeserialize(p, JSON_SERDE)
    }
}

/**
 * Deserializer for [Value]
 */
object ValueDeserializer : JsonDeserializer<Value>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Value {
        return sealedDeserialize(p, JSON_SERDE)
    }
}

/**
 * Deserializer for [IdentifiableBox]
 */
object IdentifiableBoxDeserializer : JsonDeserializer<IdentifiableBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): IdentifiableBox {
        return sealedDeserialize(p, JSON_SERDE)
    }
}

/**
 * Deserializer for [IdBox]
 */
object IdBoxDeserializer : JsonDeserializer<IdBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): IdBox {
        return sealedDeserialize(p, JSON_SERDE)
    }
}

/**
 * Deserializer for [AssetValueType]
 */
object AssetValueTypeDeserializer : JsonDeserializer<AssetValueType>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): AssetValueType {
        val text = p.readValueAs(String::class.java)
        return AssetValueType::class.nestedClasses
            .findLast { it.simpleName == text }
            ?.createInstance() as AssetValueType?
            ?: throw DeserializationException("AssetValueType $text not found")
    }
}

/**
 * Deserializer for [PublicKey]
 */
object PublicKeyDeserializer : JsonDeserializer<PublicKey>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): PublicKey {
        val key = p.readValueAs(String::class.java)
        return PublicKey(Algorithm.Ed25519(), key.fromHex())
    }
}

/**
 * Deserializer for [Name]
 */
object NameDeserializer : JsonDeserializer<Name>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Name {
        val key = p.readValueAs(String::class.java)
        return Name(key)
    }
}

/**
 * Deserializer for [Mintable]
 */
object MintableDeserializer : JsonDeserializer<Mintable>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Mintable {
        return when (val value = p.readValueAs(String::class.java)) {
            Mintable.Once::class.simpleName -> Mintable.Once()
            Mintable.Not::class.simpleName -> Mintable.Not()
            Mintable.Infinitely::class.simpleName -> Mintable.Infinitely()
            else -> throw DeserializationException("Unknown Mintable type: $value")
        }
    }
}

/**
 * Deserializer for [asset ID][AssetId]
 */
object AssetIdDeserializer : JsonDeserializer<AssetId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): AssetId {
        return p.readValueAs(String::class.java).asAssetId()
    }
}

/**
 * Deserializer for [asset definition ID][AssetDefinitionId]
 */
object AssetDefinitionIdDeserializer : JsonDeserializer<AssetDefinitionId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): AssetDefinitionId {
        return p.readValueAs(String::class.java).asAssetDefinitionId()
    }
}

/**
 * Deserializer for [account ID][AccountId]
 */
object AccountIdDeserializer : JsonDeserializer<AccountId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): AccountId {
        return p.readValueAs(String::class.java).asAccountId()
    }
}

/**
 * Deserializer for [domain ID][DomainId]
 */
object DomainIdDeserializer : JsonDeserializer<DomainId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): DomainId {
        return p.readValueAs(String::class.java).asDomainId()
    }
}

/**
 * Deserializer for [asset definition ID][AssetDefinitionId]
 */
object AssetDefinitionIdKeyDeserializer : KeyDeserializer() {
    override fun deserializeKey(key: String?, ctxt: DeserializationContext?): AssetDefinitionId? {
        return JSON_SERDE.readValue(key, AssetDefinitionId::class.java)
    }
}

/**
 * Deserializer for [account ID][AccountId]
 */
object AccountIdKeyDeserializer : KeyDeserializer() {
    override fun deserializeKey(key: String?, ctxt: DeserializationContext?): AccountId? {
        return JSON_SERDE.readValue(key, AccountId::class.java)
    }
}

/**
 * Deserializer for [asset ID][AssetId]
 */
object AssetIdKeyDeserializer : KeyDeserializer() {
    override fun deserializeKey(key: String, ctxt: DeserializationContext?): AssetId? {
        return JSON_SERDE.readValue(key, AssetId::class.java)
    }
}

/**
 * Deserializer for [domain ID][DomainId]
 */
object DomainIdKeyDeserializer : KeyDeserializer() {
    override fun deserializeKey(key: String, ctxt: DeserializationContext?): DomainId? {
        return JSON_SERDE.readValue(key, DomainId::class.java)
    }
}

// ==================================================

/**
 * Serializer for [AssetDefinitionId]
 */
object AssetDefinitionIdSerializer : JsonSerializer<AssetDefinitionId>() {
    override fun serialize(value: AssetDefinitionId, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.asString())
    }
}

/**
 * Serializer for [AssetId]
 */
object AssetIdSerializer : JsonSerializer<AssetId>() {
    override fun serialize(value: AssetId, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.asString())
    }
}

/**
 * Serializer for [AccountId]
 */
object AccountIdSerializer : JsonSerializer<AccountId>() {
    override fun serialize(value: AccountId, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.asString())
    }
}

/**
 * Serializer for [DomainId]
 */
object DomainIdSerializer : JsonSerializer<DomainId>() {
    override fun serialize(value: DomainId, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.asString())
    }
}

/**
 * Serializer for [PermissionTokenId]
 */
object TokenIdSerializer : JsonSerializer<PermissionTokenId>() {
    override fun serialize(value: PermissionTokenId, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.asString())
    }
}

/**
 * Serializer for [RoleId]
 */
object RoleIdSerializer : JsonSerializer<RoleId>() {
    override fun serialize(value: RoleId, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.asString())
    }
}

/**
 * Serializer for [TriggerId]
 */
object TriggerIdSerializer : JsonSerializer<TriggerId>() {
    override fun serialize(value: TriggerId, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.asString())
    }
}

/**
 * Serializer for [Name] as key
 */
object NameAsKeySerializer : JsonSerializer<Name>() {
    override fun serialize(value: Name, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeFieldName(value.string)
    }
}

/**
 * Serializer for [Name]
 */
object NameSerializer : JsonSerializer<Name>() {
    override fun serialize(value: Name, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.string)
    }
}

/**
 * Serializer for [Name]
 */
object ValidatorModeSerializer : JsonSerializer<ValidatorMode>() {
    override fun serialize(value: ValidatorMode, gen: JsonGenerator, serializers: SerializerProvider) {
        when (value) {
            is ValidatorMode.Path -> gen.writeString(value.string)
            else -> throw IrohaSdkException("Unsupported type ${this::class}")
        }
    }
}

/**
 * Custom serializer for [UInt]
 */
object UIntSerializer : JsonSerializer<UInt>() {
    override fun serialize(value: UInt, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeNumber(value.toLong())
    }
}

/**
 * Custom serializer for [PublicKey]
 */
object PublicKeySerializer : JsonSerializer<PublicKey>() {
    override fun serialize(value: PublicKey, gen: JsonGenerator, serializers: SerializerProvider) {
        val res = ByteArrayOutputStream()
        Multihash.putUvarint(res, Ed25519.index.toLong())
        Multihash.putUvarint(res, value.payload.size.toLong())
        res.write(value.payload)
        gen.writeString(res.toByteArray().toHex())
    }
}

/**
 * Custom serializer for [Metadata]
 */
object MetadataSerializer : JsonSerializer<Metadata>() {
    override fun serialize(value: Metadata, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeObject(value.map)
    }
}

/**
 * Custom serializer for [EvaluatesTo]
 */
object EvaluatesToSerializer : JsonSerializer<EvaluatesTo<*>>() {
    override fun serialize(value: EvaluatesTo<*>, gen: JsonGenerator, serializers: SerializerProvider) {
        value.serialize(gen)
    }
}

/**
 * Custom serializer for [IdentifiableBox.NewRole]
 */
object IdentifiableBoxNewRoleSerializer : JsonSerializer<IdentifiableBox.NewRole>() {
    override fun serialize(value: IdentifiableBox.NewRole, gen: JsonGenerator, serializers: SerializerProvider) {
        serializeSingleMember(gen, value.newRole)
    }
}

/**
 * Custom serializer for Iroha2 enumeration types
 */
object EnumerationSerializer : JsonSerializer<ModelEnum>() {
    override fun serialize(value: ModelEnum, gen: JsonGenerator, serializers: SerializerProvider) {
        when (value) {
            is InstructionBox.Grant -> value.serialize(gen)
            is InstructionBox.Burn -> value.serialize(gen)
            is InstructionBox.Mint -> value.serialize(gen)
            is InstructionBox.SetKeyValue -> value.serialize(gen)
            is InstructionBox.Register -> value.serialize(gen)
            is Expression.Raw -> value.serialize(gen)
            is Value.Identifiable -> value.serialize(gen)
            is Value.Id -> value.serialize(gen)
            else -> serializeSingleMember(gen, value)
        }
    }
}

private fun Value.Identifiable.serialize(gen: JsonGenerator) = this.serializeEnum(gen)

private fun Value.Id.serialize(gen: JsonGenerator) = this.serializeEnum(gen)

private fun Expression.Raw.serialize(gen: JsonGenerator) = this.serializeEnum(gen)

private fun EvaluatesTo<*>.serialize(gen: JsonGenerator) = this.serializeEnum(gen)

private fun InstructionBox.SetKeyValue.serialize(gen: JsonGenerator) = this.serializeBox<SetKeyValueBox>(gen)

private fun InstructionBox.Grant.serialize(gen: JsonGenerator) = this.serializeBox<GrantBox>(gen)

private fun InstructionBox.Burn.serialize(gen: JsonGenerator) = this.serializeBox<BurnBox>(gen)

private fun InstructionBox.Mint.serialize(gen: JsonGenerator) = this.serializeBox<MintBox>(gen)

private fun InstructionBox.Register.serialize(gen: JsonGenerator) {
    val clazz = this::class
    val memberProperties = clazz.memberProperties
    when (memberProperties.size) {
        0 -> gen.writeString(clazz.simpleName)
        1 -> {
            gen.writeStartObject()
            gen.writeObjectField(
                clazz.simpleName,
                memberProperties.first().call(this)
                    ?.cast<RegisterBox>()
                    ?.`object`?.expression
            )
            gen.writeEndObject()
        }

        else -> throw SerializationException("Expected enum that accepts exactly 0 or 1 members as tuple")
    }
}

/**
 * Serializes BurnBox, MintBox, GrantBox etc...
 */
private inline fun <reified B> InstructionBox.serializeBox(gen: JsonGenerator) {
    val clazz = this::class
    val memberProperties = clazz.memberProperties
    when (memberProperties.size) {
        0 -> gen.writeString(clazz.simpleName)
        1 -> {
            gen.writeStartObject()
            gen.writeObjectFieldStart(clazz.simpleName)
            memberProperties.first().call(this)?.cast<B>()?.serializeBox(gen)
            gen.writeEndObject()
            gen.writeEndObject()
        }

        else -> throw SerializationException("Expected enum that accepts exactly 0 or 1 members as tuple")
    }
}

private inline fun <reified B> B.serializeBox(
    gen: JsonGenerator
) = when (B::class) {
    BurnBox::class -> this?.cast<BurnBox>()?.serializeBox(gen)
    MintBox::class -> this?.cast<MintBox>()?.serializeBox(gen)
    GrantBox::class -> this?.cast<GrantBox>()?.serializeBox(gen)
    SetKeyValueBox::class -> this?.cast<SetKeyValueBox>()?.serializeBox(gen)
    else -> throw IrohaSdkException("Unexpected type ${B::class}")
}

private fun BurnBox.serializeBox(
    gen: JsonGenerator
) = mintBurnSerialize(gen, this.`object`.expression, this.destinationId)

private fun MintBox.serializeBox(
    gen: JsonGenerator
) = mintBurnSerialize(gen, this.`object`.expression, this.destinationId)

private fun GrantBox.serializeBox(gen: JsonGenerator) {
    val fieldData = when (val rawValue = this.`object`.expression.cast<Expression.Raw>().value) {
        is Value.PermissionToken -> Value.PermissionToken::class.simpleName to rawValue.permissionToken
        is Value.Id -> RoleId::class.simpleName to rawValue.idBox.cast<IdBox.RoleId>().roleId
        else -> throw IrohaSdkException("Grant InstructionBox serialization error")
    }
    gen.writeObjectField(fieldData.first, fieldData.second)
    gen.writeObjectField("destination_id", this.destinationId)
}

private fun SetKeyValueBox.serializeBox(gen: JsonGenerator) {
    val id = this.objectId.expression
        .cast<Expression.Raw>().value
        .cast<Value.Id>().idBox
        .extractId()
    gen.writeObjectField(id::class.simpleName, id)
    gen.writeObjectField("key", this.key)
    gen.writeObjectField("value", this.value)
}

private fun mintBurnSerialize(
    gen: JsonGenerator,
    expression: Expression,
    destinationId: EvaluatesTo<IdBox>
) {
    val rawValue = expression
        .cast<Expression.Raw>().value
        .cast<Value.Numeric>().numericValue
    val fieldData = when (rawValue) {
        is NumericValue.U32 -> NumericValue.U32::class.simpleName to rawValue.u32
        is NumericValue.U64 -> NumericValue.U64::class.simpleName to rawValue.u64
        is NumericValue.U128 -> NumericValue.U128::class.simpleName to rawValue.u128
        is NumericValue.Fixed -> NumericValue.Fixed::class.simpleName to rawValue.fixed.fixedPointOfI64
        else -> throw IrohaSdkException("Grant InstructionBox serialization error")
    }
    gen.writeObjectField(fieldData.first, fieldData.second)
    gen.writeObjectField("destination_id", destinationId)
}

/**
 * Serialise a single member object or nothing at all
 */
private fun serializeSingleMember(gen: JsonGenerator, value: Any) {
    val clazz = value::class
    val memberProperties = clazz.memberProperties
    when (memberProperties.size) {
        0 -> gen.writeString(clazz.simpleName)
        1 -> {
            gen.writeStartObject()
            gen.writeObjectField(clazz.simpleName, memberProperties.first().call(value))
            gen.writeEndObject()
        }

        else -> throw SerializationException("Expected enum that accepts exactly 0 or 1 members as tuple")
    }
}

/**
 * Serialise an enum
 */
private inline fun <reified T> T.serializeEnum(gen: JsonGenerator) {
    val clazz = this!!::class
    val memberProperties = clazz.memberProperties
    when (memberProperties.size) {
        0 -> gen.writeString(clazz.simpleName)
        1 -> gen.writeObject(memberProperties.first().call(this))
        else -> throw SerializationException("Expected enum that accepts exactly 0 or 1 members as tuple")
    }
}

private fun String.asClass(): Class<*> {
    return runCatching {
        Class.forName(this)
    }.getOrNull() ?: run {
        when (this) {
            "kotlin.Long" -> Long::class.java
            "kotlin.Int" -> Int::class.java
            else -> null
        }
    } ?: throw DeserializationException("Class $this not found")
}
