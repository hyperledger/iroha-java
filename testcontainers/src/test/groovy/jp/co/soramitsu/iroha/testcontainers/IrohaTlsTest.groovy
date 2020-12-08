package jp.co.soramitsu.iroha.testcontainers

import jp.co.soramitsu.iroha.java.IrohaAPI
import jp.co.soramitsu.iroha.java.QueryAPI
import jp.co.soramitsu.iroha.java.Transaction
import jp.co.soramitsu.iroha.java.debug.Account
import jp.co.soramitsu.iroha.java.debug.TestTransactionStatusObserver
import jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder
import jp.co.soramitsu.iroha.testcontainers.detail.IrohaConfig
import jp.co.soramitsu.iroha.testcontainers.detail.ToriiTlsConfig
import jp.co.soramitsu.iroha.testcontainers.detail.Verbosity
import org.testcontainers.shaded.org.apache.commons.io.IOUtils
import spock.lang.Specification
import spock.lang.Unroll

class IrohaTlsTest extends Specification {

    static def serverSertificate = IrohaTlsTest.class.classLoader.getResource("server.crt").withInputStream { s ->
        IOUtils.toString(s, "UTF-8")
    }

    static def serverKey = IrohaTlsTest.class.classLoader.getResource("server.key").withInputStream { s ->
        IOUtils.toString(s, "UTF-8")
    }

    static def getGenesisBlock() {
        return new GenesisBlockBuilder()
                .addDefaultTransaction()
                .build()
    }

    static IrohaConfig irohaConfig = IrohaConfig.builder()
            .torii_tls_params(new ToriiTlsConfig())
            .build();

    static peerConfig = PeerConfig.builder()
            .irohaConfig(irohaConfig)
            .genesisBlock(getGenesisBlock())
            .build()
            .withServerCertificate(serverSertificate)
            .withServerKey(serverKey)

    static def iroha = new IrohaContainer()
            .withPeerConfig(peerConfig)
            .withVerbosity(Verbosity.TRACE)
    static IrohaAPI api

    static Account defaultAccount = new Account(GenesisBlockBuilder.defaultAccountId, GenesisBlockBuilder.defaultKeyPair)

    def setupSpec() {
        iroha.start()
        api = iroha.getSecureApi()
    }

    def cleanupSpec() {
        iroha.stop()
    }

    @Unroll
    def "run Iroha with secure TLS connection"() {
        given:
        def qapi = new QueryAPI(api, defaultAccount)
        def key = "key"
        def value = "value"

        when:
        def tx = Transaction.builder(defaultAccount.getId())
                .setAccountDetail(defaultAccount.getId(), key, value)
                .sign(defaultAccount.keyPair)
                .build()

        def transaction_observer = new TestTransactionStatusObserver()
        api.transaction(tx).blockingSubscribe(transaction_observer)

        def actual_value = qapi.getAccountDetails(defaultAccount.getId(), defaultAccount.getId(), key, 1).getDetail()

        then:
        transaction_observer.assertComplete()
        actual_value == "{ \"${defaultAccount.getId()}\" : { \"$key\" : \"$value\" } }"
    }
}
