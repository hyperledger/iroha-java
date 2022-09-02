package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_NAME
import jp.co.soramitsu.iroha2.engine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.engine.AliceAndBobEachHave100Xor
import jp.co.soramitsu.iroha2.engine.AliceHas100XorAndPermissionToBurn
import jp.co.soramitsu.iroha2.engine.DEFAULT_ASSET_ID
import jp.co.soramitsu.iroha2.engine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.engine.IrohaTest
import jp.co.soramitsu.iroha2.engine.WithIroha
import jp.co.soramitsu.iroha2.engine.XorAndValAssets
import jp.co.soramitsu.iroha2.generated.Duration
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.events.data.events.asset.AssetDefinitionEventFilter
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.TriggerId
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.action.Repeats
import jp.co.soramitsu.iroha2.query.QueryBuilder
import jp.co.soramitsu.iroha2.transaction.EntityFilters
import jp.co.soramitsu.iroha2.transaction.EventFilters
import jp.co.soramitsu.iroha2.transaction.Filters
import jp.co.soramitsu.iroha2.transaction.Instructions
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.withTimeout
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.math.BigInteger
import java.security.KeyPair
import java.time.Instant
import java.util.Date
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TriggersTest : IrohaTest<Iroha2Client>() {

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `data created trigger`(): Unit = runBlocking {
        val triggerId = TriggerId("data_trigger".asName())
        val newAssetName = "token1"

        // check account assets before trigger
        val query = QueryBuilder.findAllAssetsDefinitions()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val assetDefinitions = client.sendQuery(query)
        assertEquals(1, assetDefinitions.size)
        val asset = assetDefinitions.first { it.id.name.string == "xor" }
        assertNotNull(asset)

        // Check default asset quantity before trigger
        val prevQuantity = checkDefaultDomainQuantity()
        assertEquals(100L, prevQuantity)

        val filter = Filters.data(
            EntityFilters.byAssetDefinition(
                eventFilter = AssetDefinitionEventFilter.ByCreated()
            )
        )
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerEventTrigger(
                triggerId,
                listOf(Instructions.mintAsset(DEFAULT_ASSET_ID, 1L)),
                Repeats.Indefinitely(),
                ALICE_ACCOUNT_ID,
                Metadata(mapOf()),
                filter
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        // register new asset
        // after that trigger should mint DEFAULT_ASSET_ID
        createNewAsset(newAssetName, assetDefinitions.size)

        // check new quantity after trigger is run
        val newQuantity = checkDefaultDomainQuantity()
        assertEquals(prevQuantity + 1L, newQuantity)
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `pre commit trigger should mint asset to account for every transaction`(): Unit = runBlocking {
        val triggerId = TriggerId("pre_commit_trigger".asName())
        val newAssetName = "token1"

        // check account assets before trigger
        val query = QueryBuilder.findAllAssetsDefinitions()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val assetDefinitions = client.sendQuery(query)
        assertEquals(1, assetDefinitions.size)

        // check DEFAULT_ASSET_ID quantity before trigger
        val prevQuantity = checkDefaultDomainQuantity()
        assertEquals(100L, prevQuantity)

        // register pre commit trigger
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerPreCommitTrigger(
                triggerId,
                listOf(Instructions.mintAsset(DEFAULT_ASSET_ID, 10L)),
                Repeats.Indefinitely(),
                ALICE_ACCOUNT_ID
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        // check DEFAULT_ASSET_ID quantity after trigger is run
        var newQuantity = checkDefaultDomainQuantity()
        assertEquals(110L, newQuantity)

        // register new asset
        // after that trigger should mint 10 more quantity to DEFAULT_ASSET_ID
        createNewAsset(newAssetName, assetDefinitions.size)

        // check DEFAULT_ASSET_ID quantity after trigger is run
        newQuantity = checkDefaultDomainQuantity()
        assertEquals(120L, newQuantity)

        // transfer asset instruction just to test trigger
        val bobAssetId = AliceAndBobEachHave100Xor.BOB_ASSET_ID
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            transferAsset(DEFAULT_ASSET_ID, 100, bobAssetId)
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        // check DEFAULT_ASSET_ID quantity after trigger is run
        newQuantity = checkDefaultDomainQuantity()
        assertEquals(30L, newQuantity)
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `executable trigger`(): Unit = runBlocking {
        val triggerId = TriggerId("executable_trigger".asName())

        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerExecutableTrigger(
                triggerId,
                listOf(Instructions.mintAsset(DEFAULT_ASSET_ID, 1L)),
                Repeats.Exactly(1L),
                ALICE_ACCOUNT_ID
            )
            executeTrigger(triggerId)
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        assertEquals(101L, readQuantity())
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `endless time trigger`(): Unit = runBlocking {
        val triggerId = TriggerId(Name("endless_time_trigger"))

        sendAndAwaitTimeTrigger(
            triggerId,
            Repeats.Indefinitely(),
            Instructions.burnAsset(DEFAULT_ASSET_ID, 1L)
        )
        sendAndWait10Txs()

        delay(3000)
        assert(readQuantity() <= 90L)
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `time trigger execution repeats few times`(): Unit = runBlocking {
        val triggerId = TriggerId(Name("time_trigger"))

        sendAndAwaitTimeTrigger(
            triggerId,
            Repeats.Exactly(5L),
            Instructions.burnAsset(DEFAULT_ASSET_ID, 1L)
        )
        sendAndWait10Txs()

        delay(3000)
        assertEquals(95, readQuantity())
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `wasm trigger to mint nft for every user`(): Unit = runBlocking {
        val triggerId = TriggerId("wasm_trigger".asName())

        val currentTime = Date().time / 1000
        val filter = Filters.time(
            EventFilters.timeEventFilter(
                Duration(BigInteger.valueOf(currentTime), 0),
                Duration(BigInteger.valueOf(1L), 0)
            )
        )
        val wasm = this.javaClass.classLoader
            .getResource("create_nft_for_every_user_smartcontract.wasm")
            .readBytes()

        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerWasmTrigger(
                triggerId,
                wasm,
                Repeats.Indefinitely(),
                ALICE_ACCOUNT_ID,
                Metadata(mapOf()),
                filter
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        // send some transactions to keep Iroha2 network busy
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            setKeyValue(ALICE_ACCOUNT_ID, "test".asName(), "test".asValue())
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            setKeyValue(ALICE_ACCOUNT_ID, "test2".asName(), "test2".asValue())
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        QueryBuilder.findAssetsByAccountId(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { assets ->
                assert(assets.size > 1)
                assert(assets.all { it.id.accountId == ALICE_ACCOUNT_ID })
                assert(assets.any { it.id.definitionId == XorAndValAssets.XOR_DEFINITION_ID })
                assert(
                    assets.any {
                        it.id.definitionId == DefinitionId(
                            "nft_number_1_for_alice".asName(),
                            DEFAULT_DOMAIN_ID
                        )
                    }
                )
            }
    }

    private suspend fun sendAndWait10Txs() {
        for (i in 0..10) {
            client.sendTransaction {
                accountId = ALICE_ACCOUNT_ID
                setKeyValue(ALICE_ACCOUNT_ID, "key$i".asName(), "value$i".asValue())
                buildSigned(ALICE_KEYPAIR)
            }.also { d ->
                delay(1000)
                withTimeout(txTimeout) { d.await() }
            }
        }
    }

    private suspend fun readQuantity(
        assetId: AssetId = DEFAULT_ASSET_ID,
        accountId: AccountId = ALICE_ACCOUNT_ID,
        keyPair: KeyPair = ALICE_KEYPAIR
    ): Long {
        return QueryBuilder.findAssetById(assetId)
            .account(accountId)
            .buildSigned(keyPair)
            .let { query -> client.sendQuery(query) }
            .value.cast<AssetValue.Quantity>().u32
    }

    private suspend fun sendAndAwaitTimeTrigger(
        triggerId: TriggerId,
        repeats: Repeats,
        instruction: Instruction,
        accountId: AccountId = ALICE_ACCOUNT_ID
    ) {
        client.sendTransaction {
            this.accountId = accountId
            registerTimeTrigger(
                triggerId,
                listOf(instruction),
                repeats,
                accountId,
                EventFilters.timeEventFilter(
                    Duration(BigInteger.valueOf(Instant.now().epochSecond), 0L),
                    Duration(BigInteger.valueOf(1L), 0L)
                )
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }

    private suspend fun createNewAsset(assetName: String, prevSize: Int) {
        val newAsset = DefinitionId(assetName.asName(), DEFAULT_DOMAIN_ID)
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerAsset(newAsset, AssetValueType.Quantity())
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        // check new asset is created
        val query = QueryBuilder.findAllAssetsDefinitions()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
        val assetDefinitions = client.sendQuery(query)
        assertEquals(prevSize + 1, assetDefinitions.size)
        val asset = assetDefinitions.first { it.id.name.string == assetName }
        assertNotNull(asset)
    }

    private suspend fun checkDefaultDomainQuantity(): Long {
        return QueryBuilder.findDomainById(DEFAULT_DOMAIN_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .accounts
            .filter { it.key.name == ALICE_ACCOUNT_NAME }
            .map { it.value.assets[DEFAULT_ASSET_ID] }
            .map { (it?.value as AssetValue.Quantity).u32 }
            .first()
    }

    @Disabled
    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `unregister executable trigger`(): Unit = runBlocking {
        val triggerName = "executable_trigger"
        val triggerId = TriggerId(triggerName.asName())

        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerExecutableTrigger(
                triggerId,
                listOf(Instructions.mintAsset(DEFAULT_ASSET_ID, 1L)),
                Repeats.Exactly(1L),
                ALICE_ACCOUNT_ID
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        var trigger = QueryBuilder.findTriggerById(triggerId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
        assertNotNull(trigger)

        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            unregisterTrigger(
                triggerName
            )
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }

        trigger = QueryBuilder.findTriggerById(triggerId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
        assertNotNull(trigger)
    }
}
