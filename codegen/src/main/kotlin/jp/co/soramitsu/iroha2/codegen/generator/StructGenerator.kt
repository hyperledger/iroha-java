package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.TypeSpec
import jp.co.soramitsu.iroha2.codegen.StructBlueprint

object StructGenerator : AbstractGenerator<StructBlueprint>() {
    override fun implKDoc(blueprint: StructBlueprint, clazz: TypeSpec.Builder) {
        super.implKDoc(blueprint, clazz)
        clazz.addKdoc("\n\nGenerated from '${blueprint.type.name}' regular structure")
    }
}
