package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.Token
import jp.co.soramitsu.iroha2.generated.datamodel.permission.token.TokenId
import jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedSignedTransaction
import jp.co.soramitsu.iroha2.query.QueryBuilder
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.AliceAndBobEachHave100Xor
import jp.co.soramitsu.iroha2.testengine.AliceHas100XorAndPermissionToBurn
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.BOB_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.DEFAULT_ASSET_DEFINITION_ID
import jp.co.soramitsu.iroha2.testengine.DEFAULT_ASSET_ID
import jp.co.soramitsu.iroha2.testengine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.testengine.DefaultGenesis
import jp.co.soramitsu.iroha2.testengine.IrohaTest
import jp.co.soramitsu.iroha2.testengine.StoreAssetWithMetadata
import jp.co.soramitsu.iroha2.testengine.WithIroha
import jp.co.soramitsu.iroha2.transaction.Instructions
import jp.co.soramitsu.iroha2.transaction.Instructions.fail
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.withTimeout
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.security.KeyPair
import java.security.SecureRandom
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class InstructionsTest : IrohaTest<Iroha2Client>() {

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `register domain instruction committed`(): Unit = runBlocking {
        val domainId = "new_domain_name".asDomainId()
        client.tx { registerDomain(domainId) }

        QueryBuilder.findDomainById(domainId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { result -> assertEquals(result.id, domainId) }
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `register account instruction committed`(): Unit = runBlocking {
        val newAccountId = AccountId("foo".asName(), DEFAULT_DOMAIN_ID)
        client.tx { registerAccount(newAccountId, listOf()) }

        QueryBuilder.findAccountById(newAccountId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { account -> assertEquals(account.id, newAccountId) }
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `register and unregister account instruction committed`(): Unit = runBlocking {
        val newAccountId = AccountId("foo".asName(), DEFAULT_DOMAIN_ID)
        client.tx { registerAccount(newAccountId, listOf()) }

        QueryBuilder.findAccountById(newAccountId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { account -> assertEquals(account.id, newAccountId) }

        client.tx { unregisterAccount(newAccountId) }
        assertThrows<IrohaClientException> {
            runBlocking {
                QueryBuilder.findAccountById(newAccountId)
                    .account(ALICE_ACCOUNT_ID)
                    .buildSigned(ALICE_KEYPAIR)
                    .let { query -> client.sendQuery(query) }
            }
        }
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `register and unregister domain instruction committed`(): Unit = runBlocking {
        val newDomainId = DomainId("foo".asName())
        client.tx { registerDomain(newDomainId) }

        QueryBuilder.findDomainById(newDomainId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { domain -> assertEquals(newDomainId, domain.id) }

        client.tx { unregisterDomain(newDomainId) }

        assertThrows<IrohaClientException> {
            runBlocking {
                QueryBuilder.findDomainById(newDomainId)
                    .account(ALICE_ACCOUNT_ID)
                    .buildSigned(ALICE_KEYPAIR)
                    .let { query -> client.sendQuery(query) }
            }
        }
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `register account with metadata instruction committed`(): Unit = runBlocking {
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
                Pair(cityKey, cityValue)
            )
        )

        val encodedTx = TransactionBuilder {
            account(ALICE_ACCOUNT_ID)
            registerAccount(newAccountId, listOf(), metadata)
        }.buildSigned()
            .let { VersionedSignedTransaction.encode(it) }

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

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `register asset instruction committed`(): Unit = runBlocking {
        client.tx {
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
        }
        val assetDefinitions = QueryBuilder.findAllAssetsDefinitions()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { q -> client.sendQuery(q) }

        assertFalse { assetDefinitions.isEmpty() }
        assetDefinitions.find { it.id == DEFAULT_ASSET_DEFINITION_ID }
            ?: fail("Expected query response contains assetDefinition $DEFAULT_ASSET_DEFINITION_ID, but it is not. Response was $assetDefinitions")
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `store asset instruction committed`(): Unit = runBlocking {
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
                assertEquals(pair3.second.u32, (value.metadata.map[pair3.first]?.cast<Value.U32>())?.u32)
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
    @WithIroha(DefaultGenesis::class)
    fun `grant access to asset key-value committed`(): Unit = runBlocking {
        val aliceAssetId = DEFAULT_ASSET_ID

        // transaction from behalf of Alice. Alice gives permission to Bob to set key-value Asset.Store in her account
        client.tx {
            registerAssetDefinition(aliceAssetId.definitionId, AssetValueType.Store())
            // grant by Alice to Bob permissions to set key value in Asset.Store
            registerPermissionToken(Permissions.CanSetKeyValueUserAssetsToken.type, IdKey.AssetId)
            grantSetKeyValueAsset(aliceAssetId, BOB_ACCOUNT_ID)
        }
        // transaction from behalf of Bob. He tries to set key-value Asset.Store to the Alice account
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
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `mint asset instruction committed`(): Unit = runBlocking {
        client.tx {
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            mintAsset(DEFAULT_ASSET_ID, 5)
        }

        QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { result ->
                assertEquals(5, (result.assets[DEFAULT_ASSET_ID]?.value as? AssetValue.Quantity)?.u32)
            }
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `burn asset instruction committed`(): Unit = runBlocking {
        // check balance before burn
        val query = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        var result = client.sendQuery(query)
        assertEquals(100, (result.assets[DEFAULT_ASSET_ID]?.value as? AssetValue.Quantity)?.u32)

        client.tx { burnAsset(DEFAULT_ASSET_ID, 50) }

        // check balance after burn
        result = client.sendQuery(query)
        assertEquals(50, (result.assets[DEFAULT_ASSET_ID]?.value as? AssetValue.Quantity)?.u32)
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `burn public key instruction committed`(): Unit = runBlocking {
        // mint public key, because needs at least 2 public keys to burn one of them
        mintPublicKey(ALICE_ACCOUNT_ID)

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
    @WithIroha(DefaultGenesis::class)
    fun `burn and mint public key instruction committed`(): Unit = runBlocking {
        // mint public key, because needs at least 2 public keys to burn one of them
        mintPublicKey(BOB_ACCOUNT_ID)

        // check Bob's public key before burn it
        val bobPubKey = BOB_KEYPAIR.public.toIrohaPublicKey()
        var query = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        var signatories = client.sendQuery(query).signatories
        assertEquals(2, signatories.size)

        // burn Bob's public key
        client.tx { burnPublicKey(BOB_ACCOUNT_ID, bobPubKey) }

        // check Bob's account has only 1 public key (was 2)
        query = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        signatories = client.sendQuery(query).signatories
        assertEquals(1, signatories.size)

        // generate new key pair without salt for Bob's account
        val newKeyPair = generateKeyPair()

        // change Bob's account public key
        client.tx { mintPublicKey(BOB_ACCOUNT_ID, newKeyPair.public.toIrohaPublicKey()) }

        // check public keys in Bob's account
        val newPubKeyQuery = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val signatoriesWithNewPubKey = client.sendQuery(newPubKeyQuery).signatories
        assertEquals(2, signatoriesWithNewPubKey.size)
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `change user account metadata`(): Unit = runBlocking {
        val saltKey = "salt"

        // grant permission to Alice to change Bob's account metadata
        client.sendTransaction {
            account(BOB_ACCOUNT_ID)
            registerPermissionToken(Permissions.CanSetKeyValueInUserMetadata.type, IdKey.AccountId)
            grantSetKeyValueAccount(BOB_ACCOUNT_ID, ALICE_ACCOUNT_ID)
            buildSigned(BOB_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        // add\update salt value in Bob's account metadata
        val salt = "ABCDEFG".asValue()
        client.tx { setKeyValue(BOB_ACCOUNT_ID, saltKey.asName(), salt) }

        // check new metadata in Bob's account
        val saltQuery = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val bobAccountMetadata = client.sendQuery(saltQuery).metadata
        assertEquals(salt, bobAccountMetadata.map["salt".asName()])
    }

    @Test
    @WithIroha(AliceAndBobEachHave100Xor::class)
    fun `transfer asset instruction committed`(): Unit = runBlocking {
        val aliceAssetId = DEFAULT_ASSET_ID
        val bobAssetId = AliceAndBobEachHave100Xor.BOB_ASSET_ID

        assertEquals(100, getAccountAmount(ALICE_ACCOUNT_ID, aliceAssetId))
        assertEquals(100, getAccountAmount(BOB_ACCOUNT_ID, bobAssetId))

        client.tx { transferAsset(aliceAssetId, 40, bobAssetId) }

        // check balance after transfer
        assertEquals(60, getAccountAmount(ALICE_ACCOUNT_ID, aliceAssetId))
        assertEquals(140, getAccountAmount(BOB_ACCOUNT_ID, bobAssetId))
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `burn if condition otherwise not burn`(): Unit = runBlocking {
        val toBurn = 80L
        val initAliceAmount = getAccountAmount()

        sendTransactionToBurnIfCondition(initAliceAmount >= toBurn, DEFAULT_ASSET_ID, toBurn)
        val aliceAmountAfterBurn = getAccountAmount()
        assert(aliceAmountAfterBurn == initAliceAmount - toBurn)

        sendTransactionToBurnIfCondition(aliceAmountAfterBurn >= toBurn, DEFAULT_ASSET_ID, toBurn)
        val finalAliceAmount = getAccountAmount()
        assert(finalAliceAmount == aliceAmountAfterBurn)
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `pair instruction committed`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            pair(
                Instructions.burnAsset(DEFAULT_ASSET_ID, 10),
                Instructions.burnAsset(DEFAULT_ASSET_ID, 20)
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        assert(getAccountAmount() == 70L)
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `instruction sequence committed`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            sequence(
                Instructions.burnAsset(DEFAULT_ASSET_ID, 10),
                Instructions.burnAsset(DEFAULT_ASSET_ID, 20),
                Instructions.burnAsset(DEFAULT_ASSET_ID, 30)
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        assert(getAccountAmount() == 40L)
    }

    @Test
    @WithIroha(DefaultGenesis::class)
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
    @WithIroha(StoreAssetWithMetadata::class)
    fun `remove asset instruction committed`(): Unit = runBlocking {
        val assetId = StoreAssetWithMetadata.ASSET_ID
        val assetKey = StoreAssetWithMetadata.ASSET_KEY

        val assetBefore = getAsset(assetId)
        assertEquals(
            StoreAssetWithMetadata.ASSET_VALUE,
            assetBefore.value.cast<AssetValue.Store>().metadata.map[assetKey]
        )

        client.tx { removeKeyValue(assetId, assetKey) }

        val assetAfter = getAsset(assetId)
        assert(assetAfter.value.cast<AssetValue.Store>().metadata.map.isEmpty())
    }

    @Test
    @WithIroha(DefaultGenesis::class)
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
                .let { value -> value?.cast<AssetValue.Fixed>()?.fixed?.fixedPoint ?: BigDecimal.ZERO }
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
    @WithIroha(DefaultGenesis::class)
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
                    assetValue.string
                )
            }
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `double domain registration should fails`(): Unit = runBlocking {
        val domainId = UUID.randomUUID().toString().asDomainId()
        client.tx { registerDomain(domainId) }
        assertThrows<TransactionRejectedException> {
            runBlocking { client.tx { registerDomain(domainId) } }
        }
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `register and grant role to account`(): Unit = runBlocking {
        val assetId = AssetId(DEFAULT_ASSET_DEFINITION_ID, BOB_ACCOUNT_ID)
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Store())
        }

        val roleId = RoleId("BOB_ASSET_ACCESS".asName())
        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            registerPermissionToken(Permissions.CanSetKeyValueUserAssetsToken.type, IdKey.AssetId)
            registerPermissionToken(Permissions.CanRemoveKeyValueInUserAssets.type, IdKey.AssetId)
            registerRole(
                roleId,
                Token(
                    TokenId(Permissions.CanSetKeyValueUserAssetsToken.type),
                    mapOf(IdKey.AssetId.type.asName() to assetId.toValueId())
                ),
                Token(
                    TokenId(Permissions.CanRemoveKeyValueInUserAssets.type),
                    mapOf(IdKey.AssetId.type.asName() to assetId.toValueId())
                )
            )
            grantRole(roleId, ALICE_ACCOUNT_ID)
            setKeyValue(assetId, "key".asName(), "value".asValue())
        }

        QueryBuilder.findAssetById(assetId)
            .account(BOB_ACCOUNT_ID)
            .buildSigned(BOB_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { asset ->
                assertTrue(
                    asset.value
                        .cast<AssetValue.Store>()
                        .metadata.map
                        .containsValue("value".asValue())
                )
            }
    }

    private suspend fun getAccountAmount(
        accountId: AccountId = ALICE_ACCOUNT_ID,
        assetId: AssetId = DEFAULT_ASSET_ID
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

    private suspend fun sendTransactionToBurnIfCondition(condition: Boolean, assetId: AssetId, toBurn: Long) {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            `if`(
                condition = condition,
                then = Instructions.burnAsset(assetId, toBurn),
                otherwise = Instructions.burnAsset(assetId, 0)
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

    private suspend fun mintPublicKey(accountId: AccountId) {
        client.tx {
            mintPublicKey(accountId, generateKeyPair().public.toIrohaPublicKey())
        }
    }

    private suspend fun Iroha2Client.tx(
        account: AccountId = ALICE_ACCOUNT_ID,
        keyPair: KeyPair = ALICE_KEYPAIR,
        builder: TransactionBuilder.() -> Unit = {}
    ) {
        this.sendTransaction {
            account(account)
            builder(this)
            buildSigned(keyPair)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }
}
