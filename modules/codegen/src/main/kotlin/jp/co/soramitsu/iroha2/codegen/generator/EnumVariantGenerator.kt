package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
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
                        property.typeName
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

    override fun implSuperClasses(blueprint: EnumVariantBlueprint, clazz: TypeSpec.Builder) {
        super.implSuperClasses(blueprint, clazz)
        clazz.superclass(
            ClassName(
                blueprint.parentBlueprint.packageName, blueprint.parentBlueprint.className
            )
        )
//        clazz.addSuperinterface(Enumeration2::class)
    }
}
