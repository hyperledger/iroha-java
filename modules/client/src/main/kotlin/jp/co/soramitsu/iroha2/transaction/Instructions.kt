package jp.co.soramitsu.iroha2.transaction

import jp.co.soramitsu.iroha2.CAN_BURN_ASSET_WITH_DEFINITION
import jp.co.soramitsu.iroha2.CAN_BURN_USER_ASSETS_TOKEN
import jp.co.soramitsu.iroha2.CAN_MINT_USER_ASSETS_DEFINITION
import jp.co.soramitsu.iroha2.CAN_MINT_USER_ASSET_DEFINITIONS_TOKEN
import jp.co.soramitsu.iroha2.CAN_REGISTER_DOMAINS_TOKEN
import jp.co.soramitsu.iroha2.CAN_REMOVE_KEY_VALUE_IN_ASSET_DEFINITION
import jp.co.soramitsu.iroha2.CAN_REMOVE_KEY_VALUE_IN_USER_ASSETS
import jp.co.soramitsu.iroha2.CAN_REMOVE_KEY_VALUE_IN_USER_METADATA
import jp.co.soramitsu.iroha2.CAN_SET_KEY_VALUE_IN_ASSET_DEFINITION
import jp.co.soramitsu.iroha2.CAN_SET_KEY_VALUE_IN_USER_METADATA
import jp.co.soramitsu.iroha2.CAN_SET_KEY_VALUE_USER_ASSETS_TOKEN
import jp.co.soramitsu.iroha2.CAN_TRANSFER_USER_ASSETS_TOKEN
import jp.co.soramitsu.iroha2.CAN_UNREGISTER_ASSET_WITH_DEFINITION
import jp.co.soramitsu.iroha2.DigestFunction
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.cast
import jp.co.soramitsu.iroha2.evaluatesTo
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Mintable
import jp.co.soramitsu.iroha2.generated.datamodel.domain.IpfsPath
import jp.co.soramitsu.iroha2.generated.datamodel.domain.NewDomain
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.isi.BurnBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.ExecuteTriggerBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.FailBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.GrantBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.If
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.isi.MintBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Pair
import jp.co.soramitsu.iroha2.generated.datamodel.isi.RegisterBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.RemoveKeyValueBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.SequenceBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.SetKeyValueBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.TransferBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.UnregisterBox
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.peer.Peer
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import jp.co.soramitsu.iroha2.generated.datamodel.role.Role
import jp.co.soramitsu.iroha2.generated.dataprimitives.fixed.Fixed
import jp.co.soramitsu.iroha2.toValueId
import java.math.BigDecimal
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id as DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.peer.Id as PeerId
import jp.co.soramitsu.iroha2.generated.datamodel.role.Id as RoleId
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id as TriggerId

val ACCOUNT_ID_TOKEN_PARAM_NAME by lazy { "account_id".asName() }
val ASSET_ID_TOKEN_PARAM_NAME by lazy { "asset_id".asName() }
val ASSET_DEFINITION_PARAM_NAME by lazy { "asset_definition_id".asName() }

object Instructions {

    /**
     * Instruction for role registration
     */
    fun registerRole(
        roleId: RoleId,
        vararg tokens: PermissionToken
    ): Instruction.Register {
        return registerSome {
            IdentifiableBox.Role(
                Role(roleId, tokens.toList())
            )
        }
    }

