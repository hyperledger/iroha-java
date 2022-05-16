@file:JvmName("EntryPoint")

package jp.co.soramitsu.iroha2

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import jp.co.soramitsu.iroha2.codegen.generator.GeneratorEntryPoint
import jp.co.soramitsu.iroha2.parse.Schema
import jp.co.soramitsu.iroha2.parse.SchemaParser
import java.nio.file.Paths

const val OUTPUT_PATH_ARG_NAME = "outputPath"
const val SCHEMA_FILE_ARG_NAME = "schemaFileName"
const val SCHEMA_FILTER_TY = "ty:"
val schemaFilterPattern = "data::filters::(.+)<".toRegex()
val schemaFilterRegex = "\"iroha_data_model::events::data::filters(.+)\"".toRegex()

fun main(args: Array<String>) {
    val argsMap = parseArgs(args)
    val outputPath = Paths.get(tryExtractArg(argsMap, OUTPUT_PATH_ARG_NAME))
    val schemaFileName = tryExtractArg(argsMap, SCHEMA_FILE_ARG_NAME)

    val schema = readSchema(schemaFileName)
    val parseResult = SchemaParser().parse(schema)
    GeneratorEntryPoint.generate(parseResult, outputPath)
}

fun getFilterEventName(name: String): String {
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

fun readSchema(fileName: String): Schema {
    val resource = Thread.currentThread().contextClassLoader.getResourceAsStream(fileName)!!
    val sb = StringBuilder()
    resource.bufferedReader().forEachLine { line -> sb.appendLine(parseLine(line)) }
    return ObjectMapper().readValue(
        sb.toString(),
        object : TypeReference<Map<String, Any>>() {}
    )
}

fun parseLine(line: String): String {
    if (line.contains(schemaFilterPattern)) {
        var tmpLine = line.substringBeforeLast("\"").replace("\"", "").trim()
        if (tmpLine.startsWith(SCHEMA_FILTER_TY)) {
            tmpLine = tmpLine.substring(SCHEMA_FILTER_TY.length + 1)
        }
        val newFilterName = getFilterEventName(tmpLine)
        val newLine = "\"" + tmpLine.substringBefore(getDelimiter(line)) + newFilterName + "\""
        return line.replace(schemaFilterRegex, newLine)
    }
    return line
}

fun getDelimiter(name: String): String {
    return name.substringBefore("<").substringAfterLast("::")
}

fun parseArgs(args: Array<String>): Map<String, String> {
    return args.map { it.split("=") }
        .onEach { if (it.size != 2) throw RuntimeException("Incorrect format: expected format argumentKey=argumentValue") }
        .associateBy({ it[0] }, { it[1] })
}

fun tryExtractArg(args: Map<String, String>, argName: String): String {
    return args[argName] ?: throw RuntimeException("Property '$argName' must be specified")
}
