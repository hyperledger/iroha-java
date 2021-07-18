package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.*
import jp.co.soramitsu.iroha2.codegen.blueprint.EnumVariantBlueprint

object EnumVariantGenerator : AbstractGenerator<EnumVariantBlueprint>() {

    override fun implKDoc(blueprint: EnumVariantBlueprint, clazz: TypeSpec.Builder) {
        clazz.addKdoc("'${blueprint.className}' variant")
    }

    override fun implFunctions(blueprint: EnumVariantBlueprint, clazz: TypeSpec.Builder) {
        clazz.addFunction(
            FunSpec.builder("discriminant")
                .addModifiers(KModifier.OVERRIDE)
                .returns(Int::class)
                .addCode("return DISCRIMINANT")
                .build()
        )
    }

    override fun implConstructor(blueprint: EnumVariantBlueprint, clazz: TypeSpec.Builder) {
        if (blueprint.properties.isNotEmpty()) {
            val constructorBuilder = FunSpec.constructorBuilder()
            for (property in blueprint.properties) {
                constructorBuilder.addParameter(
                    ParameterSpec.builder(property.name, property.typeName)
                        .build()
                )
                clazz.addProperty(
                    PropertySpec.builder(
                        property.name,
                        property.typeName,
                        KModifier.PRIVATE
                    ).initializer(property.name)
                        .build()
                )
            }
            clazz.primaryConstructor(constructorBuilder.build())
        }
    }

    override fun implCompanions(
        blueprint: EnumVariantBlueprint,
        clazz: TypeSpec.Builder
    ): TypeSpec.Builder {
        return super.implCompanions(blueprint, clazz)
            .addProperty(
                PropertySpec.builder("DISCRIMINANT", Int::class, KModifier.CONST)
                    .initializer("%L", blueprint.discriminant)
                    .build()
            )
    }
}
