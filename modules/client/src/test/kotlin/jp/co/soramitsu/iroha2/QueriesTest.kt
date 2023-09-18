package jp.co.soramitsu.iroha2

import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import jp.co.soramitsu.iroha2.annotations.Permission
import jp.co.soramitsu.iroha2.annotations.Query
import jp.co.soramitsu.iroha2.annotations.Sdk
import jp.co.soramitsu.iroha2.annotations.SdkTestId
import jp.co.soramitsu.iroha2.client.Iroha2Client
import jp.co.soramitsu.iroha2.generated.AccountId
import jp.co.soramitsu.iroha2.generated.AssetId
import jp.co.soramitsu.iroha2.generated.AssetValueType
import jp.co.soramitsu.iroha2.generated.Container
import jp.co.soramitsu.iroha2.generated.GenericPredicateBox
import jp.co.soramitsu.iroha2.generated.InstructionBox
import jp.co.soramitsu.iroha2.generated.Metadata
import jp.co.soramitsu.iroha2.generated.Name
import jp.co.soramitsu.iroha2.generated.StringPredicate
import jp.co.soramitsu.iroha2.generated.TransactionValue
import jp.co.soramitsu.iroha2.generated.Value
import jp.co.soramitsu.iroha2.generated.ValueOfKey
import jp.co.soramitsu.iroha2.generated.ValuePredicate
import jp.co.soramitsu.iroha2.generated.VersionedSignedTransaction
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
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Owner("akostyuchenko")
@Sdk("Java/Kotlin")
@Permission("no_permission_required")
class QueriesTest : IrohaTest<Iroha2Client>() {

