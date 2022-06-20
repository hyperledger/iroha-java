package jp.co.soramitsu.iroha2.parse

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.streams.toList

class SchemaReader {
    companion object {
        private const val SCHEMA_FILTER_TY = "ty:"
        private const val REPEATED_PATTERN = "iroha_data_model::(.+)::"

        private val dataFilterRegex = "data::filters::(.+)<".toRegex()
        private val eventDataFilterRegex = "\"iroha_data_model::events::data::filters(.+)\"".toRegex()
    }

    private val repeated = mutableMapOf<String, Int>()

    fun readSchema(fileName: String): Schema {
        val resource = Thread.currentThread().contextClassLoader.getResourceAsStream(fileName)!!
        val sb = StringBuilder()
        val lines = resource.bufferedReader().lines().toList()

        lines.forEach { countRepeated(it) }
        repeated.entries.removeIf { it.value <= 2 } // todo <= 1

        val repeatedRegex = repeated.keys
            .map { it.split("::").last().replace("\": {") }
            .let { classes -> "(${classes.joinToString("|")})" }
            .let { classes -> "$REPEATED_PATTERN$classes\\W".toRegex() }
        lines.forEach { sb.appendLine(parseLine(it, repeatedRegex)) }

        return ObjectMapper().readValue(
            sb.toString(),
            object : TypeReference<MutableMap<String, Any>>() {}
        )
    }

    private fun parseLine(line: String, repeatedRegex: Regex): String {
        if (line.contains(dataFilterRegex)) {
            var tmpLine = line.substringBeforeLast("\"").replace("\"").trim()
            if (tmpLine.startsWith(SCHEMA_FILTER_TY)) {
                tmpLine = tmpLine.substring(SCHEMA_FILTER_TY.length + 1)
            }
            val newFilterName = getFilterEventName(tmpLine)
            val newLine = "\"" + tmpLine.substringBefore(getDelimiter(line)) + newFilterName + "\""

            return line.replace(eventDataFilterRegex, newLine)
        } else if (line.contains(repeatedRegex)) {
            val extracted = repeatedRegex.find(line)?.value
                ?: return line

            val lineParts = extracted.split("::")
            val className = lineParts[lineParts.size - 1] // todo safely
            val prefix = lineParts[lineParts.size - 2]
                .split('_').joinToString("") { part ->
                    part.replaceFirstChar { it.uppercaseChar() }
                }

            return extracted.replace(
                className, "$prefix$className"
            ).let { modified ->
                line.replace(extracted, modified)
            }
        }

        return line
    }

    private fun countRepeated(line: String) {
        if (line.contains("\"iroha_data_model::(.+)::(.+)\": \\{".toRegex())) { // todo
            val parts = line.split("::")
            if (parts.size <= 1) return

            val key = "::${parts.last()}".replace(">")
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
                !name.contains("<") -> newName.replace(">")
                else -> newName + getFilterEventName(name.substringAfter("<"))
            }
        }
        val tokens = name.replace(">").split("::")
        val sb = StringBuilder()
        for (i in 1 until tokens.size) {
            sb.append(tokens[i].replaceFirstChar { tokens[i].first().uppercaseChar() })
        }
        return sb.toString()
    }

    private fun String.replace(oldValue: String) = this.replace(oldValue, "")
}
