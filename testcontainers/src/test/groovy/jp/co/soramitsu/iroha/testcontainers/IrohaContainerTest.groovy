package jp.co.soramitsu.iroha.testcontainers

import com.google.protobuf.util.JsonFormat
import io.reactivex.observers.TestObserver
import iroha.protocol.BlockOuterClass
import jp.co.soramitsu.iroha.java.IrohaAPI
import jp.co.soramitsu.iroha.java.Transaction
import jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder
import jp.co.soramitsu.iroha.testcontainers.detail.IrohaConfig
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class IrohaContainerTest extends Specification {

    IrohaContainer ir = new IrohaContainer()

    def "temp folder is created and files are written"() {
        given:
        def mapper = new ObjectMapper()
        def c = ir.getConf()
        def d = c.getDir()

        when: "create tmp dir and dump files"
        ir.configure()

        then:
        d.exists()
        d.list().toList().toSet() == [
                GenesisBlockBuilder.defaultGenesisBlockName,
                PeerConfig.peerKeypairName + ".pub",
                PeerConfig.peerKeypairName + ".priv",
                IrohaConfig.defaultConfigFileName
        ].toSet()

        when: "parse config.docker"
        File confFile = new File(d, "config.docker")
        IrohaConfig config = mapper.readValue(confFile, IrohaConfig.class)

        then: "configs are equal"
        config == c.getIrohaConfig()

        when: "parse genesis.block"
        File blockFile = new File(d, "genesis.block")
        def builder = BlockOuterClass.Block.newBuilder()
        JsonFormat.parser().merge(blockFile.getText(), builder)

        then: "genesis blocks are equal"
        builder.build() == c.getGenesisBlock()

    }

    def "iroha starts and stops with zero configuration"() {
        when: "start iroha"
        ir.start()

        and: "immediately send transaction"
        def api = new IrohaAPI(ir.getToriiAddress())
        def s = new TestObserver<>()
        api.transaction(Transaction.builder("test@test")
                .createDomain("dom", GenesisBlockBuilder.defaultRoleName)
                .sign(GenesisBlockBuilder.defaultKeyPair)
                .build()
        ).blockingSubscribe(s)

        then: "api is accessible"
        s.assertSubscribed()
        s.assertComplete()
        s.assertNoErrors()
        s.assertNoTimeout()

        and: "iroha is running"
        ir.irohaDockerContainer.isCreated()
        ir.irohaDockerContainer.isRunning()
        ir.postgresDockerContainer.isCreated()
        ir.postgresDockerContainer.isRunning()

        when: "stop iroha"
        ir.stop()

        then: "container is no more accessible"
        !ir.irohaDockerContainer.isCreated()
        !ir.irohaDockerContainer.isRunning()
        !ir.postgresDockerContainer.isCreated()
        !ir.postgresDockerContainer.isRunning()
    }
}
