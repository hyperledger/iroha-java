package jp.co.soramitsu.iroha2

import io.qameta.allure.Feature
import io.qameta.allure.Issue
import io.qameta.allure.Owner
import io.qameta.allure.Story
import jp.co.soramitsu.iroha2.annotations.Sdk
import jp.co.soramitsu.iroha2.annotations.SdkTestId
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.client.blockstream.BlockStreamStorage
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.CommittedBlock
import jp.co.soramitsu.iroha2.generated.Executable
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.TransactionPayload
import jp.co.soramitsu.iroha2.generated.VersionedBlockMessage
import jp.co.soramitsu.iroha2.generated.VersionedSignedTransaction
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
import kotlinx.coroutines.runBlocking
import org.apache.commons.lang3.RandomStringUtils.random
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@Owner("akostyuchenko")
@Sdk("Java/Kotlin")
@Feature("Block Streaming")
class BlockStreamTest : IrohaTest<Iroha2Client>() {

    @Test
    @WithIroha([NewAccountWithMetadata::class])
    @Story("Successful subscription to block stream")
    @SdkTestId("subscription_to_block_stream")
    @Issue("https://app.zenhub.com/workspaces/iroha-v2-60ddb820813b9100181fc060/issues/gh/hyperledger/iroha-java/361")
    @ResourceLock("blockStream")
    fun `subscription to block stream`(): Unit = runBlocking {
        val idToSubscription = client.subscribeToBlockStream(from = 1, count = 2)
        val actionId = idToSubscription.first.first().id
        val subscription = idToSubscription.second
        val newAssetName = "rox"

        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            registerAssetDefinition(newAssetName.asName(), DEFAULT_DOMAIN_ID, AssetValueType.Store())
        }
        var blocks = mutableListOf<VersionedBlockMessage>()
        subscription.receive<VersionedBlockMessage>(actionId).collect { block -> blocks.add(block) }

        val expectedSize = NewAccountWithMetadata().block.transactions.sumOf { it.size } + 1 // plus wasm
        var isi = blocks[0].validate(1, GENESIS, GENESIS, expectedSize)
        val registerDomain = isi[0].cast<InstructionBox.Register>().extractDomain().id.name.string

        assertEquals(DEFAULT_DOMAIN_ID.asString(), registerDomain)
        assertEquals(ALICE_ACCOUNT_ID.asString(), isi[1].extractAccount().id.asString())
        assertEquals(BOB_ACCOUNT_ID.asString(), isi[2].extractAccount().id.asString())
        assertEquals(
            "${NewAccountWithMetadata.ACCOUNT_NAME.string}$ACCOUNT_ID_DELIMITER$DEFAULT_DOMAIN",
            isi[3].extractAccount().id.asString(),
        )

        isi = blocks[1].validate(2, DEFAULT_DOMAIN, BOB_ACCOUNT, 1)
        var newAssetDefinition = isi[0].cast<InstructionBox.Register>().extractAssetDefinition()
        assertNotNull(newAssetDefinition)
        assertEquals(newAssetName, newAssetDefinition.id.name.string)
        assertEquals(DEFAULT_DOMAIN, newAssetDefinition.id.domainId.asString())

//        blocks = mutableListOf()
//        subscription.receiveBlocking<VersionedBlockMessage>(actionId).collect { block -> blocks.add(block) }
//        isi = checkBlockStructure(blocks[0], 2, DEFAULT_DOMAIN, BOB_ACCOUNT, 1)
//
//        newAssetDefinition = isi[0].cast<InstructionBox.Register>().extractAssetDefinition()
//        assertNotNull(newAssetDefinition)
//        assertEquals(newAssetName, newAssetDefinition.id.name.string)
//        assertEquals(DEFAULT_DOMAIN, newAssetDefinition.id.domainId.asString())

        subscription.stop()
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Story("Successful subscription to endless block stream")
    @SdkTestId("subscription_to_endless_block_stream")
    @ResourceLock("blockStream")
    fun `subscription to endless block stream`(): Unit = runBlocking {
        val repeatTimes = 5
        val shift = 1 // to test not to take more than was ordered
        val idToSubscription = client.subscribeToBlockStream(
            onBlock = { block -> block.extractBlock().height() },
            cancelIf = { block -> block.extractBlock().height() == BigInteger.valueOf(repeatTimes.toLong()) },
        )
        val initialActionId = idToSubscription.first.first().id
        val subscription = idToSubscription.second
        var heightSum = BigInteger.ZERO

        subscription.receive<BigInteger>(initialActionId) { heightSum += it }

        repeat(repeatTimes + shift) {
            client.tx { setKeyValue(ALICE_ACCOUNT_ID, random(16).asName(), random(16).asValue()) }
        }
        assertEquals((1..repeatTimes.toLong()).sum(), heightSum.toLong())

        val isi = mutableListOf<InstructionBox>()
        subscription.subscribeAndReceive<InstructionBox>(
            BlockStreamStorage(
                onBlock = { it.extractBlock().transactions.first().value.extractInstruction() },
            ),
            collector = { isi.add(it) },
        )

        lateinit var lastValue: String
        repeat(repeatTimes * 2) {
            lastValue = random(16)
            client.tx { setKeyValue(ALICE_ACCOUNT_ID, random(16).asName(), lastValue.asValue()) }
        }
        assertEquals(lastValue, isi.last().cast<InstructionBox.SetKeyValue>().extractValueString())

        subscription.stop()
    }

    private fun CommittedBlock.payloads(): List<TransactionPayload> = this.transactions.map { tx ->
        tx.value
            .cast<VersionedSignedTransaction.V1>()
            .signedTransaction
            .payload
    }

    private fun VersionedBlockMessage.validate(
        expectedHeight: Long,
        expectedDomain: String,
        expectedAccount: String,
        expectedIsiSize: Int,
    ): List<InstructionBox> {
        val committedBlock = this.extractBlock()
        assertEquals(expectedHeight, committedBlock.header.height.toLong())

        val payloads = committedBlock.payloads()
        assertTrue { payloads.any { it.authority.domainId.name.string == expectedDomain } }
        assertTrue { payloads.any { it.authority.name.string == expectedAccount } }

        val instructions = payloads.reversed().map {
            it.instructions.cast<Executable.Instructions>().vec
        }.flatten() // wasm isi in the end
        assertEquals(expectedIsiSize, instructions.size)

        return instructions
    }
}
