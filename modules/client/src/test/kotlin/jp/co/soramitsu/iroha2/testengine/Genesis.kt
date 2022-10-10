package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.Genesis
import jp.co.soramitsu.iroha2.IdKey
import jp.co.soramitsu.iroha2.Permissions
import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asValue
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.core.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.Token
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.TokenId
import jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.action.Repeats
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import jp.co.soramitsu.iroha2.transaction.Instructions

/**
 * Create a default genesis where there is just one domain with only Alice and Bob in it
 */
open class DefaultGenesis : Genesis(rawGenesisBlock())

/**
 * Give Alice access to Bob's metadata
 */
open class AliceHasRoleWithAccessToBobsMetadata : Genesis(
    rawGenesisBlock(
        Instructions.registerPermissionToken(Permissions.CanSetKeyValueInUserMetadata.type, IdKey.AccountId),
        Instructions.registerPermissionToken(Permissions.CanRemoveKeyValueInUserMetadata.type, IdKey.AccountId),
        Instructions.registerRole(
            ROLE_ID,
            Token(
                TokenId(Permissions.CanSetKeyValueInUserMetadata.type),
                mapOf(IdKey.AccountId.type.asName() to ALICE_ACCOUNT_ID.asValue())
            ),
            Token(
                TokenId(Permissions.CanRemoveKeyValueInUserMetadata.type),
                mapOf(IdKey.AccountId.type.asName() to ALICE_ACCOUNT_ID.asValue())
            )
        ),
        Instructions.grantRole(ROLE_ID, ALICE_ACCOUNT_ID)
    )
) {
    companion object {
        val ROLE_ID = RoleId("USER_METADATA_ACCESS".asName())
    }
}

/**
 * Give Alice 100 XOR and the permission to burn them
 */
open class AliceHas100XorAndPermissionToBurn : Genesis(
    rawGenesisBlock(
        Instructions.registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity()),
        Instructions.mintAsset(DEFAULT_ASSET_ID, 100),
        Instructions.registerPermissionToken(Permissions.CanBurnAssetWithDefinition.type, IdKey.AssetDefinitionId),
        Instructions.grantBurnAssetWithDefinitionId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID)
    )
)

/**
 * Give Alice test assets
 */
open class AliceWithTestAssets : Genesis(
    rawGenesisBlock(
        Instructions.registerAssetDefinition(TEST_ASSET_DEFINITION_ID, AssetValueType.Store()),
        Instructions.registerAssetDefinition(TEST_ASSET_DEFINITION_ID2, AssetValueType.Store())
    )
) {
    companion object {
        val TEST_ASSET_DEFINITION_ID = DefinitionId("test".asName(), DEFAULT_DOMAIN_ID)
        val TEST_ASSET_DEFINITION_ID2 = DefinitionId("test2".asName(), DEFAULT_DOMAIN_ID)
    }
}

/**
 * Register an executable trigger without instructions
 */
open class WithExecutableTrigger : Genesis(
    rawGenesisBlock(
        Instructions.registerExecutableTrigger(
            TRIGGER_ID,
            listOf(),
            Repeats.Exactly(1L),
            ALICE_ACCOUNT_ID,
            Metadata(mapOf())
        )
    )
) {
    companion object {
        val TRIGGER_ID = TriggerId("some_trigger".asName())
    }
}

/**
 * Mint 100 XOR for Alice and Bob
 */
open class AliceAndBobEachHave100Xor : Genesis(
    rawGenesisBlock(
        Instructions.registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity()),
        Instructions.mintAsset(DEFAULT_ASSET_ID, 100),
        Instructions.mintAsset(BOB_ASSET_ID, 100)
    )
) {
    companion object {
        val BOB_ASSET_ID = AssetId(DEFAULT_ASSET_DEFINITION_ID, BOB_ACCOUNT_ID)
    }
}

/**
 * Create a Store asset with metadata
 */
