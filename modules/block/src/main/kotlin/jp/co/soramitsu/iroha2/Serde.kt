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
import jp.co.soramitsu.iroha2.generated.Asset
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.BlockHeader
import jp.co.soramitsu.iroha2.generated.BurnExpr
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.ExecutorMode
import jp.co.soramitsu.iroha2.generated.Expression
import jp.co.soramitsu.iroha2.generated.GrantExpr
import jp.co.soramitsu.iroha2.generated.Hash
import jp.co.soramitsu.iroha2.generated.HashValue
import jp.co.soramitsu.iroha2.generated.IdBox
import jp.co.soramitsu.iroha2.generated.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.InstructionExpr
import jp.co.soramitsu.iroha2.generated.Ipv4Addr
import jp.co.soramitsu.iroha2.generated.Ipv6Addr
import jp.co.soramitsu.iroha2.generated.LengthLimits
import jp.co.soramitsu.iroha2.generated.Limits
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.MintExpr
import jp.co.soramitsu.iroha2.generated.Mintable
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.NewAccount
import jp.co.soramitsu.iroha2.generated.NewAssetDefinition
import jp.co.soramitsu.iroha2.generated.NewDomain
import jp.co.soramitsu.iroha2.generated.NewParameterExpr
import jp.co.soramitsu.iroha2.generated.NewRole
import jp.co.soramitsu.iroha2.generated.NumericValue
import jp.co.soramitsu.iroha2.generated.Parameter
import jp.co.soramitsu.iroha2.generated.Peer
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.PublicKey
import jp.co.soramitsu.iroha2.generated.RawGenesisBlock
import jp.co.soramitsu.iroha2.generated.RegisterExpr
import jp.co.soramitsu.iroha2.generated.RegistrableBox
import jp.co.soramitsu.iroha2.generated.Role
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.SequenceExpr
import jp.co.soramitsu.iroha2.generated.SetKeyValueExpr
import jp.co.soramitsu.iroha2.generated.SignatureCheckCondition
import jp.co.soramitsu.iroha2.generated.SignedBlock
import jp.co.soramitsu.iroha2.generated.SocketAddr
import jp.co.soramitsu.iroha2.generated.StringWithJson
import jp.co.soramitsu.iroha2.generated.TransactionLimits
import jp.co.soramitsu.iroha2.generated.TransactionQueryOutput
import jp.co.soramitsu.iroha2.generated.TransactionValue
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.TriggerOfTriggeringFilterBox
import jp.co.soramitsu.iroha2.generated.Value
import java.io.ByteArrayOutputStream
import kotlin.reflect.KClass
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
        module.addDeserializer(SequenceExpr::class.java, SequenceExprDeserializer)
        module.addDeserializer(InstructionExpr::class.java, InstructionDeserializer)
        module.addDeserializer(ExecutorMode::class.java, ExecutorDeserializer)
        module.addDeserializer(GrantExpr::class.java, GrantExprDeserializer)
        module.addDeserializer(Value::class.java, ValueDeserializer)
        module.addDeserializer(AssetValue::class.java, AssetValueDeserializer)
        module.addDeserializer(PublicKey::class.java, PublicKeyDeserializer)
        module.addDeserializer(IdBox::class.java, IdBoxDeserializer)
        module.addDeserializer(AssetValueType::class.java, AssetValueTypeDeserializer)
        module.addDeserializer(Name::class.java, NameDeserializer)
        module.addDeserializer(Mintable::class.java, MintableDeserializer)
        module.addDeserializer(DomainId::class.java, DomainIdDeserializer)
        module.addDeserializer(AccountId::class.java, AccountIdDeserializer)
        module.addDeserializer(RoleId::class.java, RoleIdDeserializer)
        module.addDeserializer(AssetDefinitionId::class.java, AssetDefinitionIdDeserializer)
        module.addDeserializer(AssetId::class.java, AssetIdDeserializer)
        module.addDeserializer(RegisterExpr::class.java, RegisterExprDeserializer)
        module.addDeserializer(MintExpr::class.java, MintExprDeserializer)
        module.addDeserializer(SetKeyValueExpr::class.java, SetKeyValueExprDeserializer)
        module.addDeserializer(Metadata::class.java, MetadataDeserializer)
        module.addDeserializer(NewRole::class.java, NewRoleDeserializer)
        module.addDeserializer(NewParameterExpr::class.java, NewParameterExprDeserializer)
        module.addDeserializer(PermissionToken::class.java, PermissionTokenDeserializer)
        module.addDeserializer(StringWithJson::class.java, StringWithJsonDeserializer)
        module.addKeyDeserializer(AssetDefinitionId::class.java, AssetDefinitionIdKeyDeserializer)
        module.addKeyDeserializer(AccountId::class.java, AccountIdKeyDeserializer)
        module.addKeyDeserializer(AssetId::class.java, AssetIdKeyDeserializer)
        module.addKeyDeserializer(DomainId::class.java, DomainIdKeyDeserializer)

        // serializers
        module.addSerializer(RawGenesisBlock::class.java, RawGenesisBlockSerializer)
        module.addSerializer(PermissionToken::class.java, PermissionTokenSerializer)
        module.addKeySerializer(Name::class.java, NameAsKeySerializer)
        module.addSerializer(DomainId::class.java, DomainIdSerializer)
        module.addSerializer(AssetDefinitionId::class.java, AssetDefinitionIdSerializer)
        module.addSerializer(AccountId::class.java, AccountIdSerializer)
        module.addSerializer(AssetId::class.java, AssetIdSerializer)
        module.addSerializer(RoleId::class.java, RoleIdSerializer)
        module.addSerializer(SocketAddr::class.java, SocketAddrSerializer)
        module.addSerializer(TriggerId::class.java, TriggerIdSerializer)
        module.addSerializer(Name::class.java, NameSerializer)
        module.addSerializer(UInt::class.java, UIntSerializer)
        module.addSerializer(PublicKey::class.java, PublicKeySerializer)
        module.addSerializer(ModelEnum::class.java, EnumerationSerializer)
        module.addSerializer(EvaluatesTo::class.java, EvaluatesToSerializer)
        module.addSerializer(Metadata::class.java, MetadataSerializer)
        module.addSerializer(IdentifiableBox.NewRole::class.java, IdentifiableBoxNewRoleSerializer)
        module.addSerializer(Parameter::class.java, ParameterSerializer)
        module.addSerializer(ExecutorMode::class.java, ExecutorModeSerializer)
        module.addSerializer(SequenceExpr::class.java, SequenceExprSerializer)
        module.addSerializer(NewParameterExpr::class.java, NewParameterExprSerializer)
        module.addSerializer(StringWithJson::class.java, StringWithJsonSerializer)

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

