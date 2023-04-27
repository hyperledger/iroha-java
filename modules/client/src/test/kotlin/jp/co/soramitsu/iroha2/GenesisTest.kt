package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.query.QueryBuilder
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.IrohaTest
import jp.co.soramitsu.iroha2.testengine.WithIroha
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class GenesisTest : IrohaTest<Iroha2Client>(
    account = ALICE_ACCOUNT_ID,
    keyPair = ALICE_KEYPAIR
) {
    companion object {
        private val ALICE_KEYPAIR = keyPairFromHex(
            "cc25624d62896d3a0bfd8940f928dc2abf27cc57cefeb442aa96d9081aae58a1",
            "3bac34cda9e3763fa069c1198312d1ec73b53023b8180c822ac355435edc4a24"
        )
    }

    @Test
    @WithIroha(source = "src/test/resources/genesis.json")
    fun `register asset instruction committed`(): Unit = runBlocking {
        QueryBuilder.findAllAccounts()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { accounts -> assert(accounts.any { it.id == ALICE_ACCOUNT_ID }) }
            .also { accounts -> assert(accounts.any { it.id == BOB_ACCOUNT_ID }) }
    }
}
