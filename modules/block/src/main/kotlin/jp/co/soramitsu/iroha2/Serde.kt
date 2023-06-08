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
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.ipfs.multihash.Multihash
import jp.co.soramitsu.iroha2.DigestFunction.Ed25519
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.crypto.hash.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.LengthLimits
import jp.co.soramitsu.iroha2.generated.datamodel.NumericValue
import jp.co.soramitsu.iroha2.generated.datamodel.RegistrableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.ValueKind
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount
import jp.co.soramitsu.iroha2.generated.datamodel.account.SignatureCheckCondition
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Mintable
import jp.co.soramitsu.iroha2.generated.datamodel.asset.NewAssetDefinition
import jp.co.soramitsu.iroha2.generated.datamodel.blockvalue.BlockHeaderValue
import jp.co.soramitsu.iroha2.generated.datamodel.blockvalue.BlockValue
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.NewDomain
import jp.co.soramitsu.iroha2.generated.datamodel.events.EventsFilterBox
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.isi.BurnBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.GrantBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.isi.MintBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.RegisterBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.SetKeyValueBox
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Limits
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.generated.datamodel.peer.Peer
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.Definition
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.Token
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.TokenId
import jp.co.soramitsu.iroha2.generated.datamodel.permission.validator.Validator
import jp.co.soramitsu.iroha2.generated.datamodel.role.NewRole
import jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionLimits
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionQueryResult
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionValue
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Trigger
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.generated.primitives.addr.Ipv4Addr
import jp.co.soramitsu.iroha2.generated.primitives.addr.Ipv6Addr
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
        module.addDeserializer(Instruction::class.java, InstructionDeserializer)
        module.addDeserializer(GrantBox::class.java, GrantBoxDeserializer)
        module.addDeserializer(Value::class.java, ValueDeserializer)
        module.addDeserializer(ValueKind::class.java, ValueKindDeserializer)
        module.addDeserializer(AssetValue::class.java, AssetValueDeserializer)
        module.addDeserializer(PublicKey::class.java, PublicKeyDeserializer)
        module.addDeserializer(IdBox::class.java, IdBoxDeserializer)
        module.addDeserializer(AssetValueType::class.java, AssetValueTypeDeserializer)
        module.addDeserializer(Name::class.java, NameDeserializer)
        module.addDeserializer(Mintable::class.java, MintableDeserializer)
        module.addDeserializer(DomainId::class.java, DomainIdDeserializer)
        module.addDeserializer(AccountId::class.java, AccountIdDeserializer)
        module.addDeserializer(AssetDefinitionId::class.java, AssetDefinitionIdDeserializer)
        module.addDeserializer(AssetId::class.java, AssetIdDeserializer)
        module.addDeserializer(RegisterBox::class.java, RegisterBoxDeserializer)
        module.addDeserializer(SetKeyValueBox::class.java, SetKeyValueBoxDeserializer)
        module.addDeserializer(Metadata::class.java, MetadataDeserializer)
        module.addDeserializer(TokenId::class.java, TokenIdDeserializer)
        module.addKeyDeserializer(AssetDefinitionId::class.java, AssetDefinitionIdKeyDeserializer)
        module.addKeyDeserializer(AccountId::class.java, AccountIdKeyDeserializer)
        module.addKeyDeserializer(AssetId::class.java, AssetIdKeyDeserializer)
        module.addKeyDeserializer(DomainId::class.java, DomainIdKeyDeserializer)

        // serializers
        module.addKeySerializer(Name::class.java, NameAsKeySerializer)
        module.addSerializer(DomainId::class.java, DomainIdSerializer)
        module.addSerializer(TokenId::class.java, TokenIdSerializer)
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

private fun sealedDeserializeInstruction(p: JsonParser, mapper: ObjectMapper): Instruction {
    val node = p.readValueAsTree<JsonNode>().fields().next()
    val param = node.key

    val subtype = Instruction::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName == param
    } ?: throw DeserializationException("Class with constructor($param) not found")

    val argTypeName = subtype.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by $param")

    val toConvert: JsonNode = node.value

    val arg = mapper.convertValue(toConvert, argTypeName.asClass())
    return subtype.primaryConstructor?.call(arg) as Instruction
}

