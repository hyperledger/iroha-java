package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.ChainId
import jp.co.soramitsu.iroha2.generated.Duration
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.RawGenesisTransaction
import jp.co.soramitsu.iroha2.generated.Repeats
import jp.co.soramitsu.iroha2.generated.TriggerId
import jp.co.soramitsu.iroha2.transaction.EventFilters
import jp.co.soramitsu.iroha2.transaction.Instructions
import org.junit.jupiter.api.Test
import java.math.BigInteger
import java.util.UUID
import kotlin.test.assertEquals

class SerializerTest {
    @Test
    fun `should serialize grant permission token genesis block`() {
        val genesis = Genesis(
            RawGenesisTransaction(
                ChainId(UUID.randomUUID().toString()),
                Genesis.EXECUTOR_FILE_NAME,
                emptyList(),
                Instructions.grantPermissionToken(
                    Permissions.CanUnregisterAccount,
                    "ed012004FF5B81046DDCCF19E2E451C45DFB6F53759D4EB30FA2EFA807284D1CC33016${ACCOUNT_ID_DELIMITER}wonderland".asAccountId()
                        .asJsonString(),
                    "ed0120CE7FA46C9DCE7EA4B125E2E36BDB63EA33073E7590AC92816AE1E861B7048B03${ACCOUNT_ID_DELIMITER}wonderland".asAccountId(),
                ).let { listOf(it) },
                emptyList(),
            ),
        )
        val expectedJson = """
            {
                "chain": "00000000-0000-0000-0000-000000000000",
                "executor" : "executor.wasm",
                "instructions" : [ {
                  "Grant" : {
                    "Permission" : {
                      "object" : {
                        "definition" : "CanUnregisterAccount",
                        "payload" : {
                          "account" : "ed012004FF5B81046DDCCF19E2E451C45DFB6F53759D4EB30FA2EFA807284D1CC33016@wonderland"
                        }
                      }
                    },
                    "destination" : "ed0120CE7FA46C9DCE7EA4B125E2E36BDB63EA33073E7590AC92816AE1E861B7048B03@wonderland"
                  }
                } ]
            }
        """.trimIndent()
        val json = JSON_SERDE.writeValueAsString(genesis).trimIndent()
        assertEquals(expectedJson, json)
    }

    @Test
    fun `should serialize mint asset genesis block`() {
        val triggerId = TriggerId(name = Name("time_trigger"))
        val aliceAccountId =
            "ed0120CE7FA46C9DCE7EA4B125E2E36BDB63EA33073E7590AC92816AE1E861B7048B03${ACCOUNT_ID_DELIMITER}wonderland".asAccountId()
        val assetId = AssetId(
            aliceAccountId,
            AssetDefinitionId("wonderland".asDomainId(), "xor".asName()),
        )
        val genesis = Genesis(
            RawGenesisTransaction(
                ChainId(UUID.randomUUID().toString()),
                Genesis.EXECUTOR_FILE_NAME,
                emptyList(),
                listOf(
                    Instructions.mintAsset(assetId, 100),
                    Instructions.setKeyValue(
                        assetId,
                        "key".asName(),
                        "value",
                    ),
                    Instructions.registerTrigger(
                        triggerId,
                        listOf(Instructions.mintAsset(assetId, 1)),
                        Repeats.Indefinitely(),
                        aliceAccountId,
                        Metadata(mapOf()),
                        EventFilters.timeEventFilter(
                            Duration(BigInteger.valueOf(1715676978L), 0L),
                            Duration(BigInteger.valueOf(1L), 0L),
                        ),
                    ),
                ),
                emptyList(),
            ),
        )
        val expectedJson = """
            {
              "block" : {
                "chain": "00000000-0000-0000-0000-000000000000",
                "executor" : "executor.wasm",
                "instructions" : [ {
                  "Mint" : {
                    "object" : "100_u32",
                    "destination" : {
                      "AssetId" : "xor#wonderland#ed0120CE7FA46C9DCE7EA4B125E2E36BDB63EA33073E7590AC92816AE1E861B7048B03@wonderland"
                    }
                  }
                }, {
                  "Sequence" : [ {
                    "SetKeyValue" : {
                      "AssetId" : "xor#wonderland#ed0120CE7FA46C9DCE7EA4B125E2E36BDB63EA33073E7590AC92816AE1E861B7048B03@wonderland",
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
                              "destination" : {
                                "AssetId" : "xor#wonderland#ed0120CE7FA46C9DCE7EA4B125E2E36BDB63EA33073E7590AC92816AE1E861B7048B03@wonderland"
                              }
                            }
                          } ]
                        },
                        "repeats" : "Indefinitely",
                        "authority" : "ed0120CE7FA46C9DCE7EA4B125E2E36BDB63EA33073E7590AC92816AE1E861B7048B03@wonderland",
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
                } ]
              }
            }
        """.trimIndent()
        val json = JSON_SERDE.writeValueAsString(genesis).trimIndent()
        assertEquals(expectedJson, json)
    }
}