    /**
     * Instruction for account registration
     */
    fun registerAccount(
        id: AccountId,
        signatories: List<PublicKey>,
        metadata: Metadata = Metadata(mapOf())
    ): Instruction.Register {
        return registerSome {
            IdentifiableBox.NewAccount(
                NewAccount(id, signatories, metadata)
            )
        }
    }

//    /**
//     * Instruction for time trigger registration
//     */
//    fun registerTimeTrigger(
//        triggerId: jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id,
//        isi: List<Instruction>,
//        repeats: Repeats,
//        accountId: jp.co.soramitsu.iroha2.generated.datamodel.account.Id,
//        schedule: Schedule,
//        metadata: Metadata
//    ): Instruction {
//        return registerSome {
//            IdentifiableBox.Trigger(
//                Trigger(
//                    triggerId,
//                    Action(
//                        Executable.Instructions(isi),
//                        repeats,
//                        accountId,
//                        EventFilter.Time(
//                            TimeEventFilter(
//                                ExecutionTime.Schedule(schedule)
//                            )
//                        )
//                    ),
//                    metadata
//                )
//            )
//        }
//    }

//    /**
//     * Instruction for executable trigger registration
//     */
//    fun registerExecutableTrigger(
//        triggerId: TriggerId,
//        isi: List<Instruction>,
//        repeats: Repeats,
//        accountId: AccountId,
//        metadata: Metadata
//    ): Instruction.Register {
//        return registerSome {
//            IdentifiableBox.Trigger(
//                Trigger(
//                    triggerId,
//                    Action(
//                        Executable.Instructions(isi),
//                        repeats,
//                        accountId,
//                        EventFilter.ExecuteTrigger(
//                            ExecutableEventFilter(triggerId, accountId)
//                        )
//                    ),
//                    metadata
//                )
//            )
//        }
//    }

//    /**
//     * Instruction for data trigger registration
//     */
//    fun registerDataCreatedEventTrigger(
//        triggerId: TriggerId,
//        isi: List<Instruction>,
//        repeats: Repeats,
//        accountId: AccountId,
//        metadata: Metadata,
//        filter: FilterOptEntityFilter
//    ): Instruction.Register {
//        return registerSome {
//            IdentifiableBox.Trigger(
//                Trigger(
//                    triggerId,
//                    Action(
//                        Executable.Instructions(isi),
//                        repeats,
//                        accountId,
//                        EventFilter.Data(filter)
//                    ),
//                    metadata
//                )
//            )
//        }
//    }

//    /**
//     * Instruction for pre commit trigger to run after every transaction
//     */
//    fun registerPreCommitTrigger(
//        triggerId: TriggerId,
//        isi: List<Instruction>,
//        repeats: Repeats,
//        accountId: AccountId,
//        metadata: Metadata
//    ): Instruction.Register {
//        return registerSome {
//            IdentifiableBox.Trigger(
//                Trigger(
//                    triggerId,
//                    Action(
//                        Executable.Instructions(isi),
//                        repeats,
//                        accountId,
//                        EventFilter.Time(
//                            TimeEventFilter(ExecutionTime.PreCommit())
//                        )
//                    ),
//                    metadata
//                )
//            )
//        }
//    }

    /**
     * Instruction for asset registration
     */
    fun registerAsset(
        id: DefinitionId,
        assetValueType: AssetValueType,
        metadata: Metadata = Metadata(mapOf()),
        mintable: Mintable = Mintable.Infinitely()
    ): Instruction.Register {
        return registerSome {
            IdentifiableBox.AssetDefinition(
                AssetDefinition(id, assetValueType, mintable, metadata)
            )
        }
    }

    /**
     * Instruction for domain registration
     */
    fun registerDomain(
        domainId: DomainId,
        metadata: Map<Name, Value> = mapOf(),
        logo: IpfsPath? = null
    ): Instruction.Register {
        return registerSome {
            IdentifiableBox.NewDomain(
                NewDomain(
                    domainId,
                    logo,
                    Metadata(metadata)
                )
            )
        }
    }

    /**
     * Instruction for peer registration
     */
    fun registerPeer(
        address: String,
        payload: ByteArray,
        digestFunction: String = DigestFunction.Ed25519.hashFunName
    ): Instruction.Register {
        return registerSome {
            IdentifiableBox.Peer(
                Peer(
                    PeerId(
                        address,
                        PublicKey(digestFunction, payload)
                    )
                )
            )
        }
    }

    /**
     * Instruction to unregister peer
     */
    fun unregisterPeer(
        address: String,
        payload: ByteArray,
        digestFunction: String = DigestFunction.Ed25519.hashFunName
    ): Instruction.Unregister {
        return unregisterSome {
            IdBox.PeerId(
                PeerId(
                    address,
                    PublicKey(digestFunction, payload)
                )
            )
        }
    }

    /**
     * Instruction to set key value at the object
     */
    fun setKeyValue(
        assetId: AssetId,
        key: Name,
        value: Value
    ): Instruction.SetKeyValue {
        return Instruction.SetKeyValue(
            SetKeyValueBox(
                objectId = IdBox.AssetId(assetId).evaluatesTo(),
                key = key.evaluatesTo(),
                value = value.evaluatesTo()
            )
        )
    }

    /**
     * Instruction to set key value at the account's metadata
     */
    fun setKeyValue(
        accountId: AccountId,
        key: Name,
        value: Value
    ): Instruction.SetKeyValue {
        return Instruction.SetKeyValue(
            SetKeyValueBox(
                objectId = IdBox.AccountId(accountId).evaluatesTo(),
                key = key.evaluatesTo(),
                value = value.evaluatesTo()
            )
        )
    }

    /**
     * Instruction to remove key value at the object
     */
    fun removeKeyValue(assetId: AssetId, key: Name): Instruction.RemoveKeyValue {
        return Instruction.RemoveKeyValue(
            RemoveKeyValueBox(
                objectId = IdBox.AssetId(assetId).evaluatesTo(),
                key = key.evaluatesTo()
            )
        )
    }

