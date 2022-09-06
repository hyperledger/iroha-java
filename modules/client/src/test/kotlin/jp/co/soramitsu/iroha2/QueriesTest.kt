package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.engine.ALICE_ACCOUNT_NAME
import jp.co.soramitsu.iroha2.engine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.engine.AliceHas100XorAndPermissionToBurn
import jp.co.soramitsu.iroha2.engine.AliceHasRoleWithAccessToBobsMetadata
import jp.co.soramitsu.iroha2.engine.AliceWithTestAssets
import jp.co.soramitsu.iroha2.engine.BOB_ACCOUNT_NAME
import jp.co.soramitsu.iroha2.engine.DEFAULT_ASSET_DEFINITION_ID
import jp.co.soramitsu.iroha2.engine.DEFAULT_ASSET_ID
import jp.co.soramitsu.iroha2.engine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.engine.DefaultGenesis
import jp.co.soramitsu.iroha2.engine.IrohaTest
import jp.co.soramitsu.iroha2.engine.NewAccountWithMetadata
import jp.co.soramitsu.iroha2.engine.NewDomain
import jp.co.soramitsu.iroha2.engine.NewDomainWithMetadata
import jp.co.soramitsu.iroha2.engine.StoreAssetWithMetadata
import jp.co.soramitsu.iroha2.engine.TEST_ASSET_DEFINITION_ID
import jp.co.soramitsu.iroha2.engine.WithExecutableTrigger
import jp.co.soramitsu.iroha2.engine.WithIroha
import jp.co.soramitsu.iroha2.engine.XorAndValAssets
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.pagination.Pagination
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.PredicateBox
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.value.Predicate
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionValue
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction
import jp.co.soramitsu.iroha2.query.QueryBuilder
import jp.co.soramitsu.iroha2.transaction.ASSET_DEFINITION_PARAM_NAME
import jp.co.soramitsu.iroha2.transaction.QueryFilters
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.withTimeout
import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.string.Predicate as PredicateValue

class QueriesTest : IrohaTest<Iroha2Client>() {

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
    @WithIroha(NewAccountWithMetadata::class)
    fun `find all accounts with filter`(): Unit = runBlocking {
        val filter = PredicateBox.Or(
            listOf(
                PredicateBox.Raw(
                    Predicate.Identifiable(
                        PredicateValue.Is(
                            "alice@wonderland"
                        )
                    )
                ),
                PredicateBox.Raw(
                    Predicate.Identifiable(
                        PredicateValue.Is(
                            "bob@wonderland"
                        )
                    )
                )
            )
        )
        QueryBuilder.findAllAccounts(filter)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { accounts ->
                assert(accounts.size == 2)
                assert(accounts.any { it.id.name == ALICE_ACCOUNT_NAME })
                assert(accounts.any { it.id.name == BOB_ACCOUNT_NAME })
            }
    }

