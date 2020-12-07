package jp.co.soramitsu.iroha.java


import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3
import jp.co.soramitsu.iroha.java.debug.TestTransactionStatusObserver
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer
import jp.co.soramitsu.iroha.testcontainers.PeerConfig
import jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder
import jp.co.soramitsu.iroha.testcontainers.detail.IrohaConfig
import spock.lang.Specification

import java.util.concurrent.atomic.AtomicInteger

import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultDomainName

class MstTest extends Specification {

    static def crypto = new Ed25519Sha3()

    static def keyPairA = crypto.generateKeypair()
    static def keyPairB = crypto.generateKeypair()
    static def mstAccountId = "mst@test"

    static def getPeerConfig() {
        return PeerConfig.builder()
                .irohaConfig(getIrohaConfig())
                .genesisBlock(getGenesisBlock())
                .build()
    }

    static def getGenesisBlock() {
        return new GenesisBlockBuilder()
                .addDefaultTransaction()
                .addTransaction(
                Transaction.builder(null)
                        .createAccount(mstAccountId, keyPairA.getPublic())
                        .addSignatory(mstAccountId, keyPairB.getPublic())
                        .setAccountQuorum(mstAccountId, 2)
                        .build()
                        .build())
                .build()
    }

    static def getIrohaConfig() {
        return IrohaConfig.builder()
                .mst_enable(true)
                .build()
    }

    static AtomicInteger counter = new AtomicInteger(0)

    def tx() {
        int i = counter.getAndIncrement()
        return Transaction.builder(mstAccountId)
                .createAsset("usd${i}", defaultDomainName, 2)
                .build()
    }

    static IrohaAPI api
    static def iroha = new IrohaContainer()
            .withPeerConfig(getPeerConfig())


    def setupSpec() {
        iroha.start()
        api = iroha.getApi()
    }

    def cleanupSpec() {
        iroha.stop()
    }

    def "two signers, same process"() {
        def obs = new TestTransactionStatusObserver()

        when:
        def t1 = tx()
                .sign(keyPairA)
                .sign(keyPairB)
                .build()

        api.transaction(t1)
                .doOnNext({e -> println("ON NEXT: ${e}")})
                .doOnError({e -> println("ON ERROR: ${e}")})
                .doOnComplete({e -> println("ON COMPLETE: ${e}")})
                .blockingSubscribe(obs)

        then:
        noExceptionThrown()
        t1.getSignaturesCount() == 2
        obs.assertNTransactionsSent(1)
        obs.assertAllTransactionsCommitted()
        obs.assertNoTransactionFailed()
    }

    def "two signers, only one signed"() {
        given:
        def obs = new TestTransactionStatusObserver()

        when: "A signed transaction"
        def tx1 = tx().sign(keyPairA).build()

        and: "then tx is sent to iroha"
        api.transaction(tx1).blockingSubscribe(obs)

        then:
        obs.assertNTransactionsSent(1)
        obs.assertAllTransactionsRejected()
        obs.assertNoTransactionCommitted()
    }

    def "two signers, different processes"() {
        def obs = new TestTransactionStatusObserver()

        when: "A signed transaction"
        def tx1 = tx().sign(keyPairA).build()

        // send transaction from A to B

        and: "B signed transaction"
        def tx2 = Transaction.parseFrom(tx1).sign(keyPairB).build()

        and: "then tx is sent to iroha"
        api.transaction(tx2)
                .blockingSubscribe(obs)

        then:
        noExceptionThrown()
        tx2.getSignaturesCount() == 2
        obs.assertNTransactionsSent(1)
        obs.assertAllTransactionsCommitted()
        obs.assertNoTransactionFailed()
    }
}
