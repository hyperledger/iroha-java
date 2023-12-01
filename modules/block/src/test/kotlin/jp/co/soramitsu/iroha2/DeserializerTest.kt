package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.RawGenesisBlock
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
        // genesis.json has 8 instructions ("isi")
        // Register -> NewDomain
        // Register -> NewAccount (2)
        // Register -> NewAssetDefinition
        // Grant -> PermissionToken (2)
        // Mint -> AssetId (2)
        assert(block.transactions.flatten().size == 8)

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
        assert(block.transactions.flatten().size == 23)

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
        // genesis.json has 17 instructions ("isi")
        // Register -> NewDomain
        // Register -> NewAccount (3)
        // Register -> NewAssetDefinition (5)
        // Register -> NewRole
        // Mint -> AssetId
        // Grant -> PermissionToken
        // SetKeyValue -> AssetId (2)
        // Mint -> AssetId (3)
        assert(block.transactions.flatten().size == 17)

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

// {"transactions":[[{"Register":{"NewDomain":{"id":"wonderland","logo":null,"metadata":{}}}},{"Register":{"NewDomain":{"id":"dnalrednow","logo":null,"metadata":{}}}},{"Register":{"NewDomain":{"id":"test","logo":null,"metadata":{}}}},{"Register":{"NewDomain":{"id":"admin","logo":null,"metadata":{}}}},{"Register":{"NewAccount":{"id":"alice@wonderland","signatories":[publicKey],"metadata":{}}}},{"Register":{"NewAccount":{"id":"admin@wonderland","signatories":[publicKey],"metadata":{}}}},{"Register":{"NewAccount":{"id":"admin@dnalrednow","signatories":[publicKey],"metadata":{}}}},{"Register":{"NewAccount":{"id":"admin2@wonderland","signatories":[publicKey],"metadata":{}}}},{"Register":{"NewAccount":{"id":"bob@admin","signatories":[publicKey],"metadata":{"authentication":{"String":"9321c6560bdbe28df7808e4d03b0ef625c4e8f8e9935907763477ac601421066"}}}}},{"Register":{"NewAssetDefinition":{"id":"test_asset#admin","value_type":"Store","mintable":"Once","logo":null,"metadata":{}}}},{"Register":{"NewAssetDefinition":{"id":"wonderland_asset#admin","value_type":"Store","mintable":"Once","logo":null,"metadata":{}}}},{"Register":{"NewAssetDefinition":{"id":"123#test","value_type":"Store","mintable":"Infinitely","logo":null,"metadata":{}}}},{"Register":{"Asset":{"id":"wonderland_asset#admin#bob@admin","value":{"Store":{}}}}},{"Register":{"Asset":{"id":"test_asset#admin#bob@admin","value":{"Store":{}}}}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"id"},"value":{"String":"123"}}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"wt"},"value":"123"}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"fg"},"value":{"String":"test"}}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"ij"},"value":{"String":"test"}}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"gh"},"value":"123"}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"ef"},"value":"1234"}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"cd"},"value":"123"}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"ab"},"value":{"Bool":false}}},{"Grant":{"object":{"PermissionToken":{"definition_id":"CanSetKeyValueInUserAssets","payload":{"asset_id":"123#test#alice@wonderland"}}},"destination_id":{"AccountId":"bob@admin"}}}]],"executor":"./executor.wasm"}
// {"transactions":[[{"Register":{"NewDomain":{"id":"wonderland","logo":null,"metadata":{}}}},{"Register":{"NewDomain":{"id":"dnalrednow","logo":null,"metadata":{}}}},{"Register":{"NewDomain":{"id":"test","logo":null,"metadata":{}}}},{"Register":{"NewDomain":{"id":"admin","logo":null,"metadata":{}}}},{"Register":{"NewAccount":{"id":"alice@wonderland","signatories":[publicKey],"metadata":{}}}},{"Register":{"NewAccount":{"id":"admin@wonderland","signatories":[publicKey],"metadata":{}}}},{"Register":{"NewAccount":{"id":"admin@dnalrednow","signatories":[publicKey],"metadata":{}}}},{"Register":{"NewAccount":{"id":"admin2@wonderland","signatories":[publicKey],"metadata":{}}}},{"Register":{"NewAccount":{"id":"bob@admin","signatories":[publicKey],"metadata":{"authentication":{"String":"9321c6560bdbe28df7808e4d03b0ef625c4e8f8e9935907763477ac601421066"}}}}},{"Register":{"NewAssetDefinition":{"id":"test_asset#admin","value_type":"Store","mintable":"Once","logo":null,"metadata":{}}}},{"Register":{"NewAssetDefinition":{"id":"wonderland_asset#admin","value_type":"Store","mintable":"Once","logo":null,"metadata":{}}}},{"Register":{"NewAssetDefinition":{"id":"123#test","value_type":"Store","mintable":"Infinitely","logo":null,"metadata":{}}}},{"Register":{"Asset":{"id":"wonderland_asset#admin#bob@admin","value":{"Store":{}}}}},{"Register":{"Asset":{"id":"test_asset#admin#bob@admin","value":{"Store":{}}}}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"id"},"value":{"String":"123"}}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"wt"},"value":"123_u32"}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"fg"},"value":{"String":"test"}}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"ij"},"value":{"String":"test"}}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"gh"},"value":"123_u32"}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"ef"},"value":"1234_u32"}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"cd"},"value":"123_u32"}},{"SetKeyValue":{"AssetId":"123#test#alice@wonderland","key":{"Name":"ab"},"value":{"Bool":false}}},{"Grant":{"object":{"PermissionToken":{"definition_id":"CanSetKeyValueInUserAssets","payload":{"asset_id":"123#test#alice@wonderland"}}},"destination_id":{"AccountId":"bob@admin"}}}]],"executor":"./executor.wasm"}
