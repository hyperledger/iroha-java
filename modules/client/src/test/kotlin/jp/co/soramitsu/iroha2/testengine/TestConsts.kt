package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.keyPairFromHex

const val DEFAULT_DOMAIN = "wonderland"
const val BOB_ACCOUNT = "bob"
const val GENESIS = "genesis"

@JvmField
val DEFAULT_DOMAIN_ID = DEFAULT_DOMAIN.asDomainId()

@JvmField
val ALICE_ACCOUNT_NAME = "alice".asName()

@JvmField
val ALICE_ACCOUNT_ID = AccountId(ALICE_ACCOUNT_NAME, DEFAULT_DOMAIN_ID)

@JvmField
val ALICE_KEYPAIR = generateKeyPair()

val ALICE_MANUAL_KEYPAIR = keyPairFromHex(
    "7233bfc89dcbd68c19fde6ce6158225298ec1131b6a130d1aeb454c1ab5183c0",
    "9ac47abf59b356e0bd7dcbbbb4dec080e302156a48ca907e47cb6aea1d32719e",
)

@JvmField
val BOB_ACCOUNT_NAME = BOB_ACCOUNT.asName()

@JvmField
val BOB_ACCOUNT_ID = AccountId(BOB_ACCOUNT_NAME, DEFAULT_DOMAIN_ID)

@JvmField
val BOB_KEYPAIR = generateKeyPair()

@JvmField
val DEFAULT_ASSET_DEFINITION_ID = AssetDefinitionId("xor".asName(), DEFAULT_DOMAIN_ID)

@JvmField
val DEFAULT_ASSET_ID = AssetId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID)

@JvmField
val XOR_DEFINITION_ID = AssetDefinitionId("xor".asName(), DEFAULT_DOMAIN_ID)

@JvmField
val VAL_DEFINITION_ID = AssetDefinitionId("val".asName(), DEFAULT_DOMAIN_ID)
