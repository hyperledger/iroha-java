package jp.co.soramitsu.iroha2;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import jp.co.soramitsu.iroha2.client.Iroha2AsyncClient;
import jp.co.soramitsu.iroha2.client.blockstream.*;
import jp.co.soramitsu.iroha2.generated.*;
import jp.co.soramitsu.iroha2.generated.Metadata;
import jp.co.soramitsu.iroha2.query.QueryAndExtractor;
import jp.co.soramitsu.iroha2.query.QueryBuilder;
import jp.co.soramitsu.iroha2.testengine.DefaultGenesis;
import jp.co.soramitsu.iroha2.testengine.IrohaTest;
import jp.co.soramitsu.iroha2.testengine.WithIroha;
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder;
import kotlin.*;
import kotlin.Pair;
import kotlin.coroutines.*;
import kotlinx.coroutines.flow.*;
import static org.apache.commons.lang3.RandomStringUtils.random;
import org.jetbrains.annotations.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static jp.co.soramitsu.iroha2.testengine.TestConstsKt.ALICE_ACCOUNT_ID;
import static jp.co.soramitsu.iroha2.testengine.TestConstsKt.ALICE_KEYPAIR;
import static jp.co.soramitsu.iroha2.testengine.TestConstsKt.DEFAULT_ASSET_DEFINITION_ID;
import static jp.co.soramitsu.iroha2.testengine.TestConstsKt.DEFAULT_ASSET_ID;
import static jp.co.soramitsu.iroha2.testengine.TestConstsKt.DEFAULT_DOMAIN_ID;
import org.junit.jupiter.api.parallel.ResourceLock;

