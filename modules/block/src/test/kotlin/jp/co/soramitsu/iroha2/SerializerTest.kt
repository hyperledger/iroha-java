package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.Duration
import jp.co.soramitsu.iroha2.generated.InstructionExpr
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.RawGenesisBlock
import jp.co.soramitsu.iroha2.generated.Repeats
import jp.co.soramitsu.iroha2.generated.SequenceExpr
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.transaction.EventFilters
import jp.co.soramitsu.iroha2.transaction.Instructions
import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class SerializerTest {
    @Test
    fun `should serialize grant permission token genesis block`() {
        val genesis = Genesis(
            RawGenesisBlock(
                listOf(
                    Instructions.grantPermissionToken(
                        Permissions.CanUnregisterAccount,
                        "bob${ACCOUNT_ID_DELIMITER}wonderland".asAccountId().asJsonString(),
                        "alice${ACCOUNT_ID_DELIMITER}wonderland".asAccountId(),
                    ),
                ).let { listOf(it) },
                Genesis.executorMode,
            ),
        )
        val expectedJson = """
            {
              "block" : {
                "transactions" : [ [ {
                  "Grant" : {
                    "object" : {
                      "PermissionToken" : {
                        "definition_id" : "CanUnregisterAccount",
                        "payload" : {
                          "account_id" : "bob@wonderland"
                        }
                      }
                    },
                    "destination_id" : {
                      "AccountId" : "alice@wonderland"
                    }
                  }
                } ] ],
                "executor" : "executor.wasm"
              }
            }
        """.trimIndent()
        val json = JSON_SERDE.writeValueAsString(genesis).trimIndent()
        assertEquals(expectedJson, json)
    }

    @Test
    fun `should serialize mint asset genesis block`() {
        val triggerId = TriggerId(name = Name("time_trigger"))
        val aliceAccountId = "alice${ACCOUNT_ID_DELIMITER}wonderland".asAccountId()
        val assetId = AssetId(
            AssetDefinitionId("xor".asName(), "wonderland".asDomainId()),
            aliceAccountId,
        )
        val genesis = Genesis(
            RawGenesisBlock(
                listOf(
                    Instructions.mintAsset(
                        assetId,
                        100,
                    ),
                    InstructionExpr.Sequence(
                        SequenceExpr(
                            listOf(
                                Instructions.setKeyValue(
                                    assetId,
                                    "key".asName(),
                                    "value".asValue(),
                                ),
                            ),
                        ),
                    ),
                    Instructions.registerTimeTrigger(
                        triggerId,
                        listOf(
                            Instructions.mintAsset(
                                assetId,
                                1,
                            ),
                        ),
                        Repeats.Indefinitely(),
                        aliceAccountId,
                        EventFilters.timeEventFilter(
                            Duration(BigInteger.valueOf(1715676978L), 0L),
                            Duration(BigInteger.valueOf(1L), 0L),
                        ),
                        Metadata(mapOf()),
                    ),
                ).let { listOf(it) },
                Genesis.executorMode,
            ),
        )
        val expectedJson = """
            {
              "block" : {
                "transactions" : [ [ {
                  "Mint" : {
                    "object" : "100_u32",
                    "destination_id" : {
                      "AssetId" : "xor#wonderland#alice@wonderland"
                    }
                  }
                }, {
                  "Sequence" : [ {
                    "SetKeyValue" : {
                      "AssetId" : "xor#wonderland#alice@wonderland",
                      "key" : {
                        "Name" : "key"
                      },
                      "value" : {
                        "String" : "value"
                      }
                    }
                  } ]
                }, {
                  "Register" : {
                    "Trigger" : {
                      "id" : "time_trigger",
                      "action" : {
                        "executable" : {
                          "Instructions" : [ {
                            "Mint" : {
                              "object" : "1_u32",
                              "destination_id" : {
                                "AssetId" : "xor#wonderland#alice@wonderland"
                              }
                            }
                          } ]
                        },
                        "repeats" : "Indefinitely",
                        "authority" : "alice@wonderland",
                        "filter" : {
                          "Time" : {
                            "Schedule" : {
                              "start" : {
                                "secs" : 1715676978,
                                "nanos" : 0
                              },
                              "period" : {
                                "secs" : 1,
                                "nanos" : 0
                              }
                            }
                          }
                        },
                        "metadata" : { }
                      }
                    }
                  }
                } ] ],
                "executor" : "executor.wasm"
              }
            }
        """.trimIndent()
        val json = JSON_SERDE.writeValueAsString(genesis).trimIndent()
        assertEquals(expectedJson, json)
    }
}
