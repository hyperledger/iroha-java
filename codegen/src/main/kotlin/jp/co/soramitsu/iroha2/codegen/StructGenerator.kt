package jp.co.soramitsu.iroha2.codegen

import com.squareup.kotlinpoet.*

object StructGenerator : AbstractGenerator<StructBlueprint>() {
    override fun implKDoc(blueprint: StructBlueprint, clazz: TypeSpec.Builder): TypeSpec.Builder {
        return super.implKDoc(blueprint, clazz)
            .addKdoc("\n\nGenerated from '${blueprint.type.name}' regular structure")
    }


}
