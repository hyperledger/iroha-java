package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

val DEFAULT_DOMAIN_ID = "wonderland".asDomainId()
val ALICE_ACCOUNT_NAME = "alice".asName()
val ALICE_KEYPAIR = generateKeyPair()
val ALICE_ACCOUNT_ID = AccountId(ALICE_ACCOUNT_NAME, DEFAULT_DOMAIN_ID)

val BOB_ACCOUNT_NAME = "bob".asName()
val BOB_KEYPAIR = generateKeyPair()
val BOB_ACCOUNT_ID = AccountId(BOB_ACCOUNT_NAME, DEFAULT_DOMAIN_ID)

val DEFAULT_ASSET_DEFINITION_ID = DefinitionId("xor".asName(), DEFAULT_DOMAIN_ID)
val DEFAULT_ASSET_ID = AssetId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID)
