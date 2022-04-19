package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.engine.AliceHas100XorAndPermissionToBurn
import jp.co.soramitsu.iroha2.engine.DEFAULT_ASSET_ID
import jp.co.soramitsu.iroha2.engine.IrohaRunnerExtension
import jp.co.soramitsu.iroha2.engine.WithIroha
import jp.co.soramitsu.iroha2.generated.core.time.Duration
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.events.time.Schedule
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Repeats
import jp.co.soramitsu.iroha2.query.QueryBuilder
import jp.co.soramitsu.iroha2.transaction.Instructions
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.math.BigInteger
import java.security.KeyPair
import java.time.Instant
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.trigger.Id as TriggerId

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(IrohaRunnerExtension::class)
@Timeout(40)
class TriggersTest {

    lateinit var client: Iroha2Client

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
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
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

    private suspend fun sendAndWait10Txs() {
        for (i in 0..10) {
            client.sendTransaction {
                accountId = ALICE_ACCOUNT_ID
                setKeyValue(ALICE_ACCOUNT_ID, "key$i".asName(), "value$i".asValue())
                buildSigned(ALICE_KEYPAIR)
            }.also {
                delay(1000)
                Assertions.assertDoesNotThrow {
                    it.get(10, TimeUnit.SECONDS)
                }
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
        accountId: AccountId = ALICE_ACCOUNT_ID,
    ) {
        client.sendTransaction {
            this.accountId = accountId
            registerTimeTrigger(
                triggerId, listOf(instruction), repeats, accountId,
                Schedule(
                    Duration(BigInteger.valueOf(Instant.now().epochSecond), 0L),
                    Duration(BigInteger.valueOf(1L), 0L)
                )
            )
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }
    }
}
