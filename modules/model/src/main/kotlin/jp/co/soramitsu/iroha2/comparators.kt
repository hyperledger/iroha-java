package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.Name
import org.bouncycastle.util.encoders.Hex
import kotlin.reflect.KClass
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId as AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

@JvmName("StringComparator")
fun KClass<String>.comparator() = compareBy<String> { it }

@JvmName("NameComparator")
fun KClass<Name>.comparator() = compareBy<Name> { it.string }

@JvmName("AccountIdComparator")
fun KClass<AccountId>.comparator() = compareBy<AccountId> { it.name.string }
    .thenBy { it.domainId.name.string }

@JvmName("AssetDefinitionIdComparator")
fun KClass<AssetDefinitionId>.comparator() = compareBy<AssetDefinitionId> { it.name.string }
    .thenBy { it.domainId.name.string }

@JvmName("AssetIdComparator")
fun KClass<AssetId>.comparator() = compareBy<AssetId> { it.definitionId.name.string }
    .thenBy { it.definitionId.domainId.name.string }
    .thenBy { it.accountId.name.string }
    .thenBy { it.accountId.domainId.name.string }

@JvmName("PublicKeyComparator")
fun KClass<PublicKey>.comparator() = compareBy<PublicKey> { Hex.toHexString(it.payload) }