    @Test
    @WithIroha(DefaultGenesis::class)
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
    @WithIroha(DefaultGenesis::class)
    fun `find accounts by name with filter`(): Unit = runBlocking {
        val filter = QueryFilters.startsWith("alice")
        QueryBuilder.findAccountsByName(ALICE_ACCOUNT_NAME, filter)
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
    fun `find account key value by ID and key`(): Unit = runBlocking {
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
    @WithIroha(DefaultGenesis::class)
    fun `find accounts by domain ID`(): Unit = runBlocking {
        QueryBuilder.findAccountsByDomainId(DEFAULT_DOMAIN_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { accounts ->
                assert(accounts.all { it.id.domainId == DEFAULT_DOMAIN_ID })
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
        QueryBuilder.findAssetsByDomainIdAndAssetDefinitionId(
            DEFAULT_DOMAIN_ID,
            XorAndValAssets.XOR_DEFINITION_ID
        )
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.all { it.id.definitionId == XorAndValAssets.XOR_DEFINITION_ID })
                assert(assets.all { it.id.accountId.domainId == DEFAULT_DOMAIN_ID })
            }
    }

    @Test
    @WithIroha(XorAndValAssets::class)
    fun `find asset quantity by id`(): Unit = runBlocking {
        val assetId = AssetId(XorAndValAssets.XOR_DEFINITION_ID, ALICE_ACCOUNT_ID)
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
    fun `find asset key value by ID and key`(): Unit = runBlocking {
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
    fun `find asset definition key value by ID and key`(): Unit = runBlocking {
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
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `find asset definition by ID`(): Unit = runBlocking {
        QueryBuilder.findAssetDefinitionById(DEFAULT_ASSET_DEFINITION_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { assetDefinition ->
                assertEquals(assetDefinition.id, DEFAULT_ASSET_DEFINITION_ID)
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
                assert(domains.any { it.id == DEFAULT_DOMAIN_ID })
                assert(domains.any { it.id == NewDomain.DOMAIN_ID })
            }
    }

    @Test
    @WithIroha(NewDomain::class)
    fun `find all domains with filter`(): Unit = runBlocking {
        val filter = QueryFilters.startsWith("wonder")
        QueryBuilder.findAllDomains(filter)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { domains ->
                assert(domains.size == 1)
                assert(domains.any { it.id == DEFAULT_DOMAIN_ID })
            }
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `find domain by ID`(): Unit = runBlocking {
        QueryBuilder.findDomainById(DEFAULT_DOMAIN_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { domain ->
                assert(domain.id == DEFAULT_DOMAIN_ID)
            }
    }

    @Test
    @WithIroha(DefaultGenesis::class)
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
    @WithIroha(DefaultGenesis::class)
    fun `find transactions by account id`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAsset(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            buildSigned(ALICE_KEYPAIR)
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
                        ?.assetDefinitionId == DEFAULT_ASSET_DEFINITION_ID
                }
            }.also {
                assert(it)
            }
    }

    @Test
    @WithIroha(DefaultGenesis::class)
    fun `find transaction by hash`(): Unit = runBlocking {
        val hash = client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAsset(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            buildSigned(ALICE_KEYPAIR)
        }.let { d ->
            withTimeout(txTimeout) { d.await() }
        }

        QueryBuilder.findTransactionByHash(hash)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .cast<TransactionValue.Transaction>()
            .versionedTransaction.hash()
            .also { assertContentEquals(hash, it) }
    }

    @Test
    @WithIroha(NewDomainWithMetadata::class)
    fun `find domain key value by ID and key`(): Unit = runBlocking {
        QueryBuilder.findDomainKeyValueByIdAndKey(
            NewDomainWithMetadata.DOMAIN_ID,
            NewDomainWithMetadata.KEY
        ).account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { assertEquals(NewDomainWithMetadata.VALUE, it) }
    }

    @Test
    @WithIroha(WithExecutableTrigger::class)
    fun `find trigger by ID`(): Unit = runBlocking {
        val triggerId = WithExecutableTrigger.TRIGGER_ID

        QueryBuilder.findTriggerById(triggerId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { trigger ->
                assertTrue { trigger.id == triggerId }
            }
    }

    @Test
    @WithIroha(WithExecutableTrigger::class)
    fun `find all active trigger IDs`(): Unit = runBlocking {
        val triggerId = WithExecutableTrigger.TRIGGER_ID

        QueryBuilder.findAllActiveTriggerIds()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { ids ->
                assertTrue { ids.all { it == triggerId } }
            }
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `find all account with pagination`(): Unit = runBlocking {
        var page = Pagination(0, 5)
        var accounts = QueryBuilder.findAllAccounts()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQueryWithPagination(query, page) }
        assertEquals(3, accounts.data.size)
        assertEquals(page, accounts.pagination)
        assertEquals(3, accounts.total.toInt())

        createAccount("foo")
        createAccount("bar")

        page = Pagination(3, 5)
        accounts = QueryBuilder.findAllAccounts()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQueryWithPagination(query, page) }
        assertEquals(2, accounts.data.size)
        assertEquals(page, accounts.pagination)
        assertEquals(5, accounts.total.toInt())
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `find all transactions`(): Unit = runBlocking {
        repeat(5) {
            client.sendTransaction {
                account(ALICE_ACCOUNT_ID)
                burnAsset(DEFAULT_ASSET_ID, 1)
                buildSigned(ALICE_KEYPAIR)
            }.also { d ->
                withTimeout(txTimeout) { d.await() }
            }
        }
        QueryBuilder.findAllTransactions()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { txs ->
                assertTrue(txs.size == 6) // 5 + genesis tx
            }
    }

    @Test
    @WithIroha(AliceHas100XorAndPermissionToBurn::class)
    fun `find all blocks`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            burnAsset(DEFAULT_ASSET_ID, 1)
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
        QueryBuilder.findAllBlocks()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { blocks ->
                assertTrue(blocks.size == 2)
            }
    }

    @Test
    @WithIroha(AliceHasRoleWithAccessToBobsMetadata::class)
    fun `find all role IDs`(): Unit = runBlocking {
        QueryBuilder.findAllRoleIds()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { ids ->
                assertContains(
                    ids,
                    AliceHasRoleWithAccessToBobsMetadata.ROLE_ID
                )
            }
    }

    @Test
    @WithIroha(AliceHasRoleWithAccessToBobsMetadata::class)
    fun `find roles by account ID`(): Unit = runBlocking {
        QueryBuilder.findRolesByAccountId(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { ids ->
                assertContains(
                    ids,
                    AliceHasRoleWithAccessToBobsMetadata.ROLE_ID
                )
            }
    }

    @Test
    @WithIroha(AliceHasRoleWithAccessToBobsMetadata::class)
    fun `find all roles`(): Unit = runBlocking {
        QueryBuilder.findAllRoles()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { roles ->
                assertContains(
                    roles.map { it.id },
                    AliceHasRoleWithAccessToBobsMetadata.ROLE_ID
                )
            }
    }

    @Test
    @WithIroha(AliceHasRoleWithAccessToBobsMetadata::class)
    fun `find role by ID`(): Unit = runBlocking {
        val roleId = AliceHasRoleWithAccessToBobsMetadata.ROLE_ID
        QueryBuilder.findRoleByRoleId(roleId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { role -> assertEquals(role.id, roleId) }
    }

    @Test
    @WithIroha(AliceWithTestAssets::class)
    fun `find asset definitions with or filter`(): Unit = runBlocking {
        val filter = QueryFilters.or(listOf("${TEST_ASSET_DEFINITION_ID.name.string}#${TEST_ASSET_DEFINITION_ID.domainId.name.string}"))
        QueryBuilder.findAllAssetsDefinitions(filter)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assertEquals(1, assets.size)
                assertEquals(TEST_ASSET_DEFINITION_ID, assets[0].id)
            }
    }

    private suspend fun createAccount(name: String) {
        val newAccountId = AccountId(name.asName(), DEFAULT_DOMAIN_ID)
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerAccount(newAccountId, listOf())
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }
}
