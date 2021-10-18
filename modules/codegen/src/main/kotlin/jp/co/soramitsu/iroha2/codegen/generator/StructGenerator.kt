package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.TypeSpec
import jp.co.soramitsu.iroha2.GsonSerializable
import jp.co.soramitsu.iroha2.codegen.blueprint.StructBlueprint

object StructGenerator : AbstractGenerator<StructBlueprint>() {

    private val manuallySerializableTypes = listOf("EvaluatesTo", "Metadata")

    override fun implKDoc(blueprint: StructBlueprint, clazz: TypeSpec.Builder) {
        super.implKDoc(blueprint, clazz)
        clazz.addKdoc("\n\nGenerated from '${blueprint.source.name}' regular structure")
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun implSuperClasses(blueprint: StructBlueprint, clazz: TypeSpec.Builder) {
        super.implSuperClasses(blueprint, clazz)
        if (blueprint.className in manuallySerializableTypes) {
            clazz.addSuperinterface(GsonSerializable::class)
        }
    }
}
