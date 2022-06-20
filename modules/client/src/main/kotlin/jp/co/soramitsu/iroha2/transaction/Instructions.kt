package jp.co.soramitsu.iroha2.transaction

import jp.co.soramitsu.iroha2.DigestFunction
import jp.co.soramitsu.iroha2.Permissions
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asValue
import jp.co.soramitsu.iroha2.cast
import jp.co.soramitsu.iroha2.evaluatesTo
import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.RegistrableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Mintable
import jp.co.soramitsu.iroha2.generated.datamodel.domain.IpfsPath
import jp.co.soramitsu.iroha2.generated.datamodel.domain.NewDomain
import jp.co.soramitsu.iroha2.generated.datamodel.events.FilterBox
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
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Executable
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.WasmSmartContract
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Trigger
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.action.Action
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.action.Repeats
import jp.co.soramitsu.iroha2.generated.dataprimitives.fixed.Fixed
import jp.co.soramitsu.iroha2.toValueId
import java.math.BigDecimal
import java.math.BigInteger
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id as DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.EventFilter as TimeEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.peer.Id as PeerId
import jp.co.soramitsu.iroha2.generated.datamodel.role.Id as RoleId
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id as TriggerId

val ACCOUNT_ID_TOKEN_PARAM_NAME by lazy { "account_id".asName() }
val ASSET_ID_TOKEN_PARAM_NAME by lazy { "asset_id".asName() }
val ASSET_DEFINITION_PARAM_NAME by lazy { "asset_definition_id".asName() }
val COUNT_PARAM_NAME by lazy { "count".asName() }
val PERIOD_PARAM_NAME by lazy { "period".asName() }

/**
 * Iroha Special Instructions cover all possible actions within a blockchain
 * @see [Iroha2 Tutorial on Iroha Special Instructions](https://hyperledger.github.io/iroha-2-docs/guide/advanced/isi.html)
 */
object Instructions {

    /**
     * Register a role that has the specified permissions
     */
    fun registerRole(
        roleId: RoleId,
        vararg tokens: PermissionToken
    ): Instruction.Register {
        return registerSome {
            RegistrableBox.Role(
                Role(roleId, tokens.toList())
            )
        }
    }

    /**
     * Register an account
     */
    @JvmOverloads
    fun registerAccount(
        id: AccountId,
        signatories: List<PublicKey>,
        metadata: Metadata = Metadata(mapOf())
    ): Instruction.Register {
        return registerSome {
            RegistrableBox.Account(
                NewAccount(id, signatories, metadata)
            )
        }
    }

    /**
     * Register a time trigger
     */
    fun registerTimeTrigger(
        triggerId: TriggerId,
        isi: List<Instruction>,
        repeats: Repeats,
        accountId: AccountId,
        filter: TimeEventFilter,
        metadata: Metadata
    ): Instruction {
        return registerSome {
            RegistrableBox.Trigger(
                Trigger(
                    triggerId,
                    Action(
                        Executable.Instructions(isi),
                        repeats,
                        accountId,
                        FilterBox.Time(filter),
                        metadata
                    )
                )
            )
        }
    }

    /**
     * Register an executable trigger
     */
    fun registerExecutableTrigger(
        triggerId: TriggerId,
        isi: List<Instruction>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata
    ): Instruction.Register {
        return registerSome {
            RegistrableBox.Trigger(
                Trigger(
                    triggerId,
                    Action(
                        Executable.Instructions(isi),
                        repeats,
                        accountId,
                        Filters.executeTrigger(triggerId, accountId),
                        metadata
                    )
                )
            )
        }
    }

    /**
     * Register an event trigger
     */
    fun registerEventTrigger(
        triggerId: TriggerId,
        isi: List<Instruction>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata,
        filter: FilterBox
    ): Instruction.Register {
        return registerSome {
            RegistrableBox.Trigger(
                Trigger(
                    triggerId,
                    Action(
                        Executable.Instructions(isi),
                        repeats,
                        accountId,
                        filter,
                        metadata
                    )
                )
            )
        }
    }

    /**
     * Register a WASM trigger
     */
    fun registerWasmTrigger(
        triggerId: TriggerId,
        wasm: ByteArray,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata,
        filter: FilterBox
    ): Instruction.Register {
        return registerSome {
            RegistrableBox.Trigger(
                Trigger(
                    triggerId,
                    Action(
                        Executable.Wasm(
                            WasmSmartContract(wasm)
                        ),
                        repeats,
                        accountId,
                        filter,
                        metadata
                    ),
                )
            )
        }
    }

