package jp.co.soramitsu.iroha2.type

abstract class UIntType(name: String) : Type(name)

object U8Type : UIntType("u8")

object U16Type : UIntType("u16")

object U32Type : UIntType("u32")

object U64Type : UIntType("u64")

object U128Type : UIntType("u128")

object U256Type : UIntType("u256")
