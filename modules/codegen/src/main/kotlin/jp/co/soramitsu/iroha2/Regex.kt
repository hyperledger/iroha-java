package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.parse.ArrayResolver

val COMMON_SCHEMA_GENERIC_REGEX = "^.*\"([^<]*)<(.+)>\".*\$".toRegex() // "type": "PartName<SubType>"
val DEFINITION_SCHEMA_GENERIC_REGEX = "^\\s{2}\"([^<]*)<(.+)>\".*\$".toRegex() // "PartName<SubType>": {
val GENERIC_REGEX = "^([^<]*)<(.+)>\$".toRegex() // PartName<SubType>
val ARRAY_REGEX = "${ArrayResolver.NAME}<(\\S+), (\\S+)\\>".toRegex()
