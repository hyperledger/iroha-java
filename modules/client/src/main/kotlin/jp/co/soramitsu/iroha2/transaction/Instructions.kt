package jp.co.soramitsu.iroha2.transaction

import jp.co.soramitsu.iroha2.Permissions
import jp.co.soramitsu.iroha2.asJsonString
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asNumeric
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AccountMintBox
import jp.co.soramitsu.iroha2.generated.Action
import jp.co.soramitsu.iroha2.generated.Asset
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetTransferBox
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.BurnBox
import jp.co.soramitsu.iroha2.generated.BurnOfNumericAndAsset
import jp.co.soramitsu.iroha2.generated.BurnOfPublicKeyAndAccount
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.Executable
import jp.co.soramitsu.iroha2.generated.ExecuteTrigger
import jp.co.soramitsu.iroha2.generated.ExecuteTriggerEventFilter
import jp.co.soramitsu.iroha2.generated.Fail
import jp.co.soramitsu.iroha2.generated.GrantBox
import jp.co.soramitsu.iroha2.generated.GrantOfPermissionTokenAndAccount
import jp.co.soramitsu.iroha2.generated.GrantOfRoleIdAndAccount
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.IpfsPath
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.MetadataValueBox
import jp.co.soramitsu.iroha2.generated.MintBox
import jp.co.soramitsu.iroha2.generated.MintOfNumericAndAsset
import jp.co.soramitsu.iroha2.generated.MintOfPublicKeyAndAccount
import jp.co.soramitsu.iroha2.generated.MintOfSignatureCheckConditionAndAccount
import jp.co.soramitsu.iroha2.generated.Mintable
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.NewAccount
import jp.co.soramitsu.iroha2.generated.NewAssetDefinition
import jp.co.soramitsu.iroha2.generated.NewDomain
import jp.co.soramitsu.iroha2.generated.NewRole
import jp.co.soramitsu.iroha2.generated.Peer
import jp.co.soramitsu.iroha2.generated.PeerId
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.PublicKey
import jp.co.soramitsu.iroha2.generated.RegisterBox
import jp.co.soramitsu.iroha2.generated.RegisterOfAccount
import jp.co.soramitsu.iroha2.generated.RegisterOfAsset
import jp.co.soramitsu.iroha2.generated.RegisterOfAssetDefinition
import jp.co.soramitsu.iroha2.generated.RegisterOfDomain
import jp.co.soramitsu.iroha2.generated.RegisterOfPeer
import jp.co.soramitsu.iroha2.generated.RegisterOfRole
import jp.co.soramitsu.iroha2.generated.RegisterOfTrigger
import jp.co.soramitsu.iroha2.generated.RemoveKeyValueBox
import jp.co.soramitsu.iroha2.generated.RemoveKeyValueOfAsset
import jp.co.soramitsu.iroha2.generated.Repeats
import jp.co.soramitsu.iroha2.generated.RevokeBox
import jp.co.soramitsu.iroha2.generated.RevokeOfPermissionTokenAndAccount
import jp.co.soramitsu.iroha2.generated.RevokeOfRoleIdAndAccount
import jp.co.soramitsu.iroha2.generated.Role
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.SetKeyValueBox
import jp.co.soramitsu.iroha2.generated.SetKeyValueOfAccount
import jp.co.soramitsu.iroha2.generated.SetKeyValueOfAsset
import jp.co.soramitsu.iroha2.generated.SetKeyValueOfAssetDefinition
import jp.co.soramitsu.iroha2.generated.SetKeyValueOfDomain
import jp.co.soramitsu.iroha2.generated.SetKeyValueOfTrigger
import jp.co.soramitsu.iroha2.generated.SignatureCheckCondition
import jp.co.soramitsu.iroha2.generated.TimeEventFilter
import jp.co.soramitsu.iroha2.generated.TransferBox
import jp.co.soramitsu.iroha2.generated.TransferOfAccountAndDomainIdAndAccount
import jp.co.soramitsu.iroha2.generated.TransferOfAssetAndNumericAndAccount
import jp.co.soramitsu.iroha2.generated.Trigger
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.TriggeringEventEventFilterBox
import jp.co.soramitsu.iroha2.generated.UnregisterBox
import jp.co.soramitsu.iroha2.generated.UnregisterOfAccount
import jp.co.soramitsu.iroha2.generated.UnregisterOfAsset
import jp.co.soramitsu.iroha2.generated.UnregisterOfDomain
import jp.co.soramitsu.iroha2.generated.UnregisterOfPeer
import jp.co.soramitsu.iroha2.generated.UnregisterOfRole
import jp.co.soramitsu.iroha2.generated.UnregisterOfTrigger
import jp.co.soramitsu.iroha2.generated.WasmSmartContract
import java.math.BigDecimal

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
        vararg tokens: PermissionToken,
    ) = InstructionBox.Register(
        RegisterBox.Role(RegisterOfRole(NewRole(Role(roleId, tokens.toList())))),
    )

    /**
     * Register an account
     */
    @JvmOverloads
    fun registerAccount(
        id: AccountId,
        signatories: List<PublicKey>,
        metadata: Metadata = Metadata(mapOf()),
    ) = InstructionBox.Register(
        RegisterBox.Account(RegisterOfAccount(NewAccount(id, signatories, metadata))),
    )

    /**
     * Register a WASM trigger
     */
    fun registerTrigger(
        triggerId: TriggerId,
        wasm: ByteArray,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata,
        filter: TriggeringEventEventFilterBox,
    ) = InstructionBox.Register(
        RegisterBox.Trigger(
            RegisterOfTrigger(
                Trigger(
                    triggerId,
                    Action(Executable.Wasm(WasmSmartContract(wasm)), repeats, accountId, filter, metadata),
                ),
            ),
        ),
    )

    /**
     * Register a instructions trigger to run after every transaction
     */
    fun registerTrigger(
        triggerId: TriggerId,
        isi: List<InstructionBox>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata,
        filter: TimeEventFilter,
    ) = registerTrigger(
        triggerId,
        isi,
        repeats,
        accountId,
        metadata,
        TriggeringEventEventFilterBox.Time(TimeEventFilter(filter.executionTime)),
    )

    /**
     * Register a instructions trigger to run after every transaction
     */
    fun registerTrigger(
        triggerId: TriggerId,
        isi: List<InstructionBox>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata,
        filter: TriggeringEventEventFilterBox = TriggeringEventEventFilterBox.ExecuteTrigger(
            ExecuteTriggerEventFilter(triggerId, accountId),
        ),
    ) = InstructionBox.Register(
        RegisterBox.Trigger(
            RegisterOfTrigger(
                Trigger(
                    triggerId,
                    Action(Executable.Instructions(isi), repeats, accountId, filter, metadata),
                ),
            ),
        ),
    )

    /**
     * Unregister a trigger
     */
    fun unregisterTrigger(id: TriggerId) = InstructionBox.Unregister(
        UnregisterBox.Trigger(UnregisterOfTrigger(id)),
    )

    /**
     * Unregister a trigger
     */
    fun unregisterTrigger(
        triggerName: String,
        domainId: DomainId? = null,
    ) = unregisterTrigger(TriggerId(domainId, triggerName.asName()))

    /**
     * Unregister an asset
     */
    fun unregisterAsset(id: AssetId) = InstructionBox.Unregister(UnregisterBox.Asset(UnregisterOfAsset(id)))

    /**
     * Unregister an account
     */
    fun unregisterAccount(id: AccountId) = InstructionBox.Unregister(UnregisterBox.Account(UnregisterOfAccount(id)))

    /**
     * Unregister a domain
     */
    fun unregisterDomain(id: DomainId) = InstructionBox.Unregister(UnregisterBox.Domain(UnregisterOfDomain(id)))

    /**
     * Unregister a role
     */
    fun unregisterRole(id: RoleId) = InstructionBox.Unregister(UnregisterBox.Role(UnregisterOfRole(id)))

    /**
     * Register an asset
     */
    @JvmOverloads
    fun registerAssetDefinition(
        id: AssetDefinitionId,
        assetValueType: AssetValueType,
        metadata: Metadata = Metadata(mapOf()),
        mintable: Mintable = Mintable.Infinitely(),
        logo: IpfsPath? = null,
    ) = InstructionBox.Register(
        RegisterBox.AssetDefinition(
            RegisterOfAssetDefinition(
                NewAssetDefinition(id, assetValueType, mintable, logo, metadata),
            ),
        ),
    )

    /**
     * Register an asset
     */
    fun registerAsset(id: AssetId, assetValue: AssetValue) = InstructionBox.Register(
        RegisterBox.Asset(RegisterOfAsset(Asset(id, assetValue))),
    )

    /**
     * Register a domain
     */
    @JvmOverloads
    fun registerDomain(
        domainId: DomainId,
        metadata: Map<Name, MetadataValueBox> = mapOf(),
        logo: IpfsPath? = null,
    ) = InstructionBox.Register(
        RegisterBox.Domain(RegisterOfDomain(NewDomain(domainId, logo, Metadata(metadata)))),
    )

    /**
     * Register a peer
     */
    fun registerPeer(
        peerId: PeerId,
    ) = InstructionBox.Register(
        RegisterBox.Peer(RegisterOfPeer(Peer(peerId))),
    )

    /**
     * Unregister a peer
     */
    fun unregisterPeer(peerId: PeerId) = InstructionBox.Unregister(
        UnregisterBox.Peer(UnregisterOfPeer(peerId)),
    )

    /**
     * Set key/value for a given asset
     */
    fun setKeyValue(
        assetId: AssetId,
        key: Name,
        value: MetadataValueBox,
    ) = InstructionBox.SetKeyValue(
        SetKeyValueBox.Asset(
            SetKeyValueOfAsset(assetId, key, value),
        ),
    )

    /**
     * Set key/value for a given trigger
     */
    fun setKeyValue(
        triggerId: TriggerId,
        key: Name,
        value: MetadataValueBox,
    ) = InstructionBox.SetKeyValue(
        SetKeyValueBox.Trigger(
            SetKeyValueOfTrigger(triggerId, key, value),
        ),
    )

    /**
     * Set key/value for a given asset definition
     */
    fun setKeyValue(
        definitionId: AssetDefinitionId,
        key: Name,
        value: MetadataValueBox,
    ) = InstructionBox.SetKeyValue(
        SetKeyValueBox.AssetDefinition(
            SetKeyValueOfAssetDefinition(definitionId, key, value),
        ),
    )

    /**
     * Set key/value in the metadata of a given domain
     */
    fun setKeyValue(
        domainId: DomainId,
        key: Name,
        value: MetadataValueBox,
    ) = InstructionBox.SetKeyValue(
        SetKeyValueBox.Domain(SetKeyValueOfDomain(domainId, key, value)),
    )

    /**
     * Set key/value in the metadata of a given account
     */
    fun setKeyValue(
        accountId: AccountId,
        key: Name,
        value: MetadataValueBox,
    ) = InstructionBox.SetKeyValue(
        SetKeyValueBox.Account(SetKeyValueOfAccount(accountId, key, value)),
    )

    /**
     * Remove key/value from a given asset
     */
    fun removeKeyValue(assetId: AssetId, key: Name) = InstructionBox.RemoveKeyValue(
        RemoveKeyValueBox.Asset(RemoveKeyValueOfAsset(assetId, key)),
    )

    /**
     * Execute a trigger
     */
    fun executeTrigger(triggerId: TriggerId) = InstructionBox.ExecuteTrigger(ExecuteTrigger(triggerId))

    /**
     * Mint an asset of the [AssetValueType.Quantity] asset value type
     */
    fun mintAsset(assetId: AssetId, quantity: Int) = InstructionBox.Mint(
        MintBox.Asset(MintOfNumericAndAsset(quantity.asNumeric(), assetId)),
    )

    /**
     * Mint an asset of the [AssetValueType.Fixed] asset value type
     */
    fun mintAsset(assetId: AssetId, quantity: BigDecimal) = InstructionBox.Mint(
        MintBox.Asset(MintOfNumericAndAsset(quantity.asNumeric(), assetId)),
    )

    /**
     * Mint a public key
     */
    fun mintPublicKey(accountId: AccountId, pubKey: PublicKey) = InstructionBox.Mint(
        MintBox.Account(AccountMintBox.PublicKey(MintOfPublicKeyAndAccount(pubKey, accountId))),
    )

    /**
     * Mint
     */
    fun mintSignatureCheckCondition(accountId: AccountId, signature: SignatureCheckCondition) = InstructionBox.Mint(
        MintBox.Account(
            AccountMintBox.SignatureCheckCondition(
                MintOfSignatureCheckConditionAndAccount(signature, accountId),
            ),
        ),
    )

    /**
     * Burn an asset of the [AssetValueType.Quantity] asset value type
     */
    fun burnAsset(assetId: AssetId, value: Int) = InstructionBox.Burn(
        BurnBox.Asset(BurnOfNumericAndAsset(value.asNumeric(), assetId)),
    )

    /**
     * Burn an asset of the [AssetValueType.Fixed] asset value type
     */
    fun burnAsset(assetId: AssetId, value: BigDecimal) = InstructionBox.Burn(
        BurnBox.Asset(BurnOfNumericAndAsset(value.asNumeric(), assetId)),
    )

    /**
     * Burn a public key
     */
    fun burnPublicKey(accountId: AccountId, pubKey: PublicKey) = InstructionBox.Burn(
        BurnBox.AccountPublicKey(BurnOfPublicKeyAndAccount(pubKey, accountId)),
    )

    fun removePublicKey(accountId: AccountId, pubKey: PublicKey) = burnPublicKey(accountId, pubKey)

    /**
     * Grant an account the custom permission
     */
    fun grantPermissionToken(
        permission: Permissions,
        payload: String = "",
        destinationId: AccountId,
    ) = grantPermissionToken(permission.type.string, payload, destinationId)

    /**
     * Grant an account the custom permission
     */
    fun grantPermissionToken(
        permission: String,
        payload: String = "",
        destinationId: AccountId,
    ) = InstructionBox.Grant(
        GrantBox.PermissionToken(
            GrantOfPermissionTokenAndAccount(
                PermissionToken(permission.asName(), payload.asJsonString()),
                destinationId,
            ),
        ),
    )

    /**
     * Grant an account a given role.
     */
    fun grantRole(roleId: RoleId, destinationId: AccountId) = InstructionBox.Grant(
        GrantBox.Role(GrantOfRoleIdAndAccount(roleId, destinationId)),
    )

    /**
     * Transfer an asset from the identifiable source.
     */
    fun transferAsset(sourceId: AssetId, value: Int, destinationId: AccountId) = InstructionBox.Transfer(
        TransferBox.Asset(
            AssetTransferBox.Numeric(
                TransferOfAssetAndNumericAndAccount(sourceId, value.asNumeric(), destinationId),
            ),
        ),
    )

    /**
     * Transfer domain ownership.
     */
    fun transferDomainOwnership(sourceId: AccountId, domainId: DomainId, destinationId: AccountId) =
        InstructionBox.Transfer(
            TransferBox.Domain(
                TransferOfAccountAndDomainIdAndAccount(sourceId, domainId, destinationId),
            ),
        )

    /**
     * Fail a transaction with a given [message].
     */
    fun fail(message: String) = InstructionBox.Fail(Fail(message))

    /**
     * Revoke an account the [Permissions.CanSetKeyValueUserAssetsToken] permission
     */
    fun revokeSetKeyValueAsset(assetId: AssetId, target: AccountId): InstructionBox {
        return revokeSome(target) {
            PermissionToken(
                definitionId = Permissions.CanSetKeyValueUserAssetsToken.type,
                payload = assetId.asJsonString().asJsonString(),
            )
        }
    }

    /**
     * Revoke an account the [Permissions.CanSetKeyValueInUserAccount] permission
     */
    fun revokeSetKeyValueAccount(accountId: AccountId, target: AccountId): InstructionBox {
        return revokeSome(target) {
            PermissionToken(
                definitionId = Permissions.CanSetKeyValueInUserAccount.type,
                payload = accountId.asJsonString().asJsonString(),
            )
        }
    }

    /**
     * Revoke an account the [Permissions.CanSetKeyValueInDomain] permission
     */
    fun grantSetKeyValueDomain(domainId: DomainId, target: AccountId): InstructionBox {
        return InstructionBox.Grant(
            GrantBox.PermissionToken(
                GrantOfPermissionTokenAndAccount(
                    PermissionToken(
                        definitionId = Permissions.CanSetKeyValueInDomain.type,
                        payload = domainId.asJsonString().asJsonString(),
                    ),
                    target,
                ),
            ),
        )
    }

    /**
     * Revoke an account the [Permissions.CanSetKeyValueInDomain] permission
     */
    fun revokeSetKeyValueDomain(domainId: DomainId, target: AccountId): InstructionBox {
        return revokeSome(target) {
            PermissionToken(
                definitionId = Permissions.CanSetKeyValueInDomain.type,
                payload = domainId.asJsonString().asJsonString(),
            )
        }
    }

    /**
     * Revoke an account a given role.
     */
    fun revokeRole(roleId: RoleId, accountId: AccountId): InstructionBox {
        return InstructionBox.Revoke(
            RevokeBox.Role(RevokeOfRoleIdAndAccount(roleId, accountId)),
        )
    }

    private inline fun revokeSome(
        accountId: AccountId,
        permissionToken: () -> PermissionToken,
    ) = InstructionBox.Revoke(
        RevokeBox.PermissionToken(
            RevokeOfPermissionTokenAndAccount(permissionToken(), accountId),
        ),
    )
}
