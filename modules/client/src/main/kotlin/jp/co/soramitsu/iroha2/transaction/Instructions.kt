package jp.co.soramitsu.iroha2.transaction

import jp.co.soramitsu.iroha2.Permissions
import jp.co.soramitsu.iroha2.asJsonString
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asStringWithJson
import jp.co.soramitsu.iroha2.asValue
import jp.co.soramitsu.iroha2.cast
import jp.co.soramitsu.iroha2.evaluatesTo
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.ActionOfTriggeringFilterBox
import jp.co.soramitsu.iroha2.generated.Algorithm
import jp.co.soramitsu.iroha2.generated.Asset
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.BurnExpr
import jp.co.soramitsu.iroha2.generated.ConditionalExpr
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.Executable
import jp.co.soramitsu.iroha2.generated.ExecuteTriggerExpr
import jp.co.soramitsu.iroha2.generated.Fail
import jp.co.soramitsu.iroha2.generated.GrantExpr
import jp.co.soramitsu.iroha2.generated.IdBox
import jp.co.soramitsu.iroha2.generated.InstructionExpr
import jp.co.soramitsu.iroha2.generated.IpfsPath
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.MintExpr
import jp.co.soramitsu.iroha2.generated.Mintable
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.NewAccount
import jp.co.soramitsu.iroha2.generated.NewAssetDefinition
import jp.co.soramitsu.iroha2.generated.NewDomain
import jp.co.soramitsu.iroha2.generated.NewRole
import jp.co.soramitsu.iroha2.generated.PairExpr
import jp.co.soramitsu.iroha2.generated.Peer
import jp.co.soramitsu.iroha2.generated.PeerId
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.PublicKey
import jp.co.soramitsu.iroha2.generated.RegisterExpr
import jp.co.soramitsu.iroha2.generated.RegistrableBox
import jp.co.soramitsu.iroha2.generated.RemoveKeyValueExpr
import jp.co.soramitsu.iroha2.generated.Repeats
import jp.co.soramitsu.iroha2.generated.RevokeExpr
import jp.co.soramitsu.iroha2.generated.Role
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.SequenceExpr
import jp.co.soramitsu.iroha2.generated.SetKeyValueExpr
import jp.co.soramitsu.iroha2.generated.SignatureCheckCondition
import jp.co.soramitsu.iroha2.generated.TimeEventFilter
import jp.co.soramitsu.iroha2.generated.TransferExpr
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.generated.TriggerOfTriggeringFilterBox
import jp.co.soramitsu.iroha2.generated.TriggeringFilterBox
import jp.co.soramitsu.iroha2.generated.UnregisterExpr
import jp.co.soramitsu.iroha2.generated.Value
import jp.co.soramitsu.iroha2.generated.WasmSmartContract
import jp.co.soramitsu.iroha2.toSocketAddr
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
    ) = registerSome {
        RegistrableBox.Role(NewRole(Role(roleId, tokens.toList())))
    }

    /**
     * Register an account
     */
    @JvmOverloads
    fun registerAccount(
        id: AccountId,
        signatories: List<PublicKey>,
        metadata: Metadata = Metadata(mapOf()),
    ) = registerSome {
        RegistrableBox.Account(NewAccount(id, signatories, metadata))
    }

    /**
     * Register a time trigger
     */
    fun registerTimeTrigger(
        triggerId: TriggerId,
        isi: List<InstructionExpr>,
        repeats: Repeats,
        accountId: AccountId,
        filter: TimeEventFilter,
        metadata: Metadata,
    ) = registerSome {
        RegistrableBox.Trigger(
            TriggerOfTriggeringFilterBox(
                triggerId,
                ActionOfTriggeringFilterBox(
                    Executable.Instructions(isi),
                    repeats,
                    accountId,
                    TriggeringFilterBox.Time(filter),
                    metadata,
                ),
            ),
        )
    }

    /**
     * Register an executable trigger
     */
    fun registerExecutableTrigger(
        triggerId: TriggerId,
        isi: List<InstructionExpr>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata,
    ) = registerSome {
        RegistrableBox.Trigger(
            TriggerOfTriggeringFilterBox(
                triggerId,
                ActionOfTriggeringFilterBox(
                    Executable.Instructions(isi),
                    repeats,
                    accountId,
                    Filters.executeTrigger(triggerId, accountId),
                    metadata,
                ),
            ),
        )
    }

    /**
     * Register an event trigger
     */
    fun registerEventTrigger(
        triggerId: TriggerId,
        isi: List<InstructionExpr>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata,
        filter: TriggeringFilterBox,
    ) = registerSome {
        RegistrableBox.Trigger(
            TriggerOfTriggeringFilterBox(
                triggerId,
                ActionOfTriggeringFilterBox(
                    Executable.Instructions(isi),
                    repeats,
                    accountId,
                    filter,
                    metadata,
                ),
            ),
        )
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
        filter: TriggeringFilterBox,
    ) = registerSome {
        RegistrableBox.Trigger(
            TriggerOfTriggeringFilterBox(
                triggerId,
                ActionOfTriggeringFilterBox(
                    Executable.Wasm(WasmSmartContract(wasm)),
                    repeats,
                    accountId,
                    filter,
                    metadata,
                ),
            ),
        )
    }

    /**
     * Register a pre-commit trigger to run after every transaction
     */
    fun registerPreCommitTrigger(
        triggerId: TriggerId,
        isi: List<InstructionExpr>,
        repeats: Repeats,
        accountId: AccountId,
        metadata: Metadata,
    ) = registerSome {
        RegistrableBox.Trigger(
            TriggerOfTriggeringFilterBox(
                triggerId,
                ActionOfTriggeringFilterBox(
                    Executable.Instructions(isi),
                    repeats,
                    accountId,
                    TriggeringFilterBox.Time(EventFilters.timeEventFilter()),
                    metadata,
                ),
            ),
        )
    }

    /**
     * Unregister a trigger
     */
    fun unregisterTrigger(id: TriggerId) = unregisterSome { IdBox.TriggerId(id) }

    /**
     * Unregister a trigger
     */
    fun unregisterTrigger(
        triggerName: String,
        domainId: DomainId? = null,
    ) = unregisterSome {
        IdBox.TriggerId(TriggerId(domainId, triggerName.asName()))
    }

    /**
     * Unregister an asset
     */
    fun unregisterAsset(id: AssetId) = unregisterSome { IdBox.AssetId(id) }

    /**
     * Unregister an account
     */
    fun unregisterAccount(id: AccountId) = unregisterSome { IdBox.AccountId(id) }

    /**
     * Unregister a domain
     */
    fun unregisterDomain(id: DomainId) = unregisterSome { IdBox.DomainId(id) }

    /**
     * Unregister a role
     */
    fun unregisterRole(id: RoleId) = unregisterSome { IdBox.RoleId(id) }

    /**
     * Register an asset
     */
    @JvmOverloads
    fun registerAssetDefinition(
        id: AssetDefinitionId,
        assetValueType: AssetValueType,
        metadata: Metadata = Metadata(mapOf()),
        mintable: Mintable = Mintable.Infinitely(),
    ) = registerSome {
        RegistrableBox.AssetDefinition(
            NewAssetDefinition(id, assetValueType, mintable, metadata = metadata),
        )
    }

    /**
     * Register an asset
     */
    fun registerAsset(id: AssetId, assetValue: AssetValue) = registerSome {
        RegistrableBox.Asset(Asset(id, assetValue))
    }

    /**
     * Register a domain
     */
    @JvmOverloads
    fun registerDomain(
        domainId: DomainId,
        metadata: Map<Name, Value> = mapOf(),
        logo: IpfsPath? = null,
    ) = registerSome {
        RegistrableBox.Domain(NewDomain(domainId, logo, Metadata(metadata)))
    }

    /**
     * Register a peer
     */
    @JvmOverloads
    fun registerPeer(
        address: String,
        payload: ByteArray,
        digestFunction: Algorithm = Algorithm.Ed25519(),
    ) = registerSome {
        RegistrableBox.Peer(
            Peer(PeerId(address.toSocketAddr(), PublicKey(digestFunction, payload))),
        )
    }

    /**
     * Unregister a peer
     */
    @JvmOverloads
    fun unregisterPeer(
        address: String,
        payload: ByteArray,
        digestFunction: Algorithm = Algorithm.Ed25519(),
    ) = unregisterSome {
        IdBox.PeerId(
            PeerId(address.toSocketAddr(), PublicKey(digestFunction, payload)),
        )
    }

    /**
     * Set key/value for a given asset
     */
    fun setKeyValue(
        assetId: AssetId,
        key: Name,
        value: Value,
    ) = InstructionExpr.SetKeyValue(
        SetKeyValueExpr(
            objectId = IdBox.AssetId(assetId).evaluatesTo(),
            key = key.evaluatesTo(),
            value = value.evaluatesTo(),
        ),
    )

    /**
     * Set key/value for a given asset definition
     */
    fun setKeyValue(
        definitionId: AssetDefinitionId,
        key: Name,
        value: Value,
    ) = InstructionExpr.SetKeyValue(
        SetKeyValueExpr(
            objectId = IdBox.AssetDefinitionId(definitionId).evaluatesTo(),
            key = key.evaluatesTo(),
            value = value.evaluatesTo(),
        ),
    )

    /**
     * Set key/value in the metadata of a given account
     */
    fun setKeyValue(
        accountId: AccountId,
        key: Name,
        value: Value,
    ) = InstructionExpr.SetKeyValue(
        SetKeyValueExpr(
            objectId = IdBox.AccountId(accountId).evaluatesTo(),
            key = key.evaluatesTo(),
            value = value.evaluatesTo(),
        ),
    )

    /**
     * Remove key/value from a given asset
     */
    fun removeKeyValue(assetId: AssetId, key: Name) = InstructionExpr.RemoveKeyValue(
        RemoveKeyValueExpr(
            objectId = IdBox.AssetId(assetId).evaluatesTo(),
            key = key.evaluatesTo(),
        ),
    )

    /**
     * Set key/value in the metadata of a given domain
     */
    fun setKeyValue(
        domainId: DomainId,
        key: Name,
        value: Value,
    ) = InstructionExpr.SetKeyValue(
        SetKeyValueExpr(
            objectId = IdBox.DomainId(domainId).evaluatesTo(),
            key = key.evaluatesTo(),
            value = value.evaluatesTo(),
        ),
    )

    /**
     * Execute a trigger
     */
    fun executeTrigger(triggerId: TriggerId) = InstructionExpr.ExecuteTrigger(
        ExecuteTriggerExpr(triggerId.evaluatesTo()),
    )

    /**
     * Mint an asset of the [AssetValueType.Quantity] asset value type
     */
    fun mintAsset(assetId: AssetId, quantity: Int) = mintSome(quantity.asValue(), assetId)

    /**
     * Mint an asset of the [AssetValueType.Fixed] asset value type
     */
    fun mintAsset(assetId: AssetId, quantity: BigDecimal) = mintSome(quantity.asValue(), assetId)

    /**
     * Mint a public key
     */
    fun mintPublicKey(accountId: AccountId, pubKey: PublicKey) = mintSome(
        Value.PublicKey(pubKey),
        IdBox.AccountId(accountId),
    )

    /**
     * Mint
     */
    fun mintSignatureCheckCondition(accountId: AccountId, signature: SignatureCheckCondition) = mintSome(
        Value.SignatureCheckCondition(signature),
        IdBox.AccountId(accountId),
    )

    /**
     * Burn an asset of the [AssetValueType.Quantity] asset value type
     */
    fun burnAsset(assetId: AssetId, value: Int) = burnSome(value.asValue(), IdBox.AssetId(assetId))

    /**
     * Burn an asset of the [AssetValueType.Fixed] asset value type
     */
    fun burnAsset(assetId: AssetId, value: BigDecimal) = burnSome(value.asValue(), IdBox.AssetId(assetId))

    /**
     * Burn a public key
     */
    fun burnPublicKey(accountId: AccountId, pubKey: PublicKey) = burnSome(
        Value.PublicKey(pubKey),
        IdBox.AccountId(accountId),
    )

    fun removePublicKey(accountId: AccountId, pubKey: PublicKey) = burnPublicKey(accountId, pubKey)

    /**
     * Grant an account the [Permissions.CanTransferUserAssetsToken] permission
     */
    fun grantPermissionToken(
        permission: Permissions,
        payload: String,
        target: AccountId,
    ) = grantSome(target, PermissionToken(permission.type, payload.asStringWithJson()).asValue())

    /**
     * Grant an account the custom permission
     */
    fun grantPermissionToken(
        permission: String,
        payload: String,
        target: AccountId,
    ) = grantSome(target, PermissionToken(permission.asName(), payload.asStringWithJson()).asValue())

    /**
     * Grant an account a given role.
     */
    fun grantRole(roleId: RoleId, accountId: AccountId) = InstructionExpr.Grant(
        GrantExpr(
            destinationId = accountId.evaluatesTo().cast(),
            `object` = IdBox.RoleId(roleId).evaluatesTo().cast(),
        ),
    )

    /**
     * Transfer an asset from the identifiable source.
     */
    fun transferAsset(sourceId: AssetId, value: Int, destinationId: AccountId) = InstructionExpr.Transfer(
        TransferExpr(
            sourceId = IdBox.AssetId(sourceId).evaluatesTo(),
            `object` = value.asValue().evaluatesTo(),
            destinationId = IdBox.AccountId(destinationId).evaluatesTo(),
        ),
    )

    /**
     * Transfer domain ownership.
     */
    fun transferDomainOwnership(sourceId: AccountId, value: IdBox.DomainId, destinationId: AccountId) =
        InstructionExpr.Transfer(
            TransferExpr(
                sourceId = IdBox.AccountId(sourceId).evaluatesTo(),
                `object` = Value.Id(value).evaluatesTo(),
                destinationId = IdBox.AccountId(destinationId).evaluatesTo(),
            ),
        )

    /**
     * Evaluate one instruction if a [condition] is met and another one otherwise.
     */
    fun `if`(
        condition: Boolean,
        then: InstructionExpr,
        otherwise: InstructionExpr,
    ) = InstructionExpr.If(ConditionalExpr(condition.evaluatesTo(), then, otherwise))

    /**
     * PairExpr two instructions together.
     */
    fun pair(left: InstructionExpr, right: InstructionExpr) = InstructionExpr.Pair(PairExpr(left, right))

    /**
     * Combine multiple [instructions] into a sequence.
     */
    fun sequence(instructions: List<InstructionExpr>) = InstructionExpr.Sequence(SequenceExpr(instructions))

    /**
     * Fail a transaction with a given [message].
     */
    fun fail(message: String) = InstructionExpr.Fail(Fail(message))

    /**
     * Revoke an account the [Permissions.CanSetKeyValueUserAssetsToken] permission
     */
    fun revokeSetKeyValueAsset(assetId: AssetId, target: AccountId): InstructionExpr {
        return revokeSome(target) {
            PermissionToken(
                definitionId = Permissions.CanSetKeyValueUserAssetsToken.type,
                payload = assetId.asJsonString().asStringWithJson(),
            )
        }
    }

    /**
     * Revoke an account the [Permissions.CanSetKeyValueInUserAccount] permission
     */
    fun revokeSetKeyValueAccount(accountId: AccountId, target: AccountId): InstructionExpr {
        return revokeSome(target) {
            PermissionToken(
                definitionId = Permissions.CanSetKeyValueInUserAccount.type,
                payload = accountId.asJsonString().asStringWithJson(),
            )
        }
    }

    /**
     * Revoke an account the [Permissions.CanSetKeyValueInDomain] permission
     */
    fun grantSetKeyValueDomain(domainId: DomainId, target: AccountId): InstructionExpr {
        return grantSome(
            target,
            PermissionToken(
                definitionId = Permissions.CanSetKeyValueInDomain.type,
                payload = domainId.asJsonString().asStringWithJson(),
            ).asValue(),
        )
    }

    /**
     * Revoke an account the [Permissions.CanSetKeyValueInDomain] permission
     */
    fun revokeSetKeyValueDomain(domainId: DomainId, target: AccountId): InstructionExpr {
        return revokeSome(target) {
            PermissionToken(
                definitionId = Permissions.CanSetKeyValueInDomain.type,
                payload = domainId.asJsonString().asStringWithJson(),
            )
        }
    }

    /**
     * Revoke an account a given role.
     */
    fun revokeRole(roleId: RoleId, accountId: AccountId): InstructionExpr {
        return InstructionExpr.Revoke(
            RevokeExpr(
                destinationId = accountId.evaluatesTo().cast(),
                `object` = IdBox.RoleId(roleId).evaluatesTo().cast(),
            ),
        )
    }

    private inline fun unregisterSome(idBox: () -> IdBox) = InstructionExpr.Unregister(
        UnregisterExpr(idBox().evaluatesTo()),
    )

    private inline fun registerSome(
        regBox: () -> RegistrableBox,
    ) = InstructionExpr.Register(RegisterExpr(regBox().evaluatesTo()))

    private fun grantSome(accountId: AccountId, value: Value) = InstructionExpr.Grant(
        GrantExpr(
            `object` = value.evaluatesTo(),
            destinationId = accountId.evaluatesTo(),
        ),
    )

    private inline fun revokeSome(
        accountId: AccountId,
        permissionToken: () -> PermissionToken,
    ): InstructionExpr.Revoke {
        return InstructionExpr.Revoke(
            RevokeExpr(
                destinationId = accountId.evaluatesTo(),
                `object` = Value.PermissionToken(permissionToken()).evaluatesTo(),
            ),
        )
    }

    private fun burnSome(value: Value, idBox: IdBox) = InstructionExpr.Burn(
        BurnExpr(
            `object` = value.evaluatesTo(),
            destinationId = idBox.evaluatesTo(),
        ),
    )

    private fun mintSome(value: Value, idBox: IdBox) = InstructionExpr.Mint(
        MintExpr(
            `object` = value.evaluatesTo(),
            destinationId = idBox.evaluatesTo(),
        ),
    )

    private fun mintSome(value: Value, assetId: AssetId) = InstructionExpr.Mint(
        MintExpr(
            `object` = value.evaluatesTo(),
            destinationId = IdBox.AssetId(assetId).evaluatesTo(),
        ),
    )
}