    /**
     * Register a pre-commit trigger to run after every transaction
     */
    fun registerPreCommitTrigger(
        triggerId: TriggerId,
        isi: List<Instruction>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata
    ): Instruction.Register {
        return registerSome {
            RegistrableBox.Trigger(
                Trigger(
                    triggerId,
                    Action(
                        Executable.Instructions(isi),
                        repeats,
                        accountId,
                        Filters.time(EventFilters.timeEventFilter()),
                        metadata
                    )
                )
            )
        }
    }

    /**
     * Unregister a trigger
     */
    fun unregisterTrigger(
        triggerName: String
    ): Instruction.Unregister {
        return unregisterSome {
            IdBox.TriggerId(
                TriggerId(
                    triggerName.asName()
                )
            )
        }
    }

    /**
     * Register an asset
     */
    @JvmOverloads
    fun registerAsset(
        id: DefinitionId,
        assetValueType: AssetValueType,
        metadata: Metadata = Metadata(mapOf()),
        mintable: Mintable = Mintable.Infinitely()
    ): Instruction.Register {
        return registerSome {
            RegistrableBox.AssetDefinition(
                AssetDefinition(id, assetValueType, mintable, metadata)
            )
        }
    }

    /**
     * Register a domain
     */
    @JvmOverloads
    fun registerDomain(
        domainId: DomainId,
        metadata: Map<Name, Value> = mapOf(),
        logo: IpfsPath? = null
    ): Instruction.Register {
        return registerSome {
            RegistrableBox.Domain(
                NewDomain(
                    domainId,
                    logo,
                    Metadata(metadata)
                )
            )
        }
    }

