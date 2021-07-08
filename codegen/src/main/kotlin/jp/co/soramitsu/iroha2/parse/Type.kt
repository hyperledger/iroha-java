package jp.co.soramitsu.iroha2.parse

//todo docs
class TypeNest(val name: String, var value: Type?) {
    fun requireValue() = value ?: throw IllegalArgumentException("TypeReference is null")
    override fun toString(): String {
        return "TypeNest(name='$name', value=$value)"
    }

}

abstract class Type(val name: String)

object BooleanType : Type("bool")

class MapType(name: String, key: TypeNest, value: TypeNest) : Type(name)

abstract class WrapperType(name: String, innerType: TypeNest) : Type(name)

class OptionType(fullName: String, innerType: TypeNest) : WrapperType(fullName, innerType)

class VecType(fullName: String, innerType: TypeNest) : WrapperType(fullName, innerType)

class ArrayType(fullName: String, innerType: TypeNest, size: UInt) :
    WrapperType(fullName, innerType)

class EnumType(name: String, val variants: List<Variant>) : Type(name) {
    class Variant(val name: String, val discriminant: Int, val type: TypeNest?)
}

class TupleStructType(name: String, val types: List<TypeNest>) : Type(name)

class StructType(name: String, val mapping: LinkedHashMap<String, TypeNest>) : Type(name)

object StringType : Type("String")

class CompactType(name: String, innerType: TypeNest) : Type(name)

abstract class UIntType(name: String) : Type(name)

object U8Type : UIntType("u8")
object U16Type : UIntType("u16")
object U32Type : UIntType("u32")
object U64Type : UIntType("u64")
object U128Type : UIntType("u128")
object U256Type : UIntType("u256")
