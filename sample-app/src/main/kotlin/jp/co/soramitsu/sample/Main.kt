package jp.co.soramitsu.sample

import jp.co.soramitsu.iroha2.asAssetDefinitionId
import jp.co.soramitsu.iroha2.asDomainId
import jp.co.soramitsu.iroha2.asName
import jp.co.soramitsu.iroha2.asString
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.keyPairFromHex
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>): Unit = runBlocking {
    val peerUrl = args[0]
    val telemetryUrl = args[1]
    val admin = AccountId(args[2].asName(), args[3].asDomainId()) // transactions send behalf of this account
    val keyPair = keyPairFromHex(args[4], args[5])

    val helper = Helper(peerUrl, telemetryUrl, admin, keyPair)

    val domain = "domain_${System.currentTimeMillis()}"
    helper.registerDomain(domain)
        .also { println("DOMAIN $domain CREATED") }

    val account = "account_${System.currentTimeMillis()}@$domain"
    helper.registerAccount(account, listOf())
        .also { println("ACCOUNT $account CREATED") }

    val assetDefinition = "asset_${System.currentTimeMillis()}#$domain"
    helper.registerAssetDefinition(assetDefinition, AssetValueType.Store())
        .also { println("ASSET DEFINITION $assetDefinition CREATED") }

    val asset = "${assetDefinition.asAssetDefinitionId().name.string}##$account"
    helper.registerAsset(asset, AssetValue.Store(Metadata(mapOf())))
        .also { println("ASSET $asset CREATED") }

    helper.findAllDomains()
        .also { println("ALL DOMAINS: ${it.map { d -> d.id.asString() }}") }
    helper.findAllAccounts()
        .also { println("ALL ACCOUNTS: ${it.map { a -> a.id.asString() }}") }
    helper.findAllAssets()
        .also { println("ALL ASSETS: ${it.map { a -> a.id.asString() }}") }

    helper.findDomainById(domain).also { println("DOMAIN $domain exists") }
    helper.findAccountById(account).also { println("ACCOUNT $account exists") }
    helper.findAssetById(asset).also { println("ASSET $asset exists") }
}