    /**
     * Register a peer
     */
    @JvmOverloads
    fun registerPeer(
        address: String,
        payload: ByteArray,
        digestFunction: String = DigestFunction.Ed25519.hashFunName
    ): Instruction.Register {
        return registerSome {
            RegistrableBox.Peer(
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
     * Unregister a peer
     */
    @JvmOverloads
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
     * Set key/value for a given asset
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
     * Set key/value for a given asset definition
     */
    fun setKeyValue(
        definitionId: DefinitionId,
        key: Name,
        value: Value
    ): Instruction.SetKeyValue {
        return Instruction.SetKeyValue(
            SetKeyValueBox(
                objectId = IdBox.AssetDefinitionId(definitionId).evaluatesTo(),
                key = key.evaluatesTo(),
                value = value.evaluatesTo()
            )
        )
    }

    /**
     * Set key/value in the metadata of a given account
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
     * Remove key/value from a given asset
     */
    fun removeKeyValue(assetId: AssetId, key: Name): Instruction.RemoveKeyValue {
        return Instruction.RemoveKeyValue(
            RemoveKeyValueBox(
                objectId = IdBox.AssetId(assetId).evaluatesTo(),
                key = key.evaluatesTo()
            )
        )
    }

    /**
     * Execute a trigger
     */
    fun executeTrigger(triggerId: TriggerId): Instruction.ExecuteTrigger {
        return Instruction.ExecuteTrigger(ExecuteTriggerBox(triggerId))
    }

    /**
     * Mint an asset of the [AssetValueType.Quantity] asset value type
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
     * Mint an asset of the [AssetValueType.Fixed] asset value type
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
     * Mint a public key
     */
    fun mintPublicKey(accountId: AccountId, pubKey: PublicKey): Instruction {
        return mintSome(
            Value.PublicKey(pubKey),
            IdBox.AccountId(accountId)
        )
    }

    /**
     * Burn an asset of the [AssetValueType.Quantity] asset value type
     */
    fun burnAsset(assetId: AssetId, value: Long): Instruction {
        return burnSome(
            Value.U32(value),
            IdBox.AssetId(assetId)
        )
    }

    /**
     * Burn an asset of the [AssetValueType.Fixed] asset value type
     */
    fun burnAsset(assetId: AssetId, value: BigDecimal): Instruction {
        return burnSome(
            Value.Fixed(Fixed(value)),
            IdBox.AssetId(assetId)
        )
    }

    /**
     * Burn a public key
     */
    fun burnPublicKey(accountId: AccountId, pubKey: PublicKey): Instruction {
        return burnSome(
            Value.PublicKey(pubKey),
            IdBox.AccountId(accountId)
        )
    }

    fun removePublicKey(accountId: AccountId, pubKey: PublicKey) = burnPublicKey(accountId, pubKey)

    /**
     * Grant an account the [Permissions.CanSetKeyValueUserAssetsToken] permission
     */
    fun grantSetKeyValueAsset(assetId: AssetId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanSetKeyValueUserAssetsToken.permissionName.asName(),
                params = mapOf(ASSET_ID_TOKEN_PARAM_NAME to assetId.toValueId())
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanRemoveKeyValueInUserAssets] permission
     */
    fun grantRemoveKeyValueAsset(assetId: AssetId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanRemoveKeyValueInUserAssets.permissionName.asName(),
                params = mapOf(ASSET_ID_TOKEN_PARAM_NAME to assetId.toValueId())
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanSetKeyValueInUserMetadata] permission
     */
    fun grantSetKeyValueMetadata(accountId: AccountId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanSetKeyValueInUserMetadata.permissionName.asName(),
                params = mapOf(ACCOUNT_ID_TOKEN_PARAM_NAME to accountId.toValueId())
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanRemoveKeyValueInUserMetadata] permission
     */
    fun grantRemoveKeyValueMetadata(accountId: AccountId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanRemoveKeyValueInUserMetadata.permissionName.asName(),
                params = mapOf(ACCOUNT_ID_TOKEN_PARAM_NAME to accountId.toValueId())
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanSetKeyValueInAssetDefinition] permission
     */
    fun grantSetKeyValueAssetDefinition(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanSetKeyValueInAssetDefinition.permissionName.asName(),
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanRemoveKeyValueInAssetDefinition] permission
     */
    fun grantRemoveKeyValueAssetDefinition(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanRemoveKeyValueInAssetDefinition.permissionName.asName(),
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanMintUserAssetDefinitionsToken] permission
     */
    fun grantMintUserAssetDefinitions(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanMintUserAssetDefinitionsToken.permissionName.asName(),
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanTransferOnlyFixedNumberOfTimesPerPeriod] permission
     */
    fun grantTransferOnlyFixedNumberOfTimesPerPeriod(period: BigInteger, count: Long, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanTransferOnlyFixedNumberOfTimesPerPeriod.permissionName.asName(),
                params = mapOf(
                    PERIOD_PARAM_NAME to period.asValue(),
                    COUNT_PARAM_NAME to count.asValue()
                )
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanBurnAssetWithDefinition] permission
     */
    fun grantBurnAssetWithDefinitionId(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanBurnAssetWithDefinition.permissionName.asName(),
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanBurnUserAssetsToken] permission
     */
    fun grantBurnAssets(assetId: AssetId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanBurnUserAssetsToken.permissionName.asName(),
                params = mapOf(
                    ASSET_ID_TOKEN_PARAM_NAME to assetId.toValueId()
                )
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanRegisterDomainsToken] permission
     */
    fun grantRegisterDomains(target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanRegisterDomainsToken.permissionName.asName(),
                params = emptyMap()
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanTransferUserAssetsToken] permission
     */
    fun grantTransferUserAsset(assetId: AssetId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanTransferUserAssetsToken.permissionName.asName(),
                params = mapOf(
                    ASSET_ID_TOKEN_PARAM_NAME to assetId.toValueId()
                )
            )
        }
    }

    /**
     * Grant an account the [Permissions.CanUnregisterAssetWithDefinition] permission
     */
    fun grantUnregisterAssetDefinition(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return grantSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = Permissions.CanUnregisterAssetWithDefinition.permissionName.asName(),
                params = mapOf(
                    ASSET_DEFINITION_PARAM_NAME to assetDefinitionId.toValueId()
                )
            )
        }
    }

    /**
     * Grant an account a given role.
     */
    fun grantRole(roleId: RoleId, accountId: AccountId): Instruction {
        return Instruction.Grant(
            GrantBox(
                destinationId = IdBox.RoleId(roleId).evaluatesTo(),
                `object` = accountId.evaluatesTo().cast()
            )
        )
    }

    /**
     * Transfer an asset from the identifiable source.
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

    /**
     * Evaluate one instruction if a [condition] is met and another one otherwise.
     */
    fun `if`(condition: Boolean, then: Instruction, otherwise: Instruction): Instruction {
        return Instruction.If(
            If(condition.evaluatesTo(), then, otherwise)
        )
    }

    /**
     * Pair two instructions together.
     */
    fun pair(left: Instruction, right: Instruction): Instruction {
        return Instruction.Pair(Pair(left, right))
    }

    /**
     * Combine multiple [instructions] into a sequence.
     */
    fun sequence(instructions: List<Instruction>): Instruction {
        return Instruction.Sequence(SequenceBox(instructions))
    }

    /**
     * Fail a transaction with a given [message].
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
        regBox: () -> RegistrableBox
    ): Instruction.Register {
        return Instruction.Register(
            RegisterBox(regBox().evaluatesTo())
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
