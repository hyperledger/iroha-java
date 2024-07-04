package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.RawGenesisTransaction
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class DeserializerTest {
    @Test
    fun `should deserialize genesis block`() {
        val json = File("../test-tools/src/main/resources/genesis.json")
        val node = JSON_SERDE.readTree(json)
        val transaction = JSON_SERDE.convertValue(node, RawGenesisTransaction::class.java)

        assert(transaction.instructions.isNotEmpty())
        // genesis.json has 10 instructions ("isi")
        // Register -> NewDomain
        // Register -> NewAccount (2)
        // Register -> NewAssetDefinition
        // Grant -> PermissionToken (2)
        // Mint -> AssetId (2)
        // Register -> Trigger (2)
        assert(transaction.instructions.size == 10)

        val genesis = Genesis(transaction)
        val newJson = removeWhiteSpaceAndReplacePubKey(genesis.asJson())
        val initialJson = removeWhiteSpaceAndReplacePubKey(json.readText())
        assertEquals(initialJson, newJson)
    }

    @Test
    fun `should deserialize genesis block2`() {
        val json = File("../test-tools/src/main/resources/genesis2.json")
        val node = JSON_SERDE.readTree(json)
        val transaction = JSON_SERDE.convertValue(node, RawGenesisTransaction::class.java)

        assert(transaction.instructions.isNotEmpty())
        // genesis.json has 23 instructions ("isi")
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
        assert(transaction.instructions.size == 23)

        val genesis = Genesis(transaction)
        val newJson = removeWhiteSpaceAndReplacePubKey(genesis.asJson())
        val initialJson = removeWhiteSpaceAndReplacePubKey(json.readText())
        assertEquals(initialJson, newJson)
    }

    @Test
    fun `should deserialize genesis block3`() {
        val json = File("../test-tools/src/main/resources/genesis3.json")
        val node = JSON_SERDE.readTree(json)
        val transaction = JSON_SERDE.convertValue(node, RawGenesisTransaction::class.java)

        assert(transaction.instructions.isNotEmpty())
        // genesis.json has 17 instructions ("isi")
        // Register -> NewDomain
        // Register -> NewAccount (3)
        // Register -> NewAssetDefinition (5)
        // Register -> NewRole
        // Mint -> AssetId
        // Grant -> PermissionToken
        // SetKeyValue -> AssetId (2)
        // Mint -> AssetId (3)
        assert(transaction.instructions.size == 17)

        val genesis = Genesis(transaction)
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
