package jp.co.soramitsu.iroha2.type

import jp.co.soramitsu.iroha2.parse.TypeNest

abstract class WrapperType(name: String, val innerType: TypeNest) : Type(name) {
    override fun notResolvedTypes() = if (innerType.value == null) {setOf(innerType.name)} else {setOf()}
}

class OptionType(fullName: String, innerType: TypeNest) : WrapperType(fullName, innerType)

class VecType(fullName: String, innerType: TypeNest) : WrapperType(fullName, innerType)

class SetType(fullName: String, innerType: TypeNest) : WrapperType(fullName, innerType)

class ArrayType(fullName: String, innerType: TypeNest, size: UInt) :
    WrapperType(fullName, innerType)

class CompactType(name: String, innerType: TypeNest) : WrapperType(name, innerType)
