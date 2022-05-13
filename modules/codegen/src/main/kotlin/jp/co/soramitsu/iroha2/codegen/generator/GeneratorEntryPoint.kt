package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.FileSpec
import jp.co.soramitsu.iroha2.codegen.blueprint.EnumBlueprint
import jp.co.soramitsu.iroha2.codegen.blueprint.StructBlueprint
import jp.co.soramitsu.iroha2.codegen.blueprint.TupleStructBlueprint
import jp.co.soramitsu.iroha2.parse.Types
import jp.co.soramitsu.iroha2.type.EnumType
import jp.co.soramitsu.iroha2.type.IterableType
import jp.co.soramitsu.iroha2.type.MapType
import jp.co.soramitsu.iroha2.type.StructType
import jp.co.soramitsu.iroha2.type.TupleStructType
import java.nio.file.Path

object GeneratorEntryPoint {
    @OptIn(ExperimentalUnsignedTypes::class)
    fun generate(types: Types, outputPath: Path) {
        types.values.mapNotNull {
            when (it) {
                is StructType -> StructBlueprint(it)
                is EnumType -> EnumBlueprint(it)
                is TupleStructType -> TupleStructBlueprint(it)
                else -> null
            }
        }.forEach { type ->
            val typeSpec = when (type) {
                is StructBlueprint -> StructGenerator.generate(type)
                is EnumBlueprint -> EnumGenerator.generate(type)
                is TupleStructBlueprint -> TupleStructGenerator.generate(type)
                else -> throw RuntimeException("Unexpected blueprint type: ${type::class}")
            }
            val builder = FileSpec.builder(type.packageName, type.className)
                .addType(typeSpec)
                .addComment("\nAuto-generated file. DO NOT EDIT!\n")

            val isSortedMap = type.properties
                .map { it.original as? MapType }
                .any { it?.sortedByKey == true }
            val isSortedVec = type.properties
                .map { it.original as? IterableType }
                .any { it?.sorted == true }
            if (isSortedMap || isSortedVec) {
                builder.addImport("jp.co.soramitsu.iroha2", "comparator")
            }
            builder.build().writeTo(outputPath)
        }
    }
}
