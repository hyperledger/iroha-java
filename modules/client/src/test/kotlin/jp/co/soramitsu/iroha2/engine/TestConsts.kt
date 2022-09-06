package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId

@JvmField
val DEFAULT_DOMAIN_ID = "wonderland".asDomainId()

@JvmField
val ALICE_ACCOUNT_NAME = "alice".asName()

@JvmField
val ALICE_ACCOUNT_ID = AccountId(ALICE_ACCOUNT_NAME, DEFAULT_DOMAIN_ID)

@JvmField
val ALICE_KEYPAIR = generateKeyPair()

@JvmField
val BOB_ACCOUNT_NAME = "bob".asName()

@JvmField
val BOB_ACCOUNT_ID = AccountId(BOB_ACCOUNT_NAME, DEFAULT_DOMAIN_ID)

@JvmField
val BOB_KEYPAIR = generateKeyPair()

@JvmField
val DEFAULT_ASSET_DEFINITION_ID = AssetDefinitionId("xor".asName(), DEFAULT_DOMAIN_ID)

@JvmField
val DEFAULT_ASSET_ID = AssetId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID)

@JvmField
val TEST_ASSET_DEFINITION_ID = AssetDefinitionId("test".asName(), DEFAULT_DOMAIN_ID)

@JvmField
val TEST_ASSET_DEFINITION_ID2 = AssetDefinitionId("test2".asName(), DEFAULT_DOMAIN_ID)
