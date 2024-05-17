package jp.co.soramitsu.iroha2

import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import jp.co.soramitsu.iroha2.annotations.Permission
import jp.co.soramitsu.iroha2.annotations.Sdk
import jp.co.soramitsu.iroha2.annotations.SdkTestId
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValue
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.BatchedResponse
import jp.co.soramitsu.iroha2.generated.BatchedResponseV1
import jp.co.soramitsu.iroha2.generated.DomainId
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.MetadataValueBox
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.PublicKey
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.SignatureCheckCondition
import jp.co.soramitsu.iroha2.generated.SignedTransaction
import jp.co.soramitsu.iroha2.query.QueryBuilder
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_ID_VALUE
import jp.co.soramitsu.iroha2.testengine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.AliceAndBobEachHave100Xor
import jp.co.soramitsu.iroha2.testengine.AliceAndBobHasPermissionToMintPublicKeys
import jp.co.soramitsu.iroha2.testengine.AliceHas100XorAndPermissionToBurn
import jp.co.soramitsu.iroha2.testengine.AliceHasPermissionToUnregisterDomain
import jp.co.soramitsu.iroha2.testengine.AliceHasRoleWithAccessToBobsMetadata
import jp.co.soramitsu.iroha2.testengine.AliceWithTestAssets
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.BOB_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.BobCanUnregisterAnyRole
import jp.co.soramitsu.iroha2.testengine.DEFAULT_ASSET_DEFINITION_ID
import jp.co.soramitsu.iroha2.testengine.DEFAULT_ASSET_ID
import jp.co.soramitsu.iroha2.testengine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.testengine.DefaultGenesis
import jp.co.soramitsu.iroha2.testengine.FatGenesis
import jp.co.soramitsu.iroha2.testengine.IROHA_CONFIG_DELIMITER
import jp.co.soramitsu.iroha2.testengine.IrohaTest
import jp.co.soramitsu.iroha2.testengine.NewAccountWithMetadata
import jp.co.soramitsu.iroha2.testengine.NewDomainWithMetadata
import jp.co.soramitsu.iroha2.testengine.RubbishToTestMultipleGenesis
import jp.co.soramitsu.iroha2.testengine.StoreAssetWithMetadata
import jp.co.soramitsu.iroha2.testengine.WithDomainTransferredToBob
import jp.co.soramitsu.iroha2.testengine.WithIroha
import jp.co.soramitsu.iroha2.testengine.WithIrohaManual
import jp.co.soramitsu.iroha2.testengine.XorAndValAssets
import jp.co.soramitsu.iroha2.transaction.Instructions
import jp.co.soramitsu.iroha2.transaction.Instructions.fail
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.withTimeout
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.security.SecureRandom
import java.time.Instant
import java.util.UUID
import kotlin.reflect.full.callSuspend
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.jvm.isAccessible
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@Disabled
@Owner("akostyuchenko")
@Sdk("Java/Kotlin")
class InstructionsTest : IrohaTest<Iroha2Client>() {

