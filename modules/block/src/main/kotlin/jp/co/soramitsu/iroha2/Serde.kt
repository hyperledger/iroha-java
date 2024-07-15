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
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.ipfs.multihash.Multihash
import io.ktor.util.toUpperCasePreservingASCIIRules
import jp.co.soramitsu.iroha2.DigestFunction.Ed25519
import jp.co.soramitsu.iroha2.RegisterBoxDeserializer.toArg
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.Action
import jp.co.soramitsu.iroha2.generated.Algorithm
import jp.co.soramitsu.iroha2.generated.Asset
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetType
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.BlockHeader
import jp.co.soramitsu.iroha2.generated.BurnBox
import jp.co.soramitsu.iroha2.generated.ChainId
import jp.co.soramitsu.iroha2.generated.CustomInstruction
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.Duration
import jp.co.soramitsu.iroha2.generated.Executable
import jp.co.soramitsu.iroha2.generated.ExecuteTrigger
import jp.co.soramitsu.iroha2.generated.ExecuteTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.ExecutionTime
import jp.co.soramitsu.iroha2.generated.Executor
import jp.co.soramitsu.iroha2.generated.GrantBox
import jp.co.soramitsu.iroha2.generated.GrantOfPermissionAndAccount
import jp.co.soramitsu.iroha2.generated.Hash
import jp.co.soramitsu.iroha2.generated.IdBox
import jp.co.soramitsu.iroha2.generated.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.IpfsPath
import jp.co.soramitsu.iroha2.generated.Ipv4Addr
import jp.co.soramitsu.iroha2.generated.Ipv6Addr
import jp.co.soramitsu.iroha2.generated.Log
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.MintBox
import jp.co.soramitsu.iroha2.generated.MintOfNumericAndAsset
import jp.co.soramitsu.iroha2.generated.Mintable
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.NewAccount
import jp.co.soramitsu.iroha2.generated.NewAssetDefinition
import jp.co.soramitsu.iroha2.generated.NewDomain
import jp.co.soramitsu.iroha2.generated.NewRole
import jp.co.soramitsu.iroha2.generated.Numeric
import jp.co.soramitsu.iroha2.generated.Parameter
import jp.co.soramitsu.iroha2.generated.Peer
import jp.co.soramitsu.iroha2.generated.Permission
import jp.co.soramitsu.iroha2.generated.PublicKey
import jp.co.soramitsu.iroha2.generated.RawGenesisTransaction
import jp.co.soramitsu.iroha2.generated.RegisterBox
import jp.co.soramitsu.iroha2.generated.RegisterOfAccount
import jp.co.soramitsu.iroha2.generated.RegisterOfAsset
import jp.co.soramitsu.iroha2.generated.RegisterOfAssetDefinition
import jp.co.soramitsu.iroha2.generated.RegisterOfDomain
import jp.co.soramitsu.iroha2.generated.RegisterOfPeer
import jp.co.soramitsu.iroha2.generated.RegisterOfRole
import jp.co.soramitsu.iroha2.generated.RegisterOfTrigger
import jp.co.soramitsu.iroha2.generated.RemoveKeyValueBox
import jp.co.soramitsu.iroha2.generated.Repeats
import jp.co.soramitsu.iroha2.generated.RevokeBox
import jp.co.soramitsu.iroha2.generated.Role
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.Schedule
import jp.co.soramitsu.iroha2.generated.SetKeyValueBox
import jp.co.soramitsu.iroha2.generated.SetKeyValueOfAccount
import jp.co.soramitsu.iroha2.generated.SetKeyValueOfAsset
import jp.co.soramitsu.iroha2.generated.SetKeyValueOfAssetDefinition
import jp.co.soramitsu.iroha2.generated.SetKeyValueOfDomain
import jp.co.soramitsu.iroha2.generated.SetKeyValueOfTrigger
import jp.co.soramitsu.iroha2.generated.SetParameter
import jp.co.soramitsu.iroha2.generated.SignedBlock
import jp.co.soramitsu.iroha2.generated.SocketAddr
import jp.co.soramitsu.iroha2.generated.TimeEventFilter
import jp.co.soramitsu.iroha2.generated.TransactionQueryOutput
import jp.co.soramitsu.iroha2.generated.TransferBox
import jp.co.soramitsu.iroha2.generated.Trigger
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.TriggeringEventFilterBox
import jp.co.soramitsu.iroha2.generated.UnregisterBox
import jp.co.soramitsu.iroha2.generated.Upgrade
import jp.co.soramitsu.iroha2.generated.WasmSmartContract
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.util.HashMap
import kotlin.reflect.KClass
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
        module.addDeserializer(AssetValue::class.java, AssetValueDeserializer)
        module.addDeserializer(PublicKey::class.java, PublicKeyDeserializer)
        module.addDeserializer(IdBox::class.java, IdBoxDeserializer)
        module.addDeserializer(Name::class.java, NameDeserializer)
        module.addDeserializer(Mintable::class.java, MintableDeserializer)
        module.addDeserializer(DomainId::class.java, DomainIdDeserializer)
        module.addDeserializer(AccountId::class.java, AccountIdDeserializer)
        module.addDeserializer(RoleId::class.java, RoleIdDeserializer)
        module.addDeserializer(AssetDefinitionId::class.java, AssetDefinitionIdDeserializer)
        module.addDeserializer(AssetId::class.java, AssetIdDeserializer)
        module.addDeserializer(RegisterBox::class.java, RegisterBoxDeserializer)
        module.addDeserializer(MintBox::class.java, MintBoxDeserializer)
        module.addDeserializer(Metadata::class.java, MetadataDeserializer)
        module.addDeserializer(NewRole::class.java, NewRoleDeserializer)
        module.addDeserializer(TriggerId::class.java, TriggerIdDeserializer)
        module.addDeserializer(InstructionBox::class.java, InstructionDeserializer)
        module.addDeserializer(GrantBox::class.java, GrantBoxDeserializer)
        module.addDeserializer(TriggeringEventFilterBox::class.java, TriggeringEventFilterBoxDeserializer)
        module.addDeserializer(SetKeyValueBox::class.java, SetKeyValueBoxDeserializer)
        module.addDeserializer(AssetType::class.java, AssetTypeDeserializer)
        module.addDeserializer(ChainId::class.java, ChainIdDeserializer)
        module.addDeserializer(NewDomain::class.java, NewDomainDeserializer)
