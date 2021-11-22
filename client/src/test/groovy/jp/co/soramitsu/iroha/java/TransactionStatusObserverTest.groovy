package jp.co.soramitsu.iroha.java

import io.reactivex.Observable
import iroha.protocol.Endpoint.ToriiResponse
import jp.co.soramitsu.iroha.java.debug.TestTransactionStatusObserver
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer
import spock.lang.Specification

import java.util.concurrent.atomic.AtomicBoolean

import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.*

class TransactionStatusObserverTest extends Specification {
    def "onError handler is executed for exceptions thrown from handlers"() {
        given:
        def caught = false
        def msg = "1337"
        def observer = TransactionStatusObserver.builder()
                .onComplete({ throw new Exception(msg) })
                .onError({ e ->
            caught = true
            assert e.message == msg
        }).build()

        when:
        Observable.just(new ToriiResponse())
                .blockingSubscribe(observer)

        then:
        caught
    }

    def "check all statuses"() {
        given:
        def iroha = new IrohaContainer()
        iroha.start()

        def rejected = new AtomicBoolean(false)
        def committed = new AtomicBoolean(false)
        def sent = new AtomicBoolean(false)
        def failed = new AtomicBoolean(false)
        def error = new AtomicBoolean(false)
        def esc = new AtomicBoolean(false)
        def notreceived = new AtomicBoolean(false)
        def badstatus = new AtomicBoolean(false)
        def mstpending = new AtomicBoolean(false)
        def mstexpired = new AtomicBoolean(false)
        def complete = new AtomicBoolean(false)
        def sfvsucc = new AtomicBoolean(false)
        def slvsucc = new AtomicBoolean(false)

        def p = { AtomicBoolean b ->
            { ToriiResponse t ->
                if (t != null)
                    println("${t.txStatus}: ${t.txHash}")
                b.set(true)
            }
        }

        def obs = TransactionStatusObserver.builder()
                .onRejected(p(rejected))
                .onTransactionCommitted(p(committed))
                .onTransactionSent(p(sent))
                .onTransactionFailed(p(failed))
                .onError(p(error))
                .onEnoughSignaturesCollected(p(esc))
                .onNotReceived(p(notreceived))
                .onUnrecognizedStatus(p(badstatus))
                .onMstPending(p(mstpending))
                .onMstExpired(p(mstexpired))
                .onComplete(p(complete))
                .onStatefulValidationSuccess(p(sfvsucc))
                .onStatelessValidationSuccess(p(slvsucc))
                .build()

        def api = iroha.getApi()

        when:
        def tx = Transaction.builder(defaultAccountId)
                .createAccount('z', defaultDomainName, defaultKeyPair.getPublic())
                .sign(defaultKeyPair)
                .build()

        api.transaction(tx)
                .blockingSubscribe(obs)

        then:
        sent.get()
        esc.get()
        // stateless validation success status can be omitted by iroha, so we do not expect it any more
//        slvsucc.get()
        sfvsucc.get()
        committed.get()
        !rejected.get()
        !failed.get()
        !error.get()
        !notreceived.get()
        !badstatus.get()
        !mstpending.get()
        !mstexpired.get()

        cleanup:
        iroha.stop()
    }

    def "check all statuses with test observer"() {
        given:
        def iroha = new IrohaContainer()
        iroha.start()

        def obs = new TestTransactionStatusObserver()

        def api = iroha.getApi()

        when:
        def tx = Transaction.builder(defaultAccountId)
                .createAccount('z', defaultDomainName, defaultKeyPair.getPublic())
                .sign(defaultKeyPair)
                .build()

        api.transaction(tx)
                .blockingSubscribe(obs)

        then:
        obs.assertNTransactionsSent(1)
        obs.assertNoTransactionFailed()
        obs.assertAllTransactionsCommitted()
        obs.assertComplete()

        cleanup:
        iroha.stop()
    }
}
