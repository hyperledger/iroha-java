package jp.co.soramitsu.iroha.java.query

import iroha.protocol.Queries
import jp.co.soramitsu.iroha.java.*
import jp.co.soramitsu.iroha.java.debug.Account
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer
import jp.co.soramitsu.iroha.testcontainers.PeerConfig
import jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder
import spock.lang.Specification
import spock.lang.Unroll

import static jp.co.soramitsu.iroha.java.Transaction.builder
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultKeyPair

class GetAccountTransactions extends Specification {

    static final def defaultAccount = "test"
    static final def defaultDomain = "test"
    static final def defaultAccountId = String.format("%s@%s", defaultAccount, defaultDomain)
    static Account A = Account.create("a@test")
    static Date now = new Date()
    static tx1 = setDetail(A, "key1", "Avalue1", now.getTime() + 1)
    static tx2 = setDetail(A, "key2", "Avalue2", now.getTime() + 2)
    static tx3 = setDetail(A, "key3", "Avalue3", now.getTime() + 1)
    static tx4 = setDetail(A, "key4", "Avalue4", now.getTime() + 2)

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

    static def createAccount(Account a) {
        return builder(defaultAccountId)
                .createAccount(a.id, a.keyPair.public)
                .sign(defaultKeyPair)
                .build()
    }

    static def setDetail(Account account, String key, String value, Long createdTime) {
        return builder(account.id)
                .setAccountDetail(account.id, key, value)
                .setCreatedTime(createdTime)
                .sign(account.keyPair)
                .build()
    }

    static def getGenesisBlock() {
        return new GenesisBlockBuilder()
                .addDefaultTransaction()
                .addTransaction(createAccount(A))
                .addTransaction(tx1)
                .addTransaction(tx2)
                .addTransaction(tx3)
                .addTransaction(tx4)
                .build()
    }

    @Unroll
    def "getAccountTransactions"() {
        given:
        def qapi = new QueryAPI(api, A)

        when: "get account transactions without ordering returns txs in order of creation (tx1, tx2, tx3, tx4)"
        def response = qapi.getAccountTransactions(A.id, 100)

        then:
        response.transactionsCount == 4
        response.transactionsList.get(0) == tx1
        response.transactionsList.get(1) == tx2
        response.transactionsList.get(2) == tx3
        response.transactionsList.get(3) == tx4

        when: "get account transactions without empty ordering returns txs in order of creation (tx1, tx2, tx3, tx4)"
        QueryBuilder.Ordering ordering = new QueryBuilder.Ordering()
        response = qapi.getAccountTransactions(A.id, 100, ordering)

        then:
        response.transactionsCount == 4
        response.transactionsList.get(0) == tx1
        response.transactionsList.get(1) == tx2
        response.transactionsList.get(2) == tx3
        response.transactionsList.get(3) == tx4

        when: "get account transactions with time ordering returns txs ordered by time (tx1, tx3, tx2, tx4)"
        ordering = new QueryBuilder.Ordering()
        ordering.addFieldOrdering(Queries.Field.kCreatedTime, Queries.Direction.kAscending)
        response = qapi.getAccountTransactions(A.id, 100, ordering)

        then:
        response.transactionsCount == 4
        response.transactionsList.get(0) == tx1
        response.transactionsList.get(1) == tx3
        response.transactionsList.get(2) == tx2
        response.transactionsList.get(3) == tx4

        when: "get account transactions with descending time ordering returns txs ordered by time (tx2, tx4, tx1, tx3)"
        ordering = new QueryBuilder.Ordering()
        ordering.addFieldOrdering(Queries.Field.kCreatedTime, Queries.Direction.kDescending)
        response = qapi.getAccountTransactions(A.id, 100, ordering)

        then:
        response.transactionsCount == 4
        response.transactionsList.get(0) == tx2
        response.transactionsList.get(1) == tx4
        response.transactionsList.get(2) == tx1
        response.transactionsList.get(3) == tx3
    }
}
