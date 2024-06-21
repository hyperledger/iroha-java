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
import jp.co.soramitsu.iroha2.generated.*
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * This JSON mapper is configured to serialise and deserialise `Genesis block` in a format compatible with Iroha 2 peer
 */
public val JSON_SERDE by lazy {
    ObjectMapper().also { mapper ->
        val module = SimpleModule()

        // deserializers
        module.addDeserializer(Name::class.java, NameDeserializer)

        module.addDeserializer(InstructionBox::class.java, InstructionDeserializer)
//        module.addDeserializer(RegisterBox::class.java, RegisterBoxDeserializer)
//        module.addDeserializer(MintBox::class.java, MintBoxDeserializer)
        module.addDeserializer(GrantBox::class.java, GrantBoxDeserializer)
//        module.addDeserializer(SetKeyValueBox::class.java, SetKeyValueBoxDeserializer)
//        module.addDeserializer(RemoveKeyValueBox::class.java, RemoveKeyValueBoxDeserializer)

        module.addDeserializer(IdBox::class.java, IdBoxDeserializer)
        module.addDeserializer(DomainId::class.java, DomainIdDeserializer)
        module.addDeserializer(AssetDefinitionId::class.java, AssetDefinitionIdDeserializer)
        module.addDeserializer(AccountId::class.java, AccountIdDeserializer)
        module.addDeserializer(AssetId::class.java, AssetIdDeserializer)
        module.addDeserializer(RoleId::class.java, RoleIdDeserializer)

        module.addDeserializer(AssetValue::class.java, AssetValueDeserializer)
        module.addDeserializer(PublicKey::class.java, PublicKeyDeserializer)
        module.addDeserializer(AssetValueType::class.java, AssetValueTypeDeserializer)
        module.addDeserializer(Mintable::class.java, MintableDeserializer)
        module.addDeserializer(Metadata::class.java, MetadataDeserializer)
//        module.addDeserializer(NewParameter::class.java, NewParameterBoxDeserializer)
        module.addDeserializer(NewRole::class.java, NewRoleDeserializer)
        module.addDeserializer(Permission::class.java, PermissionTokenDeserializer)
        module.addDeserializer(TriggerId::class.java, TriggerIdDeserializer)
        module.addKeyDeserializer(AssetDefinitionId::class.java, AssetDefinitionIdKeyDeserializer)
        module.addKeyDeserializer(AccountId::class.java, AccountIdKeyDeserializer)
        module.addKeyDeserializer(AssetId::class.java, AssetIdKeyDeserializer)

        // serializers
        module.addSerializer(Name::class.java, NameSerializer)

        module.addSerializer(DomainId::class.java, DomainIdSerializer)
        module.addSerializer(AssetDefinitionId::class.java, AssetDefinitionIdSerializer)
        module.addSerializer(AccountId::class.java, AccountIdSerializer)
        module.addSerializer(AssetId::class.java, AssetIdSerializer)
        module.addSerializer(TriggerId::class.java, TriggerIdSerializer)
        module.addSerializer(RoleId::class.java, RoleIdSerializer)

        module.addSerializer(IdentifiableBox.NewRole::class.java, IdentifiableBoxNewRoleSerializer)
        module.addSerializer(Permission::class.java, PermissionTokenSerializer)
        module.addSerializer(SocketAddr::class.java, SocketAddrSerializer)
        module.addSerializer(UInt::class.java, UIntSerializer)
        module.addSerializer(PublicKey::class.java, PublicKeySerializer)
//        module.addSerializer(ModelEnum::class.java, EnumerationSerializer)
        module.addSerializer(Metadata::class.java, MetadataSerializer)
        module.addSerializer(IdentifiableBox.NewRole::class.java, IdentifiableBoxNewRoleSerializer)
        module.addSerializer(TimeEventFilter::class.java, TimeEventFilterSerializer)
        module.addSerializer(Schedule::class.java, ScheduleSerializer)

        mapper.registerModule(module)
        mapper.registerModule(
            KotlinModule.Builder()
                .configure(KotlinFeature.NullToEmptyCollection, true)
                .configure(KotlinFeature.NullToEmptyMap, true)
                .build(),
        )

        mapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
    }
}

/**
 * Deserializer for [Iroha Special Instructions][InstructionExpr]
 */
