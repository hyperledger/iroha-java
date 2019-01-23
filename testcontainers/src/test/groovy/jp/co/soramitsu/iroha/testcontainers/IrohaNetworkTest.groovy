package jp.co.soramitsu.iroha.testcontainers

import io.reactivex.observers.TestObserver
import jp.co.soramitsu.iroha.java.Transaction
import jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder
import jp.co.soramitsu.iroha.testcontainers.network.IrohaNetwork
import spock.lang.Specification

class IrohaNetworkTest extends Specification {

    def "network starts and stops without troubles"() {
        given: "network of 4 peers"
        def kp = GenesisBlockBuilder.defaultKeyPair

        IrohaNetwork network = new IrohaNetwork(4)
                .addDefaultTransaction()

        when: "network starts"
        network.start()

        then: "all containers are running"
        network.getPeers()
                .stream()
                .map({ p -> p.getContainer() })
                .allMatch({ c ->
            def ir = c.getIrohaDockerContainer()
            def pg = c.getPostgresDockerContainer()

            return ir.isRunning() && pg.isRunning()
        })

        when: "valid tx is created"
        def tx = Transaction.builder("bogdan@test")
                .setAccountDetail("bogdan@test", "key", "value")
                .sign(kp)
                .build()

        and: "sent to first peer"
        def api = network.getApis().get(0)

        def to = new TestObserver()
        api.transaction(tx).blockingSubscribe(to)

        then: "tx is committed"
        to.assertSubscribed()
        to.assertNoTimeout()
        to.assertNoErrors()
        to.assertComplete()

        and: "all containers are still alive"
        network.getPeers()
                .stream()
                .map({ p -> p.getContainer() })
                .allMatch({ c ->
            def ir = c.getIrohaDockerContainer()
            def pg = c.getPostgresDockerContainer()

            return ir.isRunning() && pg.isRunning()
        })

        when: "network stops"
        network.stop()

        then: "all containers are not running"
        network.getPeers()
                .stream()
                .map({ p -> p.getContainer() })
                .allMatch({ c ->
            def ir = c.getIrohaDockerContainer()
            def pg = c.getPostgresDockerContainer()

            return !ir.isRunning() && !pg.isRunning()
        })
    }
}
