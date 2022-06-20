package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.crypto.signature.SignatureOf
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import jp.co.soramitsu.iroha2.generated.datamodel.role.RoleId
import kotlin.reflect.KClass

@JvmName("StringComparator")
fun String.Companion.comparator() = compareBy<String> { it }

@JvmName("NameComparator")
fun Name.Companion.comparator() = compareBy<Name> { it.string }

@JvmName("AccountIdComparator")
fun AccountId.Companion.comparator() = compareBy<AccountId> { it.name.string }
    .thenBy { it.domainId.name.string }

@JvmName("AssetDefinitionIdComparator")
fun AssetDefinitionId.Companion.comparator() = compareBy<AssetDefinitionId> { it.name.string }
    .thenBy { it.domainId.name.string }

@JvmName("AssetIdComparator")
fun AssetId.Companion.comparator() = Comparator<AssetId> { o1, o2 ->
    AssetDefinitionId.comparator().compare(
        o1.definitionId, o2.definitionId
    )
}.thenComparator { o1, o2 ->
    AccountId.comparator().compare(o1.accountId, o2.accountId)
}

@JvmName("RoleIdComparator")
fun RoleId.Companion.comparator() = compareBy<RoleId> { it.name.string }

@JvmName("PublicKeyComparator")
fun PublicKey.Companion.comparator() = Comparator<PublicKey> { o1, o2 ->
    ByteArray::class.comparator().compare(o1.payload, o2.payload)
}

@JvmName("SignatureOfComparator")
fun SignatureOf.Companion.comparator() = Comparator<SignatureOf<*>> { o1, o2 ->
    PublicKey.comparator().compare(
        o1.signature.publicKey, o2.signature.publicKey
    )
}.thenComparator { o1, o2 ->
    ByteArray::class.comparator().compare(
        o1.signature.payload, o2.signature.payload
    )
}

@JvmName("PermissionTokenComparator")
fun PermissionToken.Companion.comparator() = compareBy<PermissionToken> {
    it.name.string
}.thenComparator { o1, o2 ->
    val keys1 = o1.params.map { it.key.string }
    val keys2 = o2.params.map { it.key.string }

    keys1.forEachIndexed { index, k1 ->
        val result = keys2.getOrNull(index)
            ?.let { k2 -> k1.compareTo(k2) }
            ?: 1
        if (result != 0) return@thenComparator result
    }

    keys1.size.compareTo(keys2.size)
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

class ComparisonException(message: String) : RuntimeException(message)
