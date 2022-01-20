package jp.co.soramitsu.iroha2.parse

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jp.co.soramitsu.iroha2.type.ArrayType
import jp.co.soramitsu.iroha2.type.BooleanType
import jp.co.soramitsu.iroha2.type.CompactType
import jp.co.soramitsu.iroha2.type.EnumType
import jp.co.soramitsu.iroha2.type.MapType
import jp.co.soramitsu.iroha2.type.OptionType
import jp.co.soramitsu.iroha2.type.SetType
import jp.co.soramitsu.iroha2.type.StringType
import jp.co.soramitsu.iroha2.type.StructType
import jp.co.soramitsu.iroha2.type.TupleStructType
import jp.co.soramitsu.iroha2.type.Type
import jp.co.soramitsu.iroha2.type.U128Type
import jp.co.soramitsu.iroha2.type.U16Type
import jp.co.soramitsu.iroha2.type.U256Type
import jp.co.soramitsu.iroha2.type.U32Type
import jp.co.soramitsu.iroha2.type.U64Type
import jp.co.soramitsu.iroha2.type.U8Type
import jp.co.soramitsu.iroha2.type.VecType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SchemaParserTest {

    private val gson = Gson()

    @Test
    fun `when schema is empty then types are empty too`() {
        val schemaJson = """
        {}
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assert(types.isEmpty())
    }

    @Test
    fun `should parse boolean type`() {
        val schemaJson = """
        {"bool": "Bool"}
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(1, types.size)
        assertEquals(BooleanType, types["bool"])
    }

    @Test
    fun `should parse recursive relations`() {
        val schemaJson = """
        {
          "foo::bar::AAA": {
            "Struct": {
              "declarations": [
                {
                  "name": "bbb",
                  "ty": "foo::bar::BBB"
                }
              ]
            }
          },
          "foo::bar::BBB": {
            "Struct": {
              "declarations": [
                {
                  "name": "ccc",
                  "ty": "foo::bar::CCC"
                }
              ]
            }
          },
          "foo::bar::CCC": {
            "Struct": {
              "declarations": [
                {
                  "name": "aaa",
                  "ty": "foo::bar::AAA"
                }
              ]
            }
          }
        }
        """.trimIndent()

        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(3, types.size)
    }

    @Test
    fun `should parse string type`() {
        val schemaJson = """
        {"String": "String"}
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(1, types.size)
        assertEquals(StringType, types["String"])
    }

    @Test
    fun `should parse fixed numeric types`() {
        val schemaJson = """
        {
           "u8":{
              "Int":"FixedWidth"
           },
           "u16":{
              "Int":"FixedWidth"
           },
           "u32":{
              "Int":"FixedWidth"
           },
           "u64":{
              "Int":"FixedWidth"
           },
           "u128":{
              "Int":"FixedWidth"
           },
           "u256":{
              "Int":"FixedWidth"
           }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(6, types.size)
        assertEquals(U8Type, types["u8"])
        assertEquals(U16Type, types["u16"])
        assertEquals(U32Type, types["u32"])
        assertEquals(U64Type, types["u64"])
        assertEquals(U128Type, types["u128"])
        assertEquals(U256Type, types["u256"])
    }

    @Test
    fun `should parse compact numeric types`() {
        val schemaJson = """
        {
          "iroha_schema::Compact<u8>": {
            "Int": "Compact"
          },
          "iroha_schema::Compact<u16>": {
            "Int": "Compact"
          },
          "iroha_schema::Compact<u32>": {
            "Int": "Compact"
          },
          "iroha_schema::Compact<u64>": {
            "Int": "Compact"
          },
          "iroha_schema::Compact<u128>": {
            "Int": "Compact"
          },
          "iroha_schema::Compact<u256>": {
            "Int": "Compact"
          }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        val assertAll = { type: Type?, inner: Type ->
            assertNotNull(type)
            assertTrue { type is CompactType }
            val castedType = type as CompactType
            assertNotNull(castedType.innerType.value)
            assertEquals(inner, castedType.innerType.requireValue())
        }

        assertEquals(6, types.size)
        assertAll(types["iroha_schema::Compact<u8>"], U8Type)
        assertAll(types["iroha_schema::Compact<u16>"], U16Type)
        assertAll(types["iroha_schema::Compact<u32>"], U32Type)
        assertAll(types["iroha_schema::Compact<u64>"], U64Type)
        assertAll(types["iroha_schema::Compact<u128>"], U128Type)
        assertAll(types["iroha_schema::Compact<u256>"], U256Type)
    }

    @Test
    fun `should parse maps`() {
        val structName = "foo::bar::Zulu"
        val schemaJson = """
        {
            "BTreeMap<String, $structName>": {
                "Map": {
                  "key": "String",
                  "value": "$structName"
                }
            },
            "String": "String",
            "$structName": {
                "Struct": {
                    "declarations": []
                }
            }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(3, types.size)
        assertEquals(StringType, types["String"])
        val expectedStructType = StructType(structName, listOf(), linkedMapOf())
        assertEquals(expectedStructType, types[structName])
        assertEquals(
            MapType(
                "BTreeMap<String, $structName>",
                TypeNest("String", StringType),
                TypeNest(structName, expectedStructType),
            ),
            types["BTreeMap<String, $structName>"]
        )
    }

    @Test
    fun `should parse options`() {
        val structName = "foo::bar::Zulu"
        val schemaJson = """
        {
            "Option<$structName>": {
              "Option": "$structName"
            },
            "$structName": {
                "Struct": {
                    "declarations": []
                }
            }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(2, types.size)
        val expectedStructType = StructType(structName, listOf(), linkedMapOf())
        assertEquals(expectedStructType, types[structName])
        assertEquals(
            OptionType("Option<$structName>", TypeNest(structName, expectedStructType)),
            types["Option<$structName>"]
        )
    }

    @Test
    fun `should parse vectors`() {
        val structName = "foo::bar::Zulu"
        val schemaJson = """
        {
            "Vec<$structName>": {
                "Vec": "$structName"
            },
            "$structName": {
                "Struct": {
                    "declarations": []
                }
            }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(2, types.size)
        val expectedStructType = StructType(structName, listOf(), linkedMapOf())
        assertEquals(expectedStructType, types[structName])
        assertEquals(
            VecType("Vec<$structName>", TypeNest(structName, expectedStructType)),
            types["Vec<$structName>"]
        )
    }

    @Test
    fun `should parse sets`() {
        val structName = "foo::bar::Zulu"
        val schemaJson = """
        {
            "BTreeSet<$structName>": {
                "BTreeSet": "$structName"
            },
            "$structName": {
                "Struct": {
                    "declarations": []
                }
            }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(2, types.size)
        val expectedStructType = StructType(structName, listOf(), linkedMapOf())
        assertEquals(expectedStructType, types[structName])
        assertEquals(
            SetType("BTreeSet<$structName>", TypeNest(structName, expectedStructType)),
            types["BTreeSet<$structName>"]
        )
    }

    @Test
    fun `should parse arrays`() {
        val structName = "foo::bar::Zulu"
        val schemaJson = """
        {
            "[$structName; 256]": {
              "Array": {
                "ty": "u8",
                "len": 32
              }
            },
            "$structName": {
                "Struct": {
                    "declarations": []
                }
            }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(2, types.size)
        val expectedStructType = StructType(structName, listOf(), linkedMapOf())
        assertEquals(expectedStructType, types[structName])
        assertEquals(
            ArrayType("[$structName; 256]", TypeNest(structName, expectedStructType), 256),
            types["[$structName; 256]"]
        )
    }

    @Test
    fun `should parse structs`() {
        val innerStructName = "foo::bar::Zulu"
        val targetStructName = "foo:bar:IrohaStruct<u8>"
        val schemaJson = """
        {
            "$targetStructName": {
                "Struct": {
                    "declarations": [
                        {
                          "name": "property",
                          "ty": "$innerStructName"
                        }
                    ]
                }
            },
            "$innerStructName": {
                "Struct": {
                    "declarations": []
                }
            },
            "u8":{
              "Int":"FixedWidth"
            }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(3, types.size)
        assertEquals(U8Type, types["u8"])
        val innerStructType = StructType(innerStructName, listOf(), linkedMapOf())
        assertEquals(innerStructType, types[innerStructName])

        val targetStructType = StructType(
            targetStructName,
            listOf(
                TypeNest("u8", U8Type)
            ),
            linkedMapOf(
                Pair("property", TypeNest(innerStructName, innerStructType))
            )
        )
        assertEquals(
            targetStructType,
            types[targetStructName]
        )
    }

    @Test
    fun `should parse tuple structs`() {
        val innerStructName = "foo::bar::Zulu"
        val targetTupleStructName = "foo:bar:IrohaTupleStruct<u8>"
        val schemaJson = """
        {
            "$targetTupleStructName": {
                "TupleStruct": {
                    "types": ["$innerStructName"]
                }
            },
            "$innerStructName": {
                "Struct": {
                    "declarations": []
                }
            },
            "u8":{
              "Int":"FixedWidth"
            }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(3, types.size)
        assertEquals(U8Type, types["u8"])
        val innerStructType = StructType(innerStructName, listOf(), linkedMapOf())
        assertEquals(innerStructType, types[innerStructName])

        val targetTupleStructType = TupleStructType(
            targetTupleStructName,
            listOf(
                TypeNest("u8", U8Type)
            ),
            listOf(TypeNest(innerStructName, innerStructType))
        )
        assertEquals(
            targetTupleStructType,
            types[targetTupleStructName]
        )
    }

    @Test
    fun `should parse enums`() {
        val innerStructName = "foo::bar::Zulu"
        val targetEnumName = "foo:bar:IrohaEnum<u8>"
        val schemaJson = """
        {
            "$targetEnumName": {
                "Enum": {
                    "variants": [
                        {
                            "name": "Variant1",
                            "discriminant": 5,
                            "ty": "$innerStructName" 
                        },
                        {
                            "name": "Variant2",
                            "discriminant": 10
                        }
                    ]
                }
            },
            "$innerStructName": {
                "Struct": {
                    "declarations": []
                }
            },
            "u8":{
              "Int":"FixedWidth"
            }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        val types = SchemaParser().parse(schema)

        assertEquals(3, types.size)
        assertEquals(U8Type, types["u8"])
        val innerStructType = StructType(innerStructName, listOf(), linkedMapOf())
        assertEquals(innerStructType, types[innerStructName])

        val targetEnumType = EnumType(
            targetEnumName,
            listOf(
                TypeNest("u8", U8Type)
            ),
            listOf(
                EnumType.Variant("Variant1", 5, TypeNest(innerStructName, innerStructType)),
                EnumType.Variant("Variant2", 10, null)
            )
        )
        assertEquals(
            targetEnumType,
            types[targetEnumName]
        )
    }

    @Test
    fun `should throw exception if some types was not resolved`() {
        val unresolvedStruct = "foo::bar::Zulu"
        val schemaJson = """
        {
            "Vec<$unresolvedStruct>": {
                "Vec": "$unresolvedStruct"
            }
        }
        """.trimIndent()
        val schema: Schema = gson.fromJson(schemaJson, object : TypeToken<Map<String, Any>>() {}.type)
        assertFailsWith(RuntimeException::class) { SchemaParser().parse(schema) }
    }
}
