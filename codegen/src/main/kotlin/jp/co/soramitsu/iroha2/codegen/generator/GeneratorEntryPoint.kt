package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.*
import jp.co.soramitsu.iroha2.codegen.blueprint.EnumBlueprint
import jp.co.soramitsu.iroha2.codegen.blueprint.StructBlueprint
import jp.co.soramitsu.iroha2.codegen.blueprint.TupleStructBlueprint
import jp.co.soramitsu.iroha2.parse.Types
import jp.co.soramitsu.iroha2.type.*
import java.nio.file.Paths

//todo all enum variants must imoplement discrimant behaviour
//todo versions of dependencies
//todo move modules to folder
object GeneratorEntryPoint {
    fun generate(types: Types) {
        types.values.mapNotNull {
            when(it) {
                is StructType -> StructBlueprint(it)
                is EnumType -> EnumBlueprint(it)
                is TupleStructType -> TupleStructBlueprint(it)
                else -> null
            }
        }.forEach {
            val typeSpec = when(it) {
                is StructBlueprint -> StructGenerator.generate(it)
                is EnumBlueprint -> EnumGenerator.generate(it)
                is TupleStructBlueprint -> TupleStructGenerator.generate(it)
                else -> throw RuntimeException("Unexpected blueprint type: ${it::class}")
            }
            FileSpec.builder(it.packageName, it.className)
                .addType(typeSpec)
                .addComment("\nAuto-generated file. DO NOT EDIT!\n")
                .build()
                .writeTo(Paths.get("client/src/main/kotlin"))
        }
    }
}
