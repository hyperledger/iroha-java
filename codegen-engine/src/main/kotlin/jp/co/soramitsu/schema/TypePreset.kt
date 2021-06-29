package jp.co.soramitsu.schema

import jp.co.soramitsu.schema.definitions.types.Type
import jp.co.soramitsu.schema.definitions.types.TypeReference

typealias TypePreset = Map<String, TypeReference>

typealias TypePresetBuilder = MutableMap<String, TypeReference>

fun TypePreset.newBuilder(): TypePresetBuilder = toMutableMap()

fun TypePresetBuilder.type(type: Type<*>) {
    val currentRef = getOrCreate(type.name)

    currentRef.value = type
}

fun TypePresetBuilder.getOrCreate(definition: String) = getOrPut(definition) { TypeReference(null) }

fun TypePresetBuilder.create(definition: String): TypeReference =
    TypeReference(null).also { put(definition, it) }

fun createTypePresetBuilder(): TypePresetBuilder = mutableMapOf()

fun typePreset(builder: TypePresetBuilder.() -> Unit): TypePreset {
    return createTypePresetBuilder().apply(builder)
}
