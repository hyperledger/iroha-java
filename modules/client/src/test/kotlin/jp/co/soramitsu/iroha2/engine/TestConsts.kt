package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId

@JvmField
val DEFAULT_DOMAIN_ID = "wonderland".asDomainId()

@JvmField
val ALICE_ACCOUNT_NAME = "alice".asName()

@JvmField
val ALICE_ACCOUNT_ID = AccountId(ALICE_ACCOUNT_NAME, DEFAULT_DOMAIN_ID)

@JvmField
val ALICE_KEYPAIR = generateKeyPair()

// @JvmField
// val ALICE_KEYPAIR = keyPairFromHex(
//    "7233bfc89dcbd68c19fde6ce6158225298ec1131b6a130d1aeb454c1ab5183c0",
//    "9ac47abf59b356e0bd7dcbbbb4dec080e302156a48ca907e47cb6aea1d32719e"
// )

@JvmField
val BOB_ACCOUNT_NAME = "bob".asName()

@JvmField
val BOB_ACCOUNT_ID = AccountId(BOB_ACCOUNT_NAME, DEFAULT_DOMAIN_ID)

@JvmField
val BOB_KEYPAIR = generateKeyPair()

@JvmField
val DEFAULT_ASSET_DEFINITION_ID = DefinitionId("xor".asName(), DEFAULT_DOMAIN_ID)

@JvmField
val DEFAULT_ASSET_ID = AssetId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID)
