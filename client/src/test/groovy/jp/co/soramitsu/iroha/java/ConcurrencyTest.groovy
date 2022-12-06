package jp.co.soramitsu.iroha.java

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.reactivex.internal.observers.LambdaObserver
import iroha.protocol.QryResponses.BlockQueryResponse
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer
import spock.lang.Specification

import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.*

class ConcurrencyTest extends Specification {

    interface TestOutcome {
        void fail(String s)
    }

    static def iroha = new IrohaContainer()
            .withLogger(null)

    static IrohaAPI api

    static ManagedChannel directExecutorChannel

    def setupSpec() {
        iroha.start()
        api = iroha.api

        def t = iroha.getToriiAddress()
        directExecutorChannel = ManagedChannelBuilder.forAddress(t.host, t.port)
                .directExecutor()
                .usePlaintext()
                .build()

        api.setChannelForStreamingQueryStub(directExecutorChannel)
    }

    def cleanupSpec() {
        directExecutorChannel.shutdownNow()
        directExecutorChannel.awaitTermination(30, TimeUnit.SECONDS)
        iroha.stop()
    }

    static def getTx(int n) {
        return Transaction.builder(defaultAccountId, FieldValidator.defaultConfig)
                .createAccount("${n}", defaultDomainName, defaultKeyPair.public)
                .sign(defaultKeyPair)
                .build()
    }

    def subscribeForBlocks() {
        def bq = BlocksQuery.builder(defaultAccountId, 1, FieldValidator.defaultConfig)
                .buildSigned(defaultKeyPair)

        return api.blocksQuery(bq)
    }

    String last = ""

    def getThreadInfo() {
        def t = Thread.currentThread()
        return "name=${t.name} id=${t.id}"
    }

    def checkCurrentThread(TestOutcome test) {
        def t = Thread.currentThread()
        if (last.isEmpty()) {
            last = t.name
            return
        }

        if (last != t.name) {
            test.fail("TEST FAILED: last=${last} != current ${t.name}")
        }
    }

    def "iroha api subscribe blocks works on single thread"() {
        given:
        TestOutcome test = Mock(TestOutcome) {
            0 * fail(_)
        }


        def th1 = Thread.start {
            subscribeForBlocks()
                    .subscribe(new LambdaObserver<BlockQueryResponse>(
                    { BlockQueryResponse b ->
                        def block = b.blockResponse.block.blockV1.payload
                        def height = block.height
                        def time = block.createdTime
                        println("[${getThreadInfo()}] BLOCK height=${height}, time=${time}")
                        checkCurrentThread(test)
                    },
                    { e ->
                        println("[${getThreadInfo()}] ERROR: ${e}")
                    },
                    {
                        println("[${getThreadInfo()}] COMPLETE")
                    },
                    {
                        println("[${getThreadInfo()}] SUBSCRIBED")
                    }
            ))
        }


        when: "send few txes"
        def th2 = Thread.start {
            IntStream.range(0, 100)
                    .parallel()
                    .boxed()
                    .map({ n -> getTx(n) })
                    .map({ tx -> api.transaction(tx) })
                    .forEach({ o -> o.blockingSubscribe() })
        }

        println("[${getThreadInfo()}] MAIN THREAD")

        then:
        th2.join()
        th1.join()
        noExceptionThrown()
    }
}
