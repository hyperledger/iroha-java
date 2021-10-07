package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.engine.AliceAndBobEachHave100Xor
import jp.co.soramitsu.iroha2.engine.AliceHas100XorAndPermissionToBurn
import jp.co.soramitsu.iroha2.engine.BOB_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.BOB_KEYPAIR
import jp.co.soramitsu.iroha2.engine.DEFAULT_ASSET_DEFINITION_ID
import jp.co.soramitsu.iroha2.engine.DEFAULT_ASSET_ID
import jp.co.soramitsu.iroha2.engine.DEFAULT_DOMAIN_NAME
import jp.co.soramitsu.iroha2.engine.IrohaRunnerExtension
import jp.co.soramitsu.iroha2.engine.WithIroha
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.util.concurrent.TimeUnit
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.fail
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(IrohaRunnerExtension::class)
@Timeout(30)
class InstructionsTest {

    lateinit var client: Iroha2Client

    @Test
    @WithIroha
    fun `register account instruction committed`(): Unit = runBlocking {
        val newAccountId = AccountId("foo", DEFAULT_DOMAIN_NAME)
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerAccount(newAccountId, mutableListOf())
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }
        val query = QueryBuilder.findAccountById(newAccountId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        client.sendQuery(query)
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
        val pair1 = "key1" to "bar".asValue()
        val pair2 = "key2" to true.asValue()
        val pair3 = "key3" to 12345.asValue()

        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAsset(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Store())
            storeAsset(DEFAULT_ASSET_ID, pair1.first, pair1.second)
            storeAsset(DEFAULT_ASSET_ID, pair2.first, pair2.second)
            storeAsset(DEFAULT_ASSET_ID, pair3.first, pair3.second)
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
        assertEquals(DEFAULT_ASSET_ID.definitionId.domainName, asset.id.definitionId.domainName)
        when (val value = asset.value) {
            is AssetValue.Store -> {
                assertEquals(pair1.second.string, value.metadata.map[pair1.first]?.cast<Value.String>()?.string)
                assertEquals(pair2.second.bool, value.metadata.map[pair2.first]?.cast<Value.Bool>()?.bool)
                assertEquals(pair3.second.u32, (value.metadata.map[pair3.first]?.cast<Value.U32>())?.u32)
            }
            else -> fail("Expected result asset value has type `AssetValue.Store`, but it was `${asset.value::class.simpleName}`")
        }

        // try to find saved assets by domain name
        val findAssetsByDomainNameQry = QueryBuilder.findAssetsByDomainName(DEFAULT_DOMAIN_NAME)
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
            storeAsset(aliceAssetId, "foo", "bar".asValue())
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
        assertEquals(aliceAssetId.definitionId.domainName, asset.id.definitionId.domainName)
        when (val value = asset.value) {
            is AssetValue.Store -> {
                assertEquals("bar", (value.metadata.map["foo"]?.cast<Value.String>())?.string)
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
            mintAsset(DEFAULT_ASSET_ID, 5U)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        val query = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val result = client.sendQuery(query)
        assertEquals(5U, (result.assets[DEFAULT_ASSET_ID]?.value as? AssetValue.Quantity)?.u32)
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `burn asset instruction committed`(): Unit = runBlocking {
        // check balance before burn
        val query = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        var result = client.sendQuery(query)
        assertEquals(100U, (result.assets[DEFAULT_ASSET_ID]?.value as? AssetValue.Quantity)?.u32)

        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            burnAsset(DEFAULT_ASSET_ID, 50U)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        // check balance after burn
        result = client.sendQuery(query)
        assertEquals(50U, (result.assets[DEFAULT_ASSET_ID]?.value as? AssetValue.Quantity)?.u32)
    }

    @Test
    @WithIroha
    fun `burn public key instruction committed`(): Unit = runBlocking {
        val alicePubKey = ALICE_KEYPAIR.public.toIrohaPublicKey()
        // check public key before burn it
        val query = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val signatories = client.sendQuery(query).signatories
        assertEquals(1, signatories.size)
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
    @WithIroha(AliceAndBobEachHave100Xor::class)
    fun `transfer asset instruction committed`(): Unit = runBlocking {
        val aliceAssetId = DEFAULT_ASSET_ID
        val bobAssetId = AliceAndBobEachHave100Xor.BOB_ASSET_ID

        val aliceQuery = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val bobQuery = QueryBuilder.findAccountById(BOB_ACCOUNT_ID)
            .account(BOB_ACCOUNT_ID)
            .buildSigned(BOB_KEYPAIR)

        val aliceBefore = client.sendQuery(aliceQuery)
        assertEquals(100U, (aliceBefore.assets[aliceAssetId]?.value as? AssetValue.Quantity)?.u32)
        val bobBefore = client.sendQuery(bobQuery)
        assertEquals(100U, (bobBefore.assets[bobAssetId]?.value as? AssetValue.Quantity)?.u32)

        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            transferAsset(aliceAssetId, 40U, bobAssetId)
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        // check balance after transfer
        val aliceAfter = client.sendQuery(aliceQuery)
        assertEquals(60U, (aliceAfter.assets[aliceAssetId]?.value as? AssetValue.Quantity)?.u32)
        val bobAfter = client.sendQuery(bobQuery)
        assertEquals(140U, (bobAfter.assets[bobAssetId]?.value as? AssetValue.Quantity)?.u32)
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `burn if condition otherwise not burn`(): Unit = runBlocking {
        val toBurn = 80U
        val aliceQuery = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)

        val initAliceAmount =
            (client.sendQuery(aliceQuery).assets[DEFAULT_ASSET_ID]?.value as? AssetValue.Quantity)?.u32 ?: 0U

        sendTransactionToBurnIfCondition(initAliceAmount >= toBurn, DEFAULT_ASSET_ID, toBurn)
        val aliceAmountAfterBurn =
            (client.sendQuery(aliceQuery).assets[DEFAULT_ASSET_ID]?.value as? AssetValue.Quantity)?.u32 ?: 0U
        assert(aliceAmountAfterBurn == initAliceAmount - toBurn)

        sendTransactionToBurnIfCondition(aliceAmountAfterBurn >= toBurn, DEFAULT_ASSET_ID, toBurn)
        val finalAliceAmount =
            (client.sendQuery(aliceQuery).assets[DEFAULT_ASSET_ID]?.value as? AssetValue.Quantity)?.u32 ?: 0U
        assert(finalAliceAmount == aliceAmountAfterBurn)
    }

    private suspend fun sendTransactionToBurnIfCondition(condition: Boolean, assetId: Id, toBurn: UInt) {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            doIf(
                condition = condition,
                then = Instructions.burnAsset(assetId, toBurn),
                otherwise = Instructions.burnAsset(assetId, 0U)
            )
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }
    }
}
