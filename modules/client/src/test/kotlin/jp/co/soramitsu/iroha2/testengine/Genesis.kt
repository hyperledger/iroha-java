package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.Genesis
import jp.co.soramitsu.iroha2.Permissions
import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asJsonString
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asStringWithJson
import jp.co.soramitsu.iroha2.asValue
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.RawGenesisBlock
import jp.co.soramitsu.iroha2.generated.Repeats
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import jp.co.soramitsu.iroha2.transaction.Instructions
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils

/**
 * Create a default genesis where there is just one domain with only Alice and Bob in it
 */
open class DefaultGenesis : Genesis(rawGenesisBlock())

open class AliceCanUpgradeValidator : Genesis(
    rawGenesisBlock(
        Instructions.grantPermissionToken(
            Permissions.CanUpgradeValidator,
            "",
            ALICE_ACCOUNT_ID,
        ),
    ),
)

open class AliceCanUnregisterAnyPeer : Genesis(
    rawGenesisBlock(
        Instructions.grantPermissionToken(
            Permissions.CanUnregisterAnyPeer,
            "",
            ALICE_ACCOUNT_ID,
        ),
    ),
)

open class AliceAndBobHasPermissionToMintPublicKeys : Genesis(
    rawGenesisBlock(
        Instructions.grantPermissionToken(
            Permissions.CanMintUserPublicKeys,
            ALICE_ACCOUNT_ID.asJsonString(),
            ALICE_ACCOUNT_ID,
        ),
        Instructions.grantPermissionToken(
            Permissions.CanMintUserPublicKeys,
            BOB_ACCOUNT_ID.asJsonString(),
            BOB_ACCOUNT_ID,
        ),
    ),
)

open class AliceHasPermissionToUnregisterDomain : Genesis(
    rawGenesisBlock(
        Instructions.registerDomain(NEW_DOMAIN_ID),
        Instructions.grantPermissionToken(
            Permissions.CanUnregisterDomain,
            NEW_DOMAIN_ID.asJsonString(),
            ALICE_ACCOUNT_ID,
        ),
    ),
) {
    companion object {
        val NEW_DOMAIN_ID = DomainId("NEW_DOMAIN".asName())
    }
}

open class WithManyDomains : Genesis(
    rawGenesisBlock(
        *registerDomains(25),
    ),
)

fun registerDomains(count: Int): Array<InstructionBox> {
    val instructions = mutableListOf<InstructionBox>()
    for (i in 1..count) {
        instructions.add(Instructions.registerDomain(DomainId("NEW_DOMAIN$i".asName())))
    }
    return instructions.toTypedArray()
}

/**
 * Give Alice access to Bob's metadata
 */
open class AliceHasRoleWithAccessToBobsMetadata : Genesis(
    rawGenesisBlock(
        Instructions.registerRole(
            ROLE_ID,
            PermissionToken(
                Permissions.CanSetKeyValueInUserAccount.type,
                BOB_ACCOUNT_ID.asJsonString().asStringWithJson(),
            ),
            PermissionToken(
                Permissions.CanRemoveKeyValueInUserAccount.type,
                BOB_ACCOUNT_ID.asJsonString().asStringWithJson(),
            ),
        ),
        Instructions.grantRole(ROLE_ID, ALICE_ACCOUNT_ID),
    ),
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
        Instructions.grantPermissionToken(
            Permissions.CanMintUserAssetDefinitionsToken,
            DEFAULT_ASSET_DEFINITION_ID.asJsonString(),
            ALICE_ACCOUNT_ID,
        ),
    ),
)

/**
 * Give Alice test assets
 */
open class AliceWithTestAssets : Genesis(
    rawGenesisBlock(
        Instructions.registerAssetDefinition(TEST_ASSET_DEFINITION_ID, AssetValueType.Store()),
        Instructions.registerAssetDefinition(TEST_ASSET_DEFINITION_ID2, AssetValueType.Store()),
    ),
) {
    companion object {
        val TEST_ASSET_DEFINITION_ID = AssetDefinitionId("test".asName(), DEFAULT_DOMAIN_ID)
        val TEST_ASSET_DEFINITION_ID2 = AssetDefinitionId("test2".asName(), DEFAULT_DOMAIN_ID)
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
            Metadata(mapOf()),
        ),
    ),
) {
    companion object {
        val TRIGGER_ID = TriggerId("some_trigger".asName(), DEFAULT_DOMAIN_ID)
    }
}

/**
 * Mint 100 XOR for Alice and Bob
 */