private fun sealedDeserializeGrantBox(p: JsonParser, mapper: ObjectMapper): GrantBox {
    val jsonNode = p.readValueAsTree<JsonNode>()

    val iter = jsonNode.iterator()
    val nodes = mutableListOf<JsonNode>()
    while (iter.hasNext()) {
        val node = iter.next()
        nodes.add(node)
    }

    val node = nodes[0].fields().next()
    val destination = nodes[1]

    val param = node.key

    val subtype = Value::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName?.contains(param) ?: false
    } ?: throw DeserializationException("Class with constructor($param) not found")

    val argTypeName = subtype.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by $param")

    val grantObject = mapper.convertValue(node.value, argTypeName.asClass())
    val destinationId = mapper.convertValue(destination, "jp.co.soramitsu.iroha2.generated.datamodel.IdBox".asClass())
    return GrantBox(
        `object` = grantObject.evaluatesTo().cast(),
        destinationId = destinationId.evaluatesTo().cast()
    )
}

private fun sealedDeserializeValue(p: JsonParser, mapper: ObjectMapper): Value {
    val node = p.readValueAsTree<JsonNode>().fields().next()
    val param = if (node.key.contains("Id")) "Id" else node.key

    val clazz = when (param) {
        "Bool" -> Boolean::class
        "String" -> String::class
        "Name" -> Name::class
        "Vec" -> Value::class
        "LimitedMetadata" -> Metadata::class
        "MetadataLimits" -> Limits::class
        "TransactionLimits" -> TransactionLimits::class
        "LengthLimits" -> LengthLimits::class
        "Id" -> IdBox::class
        "Identifiable" -> IdentifiableBox::class
        "PublicKey" -> PublicKey::class
        "SignatureCheckCondition" -> SignatureCheckCondition::class
        "TransactionValue" -> TransactionValue::class
        "TransactionQueryResult" -> TransactionQueryResult::class
        "PermissionToken" -> Token::class
        "Hash" -> Hash::class
        "Block" -> BlockValue::class
        "BlockHeader" -> BlockHeaderValue::class
        "Ipv4Addr" -> Ipv4Addr::class
        "Ipv6Addr" -> Ipv6Addr::class
        "U32" -> NumericValue::class
        "U64" -> NumericValue::class
        "U128" -> NumericValue::class
        "Fixed" -> NumericValue::class
        else -> throw DeserializationException("Value key $param not found")
    }

    if (param == "Bool") {
        return Value.Bool(node.value.booleanValue())
    } else if (param == "String") {
        return Value.String(node.value.asText())
    } else {
        val name = if (node.key == "Id") node.value.fields().next().key else node.key
        val value = if (node.key == "Id") node.value.fields().next().value else node.value
        val subtype = clazz.nestedClasses.find { clazz ->
            !clazz.isCompanion && clazz.simpleName == name
        } ?: throw DeserializationException("Class with constructor($param) not found")

        val argTypeName = subtype.primaryConstructor?.parameters
            ?.firstOrNull()?.type?.toString()
            ?: throw DeserializationException("Subtype parameter not found by $param")

        val arg = mapper.convertValue(value, argTypeName.asClass())
        return when (clazz) {
            Name::class -> Value.Name(subtype.primaryConstructor?.call(arg) as Name)
            Value::class -> throw DeserializationException("Value type $clazz not supported")
            Metadata::class -> Value.LimitedMetadata(subtype.primaryConstructor?.call(arg) as Metadata)
            Limits::class -> Value.MetadataLimits(subtype.primaryConstructor?.call(arg) as Limits)
            TransactionLimits::class -> Value.TransactionLimits(subtype.primaryConstructor?.call(arg) as TransactionLimits)
            LengthLimits::class -> Value.LengthLimits(subtype.primaryConstructor?.call(arg) as LengthLimits)
            IdBox::class -> Value.Id(subtype.primaryConstructor?.call(arg) as IdBox)
            IdentifiableBox::class -> Value.Identifiable(subtype.primaryConstructor?.call(arg) as IdentifiableBox)
            PublicKey::class -> Value.PublicKey(subtype.primaryConstructor?.call(arg) as PublicKey)
            SignatureCheckCondition::class -> Value.SignatureCheckCondition(subtype.primaryConstructor?.call(arg) as SignatureCheckCondition)
            TransactionValue::class -> Value.TransactionValue(subtype.primaryConstructor?.call(arg) as TransactionValue)
            TransactionQueryResult::class -> Value.TransactionQueryResult(subtype.primaryConstructor?.call(arg) as TransactionQueryResult)
            Token::class -> Value.PermissionToken(subtype.primaryConstructor?.call(arg) as Token)
            Hash::class -> Value.Hash(subtype.primaryConstructor?.call(arg) as Hash)
            BlockValue::class -> Value.Block(subtype.primaryConstructor?.call(arg) as BlockValue)
            BlockHeaderValue::class -> Value.BlockHeader(subtype.primaryConstructor?.call(arg) as BlockHeaderValue)
            Ipv4Addr::class -> Value.Ipv4Addr(subtype.primaryConstructor?.call(arg) as Ipv4Addr)
            Ipv6Addr::class -> Value.Ipv6Addr(subtype.primaryConstructor?.call(arg) as Ipv6Addr)
            NumericValue::class -> Value.Numeric(subtype.primaryConstructor?.call(arg) as NumericValue)
            else -> throw DeserializationException("Value type $clazz not found")
        }
    }
}

