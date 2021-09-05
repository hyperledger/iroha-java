package jp.co.soramitsu.iroha2.type

abstract class IntType(name: String) : Type(name)

object I8Type : IntType("i8")

object I16Type : IntType("i16")

object I32Type : IntType("i32")

object I64Type : IntType("i64")

object I128Type : IntType("i128")

object I256Type : IntType("i256")
