package jp.co.soramitsu.iroha2

import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import jp.co.soramitsu.iroha2.annotations.Permission
import jp.co.soramitsu.iroha2.annotations.Sdk
import jp.co.soramitsu.iroha2.annotations.SdkTestId
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.Asset
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.PermissionTokenId
import jp.co.soramitsu.iroha2.generated.PublicKey
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.Value
import jp.co.soramitsu.iroha2.generated.VersionedSignedTransaction
import jp.co.soramitsu.iroha2.query.QueryBuilder
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.ALICE_MANUAL_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.AliceAndBobEachHave100Xor
import jp.co.soramitsu.iroha2.testengine.AliceAndBobHasPermissionToMintPublicKeys
import jp.co.soramitsu.iroha2.testengine.AliceHas100XorAndPermissionToBurn
import jp.co.soramitsu.iroha2.testengine.AliceHasPermissionToUnregisterDomain
import jp.co.soramitsu.iroha2.testengine.AliceHasRoleWithAccessToBobsMetadata
import jp.co.soramitsu.iroha2.testengine.AliceWithTestAssets
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.BOB_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.DEFAULT_ASSET_DEFINITION_ID
import jp.co.soramitsu.iroha2.testengine.DEFAULT_ASSET_ID
import jp.co.soramitsu.iroha2.testengine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.testengine.DefaultGenesis
import jp.co.soramitsu.iroha2.testengine.IROHA_CONFIG_DELIMITER
import jp.co.soramitsu.iroha2.testengine.IrohaTest
import jp.co.soramitsu.iroha2.testengine.NewAccountWithMetadata
import jp.co.soramitsu.iroha2.testengine.NewDomainWithMetadata
import jp.co.soramitsu.iroha2.testengine.RubbishToTestMultipleGenesis
import jp.co.soramitsu.iroha2.testengine.StoreAssetWithMetadata
import jp.co.soramitsu.iroha2.testengine.WithIroha
import jp.co.soramitsu.iroha2.testengine.WithIrohaManual
import jp.co.soramitsu.iroha2.testengine.XorAndValAssets
import jp.co.soramitsu.iroha2.transaction.Instructions
import jp.co.soramitsu.iroha2.transaction.Instructions.fail
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.withTimeout
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.security.SecureRandom
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Owner("akostyuchenko")
@Sdk("Java/Kotlin")
class InstructionsTest : IrohaTest<Iroha2Client>(
    account = ALICE_ACCOUNT_ID,
    keyPair = ALICE_KEYPAIR,
) {
    @Test
    @Disabled
    // EXAMPLE
    @WithIrohaManual("http://localhost:8080", "http://localhost:8180")
    fun `register domain with manual initialized Iroha`(): Unit = runBlocking {
        val domainId = "new_domain_name".asDomainId()
        client.sendTransaction {
            account(super.account)
            registerDomain(domainId)
            buildSigned(ALICE_MANUAL_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        QueryBuilder.findDomainById(domainId)
            .account(super.account)
            .buildSigned(ALICE_MANUAL_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { result -> assertEquals(result.id, domainId) }
    }

    /**
     * Using for docs generation
     */
    // #region java_register_domain
    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Domains")
    @Story("Account registers a domain")
    @Permission("no_permission_required")
    @SdkTestId("register_domain")
    fun `register domain`(): Unit = runBlocking {
        val domainId = "new_domain_name".asDomainId()
        client.sendTransaction {
            account(super.account)
            registerDomain(domainId)
            buildSigned(super.keyPair)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        QueryBuilder.findDomainById(domainId)
            .account(super.account)
            .buildSigned(super.keyPair)
            .let { query -> client.sendQuery(query) }
            .also { result -> assertEquals(result.id, domainId) }
    }
    // #endregion java_register_domain

    /**
     * Using for docs generation
     */
    // #region java_register_account
    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Accounts")
    @Story("Account registers an account")
    @Permission("no_permission_required")
    @SdkTestId("register_account")
    fun `register account`(): Unit = runBlocking {
        val newAccountId = AccountId("foo".asName(), DEFAULT_DOMAIN_ID)
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAccount(newAccountId, listOf())
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        QueryBuilder.findAccountById(newAccountId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { account -> assertEquals(account.id, newAccountId) }
    }
    // #endregion java_register_account

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Accounts")
    @Story("Account registers an account")
    @Story("Account unregisters an account")
    @Permission("no_permission_required")
    @SdkTestId("register_account")
    @SdkTestId("unregister_account")
    fun `register and unregister account`(): Unit = runBlocking {
        val joeId = AccountId("foo".asName(), DEFAULT_DOMAIN_ID)
        val joeKeyPair = generateKeyPair()
        client.tx { registerAccount(joeId, listOf(joeKeyPair.public.toIrohaPublicKey())) }

        QueryBuilder.findAccountById(joeId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { account -> assertEquals(account.id, joeId) }

        client.tx(joeId, joeKeyPair) {
            grantPermissionToken(
                Permissions.CanUnregisterAccount,
                IdKey.AccountId.type.asName() to joeId.toValueId(),
                ALICE_ACCOUNT_ID,
            )
            unregisterAccount(joeId)
        }
        assertThrows<IrohaClientException> {
            runBlocking {
                QueryBuilder.findAccountById(joeId)
                    .account(ALICE_ACCOUNT_ID)
                    .buildSigned(ALICE_KEYPAIR)
                    .let { query -> client.sendQuery(query) }
            }
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Assets")
    @Story("Account registers an asset definition")
    @Story("Account unregisters an asset definition")
    @Permission("no_permission_required")
    @SdkTestId("register_asset_definition")
    @SdkTestId("unregister_asset_definition")
    fun `register and unregister asset`(): Unit = runBlocking {
        val definitionId = AssetDefinitionId("XSTUSD".asName(), DEFAULT_DOMAIN_ID)
        client.tx { registerAssetDefinition(definitionId, AssetValueType.Quantity()) }

        val assetId = AssetId(definitionId, ALICE_ACCOUNT_ID)
        client.tx { registerAsset(assetId, AssetValue.Quantity(0)) }

        QueryBuilder.findAssetById(assetId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { asset -> assertEquals(asset.id, assetId) }

        client.tx { unregisterAsset(assetId) }
        assertThrows<IrohaClientException> {
            runBlocking {
                QueryBuilder.findAssetById(assetId)
                    .account(ALICE_ACCOUNT_ID)
                    .buildSigned(ALICE_KEYPAIR)
                    .let { query -> client.sendQuery(query) }
            }
        }
    }

    @Test
    @WithIroha([AliceHasPermissionToUnregisterDomain::class])
    @Feature("Domains")
    @Story("Account unregisters a domain")
    @Permission("can_unregister_domain")
    @SdkTestId("unregister_domain")
    fun `unregister domain`(): Unit = runBlocking {
        client.tx { unregisterDomain(AliceHasPermissionToUnregisterDomain.NEW_DOMAIN_ID) }

        assertThrows<IrohaClientException> {
            runBlocking {
                QueryBuilder.findDomainById(AliceHasPermissionToUnregisterDomain.NEW_DOMAIN_ID)
                    .account(ALICE_ACCOUNT_ID)
                    .buildSigned(ALICE_KEYPAIR)
                    .let { query -> client.sendQuery(query) }
            }
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Accounts")
    @Story("Account registers an account")
    @Permission("no_permission_required")
    @SdkTestId("register_account_with_metadata")
    fun `register account with metadata`(): Unit = runBlocking {
        val newAccountId = AccountId("foo".asName(), DEFAULT_DOMAIN_ID)
        val addressKey = "address".asName()
        val phoneKey = "phone".asName()
        val emailKey = "email".asName()
        val cityKey = "city".asName()
        val addressValue = "address".asValue()
        val phoneValue = "phone".asValue()
        val emailValue = "email".asValue()
        val cityValue = "city".asValue()
        val metadata = Metadata(
            mapOf(
                Pair(addressKey, addressValue),
                Pair(phoneKey, phoneValue),
                Pair(emailKey, emailValue),
                Pair(cityKey, cityValue),
            ),
        )
        val encodedTx = TransactionBuilder {
            account(ALICE_ACCOUNT_ID)
            registerAccount(newAccountId, listOf(), metadata)
        }.buildSigned().let { VersionedSignedTransaction.encode(it) }

        val decodedTx = encodedTx.let { VersionedSignedTransaction.decode(it) }
        val signedTx = decodedTx.appendSignatures(ALICE_KEYPAIR)

        client.sendTransaction { signedTx }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        val accountMetadata = QueryBuilder.findAccountById(newAccountId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { account -> assertEquals(account.id, newAccountId) }
            .metadata
        assertEquals(4, accountMetadata.map.size)
        assertEquals(addressValue, accountMetadata.map[addressKey])
        assertEquals(phoneValue, accountMetadata.map[phoneKey])
        assertEquals(emailValue, accountMetadata.map[emailKey])
        assertEquals(cityValue, accountMetadata.map[cityKey])
    }

    /**
     * Using for docs generation
     */
    // #region java_register_asset
    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Assets")
    @Story("Account registers an asset definition")
    @Permission("no_permission_required")
    @SdkTestId("DEPRECATE CANDIDATE")
    fun `register asset`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
        val assetDefinitions = QueryBuilder.findAllAssetsDefinitions()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { q -> client.sendQuery(q) }

        assertFalse { assetDefinitions.isEmpty() }
        assetDefinitions.find { it.id == DEFAULT_ASSET_DEFINITION_ID }
            ?: fail("Expected query response contains assetDefinition $DEFAULT_ASSET_DEFINITION_ID, but it is not. Response was $assetDefinitions")
    }
    // #endregion java_register_asset

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Assets")
    @Story("Account registers an asset definition")
    @Permission("no_permission_required")
    @SdkTestId("register_asset_definition_with_store_value_type")
    fun `store asset`(): Unit = runBlocking {
        val pair1 = "key1".asName() to "bar".asValue()
        val pair2 = "key2".asName() to true.asValue()
        val pair3 = "key3".asName() to 12345.asValue()

        client.tx {
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Store())
            setKeyValue(DEFAULT_ASSET_ID, pair1.first, pair1.second)
            setKeyValue(DEFAULT_ASSET_ID, pair2.first, pair2.second)
            setKeyValue(DEFAULT_ASSET_ID, pair3.first, pair3.second)
        }

        val findAssetByIdQry = QueryBuilder.findAssetById(DEFAULT_ASSET_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val asset = client.sendQuery(findAssetByIdQry)

        assertEquals(DEFAULT_ASSET_ID.definitionId.name, asset.id.definitionId.name)
        assertEquals(DEFAULT_ASSET_ID.definitionId.domainId, asset.id.definitionId.domainId)
        when (val value = asset.value) {
            is AssetValue.Store -> {
                assertEquals(pair1.second.string, value.metadata.map[pair1.first]?.cast<Value.String>()?.string)
                assertEquals(pair2.second.bool, value.metadata.map[pair2.first]?.cast<Value.Bool>()?.bool)
                assertEquals(
                    pair3.second.numericValue,
                    value.metadata.map[pair3.first]?.cast<Value.Numeric>()?.numericValue,
                )
            }

            else -> fail("Expected result asset value has type `AssetValue.Store`, but it was `${asset.value::class.simpleName}`")
        }

        // try to find saved assets by domain name
        val findAssetsByDomainNameQry = QueryBuilder.findAssetsByDomainId(DEFAULT_DOMAIN_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val assetsByDomainName = client.sendQuery(findAssetsByDomainNameQry)
        assertEquals(1, assetsByDomainName.size)
        assertEquals(asset, assetsByDomainName.first())
    }

    @Test
    @WithIroha(
        [DefaultGenesis::class],
        configs = ["WSV_ACCOUNT_METADATA_LIMITS$IROHA_CONFIG_DELIMITER{\"max_entry_byte_size\": 65536, \"max_len\": 1048576}"],
    )
    @Feature("Accounts")
    @Story("Account metadata limit adjustment")
    @Permission("no_permission_required")
    @SdkTestId("account_metadata_limit_increased")
    fun `account metadata limit increased`(): Unit = runBlocking {
        client.tx {
            // 5000 characters string would be rejected by Iroha with default WSV_ACCOUNT_METADATA_LIMITS config
            setKeyValue(ALICE_ACCOUNT_ID, "key".asName(), RandomStringUtils.random(5000).asValue())
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Accounts")
    @Story("Account set key value pair")
    @Permission("can_set_key_value_in_user_asset")
    @SdkTestId("set_key_value_pair_for_another_account_asset_definition")
    fun `grant access to asset key-value and then revoke`(): Unit = runBlocking {
        val aliceAssetId = DEFAULT_ASSET_ID

        client.tx {
            registerAssetDefinition(aliceAssetId.definitionId, AssetValueType.Store())
            // grant by Alice to Bob permissions to set key value in Asset.Store
            grantPermissionToken(
                Permissions.CanSetKeyValueUserAssetsToken,
                IdKey.AssetId.type.asName() to aliceAssetId.asValue(),
                BOB_ACCOUNT_ID,
            )
        }
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            setKeyValue(aliceAssetId, "foo".asName(), "bar".asValue())
        }

        val query = QueryBuilder.findAssetById(aliceAssetId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val asset = client.sendQuery(query)

        assertEquals(aliceAssetId.definitionId.name, asset.id.definitionId.name)
        assertEquals(aliceAssetId.definitionId.domainId, asset.id.definitionId.domainId)
        when (val value = asset.value) {
            is AssetValue.Store -> {
                assertEquals("bar", (value.metadata.map["foo".asName()]?.cast<Value.String>())?.string)
            }

            else -> fail("Expected result asset value has type `AssetValue.Store`, but it was `${asset.value::class.simpleName}`")
        }

        client.tx {
            revokeSetKeyValueAsset(aliceAssetId, BOB_ACCOUNT_ID)
        }
        QueryBuilder.findPermissionTokensByAccountId(BOB_ACCOUNT_ID)
            .account(BOB_ACCOUNT_ID)
            .buildSigned(BOB_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { permissions -> assertTrue { permissions.isEmpty() } }
    }

    /**
     * Using for docs generation
     */
    // #region java_mint_asset
    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Assets")
    @Story("Account mints an asset")
    @Permission("no_permission_required")
    @SdkTestId("mint_asset_for_account_in_same_domain")
    fun `mint asset`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            mintAsset(DEFAULT_ASSET_ID, 5)
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { result ->
                assertEquals(5, result.assets[DEFAULT_ASSET_ID]?.value?.cast<AssetValue.Quantity>()?.u32)
            }
    }
    // #endregion java_mint_asset

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    @Feature("Assets")
    @Story("Account burn an asset")
    @Permission("no_permission_required")
    @SdkTestId("burn_asset_for_account_in_same_domain")
    fun `burn asset`(): Unit = runBlocking {
        // check balance before burn
        val query = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        var result = client.sendQuery(query)
        assertEquals(100, result.assets[DEFAULT_ASSET_ID]?.value?.cast<AssetValue.Quantity>()?.u32)

        client.tx { burnAsset(DEFAULT_ASSET_ID, 50) }

        // check balance after burn
        result = client.sendQuery(query)
        assertEquals(50, result.assets[DEFAULT_ASSET_ID]?.value?.cast<AssetValue.Quantity>()?.u32)
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Assets")
    @Story("Account burn an asset")
    @Permission("can_burn_assets_with_definition")
    @SdkTestId("burn_other_user_asset")
    fun `burn other user asset`(): Unit = runBlocking {
        client.tx {
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            mintAsset(DEFAULT_ASSET_ID, 100)
            grantPermissionToken(
                Permissions.CanBurnAssetWithDefinition,
                IdKey.AssetDefinitionId.type.asName() to DEFAULT_ASSET_DEFINITION_ID.asValue(),
                BOB_ACCOUNT_ID,
            )
        }
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) { burnAsset(DEFAULT_ASSET_ID, 50) }

        val result = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
        assertEquals(50, result.assets[DEFAULT_ASSET_ID]?.value?.cast<AssetValue.Quantity>()?.u32)
    }

    @Test
    @WithIroha([AliceAndBobHasPermissionToMintPublicKeys::class])
    @Feature("Accounts")
    @Story("Account burn a public key")
    @Permission("no_permission_required")
    @SdkTestId("burn_one_of_several_public_keys")
    fun `burn public key`(): Unit = runBlocking {
        // mint public key, because needs at least 2 public keys to burn one of them
        client.tx { mintPublicKey(ALICE_ACCOUNT_ID, generateKeyPair().public.toIrohaPublicKey()) }

        val alicePubKey = ALICE_KEYPAIR.public.toIrohaPublicKey()
        // check public key before burn it
        val query = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val signatories = client.sendQuery(query).signatories

        assertEquals(2, signatories.size)
        assertTrue {
            signatories.any { s ->
                s.payload.contentEquals(alicePubKey.payload)
            }
        }
        client.tx { burnPublicKey(ALICE_ACCOUNT_ID, alicePubKey) }

        // if keys was burned, then peer should return an error due cannot verify signature
        assertFails { client.sendQuery(query) }
    }

    @Test
    @WithIroha([AliceAndBobHasPermissionToMintPublicKeys::class])
    @Feature("Accounts")
    @Story("Account mints a public key")
    @Permission("no_permission_required")
    @SdkTestId("mint_public_key_after_burning_one_public_key")
    fun `burn and mint public key`(): Unit = runBlocking {
        val keyPair = generateKeyPair()

        // mint public key, because needs at least 2 public keys to burn one of them
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            mintPublicKey(BOB_ACCOUNT_ID, keyPair.public.toIrohaPublicKey())
        }
        // check Bob's public key before burn it
        val bobPubKey = keyPair.public.toIrohaPublicKey()
        var query = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        var signatories = client.sendQuery(query).signatories
        assertEquals(2, signatories.size)

        // burn Bob's public key
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) { burnPublicKey(BOB_ACCOUNT_ID, bobPubKey) }

        // check Bob's account has only 1 public key (was 2)
        query = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        signatories = client.sendQuery(query).signatories
        assertEquals(1, signatories.size)

        // generate new key pair without salt for Bob's account
        val newKeyPair = generateKeyPair()

        // change Bob's account public key
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) { mintPublicKey(BOB_ACCOUNT_ID, newKeyPair.public.toIrohaPublicKey()) }

        // check public keys in Bob's account
        val newPubKeyQuery = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val signatoriesWithNewPubKey = client.sendQuery(newPubKeyQuery).signatories
        assertEquals(2, signatoriesWithNewPubKey.size)
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Accounts")
    @Story("Account changes account metadata")
    @Permission("can_set_key_value_in_user_account")
    @SdkTestId("change_account_metadata_by_granted_account")
    fun `change user account metadata`(): Unit = runBlocking {
        val saltKey = "salt"

        // grant permission to Alice to change Bob's account metadata
        client.sendTransaction {
            account(BOB_ACCOUNT_ID)
            grantPermissionToken(
                Permissions.CanSetKeyValueInUserAccount,
                IdKey.AccountId.type.asName() to BOB_ACCOUNT_ID.asValue(),
                ALICE_ACCOUNT_ID,
            )
            buildSigned(BOB_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        // add/update salt value in Bob's account metadata
        val salt = RandomStringUtils.random(10).asValue()
        client.tx { setKeyValue(BOB_ACCOUNT_ID, saltKey.asName(), salt) }

        // check new metadata in Bob's account
        val saltQuery = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val bobAccountMetadata = client.sendQuery(saltQuery).metadata
        assertEquals(salt, bobAccountMetadata.map["salt".asName()])
    }

    @Test
    @WithIroha([AliceAndBobEachHave100Xor::class])
    @Feature("Assets")
    @Story("Account transfers assets")
    @Permission("can_transfer_user_asset")
    @SdkTestId("transfer_asset")
    fun `transfer asset`(): Unit = runBlocking {
        val aliceAssetId = DEFAULT_ASSET_ID
        val bobAssetId = AliceAndBobEachHave100Xor.BOB_ASSET_ID

        assertEquals(100, getAccountAmount(ALICE_ACCOUNT_ID, aliceAssetId))
        assertEquals(100, getAccountAmount(BOB_ACCOUNT_ID, bobAssetId))

        client.tx { transferAsset(aliceAssetId, 40, BOB_ACCOUNT_ID) }
        assertEquals(60, getAccountAmount(ALICE_ACCOUNT_ID, aliceAssetId))
        assertEquals(140, getAccountAmount(BOB_ACCOUNT_ID, bobAssetId))

        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) { transferAsset(bobAssetId, 40, ALICE_ACCOUNT_ID) }
        assertEquals(100, getAccountAmount(ALICE_ACCOUNT_ID, aliceAssetId))
        assertEquals(100, getAccountAmount(BOB_ACCOUNT_ID, bobAssetId))

        val joeDomain = "joe_domain".asDomainId()
        client.tx { registerDomain(joeDomain) }

        val joeId = AccountId("joe".asName(), joeDomain)
        val joeKeyPair = generateKeyPair()
        registerAccount(joeId, joeKeyPair.public.toIrohaPublicKey())

        client.tx {
            grantPermissionToken(
                Permissions.CanTransferUserAssetsToken,
                IdKey.AssetId.type.asName() to aliceAssetId.asValue(),
                joeId,
            )
        }
        client.tx(account = joeId, keyPair = joeKeyPair) {
            transferAsset(aliceAssetId, 40, BOB_ACCOUNT_ID)
        }
        assertEquals(60, getAccountAmount(ALICE_ACCOUNT_ID, aliceAssetId))
        assertEquals(140, getAccountAmount(BOB_ACCOUNT_ID, bobAssetId))
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    @Feature("Assets")
    @Story("Account burns an asset")
    @Permission("no_permission_required")
    @SdkTestId("burn_asset_if_condition")
    @SdkTestId("not_burn_asset_if_condition_not_met")
    fun `burn if condition otherwise not burn`(): Unit = runBlocking {
        val toBurn = 80
        val initAliceAmount = getAccountAmount()

        sendTransactionToBurnIfCondition(initAliceAmount >= toBurn, DEFAULT_ASSET_ID, toBurn)
        val aliceAmountAfterBurn = getAccountAmount()
        assert(aliceAmountAfterBurn == initAliceAmount - toBurn)

        sendTransactionToBurnIfCondition(aliceAmountAfterBurn >= toBurn, DEFAULT_ASSET_ID, toBurn)
        val finalAliceAmount = getAccountAmount()
        assert(finalAliceAmount == aliceAmountAfterBurn)
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    @Feature("Atomicity")
    @Story("Client sends a pair instructions within transaction")
    @Permission("no_permission_required")
    @SdkTestId("pair_instruction")
    fun `pair`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            pair(
                Instructions.burnAsset(DEFAULT_ASSET_ID, 10),
                Instructions.burnAsset(DEFAULT_ASSET_ID, 20),
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        assert(getAccountAmount() == 70L)
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    @Feature("Atomicity")
    @Story("Client sends a multiple instructions within transaction")
    @Permission("no_permission_required")
    @SdkTestId("multiple_instructions_within_transaction")
    fun `instruction sequence committed`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            sequence(
                Instructions.burnAsset(DEFAULT_ASSET_ID, 10),
                Instructions.burnAsset(DEFAULT_ASSET_ID, 20),
                Instructions.burnAsset(DEFAULT_ASSET_ID, 30),
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        assert(getAccountAmount() == 40L)
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Atomicity")
    @Story("Client sends a wrong instruction in transaction")
    @Permission("no_permission_required")
    @SdkTestId("instruction_failed")
    fun `instruction failed`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            fail("FAIL MESSAGE")
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            assertFailsWith<TransactionRejectedException> {
                withTimeout(txTimeout) { d.await() }
            }
        }
    }

    @Test
    @WithIroha([StoreAssetWithMetadata::class])
    @Feature("Assets")
    @Story("Account removes asset metadata")
    @Permission("no_permission_required")
    @SdkTestId("remove_asset_metadata")
    fun `remove asset`(): Unit = runBlocking {
        val assetId = StoreAssetWithMetadata.ASSET_ID
        val assetKey = StoreAssetWithMetadata.ASSET_KEY

        val assetBefore = getAsset(assetId)
        assertEquals(
            StoreAssetWithMetadata.ASSET_VALUE,
            assetBefore.value.cast<AssetValue.Store>().metadata.map[assetKey],
        )
        client.tx { removeKeyValue(assetId, assetKey) }

        val assetAfter = getAsset(assetId)
        assert(assetAfter.value.cast<AssetValue.Store>().metadata.map.isEmpty())
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Assets")
    @Story("Account registers an asset definition")
    @SdkTestId("register_fixed_asset_definition")
    @Story("Account mint an asset")
    @SdkTestId("mint_fixed_asset")
    @Story("Account burn an asset")
    @Permission("no_permission_required")
    @SdkTestId("burn_fixed_asset")
    fun `check assets with type Fixed are properly minted and burned`(): Unit = runBlocking {
        client.tx { registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Fixed()) }

        // counter to track all changes in balance
        var counter = BigDecimal.ZERO
        // count of changes (both mint and burn)
        val probes = 20
        val random = SecureRandom()
        // return positive random number what is never greater than counter
        val getFpNumber = {
            BigDecimal(random.nextDouble() + random.nextInt())
                .abs()
                .stripTrailingZeros()
                .remainder(counter, MathContext.DECIMAL64)
                .setScale(random.nextInt(DEFAULT_SCALE), RoundingMode.DOWN)
        }
        val mintAsset: suspend (BigDecimal) -> Unit = {
            client.tx { mintAsset(DEFAULT_ASSET_ID, it) }
            counter += it
        }
        val burnAsset: suspend (BigDecimal) -> Unit = {
            client.tx { burnAsset(DEFAULT_ASSET_ID, it) }
            counter -= it
        }
        val assertBalance: suspend (BigDecimal) -> Unit = { expectedBalance ->
            QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR)
                .let { query -> client.sendQuery(query) }
                .let { account -> account.assets[DEFAULT_ASSET_ID]?.value }
                .let { value ->
                    value?.cast<AssetValue.Fixed>()?.fixed?.fixedPointOfI64 ?: BigDecimal.ZERO
                }
                .also { actualBalance ->
                    assertTrue("expected value `$expectedBalance`, but was `$actualBalance`") {
                        expectedBalance.compareTo(actualBalance) == 0
                    }
                }
        }
        assertBalance(counter)
        mintAsset(BigDecimal.TEN)
        assertBalance(counter)
        for (i in 0..probes) {
            if (i % 2 == 0) {
                mintAsset(getFpNumber())
            } else {
                burnAsset(getFpNumber())
            }
            assertBalance(counter)
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Assets")
    @Story("Account registers an asset definition")
    @Permission("no_permission_required")
    @SdkTestId("register_asset_definition_with_metadata")
    fun `register asset with metadata`(): Unit = runBlocking {
        val assetKey = Name("asset_metadata_key")
        val assetValue = Value.String("some string value")
        val metadata = Metadata(mapOf(assetKey to assetValue))

        client.tx {
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Store(), metadata)
        }

        QueryBuilder.findAssetDefinitionKeyValueByIdAndKey(DEFAULT_ASSET_DEFINITION_ID, assetKey)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { value ->
                Assertions.assertEquals(
                    value.cast<Value.String>().string,
                    assetValue.string,
                )
            }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Domains")
    @Story("Account registers a domain")
    @Permission("no_permission_required")
    @SdkTestId("register_existence_domain")
    fun `double domain registration should fails`(): Unit = runBlocking {
        val domainId = UUID.randomUUID().toString().asDomainId()
        client.tx { registerDomain(domainId) }
        assertThrows<TransactionRejectedException> {
            runBlocking { client.tx { registerDomain(domainId) } }
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Assets")
    @Story("Account registers an asset definition")
    @SdkTestId("register_asset_definition_with_store_value_type")
    @Permission("can_remove_key_value_in_user_asset")
    @Story("Account registers a role")
    @SdkTestId("register_role")
    @SdkTestId("attach_permissions_to_role")
    @SdkTestId("grant_role_to_account")
    @Story("Account set key value pair")
    @Permission("can_set_key_value_in_user_asset")
    @Feature("Accounts")
    @SdkTestId("set_key_value_in_foreign_asset_after_granting_role")
    fun `register and grant role to account and then revoke it`(): Unit = runBlocking {
        val assetId = AssetId(DEFAULT_ASSET_DEFINITION_ID, BOB_ACCOUNT_ID)
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Store())
        }

        val roleId = RoleId("BOB_ASSET_ACCESS".asName())
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            registerRole(
                roleId,
                PermissionToken(
                    PermissionTokenId(Permissions.CanSetKeyValueUserAssetsToken.type),
                    mapOf(IdKey.AssetId.type.asName() to assetId.toValueId()),
                ),
                PermissionToken(
                    PermissionTokenId(Permissions.CanRemoveKeyValueInUserAssets.type),
                    mapOf(IdKey.AssetId.type.asName() to assetId.toValueId()),
                ),
            )
            grantRole(roleId, ALICE_ACCOUNT_ID)
            setKeyValue(assetId, "key".asName(), "value".asValue())
        }

        QueryBuilder.findAssetById(assetId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { asset ->
                assertTrue(
                    asset.value.cast<AssetValue.Store>()
                        .metadata.map
                        .containsValue("value".asValue()),
                )
            }

        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            revokeRole(roleId, ALICE_ACCOUNT_ID)
        }
        QueryBuilder.findRolesByAccountId(ALICE_ACCOUNT_ID)
            .account(BOB_ACCOUNT_ID)
            .buildSigned(BOB_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { roles ->
                assertTrue(
                    roles.isEmpty(),
                )
            }
    }

    @Test
    @WithIroha(
        [
            DefaultGenesis::class,
            AliceHas100XorAndPermissionToBurn::class,
            StoreAssetWithMetadata::class,
            AliceHasRoleWithAccessToBobsMetadata::class,
            AliceWithTestAssets::class,
            AliceAndBobEachHave100Xor::class,
            XorAndValAssets::class,
            NewAccountWithMetadata::class,
            NewDomainWithMetadata::class,
            RubbishToTestMultipleGenesis::class,
        ],
    )
    @Feature("Configurations")
    @Permission("no_permission_required")
    fun `multiple genesis`(): Unit = runBlocking {
        val assetId = StoreAssetWithMetadata.ASSET_ID
        val assetKey = StoreAssetWithMetadata.ASSET_KEY

        val assetBefore = getAsset(assetId)
        assertEquals(
            StoreAssetWithMetadata.ASSET_VALUE,
            assetBefore.value.cast<AssetValue.Store>().metadata.map[assetKey],
        )
        QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { alice ->
                assertEquals(
                    alice.metadata.map[RubbishToTestMultipleGenesis.ALICE_KEY_VALUE.asName()],
                    RubbishToTestMultipleGenesis.ALICE_KEY_VALUE.asValue(),
                )
            }
        QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(BOB_ACCOUNT_ID)
            .buildSigned(BOB_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { bob ->
                assertEquals(
                    bob.metadata.map[RubbishToTestMultipleGenesis.BOB_KEY_VALUE.asName()],
                    RubbishToTestMultipleGenesis.BOB_KEY_VALUE.asValue(),
                )
            }
        QueryBuilder.findDomainById(DEFAULT_DOMAIN_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { domain ->
                assertEquals(
                    domain.metadata.map[RubbishToTestMultipleGenesis.DOMAIN_KEY_VALUE.asName()],
                    RubbishToTestMultipleGenesis.DOMAIN_KEY_VALUE.asValue(),
                )
            }
    }

    private suspend fun registerAccount(id: AccountId, publicKey: PublicKey) {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAccount(id, listOf(publicKey))
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }

    private suspend fun getAccountAmount(
        accountId: AccountId = ALICE_ACCOUNT_ID,
        assetId: AssetId = DEFAULT_ASSET_ID,
    ): Long {
        return QueryBuilder.findAccountById(accountId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query).assets[assetId]?.value
            }.let { value ->
                (value as? AssetValue.Quantity)?.u32 ?: 0
            }
    }

    private suspend fun sendTransactionToBurnIfCondition(condition: Boolean, assetId: AssetId, toBurn: Int) {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            `if`(
                condition = condition,
                then = Instructions.burnAsset(assetId, toBurn),
                otherwise = Instructions.burnAsset(assetId, 0),
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }

    private suspend fun getAsset(assetId: AssetId? = null): Asset {
        return QueryBuilder.findAssetById(assetId ?: DEFAULT_ASSET_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }
    }
}
