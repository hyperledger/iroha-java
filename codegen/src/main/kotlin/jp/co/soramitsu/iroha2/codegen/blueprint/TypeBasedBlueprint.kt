package jp.co.soramitsu.iroha2.codegen.blueprint

import jp.co.soramitsu.iroha2.codegen.Blueprint
import jp.co.soramitsu.iroha2.type.CompositeType

@ExperimentalUnsignedTypes
abstract class TypeBasedBlueprint<T : CompositeType>(source: T) : Blueprint<T>(source){
    override val className: String = defineClassName(source.name)
    override val packageName: String = definePackageName(className, source)
    override val properties = resolveProperties(source)
}
