package jp.co.soramitsu.iroha.java.subscription

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import iroha.protocol.Endpoint.ToriiResponse
import iroha.protocol.Endpoint.TxStatus
import jp.co.soramitsu.iroha.java.IrohaAPI
import jp.co.soramitsu.iroha.java.timeout.TimeoutStrategy
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
        TimeoutStrategy timeoutStrategy = Mock(TimeoutStrategy) {
            nextTimeout() >> {
                println("called nextTimeout()")
                throw new RuntimeException("TEST FAILED: nextTimeout should have never been called")
            }
        }

        SubscriptionStrategy strategy = new WaitForTerminalStatus(timeoutStrategy)

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
                .subscribe(obs)

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
        TimeoutStrategy timeoutStrategy = Mock(TimeoutStrategy) {
            nextTimeout() >> {
                println("called nextTimeout()")
                throw new RuntimeException("TEST FAILED: nextTimeout should have never been called")
            }
        }

        SubscriptionStrategy strategy = new WaitForTerminalStatus(timeoutStrategy)

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

        println(obs.toString())

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

    def "client gracefully handles and re-subscribes after onError received"() {
        given:
        final int ERRORS = 5

        TimeoutStrategy timeoutStrategy = Mock(TimeoutStrategy) {
            // should be called 5 times
            ERRORS * nextTimeout() >> {
                println("called nextTimeout()")
                return true
            }
        }

        SubscriptionStrategy strategy = new WaitForTerminalStatus(timeoutStrategy)
                .doOnError({ t ->
            println("[Client] received onError from Iroha: ${t}")
        })
                .doOnComplete({ ->
            println("[Client] received onComplete from Iroha")
        })

        def throwable = new RuntimeException("<<nasty error>>")
        def responses = makeResponseList([
                TxStatus.ENOUGH_SIGNATURES_COLLECTED,
                TxStatus.STATELESS_VALIDATION_SUCCESS,
                TxStatus.STATEFUL_VALIDATION_SUCCESS,
                TxStatus.COMMITTED
        ])

        IrohaAPI api = Mock(IrohaAPI) {
            // first ERRORS subscriptions, iroha returns error
            ERRORS * txStatus(_) >> {
                // first 5 subscriptions client gets onError
                return Observable.create({ o ->
                    o.onError(throwable)
                })
            }

            // next subscription client gets few non-terminal statuses, then terminal status
            1 * txStatus(_) >> {
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
                .subscribe(obs)

        then:
        obs.assertSubscribed()
        obs.assertNoErrors()
        obs.assertComplete()
        obs.assertNoTimeout()
        obs.assertValueCount(responses.size())
        obs.assertValueSequence(responses)
    }

    def "client resubscribes after onComplete, when no terminal status received"() {
        given:

        TimeoutStrategy timeoutStrategy = Mock(TimeoutStrategy) {
            nextTimeout() >> {
                println("called nextTimeout()")
                return true
            }
        }

        SubscriptionStrategy strategy = new WaitForTerminalStatus(timeoutStrategy)
                .doOnError({ t ->
            println("[Client] received onError from Iroha: ${t}")
        })
                .doOnComplete({ ->
            println("[Client] received onComplete from Iroha")
        })

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
