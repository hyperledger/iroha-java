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
    def "getPeer query: issuer=#issuer.id"() {
        given:
        def qapi = new QueryAPI(api, issuer)

        when:
        def response = qapi.getPeers()

        then:
        (response.getPeersCount() == 1) &&
                (response.getPeers(0).getAddress() == expected_address) &&
                (response.getPeers(0).getPeerKey() == expected_key)

        where:
        issuer | expected_address | expected_key
        A      | "0.0.0.0:10001"  | "43eeb17f0bab10dd51ab70983c25200a1742d31b3b7b54c38c34d7b827b26eed"
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
        A      | null      | null   | null   | "{ \"a@test\" : { \"key1\" : \"Avalue1\", \"key2\" : \"Avalue3\", \"key4\" : \"Avalue4\", \"key5\" : \"Avalue5\", \"key6\" : \"Avalue6\" } }"
        B      | null      | null   | null   | "{ \"b@test\" : { \"key1\" : \"Bvalue1\", \"key2\" : \"Bvalue2\", \"key3\" : \"Bvalue3\", \"key4\" : \"Bvalue4\" } }"
        A      | A.id      | null   | null   | "{ \"a@test\" : { \"key1\" : \"Avalue1\", \"key2\" : \"Avalue3\", \"key4\" : \"Avalue4\", \"key5\" : \"Avalue5\", \"key6\" : \"Avalue6\" } }"
        A      | B.id      | null   | null   | "{ \"b@test\" : { \"key1\" : \"Bvalue1\", \"key2\" : \"Bvalue2\", \"key3\" : \"Bvalue3\", \"key4\" : \"Bvalue4\" } }"
        B      | A.id      | null   | null   | "{ \"a@test\" : { \"key1\" : \"Avalue1\", \"key2\" : \"Avalue3\", \"key4\" : \"Avalue4\", \"key5\" : \"Avalue5\", \"key6\" : \"Avalue6\" } }"
        B      | B.id      | null   | null   | "{ \"b@test\" : { \"key1\" : \"Bvalue1\", \"key2\" : \"Bvalue2\", \"key3\" : \"Bvalue3\", \"key4\" : \"Bvalue4\" } }"
        A      | null      | A.id   | null   | "{ \"a@test\" : { \"key1\" : \"Avalue1\", \"key2\" : \"Avalue3\", \"key4\" : \"Avalue4\", \"key5\" : \"Avalue5\", \"key6\" : \"Avalue6\" } }"
        A      | null      | null   | "key2" | "{ \"a@test\" : { \"key2\" : \"Avalue3\" } }"
        B      | null      | null   | "key2" | "{ \"b@test\" : { \"key2\" : \"Bvalue2\" } }"
        A      | B.id      | null   | "key2" | "{ \"b@test\" : { \"key2\" : \"Bvalue2\" } }"
        A      | B.id      | A.id   | "key2" | "{}"
    }

    @Unroll
    def "exception in @Deprecated getAccountDetails"(Account issuer, String accountId, String writer, String key) {
        given:
        def qapi = new QueryAPI(api, issuer)

        when:
        qapi.getAccountDetails(accountId, writer, key)

        then:
        thrown ErrorResponseException

        where:
        issuer | accountId            | writer      | key
        A      | "nonexistent@domain" | null        | null
    }

    def "validation exception in getAccountDetails"(Account issuer, String accountId, String writer, String key, Integer pageSize) {
        given:
        def qapi = new QueryAPI(api, issuer)

        when:
        qapi.getAccountDetails(accountId, writer, key, pageSize)

        then:
        thrown ValidationException

        where:
        issuer | accountId            | writer      | key  | pageSize
        A      | "invalid"            | null        | null | 10
        A      | null                 | null        | null | -10
        A      | null                 | null        | null | 0
    }

    def "exception in getAccountDetails"(Account issuer, String accountId, String writer, String key, Integer pageSize) {
        given:
        def qapi = new QueryAPI(api, issuer)

        when:
        qapi.getAccountDetails(accountId, writer, key, pageSize)

        then:
        thrown ErrorResponseException

        where:
        issuer | accountId            | writer      | key  | pageSize
        A      | "nonexistent@domain" | null        | null | 10
    }

    @Unroll
    def "validation exception in getAccount"(Account issuer, String accountId) {
        given:
        def qapi = new QueryAPI(api, issuer)

        when:
        qapi.getAccount(accountId)

        then:
        thrown ValidationException

        where:
        issuer | accountId
        A      | "invalid"
    }

    @Unroll
    def "exception in getAccount"(Account issuer, String accountId) {
        given:
        def qapi = new QueryAPI(api, issuer)

        when:
        qapi.getAccount(accountId)

        then:
        thrown ErrorResponseException

        where:
        issuer | accountId
        A      | "nonexistent@domain"
    }

}