    @Test
    @WithIroha([NewAccountWithMetadata::class])
    @Feature("Accounts")
    @Query("FindAllAccounts")
    @Story("Account queries all accounts")
    @SdkTestId("find_all_accounts")
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
    @Feature("Accounts")
    @Query("FindAllAccountsWithFilter")
    @Story("Account queries all accounts with a filter")
    @SdkTestId("find_all_accounts_with_filter")
    fun `find all accounts with filter`(): Unit = runBlocking {
        val filter = QueryFilters.or(
            StringPredicate.Is("alice@wonderland"),
            StringPredicate.Is("bob@wonderland"),
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
    @Feature("Accounts")
    @Query("FindAccountsByName")
    @Story("Account queries accounts by name")
    @SdkTestId("find_accounts_by_name")
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
    @Feature("Accounts")
    @Query("FindAccountsByNameWithFilter")
    @Story("Account queries accounts by name with a filter")
    @SdkTestId("find_accounts_by_name_with_filter")
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
    @Feature("Accounts")
    @Query("FindAccountKeyValueByIdAndKey")
    @Story("Account queries account key value by ID and key")
    @SdkTestId("find_account_key_value_by_ID_and_key")
    fun `find account key value by ID and key`(): Unit = runBlocking {
        QueryBuilder.findAccountKeyValueByIdAndKey(
            NewAccountWithMetadata.ACCOUNT_ID,
            NewAccountWithMetadata.KEY,
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
    @Feature("Accounts")
    @Query("FindAccountsByDomainId")
    @Story("Account queries accounts by domain ID")
    @SdkTestId("find_accounts_by_domain_ID")
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
    @Feature("Accounts")
    @Query("FindAccountsWithAsset")
    @Story("Account queries accounts with a specific asset")
    @SdkTestId("find_accounts_with_asset")
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
    @Feature("Assets")
    @Query("FindTotalAssetQuantityByAssetDefinitionId")
    @Story("Account queries total asset quantity by AssetDefinitionId")
    @SdkTestId("find_total_asset_quantity_by_AssetDefinitionId")
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
    @Feature("Assets")
    @Query("FindAllAssets")
    @Story("Account queries all assets")
    @SdkTestId("find_all_assets")
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
    @Feature("Assets")
    @Query("FindAssetsByName")
    @Story("Account queries assets by name")
    @SdkTestId("find_assets_by_name")
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
    @Feature("Assets")
    @Query("FindAssetsByAccountId")
    @Story("Account queries assets by account ID")
    @SdkTestId("find_assets_by_account_ID")
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
    @Feature("Assets")
    @Query("FindAssetsByDomainIdAndAssetDefinitionId")
    @Story("Account queries assets by domain name and asset definition ID")
    @SdkTestId("find_assets_by_domain_name_and_asset_definition_ID")
    fun `find assets by domain name and asset definition id`(): Unit = runBlocking {
        QueryBuilder.findAssetsByDomainIdAndAssetDefinitionId(
            DEFAULT_DOMAIN_ID,
            XOR_DEFINITION_ID,
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
    @Feature("Assets")
    @Query("FindAssetQuantityById")
    @Story("Account queries asset quantity by asset ID")
    @SdkTestId("find_asset_quantity_by_ID")
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
    @Feature("Assets")
    @Query("FindAssetKeyValueByIdAndKey")
    @Story("Account queries asset key value by ID and key")
    @SdkTestId("find_asset_key_value_by_ID_and_key")
    fun `find asset key value by ID and key`(): Unit = runBlocking {
        QueryBuilder.findAssetKeyValueByIdAndKey(
            StoreAssetWithMetadata.ASSET_ID,
            StoreAssetWithMetadata.ASSET_KEY,
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
    @Feature("Assets")
    @Query("FindAssetDefinitionKeyValueByIdAndKey")
    @Story("Account queries asset definition key value by ID and key")
    @SdkTestId("find_asset_definition_key_value_by_ID_and_key")
    fun `find asset definition key value by ID and key`(): Unit = runBlocking {
        QueryBuilder.findAssetDefinitionKeyValueByIdAndKey(
            StoreAssetWithMetadata.DEFINITION_ID,
            StoreAssetWithMetadata.ASSET_KEY,
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
    @Feature("Assets")
    @Query("FindAssetByMetadataFilters")
    @Story("Account queries asset by metadata filters")
    @SdkTestId("find_asset_by_metadata_filters")
    @Disabled // https://github.com/hyperledger/iroha/issues/2697
    fun `find asset by metadata filters`(): Unit = runBlocking {
        val filter = GenericPredicateBox.Raw(
            ValuePredicate.Container(
                Container.ValueOfKey(
                    ValueOfKey(
                        StoreAssetWithMetadata.ASSET_KEY,
                        ValuePredicate.Identifiable(
                            StringPredicate.Is(
                                StoreAssetWithMetadata.ASSET_VALUE.string,
                            ),
                        ),
                    ),
                ),
            ),
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
    @Feature("Assets")
    @Query("FindAssetDefinitionById")
    @Story("Account queries asset definition by ID")
    @SdkTestId("find_asset_definition_by_ID")
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
    @Feature("Domains")
    @Query("FindAllDomains")
    @Story("Account queries all domains")
    @SdkTestId("find_all_domains")
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
    @Feature("Domains")
    @Query("FindAllDomainsWithFilter")
    @Story("Account queries all domains with filter")
    @SdkTestId("find_all_domains_with_filter")
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
    @Feature("Domains")
    @Query("FindDomainById")
    @Story("Domain queries domain by ID")
    @SdkTestId("find_domain_by_ID")
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
    @Feature("Peers")
    @Query("FindAllPeers")
    @Story("Peer queries all peers")
    @SdkTestId("find_all_peers")
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
    @Feature("Transactions")
    @Query("FindTransactionsByAccountId")
    @Story("Transaction queries transactions by account id")
    @SdkTestId("find_transactions_by_account_id")
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
                    value.cast<TransactionValue>()
                        .value
                        .cast<VersionedSignedTransaction.V1>()
                        .signedTransaction
                        .payload
                        .authority == ALICE_ACCOUNT_ID
                }
            }.also {
                assert(it)
            }
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    @Feature("PermissionTokens")
    @Query("FindPermissionTokensByAccountId")
    @Story("PermissionToken queries permission tokens by account id")
    @SdkTestId("find_permission_tokens_by_account_id")
    fun `find permission tokens by account id`(): Unit = runBlocking {
        QueryBuilder.findPermissionTokensByAccountId(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query ->
                client.sendQuery(query)
            }
            .let { tokens ->
                tokens.any {
                    "{\"asset_definition_id\":\"xor#wonderland\"}" == it.payload.string
                }
            }.also {
                assert(it)
            }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Transactions")
    @Query("FindTransactionByHash")
    @Story("Transaction queries transaction by hash")
    @SdkTestId("find_transaction_by_hash")
    fun `find transaction by hash`(): Unit = runBlocking {
        client.sendTransaction {
            account(ALICE_ACCOUNT_ID)
            registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity())
            buildSigned(ALICE_KEYPAIR)
        }.let { d ->
            withTimeout(txTimeout) { d.await() }
        }

        val transactions = QueryBuilder.findAllTransactions()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }

        val hash = VersionedSignedTransaction.encode(transactions[2].transaction.value).hash()

        val txByHash = QueryBuilder.findTransactionByHash(hash)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
        assertEquals(
            DEFAULT_ASSET_DEFINITION_ID,
            txByHash.transaction.value
                .extractInstruction<InstructionBox.Register>()
                .registerBox.`object`.extractNewAssetDefinition().id,
        )
        txByHash.transaction.value
            .let { VersionedSignedTransaction.encode(it).hash() }
            .also { assertContentEquals(hash, it) }
    }

    @Test
    @WithIroha([NewDomainWithMetadata::class])
    @Feature("Domains")
    @Query("FindDomainKeyValueByIdAndKey")
    @Story("Domain queries domain key value by ID and key")
    @SdkTestId("find_domain_key_value_by_ID_and_key")
    fun `find domain key value by ID and key`(): Unit = runBlocking {
        QueryBuilder.findDomainKeyValueByIdAndKey(
            NewDomainWithMetadata.DOMAIN_ID,
            NewDomainWithMetadata.KEY,
        ).account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { assertEquals(NewDomainWithMetadata.VALUE, it) }
    }

    @Test
    @WithIroha([WithExecutableTrigger::class])
    @Feature("Triggers")
    @Query("FindTriggerById")
    @Story("Trigger queries trigger by ID")
    @SdkTestId("find_trigger_by_id")
    fun `find trigger by ID`(): Unit = runBlocking {
        val triggerId = WithExecutableTrigger.TRIGGER_ID

        QueryBuilder.findTriggerById(triggerId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { trigger -> assertTrue { trigger.id() == triggerId } }
    }

    @Test
    @WithIroha([WithExecutableTrigger::class])
    @Feature("Triggers")
    @Query("FindTriggersByDomainId")
    @Story("Trigger queries triggers by domain ID")
    @SdkTestId("find_triggers_by_domain_id")
    fun `find triggers by domain ID`(): Unit = runBlocking {
        val domainId = WithExecutableTrigger.TRIGGER_ID.domainId!!
        QueryBuilder.findTriggersByDomainId(domainId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { client.sendQuery(it) }
            .also { triggers -> assert(triggers.all { it.id().domainId == domainId }) }
    }

    @Test
    @WithIroha([WithExecutableTrigger::class])
    @Feature("Triggers")
    @Query("FindAllActiveTriggerIds")
    @Story("Trigger queries all active trigger IDs")
    @SdkTestId("find_all_active_trigger_ids")
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
    @Feature("Accounts")
    @Query("FindAllAccountsWithPaginationAndSorting")
    @Story("Account queries all accounts with pagination and sorting by metadata key")
    @SdkTestId("pagination_plus_sorting_by_metadata_key")
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
                .let { query -> client.sendQuery(query, sorting = key.string) }
                .let { accounts ->
                    assertEquals(if (key == keyU32) 0.asValue() else 0L.asValue(), accounts[0].metadata.map[key])
                    assertEquals(if (key == keyU32) 1.asValue() else 1L.asValue(), accounts[1].metadata.map[key])
                    assertEquals(if (key == keyU32) 2.asValue() else 2L.asValue(), accounts[2].metadata.map[key])
                }
        }
    }

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Accounts")
    @Query("FindAllAccountsWithPagination")
    @Story("Account queries all accounts with pagination after inserting some new accounts")
    @SdkTestId("pagination_works_correct_after_inserting_some_new_accounts")
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
            .let { query -> client.sendQuery(query, limit = 3, sorting = key.string) }
            .let { accounts ->
                assertEquals(3, accounts.size)
                assertEquals(metadata2, accounts[2].metadata.map[key])
            }

        QueryBuilder.findAllAccounts(QueryFilters.startsWith("new_"))
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query, 3, 3) }
            .let { accounts ->
                assertEquals(2, accounts.size)
                assertEquals(metadata4, accounts[1].metadata.map[key])
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
            .let { query -> client.sendQuery(query, 6, 3) }
            .let { accounts ->
                assertEquals(3, accounts.size)
                assertEquals(metadata6, accounts[0].metadata.map[key])
                assertEquals(metadata8, accounts[2].metadata.map[key])
            }
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    @Feature("Accounts")
    @Query("FindAllAccountsWithPagination")
    @Story("Account queries all accounts with pagination")
    @SdkTestId("find_all_account_with_pagination")
    fun `find all account with pagination`(): Unit = runBlocking {
        var accounts = QueryBuilder.findAllAccounts()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query, limit = 5) }
        assertEquals(3, accounts.size)

        createAccount("foo")
        createAccount("bar")

        accounts = QueryBuilder.findAllAccounts()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query, 3, 5) }
        assertEquals(2, accounts.size)
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    @Feature("Transactions")
    @Query("FindAllTransactions")
    @Story("Account queries all transactions")
    @SdkTestId("find_all_transactions")
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
                assertTrue(txs.size == 7) // 5 + 2 genesis txs
            }
    }

    @Test
    @WithIroha([AliceHas100XorAndPermissionToBurn::class])
    @Feature("Blocks")
    @Query("FindAllBlocks")
    @Story("Account queries all blocks")
    @SdkTestId("find_all_blocks")
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
    @Feature("Blocks")
    @Query("FindAllBlockHeaders")
    @Story("Account queries all block headers")
    @SdkTestId("find_all_block_headers")
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
    @Feature("Roles")
    @Query("FindAllRoleIds")
    @Story("Account queries all role IDs")
    @SdkTestId("find_all_role_IDs")
    fun `find all role IDs`(): Unit = runBlocking {
        QueryBuilder.findAllRoleIds()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { ids ->
                assertContains(
                    ids,
                    AliceHasRoleWithAccessToBobsMetadata.ROLE_ID,
                )
            }
    }

    @Test
    @WithIroha([AliceHasRoleWithAccessToBobsMetadata::class])
    @Feature("Roles")
    @Query("FindRolesByAccountId")
    @Story("Account queries roles by account ID")
    @SdkTestId("find_roles_by_account_ID")
    fun `find roles by account ID`(): Unit = runBlocking {
        QueryBuilder.findRolesByAccountId(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { ids ->
                assertContains(ids, AliceHasRoleWithAccessToBobsMetadata.ROLE_ID)
            }
    }

    @Test
    @WithIroha([AliceHasRoleWithAccessToBobsMetadata::class])
    @Feature("Roles")
    @Query("FindAllRoles")
    @Story("Account queries all roles")
    @SdkTestId("find_all_roles")
    fun `find all roles`(): Unit = runBlocking {
        QueryBuilder.findAllRoles()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
            .also { roles ->
                assertContains(
                    roles.map { it.id },
                    AliceHasRoleWithAccessToBobsMetadata.ROLE_ID,
                )
            }
    }

    @Test
    @WithIroha([AliceHasRoleWithAccessToBobsMetadata::class])
    @Feature("Roles")
    @Query("FindRoleByRoleId")
    @Story("Account queries role by role ID")
    @SdkTestId("find_role_by_role_ID")
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
    @Feature("Assets")
    @Query("FindAssetDefinitionsWithOrFilter")
    @Story("Account queries asset definitions with or filter")
    @SdkTestId("find_asset_definitions_with_or_filter")
    fun `find asset definitions with or filter`(): Unit = runBlocking {
        val definitionId = AliceWithTestAssets.TEST_ASSET_DEFINITION_ID
        val filter = QueryFilters.or(
            StringPredicate.Is("${definitionId.name.string}#${definitionId.domainId.name.string}"),
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

    @Test
    @WithIroha([DefaultGenesis::class])
    @Feature("Permissions")
    @Query("FindPermissionTokenSchema")
    @Story("Transaction queries all permission token ids")
    @SdkTestId("find_all_permission_token_ids")
    fun `find all permission token ids`(): Unit = runBlocking {
        val permissionTokenSchema = QueryBuilder.findPermissionTokenIdsSchema()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR)
            .let { query -> client.sendQuery(query) }
        assertEquals(Permissions.values().size, permissionTokenSchema.tokenIds.size)

        val expectedPermissions = Permissions.values().map { it.type }.toList()
        assertTrue(permissionTokenSchema.tokenIds.containsAll(expectedPermissions))
    }

    private suspend fun createAccount(
        name: String,
        metadata: Map<Name, Value> = mapOf(),
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
