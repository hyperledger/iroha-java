package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.Name

enum class Permissions(val type: Name) {
    CanUnregisterAccount("CanUnregisterAccount".asName()),
    CanMintUserPublicKeys("CanMintUserPublicKeys".asName()),
    CanBurnUserPublicKeys("CanBurnUserPublicKeys".asName()),
    CanMintUserSignatureCheckConditions("CanMintUserSignatureCheckConditions".asName()),
    CanSetKeyValueInUserAccount("CanSetKeyValueInUserAccount".asName()),
    CanRemoveKeyValueInUserAccount("CanRemoveKeyValueInUserAccount".asName()),
    CanRegisterAssetsWithDefinition("CanRegisterAssetsWithDefinition".asName()),
    CanUnregisterAssetsWithDefinition("CanUnregisterAssetsWithDefinition".asName()),
    CanUnregisterUserAsset("CanUnregisterUserAsset".asName()),
    CanBurnAssetWithDefinition("CanBurnAssetsWithDefinition".asName()),
    CanBurnUserAssetToken("CanBurnUserAsset".asName()),
    CanMintUserAssetDefinitionsToken("CanMintAssetsWithDefinition".asName()),
    CanTransferAssetsWithDefinition("CanTransferAssetsWithDefinition".asName()),
    CanTransferUserAssetsToken("CanTransferUserAsset".asName()),
    CanSetKeyValueUserAssetsToken("CanSetKeyValueInUserAsset".asName()),
    CanRemoveKeyValueInUserAssets("CanRemoveKeyValueInUserAsset".asName()),
    CanUnregisterAssetDefinition("CanUnregisterAssetDefinition".asName()),
    CanSetKeyValueInAssetDefinition("CanSetKeyValueInAssetDefinition".asName()),
    CanRemoveKeyValueInAssetDefinition("CanRemoveKeyValueInAssetDefinition".asName()),
    CanUnregisterDomain("CanUnregisterDomain".asName()),
    CanSetKeyValueInDomain("CanSetKeyValueInDomain".asName()),
    CanRemoveKeyValueInDomain("CanRemoveKeyValueInDomain".asName()),
    CanGrantPermissionToCreateParameters("CanGrantPermissionToCreateParameters".asName()),
    CanRevokePermissionToCreateParameters("CanRevokePermissionToCreateParameters".asName()),
    CanCreateParameters("CanCreateParameters".asName()),
    CanGrantPermissionToSetParameters("CanGrantPermissionToSetParameters".asName()),
    CanRevokePermissionToSetParameters("CanRevokePermissionToSetParameters".asName()),
    CanSetParameters("CanSetParameters".asName()),
    CanUnregisterAnyPeer("CanUnregisterAnyPeer".asName()),
    CanUnregisterAnyRole("CanUnregisterAnyRole".asName()),
    CanExecuteUserTrigger("CanExecuteUserTrigger".asName()),
    CanUnregisterUserTrigger("CanUnregisterUserTrigger".asName()),
    CanMintUserTrigger("CanMintUserTrigger".asName()),
    CanUpgradeValidator("CanUpgradeValidator".asName()),
}

enum class IdKey(val type: String) {
    AccountId("account_id"),
    AssetId("asset_id"),
    AssetDefinitionId("asset_definition_id"),
    DomainId("domain_id"),
}
