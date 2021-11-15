package jp.co.soramitsu.iroha.java

import jp.co.soramitsu.iroha.java.crypto.Ed25519Sha2SignatureBuilder
import jp.co.soramitsu.iroha.java.crypto.Ed25519Sha3SignatureBuilder
import jp.co.soramitsu.iroha.java.debug.Account
import jp.co.soramitsu.iroha.java.debug.TestTransactionStatusObserver
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer
import jp.co.soramitsu.iroha.testcontainers.PeerConfig
import jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder
import spock.lang.Specification
import spock.lang.Unroll

import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultAccountId
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultDomainName
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultKeyPair

class Ed25519Sha2Test extends Specification {

    static Account account = Account.createWithEd25519Sha2("a@test")

    static IrohaAPI api
    static def iroha = new IrohaContainer()
            .withIrohaDockerImage(IrohaContainer.defaultIrohaDockerImageWithURSA)
            .withPeerConfig(
                    PeerConfig.builder()
                            .genesisBlock(getGenesisBlock())
                            .build())

    def setupSpec() {
        iroha.start()
        api = iroha.getApi()
    }

    def cleanupSpec() {
        iroha.stop()
    }

    static def createAccount(Account a) {
        return Transaction.builder(defaultAccountId)
                .createAccount("a", defaultDomainName, a.getHexPublicKey())
                .sign(defaultKeyPair)
                .build()
    }

    static def getGenesisBlock() {
        return new GenesisBlockBuilder()
                .addDefaultTransaction()
                .build()
    }

    @Unroll
    def "setAccountDetail and getAccountDetail by Ed25519 crypto"() {
        given:
        def qapi = new QueryAPI(api, account, new Ed25519Sha2SignatureBuilder())

        when: "send tx with Iroha builtin Ed25519/Sha3 with default builder without specifying SignatureBuilder"
        def tx = Transaction.builder(defaultAccountId,)
                .setAccountDetail(defaultAccountId, "key", "value")
                .sign(defaultKeyPair)
                .build()
        def transaction_observer = new TestTransactionStatusObserver()
        api.transaction(tx).blockingSubscribe(transaction_observer)

        then:
        transaction_observer.assertAllTransactionsCommitted()

        when: "create account with Ed25519/Sha2 pubkey"
        tx = Transaction.builder(defaultAccountId, new Ed25519Sha3SignatureBuilder())
                .createAccount("a", defaultDomainName, account.getHexPublicKey())
                .sign(defaultKeyPair)
                .build()
        transaction_observer = new TestTransactionStatusObserver()
        api.transaction(tx).blockingSubscribe(transaction_observer)

        then:
        transaction_observer.assertAllTransactionsCommitted()

        when: "send tx from account with Ed25519/Sha2 pubkey"
        tx = Transaction.builder(account.getId(), new Ed25519Sha2SignatureBuilder())
                .setAccountDetail(account.getId(), "key", "value")
                .sign(account.keyPair)
                .build()
        transaction_observer = new TestTransactionStatusObserver()
        api.transaction(tx).blockingSubscribe(transaction_observer)

        then:
        transaction_observer.assertAllTransactionsCommitted()

        when: "query with Ed25519/Sha3 as default SignatureBuilder"
        def defaultQueryApi = new QueryAPI(api, defaultAccountId, defaultKeyPair)
        def actual_value = defaultQueryApi.getAccountDetails(account.getId(), account.getId(), "key", 1).getDetail()

        then:
        actual_value == "{ \"a@test\" : { \"key\" : \"value\" } }"

        when: "query with Ed25519/Sha2"
        actual_value = qapi.getAccountDetails(account.getId(), account.getId(), "key", 1).getDetail()

        then:
        actual_value == "{ \"a@test\" : { \"key\" : \"value\" } }"
    }

}
