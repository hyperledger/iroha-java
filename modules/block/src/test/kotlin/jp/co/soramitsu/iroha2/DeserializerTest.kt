package jp.co.soramitsu.iroha2

import io.ktor.util.toLowerCasePreservingASCIIRules
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
        assert(transaction.instructions.size == 16)

        val genesis = Genesis(transaction)
        val newJson = removeWhiteSpaceAndReplacePubKey(genesis.asJson())
        val initialJson = removeWhiteSpaceAndReplacePubKey(json.readText())
        assertEquals(initialJson.lowercase(), newJson.lowercase())
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

// {"chain":"00000000-0000-0000-0000-000000000000","executor":"./executor.wasm","parameters":[{"sumeragi":{"blocktimems":2000}},{"sumeragi":{"committimems":4000}},{"block":{"maxtransactions":512}},{"transaction":{"maxinstructions":4096}},{"transaction":{"smartcontractsize":4194304}},{"executor":{"fuel":55000000}},{"executor":{"memory":55000000}},{"smartcontract":{"fuel":55000000}},{"smartcontract":{"memory":55000000}}],"instructions":[{"register":{"domain":{"id":"wonderland","logo":null,"metadata":{"key":"value"}}}},{"register":{"trigger":{"id":"name$wonderland","action":{"executable":{"instructions":[{"setkeyvalue":{"domain":{"object":"wonderland","key":"key","value":"value"}}}]},"repeats":{"exactly":1},"authority":"ed01204164bf554923ece1fd412d241036d863a6ae430476c898248b8237d77534cfc4@wonderland","filter":{"executetrigger":{"trigger":"name","authority":"ed01204164bf554923ece1fd412d241036d863a6ae430476c898248b8237d77534cfc4@wonderland"}},"metadata":{}}}}},{"register":{"trigger":{"id":"time_trigger","action":{"executable":{"instructions":[{"setkeyvalue":{"domain":{"object":"wonderland","key":"key","value":"value"}}}]},"repeats":"indefinitely","authority":"ed01204164bf554923ece1fd412d241036d863a6ae430476c898248b8237d77534cfc4@wonderland","filter":{"time":{"schedule":{"start_ms":1790774026000,"period_ms":1000}}},"metadata":{}}}}},{"register":{"account":{"id":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland","metadata":{"key":"value"}}}},{"register":{"account":{"id":"ed012004ff5b81046ddccf19e2e451c45dfb6f53759d4eb30fa2efa807284d1cc33016@wonderland","metadata":{"key":"value"}}}},{"register":{"assetdefinition":{"id":"rose#wonderland","type":"numeric","mintable":"infinitely","logo":null,"metadata":{}}}},{"register":{"domain":{"id":"garden_of_live_flowers","logo":null,"metadata":{}}}},{"register":{"account":{"id":"ed0120e9f632d3034bab6bb26d92ac8fd93ef878d9c5e69e01b61b4c47101884ee2f99@garden_of_live_flowers","metadata":{}}}},{"register":{"assetdefinition":{"id":"cabbage#garden_of_live_flowers","type":"numeric","mintable":"infinitely","logo":null,"metadata":{}}}},{"mint":{"asset":{"object":"13","destination":"rose#wonderland#ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}},{"mint":{"asset":{"object":"44","destination":"cabbage#garden_of_live_flowers#ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}},{"transfer":{"assetdefinition":{"source":"ed01204164bf554923ece1fd412d241036d863a6ae430476c898248b8237d77534cfc4@genesis","object":"rose#wonderland","destination":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}},{"transfer":{"domain":{"source":"ed01204164bf554923ece1fd412d241036d863a6ae430476c898248b8237d77534cfc4@genesis","object":"wonderland","destination":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}},{"grant":{"permission":{"object":{"name":"cansetparameters","payload":null},"destination":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}},{"register":{"role":{"id":"alice_metadata_access","permissions":[{"name":"canremovekeyvalueinaccount","payload":{"account":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}},{"name":"cansetkeyvalueinaccount","payload":{"account":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}]}}},{"register":{"trigger":{"id":"name2$wonderland","action":{"executable":{"instructions":[{"setkeyvalue":{"domain":{"object":"wonderland","key":"key","value":"value"}}}]},"repeats":{"exactly":1},"authority":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland","filter":{"executetrigger":{"trigger":"name","authority":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}},"metadata":{}}}}}],"topology":[]}
// {"chain":"00000000-0000-0000-0000-000000000000","executor":"./executor.wasm","parameters":[{"sumeragi":{"blocktimems":2000}},{"sumeragi":{"committimems":4000}},{"block":{"maxtransactions":512}},{"transaction":{"maxinstructions":4096}},{"transaction":{"smartcontractsize":4194304}},{"executor":{"fuel":55000000}},{"executor":{"memory":55000000}},{"smartcontract":{"fuel":55000000}},{"smartcontract":{"memory":55000000}}],"instructions":[{"register":{"domain":{"id":"wonderland","logo":null,"metadata":{"key":"value"}}}},{"register":{"trigger":{"id":"name$wonderland","action":{"executable":{"instructions":[{"setkeyvalue":{"domain":{"object":"wonderland","key":"key","value":"value"}}}]},"repeats":{"exactly":1},"authority":"ed01204164bf554923ece1fd412d241036d863a6ae430476c898248b8237d77534cfc4@wonderland","filter":{"executetrigger":{"trigger":"name","authority":"ed01204164bf554923ece1fd412d241036d863a6ae430476c898248b8237d77534cfc4@wonderland"}},"metadata":{}}}}},{"register":{"trigger":{"id":"time_trigger","action":{"executable":{"instructions":[{"setkeyvalue":{"domain":{"object":"wonderland","key":"key","value":"value"}}}]},"repeats":"indefinitely","authority":"ed01204164bf554923ece1fd412d241036d863a6ae430476c898248b8237d77534cfc4@wonderland","filter":{"time":{"schedule":{"start_ms":1790774026000,"period_ms":1000}}},"metadata":{}}}}},{"register":{"account":{"id":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland","metadata":{"key":"value"}}}},{"register":{"account":{"id":"ed012004ff5b81046ddccf19e2e451c45dfb6f53759d4eb30fa2efa807284d1cc33016@wonderland","metadata":{"key":"value"}}}},{"register":{"assetdefinition":{"id":"rose#wonderland","type":"numeric","mintable":"infinitely","logo":null,"metadata":{}}}},{"register":{"domain":{"id":"garden_of_live_flowers","logo":null,"metadata":{}}}},{"register":{"account":{"id":"ed0120e9f632d3034bab6bb26d92ac8fd93ef878d9c5e69e01b61b4c47101884ee2f99@garden_of_live_flowers","metadata":{}}}},{"register":{"assetdefinition":{"id":"cabbage#garden_of_live_flowers","type":"numeric","mintable":"infinitely","logo":null,"metadata":{}}}},{"mint":{"asset":{"object":"13","destination":"rose#wonderland#ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}},{"mint":{"asset":{"object":"44","destination":"cabbage#garden_of_live_flowers#ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}},{"transfer":{"assetdefinition":{"source":"ed01204164bf554923ece1fd412d241036d863a6ae430476c898248b8237d77534cfc4@genesis","object":"rose#wonderland","destination":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}},{"transfer":{"domain":{"source":"ed01204164bf554923ece1fd412d241036d863a6ae430476c898248b8237d77534cfc4@genesis","object":"wonderland","destination":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}},{"grant":{"permission":{"object":{"name":"cansetparameters","payload":"null"},"destination":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}}},{"register":{"role":{"inner":{"id":"alice_metadata_access","permissions":[{"name":"canremovekeyvalueinaccount","payload":""},{"name":"cansetkeyvalueinaccount","payload":""}]}}}},{"register":{"trigger":{"id":"name2$wonderland","action":{"executable":{"instructions":[{"setkeyvalue":{"domain":{"object":"wonderland","key":"key","value":"value"}}}]},"repeats":{"exactly":1},"authority":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland","filter":{"executetrigger":{"trigger":"name","authority":"ed0120ce7fa46c9dce7ea4b125e2e36bdb63ea33073e7590ac92816ae1e861b7048b03@wonderland"}},"metadata":{}}}}}],"topology":[]}
