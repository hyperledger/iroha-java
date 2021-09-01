package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.generated.datamodel.account.Id

const val ALICE_ACCOUNT_NAME = "alice"
const val DEFAULT_DOMAIN_NAME = "wonderland"

val ALICE_ACCOUNT_ID = Id(ALICE_ACCOUNT_NAME, DEFAULT_DOMAIN_NAME)
