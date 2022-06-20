package jp.co.soramitsu.iroha2.parse

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.streams.toList

class SchemaReader {
    companion object {
        private const val SCHEMA_FILTER_TY = "ty:"
        private const val DATA_MODEL_PATTERN = "iroha_data_model::(.+)::"
        private const val MIN_NUMBER_OF_REPEATS_TO_RENAME = 2

        private val dataFilterRegex = "data::filters::(.+)<".toRegex()
        private val eventDataFilterRegex = "\"iroha_data_model::events::data::filters(.+)\"".toRegex()
    }

    private val repeated = mutableMapOf<String, Int>()

    fun readSchema(fileName: String): Schema {
        val resource = Thread.currentThread().contextClassLoader.getResourceAsStream(fileName)!!
        val sb = StringBuilder()
        val lines = resource.bufferedReader().lines().toList()

        lines.forEach { countRepeated(it) }
        repeated.entries.removeIf { it.value <= MIN_NUMBER_OF_REPEATS_TO_RENAME }

        val repeatedRegex = repeated.keys
            .map { it.split("::").last().replace("\": {") }
            .let { classes -> "(${classes.joinToString("|")})" }
            .let { classes -> "$DATA_MODEL_PATTERN$classes\\W".toRegex() }
        lines.forEach { sb.appendLine(parseLine(it, repeatedRegex)) }

        return ObjectMapper().readValue(
            sb.toString(),
            object : TypeReference<MutableMap<String, Any>>() {}
        )
    }

    private fun parseLine(line: String, repeatedRegex: Regex): String {
        return when {
            line.contains(dataFilterRegex) -> parseDataFilterLine(line)
            line.contains(repeatedRegex) -> parseRepeatedLine(line, repeatedRegex)
            else -> line
        }
    }

    private fun parseDataFilterLine(line: String): String {
        var tmpLine = line.substringBeforeLast("\"").replace("\"").trim()
        if (tmpLine.startsWith(SCHEMA_FILTER_TY)) {
            tmpLine = tmpLine.substring(SCHEMA_FILTER_TY.length + 1)
        }
        val newFilterName = getFilterEventName(tmpLine)
        val newLine = "\"" + tmpLine.substringBefore(getDelimiter(line)) + newFilterName + "\""

        return line.replace(eventDataFilterRegex, newLine)
    }

    private fun parseRepeatedLine(line: String, repeatedRegex: Regex): String {
        val extracted = repeatedRegex.find(line)?.value
            ?: return line
        val lineParts = extracted.split("::")
            .takeIf { it.size > 1 }
            ?: return line

        val className = lineParts[lineParts.size - 1].replace(">")
        val prefix = lineParts[lineParts.size - 2]
            .split('_')
            .joinToString("") { p -> p.replaceFirstChar { it.uppercaseChar() } }

        return when (prefix == className || prefix == "${className.replace("\\W".toRegex())}s") {
            true -> line
            false -> line.replace(className, "$prefix$className")
        }
    }

    private fun countRepeated(line: String) {
        if (line.contains("\"$DATA_MODEL_PATTERN(.+)\": \\{".toRegex())) {
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

    private fun String.replace(regex: Regex) = this.replace(regex, "")
}