private fun sealedDeserializeSequenceExpr(p: JsonParser, mapper: ObjectMapper): SequenceExpr {
    val jsonNodes = p.readValueAsTree<JsonNode>()
    val instructions = jsonNodes.map {
        mapper.convertValue(it, InstructionExpr::class.java) as InstructionExpr
    }
    return SequenceExpr(instructions)
}

private fun sealedDeserializeInstruction(p: JsonParser, mapper: ObjectMapper): InstructionExpr {
    val node = p.readValueAsTree<JsonNode>().fields().next()
    val param = node.key

    val subtype = InstructionExpr::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName == param
    } ?: throw DeserializationException("Class with constructor($param) not found")

    val argTypeName = subtype.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by $param")

    val toConvert: JsonNode = node.value

    val arg = mapper.convertValue(toConvert, argTypeName.asClass())
    return subtype.primaryConstructor?.call(arg) as InstructionExpr
}

private fun sealedDeserializeValidator(p: JsonParser, mapper: ObjectMapper): ExecutorMode {
    return ExecutorMode.Path(p.readValueAsTree<JsonNode>().asText())
}

private fun sealedDeserializeGrantExpr(p: JsonParser, mapper: ObjectMapper): GrantExpr {
    val jsonNode = p.readValueAsTree<JsonNode>()

    val iter = jsonNode.iterator()
    val nodes = mutableListOf<JsonNode>()
    while (iter.hasNext()) {
        val node = iter.next()
        nodes.add(node)
    }

    val node = jsonNode.fields().next()
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

    val subtype = Value::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName?.contains(paramAndValueToConvert.first) ?: false
    } ?: throw DeserializationException("Class with constructor(${paramAndValueToConvert.first}) not found")

    val argTypeName = subtype.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by ${paramAndValueToConvert.first}")

    val grantObject = mapper.convertValue(paramAndValueToConvert.second, argTypeName.asClass())
    val destinationId = mapper.convertValue(destination, IdBox::class.java)
    return GrantExpr(
        `object` = grantObject.evaluatesTo().cast(),
        destinationId = destinationId.evaluatesTo().cast(),
    )
}

