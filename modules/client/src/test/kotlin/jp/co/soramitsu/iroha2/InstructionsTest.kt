package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.engine.AliceAndBobEachHave100Xor
import jp.co.soramitsu.iroha2.engine.AliceHas100XorAndPermissionToBurn
import jp.co.soramitsu.iroha2.engine.BOB_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.BOB_KEYPAIR
import jp.co.soramitsu.iroha2.engine.DEFAULT_ASSET_DEFINITION_ID
import jp.co.soramitsu.iroha2.engine.DEFAULT_ASSET_ID
import jp.co.soramitsu.iroha2.engine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.engine.IrohaRunnerExtension
import jp.co.soramitsu.iroha2.engine.StoreAssetWithMetadata
import jp.co.soramitsu.iroha2.engine.WithIroha
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.security.SecureRandom
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(IrohaRunnerExtension::class)
@Timeout(40)
class InstructionsTest {

    lateinit var client: Iroha2Client

    @Test
    @WithIroha
    fun `register domain instruction committed`(): Unit = runBlocking {
        val domainId = "new_domain_name".asDomainId()
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerDomain(domainId)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        QueryBuilder.findDomainById(domainId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { result -> assertEquals(result.id, domainId) }
    }

    @Test
    @WithIroha
    fun `register account instruction committed`(): Unit = runBlocking {
        val newAccountId = AccountId("foo".asName(), DEFAULT_DOMAIN_ID)
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerAccount(newAccountId, listOf())
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        QueryBuilder.findAccountById(newAccountId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { account -> assertEquals(account.id, newAccountId) }
    }

    @Test
    @WithIroha
    fun `register asset instruction committed`(): Unit = runBlocking {
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerAsset(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        val query = QueryBuilder.findAllAssetsDefinitions()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val assetDefinitions = client.sendQuery(query)
        assertFalse { assetDefinitions.isEmpty() }
        assetDefinitions.find { it.id == DEFAULT_ASSET_DEFINITION_ID }
            ?: fail("Expected query response contains assetDefinition $DEFAULT_ASSET_DEFINITION_ID, but it is not. Response was $assetDefinitions")
    }

    @Test
    @WithIroha
    fun `store asset instruction committed`(): Unit = runBlocking {
        val pair1 = "key1".asName() to "bar".asValue()
        val pair2 = "key2".asName() to true.asValue()
        val pair3 = "key3".asName() to 12345.asValue()

        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAsset(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Store())
            setKeyValue(DEFAULT_ASSET_ID, pair1.first, pair1.second)
            setKeyValue(DEFAULT_ASSET_ID, pair2.first, pair2.second)
            setKeyValue(DEFAULT_ASSET_ID, pair3.first, pair3.second)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
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
    @WithIroha
    fun `grant access to asset key-value committed`(): Unit = runBlocking {
        val aliceAssetId = DEFAULT_ASSET_ID

        // transaction from behalf of Alice. Alice gives permission to Bob to set key-value Asset.Store in her account
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            // register asset with type store
            registerAsset(aliceAssetId.definitionId, AssetValueType.Store())
            // grant by Alice to Bob permissions to set key value in Asset.Store
            grantSetKeyValueAsset(aliceAssetId, BOB_ACCOUNT_ID)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        // transaction from behalf of Bob. He tries to set key-value Asset.Store to the Alice account
        client.sendTransaction {
            account(BOB_ACCOUNT_ID)
            setKeyValue(aliceAssetId, "foo", "bar".asValue())
            buildSigned(BOB_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
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
    @WithIroha
    fun `mint asset instruction committed`(): Unit = runBlocking {
        // currently Iroha2 does not support registering an asset and minting the asset in the same transaction,
        // so below 2 separate transaction created
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAsset(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            mintAsset(DEFAULT_ASSET_ID, 5)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
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

        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            burnAsset(DEFAULT_ASSET_ID, 50)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        // check balance after burn
        result = client.sendQuery(query)
        assertEquals(50, (result.assets[DEFAULT_ASSET_ID]?.value as? AssetValue.Quantity)?.u32)
    }

    @Test
    @WithIroha
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
        assertContentEquals(alicePubKey.payload, signatories.first().payload)

        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            burnPublicKey(ALICE_ACCOUNT_ID, alicePubKey)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        // if keys was burned, then peer should return an error due cannot verify signature
        assertFails {
            client.sendQuery(query)
        }
    }

    @Test
    @WithIroha
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
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            burnPublicKey(BOB_ACCOUNT_ID, bobPubKey)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        // check Bob's account has only 1 public key (was 2)
        query = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        signatories = client.sendQuery(query).signatories
        assertEquals(1, signatories.size)

        // generate new key pair without salt for Bob's account
        val newKeyPair = generateKeyPair()

        // change Bob's account public key
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            mintPublicKey(BOB_ACCOUNT_ID, newKeyPair.public.toIrohaPublicKey())
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        // check public keys in Bob's account
        val newPubKeyQuery = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val signatoriesWithNewPubKey = client.sendQuery(newPubKeyQuery).signatories
        assertEquals(2, signatoriesWithNewPubKey.size)
    }

    @Test
    @WithIroha
    fun `change user account metadata`(): Unit = runBlocking {
        val saltKey = "salt"

        // grant permission to Alice to change Bob's account metadata
        client.sendTransaction {
            account(BOB_ACCOUNT_ID)
            grantSetKeyValueAccount(BOB_ACCOUNT_ID, ALICE_ACCOUNT_ID)
            buildSigned(BOB_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        // check permission
        val permissionQuery = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val permissionTokens = client.sendQuery(permissionQuery).permissionTokens
        assertEquals(1, permissionTokens.size)

        // add\update salt value in Bob's account metadata
        val salt = "ABCDEFG".asValue()
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            setKeyValue(BOB_ACCOUNT_ID, saltKey.asName(), salt)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

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

        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            transferAsset(aliceAssetId, 40, bobAssetId)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        // check balance after transfer
        assertEquals(60, getAccountAmount(ALICE_ACCOUNT_ID, aliceAssetId))
        assertEquals(140, getAccountAmount(BOB_ACCOUNT_ID, bobAssetId))
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `burn if condition otherwise not burn`(): Unit = runBlocking {
        val toBurn = 80L

        QueryBuilder.findAllDomains()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.let { value ->
                println(value)
            }

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
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
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
                Instructions.burnAsset(DEFAULT_ASSET_ID, 30),
            )
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        assert(getAccountAmount() == 40L)
    }

    @Test
    @WithIroha
    fun `instruction failed`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            fail("FAIL MESSAGE")
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertThrows(ExecutionException::class.java) {
                it.get(10, TimeUnit.SECONDS)
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

        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            removeKeyValue(assetId, assetKey)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        val assetAfter = getAsset(assetId)
        assert(assetAfter.value.cast<AssetValue.Store>().metadata.map.isEmpty())
    }

    @Test
    @WithIroha
    fun `check assets with type Fixed are properly minted and burned`(): Unit = runBlocking {
        // register an asset with type `Fixed`
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerAsset(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Fixed())
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

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
            client.sendTransaction {
                account(ALICE_ACCOUNT_ID)
                mintAsset(DEFAULT_ASSET_ID, it)
                buildSigned(ALICE_KEYPAIR)
            }.also {
                Assertions.assertDoesNotThrow {
                    it.get(10, TimeUnit.SECONDS)
                }
            }
            counter += it
        }
        val burnAsset: suspend (BigDecimal) -> Unit = {
            client.sendTransaction {
                account(ALICE_ACCOUNT_ID)
                burnAsset(DEFAULT_ASSET_ID, it)
                buildSigned(ALICE_KEYPAIR)
            }.also {
                Assertions.assertDoesNotThrow {
                    it.get(10, TimeUnit.SECONDS)
                }
            }
            counter -= it
        }
        val assertBalance: suspend (BigDecimal) -> Unit = { expectedBalance ->
            QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR)
                .let { query -> client.sendQuery(query).assets[DEFAULT_ASSET_ID]?.value }
                .let { value -> (value as? AssetValue.Fixed)?.fixed?.fixedPoint ?: BigDecimal.ZERO }
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
    @WithIroha
    fun `register peer instruction committed`(): Unit = runBlocking {
        val address = "127.0.0.1:1338"
        val payload = "ed012076cd895028f2d9d520d6534abd78def38734b658f9400c31b3212ed42a423ee3".fromHex()

        registerPeer(address, payload)
        assertTrue(isPeerAvailable(address, payload))
    }

    // TODO: unregister peer test (https://github.com/hyperledger/iroha/issues/1726)

    private suspend fun isPeerAvailable(address: String, payload: ByteArray): Boolean {
        return QueryBuilder.findAllPeers()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.any { peer ->
                peer.id.address == address && peer.id.publicKey.payload.contentEquals(payload)
            }
    }

    private suspend fun unregisterPeer(address: String, payload: ByteArray) {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            unregisterPeer(address, payload)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(15, TimeUnit.SECONDS)
            }
        }
    }

    private suspend fun registerPeer(address: String, payload: ByteArray) {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerPeer(address, payload)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(15, TimeUnit.SECONDS)
            }
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
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
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
        generateKeyPair().let { pair ->
            client.sendTransaction {
                account(ALICE_ACCOUNT_ID)
                mintPublicKey(accountId, pair.public.toIrohaPublicKey())
                buildSigned(ALICE_KEYPAIR)
            }
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }
    }
}
