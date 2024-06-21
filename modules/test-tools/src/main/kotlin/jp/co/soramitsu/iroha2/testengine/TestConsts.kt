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
const val ALICE_ACCOUNT = "ed0120CE7FA46C9DCE7EA4B125E2E36BDB63EA33073E7590AC92816AE1E861B7048B03"
const val BOB_ACCOUNT = "ed012004FF5B81046DDCCF19E2E451C45DFB6F53759D4EB30FA2EFA807284D1CC33016"
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
val BOB_ACCOUNT_ID = AccountId(DEFAULT_DOMAIN_ID, BOB_ACCOUNT_NAME)

@JvmField
val BOB_KEYPAIR = generateKeyPair()

@JvmField
val DEFAULT_ASSET_DEFINITION_ID = AssetDefinitionId(DEFAULT_DOMAIN_ID, "xor".asName())

@JvmField
val DEFAULT_ASSET_ID = AssetId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID)

@JvmField
val XOR_DEFINITION_ID = AssetDefinitionId(DEFAULT_DOMAIN_ID, "xor".asName())

@JvmField
val VAL_DEFINITION_ID = AssetDefinitionId(DEFAULT_DOMAIN_ID, "val".asName())
