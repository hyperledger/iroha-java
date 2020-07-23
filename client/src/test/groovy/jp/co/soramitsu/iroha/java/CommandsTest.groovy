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
import static jp.co.soramitsu.iroha.java.ValidationException.Type.NOT_ALLOWED;

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
    def "compareAndSet command: key=#key, value=#value, oldValue=#oldValue"() {
        given:
        def qapi = new QueryAPI(api, account)

        when:
        def tx = Transaction.builder(account.getId())
                .compareAndSetAccountDetail(account.getId(), key, value, oldValue)
                .sign(account.keyPair)
                .build()
        def transaction_observer = new TestTransactionStatusObserver()
        api.transaction(tx).blockingSubscribe(transaction_observer)

        def actual_value = qapi.getAccountDetails(account.getId(), account.getId(), key, 1).getDetail()

        then:
        transaction_observer.assertAllTransactionsCommitted()
        actual_value == expected_value

        where:
        key            | value         | oldValue      | expected_value
        "initial_key1" | "updated_val" | "wrong_val"   | "{ \"a@test\" : { \"initial_key1\" : \"initial_val\" } }"
        "initial_key1" | "updated_val" | null          | "{ \"a@test\" : { \"initial_key1\" : \"initial_val\" } }"
        "initial_key2" | "updated_val" | "initial_val" | "{ \"a@test\" : { \"initial_key2\" : \"updated_val\" } }"
        "empty1"       | "value"       | "wrong"       | "{}"
        "empty2"       | "value"       | null          | "{ \"a@test\" : { \"empty2\" : \"value\" } }"
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

        // TODO (a.chernyshov) remove lower case as soon as Iroha supports case insensitive hash comparison
        def actual_value = qapi.getEngineReceipts(Utils.toHex(hash).toLowerCase())

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
        // TODO (a.chernyshov) remove lower case as soon as Iroha supports case insensitive hash comparison
        def deploy_receipt = qapi.getEngineReceipts(Utils.toHex(deploy_hash).toLowerCase())
        def callee = deploy_receipt.getEngineReceipts(0).contractAddress

        when:
        def tx = Transaction.builder(account.getId())
                .callEngine(caller, callee, input)
                .sign(account.keyPair)
                .build()
        def hash = Utils.hash(tx);
        def transaction_observer = new TestTransactionStatusObserver()
        api.transaction(tx).blockingSubscribe(transaction_observer)

        // TODO (a.chernyshov) remove lower case as soon as Iroha supports case insensitive hash comparison
        def actual_value = qapi.getEngineReceipts(Utils.toHex(hash).toLowerCase())

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

    @Unroll
    def "setSettingValue command throws NOT_ALLOWED: key=#key, value=#value"() {
        when:
        Transaction.builder(account.getId())
                .setSettingValue(key, value)
        then:
        def e = thrown(ValidationException.class)
        e.type == NOT_ALLOWED

        where:
        key       | value
        "any key" | "any value"
    }
}