//        module.addDeserializer(NewAccount::class.java, NewAccountDeserializer)
        module.addDeserializer(Trigger::class.java, TriggerDeserializer)
        module.addDeserializer(Action::class.java, ActionDeserializer)
        module.addDeserializer(Executable::class.java, ExecutableDeserializer)
        module.addDeserializer(IpfsPath::class.java, IpfsPathDeserializer)
        module.addDeserializer(Repeats::class.java, RepeatsDeserializer)

        module.addKeyDeserializer(AssetDefinitionId::class.java, AssetDefinitionIdKeyDeserializer)
        module.addKeyDeserializer(AccountId::class.java, AccountIdKeyDeserializer)
        module.addKeyDeserializer(AssetId::class.java, AssetIdKeyDeserializer)
        module.addKeyDeserializer(DomainId::class.java, DomainIdKeyDeserializer)

        // serializers
        module.addKeySerializer(Name::class.java, NameAsKeySerializer)
        module.addSerializer(RawGenesisTransaction::class.java, RawGenesisTransactionSerializer)
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
        module.addSerializer(Metadata::class.java, MetadataSerializer)
        module.addSerializer(IdentifiableBox.NewRole::class.java, IdentifiableBoxNewRoleSerializer)
        module.addSerializer(Parameter::class.java, ParameterSerializer)
        module.addSerializer(TimeEventFilter::class.java, TimeEventFilterSerializer)
        module.addSerializer(Schedule::class.java, ScheduleSerializer)
        module.addSerializer(Executor::class.java, ExecutorSerializer)
        module.addSerializer(InstructionBox::class.java, InstructionBoxSerializer)

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
 * Deserializer for [Iroha Special Instructions][InstructionBox]
 */