private fun sealedDeserializeIdBox(p: JsonParser, mapper: ObjectMapper): IdBox {
    val node = p.readValueAsTree<JsonNode>().fields().next().value.fields().next()
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

private fun sealedDeserializeRegisterBox(p: JsonParser, mapper: ObjectMapper): RegisterBox {
    val node = p.readValueAsTree<JsonNode>().fields().next().value.fields().next()

    val param = node.key.removePrefix("New")
    val subtype = RegistrableBox::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName == param
    }
    val argTypeName = subtype?.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by $param")

    val arg = mapper.convertValue(node.value, argTypeName.asClass())
    return when (arg) {
        is NewDomain -> RegisterBox(RegistrableBox.Domain(arg).evaluatesTo())
        is NewAccount -> RegisterBox(RegistrableBox.Account(arg).evaluatesTo())
        is Definition -> RegisterBox(RegistrableBox.PermissionTokenDefinition(arg).evaluatesTo())
        is Peer -> RegisterBox(RegistrableBox.Peer(arg).evaluatesTo())
        is NewAssetDefinition -> RegisterBox(RegistrableBox.AssetDefinition(arg).evaluatesTo())
        is Asset -> RegisterBox(RegistrableBox.Asset(arg).evaluatesTo())
        is Trigger<*> -> RegisterBox(RegistrableBox.Trigger(arg as Trigger<EventsFilterBox>).evaluatesTo())
        is NewRole -> RegisterBox(RegistrableBox.Role(arg).evaluatesTo())
        is Validator -> RegisterBox(RegistrableBox.Validator(arg).evaluatesTo())
        else -> throw DeserializationException("Register box `$arg` not found")
    }
}

private fun sealedDeserializeSetKeyValueBox(p: JsonParser, mapper: ObjectMapper): SetKeyValueBox {
    val iter = p.readValueAsTree<JsonNode>().iterator()
    val nodes = mutableListOf<JsonNode>()
    while (iter.hasNext()) {
        val node = iter.next()
        nodes.add(node)
    }

    val objectId = nodes[0].fields().next().value.fields().next()
    val key = nodes[1].fields().next()
    val subtype = IdBox::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName == objectId.key
    }
    val argTypeName = subtype?.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by $objectId")

    val objectIdArg = mapper.convertValue(objectId.value, argTypeName.asClass())
    val keyArg = mapper.convertValue(key.value, "jp.co.soramitsu.iroha2.generated.datamodel.name.Name".asClass())
    val valueArg = mapper.convertValue(nodes[2], "jp.co.soramitsu.iroha2.generated.datamodel.Value".asClass())
    return SetKeyValueBox(
        objectId = objectIdArg.evaluatesTo().cast(),
        key = keyArg.evaluatesTo().cast(),
        value = valueArg.evaluatesTo().cast()
    )
}

private fun sealedDeserializeMetadata(p: JsonParser, mapper: ObjectMapper): Metadata {
    val nodeMetadata = p.readValueAsTree<JsonNode>().fields()
    if (!nodeMetadata.hasNext()) {
        return Metadata(mapOf())
    }
    val node = nodeMetadata.next()
    val key = node.key.asName()
    val valueNode = node.value.fields().next()
    val value = valueNode.value.asText().asValue()
    return Metadata(mapOf(Pair(key, value)))
}

