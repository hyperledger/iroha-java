package jp.co.soramitsu.iroha2

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import jp.co.soramitsu.iroha2.codegen.CodeGenerator
import jp.co.soramitsu.iroha2.parse.SchemaParser
import java.io.InputStreamReader

const val DEFAULT_SCHEMA_FILE_NAME = "schema.json"

typealias Schema = Map<String, Any>

//todo удалить старую версию либы
//todo сделать плагин для градла
//todo генерировать генезис
//todo версии зависимостей выделить
//todo проблема с репо bintray
//todo detecting deadlocks?
//todo проверка длины для fixed array
fun main() {
    val schema = readSchema()
    val parseResult = SchemaParser.parse(schema)
    CodeGenerator.generate()
}

fun readSchema(fileName : String = DEFAULT_SCHEMA_FILE_NAME): Schema {
    val gson = Gson()
    val resource = Thread.currentThread().contextClassLoader.getResourceAsStream(fileName)!!
    return JsonReader(InputStreamReader(resource))
        .use { gson.fromJson(it, Map::class.java)  }
}
