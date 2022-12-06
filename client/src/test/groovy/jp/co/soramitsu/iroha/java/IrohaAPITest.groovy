package jp.co.soramitsu.iroha.java

import jp.co.soramitsu.iroha.java.debug.TestTransactionStatusObserver
import jp.co.soramitsu.iroha.java.subscription.SubscriptionStrategy
import jp.co.soramitsu.iroha.java.subscription.WaitForTerminalStatus
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer
import spock.lang.Specification

import java.util.stream.Collectors
import java.util.stream.IntStream

import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.*

class IrohaAPITest extends Specification {

    static private IrohaContainer iroha = new IrohaContainer()

    def setupSpec() {
        iroha.start()
    }

    def cleanupSpec() {
        iroha.stop()
    }

    def "valid transaction is accepted"() {
        given:
        def api = iroha.getApi()

        when: "send valid transaction"
        def valid = Transaction.builder(defaultAccountId, FieldValidator.defaultConfig)
                .createAccount("zzpppzp", defaultDomainName, defaultKeyPair.getPublic())
                .sign(defaultKeyPair)
                .build()

        def sub = new TestTransactionStatusObserver()
        api.transaction(valid).blockingSubscribe(sub)

        then:
        noExceptionThrown()
        sub.assertNTransactionsSent(1)
        sub.assertAllTransactionsCommitted()
        sub.assertNoTransactionFailed()
    }

    def "when sending stateless invalid tx, error is reported"() {
        given:
        def api = iroha.getApi()

        when: "send stateless invalid transaction"
        // invalid account name in create account
        def statelessInvalid = Transaction.builder(defaultAccountId, FieldValidator.defaultConfig)
                .disableValidation()
                .createAccount("...", defaultDomainName, defaultKeyPair.getPublic())
                .sign(defaultKeyPair)
                .build()

        def sub = new TestTransactionStatusObserver()
        api.transaction(statelessInvalid).blockingSubscribe(sub)

        then:
        noExceptionThrown()
        sub.assertComplete()
        sub.assertNTransactionsSent(1)
        sub.assertAllTransactionsFailed()
        sub.assertNoTransactionCommitted()
    }

    def "when sending stateful invalid tx, error is reported"() {
        given:
        def api = iroha.getApi()

        when: "send stateful invalid transaction"
        // unknown creator
        def statefulInvalid = Transaction.builder("random@account", FieldValidator.defaultConfig)
                .createAccount("x", defaultDomainName, defaultKeyPair.getPublic())
                .sign(defaultKeyPair)
                .build()

        def sub = new TestTransactionStatusObserver()
        api.transaction(statefulInvalid).blockingSubscribe(sub)

        then:
        noExceptionThrown()
        sub.assertNTransactionsSent(1)
        sub.assertAllTransactionsRejected()
        sub.assertNoTransactionCommitted()
    }

    def "send transaction list"() {
        given:
        def api = iroha.getApi()
        def txs = IntStream.range(0, 100)
                .boxed()
                .map(String.&valueOf)
                .map(
                { String name ->
                    return Transaction.builder(defaultAccountId, FieldValidator.defaultConfig)
                            .createAccount(name, defaultDomainName, defaultKeyPair.getPublic())
                            .sign(defaultKeyPair)
                            .build()
                })
                .collect(Collectors.toList())

        when:
        api.transactionListSync(txs)

        then:
        txs.stream()
                .map(Utils.&hash)
                .map(
                { byte[] h ->
                    boolean onCommitted = false

                    def obs = TransactionStatusObserver.builder()
                            .onTransactionCommitted({ z -> onCommitted = true })
                            .build()

                    SubscriptionStrategy waiter = new WaitForTerminalStatus()
                    waiter.subscribe(api, h).blockingSubscribe(obs)

                    return onCommitted
                })
                .allMatch({ p -> p })
    }
}
