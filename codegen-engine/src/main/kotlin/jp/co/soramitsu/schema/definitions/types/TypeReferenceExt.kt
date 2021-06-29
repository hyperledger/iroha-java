package jp.co.soramitsu.schema.definitions.types

import jp.co.soramitsu.schema.definitions.types.composite.Alias

class CyclicAliasingException : Exception()

/**
 * @throws CyclicAliasingException
 */
fun TypeReference.skipAliases(): TypeReference {
    var aliased = this

    val alreadySeen = mutableSetOf(this)

    while (true) {
        val aliasedValue = aliased.value

        if (aliasedValue !is Alias) break

        aliased = aliasedValue.aliasedReference

        if (aliased in alreadySeen) { // self-aliased
            throw CyclicAliasingException()
        } else {
            alreadySeen += aliased
        }
    }

    return aliased
}

fun TypeReference.skipAliasesOrNull(): TypeReference? {
    return runCatching { skipAliases() }.getOrNull()
}

fun TypeReference.resolvedOrNull(): TypeReference? = if (isResolved()) this else null