public class JavaTest extends IrohaTest<Iroha2AsyncClient> {

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void instructionFailed() {
        final VersionedSignedTransaction transaction = TransactionBuilder.Companion.builder()
            .account(ALICE_ACCOUNT_ID)
            .fail("FAIL MESSAGE")
            .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<byte[]> future = client.sendTransactionAsync(transaction);
        Assertions.assertThrows(ExecutionException.class, () -> future.get(getTxTimeout().getSeconds(), TimeUnit.SECONDS));
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void registerDomain() throws ExecutionException, InterruptedException, TimeoutException {
        final DomainId domainId = new DomainId(new Name("new_domain_name"));
        final VersionedSignedTransaction transaction = TransactionBuilder.Companion.builder()
            .account(ALICE_ACCOUNT_ID)
            .registerDomain(domainId)
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(transaction).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final QueryAndExtractor<Domain> query = QueryBuilder.findDomainById(domainId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Domain> future = client.sendQueryAsync(query);
        final Domain domain = future.get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);
        Assertions.assertEquals(domain.getId(), domainId);
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void registerAccount() throws Exception {
        final AccountId accountId = new AccountId(new Name("new_account"), DEFAULT_DOMAIN_ID);
        final VersionedSignedTransaction transaction = TransactionBuilder.Companion.builder()
            .account(ALICE_ACCOUNT_ID)
            .registerAccount(accountId, new ArrayList<>())
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(transaction).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final QueryAndExtractor<Account> query = QueryBuilder.findAccountById(accountId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Account> future = client.sendQueryAsync(query);
        final Account account = future.get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);
        Assertions.assertEquals(account.getId(), accountId);
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void mintAsset() throws Exception {
        final VersionedSignedTransaction registerAssetTx = TransactionBuilder.Companion.builder()
            .account(ALICE_ACCOUNT_ID)
            .registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, new AssetValueType.Quantity())
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(registerAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final VersionedSignedTransaction mintAssetTx = TransactionBuilder.Companion.builder()
            .account(ALICE_ACCOUNT_ID)
            .mintAsset(DEFAULT_ASSET_ID, 5)
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(mintAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final QueryAndExtractor<Account> query = QueryBuilder.findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Account> future = client.sendQueryAsync(query);
        final Account account = future.get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);
        final AssetValue value = account.getAssets().get(DEFAULT_ASSET_ID).getValue();
        Assertions.assertEquals(5, ((AssetValue.Quantity) value).getU32());
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void updateKeyValue() throws Exception {
        final Name assetMetadataKey = new Name("asset_metadata_key");
        final Value.String assetMetadataValue = new Value.String("some string value");
        final Value.String assetMetadataValue2 = new Value.String("some string value 2");
        final Metadata metadata = new Metadata(new HashMap<Name, Value>() {{
            put(assetMetadataKey, assetMetadataValue);
        }});

        final VersionedSignedTransaction registerAssetTx = TransactionBuilder.Companion.builder()
            .account(ALICE_ACCOUNT_ID)
            .registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, new AssetValueType.Store(), metadata)
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(registerAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final AssetId assetId = new AssetId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID);
        final VersionedSignedTransaction keyValueTx = TransactionBuilder.Companion.builder()
            .account(ALICE_ACCOUNT_ID)
            .setKeyValue(assetId, assetMetadataKey, assetMetadataValue2)
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(keyValueTx).get(30, TimeUnit.SECONDS);

        final QueryAndExtractor<Value> assetDefinitionValueQuery = QueryBuilder
            .findAssetKeyValueByIdAndKey(assetId, assetMetadataKey)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Value> future = client.sendQueryAsync(assetDefinitionValueQuery);

        final Value value = future.get(30, TimeUnit.SECONDS);
        Assertions.assertEquals(((Value.String) value).getString(), assetMetadataValue2.getString());
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void setKeyValue() throws Exception {
        final Value.String assetValue = new Value.String("some string value");
        final Name assetKey = new Name("asset_metadata_key");

        final VersionedSignedTransaction registerAssetTx = TransactionBuilder.Companion.builder()
            .account(ALICE_ACCOUNT_ID)
            .registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, new AssetValueType.Store())
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(registerAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final VersionedSignedTransaction keyValueTx = TransactionBuilder.Companion.builder()
            .account(ALICE_ACCOUNT_ID)
            .setKeyValue(DEFAULT_ASSET_DEFINITION_ID, assetKey, assetValue)
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(keyValueTx).get(10, TimeUnit.SECONDS);

        final QueryAndExtractor<Value> assetDefinitionValueQuery = QueryBuilder
            .findAssetDefinitionKeyValueByIdAndKey(DEFAULT_ASSET_DEFINITION_ID, assetKey)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Value> future = client.sendQueryAsync(assetDefinitionValueQuery);

        final Value value = future.get(10, TimeUnit.SECONDS);
        Assertions.assertEquals(((Value.String) value).getString(), assetValue.getString());
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    @ResourceLock("blockStream")
    public void blockStreaming() throws ExecutionException, InterruptedException {
        int count = 5;
        Pair<UUID, BlockStreamSubscription> idToSubscription = client.subscribeToBlockStreamBlocking(1, count);
        UUID actionId = idToSubscription.component1();
        BlockStreamSubscription subscription = idToSubscription.component2();

        List<VersionedBlockMessage> blocks = new ArrayList<>();
        subscription.receive(actionId, new BlockMessageCollector(blocks));

        for (int i = 0; i <= count + 1; i++) {
            final VersionedSignedTransaction transaction = TransactionBuilder.Companion.builder()
                .account(ALICE_ACCOUNT_ID)
                .setKeyValue(ALICE_ACCOUNT_ID, new Name(random(10)), new Value.String(random(10)))
                .buildSigned(ALICE_KEYPAIR);
            client.sendTransactionAsync(transaction);
        }

        QueryAndExtractor<List<VersionedCommittedBlock>> query = QueryBuilder.findAllBlocks()
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR);
        Integer blocksSize = client.sendQueryAsync(query).get().size();

        Assertions.assertEquals(blocksSize, blocks.size());

        subscription.stopBlocking();
    }

    static class BlockMessageCollector implements FlowCollector<VersionedBlockMessage> {

        List<VersionedBlockMessage> blocks;

        public BlockMessageCollector(List<VersionedBlockMessage> blocks) {
            this.blocks = blocks;
        }

        @Nullable
        @Override
        public Object emit(
            VersionedBlockMessage versionedBlockMessage,
            @NotNull Continuation<? super Unit> continuation
        ) {
            blocks.add(versionedBlockMessage);
            return null;
        }
    }
}
