package jp.co.soramitsu.iroha2.testengine

import jp.co.soramitsu.iroha2.ACCOUNT_ID_DELIMITER
import jp.co.soramitsu.iroha2.asAccountId
import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.AssetId

const val DEFAULT_DOMAIN = "wonderland"
const val ALICE_ACCOUNT = "alice"
const val BOB_ACCOUNT = "bob"
const val GENESIS = "genesis"
const val ALICE_ACCOUNT_ID_VALUE = "$ALICE_ACCOUNT$ACCOUNT_ID_DELIMITER$DEFAULT_DOMAIN"

@JvmField
val DEFAULT_DOMAIN_ID = DEFAULT_DOMAIN.asDomainId()

@JvmField
val ALICE_ACCOUNT_NAME = ALICE_ACCOUNT.asName()

@JvmField
val ALICE_ACCOUNT_ID = ALICE_ACCOUNT_ID_VALUE.asAccountId()

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