object InstructionDeserializer : JsonDeserializer<InstructionBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): InstructionBox {
        return sealedDeserializeInstruction(p.readValueAsTree(), JSON_SERDE)
    }
}

/**
 * Deserializer for [ExecutorMode]
 */
// object ExecutorDeserializer : JsonDeserializer<ExecutorMode>() {
//    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ExecutorMode {
//        return sealedDeserializeValidator(p, JSON_SERDE)
//    }
// }

object GrantBoxDeserializer : JsonDeserializer<GrantBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): GrantBox {
        return sealedDeserializeGrantBox(p, JSON_SERDE)
    }

    private fun sealedDeserializeGrantBox(p: JsonParser, mapper: ObjectMapper): GrantBox {
        val jsonNode = p.readValueAsTree<JsonNode>()

//    val iter = jsonNode.iterator()
//    val nodes = mutableListOf<JsonNode>()
//    while (iter.hasNext()) {
//        val node = iter.next()
//        nodes.add(node)
//    }
//
//    val node = jsonNode.fields().next().value.fields().next()
//    val destination = nodes[1]
//    val paramAndValueToConvert = if (RoleId::class.java.simpleName == node.key) {
//        Pair(
//            "Id",
//            mapper.createObjectNode().set<ObjectNode>(
//                jsonNode.fields().next().key,
//                jsonNode.fields().next().value,
//            ),
//        )
//    } else {
//        Pair(node.key, node.value)
//    }

//    val subtype = Value::class.nestedClasses.find { clazz ->
//        !clazz.isCompanion && clazz.simpleName?.contains(paramAndValueToConvert.first) ?: false
//    } ?: throw DeserializationException("Class with constructor(${paramAndValueToConvert.first}) not found")
//
//    val argTypeName = subtype.primaryConstructor?.parameters
//        ?.firstOrNull()?.type?.toString()
//        ?: throw DeserializationException("Subtype parameter not found by ${paramAndValueToConvert.first}")
//
//    val grantObject = mapper.convertValue(paramAndValueToConvert.second, argTypeName.asClass())
//    val destinationId = mapper.convertValue(destination, IdBox::class.java)
//    return GrantBox(
//        `object` = grantObject.evaluatesTo().cast(),
//        destinationId = destinationId.evaluatesTo().cast(),
//    )
        return GrantBox.Permission(
            GrantOfPermissionAndAccount(
                Permission(Permissions.CanBurnAssetWithDefinition.type, ""),
                "qwe".asAccountId(),
            ),
        )
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

    private fun sealedDeserializeRegisterBox(p: JsonParser, mapper: ObjectMapper): RegisterBox {
        val node = p.readValueAsTree<JsonNode>().fields().next()

        val paramClass = node.key.toArg()
        val value = mapper.convertValue(node.value, paramClass)

        return getRegisterBox(value)
    }

    private fun String.toArg(): Class<*> {
        return when (this) {
            "Domain" -> NewDomain::class.java
            "Peer" -> Peer::class.java
            "Account" -> NewAccount::class.java
            "AssetDefinition" -> NewAssetDefinition::class.java
            "Asset" -> Asset::class.java
            "Role" -> NewRole::class.java
            "Trigger" -> Trigger::class.java
            else -> throw DeserializationException("Unknown type: $this")
        }
    }

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
}

object MintBoxDeserializer : JsonDeserializer<MintBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): MintBox {
        return deserializeMintBox(p, JSON_SERDE)
    }

    private fun deserializeMintBox(p: JsonParser, mapper: ObjectMapper): MintBox {
        val jsonNode = p.readValueAsTree<JsonNode>()
        val iter = jsonNode.iterator()
        val nodes = mutableListOf<JsonNode>()
        while (iter.hasNext()) {
            val node = iter.next()
            nodes.add(node)
        }
        val numericTypeAndValue = jsonNode.fields().next().value.asText().split("_")
        val newNode = mapper.createObjectNode().set<ObjectNode>(
            numericTypeAndValue[1].toUpperCasePreservingASCIIRules(),
            IntNode(numericTypeAndValue[0].toInt()),
        )
        return MintBox.Asset(MintOfNumericAndAsset(0.asNumeric(), AssetId(AssetDefinitionId("".asDomainId(), "".asName()), AccountId("".asDomainId(), PublicKey(Algorithm.Ed25519(), byteArrayOf())))))
    }
}

