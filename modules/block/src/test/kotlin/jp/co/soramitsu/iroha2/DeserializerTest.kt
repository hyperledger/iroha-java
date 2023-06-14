package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.RawGenesisBlock
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

@Disabled // https://app.zenhub.com/workspaces/iroha-v2-60ddb820813b9100181fc060/issues/hyperledger/iroha-java/268
class DeserializerTest {
    @Test
    fun `should deserialize genesis block`() {
        val json = File("../test-tools/src/main/resources/genesis.json")
        val node = JSON_SERDE.readTree(json)
        val block = JSON_SERDE.convertValue(node, RawGenesisBlock::class.java)

        assert(block.transactions.isNotEmpty())
        assert(block.transactions.first().size == 4)
    }
}
