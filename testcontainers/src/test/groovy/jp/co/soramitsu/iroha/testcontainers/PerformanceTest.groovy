package jp.co.soramitsu.iroha.testcontainers


import iroha.protocol.TransactionOuterClass
import jp.co.soramitsu.iroha.java.IrohaAPI
import jp.co.soramitsu.iroha.java.Transaction
import jp.co.soramitsu.iroha.java.Utils
import jp.co.soramitsu.iroha.testcontainers.network.IrohaNetwork
import lombok.Data
import lombok.NoArgsConstructor
import spock.lang.Specification

import javax.xml.bind.DatatypeConverter
import java.security.SecureRandom

import static jp.co.soramitsu.iroha.testcontainers.detail.GenesisBlockBuilder.*

class PerformanceTest extends Specification {

    @Data
    @NoArgsConstructor
    class Run {
        private int number
        private String result
        private long start
        private long end

        @Override
        String toString() {
            return String.join(",", [
                    String.valueOf(number),
                    result,
                    String.valueOf(start),
                    String.valueOf(end)
            ]) + '\n'
        }
    }

    Run single(IrohaAPI api, TransactionOuterClass.Transaction tx) {
        Run run = new Run()

        api.transaction(tx)
                .doOnSubscribe({ d -> run.start = System.currentTimeMillis() })
                .doOnNext(
                { n ->
                    run.end = System.currentTimeMillis()
                    run.result = n.txStatus
                })
                .doOnError(
                { e ->
                    run.end = System.currentTimeMillis()
                    run.result = e.toString().replace(",", " ")
                })
                .blockingSubscribe()

        return run
    }

    SecureRandom secureRandom = new SecureRandom()

    def nextRandomSalt() {
        def s = secureRandom.generateSeed(4)
        return DatatypeConverter.printHexBinary(s).toLowerCase()
    }

    def getCreateAccountTx() {
        return Transaction.builder(defaultAccountId)
                .createAccount(nextRandomSalt(), defaultDomainName, defaultKeyPair.public)
                .sign(defaultKeyPair)
                .build()
    }

    def getCreateDomainTx() {
        return Transaction.builder(defaultAccountId)
                .createDomain("d${nextRandomSalt()}", defaultRoleName)
                .sign(defaultKeyPair)
                .build()
    }

    def perf(path, txtype){
        IrohaNetwork nw1 = new IrohaNetwork(11)
                .addDefaultTransaction()

        nw1.getPeers()
                .forEach({ p ->
            p.container.withLogger(null) // disable loggers
        })

        nw1.start()

        PrintWriter pw = new PrintWriter(path)

        IrohaAPI api = nw1.getApis().get(0)

        for (int i = 0; i < 30; i++) {
            def tx = txtype() as TransactionOuterClass.Transaction
            def run = single(api, tx)
            run.number = i
            println("${i}: ${run}")
            pw.write(run.toString())
            pw.flush()
        }

        pw.close()
    }

    def "create account"() {
        given:
        perf("/tmp/create_account.csv", {getCreateAccountTx()})
    }

    def "create domain"() {
        given:
        perf("/tmp/create_domain.csv", {getCreateDomainTx()})
    }
}