object TriggeringEventFilterBoxDeserializer : JsonDeserializer<TriggeringEventFilterBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): TriggeringEventFilterBox {
        val node = p.readValueAsTree<JsonNode>().fields().next()
        val paramClass = node.key.toArg()
        val value = JSON_SERDE.convertValue(node.value, paramClass)

        return TriggeringEventFilterBox.ExecuteTrigger(
            executeTriggerEventFilter = value as ExecuteTriggerEventFilter,
        )
    }

    private fun String.toArg(): Class<*> {
        return when (this) {
            "ExecuteTrigger" -> ExecuteTriggerEventFilter::class.java
            else -> throw DeserializationException("Unknown type `$this`")
        }
    }
}

object TriggerIdDeserializer : JsonDeserializer<TriggerId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): TriggerId {
        val triggerName = p.readValueAsTree<JsonNode>().asText()
        return getTriggerId(triggerName)
    }
}

object SetKeyValueBoxDeserializer : JsonDeserializer<SetKeyValueBox>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SetKeyValueBox {
        return deserializeSetKeyValueBox(p, JSON_SERDE)
    }

    private fun deserializeSetKeyValueBox(p: JsonParser, mapper: ObjectMapper): SetKeyValueBox {
        val node = p.readValueAsTree<JsonNode>()
        val paramClass = node.fields().next().key.toSetKeyValueArg()
        val value = mapper.convertValue(node.fields().next().value, paramClass)

        return getSetKeyValueBox(value)
    }

    private fun String.toSetKeyValueArg(): Class<*> {
        return when (this) {
            "Domain" -> SetKeyValueOfDomain::class.java
            "Account" -> SetKeyValueOfAccount::class.java
            "Asset" -> SetKeyValueOfAsset::class.java
            "AssetDefinition" -> SetKeyValueOfAssetDefinition::class.java
            "Trigger" -> SetKeyValueOfTrigger::class.java
            else -> throw DeserializationException("Unknown type: $this")
        }
    }

    private fun getSetKeyValueBox(arg: Any): SetKeyValueBox {
        return when (arg) {
            is SetKeyValueOfDomain -> SetKeyValueBox.Domain(arg)
            is SetKeyValueOfAccount -> SetKeyValueBox.Account(arg)
            is SetKeyValueOfAsset -> SetKeyValueBox.Asset(arg)
            is SetKeyValueOfAssetDefinition -> SetKeyValueBox.AssetDefinition(arg)
            is SetKeyValueOfTrigger -> SetKeyValueBox.Trigger(arg)
            else -> throw DeserializationException("SetKeyValue box `$arg` not found")
        }
    }
}

/**
 * Deserializer for [Metadata]
 */
object MetadataDeserializer : JsonDeserializer<Metadata>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Metadata {
        return deserializeMetadata(p, JSON_SERDE)
    }

    private fun deserializeMetadata(p: JsonParser, mapper: ObjectMapper): Metadata {
        val nodeMetadata = p.readValueAsTree<JsonNode>().fields()
        if (!nodeMetadata.hasNext()) {
            return Metadata(mapOf())
        }
        val node = nodeMetadata.next()
        val key = node.key.asName()
        val value = node.value.asText()
        return Metadata(mapOf(Pair(key, value)))
    }
}

/**
 * Deserializer for [NewRole]
 */
object NewRoleDeserializer : JsonDeserializer<NewRole>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): NewRole {
        return deserializeNewRole(p, JSON_SERDE)
    }

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
}

/**
 * Deserializer for [AssetType]
 */
object AssetTypeDeserializer : JsonDeserializer<AssetType>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): AssetType {
        val text = p.readValueAs(String::class.java)
        return AssetType::class.nestedClasses
            .findLast { it.simpleName == text }
            ?.createInstance() as AssetType?
            ?: throw DeserializationException("AssetType $text not found")
    }
}

/**
 * Deserializer for [ChainId]
 */