private fun sealedDeserializeValue(p: JsonParser, mapper: ObjectMapper): Value {
    val node = p.readValueAsTree<JsonNode>().fields().next()
    val param = when {
        node.key.contains("Id") -> "Id"
        "Numeric" == node.key -> node.value.fields().next().key
        else -> node.key
    }
    val clazz = getClazzByParam(param)

    return when (param) {
        "Bool" -> Value.Bool(node.value.booleanValue())
        "String" -> Value.String(node.value.asText())
        else -> {
            val name = if (node.key == "Id" || node.key == "Numeric") node.value.fields().next().key else node.key
            val value = if (node.key == "Id" || node.key == "Numeric") node.value.fields().next().value else node.value
            val subtype = clazz.nestedClasses.find {
                !it.isCompanion && it.simpleName == name
            } ?: throw DeserializationException("Class with constructor($param) not found")

            val argTypeName = subtype.primaryConstructor?.parameters
                ?.firstOrNull()?.type?.toString()
                ?: throw DeserializationException("Subtype parameter not found by $param")

            val arg = mapper.convertValue(value, argTypeName.asClass())
            return getValueByClazz(clazz, subtype, arg, name, param)
        }
    }
}

private fun getValueByClazz(clazz: KClass<out Any>, subtype: KClass<*>, arg: Any, name: String, param: String): Value {
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
        PermissionToken::class -> Value.PermissionToken(subtype.primaryConstructor?.call(arg) as PermissionToken)
        HashValue::class -> Value.Hash(subtype.primaryConstructor?.call(arg) as HashValue)
        SignedBlock::class -> Value.Block(subtype.primaryConstructor?.call(arg) as SignedBlock)
        BlockHeader::class -> Value.BlockHeader(subtype.primaryConstructor?.call(arg) as BlockHeader)
        Ipv4Addr::class -> Value.Ipv4Addr(subtype.primaryConstructor?.call(arg) as Ipv4Addr)
        Ipv6Addr::class -> Value.Ipv6Addr(subtype.primaryConstructor?.call(arg) as Ipv6Addr)
        NumericValue::class -> {
            when (name) {
                "U32" -> Value.Numeric(subtype.primaryConstructor?.call(arg) as NumericValue.U32)
                "U64" -> Value.Numeric(subtype.primaryConstructor?.call(arg) as NumericValue.U64)
                "U128" -> Value.Numeric(subtype.primaryConstructor?.call(arg) as NumericValue.U128)
                "Fixed" -> Value.Numeric(subtype.primaryConstructor?.call(arg) as NumericValue.Fixed)
                else -> throw DeserializationException("Numeric value $param not found")
            }
        }

        else -> throw DeserializationException("Value type $clazz not found")
    }
}

