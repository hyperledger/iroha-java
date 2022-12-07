package jp.co.soramitsu.iroha.java

import jp.co.soramitsu.crypto.ed25519.Ed25519Sha3
import spock.lang.Specification

import java.time.Instant

import static jp.co.soramitsu.iroha.java.ValidationException.Type.*

class FieldValidatorTest extends Specification {

    def fv = new FieldValidator(FieldValidator.defaultConfig)


    def "valid data is valid"() {
        given:
        def accountId = "${account}@${domain}"
        def assetId = "${asset}#${domain}"

        when:
        fv.checkAccount(account)
        fv.checkAccountDetailsKey(key)
        fv.checkAccountDetailsValue(value)
        fv.checkAccountId(accountId)
        fv.checkAmount(amount)
        fv.checkAssetId(assetId)
        fv.checkDomain(domain)
        fv.checkPeerAddress(address)
        fv.checkPrecision(precision)
        fv.checkPublicKey(publicKey)
        fv.checkAssetName(asset)
        fv.checkQuorum(quorum)
        fv.checkRoleName(role)
        fv.checkTimestamp(time)
        fv.checkDescription(description)
        fv.checkEvmAddress(evmAddress)
        fv.checkHexString(hexString)
        fv.checkHexString(emptyHexString)

        then:
        noExceptionThrown()

        where:
        account = "bogdan"
        key = "key"
        value = "value"
        domain = "amount"
        asset = "usd"
        address = "127.0.0.1:1337"
        role = "role"
        amount = "1337.1337"
        precision = 50
        quorum = 5
        publicKey = new Ed25519Sha3().generateKeypair().getPublic().getEncoded()
        time = Instant.now().toEpochMilli()
        description = "description"
        evmAddress = "7C370993FD90AF204FD582004E2E54E6A94F2651"
        hexString = "DEADface"
        emptyHexString = ""
    }

    def "invalid account is invalid"() {
        when:
        switch (type) {
            case ACCOUNT_NAME:
                fv.checkAccount(string as String)
                break
            case ACCOUNT_ID:
                fv.checkAccountId(string as String)
                break
            case ASSET_NAME:
                fv.checkAssetName(string as String)
                break
            case ASSET_ID:
                fv.checkAssetId(string as String)
                break
            case AMOUNT:
                fv.checkAmount(string as String)
                break
            case EVM_ADDRESS:
                fv.checkEvmAddress(string as String)
                break
            case HEX_STRING:
                fv.checkHexString(string as String)
                break
            case PUBKEY:
                fv.checkPublicKey(string as byte[])
                break
            case PEER_ADDRESS:
                fv.checkPeerAddress(string as String)
                break
            case QUORUM:
                fv.checkQuorum(string as int)
                break
            case PRECISION:
                fv.checkPrecision(string as int)
                break
            case ROLE_NAME:
                fv.checkRoleName(string as String)
                break
            case DETAILS_KEY:
                fv.checkAccountDetailsKey(string as String)
                break
            case DETAILS_VALUE:
                fv.checkAccountDetailsValue(string as String)
                break
            case TIMESTAMP:
                fv.checkTimestamp(string as long)
                break
            case DOMAIN:
                fv.checkDomain(string as String)
                break
            case DESCRIPTION:
                fv.checkDescription(string as String)
                break
            default:
                throw new IllegalStateException("Something went wrong")

        }

        then:
        def e = thrown(ValidationException.class)
        e.type == type

        and:
        println(e)

        where:
        type          | string
        ACCOUNT_NAME  | "кек" // wrong name
        ACCOUNT_ID    | "@domain" // no account name
        ACCOUNT_ID    | "" // wrong format
        ASSET_NAME    | "лул" // wrong asset name
        ASSET_ID      | "#domain" // empty domain
        ASSET_ID      | "" // wrong format
        AMOUNT        | "hello.123"  // number format exception
        AMOUNT        | BigDecimal.TEN.setScale(300).toString() // does not fit in uint256
        AMOUNT        | BigDecimal.ONE.negate().toString() // negative
        EVM_ADDRESS   | "7C370993FD90AF204" // too short
        EVM_ADDRESS   | "7C370993FD90AF204FD582004E2E54E6A94F26517C370993FD90AF204FD582004E2E54E6A94F2651" // too long
        EVM_ADDRESS   | "wrong symbols" // wrong symbols
        HEX_STRING    | "wrong symbols" // wrong symbols
        PUBKEY        | [1, 2, 3] as byte[] // wrong size
        PEER_ADDRESS  | "127.0.0.1" // no port
        PEER_ADDRESS  | "127.0.0.1:100a" // wrong port
        PEER_ADDRESS  | ":100" // no hostname
        QUORUM        | 0 // can't be zero or negative
        PRECISION     | -1 // too small
        PRECISION     | 300 // too big
        ROLE_NAME     | "" // empty role name
        DETAILS_KEY   | "" // empty key
        DETAILS_VALUE | "1" * 6000000 // too big
        TIMESTAMP     | -5 // can't be negative
        DOMAIN        | "" // empty
        DOMAIN        | "bogdan!com" // invalid domain
        DESCRIPTION   | "1" * 100 // too long

    }
}
