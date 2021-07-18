package jp.co.soramitsu.iroha2.codegen.blueprint

import jp.co.soramitsu.iroha2.codegen.Property
import jp.co.soramitsu.iroha2.type.StructType
import java.util.*

class StructBlueprint(type: StructType) : TypeBasedBlueprint<StructType>(type){
    override fun resolveProperties(type: StructType): List<Property> {
        return type.mapping
            .map { (name, ty) ->
                Property(
                    convertToCamelCase(name),
                    resolveKotlinType(ty.requireValue()),
                    ty.requireValue()
                )
            }
    }

}