object ChainIdDeserializer : JsonDeserializer<ChainId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ChainId {
        return ChainId(p.readValueAs(String::class.java))
    }
}

/**
 * Deserializer for [NewDomain]
 */
object NewDomainDeserializer : JsonDeserializer<NewDomain>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): NewDomain {
        val node = p.readValueAsTree<JsonNode>()
        val domainId = node["id"].asText().asDomainId()
        val logo = JSON_SERDE.convertValue(node["logo"], IpfsPath::class.java)
        val metadata = JSON_SERDE.convertValue(node["metadata"], Metadata::class.java)

        return NewDomain(domainId, logo, metadata)
    }
}

/**
 * Deserializer for [Trigger]
 */
object TriggerDeserializer : JsonDeserializer<Trigger>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Trigger {
        val node = p.readValueAsTree<JsonNode>()
        val triggerId = TriggerId(node["id"].asText().asName())
        val action = JSON_SERDE.convertValue(node["action"], Action::class.java)

        return Trigger(triggerId, action)
    }
}

/**
 * Deserializer for [Action]
 */
object ActionDeserializer : JsonDeserializer<Action>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Action {
        val node = p.readValueAsTree<JsonNode>()
        val executable = JSON_SERDE.convertValue(node["executable"], Executable::class.java)
        val repeats = JSON_SERDE.convertValue(node["repeats"], Repeats::class.java)
        val authority = JSON_SERDE.convertValue(node["authority"], AccountId::class.java)
        val filter = JSON_SERDE.convertValue(node["filter"], TriggeringEventFilterBox::class.java)
        val metadata = JSON_SERDE.convertValue(node["metadata"], Metadata::class.java)

        return Action(executable, repeats, authority, filter, metadata)
    }
}

/**
 * Deserializer for [Executable]
 */
object ExecutableDeserializer : JsonDeserializer<Executable>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Executable {
        val node = p.readValueAsTree<JsonNode>().fields().next()

        val paramClass = node.key.toArg()
        var value = JSON_SERDE.convertValue(node.value, paramClass)
        if (value is List<*>) {
            value = value.cast<List<HashMap<String, HashMap<String, *>>>>().map { map ->
                map.map { (k, v) ->
                    val instruction = JSON_SERDE.convertValue(v, getClazzByParam(k).java)
                    instruction.toInstructionBox()
                }
            }
        }

        return getExecutable(value)
    }

    private fun String.toArg(): Class<*> {
        return when (this) {
            "Wasm" -> WasmSmartContract::class.java
            "Instructions" -> List::class.java
            else -> throw DeserializationException("Unknown type: $this")
        }
    }

    private fun getExecutable(arg: Any): Executable {
        return when (arg) {
            is WasmSmartContract -> Executable.Wasm(arg)
            is List<*> -> Executable.Instructions(arg as List<InstructionBox>)
            else -> throw DeserializationException("Executable `$arg` not found")
        }
    }
}

/**
 * Deserializer for [IpfsPath]
 */
object IpfsPathDeserializer : JsonDeserializer<IpfsPath>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): IpfsPath {
        return IpfsPath(p.readValueAs(String::class.java))
    }
}

/**
 * Deserializer for [Repeats]
 */
