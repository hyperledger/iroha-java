package jp.co.soramitsu.schema.definitions.types.primitives

import jp.co.soramitsu.schema.definitions.types.Type

abstract class Primitive<I>(name: String) : Type<I>(name) {

    override val isFullyResolved = true
}