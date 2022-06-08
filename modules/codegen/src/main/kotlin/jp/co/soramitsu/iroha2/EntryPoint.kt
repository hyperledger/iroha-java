@file:JvmName("EntryPoint")

package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.codegen.generator.GeneratorEntryPoint
import jp.co.soramitsu.iroha2.parse.SchemaParser
import jp.co.soramitsu.iroha2.parse.SchemaReader
import java.nio.file.Paths

const val OUTPUT_PATH_ARG_NAME = "outputPath"
const val SCHEMA_FILE_ARG_NAME = "schemaFileName"

fun main(args: Array<String>) {
    val argsMap = parseArgs(args)
    val outputPath = Paths.get(tryExtractArg(argsMap, OUTPUT_PATH_ARG_NAME))
    val schemaFileName = tryExtractArg(argsMap, SCHEMA_FILE_ARG_NAME)

    val schema = SchemaReader().readSchema(schemaFileName)
    val parseResult = SchemaParser().parse(schema)
    GeneratorEntryPoint.generate(parseResult, outputPath)
}

fun parseArgs(args: Array<String>): Map<String, String> {
    return args.map { it.split("=") }
        .onEach { if (it.size != 2) throw RuntimeException("Incorrect format: expected format argumentKey=argumentValue") }
        .associateBy({ it[0] }, { it[1] })
}

fun tryExtractArg(args: Map<String, String>, argName: String): String {
    return args[argName] ?: throw RuntimeException("Property '$argName' must be specified")
}
