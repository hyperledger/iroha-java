package jp.co.soramitsu.iroha2

enum class Permissions(val permissionName: String) {
    CanSetKeyValueUserAssetsToken("can_set_key_value_in_user_assets"),
    CanRemoveKeyValueInUserAssets("can_remove_key_value_in_user_assets"),
    CanSetKeyValueInUserMetadata("can_set_key_value_in_user_metadata"),
    CanRemoveKeyValueInUserMetadata("can_remove_key_value_in_user_metadata"),
    CanSetKeyValueInAssetDefinition("can_set_key_value_in_asset_definition"),
    CanRemoveKeyValueInAssetDefinition("can_remove_key_value_in_asset_definition"),
    CanMintUserAssetDefinitionsToken("can_mint_user_asset_definitions"),
    CanBurnAssetWithDefinition("can_burn_asset_with_definition"),
    CanBurnUserAssetsToken("can_burn_user_assets"),
    CanRegisterDomainsToken("can_register_domains"),
    CanTransferUserAssetsToken("can_transfer_user_assets"),
    CanUnregisterAssetWithDefinition("can_unregister_asset_with_definition"),
    CanTransferOnlyFixedNumberOfTimesPerPeriod("can_transfer_only_fixed_number_of_times_per_period"),
}
