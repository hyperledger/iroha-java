package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account
import jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionEntry
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.fixed.Fixed
import jp.co.soramitsu.iroha2.generated.datamodel.isi.BurnBox
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
import java.math.BigDecimal
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id as DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.peer.Id as PeerId

val CAN_SET_KEY_VALUE_USER_ASSETS_TOKEN by lazy { "can_set_key_value_in_user_assets".asName() }
val CAN_REMOVE_KEY_VALUE_IN_USER_ASSETS by lazy { "can_remove_key_value_in_user_assets".asName() }
val CAN_SET_KEY_VALUE_IN_USER_METADATA by lazy { "can_set_key_value_in_user_metadata".asName() }
val CAN_REMOVE_KEY_VALUE_IN_USER_METADATA by lazy { "can_remove_key_value_in_user_metadata".asName() }
val CAN_SET_KEY_VALUE_IN_ASSET_DEFINITION by lazy { "can_set_key_value_in_asset_definition".asName() }
val CAN_REMOVE_KEY_VALUE_IN_ASSET_DEFINITION by lazy { "can_remove_key_value_in_asset_definition".asName() }
val CAN_MINT_USER_ASSET_DEFINITIONS_TOKEN by lazy { "can_mint_user_asset_definitions".asName() }
val CAN_MINT_USER_ASSETS_DEFINITION by lazy { "can_mint_user_asset_definitions".asName() }
val CAN_BURN_ASSET_WITH_DEFINITION by lazy { "can_burn_asset_with_definition".asName() }
val CAN_BURN_USER_ASSETS_TOKEN by lazy { "can_burn_user_assets".asName() }
val CAN_REGISTER_DOMAINS_TOKEN by lazy { "can_register_domains".asName() }
val CAN_TRANSFER_USER_ASSETS_TOKEN by lazy { "can_transfer_user_assets".asName() }
val CAN_UNREGISTER_ASSET_WITH_DEFINITION by lazy { "can_unregister_asset_with_definition".asName() }

val ACCOUNT_ID_TOKEN_PARAM_NAME by lazy { "account_id".asName() }
val ASSET_ID_TOKEN_PARAM_NAME by lazy { "asset_id".asName() }
val ASSET_DEFINITION_PARAM_NAME by lazy { "asset_definition_id".asName() }

object Instructions {

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

    /**
     * Instruction for asset registration
     */
    fun registerAsset(
        id: DefinitionId,
        assetValueType: AssetValueType,
        metadata: Metadata = Metadata(mapOf()),
        mintable: Boolean = true
    ): Instruction.Register {
        return registerSome {
            IdentifiableBox.AssetDefinition(
                AssetDefinition(assetValueType, id, metadata, mintable)
            )
        }
    }

    /**
     * Instruction for domain registration
     */
    fun registerDomain(
        domainId: DomainId,
        accounts: Map<AccountId, Account> = mapOf(),
        assetDefinitions: Map<DefinitionId, AssetDefinitionEntry> = mapOf(),
        metadata: Map<Name, Value> = mapOf()
    ): Instruction.Register {
        return registerSome {
            IdentifiableBox.Domain(
                Domain(
                    domainId,
                    accounts,
                    assetDefinitions,
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
     * Instruction for world registration
     */
    fun registerWorld(): Instruction.Register {
        return registerSome {
            IdentifiableBox.World()
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
        quanity: BigDecimal
    ): Instruction.Mint {
        return mintSome(
            Value.Fixed(Fixed(quanity)),
            assetId
        )
    }

    /**
     * Instruction for mint of a public key
     */
    fun mintPublicKey(accountId: AccountId, pubKey: PublicKey): Instruction {
        return mintSomePublicKey(
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
                params = mapOf(ASSET_ID_TOKEN_PARAM_NAME to Value.Id(IdBox.AssetId(assetId)))
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
                params = mapOf(ASSET_ID_TOKEN_PARAM_NAME to Value.Id(IdBox.AssetId(assetId)))
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
                params = mapOf(ACCOUNT_ID_TOKEN_PARAM_NAME to Value.Id(IdBox.AccountId(accountId)))
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
                params = mapOf(ACCOUNT_ID_TOKEN_PARAM_NAME to Value.Id(IdBox.AccountId(accountId)))
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
                    ASSET_DEFINITION_PARAM_NAME to Value.Id(
                        IdBox.AssetDefinitionId(
                            assetDefinitionId
                        )
                    )
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
                    ASSET_DEFINITION_PARAM_NAME to Value.Id(
                        IdBox.AssetDefinitionId(
                            assetDefinitionId
                        )
                    )
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
                    ASSET_DEFINITION_PARAM_NAME to Value.Id(
                        IdBox.AssetDefinitionId(
                            assetDefinitionId
                        )
                    )
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
                    ASSET_DEFINITION_PARAM_NAME to Value.Id(
                        IdBox.AssetDefinitionId(
                            assetDefinitionId
                        )
                    )
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
                    ASSET_DEFINITION_PARAM_NAME to Value.Id(
                        IdBox.AssetDefinitionId(
                            assetDefinitionId
                        )
                    )
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
                    ASSET_ID_TOKEN_PARAM_NAME to Value.Id(
                        IdBox.AssetId(assetId)
                    )
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
                    ASSET_ID_TOKEN_PARAM_NAME to Value.Id(
                        IdBox.AssetId(assetId)
                    )
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
                    ASSET_DEFINITION_PARAM_NAME to Value.Id(
                        IdBox.AssetDefinitionId(
                            assetDefinitionId
                        )
                    )
                )
            )
        }
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

    private inline fun unregisterSome(idBox: () -> IdentifiableBox): Instruction.Unregister {
        return Instruction.Unregister(
            UnregisterBox(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Identifiable(idBox())
                    )
                )
            )
        )
    }

    private inline fun registerSome(idBox: () -> IdentifiableBox): Instruction.Register {
        return Instruction.Register(
            RegisterBox(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Identifiable(idBox())
                    )
                )
            )
        )
    }

    private inline fun grantSome(idBox: IdBox, permissionToken: () -> PermissionToken): Instruction.Grant {
        return Instruction.Grant(
            GrantBox(
                destinationId = EvaluatesTo(
                    Expression.Raw(
                        Value.Id(idBox)
                    )
                ),
                `object` = EvaluatesTo(
                    Expression.Raw(
                        Value.PermissionToken(permissionToken())
                    )
                )
            )
        )
    }

    private fun burnSome(value: Value, idBox: IdBox): Instruction.Burn {
        return Instruction.Burn(
            BurnBox(
                `object` = EvaluatesTo(
                    Expression.Raw(value)
                ),
                destinationId = EvaluatesTo(
                    Expression.Raw(
                        Value.Id(idBox)
                    )
                )
            )
        )
    }

    private fun mintSomePublicKey(value: Value, idBox: IdBox): Instruction.Mint {
        return Instruction.Mint(
            MintBox(
                `object` = EvaluatesTo(
                    Expression.Raw(value)
                ),
                destinationId = EvaluatesTo(
                    Expression.Raw(
                        Value.Id(idBox)
                    )
                )
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
