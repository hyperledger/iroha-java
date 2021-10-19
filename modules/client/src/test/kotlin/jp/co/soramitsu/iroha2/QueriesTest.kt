package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_NAME
import jp.co.soramitsu.iroha2.engine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.engine.AliceHas100XorAndPermissionToBurn
import jp.co.soramitsu.iroha2.engine.DEFAULT_ASSET_DEFINITION_ID
import jp.co.soramitsu.iroha2.engine.DEFAULT_DOMAIN_NAME
import jp.co.soramitsu.iroha2.engine.IrohaRunnerExtension
import jp.co.soramitsu.iroha2.engine.NewAccountWithMetadata
import jp.co.soramitsu.iroha2.engine.NewDomain
import jp.co.soramitsu.iroha2.engine.StoreAssetWithMetadata
import jp.co.soramitsu.iroha2.engine.WithIroha
import jp.co.soramitsu.iroha2.engine.XorAndValAssets
import jp.co.soramitsu.iroha2.generated.crypto.Hash
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionValue
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils.randomAlphabetic
import java.util.concurrent.TimeUnit
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
    @WithIroha(XorAndValAssets::class)
    fun `find all assets`(): Unit = runBlocking {
        QueryBuilder.findAllAssets()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.any { it.id.definitionId == XorAndValAssets.XOR_DEFINITION_ID })
                assert(assets.any { it.id.definitionId == XorAndValAssets.VAL_DEFINITION_ID })
            }
    }

    @Test
    @WithIroha(XorAndValAssets::class)
    fun `find assets by name`(): Unit = runBlocking {
        QueryBuilder.findAssetsByName(XorAndValAssets.XOR_DEFINITION_ID.name)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.all { it.id.definitionId.name == XorAndValAssets.XOR_DEFINITION_ID.name })
            }
    }

    @Test
    @WithIroha(XorAndValAssets::class)
    fun `find assets by account id`(): Unit = runBlocking {
        QueryBuilder.findAssetsByAccountId(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.all { it.id.accountId == ALICE_ACCOUNT_ID })
                assert(assets.any { it.id.definitionId == XorAndValAssets.XOR_DEFINITION_ID })
                assert(assets.any { it.id.definitionId == XorAndValAssets.VAL_DEFINITION_ID })
            }
    }

    @Test
    @WithIroha(XorAndValAssets::class)
    fun `find assets by domain name and asset definition id`(): Unit = runBlocking {
        QueryBuilder.findAssetsByDomainNameAndAssetDefinitionId(
            DEFAULT_DOMAIN_NAME,
            XorAndValAssets.XOR_DEFINITION_ID
        )
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.all { it.id.definitionId == XorAndValAssets.XOR_DEFINITION_ID })
                assert(assets.all { it.id.accountId.domainName == DEFAULT_DOMAIN_NAME })
            }
    }

    @Test
    @WithIroha(XorAndValAssets::class)
    fun `find asset quantity by id`(): Unit = runBlocking {
        val assetId = Id(XorAndValAssets.XOR_DEFINITION_ID, ALICE_ACCOUNT_ID)
        QueryBuilder.findAssetQuantityById(assetId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { quantity ->
                assert(quantity == XorAndValAssets.XOR_QUANTITY)
            }
    }

    @Test
    @WithIroha(StoreAssetWithMetadata::class)
    fun `find asset key value by id and key`(): Unit = runBlocking {
        QueryBuilder.findAssetKeyValueByIdAndKey(
            StoreAssetWithMetadata.ASSET_ID,
            StoreAssetWithMetadata.ASSET_KEY
        )
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { value ->
                assert(value == StoreAssetWithMetadata.ASSET_VALUE)
            }
    }

    @Test
    @WithIroha(StoreAssetWithMetadata::class)
    fun `find asset definition key value by id and key`(): Unit = runBlocking {
        QueryBuilder.findAssetDefinitionKeyValueByIdAndKey(
            StoreAssetWithMetadata.DEFINITION_ID,
            StoreAssetWithMetadata.ASSET_KEY
        )
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also {
                assert(it == StoreAssetWithMetadata.ASSET_VALUE)
            }
    }

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

    @Test
    @WithIroha
    fun `find transactions by account id`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAsset(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            buildSigned(ALICE_KEYPAIR)
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }
        QueryBuilder.findTransactionsByAccountId(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.let { txValues ->
                txValues.all { value ->
                    value.cast<TransactionValue.Transaction>()
                        .versionedTransaction
                        .cast<VersionedTransaction.V1>()
                        ._VersionedTransactionV1
                        .transaction
                        .payload
                        .accountId == ALICE_ACCOUNT_ID
                }
            }.also {
                assert(it)
            }
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `find permission tokens by account id`(): Unit = runBlocking {
        QueryBuilder.findPermissionTokensByAccountId(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.let { tokens ->
                tokens.any {
                    it.params[ASSET_DEFINITION_PARAM_NAME]
                        ?.cast<Value.Id>()
                        ?.idBox
                        ?.cast<IdBox.AssetDefinitionId>()
                        ?.definitionId == DEFAULT_ASSET_DEFINITION_ID
                }
            }.also {
                assert(it)
            }
    }

    @Test
    @WithIroha
    @Disabled
    fun `find transaction by hash`(): Unit = runBlocking {
        val versionedTransaction = TransactionBuilder()
            .account(ALICE_ACCOUNT_ID)
            .registerAsset(DefinitionId(randomAlphabetic(10), DEFAULT_DOMAIN_NAME), AssetValueType.Quantity())
            .buildSigned(ALICE_KEYPAIR)
        val hash = versionedTransaction.hash()

        client.sendTransaction {
            versionedTransaction
        }.also {
            Assertions.assertDoesNotThrow {
                it.get(10, TimeUnit.SECONDS)
            }
        }

        QueryBuilder.findTransactionByHash(Hash(hash))
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .cast<TransactionValue.Transaction>().versionedTransaction
            .hash()
            .also { assertEquals(hash, it) }
    }
}