private fun sealedDeserializeToken(p: JsonParser, mapper: ObjectMapper): Token {
    val jsonNode = p.readValueAsTree<JsonNode>()

    val iter = jsonNode.iterator()
    val nodes = mutableListOf<JsonNode>()

    while (iter.hasNext()) {
        val node = iter.next()
        nodes.add(node)
    }
    val definitionId = jsonNode.fields().next()
    val params = nodes[1].fields()
    val tokenMap = mutableMapOf<Name, Value>()

    val arg = mapper.convertValue(definitionId.value, "jp.co.soramitsu.iroha2.generated.datamodel.permission.token.TokenId".asClass()) as TokenId
    if (params.hasNext()) {
        val params = params.next().value.fields().next()
        val arg2 = mapper.convertValue(params.key, "jp.co.soramitsu.iroha2.generated.datamodel.name.Name".asClass()) as Name
        val arg3 = mapper.convertValue(params.value, "jp.co.soramitsu.iroha2.generated.datamodel.Value".asClass()) as Value
        tokenMap[arg2] = arg3
    }

    return Token(
        arg,
        tokenMap
    )
}

/**
 * Deserializer for [Iroha Special Instructions][Instruction]
 */
object InstructionDeserializer : JsonDeserializer<Instruction>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Instruction {
        return sealedDeserializeInstruction(p, JSON_SERDE)
    }
}

object GrantBoxDeserializer : JsonDeserializer<GrantBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): GrantBox {
        return sealedDeserializeGrantBox(p, JSON_SERDE)
    }
}

/**
 * Deserializer for [Value]
 */
object ValueDeserializer : JsonDeserializer<Value>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Value {
        return sealedDeserializeValue(p, JSON_SERDE)
    }
}

object ValueKindDeserializer : JsonDeserializer<ValueKind>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ValueKind {
        return p.readValueAs(String::class.java).asValueKind()
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

object RegisterBoxDeserializer : JsonDeserializer<RegisterBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): RegisterBox {
        return sealedDeserializeRegisterBox(p, JSON_SERDE)
    }
}

object SetKeyValueBoxDeserializer : JsonDeserializer<SetKeyValueBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SetKeyValueBox {
        return sealedDeserializeSetKeyValueBox(p, JSON_SERDE)
    }
}

object MetadataDeserializer : JsonDeserializer<Metadata>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Metadata {
        return sealedDeserializeMetadata(p, JSON_SERDE)
    }
}

object TokenIdDeserializer : JsonDeserializer<TokenId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): TokenId {
        return p.readValueAs(String::class.java).asTokenId()
    }
}

object TokenDeserializer : JsonDeserializer<Token>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Token {
        return sealedDeserializeToken(p, JSON_SERDE)
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
        return PublicKey(Ed25519.hashFunName, key.fromHex())
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
 * Serializer for [TokenId]
 */
object TokenIdSerializer : JsonSerializer<TokenId>() {
    override fun serialize(value: TokenId, gen: JsonGenerator, serializers: SerializerProvider) {
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
            is Instruction.Grant -> value.serialize(gen)
            is Instruction.Burn -> value.serialize(gen)
            is Instruction.Mint -> value.serialize(gen)
            is Instruction.SetKeyValue -> value.serialize(gen)
            is Instruction.Register -> value.serialize(gen)
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

private fun Instruction.SetKeyValue.serialize(gen: JsonGenerator) = this.serializeBox<SetKeyValueBox>(gen)

private fun Instruction.Grant.serialize(gen: JsonGenerator) = this.serializeBox<GrantBox>(gen)

private fun Instruction.Burn.serialize(gen: JsonGenerator) = this.serializeBox<BurnBox>(gen)

private fun Instruction.Mint.serialize(gen: JsonGenerator) = this.serializeBox<MintBox>(gen)

private fun Instruction.Register.serialize(gen: JsonGenerator) {
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
private inline fun <reified B> Instruction.serializeBox(gen: JsonGenerator) {
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
        is Value.PermissionToken -> Value.PermissionToken::class.simpleName to rawValue.token
        is Value.Id -> RoleId::class.simpleName to rawValue.idBox.cast<IdBox.RoleId>().roleId
        else -> throw IrohaSdkException("Grant instruction serialization error")
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
        is NumericValue.Fixed -> NumericValue.Fixed::class.simpleName to rawValue.fixed.fixedPoint
        else -> throw IrohaSdkException("Grant instruction serialization error")
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