    @Test
    @Disabled // EXAMPLE
    @WithIrohaManual(
        ["http://localhost:8080", "http://localhost:8081", "http://localhost:8082", "http://localhost:8083"],
        ["http://localhost:8180", "http://localhost:8181", "http://localhost:8182", "http://localhost:8183"],
        ["http://localhost:1337", "http://localhost:1338", "http://localhost:1339", "http://localhost:1340"],
        account = ALICE_ACCOUNT_ID_VALUE,
        "7233bfc89dcbd68c19fde6ce6158225298ec1131b6a130d1aeb454c1ab5183c0",
        "9ac47abf59b356e0bd7dcbbbb4dec080e302156a48ca907e47cb6aea1d32719e",
    )
    fun `register domain with manual initialized Iroha`(): Unit = runBlocking {
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

    @Test
    @Disabled // EXAMPLE
    @WithIrohaManual(
        account = ALICE_ACCOUNT_ID_VALUE,
        publicKey = "7233bfc89dcbd68c19fde6ce6158225298ec1131b6a130d1aeb454c1ab5183c0",
        privateKey = "9ac47abf59b356e0bd7dcbbbb4dec080e302156a48ca907e47cb6aea1d32719e",
        dockerComposeFile = "../../docker-compose/docker-compose.yaml",
    )
    fun `register domain with manual initialized Iroha via docker-compose`(): Unit = runBlocking {
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
        val newAccountId = AccountId(DEFAULT_DOMAIN_ID, "foo".asName())
        client.sendTransaction {
            account(super.account)
            registerAccount(newAccountId, listOf())
            buildSigned(super.keyPair)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        QueryBuilder.findAccountById(newAccountId)
            .account(super.account)
            .buildSigned(super.keyPair)
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
        val joeId = AccountId(DEFAULT_DOMAIN_ID, "foo".asName())
        val joeKeyPair = generateKeyPair()
        client.tx { registerAccount(joeId, listOf(joeKeyPair.public.toIrohaPublicKey())) }

        QueryBuilder.findAccountById(joeId)
            .account(super.account)
            .buildSigned(super.keyPair)
            .let { query -> client.sendQuery(query) }
            .also { account -> assertEquals(account.id, joeId) }

        client.tx(joeId, joeKeyPair) {
            grantPermissionToken(
                Permissions.CanUnregisterAccount,
                joeId.asJsonString(),
                ALICE_ACCOUNT_ID,
            )
            unregisterAccount(joeId)
        }
        assertThrows<IrohaClientException> {
            runBlocking {
                QueryBuilder.findAccountById(joeId)
                    .account(super.account)
                    .buildSigned(super.keyPair)
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
        val definitionId = AssetDefinitionId(DEFAULT_DOMAIN_ID, "XSTUSD".asName())
        client.tx { registerAssetDefinition(definitionId, AssetValueType.numeric()) }

        val assetId = AssetId(definitionId, ALICE_ACCOUNT_ID)
        client.tx { registerAsset(assetId, AssetValue.Numeric(0.asNumeric())) }

        QueryBuilder.findAssetById(assetId)
            .account(super.account)
            .buildSigned(super.keyPair)
            .let { query -> client.sendQuery(query) }
            .also { asset -> assertEquals(asset.id, assetId) }

        client.tx { unregisterAsset(assetId) }
        assertThrows<IrohaClientException> {
            runBlocking {
                QueryBuilder.findAssetById(assetId)
                    .account(super.account)
                    .buildSigned(super.keyPair)
                    .let { query -> client.sendQuery(query) }
            }
        }
    }

    @Test
    @WithIroha([AliceHasPermissionToUnregisterDomain::class])
    @Feature("Domains")
    @Story("Account unregisters a domain")
    @Permission("CanUnregisterDomain")
    @SdkTestId("unregister_domain")
    fun `unregister domain`(): Unit = runBlocking {
        client.tx { unregisterDomain(AliceHasPermissionToUnregisterDomain.NEW_DOMAIN_ID) }

        assertThrows<IrohaClientException> {
            runBlocking {
                QueryBuilder.findDomainById(AliceHasPermissionToUnregisterDomain.NEW_DOMAIN_ID)
                    .account(super.account)
                    .buildSigned(super.keyPair)
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
        val newAccountId = AccountId(DEFAULT_DOMAIN_ID, "foo".asName())
        val addressKey = "address".asName()
        val phoneKey = "phone".asName()
        val emailKey = "email".asName()
        val cityKey = "city".asName()
        val addressValue = "address".asMetadataValueBox()
        val phoneValue = "phone".asMetadataValueBox()
        val emailValue = "email".asMetadataValueBox()
        val cityValue = "city".asMetadataValueBox()
        val metadata = Metadata(
            mapOf(
                Pair(addressKey, addressValue),
                Pair(phoneKey, phoneValue),
                Pair(emailKey, emailValue),
                Pair(cityKey, cityValue),
            ),
        )
        val encodedTx = TransactionBuilder {
            account(super.account)
            registerAccount(newAccountId, listOf(), metadata)
        }.buildSigned().let { SignedTransaction.encode(it) }

        val decodedTx = encodedTx.let { SignedTransaction.decode(it) }
        val signedTx = decodedTx.appendSignatures(ALICE_KEYPAIR)

        client.sendTransaction { signedTx }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        val accountMetadata = QueryBuilder.findAccountById(newAccountId)
            .account(super.account)
            .buildSigned(super.keyPair)
            .let { query -> client.sendQuery(query) }
            .also { account -> assertEquals(account.id, newAccountId) }
            .metadata
        assertEquals(4, accountMetadata.sortedMapOfName.size)
        assertEquals(addressValue, accountMetadata.sortedMapOfName[addressKey])
        assertEquals(phoneValue, accountMetadata.sortedMapOfName[phoneKey])
        assertEquals(emailValue, accountMetadata.sortedMapOfName[emailKey])
        assertEquals(cityValue, accountMetadata.sortedMapOfName[cityKey])
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
            account(super.account)
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.numeric())
            buildSigned(super.keyPair)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
        val assetDefinitions = QueryBuilder.findAllAssetsDefinitions()
            .account(super.account)
            .buildSigned(super.keyPair)
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
        val pair1 = "key1".asName() to "bar".asMetadataValueBox()
        val pair2 = "key2".asName() to true.asMetadataValueBox()
        val pair3 = "key3".asName() to 12345.asMetadataValueBox()

        client.tx {
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Store())
            setKeyValue(DEFAULT_ASSET_ID, pair1.first, pair1.second)
            setKeyValue(DEFAULT_ASSET_ID, pair2.first, pair2.second)
            setKeyValue(DEFAULT_ASSET_ID, pair3.first, pair3.second)
        }

        val findAssetByIdQry = QueryBuilder.findAssetById(DEFAULT_ASSET_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
        val asset = client.sendQuery(findAssetByIdQry)

        assertEquals(DEFAULT_ASSET_ID.definitionId.name, asset.id.definitionId.name)
        assertEquals(DEFAULT_ASSET_ID.definitionId.domainId, asset.id.definitionId.domainId)
        when (val value = asset.value) {
            is AssetValue.Store -> {
                assertEquals(
                    pair1.second.string,
                    value.metadata.sortedMapOfName[pair1.first]?.cast<MetadataValueBox.String>()?.string,
                )
                assertEquals(
                    pair2.second.bool,
                    value.metadata.sortedMapOfName[pair2.first]?.cast<MetadataValueBox.Bool>()?.bool,
                )
                assertEquals(
                    pair3.second.numeric.asBigInteger(),
                    value.metadata.sortedMapOfName[pair3.first]?.cast<MetadataValueBox.Numeric>()?.numeric?.asBigInteger(),
                )
            }

            else -> fail("Expected result asset value has type `AssetValue.Store`, but it was `${asset.value::class.simpleName}`")
        }

        // try to find saved assets by domain name
        val findAssetsByDomainNameQry = QueryBuilder.findAssetsByDomainId(DEFAULT_DOMAIN_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
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
            setKeyValue(ALICE_ACCOUNT_ID, "key".asName(), randomAlphabetic(5000).asMetadataValueBox())
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    fun `domain metadata set key value with permissions`(): Unit = runBlocking {
        val domainId = DomainId(randomAlphabetic(10).asName())
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            registerDomain(domainId)
            grantPermissionToken(
                Permissions.CanSetKeyValueInDomain.type.string,
                domainId.asJsonString(),
                ALICE_ACCOUNT_ID,
            )
        }

        client.tx(ALICE_ACCOUNT_ID, ALICE_KEYPAIR) {
            setKeyValue(domainId, randomAlphabetic(10).asName(), randomAlphabetic(10).asMetadataValueBox())
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Accounts")
    @Story("Account sets key value pair")
    @Permission("CanSetKeyValueInUserAsset")
    @SdkTestId("set_key_value_pair_for_another_account_asset_definition")
    fun `grant access to asset key-value and then revoke`(): Unit = runBlocking {
        val aliceAssetId = DEFAULT_ASSET_ID

        client.tx {
            registerAssetDefinition(aliceAssetId.definitionId, AssetValueType.Store())
            // grant by Alice to Bob permissions to set key value in Asset.Store
            grantPermissionToken(
                Permissions.CanSetKeyValueUserAssetsToken,
                aliceAssetId.asJsonString(),
                BOB_ACCOUNT_ID,
            )
        }
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            setKeyValue(aliceAssetId, "foo".asName(), "bar".asMetadataValueBox())
        }

        val query = QueryBuilder.findAssetById(aliceAssetId)
            .account(super.account)
            .buildSigned(super.keyPair)
        val asset = client.sendQuery(query)

        assertEquals(aliceAssetId.definitionId.name, asset.id.definitionId.name)
        assertEquals(aliceAssetId.definitionId.domainId, asset.id.definitionId.domainId)
        when (val value = asset.value) {
            is AssetValue.Store -> {
                assertEquals(
                    "bar",
                    (value.metadata.sortedMapOfName["foo".asName()]?.cast<MetadataValueBox.String>())?.string,
                )
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
            account(super.account)
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.numeric())
            mintAsset(DEFAULT_ASSET_ID, 5)
            buildSigned(super.keyPair)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
            .let { query -> client.sendQuery(query) }
            .also { result ->
                assertEquals(5, result.assets[DEFAULT_ASSET_ID]?.value?.cast<AssetValue.Numeric>()?.numeric?.asInt())
            }
    }
    // #endregion java_mint_asset

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    @Feature("Assets")
    @Story("Account burns an asset")
    @Permission("no_permission_required")
    @SdkTestId("burn_asset_for_account_in_same_domain")
    fun `burn asset`(): Unit = runBlocking {
        // check balance before burn
        val query = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
        var result = client.sendQuery(query)
        assertEquals(100, result.assets[DEFAULT_ASSET_ID]?.value?.cast<AssetValue.Numeric>()?.numeric?.asInt())

        client.tx { burnAsset(DEFAULT_ASSET_ID, 50) }

        // check balance after burn
        result = client.sendQuery(query)
        assertEquals(50, result.assets[DEFAULT_ASSET_ID]?.value?.cast<AssetValue.Numeric>()?.numeric?.asInt())
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    @Feature("Assets")
    @Story("Account burns an asset")
    @Permission("no_permission_required")
    @SdkTestId("burn_asset_for_account_in_same_domain")
    fun `multi signature transaction`(): Unit = runBlocking {
        val newBobKeyPair = generateKeyPair()
        val newBobPublicKey = newBobKeyPair.public.toIrohaPublicKey()

        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            mintSignatureCheckCondition(
                BOB_ACCOUNT_ID,
                SignatureCheckCondition.AllAccountSignaturesAnd(listOf()),
            )
            mintPublicKey(BOB_ACCOUNT_ID, newBobPublicKey)
        }

        val keyToSuccess = randomAlphabetic(5).asName()
        val valueToSuccess = randomAlphabetic(5).asMetadataValueBox()

        val time = Instant.now().toEpochMilli().toBigInteger()
        val tx = TransactionBuilder {
            creationTimeMillis = time
            account(BOB_ACCOUNT_ID)
            setKeyValue(BOB_ACCOUNT_ID, keyToSuccess, valueToSuccess)
        }
        client.fireAndForget { tx.buildSigned(BOB_KEYPAIR) }
        delay(1000)
        client.sendTransaction { tx.buildSigned(newBobKeyPair) }.also {
            withTimeout(txTimeout) { it.await() }
        }
        // TODO: request /pending_transactions and extract our tx

        val bob = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(BOB_ACCOUNT_ID)
            .buildSigned(BOB_KEYPAIR)
            .let { client.sendQuery(it) }
        assertEquals(bob.metadata.sortedMapOfName[keyToSuccess], valueToSuccess)
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Assets")
    @Story("Account burns an asset")
    @Permission("CanBurnAssetsWithDefinition")
    @SdkTestId("burn_other_user_xasset")
    fun `burn other user asset`(): Unit = runBlocking {
        client.tx {
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.numeric())
            mintAsset(DEFAULT_ASSET_ID, 100)
            grantPermissionToken(
                Permissions.CanBurnAssetWithDefinition,
                DEFAULT_ASSET_DEFINITION_ID.asJsonString(),
                BOB_ACCOUNT_ID,
            )
        }
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) { burnAsset(DEFAULT_ASSET_ID, 50) }

        val result = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
            .let { query -> client.sendQuery(query) }
        assertEquals(50, result.assets[DEFAULT_ASSET_ID]?.value?.cast<AssetValue.Numeric>()?.numeric?.asInt())
    }

    @Test
    @WithIroha([AliceAndBobHasPermissionToMintPublicKeys::class])
    @Feature("Accounts")
    @Story("Account burns a public key")
    @Permission("no_permission_required")
    @SdkTestId("burn_one_of_several_public_keys")
    fun `burn public key`(): Unit = runBlocking {
        // mint public key, because needs at least 2 public keys to burn one of them
        client.tx { mintPublicKey(ALICE_ACCOUNT_ID, generateKeyPair().public.toIrohaPublicKey()) }

        val alicePubKey = ALICE_KEYPAIR.public.toIrohaPublicKey()
        // check public key before burn it
        val query = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
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
            .account(super.account)
            .buildSigned(super.keyPair)
        var signatories = client.sendQuery(query).signatories
        assertEquals(2, signatories.size)

        // burn Bob's public key
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) { burnPublicKey(BOB_ACCOUNT_ID, bobPubKey) }

        // check Bob's account has only 1 public key (was 2)
        query = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
        signatories = client.sendQuery(query).signatories
        assertEquals(1, signatories.size)

        // generate new key pair without salt for Bob's account
        val newKeyPair = generateKeyPair()

        // change Bob's account public key
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) { mintPublicKey(BOB_ACCOUNT_ID, newKeyPair.public.toIrohaPublicKey()) }

        // check public keys in Bob's account
        val newPubKeyQuery = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
        val signatoriesWithNewPubKey = client.sendQuery(newPubKeyQuery).signatories
        assertEquals(2, signatoriesWithNewPubKey.size)
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Accounts")
    @Story("Account changes account metadata")
    @Permission("CanSetKeyValueInUserAccount")
    @SdkTestId("change_account_metadata_by_granted_account")
    fun `change user account metadata`(): Unit = runBlocking {
        val saltKey = "salt"

        // grant permission to Alice to change Bob's account metadata
        client.sendTransaction {
            account(BOB_ACCOUNT_ID)
            grantPermissionToken(
                Permissions.CanSetKeyValueInUserAccount,
                BOB_ACCOUNT_ID.asJsonString(),
                ALICE_ACCOUNT_ID,
            )
            buildSigned(BOB_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        // add/update salt value in Bob's account metadata
        val salt = randomAlphabetic(10).asMetadataValueBox()
        client.tx { setKeyValue(BOB_ACCOUNT_ID, saltKey.asName(), salt) }

        // check new metadata in Bob's account
        val saltQuery = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
        val bobAccountMetadata = client.sendQuery(saltQuery).metadata
        assertEquals(salt, bobAccountMetadata.sortedMapOfName["salt".asName()])
    }

    @Test
    @WithIroha([AliceAndBobEachHave100Xor::class])
    @Feature("Assets")
    @Story("Account transfers assets")
    @Permission("CanTransferUserAsset")
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

        val joeId = AccountId(joeDomain, "joe".asName())
        val joeKeyPair = generateKeyPair()
        registerAccount(joeId, joeKeyPair.public.toIrohaPublicKey())

        client.tx {
            grantPermissionToken(
                Permissions.CanTransferUserAssetsToken,
                aliceAssetId.asJsonString(),
                joeId,
            )
        }
        client.tx(account = joeId, joeKeyPair) {
            transferAsset(aliceAssetId, 40, BOB_ACCOUNT_ID)
        }
        assertEquals(60, getAccountAmount(ALICE_ACCOUNT_ID, aliceAssetId))
        assertEquals(140, getAccountAmount(BOB_ACCOUNT_ID, bobAssetId))
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Atomicity")
    @Story("Client sends a wrong instruction in transaction")
    @Permission("no_permission_required")
    @SdkTestId("instruction_failed")
    fun `instruction failed`(): Unit = runBlocking {
        client.sendTransaction {
            account(super.account)
            fail("FAIL MESSAGE")
            buildSigned(super.keyPair)
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
            assetBefore.value.cast<AssetValue.Store>().metadata.sortedMapOfName[assetKey],
        )
        client.tx { removeKeyValue(assetId, assetKey) }

        val assetAfter = getAsset(assetId)
        assert(assetAfter.value.cast<AssetValue.Store>().metadata.sortedMapOfName.isEmpty())
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Assets")
    @Story("Account registers an asset definition")
    @SdkTestId("register_fixed_asset_definition")
    @Story("Account mints an asset")
    @SdkTestId("mint_fixed_asset")
    @Story("Account burns an asset")
    @Permission("no_permission_required")
    @SdkTestId("burn_fixed_asset")
    fun `check assets with type Fixed are properly minted and burned`(): Unit = runBlocking {
        client.tx { registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.numeric()) }

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
                .account(super.account)
                .buildSigned(super.keyPair)
                .let { query -> client.sendQuery(query) }
                .let { account -> account.assets[DEFAULT_ASSET_ID]?.value }
                .let { value ->
                    value?.cast<AssetValue.Numeric>()?.numeric?.asBigDecimal() ?: BigDecimal.ZERO
                }.also { actualBalance ->
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
        val assetValue = MetadataValueBox.String("some string value")
        val metadata = Metadata(mapOf(assetKey to assetValue))

        client.tx {
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Store(), metadata)
        }

        QueryBuilder.findAssetDefinitionKeyValueByIdAndKey(DEFAULT_ASSET_DEFINITION_ID, assetKey)
            .account(super.account)
            .buildSigned(super.keyPair)
            .let { query -> client.sendQuery(query) }
            .also { value ->
                Assertions.assertEquals(
                    value.cast<MetadataValueBox.String>().string,
                    assetValue.string,
                )
            }
    }

    @Test
    @WithIroha([DefaultGenesis::class], fetchSize = 111)
    fun `iroha respond with given fetch size`(): Unit = runBlocking {
        val fetchSize = 111
        repeat(2) { i ->
            val isi = mutableListOf<InstructionBox>()
            val tx = TransactionBuilder {
                account(ALICE_ACCOUNT_ID)
            }
            repeat(100) { j ->
                val definitionId = AssetDefinitionId(DEFAULT_DOMAIN_ID, "ASSET_${j}_$i".asName())
                isi.add(Instructions.registerAssetDefinition(definitionId, AssetValueType.Store()))
                isi.add(
                    Instructions.setKeyValue(
                        AssetId(definitionId, ALICE_ACCOUNT_ID),
                        randomAlphabetic(10).asName(),
                        randomAlphabetic(100).asMetadataValueBox(),
                    ),
                )
            }
            tx.instructions(isi)

            client.sendTransaction { tx.buildSigned(ALICE_KEYPAIR) }.let {
                withTimeout(txTimeout) {
                    it.await()
                }
            }
        }

        val query = QueryBuilder.findAllAssets()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val method = Iroha2Client::class.declaredMemberFunctions.firstOrNull { it.name == "sendQueryRequest" }

        val response = method?.let {
            it.isAccessible = true
            it.callSuspend(client, query, null, null, null, null)
        }
        val vec = response?.cast<BatchedResponse.V1>()?.batchedResponseV1
            ?.cast<BatchedResponseV1<*>>()?.batch
            ?.cast<MetadataValueBox.Vec>()?.vec

        assertEquals(fetchSize, vec?.size)
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Domains")
    @Story("Account registers a domain")
    @Permission("no_permission_required")
    @SdkTestId("register_existence_domain")
    fun `double domain registration should fail`(): Unit = runBlocking {
        val domainId = UUID.randomUUID().toString().asDomainId()
        client.tx { registerDomain(domainId) }
        assertThrows<TransactionRejectedException> {
            runBlocking { client.tx { registerDomain(domainId) } }
        }
    }

    @Test
    @WithIroha([BobCanUnregisterAnyRole::class])
    @Feature("Assets")
    @Story("Account registers an asset definition")
    @SdkTestId("register_asset_definition_with_store_value_type")
    @Permission("CanRemoveKeyValueInUserAsset")
    @Story("Account registers a role")
    @SdkTestId("register_role")
    @SdkTestId("attach_permissions_to_role")
    @SdkTestId("grant_role_to_account")
    @Story("Account sets key value pair")
    @Permission("CanSetKeyValueInUserAsset")
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
                    Permissions.CanSetKeyValueUserAssetsToken.type,
                    assetId.asStringWithJson(),
                ),
                PermissionToken(
                    Permissions.CanRemoveKeyValueInUserAssets.type,
                    assetId.asStringWithJson(),
                ),
            )
            grantRole(roleId, ALICE_ACCOUNT_ID)
            setKeyValue(assetId, "key".asName(), "value".asMetadataValueBox())
        }

        QueryBuilder.findAssetById(assetId)
            .account(super.account)
            .buildSigned(super.keyPair)
            .let { query -> client.sendQuery(query) }
            .also { asset ->
                assertTrue(
                    asset.value.cast<AssetValue.Store>()
                        .metadata.sortedMapOfName
                        .containsValue("value".asMetadataValueBox()),
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
        QueryBuilder.findAllRoles()
            .account(BOB_ACCOUNT_ID)
            .buildSigned(BOB_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .firstOrNull { it.id == roleId }
            .also { assertNotNull(it) }

        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            unregisterRole(roleId)
        }
        QueryBuilder.findAllRoles()
            .account(BOB_ACCOUNT_ID)
            .buildSigned(BOB_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .firstOrNull { it.id == roleId }
            .also { assertNull(it) }
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
            assetBefore.value.cast<AssetValue.Store>().metadata.sortedMapOfName[assetKey],
        )
        QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
            .let { query -> client.sendQuery(query) }
            .also { alice ->
                assertEquals(
                    alice.metadata.sortedMapOfName[RubbishToTestMultipleGenesis.ALICE_KEY_VALUE.asName()],
                    RubbishToTestMultipleGenesis.ALICE_KEY_VALUE.asMetadataValueBox(),
                )
            }
        QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(BOB_ACCOUNT_ID)
            .buildSigned(BOB_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { bob ->
                assertEquals(
                    bob.metadata.sortedMapOfName[RubbishToTestMultipleGenesis.BOB_KEY_VALUE.asName()],
                    RubbishToTestMultipleGenesis.BOB_KEY_VALUE.asMetadataValueBox(),
                )
            }
        QueryBuilder.findDomainById(DEFAULT_DOMAIN_ID)
            .account(super.account)
            .buildSigned(super.keyPair)
            .let { query -> client.sendQuery(query) }
            .also { domain ->
                assertEquals(
                    domain.metadata.sortedMapOfName[RubbishToTestMultipleGenesis.DOMAIN_KEY_VALUE.asName()],
                    RubbishToTestMultipleGenesis.DOMAIN_KEY_VALUE.asMetadataValueBox(),
                )
            }
    }

    @Test
    @WithIroha([WithDomainTransferredToBob::class])
    @Feature("Domains")
    @Story("Account transfers domain ownership")
    @SdkTestId("transfer_domain_ownership_in_genesis")
    fun `transfer domain ownership in genesis`(): Unit = runBlocking {
        val key = randomAlphabetic(5).asName()
        val value = randomAlphabetic(5).asMetadataValueBox()
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            setKeyValue(WithDomainTransferredToBob.DOMAIN_ID, key, value)
        }
        val extractedValue = QueryBuilder.findDomainById(WithDomainTransferredToBob.DOMAIN_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .metadata.sortedMapOfName[key]
        assertEquals(value.string, extractedValue?.cast<MetadataValueBox.String>()?.string)
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Domains")
    @Story("Account transfers domain ownership")
    @SdkTestId("transfer_domain_ownership")
    fun `transfer domain ownership`(): Unit = runBlocking {
        val domainId = "Kingdom".asDomainId()
        client.tx(ALICE_ACCOUNT_ID, ALICE_KEYPAIR) { registerDomain(domainId) }

        assertFailsWith(TransactionRejectedException::class) {
            client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
                setKeyValue(domainId, randomAlphabetic(5).asName(), randomAlphabetic(5).asMetadataValueBox())
            }
        }
        var kingdomDomainOwnedBy = QueryBuilder.findDomainById(domainId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }.ownedBy
        assertEquals(ALICE_ACCOUNT_ID, kingdomDomainOwnedBy)

        client.tx(ALICE_ACCOUNT_ID, ALICE_KEYPAIR) {
            transferDomainOwnership(ALICE_ACCOUNT_ID, domainId, BOB_ACCOUNT_ID)
        }
        kingdomDomainOwnedBy = QueryBuilder.findDomainById(domainId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }.ownedBy
        assertEquals(BOB_ACCOUNT_ID, kingdomDomainOwnedBy)

        val key = randomAlphabetic(5).asName()
        val value = randomAlphabetic(5).asMetadataValueBox()
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) { setKeyValue(domainId, key, value) }

        val extractedValue = QueryBuilder.findDomainById(domainId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .metadata.sortedMapOfName[key]
        assertEquals(value.string, extractedValue?.cast<MetadataValueBox.String>()?.string)
    }

    @Test
    @WithIroha([FatGenesis::class])
    fun `fat genesis apply`(): Unit = runBlocking {
        QueryBuilder.findAllAccounts()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { accounts -> assert(accounts.any { it.id == ALICE_ACCOUNT_ID }) }
            .also { accounts -> assert(accounts.any { it.id == BOB_ACCOUNT_ID }) }
    }

    private suspend fun registerAccount(id: AccountId, publicKey: PublicKey) {
        client.sendTransaction {
            account(super.account)
            registerAccount(id, listOf(publicKey))
            buildSigned(super.keyPair)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }

    private suspend fun getAccountAmount(
        accountId: AccountId = ALICE_ACCOUNT_ID,
        assetId: AssetId = DEFAULT_ASSET_ID,
    ) = QueryBuilder.findAccountById(accountId)
        .account(super.account)
        .buildSigned(super.keyPair)
        .let { query ->
            client.sendQuery(query).assets[assetId]?.value
        }.let { value ->
            (value as? AssetValue.Numeric)?.numeric?.asLong() ?: 0
        }

    private suspend fun getAsset(assetId: AssetId? = null) = QueryBuilder
        .findAssetById(assetId ?: DEFAULT_ASSET_ID)
        .account(super.account)
        .buildSigned(super.keyPair)
        .let { query ->
            client.sendQuery(query)
        }
}