private fun getClazzByParam(param: String): KClass<out Any> {
    return when (param) {
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
        "TransactionQueryOutput" -> TransactionQueryOutput::class
        "PermissionToken" -> PermissionToken::class
        "Hash" -> Hash::class
        "Block" -> SignedBlock::class
        "BlockHeader" -> BlockHeader::class
        "Ipv4Addr" -> Ipv4Addr::class
        "Ipv6Addr" -> Ipv6Addr::class
        "U32" -> NumericValue::class
        "U64" -> NumericValue::class
        "U128" -> NumericValue::class
        "Fixed" -> NumericValue::class
        else -> throw DeserializationException("Value key $param not found")
    }
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

private fun sealedDeserializeRegisterExpr(p: JsonParser, mapper: ObjectMapper): RegisterExpr {
    val node = p.readValueAsTree<JsonNode>().fields().next()

    val param = node.key.removePrefix("New")
    val subtype = RegistrableBox::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName == param
    }
    val argTypeName = subtype?.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by $param")

    val arg = mapper.convertValue(node.value, argTypeName.asClass())
    return getRegisterExpr(arg)
}

private fun getRegisterExpr(arg: Any): RegisterExpr {
    return when (arg) {
        is NewDomain -> RegisterExpr(RegistrableBox.Domain(arg).evaluatesTo())
        is NewAccount -> RegisterExpr(RegistrableBox.Account(arg).evaluatesTo())
        is Peer -> RegisterExpr(RegistrableBox.Peer(arg).evaluatesTo())
        is NewAssetDefinition -> RegisterExpr(RegistrableBox.AssetDefinition(arg).evaluatesTo())
        is Asset -> RegisterExpr(RegistrableBox.Asset(arg).evaluatesTo())
        is NewRole -> RegisterExpr(RegistrableBox.Role(arg).evaluatesTo())
        is TriggerOfTriggeringFilterBox -> RegisterExpr(RegistrableBox.Trigger(arg).evaluatesTo())
        else -> throw DeserializationException("Register box `$arg` not found")
    }
}

private fun sealedDeserializeMintExpr(p: JsonParser, mapper: ObjectMapper): MintExpr {
    val jsonNode = p.readValueAsTree<JsonNode>()
    val iter = jsonNode.iterator()
    val nodes = mutableListOf<JsonNode>()
    while (iter.hasNext()) {
        val node = iter.next()
        nodes.add(node)
    }

    val newNode = mapper.createObjectNode().set<ObjectNode>(
        jsonNode.fields().next().key,
        jsonNode.fields().next().value,
    )
    val objectId = mapper.convertValue(
        newNode,
        Value::class.java,
    ) as Value
    val destination = mapper.convertValue(
        nodes[1],
        IdBox::class.java,
    ) as IdBox
    return MintExpr(
        `object` = objectId.evaluatesTo().cast(),
        destinationId = destination.evaluatesTo().cast(),
    )
}

private fun sealedDeserializeNewParameterExpr(p: JsonParser, mapper: ObjectMapper): NewParameterExpr {
    val jsonNode = p.readValueAsTree<JsonNode>().fields().next()
    val parameter = jsonNode.value.asText().asParameter()

    return NewParameterExpr(
        parameter = parameter.evaluatesTo().cast(),
    )
}

private fun sealedDeserializeSetKeyValueExpr(p: JsonParser, mapper: ObjectMapper): SetKeyValueExpr {
    val jsonNode = p.readValueAsTree<JsonNode>()
    val iter = jsonNode.iterator()
    val nodes = mutableListOf<JsonNode>()
    while (iter.hasNext()) {
        val node = iter.next()
        nodes.add(node)
    }

    val objectId = jsonNode.fields().next()
    val key = nodes[1].fields().next()
    val subtype = IdBox::class.nestedClasses.find { clazz ->
        !clazz.isCompanion && clazz.simpleName == objectId.key
    }
    val argTypeName = subtype?.primaryConstructor?.parameters
        ?.firstOrNull()?.type?.toString()
        ?: throw DeserializationException("Subtype parameter not found by $objectId")

    val objectIdArg = mapper.convertValue(objectId.value, argTypeName.asClass())
    val keyArg = mapper.convertValue(key.value, Name::class.java)
    val valueArg = mapper.convertValue(nodes[2], Value::class.java)
    return SetKeyValueExpr(
        objectId = objectIdArg.evaluatesTo().cast(),
        key = keyArg.evaluatesTo().cast(),
        value = valueArg.evaluatesTo().cast(),
    )
}

