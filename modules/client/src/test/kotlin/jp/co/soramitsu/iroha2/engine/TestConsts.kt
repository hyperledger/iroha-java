package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

const val ALICE_ACCOUNT_NAME = "alice"
const val DEFAULT_DOMAIN_NAME = "wonderland"
val ALICE_KEYPAIR = generateKeyPair()
val ALICE_ACCOUNT_ID = AccountId(ALICE_ACCOUNT_NAME, DEFAULT_DOMAIN_NAME)
val DEFAULT_ASSET_DEFINITION_ID = DefinitionId("xor", DEFAULT_DOMAIN_NAME)
val DEFAULT_ASSET_ID = AssetId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID)
