package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.crypto.signature.SignatureOf
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import jp.co.soramitsu.iroha2.generated.datamodel.permissions.PermissionToken
import org.bouncycastle.util.encoders.Hex
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId as AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.role.Id as RoleId

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
    AssetDefinitionId.comparator().compare(o1.definitionId, o2.definitionId)
}.thenComparator { o1, o2 ->
    AccountId.comparator().compare(o1.accountId, o2.accountId)
}

@JvmName("RoleIdComparator")
fun RoleId.Companion.comparator() = compareBy<RoleId> { it.name.string }

@JvmName("PublicKeyComparator")
fun PublicKey.Companion.comparator() = compareBy<PublicKey> { Hex.toHexString(it.payload) } // todo по байтам

@JvmName("SignatureOfComparator")
fun SignatureOf.Companion.comparator() = Comparator<SignatureOf<*>> { o1, o2 ->
    PublicKey.comparator().compare(o1.signature.publicKey, o2.signature.publicKey)
}.thenBy {
    Hex.toHexString(it.signature.payload)
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

    return@thenComparator keys1.size.compareTo(keys2.size)
}
