package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetType
import jp.co.soramitsu.iroha2.generated.AssetValue
import kotlinx.coroutines.runBlocking
import java.net.URL
import java.util.UUID

fun main(args: Array<String>): Unit = runBlocking {
    val chainId = UUID.fromString("00000000-0000-0000-0000-000000000000")
    val peerUrl = "http://127.0.0.1:8080"
    val telemetryUrl = "http://127.0.0.1:8180"
    val admin = AccountId(
        "wonderland".asDomainId(),
        publicKeyFromHex("CE7FA46C9DCE7EA4B125E2E36BDB63EA33073E7590AC92816AE1E861B7048B03").toIrohaPublicKey(),
    )
    val adminKeyPair = keyPairFromHex(
        "CE7FA46C9DCE7EA4B125E2E36BDB63EA33073E7590AC92816AE1E861B7048B03",
        "CCF31D85E3B32A4BEA59987CE0C78E3B8E2DB93881468AB2435FE45D5C9DCD53",
    )
    val client = AdminIroha2Client(URL(peerUrl), URL(peerUrl), URL(telemetryUrl), log = true)
    val query = Query(client, admin, adminKeyPair)
    query.findAllDomains()
        .also { println("ALL DOMAINS: ${it.map { d -> d.id.asString() }}") }
    query.findAllAssets()
        .also { println("ALL ASSETS: ${it.map { d -> d.id.asString() }}") }
    query.findAllAccounts()
        .also { println("ALL ACCOUNTS: ${it.map { d -> d.id.asString() }}") }

    val sendTransaction = SendTransaction(client, admin, adminKeyPair, chainId)

    val domain = "looking_glass_${System.currentTimeMillis()}"
    sendTransaction.registerDomain(domain).also { println("DOMAIN $domain CREATED") }

    val madHatter = "mad_hatter_${System.currentTimeMillis()}$ACCOUNT_ID_DELIMITER$domain"
    val madHatterKeyPair = generateKeyPair()
    sendTransaction.registerAccount(madHatter, listOf(madHatterKeyPair.public.toIrohaPublicKey()))
        .also { println("ACCOUNT $madHatter CREATED") }

    val assetDefinition = "asset_time_${System.currentTimeMillis()}$ASSET_ID_DELIMITER$domain"
    sendTransaction.registerAssetDefinition(assetDefinition, AssetType.numeric())
        .also { println("ASSET DEFINITION $assetDefinition CREATED") }

    val madHatterAsset = AssetId(assetDefinition.asAssetDefinitionId(), madHatter.asAccountId())
    sendTransaction.registerAsset(madHatterAsset, AssetValue.Numeric(100.asNumeric()))
        .also { println("ASSET $madHatterAsset CREATED") }

    val whiteRabbit = "white_rabbit_${System.currentTimeMillis()}$ACCOUNT_ID_DELIMITER$domain"
    val whiteRabbitKeyPair = generateKeyPair()
    sendTransaction.registerAccount(whiteRabbit, listOf(whiteRabbitKeyPair.public.toIrohaPublicKey()))
        .also { println("ACCOUNT $whiteRabbit CREATED") }

    val whiteRabbitAsset = AssetId(assetDefinition.asAssetDefinitionId(), whiteRabbit.asAccountId())
    sendTransaction.registerAsset(whiteRabbitAsset, AssetValue.Numeric(0.asNumeric()))
        .also { println("ASSET $whiteRabbitAsset CREATED") }

    sendTransaction.transferAsset(madHatterAsset, 10, whiteRabbit, madHatter.asAccountId(), madHatterKeyPair)
        .also { println("$madHatter TRANSFERRED FROM $madHatterAsset TO $whiteRabbitAsset: 10") }
    query.getAccountAmount(madHatter, madHatterAsset.definition).also { println("$madHatterAsset BALANCE: $it") }
    query.getAccountAmount(whiteRabbit, whiteRabbitAsset.definition).also { println("$whiteRabbitAsset BALANCE: $it") }

    sendTransaction.burnAssets(madHatterAsset, 10, madHatter.asAccountId(), madHatterKeyPair)
        .also { println("$madHatterAsset WAS BURN") }

    query.getAccountAmount(madHatter, madHatterAsset.definition)
        .also { println("$madHatterAsset BALANCE: $it AFTER ASSETS BURNING") }
}