private fun sealedDeserializeNewRole(p: JsonParser, mapper: ObjectMapper): NewRole {
    val iter = p.readValueAsTree<JsonNode>().iterator()
    val nodes = mutableListOf<JsonNode>()
    while (iter.hasNext()) {
        val node = iter.next()
        nodes.add(node)
    }

    val tokens = nodes[1].map {
        mapper.convertValue(it, PermissionToken::class.java) as PermissionToken
    }
    val roleId = RoleId(nodes[0].asText().asName())
    return NewRole(Role(id = roleId, permissions = tokens))
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

/**
 * Deserializer for sequence expr [SequenceExpr]
 */
object SequenceExprDeserializer : JsonDeserializer<SequenceExpr>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SequenceExpr {
        return sealedDeserializeSequenceExpr(p, JSON_SERDE)
    }
}

/**
 * Deserializer for [Iroha Special Instructions][InstructionExpr]
 */
object InstructionDeserializer : JsonDeserializer<InstructionExpr>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): InstructionExpr {
        return sealedDeserializeInstruction(p, JSON_SERDE)
    }
}

/**
 * Deserializer for [ExecutorMode]
 */
object ExecutorDeserializer : JsonDeserializer<ExecutorMode>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ExecutorMode {
        return sealedDeserializeValidator(p, JSON_SERDE)
    }
}

object GrantExprDeserializer : JsonDeserializer<GrantExpr>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): GrantExpr {
        return sealedDeserializeGrantExpr(p, JSON_SERDE)
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

object RegisterExprDeserializer : JsonDeserializer<RegisterExpr>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): RegisterExpr {
        return sealedDeserializeRegisterExpr(p, JSON_SERDE)
    }
}

object MintExprDeserializer : JsonDeserializer<MintExpr>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): MintExpr {
        return sealedDeserializeMintExpr(p, JSON_SERDE)
    }
}

object NewParameterExprDeserializer : JsonDeserializer<NewParameterExpr>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): NewParameterExpr {
        return sealedDeserializeNewParameterExpr(p, JSON_SERDE)
    }
}

object StringWithJsonDeserializer : JsonDeserializer<StringWithJson>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): StringWithJson {
        val node = p.readValueAsTree<JsonNode>().fields().next()

        return StringWithJson(
            string = "{\"${node.key}\":\"${node.value.asText()}\"}",
        )
    }
}

object PermissionTokenDeserializer : JsonDeserializer<PermissionToken>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): PermissionToken {
        val jsonNode = p.readValueAsTree<JsonNode>()
        val iter = jsonNode.iterator()
        val nodes = mutableListOf<JsonNode>()
        while (iter.hasNext()) {
            val node = iter.next()
            nodes.add(node)
        }

        val definitionId = jsonNode.fields().next()
        val payload = when (nodes[1].isNull) {
            true -> StringWithJson("")
            false -> JSON_SERDE.convertValue(nodes[1], StringWithJson::class.java)
        }

        return PermissionToken(
            definitionId = definitionId.value.asText().asName(),
            payload = payload,
        )
    }
}

object SetKeyValueExprDeserializer : JsonDeserializer<SetKeyValueExpr>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SetKeyValueExpr {
        return sealedDeserializeSetKeyValueExpr(p, JSON_SERDE)
    }
}

object MetadataDeserializer : JsonDeserializer<Metadata>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Metadata {
        return sealedDeserializeMetadata(p, JSON_SERDE)
    }
}