object InstructionDeserializer : JsonDeserializer<InstructionBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): InstructionBox {
        return sealedDeserializeInstruction(p.readValueAsTree(), JSON_SERDE)
    }
}

/**
 * Deserializer for [GrantBox]
 */
object GrantBoxDeserializer : JsonDeserializer<GrantBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): GrantBox {
        return sealedDeserializeGrantBox(p, JSON_SERDE)
    }
}

object AssetValueDeserializer : JsonDeserializer<AssetValue>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): AssetValue {
        val node = p.readValueAsTree<JsonNode>().fields().next()
        return when (node.key) {
            "Store" -> AssetValue.Store(Metadata(mapOf()))
            else -> throw DeserializationException("AssetValue ${node.key} not found")
        }
    }
}

/**
 * Deserializer for [IdBox]
 */
object IdBoxDeserializer : JsonDeserializer<IdBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): IdBox {
        return sealedDeserializeIdBox(p, JSON_SERDE)
    }
}

// object RegisterBoxDeserializer : JsonDeserializer<RegisterBox>() {
//    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): RegisterBox {
//        return sealedDeserializeRegisterBox(p, JSON_SERDE)
//    }
// }
//
// object MintBoxDeserializer : JsonDeserializer<MintBox>() {
//    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): MintBox {
//        return deserializeMintBox(p, JSON_SERDE)
//    }
// }

object TriggerIdDeserializer : JsonDeserializer<TriggerId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): TriggerId {
        val triggerName = p.readValueAsTree<JsonNode>().asText()
        return getTriggerId(triggerName)
    }
}

object PermissionTokenDeserializer : JsonDeserializer<Permission>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Permission {
        val jsonNode = p.readValueAsTree<JsonNode>()
        val iter = jsonNode.iterator()
        val nodes = mutableListOf<JsonNode>()
        while (iter.hasNext()) {
            val node = iter.next()
            nodes.add(node)
        }

        val definitionId = jsonNode.fields().next()
        val payload = when (nodes[1].isNull) {
            true -> ""
            false -> JSON_SERDE.convertValue(nodes[1], String::class.java)
        }

        return Permission(
            id = PermissionId(definitionId.value.asText().asName()),
            payload = payload,
        )
    }
}

// object RemoveKeyValueBoxDeserializer : JsonDeserializer<RemoveKeyValueBox>() {
//    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): RemoveKeyValueBox {
//        return deserializeRemoveKeyValueBox(p, JSON_SERDE)
//    }
// }
//
// object SetKeyValueBoxDeserializer : JsonDeserializer<SetKeyValueBox>() {
//    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SetKeyValueBox {
//        return deserializeSetKeyValueBox(p, JSON_SERDE)
//    }
// }

object MetadataDeserializer : JsonDeserializer<Metadata>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Metadata {
        return deserializeMetadata(p, JSON_SERDE)
    }
}

