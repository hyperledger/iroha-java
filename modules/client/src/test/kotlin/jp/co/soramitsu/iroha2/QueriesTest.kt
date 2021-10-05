package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_NAME
import jp.co.soramitsu.iroha2.engine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.engine.DEFAULT_DOMAIN_NAME
import jp.co.soramitsu.iroha2.engine.IrohaRunnerExtension
import jp.co.soramitsu.iroha2.engine.MultipleAssets
import jp.co.soramitsu.iroha2.engine.NewAccountWithMetadata
import jp.co.soramitsu.iroha2.engine.NewDomain
import jp.co.soramitsu.iroha2.engine.WithIroha
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import kotlin.test.assertEquals

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(IrohaRunnerExtension::class)
@Timeout(30)
class QueriesTest {

    lateinit var client: Iroha2Client

    @Test
    @WithIroha(NewAccountWithMetadata::class)
    fun `find all accounts`(): Unit = runBlocking {
        QueryBuilder.findAllAccounts()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { accounts ->
                assert(accounts.any { it.id.name == ALICE_ACCOUNT_NAME })
                assert(accounts.any { it.id.name == NewAccountWithMetadata.ACCOUNT_NAME })
            }
    }

    @Test
    @WithIroha
    fun `find accounts by name`(): Unit = runBlocking {
        QueryBuilder.findAccountsByName(ALICE_ACCOUNT_NAME)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { accounts ->
                assert(accounts.all { it.id.name == ALICE_ACCOUNT_NAME })
            }
    }

    @Test
    @WithIroha(NewAccountWithMetadata::class)
    fun `find account key value by id and key`(): Unit = runBlocking {
        QueryBuilder.findAccountKeyValueByIdAndKey(
            NewAccountWithMetadata.ACCOUNT_ID,
            NewAccountWithMetadata.KEY
        )
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also {
                assertEquals(NewAccountWithMetadata.VALUE, it)
            }
    }

    @Test
    @WithIroha
    fun `find accounts by domain name`(): Unit = runBlocking {
        QueryBuilder.findAccountsByDomainName(DEFAULT_DOMAIN_NAME)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { accounts ->
                assert(accounts.all { it.id.domainName == DEFAULT_DOMAIN_NAME })
            }
    }

    @Test
    @WithIroha(MultipleAssets::class)
    fun `find all assets`(): Unit = runBlocking {
        QueryBuilder.findAllAssets()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.any { it.id.definitionId == MultipleAssets.XOR_DEFINITION_ID })
                assert(assets.any { it.id.definitionId == MultipleAssets.VAL_DEFINITION_ID })
            }
    }

    @Test
    @WithIroha(MultipleAssets::class)
    fun `find assets by name`(): Unit = runBlocking {
        QueryBuilder.findAssetsByName(MultipleAssets.XOR_DEFINITION_ID.name)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.all { it.id.definitionId.name == MultipleAssets.XOR_DEFINITION_ID.name })
            }
    }

    @Test
    @WithIroha(MultipleAssets::class)
    fun `find assets by account id`(): Unit = runBlocking {
        QueryBuilder.findAssetsByAccountId(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.all { it.id.accountId == ALICE_ACCOUNT_ID })
                assert(assets.any { it.id.definitionId == MultipleAssets.XOR_DEFINITION_ID })
                assert(assets.any { it.id.definitionId == MultipleAssets.VAL_DEFINITION_ID })
            }
    }

    @Test
    @WithIroha(MultipleAssets::class)
    fun `find assets by domain name and asset definition id`(): Unit = runBlocking {
        QueryBuilder.findAssetsByDomainNameAndAssetDefinitionId(
            DEFAULT_DOMAIN_NAME,
            MultipleAssets.XOR_DEFINITION_ID
        )
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.all { it.id.definitionId == MultipleAssets.XOR_DEFINITION_ID })
                assert(assets.all { it.id.accountId.domainName == DEFAULT_DOMAIN_NAME })
            }
    }

    @Test
    @WithIroha(MultipleAssets::class)
    fun `find asset quantity by id`(): Unit = runBlocking {
        val assetId = Id(MultipleAssets.XOR_DEFINITION_ID, ALICE_ACCOUNT_ID)
        QueryBuilder.findAssetQuantityById(assetId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { quantity ->
                assert(quantity == MultipleAssets.XOR_QUANTITY)
            }
    }

    @Test
    @WithIroha(MultipleAssets::class)
    fun `find asset key value by id and key`(): Unit = runBlocking {
        QueryBuilder.findAssetKeyValueByIdAndKey(
            MultipleAssets.STORE_ASSET_ID,
            MultipleAssets.STORE_ASSET_KEY
        )
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { value ->
                assert(value == MultipleAssets.STORE_ASSET_VALUE)
            }
    }

//    @Test
//    @WithIroha(MultipleAssets::class)
//    fun `find asset definition key value by id and key`(): Unit = runBlocking {
//        QueryBuilder.findAssetDefinitionKeyValueByIdAndKey(
//            MultipleAssets.STORE_DEFINITION_ID,
//            MultipleAssets.STORE_ASSET_KEY
//        )
//            .account(ALICE_ACCOUNT_ID)
//            .buildSigned(ALICE_KEYPAIR)
//            .let { query ->
//                client.sendQuery(query)
//            }.also {
//                assert(true)
//            }
//    }

    @Test
    @WithIroha(NewDomain::class)
    fun `find all domains`(): Unit = runBlocking {
        QueryBuilder.findAllDomains()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { domains ->
                assert(domains.any { it.name == DEFAULT_DOMAIN_NAME })
                assert(domains.any { it.name == NewDomain.DOMAIN_NAME })
            }
    }

    @Test
    @WithIroha
    fun `find domain by name`(): Unit = runBlocking {
        QueryBuilder.findDomainByName(DEFAULT_DOMAIN_NAME)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { domain ->
                assert(domain.name == DEFAULT_DOMAIN_NAME)
            }
    }

    @Test
    @WithIroha
    fun `find all peers`(): Unit = runBlocking {
        QueryBuilder.findAllPeers()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { peers ->
                assert(peers.isNotEmpty())
            }
    }
}