object NewRoleDeserializer : JsonDeserializer<NewRole>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): NewRole {
        return sealedDeserializeNewRole(p, JSON_SERDE)
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

/**
 * Serializer for [RawGenesisBlock]
 */
object RawGenesisBlockSerializer : JsonSerializer<RawGenesisBlock>() {
    override fun serialize(block: RawGenesisBlock, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeStartObject()
        when (block.transactions[0].isEmpty()) {
            true -> gen.writeObjectField("transactions", listOf<InstructionExpr>())
            false -> gen.writeObjectField("transactions", block.transactions)
        }
        gen.writeObjectField("executor", block.executor)
        gen.writeEndObject()
    }
}

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
object PermissionTokenSerializer : JsonSerializer<PermissionToken>() {
    override fun serialize(token: PermissionToken, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeStartObject()
        gen.writeObjectField(PermissionToken::definitionId.name.toSnakeCase(), token.definitionId)

        val payload = when (token.payload.string.isEmpty()) {
            true -> null
            false -> token.payload
        }
        gen.writeObjectField(PermissionToken::payload.name, payload)
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
 * Serializer for [SequenceExpr]
 */
object SequenceExprSerializer : JsonSerializer<SequenceExpr>() {
    override fun serialize(value: SequenceExpr, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeStartArray()
        value.instructions.forEach { parameter ->
            serializeSingleMember(gen, parameter)
        }
        gen.writeEndArray()
    }
}

/**
 * Serializer for [NewParameterExpr]
 */
object NewParameterExprSerializer : JsonSerializer<NewParameterExpr>() {
    override fun serialize(value: NewParameterExpr, gen: JsonGenerator, serializers: SerializerProvider) {
        val parameter = value.parameter.expression.cast<Expression.Raw>()
            .value.cast<Value.Identifiable>().identifiableBox.cast<IdentifiableBox.Parameter>().parameter
        gen.writeStartObject()
        gen.writeObjectField(Parameter::class.simpleName, parameter)
        gen.writeEndObject()
    }
}

/**
 * Serializer for [StringWithJson]
 */
object StringWithJsonSerializer : JsonSerializer<StringWithJson>() {
    override fun serialize(value: StringWithJson, gen: JsonGenerator, serializers: SerializerProvider) {
        val tree = ObjectMapper().readTree(value.string)
        val node = tree.fields().next()
        gen.writeStartObject()
        gen.writeObjectField(node.key, node.value.asText())
        gen.writeEndObject()
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
 * Serializer for [ExecutorMode]
 */
object ExecutorModeSerializer : JsonSerializer<ExecutorMode>() {
    override fun serialize(value: ExecutorMode, gen: JsonGenerator, serializers: SerializerProvider) {
        when (value) {
            is ExecutorMode.Path -> gen.writeString(value.string)
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
 * Custom serializer for [Parameter]
 */
object ParameterSerializer : JsonSerializer<Parameter>() {
    override fun serialize(value: Parameter, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.asString())
    }
}

/**
 * Custom serializer for Iroha2 enumeration types
 */
object EnumerationSerializer : JsonSerializer<ModelEnum>() {
    override fun serialize(value: ModelEnum, gen: JsonGenerator, serializers: SerializerProvider) {
        when (value) {
            is InstructionExpr.Grant -> value.serialize(gen)
            is InstructionExpr.Burn -> value.serialize(gen)
            is InstructionExpr.Mint -> value.serialize(gen)
            is InstructionExpr.SetKeyValue -> value.serialize(gen)
            is InstructionExpr.Register -> value.serialize(gen)
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

private fun InstructionExpr.SetKeyValue.serialize(gen: JsonGenerator) = this.serializeExpr<SetKeyValueExpr>(gen)

private fun InstructionExpr.Grant.serialize(gen: JsonGenerator) {
    return this.serializeExpr<GrantExpr>(gen)
}

private fun InstructionExpr.Burn.serialize(gen: JsonGenerator) = this.serializeExpr<BurnExpr>(gen)

private fun InstructionExpr.Mint.serialize(gen: JsonGenerator) = this.serializeExpr<MintExpr>(gen)

private fun InstructionExpr.Register.serialize(gen: JsonGenerator) {
    val clazz = this::class
    val memberProperties = clazz.memberProperties
    when (memberProperties.size) {
        0 -> gen.writeString(clazz.simpleName)
        1 -> {
            gen.writeStartObject()
            gen.writeObjectField(
                clazz.simpleName,
                memberProperties.first().call(this)
                    ?.cast<RegisterExpr>()
                    ?.`object`?.expression,
            )
            gen.writeEndObject()
        }

        else -> throw SerializationException("Expected enum that accepts exactly 0 or 1 members as tuple")
    }
}

/**
 * Serializes BurnExpr, MintExpr, GrantExpr etc...
 */
private inline fun <reified B> InstructionExpr.serializeExpr(gen: JsonGenerator) {
    val clazz = this::class
    val memberProperties = clazz.memberProperties
    when (memberProperties.size) {
        0 -> gen.writeString(clazz.simpleName)
        1 -> {
            gen.writeStartObject()
            gen.writeObjectFieldStart(clazz.simpleName)
            memberProperties.first().call(this)?.cast<B>()?.serializeExpr(gen)
            gen.writeEndObject()
            gen.writeEndObject()
        }

        else -> throw SerializationException("Expected enum that accepts exactly 0 or 1 members as tuple")
    }
}

private inline fun <reified B> B.serializeExpr(
    gen: JsonGenerator,
) = when (B::class) {
    BurnExpr::class -> this?.cast<BurnExpr>()?.serializeExpr(gen)
    MintExpr::class -> this?.cast<MintExpr>()?.serializeExpr(gen)
    GrantExpr::class -> this?.cast<GrantExpr>()?.serializeExpr(gen)
    SetKeyValueExpr::class -> this?.cast<SetKeyValueExpr>()?.serializeExpr(gen)
    else -> throw IrohaSdkException("Unexpected type ${B::class}")
}

private fun BurnExpr.serializeExpr(
    gen: JsonGenerator,
) = mintBurnSerialize(gen, this.`object`.expression, this.destinationId)

private fun MintExpr.serializeExpr(
    gen: JsonGenerator,
) = mintBurnSerialize(gen, this.`object`.expression, this.destinationId)

private fun GrantExpr.serializeExpr(gen: JsonGenerator) {
    val fieldData = when (val rawValue = this.`object`.expression.cast<Expression.Raw>().value) {
        is Value.PermissionToken -> Value.PermissionToken::class.simpleName to rawValue.permissionToken
        is Value.Id -> RoleId::class.simpleName to rawValue.idBox.cast<IdBox.RoleId>().roleId
        else -> throw IrohaSdkException("Grant InstructionExpr serialization error")
    }
    gen.writeObjectField(fieldData.first, fieldData.second)
    gen.writeObjectField("destination_id", this.destinationId)
}

private fun SetKeyValueExpr.serializeExpr(gen: JsonGenerator) {
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
    destinationId: EvaluatesTo<IdBox>,
) {
    val rawValue = expression
        .cast<Expression.Raw>().value
        .cast<Value.Numeric>().numericValue
    val fieldData = when (rawValue) {
        is NumericValue.U32 -> NumericValue.U32::class.simpleName to rawValue.u32
        is NumericValue.U64 -> NumericValue.U64::class.simpleName to rawValue.u64
        is NumericValue.U128 -> NumericValue.U128::class.simpleName to rawValue.u128
        is NumericValue.Fixed -> NumericValue.Fixed::class.simpleName to rawValue.fixed.fixedPointOfI64
        else -> throw IrohaSdkException("Grant InstructionExpr serialization error")
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

private fun String.asClass() = runCatching {
    Class.forName(this)
}.getOrNull() ?: run {
    when (this) {
        "kotlin.Long" -> Long::class.java
        "kotlin.Int" -> Int::class.java
        else -> null
    }
} ?: throw DeserializationException("Class $this not found")
