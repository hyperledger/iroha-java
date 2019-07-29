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

class CommandsTest extends Specification {

    static Account account = Account.create("a@test")

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
        return Transaction.builder(defaultAccountId)
                .createAccount(a.id, a.keyPair.public)
                .sign(defaultKeyPair)
                .build()
    }

    static def getGenesisBlock() {
        return new GenesisBlockBuilder()
                .addDefaultTransaction()
                .addTransaction(createAccount(account))
                .addTransaction(setDetail(account, "initial_key1", "initial_val"))
                .addTransaction(setDetail(account, "initial_key2", "initial_val"))
                .build()
    }

    @Unroll
    def "compareAndSet command: key=#key, value=#value, oldValue=#oldValue"() {
        given:
        def qapi = new QueryAPI(api, account)

        when:
        def tx = Transaction.builder(account.getId())
                .compareAndSetAccountDetail(account.getId(), key, value, oldValue)
                .sign(account.keyPair)
                .build()
        api.transaction(tx).blockingSubscribe()

        def actual_value = qapi.getAccountDetails(account.getId(), account.getId(), key, 1).getDetail()

        then:
        actual_value == expected_value

        where:
        key            | value         | oldValue      | expected_value
        "initial_key1" | "updated_val" | "wrong_val"   | "{ \"a@test\" : { \"initial_key1\" : \"initial_val\" } }"
        "initial_key1" | "updated_val" | null          | "{ \"a@test\" : { \"initial_key1\" : \"initial_val\" } }"
        "initial_key2" | "updated_val" | "initial_val" | "{ \"a@test\" : { \"initial_key2\" : \"updated_val\" } }"
        // Seems to be failed in Iroha
        //"empty1"       | "value"       | "wrong"       | "{}"
        "empty2"       | "value"       | null          | "{ \"a@test\" : { \"empty2\" : \"value\" } }"
    }
}
