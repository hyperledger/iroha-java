// package jp.co.soramitsu.iroha2
//
// import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
// import jp.co.soramitsu.iroha2.generated.AssetId
// import jp.co.soramitsu.iroha2.generated.RawGenesisBlock
// import jp.co.soramitsu.iroha2.transaction.Instructions
// import org.junit.jupiter.api.Test
// import kotlin.test.assertEquals
//
// class SerializerTest { todo
//    @Test
//    fun `should serialize grant permission token genesis block`() {
//        val genesis = Genesis(
//            RawGenesisBlock(
//                listOf(
//                    Instructions.grantPermissionToken(
//                        Permissions.CanUnregisterAccount,
//                        "bob${ACCOUNT_ID_DELIMITER}wonderland".asAccountId().asJsonString(),
//                        "alice${ACCOUNT_ID_DELIMITER}wonderland".asAccountId(),
//                    ),
//                ).let { listOf(it) },
//                Genesis.executorMode,
//            ),
//        )
//        val expectedJson = """
//            {
//              "block" : {
//                "transactions" : [ [ {
//                  "Grant" : {
//                    "object" : {
//                      "PermissionToken" : {
//                        "definition_id" : "CanUnregisterAccount",
//                        "payload" : {
//                          "account_id" : "bob@wonderland"
//                        }
//                      }
//                    },
//                    "destination_id" : {
//                      "AccountId" : "alice@wonderland"
//                    }
//                  }
//                } ] ],
//                "executor" : "executor.wasm"
//              }
//            }
//        """.trimIndent()
//        val json = JSON_SERDE.writeValueAsString(genesis).trimIndent()
//        assertEquals(expectedJson, json)
//    }
//
//    @Test
//    fun `should serialize mint asset genesis block`() {
//        val genesis = Genesis(
//            RawGenesisBlock(
//                listOf(
//                    Instructions.mintAsset(
//                        AssetId(
//                            AssetDefinitionId("xor".asName(), "wonderland".asDomainId()),
//                            "alice${ACCOUNT_ID_DELIMITER}wonderland".asAccountId(),
//                        ),
//                        100,
//                    ),
//                ).let { listOf(it) },
//                Genesis.executorMode,
//            ),
//        )
//        val expectedJson = """
//            {
//              "block" : {
//                "transactions" : [ [ {
//                  "Mint" : {
//                    "object" : "100_u32",
//                    "destination_id" : {
//                      "AssetId" : "xor#wonderland#alice@wonderland"
//                    }
//                  }
//                } ] ],
//                "executor" : "executor.wasm"
//              }
//            }
//        """.trimIndent()
//        val json = JSON_SERDE.writeValueAsString(genesis).trimIndent()
//        assertEquals(expectedJson, json)
//    }
// }
