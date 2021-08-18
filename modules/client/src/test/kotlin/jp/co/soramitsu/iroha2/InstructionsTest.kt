package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.testcontainers.DEFAULT_KEYPAIR
import jp.co.soramitsu.iroha2.testcontainers.IrohaContainer
import jp.co.soramitsu.iroha2.utils.accountExtractor
import jp.co.soramitsu.iroha2.utils.assetExtractor
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import kotlin.test.assertEquals
import kotlin.test.fail
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
        val newAccountId = Id("foo", "wonderland")
        Assertions.assertDoesNotThrow {
            client.sendTransactionAsync {
                accountId = ALICE_ACCOUNT_ID
                instruction { registerAccount(newAccountId, mutableListOf()) }
                buildSigned(DEFAULT_KEYPAIR)
            }.join()
        }
        val account = client.sendQuery(::accountExtractor) {
            accountId = ALICE_ACCOUNT_ID
            query { findAccountById(newAccountId) }
            buildSigned(DEFAULT_KEYPAIR)
        }
    }

    @Test
    fun `store asset instruction committed`() {
        val assetDefinition = DefinitionId("xor", "wonderland")
        val assetId = AssetId(assetDefinition, ALICE_ACCOUNT_ID)
        val pair1 = "key1" to "bar".asValue()
        val pair2 = "key2" to true.asValue()
        val pair3 = "key3" to 12345.asValue()

        Assertions.assertDoesNotThrow {
            client.sendTransactionAsync {
                account("alice", "wonderland")
                instruction { registerAsset(assetDefinition, AssetValueType.Store()) }
                instruction { storeAsset(assetId, pair1.first, pair1.second) }
                instruction { storeAsset(assetId, pair2.first, pair2.second) }
                instruction { storeAsset(assetId, pair3.first, pair3.second) }
                buildSigned(DEFAULT_KEYPAIR)
            }.join()
        }

        val asset = client.sendQuery(::assetExtractor) {
            accountId = ALICE_ACCOUNT_ID
            query {
                findAssetById(assetId)
            }
            buildSigned(DEFAULT_KEYPAIR)
        }

        assertEquals(assetId.definitionId.name, asset.id.definitionId.name)
        assertEquals(assetId.definitionId.domainName, asset.id.definitionId.domainName)
        when (val value = asset.value) {
            is AssetValue.Store -> {
                assertEquals(pair1.second.string, (value.metadata.map[pair1.first] as? Value.String)?.string)
                assertEquals(pair2.second.bool, (value.metadata.map[pair2.first] as? Value.Bool)?.bool)
                assertEquals(pair3.second.u32, (value.metadata.map[pair3.first] as? Value.U32)?.u32)
            }
            else -> fail("Expected result asset value has type `AssetValue.Store`, but it was `${asset.value::class.simpleName}`")
        }
    }
}
