package jp.co.soramitsu.iroha.java

import jp.co.soramitsu.iroha.java.debug.Account
import jp.co.soramitsu.iroha.java.debug.TestTransactionStatusObserver
import jp.co.soramitsu.iroha.testcontainers.IrohaContainer
import jp.co.soramitsu.iroha.testcontainers.PeerConfig
import jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder
import spock.lang.Specification
import spock.lang.Unroll

import static jp.co.soramitsu.iroha.java.Transaction.builder
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultAccountId
import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.defaultKeyPair

class CommandsTest extends Specification {

    static Account account = Account.create("a@test")

    static IrohaAPI api
    static def iroha = new IrohaContainer()
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

    static def setDetail(Account account, String key, String value) {
        return builder(account.id)
                .setAccountDetail(account.id, key, value)
                .sign(account.keyPair)
                .build()
    }

    static def createAccount(Account a) {
        return Transaction.builder(defaultAccountId)
                .createAccount(a.id, a.keyPair.public)
                .sign(defaultKeyPair)
                .build()
    }

    static def getGenesisBlock() {
        return new GenesisBlockBuilder()
                .addDefaultTransaction()
                .addTransaction(createAccount(account))
                .addTransaction(setDetail(account, "initial_key1", "initial_val"))
                .addTransaction(setDetail(account, "initial_key2", "initial_val"))
                .build()
    }

    /**
     * Binary code of contract
     * Just a valid binary code.
     */
    static def evm_binary_code = "606060405260a18060106000396000f360606040526000357c01000000000000000" +
            "0000000000000000000000000000000000000000090048063d46300fd1460435780" +
            "63ee919d5014606857603f565b6002565b34600257605260048050506082565b604" +
            "0518082815260200191505060405180910390f35b34600257608060048080359060" +
            "200190919050506093565b005b600060006000505490506090565b90565b8060006" +
            "00050819055505b5056"

    /**
     * calling setA(uint256), bytes4(keccak256(setA(uint256))) == ee919d50, and
     * append uint256 equal to 1 as the parameter
     */
    static def evm_abi_encoded_call = "ee919d50" +
            "0000000000000000000000000000000000000000000000000000000000000001"

    @Unroll
    def "compareAndSet command: key=#key, value=#value, oldValue=#oldValue, checkEmpty=#checkEmpty"() {
        given:
        def qapi = new QueryAPI(api, account)

        when:
        def tx = Transaction.builder(account.getId())
                .compareAndSetAccountDetail(account.getId(), key, value, oldValue, checkEmpty)
                .sign(account.keyPair)
                .build()
        def transaction_observer = new TestTransactionStatusObserver()
        api.transaction(tx).blockingSubscribe(transaction_observer)

        def actual_value = qapi.getAccountDetails(account.getId(), account.getId(), key, 1).getDetail()

        then:
        transaction_observer.assertComplete()
        actual_value == expected_value

        where:
        key            | value         | oldValue     | checkEmpty | expected_value
        "initial_key1" | "updated_val" | "wrong_val"  | null       | "{ \"a@test\" : { \"initial_key1\" : \"initial_val\" } }"
        "initial_key1" | "updated_val" | null         | null       | "{ \"a@test\" : { \"initial_key1\" : \"initial_val\" } }"
        "initial_key2" | "updated_val" | "initial_val"| null       | "{ \"a@test\" : { \"initial_key2\" : \"updated_val\" } }"
        "absent_key1"  | "value"       | null         | null       | "{ \"a@test\" : { \"absent_key1\" : \"value\" } }"
        "absent_key2"  | "value"       | null         | true       | "{ \"a@test\" : { \"absent_key2\" : \"value\" } }"
        // if value is empty, old value is not checked, like checkEmpty=false
        "absent_key3"  | "value"       | "wrong"      | null       | "{ \"a@test\" : { \"absent_key3\" : \"value\" } }"
        "absent_key4"  | "value"       | null         | false      | "{ \"a@test\" : { \"absent_key4\" : \"value\" } }"
        "absent_key5"  | "value"       | "any_value"  | false      | "{ \"a@test\" : { \"absent_key5\" : \"value\" } }"
        "absent_key6"  | "value"       | "any_value"  | true       | "{}"
    }

    @Unroll
    def "callEngine command deploy contract: caller=#caller, input=input"() {
        given:
        def qapi = new QueryAPI(api, account)

        when:
        def tx = Transaction.builder(account.getId())
                .callEngine(caller, null, input)
                .sign(account.keyPair)
                .build()
        def hash = Utils.hash(tx);
        def transaction_observer = new TestTransactionStatusObserver()
        api.transaction(tx).blockingSubscribe(transaction_observer)

        def actual_value = qapi.getEngineReceipts(Utils.toHex(hash))

        then:
        transaction_observer.assertAllTransactionsCommitted()

        actual_value.getEngineReceiptsCount() == 1
        def receipt = actual_value.getEngineReceipts(0)
        receipt.getCommandIndex() == 0
        receipt.getCaller() == account.getId()
        !receipt.getContractAddress().empty
        receipt.getCallResult().getCallee().empty
        receipt.getCallResult().getResultData().empty

        where:
        caller          | input
        account.getId() | evm_binary_code
        account.getId() | ""
    }

    @Unroll
    def "callEngine command call contract: caller=#caller, input=input"() {
        given:
        def qapi = new QueryAPI(api, account)

        // deploy contract
        def deploy_tx = Transaction.builder(account.getId())
                .callEngine(account.getId(), null, evm_binary_code)
                .sign(account.keyPair)
                .build()
        def deploy_hash = Utils.hash(deploy_tx);
        api.transaction(deploy_tx).blockingSubscribe()
        def deploy_receipt = qapi.getEngineReceipts(Utils.toHex(deploy_hash))
        def callee = deploy_receipt.getEngineReceipts(0).contractAddress

        when:
        def tx = Transaction.builder(account.getId())
                .callEngine(caller, callee, input)
                .sign(account.keyPair)
                .build()
        def hash = Utils.hash(tx);
        def transaction_observer = new TestTransactionStatusObserver()
        api.transaction(tx).blockingSubscribe(transaction_observer)

        def actual_value = qapi.getEngineReceipts(Utils.toHex(hash))

        then:
        transaction_observer.assertAllTransactionsCommitted()

        actual_value.getEngineReceiptsCount() == 1
        def receipt = actual_value.getEngineReceipts(0)
        receipt.getCommandIndex() == 0
        receipt.getCaller() == account.getId()
        receipt.getContractAddress().empty
        receipt.getCallResult().getCallee() == callee
        receipt.getCallResult().getResultData().empty

        where:
        caller          | input
        account.getId() | evm_abi_encoded_call
    }
}
