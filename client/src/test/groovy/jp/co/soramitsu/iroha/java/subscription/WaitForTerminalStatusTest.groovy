package jp.co.soramitsu.iroha.java.subscription

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import iroha.protocol.Endpoint.ToriiResponse
import iroha.protocol.Endpoint.TxStatus
import jp.co.soramitsu.iroha.java.IrohaAPI
import spock.lang.Specification

import java.util.stream.Collectors

class WaitForTerminalStatusTest extends Specification {

    static def makeResponse(TxStatus status) {
        return ToriiResponse.newBuilder()
                .setTxStatus(status)
                .build()
    }

    static def makeResponseList(List<TxStatus> statuses) {
        return statuses.stream()
                .map(WaitForTerminalStatusTest.&makeResponse)
                .collect(Collectors.toList()) as List<ToriiResponse>
    }

    def "valid transaction status flow"() {
        given:
        SubscriptionStrategy strategy = new WaitForTerminalStatus()

        def responses = makeResponseList([
                TxStatus.ENOUGH_SIGNATURES_COLLECTED,
                TxStatus.STATELESS_VALIDATION_SUCCESS,
                TxStatus.STATEFUL_VALIDATION_SUCCESS,
                TxStatus.COMMITTED
        ])

        IrohaAPI api = Mock(IrohaAPI) {
            txStatus(_) >> {
                return Observable.create({ o ->
                    responses.forEach({ response ->
                        println("[Iroha].onNext(${response.txStatus})")
                        o.onNext(response)
                    })

                    println("[Iroha].onComplete()")
                    o.onComplete()
                })
            }
        }

        TestObserver obs = new TestObserver()

        when:
        strategy.subscribe(api, null)
                .blockingSubscribe(obs)

        then:
        obs.assertSubscribed()
        obs.assertNoErrors()
        obs.assertComplete()
        obs.assertNoTimeout()
        obs.assertValueCount(responses.size())
        obs.assertValueSequence(responses)
    }

    def "after terminal status iroha pushes non-terminal statuses"() {
        given:
        SubscriptionStrategy strategy = new WaitForTerminalStatus()

        def responses = makeResponseList([
                TxStatus.ENOUGH_SIGNATURES_COLLECTED,
                TxStatus.STATELESS_VALIDATION_SUCCESS,
                TxStatus.STATEFUL_VALIDATION_SUCCESS,
                TxStatus.COMMITTED,
                TxStatus.ENOUGH_SIGNATURES_COLLECTED,
                TxStatus.STATELESS_VALIDATION_SUCCESS,
                TxStatus.STATEFUL_VALIDATION_SUCCESS,
                TxStatus.COMMITTED
        ])

        IrohaAPI api = Mock(IrohaAPI) {
            txStatus(_) >> {
                return Observable.create({ o ->
                    responses.forEach({ response ->
                        println("[Iroha].onNext(${response.txStatus})")
                        o.onNext(response)
                    })

                    println("[Iroha].onComplete()")
                    o.onComplete()
                })
            }
        }

        TestObserver obs = new TestObserver()

        when:
        strategy.subscribe(api, null)
                .blockingSubscribe(obs)

        then:
        obs.assertSubscribed()
        obs.assertNoErrors()
        obs.assertComplete()
        obs.assertNoTimeout()
        obs.assertValueCount(4)
        obs.assertValueSequence(makeResponseList([
                TxStatus.ENOUGH_SIGNATURES_COLLECTED,
                TxStatus.STATELESS_VALIDATION_SUCCESS,
                TxStatus.STATEFUL_VALIDATION_SUCCESS,
                TxStatus.COMMITTED
        ]))
    }

    def "client gracefully handles and does not resubscribe after onError received"() {
        given:
        SubscriptionStrategy strategy = new WaitForTerminalStatus()

        def throwable = new RuntimeException("<<nasty error>>")

        IrohaAPI api = Mock(IrohaAPI) {
            txStatus(_) >> {
                return Observable.create({ o ->
                    o.onError(throwable)
                })
            }
        }

        TestObserver obs = new TestObserver()

        when:
        strategy.subscribe(api, null)
                .blockingSubscribe(obs)

        then:
        obs.assertSubscribed()
        obs.assertError(throwable)
        obs.assertNoTimeout()
    }

    def "client resubscribes after onComplete, when no terminal status received"() {
        given:
        SubscriptionStrategy strategy = new WaitForTerminalStatus()

        def responses = makeResponseList([
                TxStatus.ENOUGH_SIGNATURES_COLLECTED,
                TxStatus.STATELESS_VALIDATION_SUCCESS,
                TxStatus.STATEFUL_VALIDATION_SUCCESS,
                TxStatus.COMMITTED
        ])


        def responseIter = responses.iterator()
        IrohaAPI api = Mock(IrohaAPI) {
            txStatus(_) >> {
                // send non-terminal statuses, then close stream
                if (responseIter.hasNext()) {
                    return Observable.create({ o ->
                        def response = responseIter.next()
                        println("[Iroha].onNext(${response.txStatus})")
                        o.onNext(response)
                        println("[Iroha].onComplete()")
                        o.onComplete()
                    })
                }

                throw new RuntimeException("TEST FAILED: subscribed more than len(responses) times")
            }
        }

        TestObserver obs = new TestObserver()

        when:
        strategy.subscribe(api, null)
                .blockingSubscribe(obs)

        then:
        obs.assertSubscribed()
        obs.assertNoErrors()
        obs.assertComplete()
        obs.assertNoTimeout()
        obs.assertValueCount(responses.size())
        obs.assertValueSequence(responses)
    }

}
