package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.PermissionToken
import jp.co.soramitsu.iroha2.generated.PublicKey
import jp.co.soramitsu.iroha2.generated.RoleId
import jp.co.soramitsu.iroha2.generated.SignatureOf
import kotlin.math.min
import kotlin.reflect.KClass

/**
 * Compare strings
 */
@JvmName("StringComparator")
fun String.Companion.comparator() = compareBy<String> { it }

/**
 * Compare Names
 */
@JvmName("NameComparator")
fun Name.Companion.comparator() = compareBy<Name> { it.string }

/**
 * Compare account IDs
 */
@JvmName("AccountIdComparator")
fun AccountId.Companion.comparator() = compareBy<AccountId> { it.name.string }
    .thenBy { it.domainId.name.string }

/**
 * Compare asset definition IDs
 */
@JvmName("AssetDefinitionIdComparator")
fun AssetDefinitionId.Companion.comparator() = compareBy<AssetDefinitionId> { it.name.string }
    .thenBy { it.domainId.name.string }

/**
 * Compare asset IDs
 */
@JvmName("AssetIdComparator")
fun AssetId.Companion.comparator() = Comparator<AssetId> { o1, o2 ->
    AssetDefinitionId.comparator().compare(
        o1.definitionId,
        o2.definitionId,
    )
}.thenComparator { o1, o2 ->
    AccountId.comparator().compare(o1.accountId, o2.accountId)
}

/**
 * Compare role IDs
 */
@JvmName("RoleIdComparator")
fun RoleId.Companion.comparator() = compareBy<RoleId> { it.name.string }

/**
 * Compare public keys
 */
@JvmName("PublicKeyComparator")
fun PublicKey.Companion.comparator() = Comparator<PublicKey> { o1, o2 ->
    ByteArray::class.comparator().compare(o1.payload, o2.payload)
}

/**
 * Compare signatures
 */
@JvmName("SignatureOfComparator")
fun SignatureOf.Companion.comparator() = Comparator<SignatureOf<*>> { o1, o2 ->
    PublicKey.comparator().compare(
        o1.signature.publicKey,
        o2.signature.publicKey,
    )
}.thenComparator { o1, o2 ->
    ByteArray::class.comparator().compare(
        o1.signature.payload,
        o2.signature.payload,
    )
}

/**
 * Compare permission tokens
 */
@JvmName("PermissionTokenComparator")
fun PermissionToken.Companion.comparator() = compareBy<PermissionToken> {
    it.definitionId.string
}.thenComparator { o1, o2 ->
    val a = o1.payload
    val b = o2.payload
    val size = min(a.size, b.size)

    for (i in 0 until size) {
        val compare = a[i].compareTo(b[i])
        if (compare != 0) {
            return@thenComparator compare
        }
    }
    a.size.compareTo(b.size)
}

private fun KClass<ByteArray>.comparator() = Comparator<ByteArray> { o1, o2 ->
    if (o1.size != o2.size) {
        throw ComparisonException("Unexpected payload length")
    }

    o1.forEachIndexed { index, b1 ->
        val result = b1.compareTo(o2[index])
        if (result != 0) return@Comparator result
    }
    return@Comparator 0
}

/**
 * Throw if an exception occurs during comparison
 */
class ComparisonException(message: String) : RuntimeException(message)
