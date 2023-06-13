package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.core.genesis.RawGenesisBlock
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class DeserializerTest {
    @Test
    fun `should deserialize genesis block`() {
        val json = File("../test-tools/src/main/resources/genesis.json")
        val node = JSON_SERDE.readTree(json)
        val block = JSON_SERDE.convertValue(node, RawGenesisBlock::class.java)

        assert(block.transactions.isNotEmpty())
        // genesis.json has 7 instructions ("isi")
        // Register -> NewDomain
        // Register -> NewAccount
        // Register -> NewAccount
        // Register -> PermissionTokenDefinition
        // Grant -> PermissionToken
        // Register -> PermissionTokenDefinition
        // Grant -> PermissionToken
        assert(block.transactions.first().isi.size == 7)

        val genesis = Genesis(block)
        val newJson = removeWhiteSpaceAndReplacePubKey(genesis.asJson())
        val initialJson = removeWhiteSpaceAndReplacePubKey(json.readText())
        assertEquals(initialJson, newJson)
    }

    @Test
    fun `should deserialize genesis block2`() {
        val json = File("../test-tools/src/main/resources/genesis2.json")
        val node = JSON_SERDE.readTree(json)
        val block = JSON_SERDE.convertValue(node, RawGenesisBlock::class.java)

        assert(block.transactions.isNotEmpty())
        // genesis.json has 27 instructions ("isi")
        // Register -> NewDomain
        // Register -> NewDomain
        // Register -> NewDomain
        // Register -> NewDomain
        // Register -> NewAccount
        // Register -> NewAccount
        // Register -> NewAccount
        // Register -> NewAccount
        // Register -> NewAccount
        // Register -> NewAssetDefinition
        // Register -> NewAssetDefinition
        // Register -> NewAssetDefinition
        // Register -> PermissionTokenDefinition
        // Register -> PermissionTokenDefinition
        // Register -> PermissionTokenDefinition
        // Register -> PermissionTokenDefinition
        // Register -> Asset
        // Register -> Asset
        // SetKeyValue -> AssetId
        // SetKeyValue -> AssetId
        // SetKeyValue -> AssetId
        // SetKeyValue -> AssetId
        // SetKeyValue -> AssetId
        // SetKeyValue -> AssetId
        // SetKeyValue -> AssetId
        // SetKeyValue -> AssetId
        // Grant -> PermissionToken
        assert(block.transactions.first().isi.size == 27)

        val genesis = Genesis(block)
        val newJson = removeWhiteSpaceAndReplacePubKey(genesis.asJson())
        val initialJson = removeWhiteSpaceAndReplacePubKey(json.readText())
        assertEquals(initialJson, newJson)
    }

    @Test
    fun `should deserialize genesis block3`() {
        val json = File("../test-tools/src/main/resources/genesis3.json")
        val node = JSON_SERDE.readTree(json)
        val block = JSON_SERDE.convertValue(node, RawGenesisBlock::class.java)

        assert(block.transactions.isNotEmpty())
        // genesis.json has 20 instructions ("isi")
        // Register -> NewDomain
        // Register -> NewAccount
        // Register -> NewAccount
        // Register -> NewAccount
        // Register -> NewAssetDefinition
        // Register -> NewAssetDefinition
        // Register -> NewAssetDefinition
        // Register -> NewAssetDefinition
        // Register -> NewAssetDefinition
        // Register -> PermissionTokenDefinition
        // Register -> PermissionTokenDefinition
        // Register -> PermissionTokenDefinition
        // Register -> NewRole
        // Mint -> AssetId
        // Grant -> PermissionToken
        // SetKeyValue -> AssetId
        // SetKeyValue -> AssetId
        // Mint -> AssetId
        // Mint -> AssetId
        // Mint -> AssetId
        assert(block.transactions.first().isi.size == 20)

        val genesis = Genesis(block)
        val newJson = removeWhiteSpaceAndReplacePubKey(genesis.asJson())
        val initialJson = removeWhiteSpaceAndReplacePubKey(json.readText())
        assertEquals(initialJson, newJson)
    }

    private fun removeWhiteSpaceAndReplacePubKey(json: String): String {
        val regex = "(\"ed01)+\\w+\"".toRegex()
        return json.replace(System.getProperty("line.separator"), "")
            .replace(" ", "")
            .replace(regex, "publicKey")
    }
}
