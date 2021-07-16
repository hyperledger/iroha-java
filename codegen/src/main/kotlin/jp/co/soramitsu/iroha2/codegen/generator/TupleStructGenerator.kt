package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.TypeSpec
import jp.co.soramitsu.iroha2.codegen.TupleStructBlueprint

object TupleStructGenerator : AbstractGenerator<TupleStructBlueprint>() {
    override fun implKDoc(
        blueprint: TupleStructBlueprint,
        clazz: TypeSpec.Builder
    ) {
        super.implKDoc(blueprint, clazz)
        //todo fix generic names
        clazz.addKdoc("\n\nGenerated from '${blueprint.type.name}' tuple structure")
    }
}
