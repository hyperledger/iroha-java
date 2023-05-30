package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.Name

enum class Permissions(val type: Name) {
    CanSetKeyValueUserAssetsToken("can_set_key_value_in_user_assets".asName()),
    CanRemoveKeyValueInUserAssets("can_remove_key_value_in_user_assets".asName()),
    CanSetKeyValueInUserMetadata("can_set_key_value_in_user_metadata".asName()),
    CanRemoveKeyValueInUserMetadata("can_remove_key_value_in_user_metadata".asName()),
    CanSetKeyValueInAssetDefinition("can_set_key_value_in_asset_definition".asName()),
    CanRemoveKeyValueInAssetDefinition("can_remove_key_value_in_asset_definition".asName()),
    CanMintUserAssetDefinitionsToken("can_mint_user_asset_definitions".asName()),
    CanBurnAssetWithDefinition("can_burn_asset_with_definition".asName()),
    CanBurnUserAssetsToken("can_burn_user_assets".asName()),
    CanRegisterDomainsToken("can_register_domains".asName()),
    CanTransferUserAssetsToken("can_transfer_user_assets".asName()),
    CanUnregisterAssetWithDefinition("can_unregister_asset_with_definition".asName()),
    CanTransferOnlyFixedNumberOfTimesPerPeriod("can_transfer_only_fixed_number_of_times_per_period".asName()),
}

enum class IdKey(val type: String) {
    AccountId("account_id"),
    AssetId("asset_id"),
    AssetDefinitionId("asset_definition_id")
}