open class AliceAndBobEachHave100Xor : Genesis(
    rawGenesisBlock(
        Instructions.registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity()),
        Instructions.grantPermissionToken(
            Permissions.CanTransferAssetsWithDefinition,
            DEFAULT_ASSET_DEFINITION_ID.asJsonString(),
            ALICE_ACCOUNT_ID,
        ),
        Instructions.grantPermissionToken(
            Permissions.CanTransferAssetsWithDefinition,
            DEFAULT_ASSET_DEFINITION_ID.asJsonString(),
            BOB_ACCOUNT_ID,
        ),
        Instructions.mintAsset(DEFAULT_ASSET_ID, 100),
        Instructions.mintAsset(BOB_ASSET_ID, 100),
    ),
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
            Metadata(mapOf(ASSET_KEY to ASSET_VALUE)),
        ),
        Instructions.setKeyValue(ASSET_ID, ASSET_KEY, ASSET_VALUE),
    ),
) {
    companion object {
        val ASSET_KEY = "key".asName()
        val ASSET_VALUE = RandomStringUtils.randomAlphabetic(50).asValue()
        val DEFINITION_ID = AssetDefinitionId("foo".asName(), DEFAULT_DOMAIN_ID)
        val ASSET_ID = AssetId(DEFINITION_ID, ALICE_ACCOUNT_ID)
    }
}

open class AliceCanMintXor : Genesis(
    rawGenesisBlock(
        Instructions.grantPermissionToken(
            Permissions.CanMintUserAssetDefinitionsToken,
            XOR_DEFINITION_ID.asJsonString(),
            ALICE_ACCOUNT_ID,
        ),
    ),
)

/**
 * Create XOR and VAL assets with one token for each and metadata
 */
open class XorAndValAssets : Genesis(
    rawGenesisBlock(
        Instructions.registerAssetDefinition(XOR_DEFINITION_ID, AssetValueType.Quantity()),
        Instructions.mintAsset(AssetId(XOR_DEFINITION_ID, ALICE_ACCOUNT_ID), XOR_QUANTITY),

        Instructions.registerAssetDefinition(VAL_DEFINITION_ID, AssetValueType.Quantity()),
        Instructions.mintAsset(AssetId(VAL_DEFINITION_ID, ALICE_ACCOUNT_ID), VAL_QUANTITY),
    ),
) {
    companion object {
        const val XOR_QUANTITY = 1
        const val VAL_QUANTITY = 1
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
            metadata = Metadata(mapOf(KEY to VALUE)),
        ),
    ),
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
            metadata = mapOf(KEY to VALUE),
        ),
    ),
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
        Instructions.registerDomain(DOMAIN_ID),
    ),
) {
    companion object {
        val DOMAIN_ID = "foo_domain".asDomainId()
    }
}

/**
 * Specific genesis to test multiple genesis case
 */
open class RubbishToTestMultipleGenesis : Genesis(
    rawGenesisBlock(
        Instructions.registerDomain(DEFAULT_DOMAIN_ID, mapOf(DOMAIN_KEY_VALUE.asName() to DOMAIN_KEY_VALUE.asValue())),
        Instructions.registerAccount(
            ALICE_ACCOUNT_ID,
            listOf(ALICE_KEYPAIR.public.toIrohaPublicKey()),
            Metadata(mapOf(ALICE_KEY_VALUE.asName() to ALICE_KEY_VALUE.asValue())),
        ),
        Instructions.registerAccount(
            BOB_ACCOUNT_ID,
            listOf(BOB_KEYPAIR.public.toIrohaPublicKey()),
            Metadata(mapOf(BOB_KEY_VALUE.asName() to BOB_KEY_VALUE.asValue())),
        ),
    ),
) {
    companion object {
        val DOMAIN_KEY_VALUE: String = RandomStringUtils.randomAlphabetic(10)
        val ALICE_KEY_VALUE: String = RandomStringUtils.randomAlphabetic(10)
        val BOB_KEY_VALUE: String = RandomStringUtils.randomAlphabetic(10)
    }
}

/**
 * Return [RawGenesisBlock] with instructions to init genesis block
 */
fun rawGenesisBlock(vararg isi: InstructionBox) = RawGenesisBlock(
    listOf(
        Instructions.registerDomain(DEFAULT_DOMAIN_ID),
        Instructions.registerAccount(
            ALICE_ACCOUNT_ID,
            listOf(ALICE_KEYPAIR.public.toIrohaPublicKey()),
        ),
        Instructions.registerAccount(
            BOB_ACCOUNT_ID,
            listOf(BOB_KEYPAIR.public.toIrohaPublicKey()),
        ),
        *isi,
    ).let { listOf(it) },
    Genesis.validatorMode,
)