    fun executeTrigger(triggerId: TriggerId): Instruction.ExecuteTrigger {
        return Instruction.ExecuteTrigger(ExecuteTriggerBox(triggerId))
    }

    /**
     * Instruction for mint of an asset with [AssetValueType] is [AssetValueType.Quantity]
     */
    fun mintAsset(
        assetId: AssetId,
        quantity: Long
    ): Instruction.Mint {
        return mintSome(
            Value.U32(quantity),
            assetId
        )
    }

    /**
     * Instruction for mint of an asset with [AssetValueType] is [AssetValueType.Fixed]
     */
    fun mintAsset(
        assetId: AssetId,
        quantity: BigDecimal
    ): Instruction.Mint {
        return mintSome(
            Value.Fixed(Fixed(quantity)),
            assetId
        )
    }

    /**
     * Instruction for mint of a public key
     */
    fun mintPublicKey(accountId: AccountId, pubKey: PublicKey): Instruction {
        return mintSome(
            Value.PublicKey(pubKey),
            IdBox.AccountId(accountId)
        )
    }

    /**
     * Instruction for burn of an asset with [AssetValueType] is [AssetValueType.Quantity]
     */
    fun burnAsset(assetId: AssetId, value: Long): Instruction {
        return burnSome(
            Value.U32(value),
            IdBox.AssetId(assetId)
        )
    }

    /**
     * Instruction for burn of an asset with [AssetValueType] is [AssetValueType.Fixed]
     */
    fun burnAsset(assetId: AssetId, value: BigDecimal): Instruction {
        return burnSome(
            Value.Fixed(Fixed(value)),
            IdBox.AssetId(assetId)
        )
    }

    /**
     * Instruction for burn of a public key
     */
    fun burnPublicKey(accountId: AccountId, pubKey: PublicKey): Instruction {
        return burnSome(
            Value.PublicKey(pubKey),
            IdBox.AccountId(accountId)
        )
    }

    fun removePublicKey(accountId: AccountId, pubKey: PublicKey) = burnPublicKey(accountId, pubKey)

