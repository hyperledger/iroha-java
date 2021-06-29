package jp.co.soramitsu.schema.definitions.types.generics

import jp.co.soramitsu.schema.definitions.types.TypeReference
import jp.co.soramitsu.schema.definitions.types.composite.DictEnum

class ResultType(ok: TypeReference, err: TypeReference) : DictEnum(
    "Result", listOf(
        Entry(Ok, ok),
        Entry(Err, err)
    )
) {

    companion object {
        const val Ok = "Ok"
        const val Err = "Err"
    }
}
