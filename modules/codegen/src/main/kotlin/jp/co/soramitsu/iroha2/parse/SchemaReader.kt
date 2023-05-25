package jp.co.soramitsu.iroha2.parse

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.streams.toList

class SchemaReader {

    /**
     * Read Iroha2 schema from a given [file][fileName]
     */
    fun readSchema(fileName: String): Map<String, Any> {
        val resource = Thread.currentThread().contextClassLoader.getResourceAsStream(fileName)!!
        val sb = StringBuilder()
        val lines = resource.bufferedReader().lines().toList()

        lines.forEach { sb.appendLine(it) }

        return ObjectMapper().readValue(
            sb.toString(),
            object : TypeReference<MutableMap<String, Any>>() {}
        )
    }
}
