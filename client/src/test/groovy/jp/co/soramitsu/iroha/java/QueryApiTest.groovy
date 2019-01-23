package jp.co.soramitsu.iroha.java


import jp.co.soramitsu.iroha.java.debug.Account
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer
import jp.co.soramitsu.iroha.testcontainers.PeerConfig
import jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder
import spock.lang.Specification
import spock.lang.Unroll

import static jp.co.soramitsu.iroha.java.Transaction.builder
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultAccountId
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultKeyPair

class QueryApiTest extends Specification {

    static Account A = Account.create("a@test")
    static Account B = Account.create("b@test")

    static IrohaAPI api
    static def iroha = new IrohaContainer()
            .withPeerConfig(
            PeerConfig.builder()
                    .genesisBlock(getGenesisBlock())
                    .build())

    def setupSpec() {
        iroha.start()
        api = iroha.getApi()
    }

    def cleanupSpec() {
        iroha.stop()
    }

    static def setDetail(Account account, String key, String value) {
        return builder(account.id)
                .setAccountDetail(account.id, key, value)
                .sign(account.keyPair)
                .build()
    }

    static def createAccount(Account a) {
        return builder(defaultAccountId)
                .createAccount(a.id, a.keyPair.public)
                .sign(defaultKeyPair)
                .build()
    }

    static def getGenesisBlock() {
        return new GenesisBlockBuilder()
                .addDefaultTransaction()
                .addTransaction(createAccount(A))
                .addTransaction(createAccount(B))
                .addTransaction(setDetail(A, "key1", "Avalue1"))
                .addTransaction(setDetail(A, "key2", "Avalue2"))
        // overwrite key2 (intentionally)
                .addTransaction(setDetail(A, "key2", "Avalue3"))
                .addTransaction(setDetail(A, "key4", "Avalue4"))
                .addTransaction(setDetail(A, "key5", "Avalue5"))
                .addTransaction(setDetail(A, "key6", "Avalue6"))
                .addTransaction(setDetail(B, "key1", "Bvalue1"))
                .addTransaction(setDetail(B, "key2", "Bvalue2"))
                .addTransaction(setDetail(B, "key3", "Bvalue3"))
                .addTransaction(setDetail(B, "key4", "Bvalue4"))
                .build()
    }

    @Unroll
    def "all getAccountDetail: issuer=#issuer.id, accountId=#accountId, writer=#writer, key=#key"() {
        given:
        def qapi = new QueryAPI(api, issuer)

        when:
        def actual_value = qapi.getAccountDetails(accountId, writer, key)

        then:
        actual_value == expected_value

        where:
        issuer | accountId | writer | key    | expected_value
        A      | null      | null   | null   | "{\"a@test\": {\"key1\": \"Avalue1\", \"key2\": \"Avalue3\", \"key4\": \"Avalue4\", \"key5\": \"Avalue5\", \"key6\": \"Avalue6\"}}"
        B      | null      | null   | null   | "{\"b@test\": {\"key1\": \"Bvalue1\", \"key2\": \"Bvalue2\", \"key3\": \"Bvalue3\", \"key4\": \"Bvalue4\"}}"
        A      | A.id      | null   | null   | "{\"a@test\": {\"key1\": \"Avalue1\", \"key2\": \"Avalue3\", \"key4\": \"Avalue4\", \"key5\": \"Avalue5\", \"key6\": \"Avalue6\"}}"
        A      | B.id      | null   | null   | "{\"b@test\": {\"key1\": \"Bvalue1\", \"key2\": \"Bvalue2\", \"key3\": \"Bvalue3\", \"key4\": \"Bvalue4\"}}"
        B      | A.id      | null   | null   | "{\"a@test\": {\"key1\": \"Avalue1\", \"key2\": \"Avalue3\", \"key4\": \"Avalue4\", \"key5\": \"Avalue5\", \"key6\": \"Avalue6\"}}"
        B      | B.id      | null   | null   | "{\"b@test\": {\"key1\": \"Bvalue1\", \"key2\": \"Bvalue2\", \"key3\": \"Bvalue3\", \"key4\": \"Bvalue4\"}}"
        A      | null      | A.id   | null   | "{\"a@test\" : {\"key1\": \"Avalue1\", \"key2\": \"Avalue3\", \"key4\": \"Avalue4\", \"key5\": \"Avalue5\", \"key6\": \"Avalue6\"}}"
        A      | null      | null   | "key2" | "{ \"a@test\" : {\"key2\" : \"Avalue3\"} }"
        B      | null      | null   | "key2" | "{ \"b@test\" : {\"key2\" : \"Bvalue2\"} }"
        A      | B.id      | null   | "key2" | "{ \"b@test\" : {\"key2\" : \"Bvalue2\"} }"
        A      | B.id      | A.id   | "key2" | "{\"a@test\" : {\"key2\" : null}}"
    }

}
