package jp.co.soramitsu.iroha2.type

import jp.co.soramitsu.iroha2.parse.TypeNest

abstract class WrapperType(override val name: String, open val innerType: TypeNest) : Type(name) {
    override fun notResolvedTypes() = if (innerType.value == null) {
        setOf(innerType.name)
    } else {
        setOf()
    }
}

abstract class IterableType(
    override val name: String,
    override val innerType: TypeNest,
    open var sorted: Boolean
) : WrapperType(name, innerType)

data class OptionType(
    override val name: String,
    override val innerType: TypeNest
) : WrapperType(name, innerType)

data class VecType(
    override val name: String,
    override val innerType: TypeNest,
    override var sorted: Boolean = false
) : IterableType(name, innerType, sorted)

data class SetType(
    override val name: String,
    override val innerType: TypeNest,
    override var sorted: Boolean = false
) : IterableType(name, innerType, sorted)

data class ArrayType(
    override val name: String,
    override val innerType: TypeNest,
    val size: Int,
    override var sorted: Boolean = false
) : IterableType(name, innerType, sorted)

data class CompactType(
    override val name: String,
    override val innerType: TypeNest
) : WrapperType(name, innerType)

data class FixedPointType(
    override val name: String,
    override val innerType: TypeNest,
    val decimalPlaces: Int
) : WrapperType(name, innerType)
