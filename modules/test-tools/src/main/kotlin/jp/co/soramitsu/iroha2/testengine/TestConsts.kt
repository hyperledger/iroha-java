package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId

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