object RepeatsDeserializer : JsonDeserializer<Repeats>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Repeats {
        val node = p.readValueAsTree<JsonNode>().fields().next()
        return JSON_SERDE.convertValue(node.value, node.key.toArg()).get()
    }

    private fun String.toArg(): Class<*> {
        return when (this) {
            "Exactly" -> Long::class.java
            "Indefinitely" -> Unit::class.java
            else -> throw DeserializationException("Unknown type: $this")
        }
    }

    private fun Any.get(): Repeats {
        return when (this) {
            is Long -> Repeats.Exactly(this)
            is Unit -> Repeats.Indefinitely()
            else -> throw DeserializationException("Unknown type: $this")
        }
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
        return Name(p.readValueAs(String::class.java))
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
 * Serializer for [RawGenesisTransaction]
 */
object RawGenesisTransactionSerializer : JsonSerializer<RawGenesisTransaction>() {
    override fun serialize(tx: RawGenesisTransaction, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeStartObject()
        when (tx.instructions.isEmpty()) {
            true -> gen.writeObjectField("instructions", listOf<InstructionBox>())
            false -> gen.writeObjectField("instructions", tx.instructions)
        }
        gen.writeObjectField("executor", tx.executor)
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
 * Serializer for [Executor]
 */
object ExecutorSerializer : JsonSerializer<Executor>() {
    override fun serialize(value: Executor, gen: JsonGenerator, serializers: SerializerProvider) {
        when (value) {
//            is Executor -> gen.writeString(value.wasm)
            else -> throw IrohaSdkException("Unsupported type ${this::class}")
        }
    }
}

/**
 * Serializer for [InstructionBox]
 */
object InstructionBoxSerializer : JsonSerializer<InstructionBox>() {
    override fun serialize(value: InstructionBox, gen: JsonGenerator, serializers: SerializerProvider) {
        when (value) {
//            is Executor -> gen.writeString(value.wasm)
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
object ParameterSerializer : JsonSerializer<Parameter>() {
    override fun serialize(value: Parameter, gen: JsonGenerator, serializers: SerializerProvider) {
        when (value) {
            is Parameter.Block -> gen.writeObject(value.blockParameter) // TODO
            is Parameter.Custom -> gen.writeObject(value.customParameter)
            is Parameter.Executor -> gen.writeObject(value.smartContractParameter)
            is Parameter.SmartContract -> gen.writeObject(value.smartContractParameter)
            is Parameter.Sumeragi -> gen.writeObject(value.sumeragiParameter)
            is Parameter.Transaction -> gen.writeObject(value.transactionParameter)
        }
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
            else -> serializeSingleMember(gen, value)
        }
    }
}

private fun InstructionBox.SetKeyValue.serialize(gen: JsonGenerator) = this.serializeBox<SetKeyValueBox>(gen)

private fun InstructionBox.Grant.serialize(gen: JsonGenerator) = this.serializeBox<GrantBox>(gen)

private fun InstructionBox.Burn.serialize(gen: JsonGenerator) = this.serializeBox<BurnBox>(gen)

private fun InstructionBox.Mint.serialize(gen: JsonGenerator) = this.serializeBox<MintBox>(gen)

private fun InstructionBox.Register.serialize(gen: JsonGenerator) = this.serializeBox<RegisterBox>(gen)

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
    gen: JsonGenerator,
) = when (B::class) {
    BurnBox::class -> this?.cast<BurnBox>()?.serializeBox(gen)
    MintBox::class -> this?.cast<MintBox>()?.serializeBox(gen)
    GrantBox::class -> this?.cast<GrantBox>()?.serializeBox(gen)
    SetKeyValueBox::class -> this?.cast<SetKeyValueBox>()?.serializeBox(gen)
    RegisterBox::class -> this?.cast<RegisterBox>()?.serializeBox(gen)
    else -> throw IrohaSdkException("Unexpected type ${B::class}")
}

private fun BurnBox.serializeBox(gen: JsonGenerator) {
    when (this) {
        is BurnBox.Asset -> gen.writeObject(this.burnOfNumericAndAsset) // TODO
        is BurnBox.TriggerRepetitions -> gen.writeObject(this.burnOfu32AndTrigger)
    }
}

private fun MintBox.serializeBox(gen: JsonGenerator) {
    when (this) {
        is MintBox.Asset -> gen.writeObject(this.mintOfNumericAndAsset) // TODO
        is MintBox.TriggerRepetitions -> gen.writeObject(this.mintOfu32AndTrigger)
    }
}

private fun GrantBox.serializeBox(gen: JsonGenerator) {
    when (this) {
        is GrantBox.Permission -> gen.writeObject(this.grantOfPermissionAndAccount) // TODO
        is GrantBox.Role -> gen.writeObject(this.grantOfRoleIdAndAccount)
        is GrantBox.RolePermission -> gen.writeObject(this.grantOfPermissionAndRole)
    }
}

private fun RegisterBox.serializeBox(gen: JsonGenerator) {
    val clazz = this::class
    val memberProperties = clazz.memberProperties
    when (memberProperties.size) {
        0 -> gen.writeString(clazz.simpleName)
        1 -> {
            gen.writeStartObject()
            gen.writeObjectField(
                clazz.simpleName,
                memberProperties.first().call(this)?.cast<RegisterBox>(), // TODO
            )
            gen.writeEndObject()
        }

        else -> throw SerializationException("Expected enum that accepts exactly 0 or 1 members as tuple")
    }
}

private fun SetKeyValueBox.serializeBox(gen: JsonGenerator) {
    when (this) {
        is SetKeyValueBox.Account -> gen.writeObject(this.setKeyValueOfAccount) // TODO
        is SetKeyValueBox.Trigger -> gen.writeObject(this.setKeyValueOfTrigger)
        is SetKeyValueBox.Asset -> gen.writeObject(this.setKeyValueOfAsset)
        is SetKeyValueBox.Domain -> gen.writeObject(this.setKeyValueOfDomain)
        is SetKeyValueBox.AssetDefinition -> gen.writeObject(this.setKeyValueOfAssetDefinition)
    }
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

private fun getClazzByParam(param: String): KClass<out Any> {
    return when (param) {
        "SetKeyValue" -> SetKeyValueBox::class
        "Bool" -> Boolean::class
        "String" -> String::class
        "Name" -> Name::class
        "LimitedMetadata" -> Metadata::class
        "Id" -> IdBox::class
        "Identifiable" -> IdentifiableBox::class
        "PublicKey" -> PublicKey::class
        "TransactionQueryOutput" -> TransactionQueryOutput::class
        "Hash" -> Hash::class
        "Block" -> SignedBlock::class
        "BlockHeader" -> BlockHeader::class
        "Ipv4Addr" -> Ipv4Addr::class
        "Ipv6Addr" -> Ipv6Addr::class
        "U32" -> Numeric::class
        "U64" -> Numeric::class
        "U128" -> Numeric::class
        "Fixed" -> Numeric::class
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

private fun getTriggerAuthority(triggerAction: JsonNode) = triggerAction.get("authority").asText().asAccountId()

private fun getTriggerId(triggerName: String): TriggerId {
    return when (triggerName.contains("$")) {
        true -> TriggerId(name = triggerName.split("$")[0].asName())
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
                false -> Duration(
                    u64 = BigInteger.valueOf(period.get("secs").asLong()),
                    u32 = period.get("nanos").asLong(),
                )
            }
            TriggeringEventFilterBox.Time(
                TimeEventFilter(
                    ExecutionTime.Schedule(
                        Schedule(
                            Duration(
                                u64 = BigInteger.valueOf(start.get("secs").asLong()),
                                u32 = start.get("nanos").asLong(),
                            ),
                            periodDuration,
                        ),
                    ),
                ),
            )
        }

        "Pipeline" -> {
            throw IrohaSdkException("${filterNode.key} is not supported")
        }

        else -> {
            throw IrohaSdkException("${filterNode.key} is not supported")
        }
    }
}

private fun Any.toInstructionBox(): InstructionBox {
    return when (this) {
        is GrantBox -> InstructionBox.Grant(this)
        is RevokeBox -> InstructionBox.Revoke(this)
        is ExecuteTrigger -> InstructionBox.ExecuteTrigger(this)
        is SetParameter -> InstructionBox.SetParameter(this)
        is Upgrade -> InstructionBox.Upgrade(this)
        is Log -> InstructionBox.Log(this)
        is CustomInstruction -> InstructionBox.Custom(this)
        is SetKeyValueBox -> InstructionBox.SetKeyValue(this)
        is RemoveKeyValueBox -> InstructionBox.RemoveKeyValue(this)
        is TransferBox -> InstructionBox.Transfer(this)
        is BurnBox -> InstructionBox.Burn(this)
        is MintBox -> InstructionBox.Mint(this)
        is RegisterBox -> InstructionBox.Register(this)
        is UnregisterBox -> InstructionBox.Unregister(this)
        else -> throw DeserializationException("Unknown type: $this")
    }
}
