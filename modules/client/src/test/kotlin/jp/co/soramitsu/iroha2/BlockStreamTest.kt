package jp.co.soramitsu.iroha2

import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import jp.co.soramitsu.iroha2.annotations.Sdk
import jp.co.soramitsu.iroha2.annotations.SdkTestId
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.CommittedBlock
import jp.co.soramitsu.iroha2.generated.Executable
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.TransactionPayload
import jp.co.soramitsu.iroha2.generated.VersionedBlockMessage
import jp.co.soramitsu.iroha2.generated.VersionedCommittedBlock
import jp.co.soramitsu.iroha2.generated.VersionedValidTransaction
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.BOB_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.DEFAULT_DOMAIN
import jp.co.soramitsu.iroha2.testengine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.testengine.GENESIS
import jp.co.soramitsu.iroha2.testengine.IrohaTest
import jp.co.soramitsu.iroha2.testengine.NewAccountWithMetadata
import jp.co.soramitsu.iroha2.testengine.WithIroha
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@Owner("akostyuchenko")
@Sdk("Java/Kotlin")
@Feature("Block Streaming")
class BlockStreamTest : IrohaTest<Iroha2Client>(account = ALICE_ACCOUNT_ID, keyPair = ALICE_KEYPAIR) {

    @Test
    @WithIroha([NewAccountWithMetadata::class])
    @Story("Successful subscription to block stream")
    @SdkTestId("when_subscribe_to_block_stream_then_success")
    fun `when subscribe to block stream then success`(): Unit = runBlocking {
        var blocksResult = client.subscribeToBlockStream(1, 2)
        val newAssetName = "rox"

        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            registerAssetDefinition(AssetDefinitionId(newAssetName.asName(), DEFAULT_DOMAIN_ID), AssetValueType.Store())
        }
        var blocks = mutableListOf<VersionedBlockMessage>()
        blocksResult.collect { block -> blocks.add(block) }

        var instructions = checkBlockStructure(
            blocks[0],
            1,
            GENESIS,
            GENESIS,
            4
        )
        val registerDomain = instructions[0].cast<InstructionBox.Register>().extractDomain().id.name.string
        assertEquals(DEFAULT_DOMAIN_ID.asString(), registerDomain)
        assertEquals(ALICE_ACCOUNT_ID.asString(), instructions[1].cast<InstructionBox.Register>().extractAccount().id.asString())
        assertEquals(BOB_ACCOUNT_ID.asString(), instructions[2].cast<InstructionBox.Register>().extractAccount().id.asString())
        assertEquals("foo$ACCOUNT_ID_DELIMITER$DEFAULT_DOMAIN", instructions[3].cast<InstructionBox.Register>().extractAccount().id.asString())

        instructions = checkBlockStructure(
            blocks[1],
            2,
            DEFAULT_DOMAIN,
            BOB_ACCOUNT,
            1
        )
        var newAssetDefinition = instructions[0].cast<InstructionBox.Register>().extractAssetDefinition()
        assertNotNull(newAssetDefinition)
        assertEquals(newAssetName, newAssetDefinition.id.name.string)
        assertEquals(DEFAULT_DOMAIN, newAssetDefinition.id.domainId.asString())

        // get the last block second time
        blocksResult = client.subscribeToBlockStream(2, 1)
        blocks = mutableListOf<VersionedBlockMessage>()
        blocksResult.collect { block -> blocks.add(block) }
        instructions = checkBlockStructure(
            blocks[0],
            2,
            DEFAULT_DOMAIN,
            BOB_ACCOUNT,
            1
        )
        newAssetDefinition = instructions[0].cast<InstructionBox.Register>().extractAssetDefinition()
        assertNotNull(newAssetDefinition)
        assertEquals(newAssetName, newAssetDefinition.id.name.string)
        assertEquals(DEFAULT_DOMAIN, newAssetDefinition.id.domainId.asString())
    }

    private fun getCommittedBlock(versionedBlockMessage: VersionedBlockMessage): CommittedBlock {
        return versionedBlockMessage.cast<VersionedBlockMessage.V1>()
            .blockMessage.versionedCommittedBlock.cast<VersionedCommittedBlock.V1>().committedBlock
    }

    private fun getInstructionPayload(committedBlock: CommittedBlock): TransactionPayload {
        return committedBlock.transactions[0]
            .cast<VersionedValidTransaction.V1>()
            .validTransaction
            .payload
    }

    private fun checkBlockStructure(
        blockMessage: VersionedBlockMessage,
        height: Long,
        instructionAccountDomain: String,
        instructionAccount: String,
        instructionSize: Int
    ): List<InstructionBox> {
        val committedBlock = getCommittedBlock(blockMessage)
        assertEquals(height, committedBlock.header.height.toLong())
        val payload = getInstructionPayload(committedBlock)
        assertEquals(instructionAccountDomain, payload.accountId.domainId.name.string)
        assertEquals(instructionAccount, payload.accountId.name.string)
        val instructions = payload.instructions.cast<Executable.Instructions>().vec
        assertEquals(instructionSize, instructions.size)
        return instructions
    }
}
