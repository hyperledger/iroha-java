package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.Name

enum class Permissions(val type: Name) {
    CanUnregisterAccount("can_unregister_account".asName()),
    CanMintUserPublicKeys("can_mint_user_public_keys".asName()),
    CanBurnUserPublicKeys("can_burn_user_public_keys".asName()),
    CanMintUserSignatureCheckConditions("can_mint_user_signature_check_conditions".asName()),
    CanSetKeyValueInUserAccount("can_set_key_value_in_user_account".asName()),
    CanRemoveKeyValueInUserAccount("can_remove_key_value_in_user_account".asName()),
    CanRegisterAssetsWithDefinition("can_register_assets_with_definition".asName()),
    CanUnregisterAssetsWithDefinition("can_unregister_assets_with_definition".asName()),
    CanUnregisterUserAssets("can_unregister_user_assets".asName()),
    CanBurnAssetWithDefinition("can_burn_assets_with_definition".asName()),
    CanBurnUserAssetsToken("can_burn_user_assets".asName()),
    CanMintUserAssetDefinitionsToken("can_mint_assets_with_definition".asName()),
    CanTransferAssetsWithDefinition("can_transfer_assets_with_definition".asName()),
    CanTransferUserAssetsToken("can_transfer_user_asset".asName()),
    CanSetKeyValueUserAssetsToken("can_set_key_value_in_user_asset".asName()),
    CanRemoveKeyValueInUserAssets("can_remove_key_value_in_user_asset".asName()),
    CanUnregisterAssetDefinition("can_unregister_asset_definition".asName()),
    CanSetKeyValueInAssetDefinition("can_set_key_value_in_asset_definition".asName()),
    CanRemoveKeyValueInAssetDefinition("can_remove_key_value_in_asset_definition".asName()),
    CanUnregisterDomain("can_unregister_domain".asName()),
    CanSetKeyValueInDomain("can_set_key_value_in_domain".asName()),
    CanRemoveKeyValueInDomain("can_remove_key_value_in_domain".asName()),
    CanGrantPermissionToCreateParameters("can_grant_permission_to_create_parameters".asName()),
    CanRevokePermissionToCreateParameters("can_revoke_permission_to_create_parameters".asName()),
    CanCreateParameters("can_create_parameters".asName()),
    CanGrantPermissionToSetParameters("can_grant_permission_to_set_parameters".asName()),
    CanRevokePermissionToSetParameters("can_revoke_permission_to_set_parameters".asName()),
    CanSetParameters("can_set_parameters".asName()),
    CanUnregisterAnyPeer("can_unregister_any_peer".asName()),
    CanUnregisterAnyRole("can_unregister_any_role".asName()),
    CanExecuteUserTrigger("can_execute_user_trigger".asName()),
    CanUnregisterUserTrigger("can_unregister_user_trigger".asName()),
    CanMintUserTrigger("can_mint_user_trigger".asName()),
    CanUpgradeValidator("can_upgrade_validator".asName())
}

enum class IdKey(val type: String) {
    AccountId("account_id"),
    AssetId("asset_id"),
    AssetDefinitionId("asset_definition_id")
}
