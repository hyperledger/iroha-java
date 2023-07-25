package jp.co.soramitsu.iroha2

import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import jp.co.soramitsu.iroha2.annotations.Sdk
import jp.co.soramitsu.iroha2.annotations.SdkTestId
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.CommittedBlock
import jp.co.soramitsu.iroha2.generated.Executable
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.VersionedBlockMessage
import jp.co.soramitsu.iroha2.generated.VersionedValidTransaction
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.BOB_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.DEFAULT_DOMAIN
import jp.co.soramitsu.iroha2.testengine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.testengine.DefaultGenesis
import jp.co.soramitsu.iroha2.testengine.GENESIS
import jp.co.soramitsu.iroha2.testengine.IrohaTest
import jp.co.soramitsu.iroha2.testengine.NewAccountWithMetadata
import jp.co.soramitsu.iroha2.testengine.WithIroha
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils.random
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@Owner("akostyuchenko")
@Sdk("Java/Kotlin")
@Feature("Block Streaming")
class BlockStreamTest : IrohaTest<Iroha2Client>() {

    @Test
    @WithIroha([NewAccountWithMetadata::class])
    @Story("Successful subscription to block stream")
    @SdkTestId("subscription_to_block_stream")
    fun `subscription to block stream`(): Unit = runBlocking {
        val idToSubscription = client.subscribeToBlockStream(from = 1, count = 2)
        val actionId = idToSubscription.first
        val subscription = idToSubscription.second
        val newAssetName = "rox"

        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            registerAssetDefinition(newAssetName.asName(), DEFAULT_DOMAIN_ID, AssetValueType.Store())
        }
        var blocks = mutableListOf<VersionedBlockMessage>()
        subscription.receiveBlocking<VersionedBlockMessage>(actionId).collect { block -> blocks.add(block) }

        val expectedSize = NewAccountWithMetadata().block.transactions.sumOf { it.size }
        var isi = checkBlockStructure(blocks[0], 1, GENESIS, GENESIS, expectedSize)
        val registerDomain = isi[0].cast<InstructionBox.Register>().extractDomain().id.name.string

        assertEquals(DEFAULT_DOMAIN_ID.asString(), registerDomain)
        assertEquals(ALICE_ACCOUNT_ID.asString(), isi[1].extractAccount().id.asString())
        assertEquals(BOB_ACCOUNT_ID.asString(), isi[2].extractAccount().id.asString())
        assertEquals(
            "${NewAccountWithMetadata.ACCOUNT_NAME.string}$ACCOUNT_ID_DELIMITER$DEFAULT_DOMAIN",
            isi[3].extractAccount().id.asString(),
        )

        isi = checkBlockStructure(blocks[1], 2, DEFAULT_DOMAIN, BOB_ACCOUNT, 1)
        var newAssetDefinition = isi[0].cast<InstructionBox.Register>().extractAssetDefinition()
        assertNotNull(newAssetDefinition)
        assertEquals(newAssetName, newAssetDefinition.id.name.string)
        assertEquals(DEFAULT_DOMAIN, newAssetDefinition.id.domainId.asString())

        // get the last block second time
        blocks = mutableListOf()
        subscription.receiveBlocking<VersionedBlockMessage>(actionId).collect { block -> blocks.add(block) }
        isi = checkBlockStructure(blocks[0], 2, DEFAULT_DOMAIN, BOB_ACCOUNT, 1)

        newAssetDefinition = isi[0].cast<InstructionBox.Register>().extractAssetDefinition()
        assertNotNull(newAssetDefinition)
        assertEquals(newAssetName, newAssetDefinition.id.name.string)
        assertEquals(DEFAULT_DOMAIN, newAssetDefinition.id.domainId.asString())
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Story("Successful subscription to endless block stream")
    @SdkTestId("subscription_to_endless_block_stream")
    fun `subscription to endless block stream`(): Unit = runBlocking {
        val repeatTimes = 5
        val shift = 1 // to test not to take more than was ordered
        val idToSubscription = client.subscribeToBlockStream(
            onBlock = { block -> block.extractBlock().height() },
            closeIf = { block -> block.extractBlock().height() == BigInteger.valueOf(repeatTimes.toLong()) },
        )
        val initialActionId = idToSubscription.first
        val subscription = idToSubscription.second
        var lastHeight = BigInteger.ZERO

        subscription.receive<BigInteger>(initialActionId) { lastHeight = it }

        repeat(repeatTimes + shift) {
            client.tx { setKeyValue(ALICE_ACCOUNT_ID, random(16).asName(), random(16).asValue()) }
        }
        assertEquals(BigInteger.valueOf(repeatTimes.toLong()), lastHeight)

        val isi = mutableListOf<InstructionBox>()
        val nextActionId = subscription.expand { block -> block.extractBlock().transactions.first().extractInstruction() }
        subscription.receive<InstructionBox>(nextActionId) { isi.add(it) }

        lateinit var lastValue: String
        repeat(repeatTimes) {
            lastValue = random(16)
            client.tx { setKeyValue(ALICE_ACCOUNT_ID, random(16).asName(), lastValue.asValue()) }
        }
        assertEquals(lastValue, isi.last().cast<InstructionBox.SetKeyValue>().extractValueString())

        subscription.unsubscribe()
    }

    private fun CommittedBlock.extractInstructionPayload() = this.transactions[0]
        .cast<VersionedValidTransaction.V1>().validTransaction.payload

    private fun checkBlockStructure(
        blockMessage: VersionedBlockMessage,
        height: Long,
        instructionAccountDomain: String,
        instructionAccount: String,
        instructionSize: Int,
    ): List<InstructionBox> {
        val committedBlock = blockMessage.extractBlock()
        val payload = committedBlock.extractInstructionPayload()
        val instructions = payload.instructions.cast<Executable.Instructions>().vec

        assertEquals(height, committedBlock.header.height.toLong())
        assertEquals(instructionAccountDomain, payload.accountId.domainId.name.string)
        assertEquals(instructionAccount, payload.accountId.name.string)
        assertEquals(instructionSize, instructions.size)

        return instructions
    }
}
