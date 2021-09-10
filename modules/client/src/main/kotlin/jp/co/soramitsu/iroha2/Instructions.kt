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
import jp.co.soramitsu.iroha2.generated.datamodel.isi.GrantBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.isi.MintBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.RegisterBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.SetKeyValueBox
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

const val CAN_SET_KEY_VALUE_USER_ASSETS_TOKEN = "can_set_key_value_in_user_assets"
const val CAN_MINT_USER_ASSETS_DEFINITION = "can_mint_user_asset_definitions"
const val ASSET_ID_TOKEN_PARAM_NAME = "asset_id"
const val ASSET_DEFINITION_PARAM_NAME = "asset_definition_id"

object Instructions {

    fun registerAccount(
        id: AccountId,
        signatories: MutableList<PublicKey>,
        metadata: Metadata = Metadata(mutableMapOf())
    ): Instruction {
        return registerSome {
            IdentifiableBox.NewAccount(
                NewAccount(id, signatories, metadata)
            )
        }
    }

    fun registerAsset(
        id: DefinitionId,
        assetValueType: AssetValueType
    ): Instruction {
        return registerSome {
            IdentifiableBox.AssetDefinition(
                AssetDefinition(assetValueType, id)
            )
        }
    }

    fun storeAsset(
        assetId: AssetId,
        key: String,
        value: Value
    ): Instruction {
        return Instruction.SetKeyValue(
            SetKeyValueBox(
                objectId = EvaluatesTo(Expression.Raw(Value.Id(IdBox.AssetId(assetId)))),
                key = EvaluatesTo(Expression.Raw(key.asValue())),
                value = EvaluatesTo(Expression.Raw(value))
            )
        )
    }

    fun mintAsset(
        assetId: AssetId,
        quantity: UInt
    ): Instruction {
        return Instruction.Mint(
            MintBox(
                `object` = EvaluatesTo(
                    Expression.Raw(
                        Value.U32(quantity)
                    )
                ),
                destinationId = EvaluatesTo(
                    Expression.Raw(
                        Value.Id(
                            IdBox.AssetId(assetId)
                        )
                    )
                )
            )
        )
    }

    fun grantSetKeyValueAsset(assetId: AssetId, target: AccountId): Instruction {
        return mintSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_SET_KEY_VALUE_USER_ASSETS_TOKEN,
                params = mutableMapOf(ASSET_ID_TOKEN_PARAM_NAME to Value.Id(IdBox.AssetId(assetId)))
            )
        }
    }

    fun grantMintUserAssetsDefinition(assetDefinitionId: DefinitionId, target: AccountId): Instruction {
        return mintSome(IdBox.AccountId(target)) {
            PermissionToken(
                name = CAN_MINT_USER_ASSETS_DEFINITION,
                params = mutableMapOf(
                    ASSET_DEFINITION_PARAM_NAME to Value.Id(
                        IdBox.AssetDefinitionId(
                            assetDefinitionId
                        )
                    )
                )
            )
        }
    }

    fun registerDomain(
        domainName: String,
        accounts: MutableMap<AccountId, Account> = mutableMapOf(),
        assetDefinitions: MutableMap<DefinitionId, AssetDefinitionEntry> = mutableMapOf()
    ): Instruction {
        return registerSome {
            IdentifiableBox.Domain(
                Domain(domainName, accounts, assetDefinitions)
            )
        }
    }

    private inline fun registerSome(idBox: () -> IdentifiableBox): Instruction {
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

    private inline fun mintSome(idBox: IdBox, permissionToken: () -> PermissionToken): Instruction {
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

//    private inline fun mintSome(idBox: IdBox, permissionToken: () -> PermissionToken): Instruction {
//        return Instruction.Grant(
//            GrantBox(
//                destinationId = EvaluatesTo(
//                    Expression.Raw(
//                        Value.Id(IdBox.AccountId(ADMIN_ACCOUNT.asIrohaAccountId()))
//                    )
//                ),
//                `object` = EvaluatesTo(
//                    Expression.Raw(
//                        Value.PermissionToken(permissionToken())
//                    )
//                )
//            )
//        )
//    }
}
