package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.DEFAULT_DOMAIN_NAME
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.testcontainers.DEFAULT_KEYPAIR
import jp.co.soramitsu.iroha2.testcontainers.IrohaContainer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import kotlin.test.fail
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

@Execution(ExecutionMode.CONCURRENT)
@Timeout(10)
class InstructionsTest {

    lateinit var client: Iroha2Client
    lateinit var irohaContainer: IrohaContainer

    @BeforeEach
    fun init() {
        irohaContainer = IrohaContainer()
        irohaContainer.start()
        client = Iroha2Client(irohaContainer.getApiUrl())
    }

    @AfterEach
    fun tearDown() {
        irohaContainer.stop()
        client.close()
    }

    @Test
    fun `register account instruction committed`() {
        val newAccountId = AccountId("foo", DEFAULT_DOMAIN_NAME)
        Assertions.assertDoesNotThrow {
            client.sendTransactionAsync {
                accountId = ALICE_ACCOUNT_ID
                registerAccount(newAccountId, mutableListOf())
                buildSigned(DEFAULT_KEYPAIR)
            }.get(10, TimeUnit.SECONDS)
        }
        client.sendQuery(::accountExtractor) {
            accountId = ALICE_ACCOUNT_ID
            findAccountById(newAccountId)
            buildSigned(DEFAULT_KEYPAIR)
        }
    }

    @Test
    fun `store asset instruction committed`() {
        val assetDefinition = DefinitionId("xor", DEFAULT_DOMAIN_NAME)
        val assetId = AssetId(assetDefinition, ALICE_ACCOUNT_ID)
        val pair1 = "key1" to "bar".asValue()
        val pair2 = "key2" to true.asValue()
        val pair3 = "key3" to 12345.asValue()

        Assertions.assertDoesNotThrow {
            client.sendTransactionAsync {
                account(ALICE_ACCOUNT_ID)
                registerAsset(assetDefinition, AssetValueType.Store())
                storeAsset(assetId, pair1.first, pair1.second)
                storeAsset(assetId, pair2.first, pair2.second)
                storeAsset(assetId, pair3.first, pair3.second)
                buildSigned(DEFAULT_KEYPAIR)
            }.get(10, TimeUnit.SECONDS)
        }

        val asset = client.sendQuery(::assetExtractor) {
            accountId = ALICE_ACCOUNT_ID
            findAssetById(assetId)
            buildSigned(DEFAULT_KEYPAIR)
        }

        assertEquals(assetId.definitionId.name, asset.id.definitionId.name)
        assertEquals(assetId.definitionId.domainName, asset.id.definitionId.domainName)
        when (val value = asset.value) {
            is AssetValue.Store -> {
                assertEquals(pair1.second.string, value.metadata.map[pair1.first]?.cast<Value.String>()?.string)
                assertEquals(pair2.second.bool, value.metadata.map[pair2.first]?.cast<Value.Bool>()?.bool)
                assertEquals(pair3.second.u32, (value.metadata.map[pair3.first]?.cast<Value.U32>())?.u32)
            }
            else -> fail("Expected result asset value has type `AssetValue.Store`, but it was `${asset.value::class.simpleName}`")
        }
    }

    @Test
    fun `grant access to asset key-value committed`() {
        val assetDefinition = DefinitionId("xor", DEFAULT_DOMAIN_NAME)
        val aliceAssetId = AssetId(assetDefinition, ALICE_ACCOUNT_ID)
        val bobAccountId = AccountId("bob", DEFAULT_DOMAIN_NAME)
        val bobKeypair = generateKeyPair()

        // transaction from behalf of Alice. Alice gives permission to Bob to set key-value Asset.Store in her account
        Assertions.assertDoesNotThrow {
            client.sendTransactionAsync {
                account(ALICE_ACCOUNT_ID)
                // register asset with type store
                registerAsset(assetDefinition, AssetValueType.Store())
                // register Bob's account
                registerAccount(bobAccountId, mutableListOf(bobKeypair.public.toIrohaPublicKey()))
                // grant by Alice to Bob permissions to set key value in Asset.Store
                grantPermissionsToKeyValueAsset(aliceAssetId, bobAccountId)
                buildSigned(DEFAULT_KEYPAIR)
            }.get(10, TimeUnit.SECONDS)
        }

        // transaction from behalf of Bob. He tries to set key-value Asset.Store to the Alice account
        Assertions.assertDoesNotThrow {
            client.sendTransactionAsync {
                account(bobAccountId)
                storeAsset(aliceAssetId, "foo", "bar".asValue())
                buildSigned(bobKeypair)
            }.get(10, TimeUnit.SECONDS)
        }

        val asset = client.sendQuery(::assetExtractor) {
            accountId = ALICE_ACCOUNT_ID
            findAssetById(aliceAssetId)
            buildSigned(DEFAULT_KEYPAIR)
        }

        assertEquals(aliceAssetId.definitionId.name, asset.id.definitionId.name)
        assertEquals(aliceAssetId.definitionId.domainName, asset.id.definitionId.domainName)
        when (val value = asset.value) {
            is AssetValue.Store -> {
                assertEquals("bar", (value.metadata.map["foo"]?.cast<Value.String>())?.string)
            }
            else -> fail("Expected result asset value has type `AssetValue.Store`, but it was `${asset.value::class.simpleName}`")
        }
    }
}
