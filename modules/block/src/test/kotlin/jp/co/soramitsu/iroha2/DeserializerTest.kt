package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class DeserializerTest {
    @Test
    fun `should deserialize genesis block`() {
        val regex = "(\"ed01)+\\w+\"".toRegex()
        val json = File("../test-tools/src/main/resources/genesis.json")
        val node = JSON_SERDE.readTree(json)
        val block = JSON_SERDE.convertValue(node, RawGenesisBlock::class.java)

        assert(block.transactions.isNotEmpty())
        assert(block.transactions.first().isi.size == 7)

        val genesis = Genesis(block)
        val newJson = genesis.asJson()
            .replace("\r\n", "")
            .replace(" ", "")
            .replace(regex, "publicKey")
        val initialJson = json.readText()
            .replace("\r\n", "")
            .replace(" ", "")
            .replace(regex, "publicKey")
        assertEquals(initialJson, newJson)
    }

    @Test
    fun `should deserialize genesis block2`() {
        val regex = "(\"ed01)+\\w+\"".toRegex()
        val json = File("../test-tools/src/main/resources/genesis2.json")
        val node = JSON_SERDE.readTree(json)
        val block = JSON_SERDE.convertValue(node, RawGenesisBlock::class.java)

        assert(block.transactions.isNotEmpty())
        assert(block.transactions.first().isi.size == 27)

        val genesis = Genesis(block)
        val newJson = genesis.asJson()
            .replace("\r\n", "")
            .replace(" ", "")
            .replace(regex, "publicKey")
        val initialJson = json.readText()
            .replace("\r\n", "")
            .replace(" ", "")
            .replace(regex, "publicKey")
        assertEquals(initialJson, newJson)
    }

    @Test
    fun `should deserialize genesis block3`() {
        val regex = "(\"ed01)+\\w+\"".toRegex()
        val json = File("../test-tools/src/main/resources/genesis3.json")
        val node = JSON_SERDE.readTree(json)
        val block = JSON_SERDE.convertValue(node, RawGenesisBlock::class.java)

        assert(block.transactions.isNotEmpty())
        assert(block.transactions.first().isi.size == 20)

        val genesis = Genesis(block)
        val newJson = genesis.asJson()
            .replace("\r\n", "")
            .replace(" ", "")
            .replace(regex, "publicKey")
        val initialJson = json.readText()
            .replace("\r\n", "")
            .replace(" ", "")
            .replace(regex, "publicKey")
        assertEquals(initialJson, newJson)
    }
}