object NewRoleDeserializer : JsonDeserializer<NewRole>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): NewRole {
        return deserializeNewRole(p, JSON_SERDE)
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
        return PublicKey(Algorithm.Ed25519(), key.substring(6, key.length).fromHex())
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
 * Deserializer for [role ID][RoleId]
 */
object RoleIdDeserializer : JsonDeserializer<RoleId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): RoleId {
        return p.readValueAs(String::class.java).asRoleId()
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

///**
// * Serializer for [RawGenesisBlockFile]
// */
//object RawGenesisBlockSerializer : JsonSerializer<RawGenesisBlockFile>() {
//    override fun serialize(block: RawGenesisBlockFile, gen: JsonGenerator, serializers: SerializerProvider) {
//        gen.writeStartObject()
//        when (block.transactions[0].isi.isEmpty()) {
//            true -> gen.writeObjectField("transactions", listOf<InstructionBox>())
//            false -> gen.writeObjectField("transactions", block.transactions)
//        }
//        gen.writeObjectField("executor", block.executorFile)
//        gen.writeEndObject()
//    }
//}

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
 * Serializer for [PermissionToken]
 */
object PermissionTokenSerializer : JsonSerializer<Permission>() {
    override fun serialize(token: Permission, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeStartObject()
        gen.writeObjectField(Permission::id.name.toSnakeCase(), token.id)

        val payload = when (token.payload.isEmpty()) {
            true -> null
            false -> token.payload
        }
        gen.writeObjectField(Permission::payload.name, payload)
        gen.writeEndObject()
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
 * Serializer for [RoleId]
 */
object RoleIdSerializer : JsonSerializer<RoleId>() {
    override fun serialize(value: RoleId, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.asString())
    }
}

object SocketAddrSerializer : JsonSerializer<SocketAddr>() {
    override fun serialize(value: SocketAddr, gen: JsonGenerator, serializers: SerializerProvider) {
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
 * Serializer for [TimeEventFilter]
 */
object TimeEventFilterSerializer : JsonSerializer<TimeEventFilter>() {
    override fun serialize(value: TimeEventFilter, gen: JsonGenerator, serializers: SerializerProvider) {
        value.serializeEnum(gen)
    }
}

/**
 * Serializer for [Schedule]
 */
object ScheduleSerializer : JsonSerializer<Schedule>() {
    override fun serialize(value: Schedule, gen: JsonGenerator, serializers: SerializerProvider) {
        val start = value.start.let { mapOf(Pair("secs", it.u64), Pair("nanos", it.u32)) }
        val period = value.period?.let { mapOf(Pair("secs", it.u64), Pair("nanos", it.u32)) }
        val schedule = mapOf(Pair("start", start), Pair("period", period))
        gen.writeObject(schedule)
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
        gen.writeObject(value.sortedMapOfName)
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
 * Custom serializer for [Parameter]
 */
// object ParameterSerializer : JsonSerializer<Parameter>() {
//    override fun serialize(value: Parameter, gen: JsonGenerator, serializers: SerializerProvider) {
//        gen.writeString(value.asString())
//    }
// }

/**
 * Custom serializer for Iroha2 enumeration types
 */
// object EnumerationSerializer : JsonSerializer<ModelEnum>() {
//    override fun serialize(value: ModelEnum, gen: JsonGenerator, serializers: SerializerProvider) {
//        when (value) {
//            is InstructionBox.Grant -> value.serialize(gen)
//            is InstructionBox.Burn -> value.serialize(gen)
//            is InstructionBox.Mint -> value.serialize(gen)
//            is InstructionBox.SetKeyValue -> value.serialize(gen)
//            is InstructionBox.Register -> value.serialize(gen)
//            else -> serializeSingleMember(gen, value)
//        }
//    }
// }

// private fun InstructionBox.SetKeyValue.serialize(gen: JsonGenerator) = this.serializeBox<SetKeyValueBox>(gen)
//
// private fun InstructionBox.Grant.serialize(gen: JsonGenerator) {
//    return this.serializeBox<GrantBox>(gen)
// }
//
// private fun InstructionBox.Burn.serialize(gen: JsonGenerator) = this.serializeBox<BurnBox>(gen)
//
// private fun InstructionBox.Mint.serialize(gen: JsonGenerator) = this.serializeBox<MintBox>(gen)

private fun InstructionBox.Register.serialize(gen: JsonGenerator) {
    val clazz = this::class
    val memberProperties = clazz.memberProperties
    when (memberProperties.size) {
        0 -> gen.writeString(clazz.simpleName)
        1 -> {
            gen.writeStartObject()
            gen.writeObjectField(
                clazz.simpleName,
                memberProperties.first().call(this)?.cast<RegisterBox>(), // todo
            )
            gen.writeEndObject()
        }

        else -> throw SerializationException("Expected enum that accepts exactly 0 or 1 members as tuple")
    }
}

// /**
// * Serializes BurnBox, MintBox, GrantBox etc...
// */
// private inline fun <reified B> InstructionBox.serializeBox(gen: JsonGenerator) {
//    val clazz = this::class
//    val memberProperties = clazz.memberProperties
//    when (memberProperties.size) {
//        0 -> gen.writeString(clazz.simpleName)
//        1 -> {
//            gen.writeStartObject()
//            gen.writeObjectFieldStart(clazz.simpleName)
//            memberProperties.first().call(this)?.cast<B>()?.serializeBox(gen)
//            gen.writeEndObject()
//            gen.writeEndObject()
//        }
//
//        else -> throw SerializationException("Expected enum that accepts exactly 0 or 1 members as tuple")
//    }
// }

// private inline fun <reified B> B.serializeBox(
//    gen: JsonGenerator,
// ) = when (B::class) {
//    BurnBox::class -> this?.cast<BurnBox>()?.serializeBox(gen)
//    MintBox::class -> this?.cast<MintBox>()?.serializeBox(gen)
//    GrantBox::class -> this?.cast<GrantBox>()?.serializeBox(gen)
//    SetKeyValueBox::class -> this?.cast<SetKeyValueBox>()?.serializeBox(gen)
//    else -> throw IrohaSdkException("Unexpected type ${B::class}")
// }

// private fun BurnBox.serializeBox(
//    gen: JsonGenerator,
// ) = mintBurnSerialize(gen, this.`object`, this.destinationId)
//
// private fun MintBox.serializeBox(
//    gen: JsonGenerator,
// ) = mintBurnSerialize(gen, this.`object`.expression, this.destinationId)
//
// private fun GrantBox.serializeBox(gen: JsonGenerator) {
//    val fieldData = when (val rawValue = this.`object`.expression.cast<Boxession.Raw>().value) {
//        is Value.PermissionToken -> Value.PermissionToken::class.simpleName to rawValue.permissionToken
//        is Value.Id -> RoleId::class.simpleName to rawValue.idBox.cast<IdBox.RoleId>().roleId
//        else -> throw IrohaSdkException("Grant InstructionBox serialization error")
//    }
//    gen.writeObjectFieldStart("object")
//    gen.writeObjectField(fieldData.first, fieldData.second)
//    gen.writeEndObject()
//    gen.writeObjectField("destination", this.destinationId)
// }
//
// private fun SetKeyValueBox.serializeBox(gen: JsonGenerator) {
//    val id = this.objectId.expression
//        .cast<Boxession.Raw>().value
//        .cast<Value.Id>().idBox
//        .extractId()
//    gen.writeObjectField(id::class.simpleName, id)
//    gen.writeObjectField("key", this.key)
//
//    val fieldValue = when (val value = this.value.expression.cast<Boxession.Raw>().value) {
//        is Value.Numeric -> value.cast<Value.Numeric>().numericValue.formatAsString()
//        else -> value
//    }
//    gen.writeObjectField("value", fieldValue)
// }
//
// private fun mintBurnSerialize(
//    gen: JsonGenerator,
//    expression: Boxession,
//    destinationId: EvaluatesTo<IdBox>,
// ) {
//    val rawValue = expression.cast<Boxession.Raw>().value.cast<Value.Numeric>().numericValue
//    gen.writeObjectField("object", rawValue)
//    gen.writeObjectField("destination", destinationId)
// }

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

private fun String.asClass() = runCatching {
    Class.forName(this)
}.getOrNull() ?: run {
    when (this) {
        "kotlin.Long" -> Long::class.java
        "kotlin.Int" -> Int::class.java
        else -> null
    }
} ?: throw DeserializationException("Class $this not found")

private fun sealedDeserializeInstruction(jsonNode: JsonNode, mapper: ObjectMapper): InstructionBox {
    val node = jsonNode.fields().next()
    val param = node.key

    val subtype = InstructionBox::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName == param
    } ?: throw DeserializationException("Class with constructor($param) not found")

    val argTypeName = subtype.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by $param")

    val toConvert: JsonNode = node.value

    val arg = mapper.convertValue(toConvert, argTypeName.asClass())
    return subtype.primaryConstructor?.call(arg) as InstructionBox
}

private fun sealedDeserializeGrantBox(p: JsonParser, mapper: ObjectMapper): GrantBox {
    val jsonNode = p.readValueAsTree<JsonNode>()

    val iter = jsonNode.iterator()
    val nodes = mutableListOf<JsonNode>()
    while (iter.hasNext()) {
        val node = iter.next()
        nodes.add(node)
    }

    val node = jsonNode.fields().next().value.fields().next()
    val destination = nodes[1]
    val paramAndValueToConvert = if (RoleId::class.java.simpleName == node.key) {
        Pair(
            "Id",
            mapper.createObjectNode().set<ObjectNode>(
                jsonNode.fields().next().key,
                jsonNode.fields().next().value,
            ),
        )
    } else {
        Pair(node.key, node.value)
    }

//    val subtype = Value::class.nestedClasses.find { clazz ->
//        !clazz.isCompanion && clazz.simpleName?.contains(paramAndValueToConvert.first) ?: false
//    } ?: throw DeserializationException("Class with constructor(${paramAndValueToConvert.first}) not found")
//
//    val argTypeName = subtype.primaryConstructor?.parameters
//        ?.firstOrNull()?.type?.toString()
//        ?: throw DeserializationException("Subtype parameter not found by ${paramAndValueToConvert.first}")

    return GrantBox.Permission(
        GrantOfPermissionAndAccount(
            Permission(PermissionId("".asName()), ""),
            AccountId("".asDomainId(), publicKeyFromHex("").toIrohaPublicKey()),
        ),
    )
}

private fun sealedDeserializeIdBox(p: JsonParser, mapper: ObjectMapper): IdBox {
    val node = p.readValueAsTree<JsonNode>().fields().next()
    val param = node.key

    val subtype = IdBox::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName == param
    } ?: throw DeserializationException("Class with constructor($param) not found")

    val argTypeName = subtype.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by $param")

    val arg = mapper.convertValue(node.value, argTypeName.asClass())
    return subtype.primaryConstructor?.call(arg) as IdBox
}

// private fun sealedDeserializeRegisterBox(p: JsonParser, mapper: ObjectMapper): RegisterBox {
//    val node = p.readValueAsTree<JsonNode>().fields().next()
//
//    val param = node.key.removePrefix("New")
//    val subtype = RegistrableBox::class.nestedClasses.find { clazz ->
//        !clazz.isCompanion && clazz.simpleName == param
//    }
//    val argTypeName = subtype?.primaryConstructor?.parameters
//        ?.firstOrNull()?.type?.toString()
//        ?: throw DeserializationException("Subtype parameter not found by $param")
//
//    val arg = mapper.convertValue(node.value, argTypeName.asClass())
//    return getRegisterBox(arg)
// }

private fun getRegisterBox(arg: Any): RegisterBox {
    return when (arg) {
        is NewDomain -> RegisterBox.Domain(RegisterOfDomain(arg))
        is NewAccount -> RegisterBox.Account(RegisterOfAccount(arg))
        is Peer -> RegisterBox.Peer(RegisterOfPeer(arg))
        is NewAssetDefinition -> RegisterBox.AssetDefinition(RegisterOfAssetDefinition(arg))
        is Asset -> RegisterBox.Asset(RegisterOfAsset(arg))
        is NewRole -> RegisterBox.Role(RegisterOfRole(arg))
        is Trigger -> RegisterBox.Trigger(RegisterOfTrigger(arg))
        else -> throw DeserializationException("Register box `$arg` not found")
    }
}

// private fun deserializeMintBox(p: JsonParser, mapper: ObjectMapper): MintBox {
//    val jsonNode = p.readValueAsTree<JsonNode>()
//    val iter = jsonNode.iterator()
//    val nodes = mutableListOf<JsonNode>()
//    while (iter.hasNext()) {
//        val node = iter.next()
//        nodes.add(node)
//    }
//    val numericTypeAndValue = jsonNode.fields().next().value.asText().split("_")
//    val newNode = mapper.createObjectNode().set<ObjectNode>(
//        numericTypeAndValue[1].toUpperCasePreservingASCIIRules(),
//        IntNode(numericTypeAndValue[0].toInt()),
//    )
//    val objectId = mapper.convertValue(
//        newNode,
//        Value::class.java,
//    ) as Value
//    val destination = mapper.convertValue(
//        nodes[1],
//        IdBox::class.java,
//    ) as IdBox
//    return MintBox(
//        `object` = objectId.evaluatesTo().cast(),
//        destinationId = destination.evaluatesTo().cast(),
//    )
// }

// private fun deserializeSetKeyValueBox(p: JsonParser, mapper: ObjectMapper): SetKeyValueBox {
//    val jsonNode = p.readValueAsTree<JsonNode>()
//    val iter = jsonNode.iterator()
//    val nodes = mutableListOf<JsonNode>()
//    while (iter.hasNext()) {
//        val node = iter.next()
//        nodes.add(node)
//    }
//
//    val objectId = jsonNode.fields().next()
//    val key = nodes[1].fields().next()
//    val subtype = IdBox::class.nestedClasses.find { clazz ->
//        !clazz.isCompanion && clazz.simpleName == objectId.key
//    }
//    val argTypeName = subtype?.primaryConstructor?.parameters
//        ?.firstOrNull()?.type?.toString()
//        ?: throw DeserializationException("Subtype parameter not found by $objectId")
//
//    val objectIdArg = mapper.convertValue(objectId.value, argTypeName.asClass())
//    val keyArg = mapper.convertValue(key.value, Name::class.java)
//    val valueArg = mapper.convertValue(nodes[2], Value::class.java)
//    return SetKeyValueBox(
//        objectId = objectIdArg.evaluatesTo().cast(),
//        key = keyArg.evaluatesTo().cast(),
//        value = valueArg.evaluatesTo().cast(),
//    )
// }

private fun deserializeNewRole(p: JsonParser, mapper: ObjectMapper): NewRole {
    val iter = p.readValueAsTree<JsonNode>().iterator()
    val nodes = mutableListOf<JsonNode>()
    while (iter.hasNext()) {
        val node = iter.next()
        nodes.add(node)
    }

    val tokens = nodes[1].map {
        mapper.convertValue(it, Permission::class.java) as Permission
    }
    val roleId = RoleId(nodes[0].asText().asName())
    return NewRole(Role(id = roleId, permissions = tokens))
}

private fun deserializeMetadata(p: JsonParser, mapper: ObjectMapper): Metadata {
    val nodeMetadata = p.readValueAsTree<JsonNode>().fields()
    if (!nodeMetadata.hasNext()) {
        return Metadata(mapOf())
    }
    val node = nodeMetadata.next()
    val key = node.key.asName()
    val valueNode = node.value.fields().next()
    val value = valueNode.value.asText().asMetadataValueBox()
    return Metadata(mapOf(Pair(key, value)))
}

//private fun String.toNumericValue(): Numeric {
//    val (number, type) = this.split('_')
//    return when (type) {
//        Numeric.U32::class.simpleName?.lowercase() -> NumericValue.U32(number.toLong())
//        Numeric.U64::class.simpleName?.lowercase() -> NumericValue.U64(number.toBigInteger())
//        Numeric.U128::class.simpleName?.lowercase() -> NumericValue.U128(number.toBigInteger())
//        "fx" -> NumericValue.Fixed(Fixed(number.toBigDecimal()))
//        else -> throw IllegalArgumentException("Number out of range")
//    }
//}

private fun getTriggerAuthority(triggerAction: JsonNode): AccountId {
    return triggerAction.get("authority").asText().asAccountId()
}

private fun getTriggerId(triggerName: String): TriggerId {
    return when (triggerName.contains("$")) {
        true -> {
            val triggerNameWithDomain = triggerName.split("$")
            TriggerId(name = triggerNameWithDomain[0].asName())
        }
        false -> TriggerId(name = triggerName.asName())
    }
}

private fun getTriggerRepeats(triggerAction: JsonNode): Repeats {
    val repeatsNodeFields = triggerAction.get("repeats").fields()
    return when (repeatsNodeFields.hasNext()) {
        true -> Repeats.Exactly(repeatsNodeFields.next().value.asLong())
        false -> Repeats.Indefinitely()
    }
}

private fun getTriggerFilter(triggerAction: JsonNode): TriggeringEventFilterBox {
    val filterNode = triggerAction.get("filter").fields().next()
    return when (filterNode.key) {
        "Data" -> {
            throw IrohaSdkException("${filterNode.key} is not supported")
        }
        "Time" -> {
            val scheduleNode = filterNode.value.get("Schedule")
            val start = scheduleNode.get("start")
            val period = scheduleNode.get("period")
            val periodDuration = when (period.isNull) {
                true -> null
                false -> Duration(u64 = BigInteger.valueOf(period.get("secs").asLong()), u32 = period.get("nanos").asLong())
            }
            TriggeringEventFilterBox.Time(
                TimeEventFilter(
                    ExecutionTime.Schedule(
                        Schedule(
                            Duration(u64 = BigInteger.valueOf(start.get("secs").asLong()), u32 = start.get("nanos").asLong()),
                            periodDuration,
                        ),
                    ),
                ),
            )
        }
        "ExecuteTrigger" -> {
            val executeTrigger = JSON_SERDE.convertValue(filterNode.value, ExecuteTriggerEventFilter::class.java)
            TriggeringEventFilterBox.ExecuteTrigger(executeTrigger)
        }
        "Pipeline" -> {
            throw IrohaSdkException("${filterNode.key} is not supported")
        }
        else -> {
            throw IrohaSdkException("${filterNode.key} is not supported")
        }
    }
}
