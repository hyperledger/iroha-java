package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.core.block.CommittedBlock
import jp.co.soramitsu.iroha2.generated.core.block.VersionedCommittedBlock
import jp.co.soramitsu.iroha2.generated.core.block.stream.VersionedBlockMessage
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Executable
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.Payload
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedValidTransaction
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.BOB_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.testengine.IrohaTest
import jp.co.soramitsu.iroha2.testengine.NewAccountWithMetadata
import jp.co.soramitsu.iroha2.testengine.WithIroha
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BlockStreamTest : IrohaTest<Iroha2Client>(account = ALICE_ACCOUNT_ID, keyPair = ALICE_KEYPAIR) {

    @Test
    @WithIroha([NewAccountWithMetadata::class])
    fun `when subscribe to block stream then success`(): Unit = runBlocking {
        var blocksResult = client.subscribeToBlockStream(1, 2)
        val defaultDomain = "wonderland"
        val newAssetName = "rox"

        client.tx(BOB_ACCOUNT_ID, BOB_KEYPAIR) {
            registerAssetDefinition(AssetDefinitionId(newAssetName.asName(), DEFAULT_DOMAIN_ID), AssetValueType.Store())
        }
        var blocks = blocksResult.await()
        var committedBlock = getCommittedBlock(blocks[0])
        var height = committedBlock.header.height.toLong()
        assertEquals(1, height)
        var payload = getInstructionPayload(committedBlock)
        var instructionAccountDomain = payload.accountId.domainId.name.string
        var instructionAccount = payload.accountId.name.string
        val genesis = "genesis"
        assertEquals(genesis, instructionAccountDomain)
        assertEquals(genesis, instructionAccount)

        var instructions = payload.instructions.cast<Executable.Instructions>().vec
        assertEquals(4, instructions.size)
        val registerDomain = instructions[0].cast<Instruction.Register>()
            .registerBox.`object`.expression
            .cast<Expression.Raw>().value
            .cast<Value.Identifiable>()
            .identifiableBox.cast<IdentifiableBox.NewDomain>()
            .newDomain.id.name.string
        assertEquals(DEFAULT_DOMAIN_ID.asString(), registerDomain)
        assertEquals(ALICE_ACCOUNT_ID.asString(), instructions[1].cast<Instruction.Register>().extractAccount().id.asString())
        assertEquals(BOB_ACCOUNT_ID.asString(), instructions[2].cast<Instruction.Register>().extractAccount().id.asString())
        assertEquals("foo@wonderland", instructions[3].cast<Instruction.Register>().extractAccount().id.asString())

        fun checkSecondBlock(blockMessage: VersionedBlockMessage) {
            committedBlock = getCommittedBlock(blockMessage)
            height = committedBlock.header.height.toLong()
            assertEquals(2, height)
            payload = getInstructionPayload(committedBlock)
            instructionAccountDomain = payload.accountId.domainId.name.string
            instructionAccount = payload.accountId.name.string
            assertEquals(defaultDomain, instructionAccountDomain)
            assertEquals("bob", instructionAccount)
            instructions = payload.instructions.cast<Executable.Instructions>().vec
            assertEquals(1, instructions.size)
            val newAssetDefinition = instructions[0].cast<Instruction.Register>()
                .registerBox.`object`.expression
                .cast<Expression.Raw>().value
                .cast<Value.Identifiable>()
                .identifiableBox.cast<IdentifiableBox.NewAssetDefinition>()
                .newAssetDefinition
            assertEquals(newAssetName, newAssetDefinition.id.name.string)
            assertEquals(defaultDomain, newAssetDefinition.id.domainId.asString())
        }
        checkSecondBlock(blocks[1])

        // get the last block second time
        blocksResult = client.subscribeToBlockStream(2, 1)
        blocks = blocksResult.await()
        checkSecondBlock(blocks[0])
    }

    private fun getCommittedBlock(versionedBlockMessage: VersionedBlockMessage): CommittedBlock {
        return versionedBlockMessage.cast<VersionedBlockMessage.V1>()
            .blockMessage.versionedCommittedBlock.cast<VersionedCommittedBlock.V1>().committedBlock
    }

    private fun getInstructionPayload(committedBlock: CommittedBlock): Payload {
        return committedBlock.transactions[0]
            .cast<VersionedValidTransaction.V1>()
            .validTransaction
            .payload
    }
}
