package jp.co.soramitsu.iroha.java.routers


import iroha.protocol.TransactionOuterClass
import jp.co.soramitsu.iroha.java.FieldValidator
import jp.co.soramitsu.iroha.java.Transaction
import jp.co.soramitsu.iroha.java.detail.router.Router
import spock.lang.Specification

import static iroha.protocol.Commands.Command.CommandCase.ADD_PEER
import static iroha.protocol.Commands.Command.CommandCase.CREATE_ASSET
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultKeyPair

class RouterTest extends Specification {

    interface Printer {
        void print(Integer s);

        void print(TransactionOuterClass.Transaction tx);
    }

    def "simple singular router"() {
        given:
        def p = Mock(Printer)
        def r = new Router<Integer, Integer>({ i -> i })

        r.use({ a -> a + 1 })
        r.use({ a -> a * 2 })

        r.handle(0, p.&print)
        r.handle(1, p.&print)
        r.handle(2, p.&print)
        r.handleDefault(p.&print)

        when:
        for (int i = 0; i < 5; i++) {
            r.process(i)
        }

        then:
        interaction {
            0 * p.print(0)
            1 * p.print(2)
            1 * p.print(6)
            3 * p.print(_)
        }
    }

    def "TxCmdRouter example"() {
        given:
        // router
        def router = new CmdRouter()

        // we want to log all commands as is, lets use middleware for this
        router.use({ cmd ->
            String s = cmd.toString().replace('\n', ' ')
            println("[Logging middleware]: ${s}")
            return cmd // do not forget to return
        })

        // you can also create modifying middleware
        // it should accept type T and return type T, but different instance

        // introspect commands ADD_PEER and CREATE_ASSET:
        router.handle(ADD_PEER, { cmd ->
            def c = cmd.getAddPeer().getPeer()
            println("  -- Address: ${c.address}, Key: ${c.peerKey}")
        })

        router.handle(CREATE_ASSET, { cmd ->
            def c = cmd.getCreateAsset()
            println("  -- Asset Name: ${c.assetName}, Asset Domain: ${c.domainId}, Precision: ${c.precision}")
        })


        tx.getPayload()
                .getReducedPayload()
                .getCommandsList()
                .forEach(router.&process)

        where:
        tx << [
                Transaction.builder(null, FieldValidator.defaultConfig)
                        .disableValidation()
                        .addPeer("127.0.0.1:123", defaultKeyPair.public)
                        .addPeer("1.1.1.1:11111", defaultKeyPair.public)
                        .createAsset("asset", "domain", 0)
                        .build()
                        .build()
        ]

    }
}