open class StoreAssetWithMetadata : Genesis(
    rawGenesisBlock(
        Instructions.registerAssetDefinition(
            DEFINITION_ID,
            AssetValueType.Store(),
            Metadata(mapOf(ASSET_KEY to ASSET_VALUE))
        ),
        Instructions.setKeyValue(ASSET_ID, ASSET_KEY, ASSET_VALUE)
    )
) {
    companion object {
        val ASSET_KEY = "key".asName()
        val ASSET_VALUE = "value".asValue()
        val DEFINITION_ID = DefinitionId("foo".asName(), DEFAULT_DOMAIN_ID)
        val ASSET_ID = AssetId(DEFINITION_ID, ALICE_ACCOUNT_ID)
    }
}

/**
 * Create XOR and VAL assets with one token for each and metadata
 */
open class XorAndValAssets : Genesis(
    rawGenesisBlock(
        Instructions.registerAssetDefinition(XOR_DEFINITION_ID, AssetValueType.Quantity()),
        Instructions.mintAsset(AssetId(XOR_DEFINITION_ID, ALICE_ACCOUNT_ID), XOR_QUANTITY),

        Instructions.registerAssetDefinition(VAL_DEFINITION_ID, AssetValueType.Quantity()),
        Instructions.mintAsset(AssetId(VAL_DEFINITION_ID, ALICE_ACCOUNT_ID), VAL_QUANTITY)
    )
) {
    companion object {
        const val XOR_QUANTITY = 1L
        const val VAL_QUANTITY = 1L
        val XOR_DEFINITION_ID = DefinitionId("xor".asName(), DEFAULT_DOMAIN_ID)
        val VAL_DEFINITION_ID = DefinitionId("val".asName(), DEFAULT_DOMAIN_ID)
    }
}

/**
 * Create a new account with metadata
 */
open class NewAccountWithMetadata : Genesis(
    rawGenesisBlock(
        Instructions.registerAccount(
            id = ACCOUNT_ID,
            signatories = listOf(KEYPAIR.public.toIrohaPublicKey()),
            metadata = Metadata(mapOf(KEY to VALUE))
        )
    )
) {
    companion object {
        val ACCOUNT_NAME = "foo".asName()
        val KEY = "key".asName()

        val VALUE = "value".asValue()
        val ACCOUNT_ID = AccountId(ACCOUNT_NAME, DEFAULT_DOMAIN_ID)
        val KEYPAIR = generateKeyPair()
    }
}

/**
 * Create a new domain with metadata
 */
open class NewDomainWithMetadata : Genesis(
    rawGenesisBlock(
        Instructions.registerDomain(
            domainId = DOMAIN_ID,
            metadata = mapOf(KEY to VALUE)
        )
    )
) {
    companion object {
        val KEY = "key".asName()
        val VALUE = "value".asValue()
        val DOMAIN_ID = DomainId("foo_domain".asName())
    }
}

/**
 * Create a new domain
 */
open class NewDomain : Genesis(
    rawGenesisBlock(
        Instructions.registerDomain(DOMAIN_ID)
    )
) {
    companion object {
        val DOMAIN_ID = "foo_domain".asDomainId()
    }
}

/**
 * Return [RawGenesisBlock] with instructions to init genesis block
 */
fun rawGenesisBlock(vararg isi: Instruction): RawGenesisBlock {
    return RawGenesisBlock(
        listOf(
            GenesisTransaction(
                listOf(
                    Instructions.registerDomain(DEFAULT_DOMAIN_ID),
                    Instructions.registerAccount(
                        ALICE_ACCOUNT_ID,
                        listOf(ALICE_KEYPAIR.public.toIrohaPublicKey())
                    ),
                    Instructions.registerAccount(
                        BOB_ACCOUNT_ID,
                        listOf(BOB_KEYPAIR.public.toIrohaPublicKey())
                    ),
                    *isi
                )
            )
        )
    )
}