    /**
     * Instruction for granting [CAN_SET_KEY_VALUE_USER_ASSETS_TOKEN] permission to an account
     */
    fun grantSetKeyValueAsset(assetId: AssetId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_SET_KEY_VALUE_USER_ASSETS_TOKEN,
                params = mapOf(ASSET_ID_TOKEN_PARAM_NAME to assetId.toValueId())
            )
        }
    }

    /**
     * Instruction for granting [CAN_REMOVE_KEY_VALUE_IN_USER_ASSETS] permission to an account
     */
    fun grantRemoveKeyValueAsset(assetId: AssetId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_REMOVE_KEY_VALUE_IN_USER_ASSETS,
                params = mapOf(ASSET_ID_TOKEN_PARAM_NAME to assetId.toValueId())
            )
        }
    }

    /**
     * Instruction for granting [CAN_SET_KEY_VALUE_IN_USER_METADATA] permission to an account
     */
    fun grantSetKeyValueMetadata(accountId: AccountId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_SET_KEY_VALUE_IN_USER_METADATA,
                params = mapOf(ACCOUNT_ID_TOKEN_PARAM_NAME to accountId.toValueId())
            )
        }
    }

    /**
     * Instruction for granting [CAN_REMOVE_KEY_VALUE_IN_USER_METADATA] permission to an account
     */
    fun grantRemoveKeyValueMetadata(accountId: AccountId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_REMOVE_KEY_VALUE_IN_USER_METADATA,
                params = mapOf(ACCOUNT_ID_TOKEN_PARAM_NAME to accountId.toValueId())
            )
        }
    }

    /**
     * Instruction for granting [CAN_SET_KEY_VALUE_IN_ASSET_DEFINITION] permission to an account
     */
    fun grantSetKeyValueAssetDefinition(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_SET_KEY_VALUE_IN_ASSET_DEFINITION,
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    /**
     * Instruction for granting [CAN_REMOVE_KEY_VALUE_IN_ASSET_DEFINITION] permission to an account
     */
    fun grantRemoveKeyValueAssetDefinition(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_REMOVE_KEY_VALUE_IN_ASSET_DEFINITION,
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    /**
     * Instruction for granting [CAN_MINT_USER_ASSET_DEFINITIONS_TOKEN] permission to an account
     */
    fun grantMintUserAssetsDefinitions(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_MINT_USER_ASSET_DEFINITIONS_TOKEN,
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    /**
     * Instruction for granting [CAN_MINT_USER_ASSETS_DEFINITION] permission to an account
     */
    fun grantMintUserAssetsDefinition(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_MINT_USER_ASSETS_DEFINITION,
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    /**
     * Instruction for granting [CAN_BURN_ASSET_WITH_DEFINITION] permission to an account
     */
    fun grantBurnAssetWithDefinitionId(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_BURN_ASSET_WITH_DEFINITION,
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    /**
     * Instruction for granting [CAN_BURN_USER_ASSETS_TOKEN] permission to an account
     */
    fun grantBurnAssets(assetId: AssetId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_BURN_USER_ASSETS_TOKEN,
                params = mapOf(
                    ASSET_ID_TOKEN_PARAM_NAME to assetId.toValueId()
                )
            )
        }
    }

    /**
     * Instruction for granting [CAN_REGISTER_DOMAINS_TOKEN] permission to an account
     */
    fun grantRegisterDomains(target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_REGISTER_DOMAINS_TOKEN,
                params = emptyMap()
            )
        }
    }

    /**
     * Instruction for granting [CAN_TRANSFER_USER_ASSETS_TOKEN] permission to an account
     */
    fun grantTransferUserAsset(assetId: AssetId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_TRANSFER_USER_ASSETS_TOKEN,
                params = mapOf(
                    ASSET_ID_TOKEN_PARAM_NAME to assetId.toValueId()
                )
            )
        }
    }

    /**
     * Instruction for granting [CAN_UNREGISTER_ASSET_WITH_DEFINITION] permission to an account
     */
    fun grantUnregisterAssetDefinition(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_UNREGISTER_ASSET_WITH_DEFINITION,
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    fun grantRole(roleId: RoleId, accountId: AccountId): Instruction {
        return Instruction.Grant(
            GrantBox(
                destinationId = IdBox.RoleId(roleId).evaluatesTo(),
                `object` = accountId.evaluatesTo().cast()
            )
        )
    }

    /**
     * Instruction for a transfer of an asset from the identifiable source
     */
    fun transferAsset(sourceId: AssetId, value: Long, destinationId: AssetId): Instruction {
        return Instruction.Transfer(
            TransferBox(
                sourceId = IdBox.AssetId(sourceId).evaluatesTo(),
                `object` = Value.U32(value).evaluatesTo(),
                destinationId = IdBox.AssetId(destinationId).evaluatesTo()
            )
        )
    }

    fun `if`(condition: Boolean, then: Instruction, otherwise: Instruction): Instruction {
        return Instruction.If(
            If(condition.evaluatesTo(), then, otherwise)
        )
    }

    /**
     * Instruction that includes two instructions
     */
    fun pair(left: Instruction, right: Instruction): Instruction {
        return Instruction.Pair(Pair(left, right))
    }

    /**
     * Instruction that includes few instructions
     */
    fun sequence(instructions: List<Instruction>): Instruction {
        return Instruction.Sequence(SequenceBox(instructions))
    }

    /**
     * Instruction to fail a transaction
     */
    fun fail(message: String): Instruction {
        return Instruction.Fail(FailBox(message))
    }

    private inline fun unregisterSome(idBox: () -> IdBox): Instruction.Unregister {
        return Instruction.Unregister(
            UnregisterBox(idBox().evaluatesTo())
        )
    }

    private inline fun registerSome(
        idBox: () -> IdentifiableBox
    ): Instruction.Register {
        return Instruction.Register(
            RegisterBox(
                EvaluatesTo(
                    Expression.Raw(Value.Identifiable(idBox()))
                )
            )
        )
    }

    private inline fun grantSome(idBox: IdBox, permissionToken: () -> PermissionToken): Instruction.Grant {
        return Instruction.Grant(
            GrantBox(
                destinationId = idBox.evaluatesTo(),
                `object` = Value.PermissionToken(permissionToken()).evaluatesTo()
            )
        )
    }

    private fun burnSome(value: Value, idBox: IdBox): Instruction.Burn {
        return Instruction.Burn(
            BurnBox(
                `object` = value.evaluatesTo(),
                destinationId = idBox.evaluatesTo()
            )
        )
    }

    private fun mintSome(value: Value, idBox: IdBox): Instruction.Mint {
        return Instruction.Mint(
            MintBox(
                `object` = value.evaluatesTo(),
                destinationId = idBox.evaluatesTo()
            )
        )
    }

    private fun mintSome(value: Value, assetId: AssetId): Instruction.Mint {
        return Instruction.Mint(
            MintBox(
                `object` = value.evaluatesTo(),
                destinationId = IdBox.AssetId(assetId).evaluatesTo()
            )
        )
    }
}
