package jp.co.soramitsu.sample

import jp.co.soramitsu.iroha2.ACCOUNT_ID_DELIMITER
import jp.co.soramitsu.iroha2.ASSET_ID_DELIMITER
import jp.co.soramitsu.iroha2.asAccountId
import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asString
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.keyPairFromHex
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>): Unit = runBlocking {
    val peerUrl = args[0]
    val telemetryUrl = args[1]
    val admin = AccountId(args[2].asName(), args[3].asDomainId()) // transactions send behalf of this account
    val adminKeyPair = keyPairFromHex(args[4], args[5])

    val helper = Helper(peerUrl, telemetryUrl, admin, adminKeyPair)

    val domain = "domain_${System.currentTimeMillis()}"
    helper.registerDomain(domain)
        .also { println("DOMAIN $domain CREATED") }

    val assetDefinition = "asset_${System.currentTimeMillis()}$ASSET_ID_DELIMITER$domain"
    helper.registerAssetDefinition(assetDefinition, AssetValueType.Quantity())
        .also { println("ASSET DEFINITION $assetDefinition CREATED") }

    val joe = "joe_${System.currentTimeMillis()}$ACCOUNT_ID_DELIMITER$domain"
    val joeKeyPair = generateKeyPair()
    helper.registerAccount(joe, listOf(joeKeyPair.public.toIrohaPublicKey()))
        .also { println("ACCOUNT $joe CREATED") }

    val joeAsset = "$assetDefinition$ASSET_ID_DELIMITER$joe"
    helper.registerAsset(joeAsset, AssetValue.Quantity(100))
        .also { println("ASSET $joeAsset CREATED") }

    helper.findAllDomains()
        .also { println("ALL DOMAINS: ${it.map { d -> d.id.asString() }}") }
    helper.findAllAccounts()
        .also { println("ALL ACCOUNTS: ${it.map { a -> a.id.asString() }}") }
    helper.findAllAssets()
        .also { println("ALL ASSETS: ${it.map { a -> a.id.asString() }}") }

    helper.findDomainById(domain).also { println("DOMAIN $domain exists") }
    helper.findAccountById(joe).also { println("ACCOUNT $joe exists") }
    helper.findAssetById(joeAsset).also { println("ASSET $joeAsset exists") }

    val carl = "carl_${System.currentTimeMillis()}$ACCOUNT_ID_DELIMITER$domain"
    helper.registerAccount(carl, listOf())
        .also { println("ACCOUNT $carl CREATED") }

    val carlAsset = "$assetDefinition$ASSET_ID_DELIMITER$carl"
    helper.registerAsset(carlAsset, AssetValue.Quantity(0))
        .also { println("ASSET $carlAsset CREATED") }

    helper.transferAsset(joeAsset, 10, carlAsset, joe.asAccountId(), joeKeyPair)
        .also { println("$joe TRANSFERRED FROM $joeAsset TO $carlAsset: 10") }
    helper.getAccountAmount(joe, joeAsset).also { println("$joeAsset BALANCE: $it") }
    helper.getAccountAmount(carl, carlAsset).also { println("$carlAsset BALANCE: $it") }

    helper.grantTransferUserAsset(joeAsset, admin, joe.asAccountId(), joeKeyPair)
    helper.transferAsset(joeAsset, 10, carlAsset, admin, adminKeyPair)
        .also { println("${admin.asString()} TRANSFERRED FROM $joeAsset TO $carlAsset: 10") }

    helper.grantBurnAssets(joeAsset, admin, joe.asAccountId(), joeKeyPair)
    helper.unregisterAsset(joeAsset).also { println("ASSET $joeAsset UNREGISTERED") }
    helper.unregisterAccount(joe).also { println("ACCOUNT $joe UNREGISTERED") }
    helper.unregisterDomain(domain).also { println("DOMAIN $domain UNREGISTERED") }
}
