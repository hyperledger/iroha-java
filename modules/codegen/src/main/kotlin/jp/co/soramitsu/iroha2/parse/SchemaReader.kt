package jp.co.soramitsu.iroha2.parse

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.streams.toList

class SchemaReader {
    companion object {
        const val SCHEMA_FILTER_TY = "ty:"
        val dataFilterPattern = "data::filters::(.+)<".toRegex()
        val dataFilterRegex = "\"iroha_data_model::events::data::filters(.+)\"".toRegex()
    }

    private val repeated = mutableMapOf<String, Int>()

    fun readSchema(fileName: String): Schema {
        val resource = Thread.currentThread().contextClassLoader.getResourceAsStream(fileName)!!
        val sb = StringBuilder()
        val lines = resource.bufferedReader().lines().toList()

        lines.forEach { countRepeated(it) }
        repeated.entries.removeIf { it.value <= 1 }

        lines.forEach { sb.appendLine(parseLine(it)) }

        return ObjectMapper().readValue(
            sb.toString(),
            object : TypeReference<MutableMap<String, Any>>() {}
        )
    }

    private fun parseLine(line: String): String {
        if (line.contains(dataFilterPattern)) {
            var tmpLine = line.substringBeforeLast("\"").replace("\"", "").trim()
            if (tmpLine.startsWith(SCHEMA_FILTER_TY)) {
                tmpLine = tmpLine.substring(SCHEMA_FILTER_TY.length + 1)
            }
            val newFilterName = getFilterEventName(tmpLine)
            val newLine = "\"" + tmpLine.substringBefore(getDelimiter(line)) + newFilterName + "\""
            return line.replace(dataFilterRegex, newLine)
        }

        return line
    }

    private fun countRepeated(line: String) {
        if (line.contains("\"iroha_data_model::(.+)::(.+)\": \\{".toRegex())) {
            val parts = line.split("::")
            if (parts.size <= 1) return

            val key = "::${parts.last()}"
            val counter = repeated.getOrDefault(key, 0)
            repeated[key] = counter + 1
        }
    }

    private fun getDelimiter(name: String): String {
        return name.substringBefore("<").substringAfterLast("::")
    }

    private fun getFilterEventName(name: String): String {
        if (name.contains("data::filters")) {
            val newName = name.substringBefore("<").substringAfterLast("::")
            return when {
                !name.contains("<") -> newName.replace(">", "")
                else -> newName + getFilterEventName(name.substringAfter("<"))
            }
        }
        val tokens = name.replace(">", "").split("::")
        val sb = StringBuilder()
        for (i in 1 until tokens.size) {
            sb.append(tokens[i].replaceFirstChar { tokens[i].first().uppercaseChar() })
        }
        return sb.toString()
    }
}
