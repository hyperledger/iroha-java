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
        publicKeyFromHex("04FF5B81046DDCCF19E2E451C45DFB6F53759D4EB30FA2EFA807284D1CC33016").toIrohaPublicKey(),
    )
    val adminKeyPair = keyPairFromHex(
        "7233bfc89dcbd68c19fde6ce6158225298ec1131b6a130d1aeb454c1ab5183c0",
        "9ac47abf59b356e0bd7dcbbbb4dec080e302156a48ca907e47cb6aea1d32719e",
    )
    val client = AdminIroha2Client(URL(peerUrl), URL(peerUrl), URL(telemetryUrl), log = true)
    val query = Query(client, admin, adminKeyPair)
    query.findAllDomains()
        .also { println("ALL DOMAINS: ${it.map { d -> d.id.asString() }}") }
    query.findAllAssets()
        .also { println("ALL ASSETS: ${it.map { d -> d.id.asString() }}") }
    query.findAllAssets()
        .also { println("ALL ACCOUNTS: ${it.map { d -> d.id.asString() }}") }

    val sendTransaction = SendTransaction(client, admin, adminKeyPair, chainId)

    val domain = "looking_glass_${System.currentTimeMillis()}"
    sendTransaction.registerDomain(domain).also { println("DOMAIN $domain CREATED") }

    val madHatter = "madHatter_${System.currentTimeMillis()}$ACCOUNT_ID_DELIMITER$domain"
    val madHatterKeyPair = generateKeyPair()
    sendTransaction.registerAccount(madHatter, listOf(madHatterKeyPair.public.toIrohaPublicKey()))
        .also { println("ACCOUNT $madHatter CREATED") }

    val assetDefinition = "asset_time_${System.currentTimeMillis()}$ASSET_ID_DELIMITER$domain"
    sendTransaction.registerAssetDefinition(assetDefinition, AssetType.numeric())
        .also { println("ASSET DEFINITION $assetDefinition CREATED") }

    val madHatterAsset = AssetId(assetDefinition.asAssetDefinitionId(), madHatter.asAccountId())
    sendTransaction.registerAsset(madHatterAsset, AssetValue.Numeric(100.asNumeric()))
        .also { println("ASSET $madHatterAsset CREATED") }

    val whiteRabbit = "whiteRabbit_${System.currentTimeMillis()}$ACCOUNT_ID_DELIMITER$domain"
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
