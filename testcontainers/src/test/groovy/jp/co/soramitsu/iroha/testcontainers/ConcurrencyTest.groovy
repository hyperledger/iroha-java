package jp.co.soramitsu.iroha.testcontainers

import jp.co.soramitsu.iroha.testcontainers.network.IrohaNetwork
import spock.lang.Specification
import spock.lang.Unroll

class ConcurrencyTest extends Specification {

    def runSinglePeer(int id) {

    }

    @Unroll
    def "#containersTotal containers can work simultaneously"() {
        given:
        def threads = (0..<containersTotal).collect { id ->
            Thread.start {
                new IrohaContainer().withCloseable { iroha ->
                    iroha.start()
                    printf("iroha %d started listening at %s\n", id, iroha.getToriiAddress())
                    Thread.sleep(2000)
                    printf("iroha %d at %s is stopping...\n", id, iroha.getToriiAddress())
                }
            }
        }

        when:
        threads*.join()

        then:
        noExceptionThrown()

        where:
        containersTotal = 3
    }


    @Unroll
    def "#networksTotal networks of #peersPerNetwork peers each can work simultaneously"() {
        given:
        def threads = (0..<networksTotal).collect { id ->
            Thread.start {
                def networkid = String.valueOf(id)
                new IrohaNetwork(peersPerNetwork).withCloseable { nw ->
                    nw.start()
                    printf("network of %d peers with network id %s started listening at %s\n",
                            peersPerNetwork, networkid, nw.getToriiAddresses())
                    Thread.sleep(2000)
                    printf("network of %d peers with network id %s is stopping...",
                            peersPerNetwork, networkid)
                }
            }
        }

        when:
        threads*.join()

        then:
        noExceptionThrown()

        where:
        networksTotal = 2
        peersPerNetwork = 3
    }

}
