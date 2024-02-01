package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.ACCOUNT_ID_DELIMITER
import jp.co.soramitsu.iroha2.Genesis
import jp.co.soramitsu.iroha2.Permissions
import jp.co.soramitsu.iroha2.asAccountId
import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asJsonString
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asString
import jp.co.soramitsu.iroha2.asStringWithJson
import jp.co.soramitsu.iroha2.asValue
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.IdBox
import jp.co.soramitsu.iroha2.generated.InstructionExpr
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.RawGenesisBlock
import jp.co.soramitsu.iroha2.generated.Repeats
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import jp.co.soramitsu.iroha2.transaction.Instructions
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.random.Random.Default.nextDouble

/**
 * Create a default genesis where there is just one domain with only Alice and Bob in it
 */
open class DefaultGenesis : Genesis(rawGenesisBlock())

open class AliceCanUpgradeExecutor : Genesis(
    rawGenesisBlock(
        Instructions.grantPermissionToken(
            Permissions.CanUpgradeExecutor,
            "",
            ALICE_ACCOUNT_ID,
        ),
    ),
)

open class WithDomainTransferredToBob : Genesis(
    rawGenesisBlock(
        Instructions.registerDomain(DOMAIN_ID),
        Instructions.transferDomainOwnership(
            "$GENESIS$ACCOUNT_ID_DELIMITER$GENESIS".asAccountId(),
            IdBox.DomainId(DOMAIN_ID),
            BOB_ACCOUNT_ID,
        ),
    ),
) {
    companion object {
        val DOMAIN_ID = randomAlphabetic(10).asDomainId()
    }
}

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
        *registerDomains(DOMAINS_COUNT),
    ),
) {
    companion object {
        const val DOMAINS_COUNT = 25
    }
}

fun registerDomains(count: Int): Array<InstructionExpr> {
    val instructions = mutableListOf<InstructionExpr>()
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
        val TRIGGER_ID = TriggerId(DEFAULT_DOMAIN_ID, "some_trigger".asName())
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
        val ACCOUNT_ID = AccountId(DEFAULT_DOMAIN_ID, ACCOUNT_NAME)
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
 * To test serializers
 */
open class FatGenesis : Genesis(
    rawGenesisBlock(
        Instructions.registerDomain(
            randomAlphabetic(10).asDomainId(),
            mapOf(randomAlphabetic(10).asName() to randomAlphabetic(10).asValue()),
        ),
        Instructions.registerAccount(
            "${randomAlphabetic(10)}@${DEFAULT_DOMAIN_ID.asString()}".asAccountId(),
            listOf(generateKeyPair().public.toIrohaPublicKey()),
            Metadata(mapOf(randomAlphabetic(10).asName() to randomAlphabetic(10).asValue())),
        ),
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
        Instructions.registerAssetDefinition(
            DEFINITION_ID,
            AssetValueType.Store(),
            Metadata(mapOf(randomAlphabetic(10).asName() to randomAlphabetic(10).asValue())),
        ),
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
        Instructions.mintAsset(AssetId(DEFAULT_ASSET_DEFINITION_ID, BOB_ACCOUNT_ID), 100),
        Instructions.burnAsset(AssetId(DEFAULT_ASSET_DEFINITION_ID, BOB_ACCOUNT_ID), 100),
        Instructions.setKeyValue(ASSET_ID, randomAlphabetic(10).asName(), Int.MAX_VALUE.asValue()),
        Instructions.setKeyValue(ASSET_ID, randomAlphabetic(10).asName(), (Int.MAX_VALUE * 10L).asValue()),
        Instructions.setKeyValue(ASSET_ID, randomAlphabetic(10).asName(), nextDouble().asValue()),
        Instructions.setKeyValue(ASSET_ID, randomAlphabetic(10).asName(), BigDecimal(nextDouble()).asValue()),
        Instructions.setKeyValue(
            ASSET_ID,
            randomAlphabetic(10).asName(),
            (BigInteger.valueOf(Long.MAX_VALUE) * BigInteger.valueOf(2)).asValue(),
        ),
        Instructions.setKeyValue(ASSET_ID, randomAlphabetic(10).asName(), randomAlphabetic(10).asValue()),
        Instructions.setKeyValue(DEFAULT_DOMAIN_ID, randomAlphabetic(10).asName(), randomAlphabetic(10).asValue()),
    ),
) {
    companion object {
        val DEFINITION_ID = AssetDefinitionId("foo".asName(), DEFAULT_DOMAIN_ID)
        val ASSET_ID = AssetId(DEFINITION_ID, BOB_ACCOUNT_ID)
        val ROLE_ID = RoleId("USER_METADATA_ACCESS".asName())
    }
}

/**
 * Grant permission token to unregister any role
 */
open class BobCanUnregisterAnyRole : Genesis(
    rawGenesisBlock(
        Instructions.grantPermissionToken(
            Permissions.CanUnregisterAnyRole,
            "",
            BOB_ACCOUNT_ID,
        ),
    ),
)

/**
 * Return [RawGenesisBlock] with instructions to init genesis block
 */
fun rawGenesisBlock(vararg isi: InstructionExpr) = RawGenesisBlock(
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
    Genesis.executorMode,
)
