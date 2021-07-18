package jp.co.soramitsu.iroha2.codegen.blueprint

import jp.co.soramitsu.iroha2.codegen.Blueprint
import jp.co.soramitsu.iroha2.codegen.Property
import jp.co.soramitsu.iroha2.type.EnumType

/**
 * Blueprint of the enum variant
 */
class EnumVariantBlueprint(val discriminant: Int, val parentBlueprint: EnumBlueprint, source: EnumType.Variant) : Blueprint<EnumType.Variant>(source) {
    override val className = defineClassName(source.name)
    override val packageName = "${parentBlueprint.packageName}.${parentBlueprint.className}"
    override val properties = resolveProperties(source)

    override fun resolveProperties(variant: EnumType.Variant): List<Property> {
        return variant.type?.requireValue()
            ?.let { listOf(Property(defineClassName(it.name).replaceFirstChar(Char::lowercase), resolveKotlinType(it), it)) }
            ?: listOf()
    }
}
