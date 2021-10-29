package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
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
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

const val CAN_SET_KEY_VALUE_USER_ASSETS_TOKEN = "can_set_key_value_in_user_assets"
const val CAN_MINT_USER_ASSETS_DEFINITION = "can_mint_user_asset_definitions"
const val CAN_BURN_ASSET_WITH_DEFINITION = "can_burn_asset_with_definition"
const val ASSET_ID_TOKEN_PARAM_NAME = "asset_id"
const val ASSET_DEFINITION_PARAM_NAME = "asset_definition_id"

/**
 * Types:
 *  - additional support for fixed types
 *  - register peer
 *  - burn
 *  - fail
 *  - grant
 *  - if
 *  - mint
 *  - pair
 *  - register
 *  - remove key value
 *  - sequence
 *  - set key value
 *  - transfer
 *  - unregister
 */
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
        domainName: String,
        accounts: Map<AccountId, Account> = mapOf(),
        assetDefinitions: Map<DefinitionId, AssetDefinitionEntry> = mapOf()
    ): Instruction.Register {
        return registerSome {
            IdentifiableBox.Domain(
                Domain(domainName, accounts, assetDefinitions)
            )
        }
    }

    /**
     * Instruction to set key value at the object
     */
    fun setKeyValue(
        assetId: AssetId,
        key: String,
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
     * Instruction to remove key value at the object
     */
    fun removeKeyValue(assetId: AssetId, key: String): Instruction.RemoveKeyValue {
        return Instruction.RemoveKeyValue(
            RemoveKeyValueBox(
                objectId = IdBox.AssetId(assetId).evaluatesTo(),
                key = key.evaluatesTo()
            )
        )
    }

    /**
     * Instruction for mint of an asset
     */
    fun mintAsset(
        assetId: AssetId,
        quantity: Long
    ): Instruction.Mint {
        return Instruction.Mint(
            MintBox(
                `object` = Value.U32(quantity).evaluatesTo(),
                destinationId = IdBox.AssetId(assetId).evaluatesTo()
            )
        )
    }

    /**
     * Instruction for burn of an asset
     */
    fun burnAsset(assetId: AssetId, value: Long): Instruction {
        return burnSome(
            Value.U32(value),
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
     * Instruction for granting `can_set_key_value_in_user_assets` permission to an account
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
     * Instruction for granting `can_mint_user_asset_definitions` permission to an account
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
     * Instruction for granting `can_burn_asset_with_definition` permission to an account
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
}
