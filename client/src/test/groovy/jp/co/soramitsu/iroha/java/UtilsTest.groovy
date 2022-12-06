package jp.co.soramitsu.iroha.java


import spock.lang.Specification
import spock.lang.Unroll

import java.time.Instant

class UtilsTest extends Specification {

    def "hash works as expected"() {
        given:
        Transaction tx = Transaction.builder("a@a", Instant.ofEpochMilli(0), FieldValidator.defaultConfig)
                .createAsset("asset", "domain", 3)
                .build()

        when: "payloads are equal"
        def p1 = tx.payload()
        def p2 = tx.build().getPayload().toByteArray()

        then:
        p2 == p1

        when: "hashes are equal"
        def h1 = Utils.hash(tx.build())
        def h2 = tx.hash()

        then:
        h2 == h1

        // To prevent inconsistent build
        when: "reduced hashes are equal"
        def r1 = tx.getReducedHashHex()
        def r2 = Utils.toHex(Utils.reducedHash(tx.build()))

        then:
        r2 == r1
    }

    @Unroll
    def "Iroha escape and unescape: inStr=#inStr"() {
        when:
        def escapedStr = Utils.irohaEscape(inStr)
        def unescapedStr = Utils.irohaUnEscape(escapedStr)

        then:
        escapedStr == expected_value
        inStr == unescapedStr

        where:
        inStr                     | expected_value
        "{\"quoted\" \n newline}" | "{\\\"quoted\\\" \\n newline}"
    }
}
