package jp.co.soramitsu.iroha2

import java.time.Instant
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.name.Name
import jp.co.soramitsu.iroha2.generated.datamodel.pagination.Pagination
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.GenericValuePredicateBox
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.string.StringPredicate
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.value.Container
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.value.ValueOfKey
import jp.co.soramitsu.iroha2.generated.datamodel.predicate.value.ValuePredicate
import jp.co.soramitsu.iroha2.generated.datamodel.sorting.Sorting
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.TransactionValue
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedSignedTransaction
import jp.co.soramitsu.iroha2.query.QueryBuilder
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.ALICE_ACCOUNT_NAME
import jp.co.soramitsu.iroha2.testengine.ALICE_KEYPAIR
import jp.co.soramitsu.iroha2.testengine.AliceCanMintXor
import jp.co.soramitsu.iroha2.testengine.AliceHas100XorAndPermissionToBurn
import jp.co.soramitsu.iroha2.testengine.AliceHasRoleWithAccessToBobsMetadata
import jp.co.soramitsu.iroha2.testengine.AliceWithTestAssets
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT_ID
import jp.co.soramitsu.iroha2.testengine.BOB_ACCOUNT_NAME
import jp.co.soramitsu.iroha2.testengine.DEFAULT_ASSET_DEFINITION_ID
import jp.co.soramitsu.iroha2.testengine.DEFAULT_ASSET_ID
import jp.co.soramitsu.iroha2.testengine.DEFAULT_DOMAIN_ID
import jp.co.soramitsu.iroha2.testengine.DefaultGenesis
import jp.co.soramitsu.iroha2.testengine.IrohaTest
import jp.co.soramitsu.iroha2.testengine.NewAccountWithMetadata
import jp.co.soramitsu.iroha2.testengine.NewDomain
import jp.co.soramitsu.iroha2.testengine.NewDomainWithMetadata
import jp.co.soramitsu.iroha2.testengine.StoreAssetWithMetadata
import jp.co.soramitsu.iroha2.testengine.VAL_DEFINITION_ID
import jp.co.soramitsu.iroha2.testengine.WithExecutableTrigger
import jp.co.soramitsu.iroha2.testengine.WithIroha
import jp.co.soramitsu.iroha2.testengine.XOR_DEFINITION_ID
import jp.co.soramitsu.iroha2.testengine.XorAndValAssets
import jp.co.soramitsu.iroha2.transaction.QueryFilters
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.time.withTimeout
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class QueriesTest : IrohaTest<Iroha2Client>(account = ALICE_ACCOUNT_ID, keyPair = ALICE_KEYPAIR) {

    @Test
    @WithIroha([NewAccountWithMetadata::class])
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
    @WithIroha([NewAccountWithMetadata::class])
    fun `find all accounts with filter`(): Unit = runBlocking {
        val filter = QueryFilters.or(
            StringPredicate.Is("alice@wonderland"),
            StringPredicate.Is("bob@wonderland")
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
    @WithIroha([DefaultGenesis::class])
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
    @WithIroha([DefaultGenesis::class])
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
    @WithIroha([NewAccountWithMetadata::class])
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
    @WithIroha([DefaultGenesis::class])
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
    @WithIroha([XorAndValAssets::class])
    fun `find accounts with asset`(): Unit = runBlocking {
        QueryBuilder.findAccountsWithAsset(XOR_DEFINITION_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .let { accounts ->
                accounts.all { account ->
                    account.assets.any { it.key.definitionId == XOR_DEFINITION_ID }
                }
            }.also { assert(it) }
    }

    @Test
    @WithIroha([XorAndValAssets::class, AliceCanMintXor::class])
    fun `find total asset quantity by AssetDefinitionId`(): Unit = runBlocking {
        val quantity = 10
        client.tx { mintAsset(AssetId(XOR_DEFINITION_ID, BOB_ACCOUNT_ID), quantity) }

        QueryBuilder.findTotalAssetQuantityByAssetDefinitionId(XOR_DEFINITION_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { assertEquals(quantity + XorAndValAssets.XOR_QUANTITY, it.toInt()) }
    }

    @Test
    @WithIroha([XorAndValAssets::class])
    fun `find all assets`(): Unit = runBlocking {
        QueryBuilder.findAllAssets()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.any { it.id.definitionId == XOR_DEFINITION_ID })
                assert(assets.any { it.id.definitionId == VAL_DEFINITION_ID })
            }
    }

    @Test
    @WithIroha([XorAndValAssets::class])
    fun `find assets by name`(): Unit = runBlocking {
        QueryBuilder.findAssetsByName(XOR_DEFINITION_ID.name)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.all { it.id.definitionId.name == XOR_DEFINITION_ID.name })
            }
    }

    @Test
    @WithIroha([XorAndValAssets::class])
    fun `find assets by account id`(): Unit = runBlocking {
        QueryBuilder.findAssetsByAccountId(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.all { it.id.accountId == ALICE_ACCOUNT_ID })
                assert(assets.any { it.id.definitionId == XOR_DEFINITION_ID })
                assert(assets.any { it.id.definitionId == VAL_DEFINITION_ID })
            }
    }

    @Test
    @WithIroha([XorAndValAssets::class])
    fun `find assets by domain name and asset definition id`(): Unit = runBlocking {
        QueryBuilder.findAssetsByDomainIdAndAssetDefinitionId(
            DEFAULT_DOMAIN_ID,
            XOR_DEFINITION_ID
        )
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assert(assets.all { it.id.definitionId == XOR_DEFINITION_ID })
                assert(assets.all { it.id.accountId.domainId == DEFAULT_DOMAIN_ID })
            }
    }

    @Test
    @WithIroha([XorAndValAssets::class])
    fun `find asset quantity by id`(): Unit = runBlocking {
        val assetId = AssetId(XOR_DEFINITION_ID, ALICE_ACCOUNT_ID)
        QueryBuilder.findAssetQuantityById(assetId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { quantity ->
                assert(quantity == XorAndValAssets.XOR_QUANTITY.toLong())
            }
    }

    @Test
    @WithIroha([StoreAssetWithMetadata::class])
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
    @WithIroha([StoreAssetWithMetadata::class])
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
    @WithIroha([StoreAssetWithMetadata::class])
    @Disabled // https://github.com/hyperledger/iroha/issues/2697
    fun `find asset by metadata filters`(): Unit = runBlocking {
        val filter = GenericValuePredicateBox.Raw(
            ValuePredicate.Container(
                Container.ValueOfKey(
                    ValueOfKey(
                        StoreAssetWithMetadata.ASSET_KEY,
                        ValuePredicate.Identifiable(
                            StringPredicate.Is(
                                StoreAssetWithMetadata.ASSET_VALUE.string
                            )
                        )
                    )
                )
            )
        )
        QueryBuilder.findAllAssets(filter)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also {
                assert(it.isNotEmpty())
            }
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
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
    @WithIroha([NewDomain::class])
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
    @WithIroha([NewDomain::class])
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
    @WithIroha([DefaultGenesis::class])
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
    @WithIroha([DefaultGenesis::class])
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
    @WithIroha([DefaultGenesis::class])
    fun `find transactions by account id`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
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
                        .versionedSignedTransaction
                        .cast<VersionedSignedTransaction.V1>()
                        .signedTransaction
                        .payload
                        .accountId == ALICE_ACCOUNT_ID
                }
            }.also {
                assert(it)
            }
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    fun `find permission tokens by account id`(): Unit = runBlocking {
        QueryBuilder.findPermissionTokensByAccountId(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.let { tokens ->
                tokens.any {
                    it.params[IdKey.AssetDefinitionId.type.asName()]
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
    @WithIroha([DefaultGenesis::class])
    fun `find transaction by hash`(): Unit = runBlocking {
        val hash = client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            buildSigned(ALICE_KEYPAIR)
        }.let { d ->
            withTimeout(txTimeout) { d.await() }
        }
        QueryBuilder.findTransactionByHash(hash)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .cast<TransactionValue.Transaction>()
            .versionedSignedTransaction.hash()
            .also { assertContentEquals(hash, it) }
    }

    @Test
    @WithIroha([NewDomainWithMetadata::class])
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
    @WithIroha([WithExecutableTrigger::class])
    fun `find trigger by ID`(): Unit = runBlocking {
        val triggerId = WithExecutableTrigger.TRIGGER_ID

        QueryBuilder.findTriggerById(triggerId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { trigger -> assertTrue { trigger.id == triggerId } }
    }

    @Test
    @WithIroha([WithExecutableTrigger::class])
    fun `find triggers by domain ID`(): Unit = runBlocking {
        val domainId = WithExecutableTrigger.TRIGGER_ID.domainId!!
        QueryBuilder.findTriggersByDomainId(domainId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { triggers -> assert(triggers.all { it.id.domainId == domainId }) }
    }

    @Test
    @WithIroha([WithExecutableTrigger::class])
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
    @WithIroha([DefaultGenesis::class])
    fun `pagination plus sorting by metadata key`(): Unit = runBlocking {
        val keyU32 = RandomStringUtils.randomAlphabetic(5).asName()
        val keyU128 = RandomStringUtils.randomAlphabetic(5).asName()

        createAccount("new_000", mapOf(keyU32 to 1.asValue(), keyU128 to 1L.asValue()))
        createAccount("new_111", mapOf(keyU32 to 0.asValue(), keyU128 to 0L.asValue()))
        createAccount("new_222", mapOf(keyU32 to 2.asValue(), keyU128 to 2L.asValue()))

        listOf(keyU32, keyU128).forEach { key ->
            QueryBuilder.findAllAccounts(QueryFilters.startsWith("new_"))
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR)
                .let { query -> client.sendQuery(query, sorting = Sorting(key)) }
                .let { accounts ->
                    assertEquals(if (key == keyU32) 0.asValue() else 0L.asValue(), accounts.data[0].metadata.map[key])
                    assertEquals(if (key == keyU32) 1.asValue() else 1L.asValue(), accounts.data[1].metadata.map[key])
                    assertEquals(if (key == keyU32) 2.asValue() else 2L.asValue(), accounts.data[2].metadata.map[key])
                }
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    fun `pagination works correct after inserting some new accounts`(): Unit = runBlocking {
        val key = "ts".asName()

        val metadata0 = Instant.now().toEpochMilli().asValue()
        createAccount("new_000", mapOf(key to metadata0))
        val metadata1 = Instant.now().toEpochMilli().asValue()
        createAccount("new_111", mapOf(key to metadata1))
        val metadata2 = Instant.now().toEpochMilli().asValue()
        createAccount("new_222", mapOf(key to metadata2))
        val metadata3 = Instant.now().toEpochMilli().asValue()
        createAccount("new_333", mapOf(key to metadata3))
        val metadata4 = Instant.now().toEpochMilli().asValue()
        createAccount("new_444", mapOf(key to metadata4))

        QueryBuilder.findAllAccounts(QueryFilters.startsWith("new_"))
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query, Pagination(0, 3), Sorting(key)) }
            .let { accounts ->
                assertEquals(3, accounts.data.size)
                assertEquals(metadata2, accounts.data[2].metadata.map[key])
            }

        QueryBuilder.findAllAccounts(QueryFilters.startsWith("new_"))
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query, Pagination(3, 3)) }
            .let { accounts ->
                assertEquals(2, accounts.data.size)
                assertEquals(metadata4, accounts.data[1].metadata.map[key])
            }

        val metadata5 = Instant.now().toEpochMilli().asValue()
        createAccount("new_555", mapOf(key to metadata5))
        val metadata6 = Instant.now().toEpochMilli().asValue()
        createAccount("new_666", mapOf(key to metadata6))
        val metadata7 = Instant.now().toEpochMilli().asValue()
        createAccount("new_777", mapOf(key to metadata7))
        val metadata8 = Instant.now().toEpochMilli().asValue()
        createAccount("new_888", mapOf(key to metadata8))

        QueryBuilder.findAllAccounts(QueryFilters.startsWith("new_"))
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query, Pagination(6, 3)) }
            .let { accounts ->
                assertEquals(3, accounts.data.size)
                assertEquals(metadata6, accounts.data[0].metadata.map[key])
                assertEquals(metadata8, accounts.data[2].metadata.map[key])
            }
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    fun `find all account with pagination`(): Unit = runBlocking {
        var page = Pagination(0, 5)
        var accounts = QueryBuilder.findAllAccounts()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query, page) }
        assertEquals(3, accounts.data.size)
        assertEquals(page, accounts.pagination)
        assertEquals(3, accounts.total.toInt())

        createAccount("foo")
        createAccount("bar")

        page = Pagination(3, 5)
        accounts = QueryBuilder.findAllAccounts()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query, page) }
        assertEquals(2, accounts.data.size)
        assertEquals(page, accounts.pagination)
        assertEquals(5, accounts.total.toInt())
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
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
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
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
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    fun `find all block headers`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            burnAsset(DEFAULT_ASSET_ID, 1)
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
        QueryBuilder.findAllBlockHeaders()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { headers ->
                assertTrue(headers.size == 2)
            }
    }

    @Test
    @WithIroha([AliceHasRoleWithAccessToBobsMetadata::class])
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
    @WithIroha([AliceHasRoleWithAccessToBobsMetadata::class])
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
    @WithIroha([AliceHasRoleWithAccessToBobsMetadata::class])
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
    @WithIroha([AliceHasRoleWithAccessToBobsMetadata::class])
    fun `find role by ID`(): Unit = runBlocking {
        val roleId = AliceHasRoleWithAccessToBobsMetadata.ROLE_ID
        try {
            QueryBuilder.findRoleByRoleId(roleId)
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR)
                .let { query -> client.sendQuery(query) }
                .also { role -> assertEquals(role.id, roleId) }
        } catch (e: Exception) {
            println(e)
        }
    }

    @Test
    @WithIroha([AliceWithTestAssets::class])
    fun `find asset definitions with or filter`(): Unit = runBlocking {
        val definitionId = AliceWithTestAssets.TEST_ASSET_DEFINITION_ID
        val filter = QueryFilters.or(
            StringPredicate.Is("${definitionId.name.string}#${definitionId.domainId.name.string}")
        )
        QueryBuilder.findAllAssetsDefinitions(filter)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }.also { assets ->
                assertEquals(1, assets.size)
                assertEquals(definitionId, assets[0].id)
            }
    }

    private suspend fun createAccount(
        name: String,
        metadata: Map<Name, Value> = mapOf()
    ) {
        val newAccountId = AccountId(name.asName(), DEFAULT_DOMAIN_ID)
        client.sendTransaction {
            accountId = ALICE_ACCOUNT_ID
            registerAccount(newAccountId, listOf(), Metadata(metadata))
            buildSigned(ALICE_KEYPAIR)
        }.also { d ->
            withTimeout(txTimeout) { d.await() }
        }
    }
}
