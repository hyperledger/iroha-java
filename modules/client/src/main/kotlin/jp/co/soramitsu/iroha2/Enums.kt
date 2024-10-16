package jp.co.soramitsu.iroha2

enum class Permissions(val type: String) {
    CanBurnUserTrigger("CanBurnUserTrigger"),
    CanUnregisterAccount("CanUnregisterAccount"),
    CanMintUserPublicKeys("CanMintUserPublicKeys"),
    CanBurnUserPublicKeys("CanBurnUserPublicKeys"),
    CanMintUserSignatureCheckConditions("CanMintUserSignatureCheckConditions"),
    CanSetKeyValueInAccount("CanSetKeyValueInAccount"),
    CanRemoveKeyValueInAccount("CanRemoveKeyValueInAccount"),
    CanRegisterAssetsWithDefinition("CanRegisterAssetsWithDefinition"),
    CanUnregisterAssetsWithDefinition("CanUnregisterAssetsWithDefinition"),
    CanUnregisterUserAsset("CanUnregisterUserAsset"),
    CanBurnAssetWithDefinition("CanBurnAssetWithDefinition"),
    CanBurnUserAssetToken("CanBurnUserAsset"),
    CanMintAssetWithDefinition("CanMintAssetWithDefinition"),
    CanTransferAssetWithDefinition("CanTransferAssetWithDefinition"),
    CanTransferUserAssetsToken("CanTransferUserAsset"),
    CanSetKeyValueInUserAsset("CanSetKeyValueInUserAsset"),
    CanRemoveKeyValueInUserAsset("CanRemoveKeyValueInUserAsset"),
    CanUnregisterAssetDefinition("CanUnregisterAssetDefinition"),
    CanSetKeyValueInAssetDefinition("CanSetKeyValueInAssetDefinition"),
    CanRemoveKeyValueInAssetDefinition("CanRemoveKeyValueInAssetDefinition"),
    CanUnregisterDomain("CanUnregisterDomain"),
    CanSetKeyValueInDomain("CanSetKeyValueInDomain"),
    CanRemoveKeyValueInDomain("CanRemoveKeyValueInDomain"),
    CanGrantPermissionToCreateParameters("CanGrantPermissionToCreateParameters"),
    CanRevokePermissionToCreateParameters("CanRevokePermissionToCreateParameters"),
    CanCreateParameters("CanCreateParameters"),
    CanGrantPermissionToSetParameters("CanGrantPermissionToSetParameters"),
    CanRevokePermissionToSetParameters("CanRevokePermissionToSetParameters"),
    CanSetParameters("CanSetParameters"),
    CanUnregisterAnyPeer("CanUnregisterAnyPeer"),
    CanUnregisterAnyRole("CanUnregisterAnyRole"),
    CanExecuteUserTrigger("CanExecuteUserTrigger"),
    CanUnregisterUserTrigger("CanUnregisterUserTrigger"),
    CanMintUserTrigger("CanMintUserTrigger"),
    CanUpgradeExecutor("CanUpgradeExecutor"),
    CanRemoveKeyValueInTrigger("CanRemoveKeyValueInTrigger"),
    CanSetKeyValueInTrigger("CanSetKeyValueInTrigger"),
}

enum class IdKey(val type: String) {
    AccountId("account"),
    AssetId("asset"),
    AssetDefinitionId("asset_definition"),
    DomainId("domain"),
}
