package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import org.junit.jupiter.api.Test
import java.io.File

class DeserializerTest {
    @Test
    fun `should deserialize genesis block`() {
        val json = File("../testcontainers/src/main/resources/genesis.json")
        val node = iroha2Mapper.readTree(json)
        val block = iroha2Mapper.convertValue(node, RawGenesisBlock::class.java)

        assert(block.transactions.isNotEmpty())
        assert(block.transactions.first().isi.size == 4)
    }
}
