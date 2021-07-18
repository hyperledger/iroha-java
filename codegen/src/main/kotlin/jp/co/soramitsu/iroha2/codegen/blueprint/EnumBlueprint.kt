package jp.co.soramitsu.iroha2.codegen.blueprint

import jp.co.soramitsu.iroha2.type.EnumType

class EnumBlueprint(type: EnumType) : TypeBasedBlueprint<EnumType>(type) {
    val variants = resolveVariants()

    private fun resolveVariants(): List<EnumVariantBlueprint> {
        return source.variants.map {
            EnumVariantBlueprint(it.discriminant, this, it)
        }
    }

//    private fun resolveVariantInnerType(variant: EnumType.Variant): Property? {
//        return variant.type?.requireValue()?.let {
//            return Property(
//                resolvePropName(variant.name).replaceFirstChar(Char::lowercase),
//                resolveKotlinType(it),
//                it
//            )
//        }
//    }
}
