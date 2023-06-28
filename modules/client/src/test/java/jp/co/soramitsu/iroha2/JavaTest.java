package jp.co.soramitsu.iroha2;

import jp.co.soramitsu.iroha2.client.*;
import jp.co.soramitsu.iroha2.generated.*;
import jp.co.soramitsu.iroha2.query.*;
import jp.co.soramitsu.iroha2.testengine.*;
import jp.co.soramitsu.iroha2.transaction.*;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.*;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;

import static jp.co.soramitsu.iroha2.testengine.TestConstsKt.*;

public class JavaTest extends IrohaTest<Iroha2AsyncClient> {

    public JavaTest() {
        super(Duration.ofSeconds(30), Network.newNetwork(), ALICE_ACCOUNT_ID, ALICE_KEYPAIR);
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void instructionFailed() {
        final VersionedSignedTransaction transaction = TransactionBuilder.Companion
                .builder()
                .account(ALICE_ACCOUNT_ID)
                .fail("FAIL MESSAGE")
                .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<byte[]> future = client.sendTransactionAsync(transaction);
        Assertions.assertThrows(ExecutionException.class,
                () -> future.get(getTxTimeout().getSeconds(), TimeUnit.SECONDS)
        );
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void registerDomainInstructionCommitted() throws ExecutionException, InterruptedException, TimeoutException {
        final DomainId domainId = new DomainId(new Name("new_domain_name"));
        final VersionedSignedTransaction transaction = TransactionBuilder.Companion
                .builder()
                .account(ALICE_ACCOUNT_ID)
                .registerDomain(domainId)
                .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(transaction).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final QueryAndExtractor<Domain> query = QueryBuilder
                .findDomainById(domainId)
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Domain> future = client.sendQueryAsync(query);
        final Domain domain = future.get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);
        Assertions.assertEquals(domain.getId(), domainId);
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void registerAccountInstructionCommitted() throws Exception {
        final AccountId accountId = new AccountId(
                new Name("new_account"),
                DEFAULT_DOMAIN_ID
        );
        final VersionedSignedTransaction transaction = TransactionBuilder.Companion
                .builder()
                .account(ALICE_ACCOUNT_ID)
                .registerAccount(accountId, new ArrayList<>())
                .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(transaction).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final QueryAndExtractor<Account> query = QueryBuilder
                .findAccountById(accountId)
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Account> future = client.sendQueryAsync(query);
        final Account account = future.get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);
        Assertions.assertEquals(account.getId(), accountId);
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void mintAssetInstructionCommitted() throws Exception {
        final VersionedSignedTransaction registerAssetTx = TransactionBuilder.Companion
                .builder()
                .account(ALICE_ACCOUNT_ID)
                .registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, new AssetValueType.Quantity())
                .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(registerAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final VersionedSignedTransaction mintAssetTx = TransactionBuilder.Companion
                .builder()
                .account(ALICE_ACCOUNT_ID)
                .mintAsset(DEFAULT_ASSET_ID, 5)
                .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(mintAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final QueryAndExtractor<Account> query = QueryBuilder
                .findAccountById(ALICE_ACCOUNT_ID)
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Account> future = client.sendQueryAsync(query);
        final Account account = future.get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);
        final AssetValue value = account.getAssets().get(DEFAULT_ASSET_ID).getValue();
        Assertions.assertEquals(5, ((AssetValue.Quantity) value).getU32());
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void updateKeyValueInstructionCommitted() throws Exception {
        final Name assetMetadataKey = new Name("asset_metadata_key");
        final Value.String assetMetadataValue = new Value.String("some string value");
        final Value.String assetMetadataValue2 = new Value.String("some string value 2");
        final Metadata metadata = new Metadata(new HashMap<Name, Value>() {{
            put(assetMetadataKey, assetMetadataValue);
        }});

        final VersionedSignedTransaction registerAssetTx = TransactionBuilder.Companion
                .builder()
                .account(ALICE_ACCOUNT_ID)
                .registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, new AssetValueType.Store(), metadata)
                .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(registerAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final AssetId assetId = new AssetId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID);
        final VersionedSignedTransaction keyValueTx = TransactionBuilder.Companion
                .builder()
                .account(ALICE_ACCOUNT_ID)
                .setKeyValue(
                        assetId,
                        assetMetadataKey,
                        assetMetadataValue2
                ).buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(keyValueTx).get(30, TimeUnit.SECONDS);

        final QueryAndExtractor<Value> assetDefinitionValueQuery = QueryBuilder
                .findAssetKeyValueByIdAndKey(assetId, assetMetadataKey)
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Value> future = client.sendQueryAsync(assetDefinitionValueQuery);

        final Value value = future.get(30, TimeUnit.SECONDS);
        Assertions.assertEquals(
                ((Value.String) value).getString(),
                assetMetadataValue2.getString()
        );
    }

    @Test
    @WithIroha(sources = DefaultGenesis.class)
    public void setKeyValueInstructionCommitted() throws Exception {
        final Value.String assetValue = new Value.String("some string value");
        final Name assetKey = new Name("asset_metadata_key");

        final VersionedSignedTransaction registerAssetTx = TransactionBuilder.Companion
                .builder()
                .account(ALICE_ACCOUNT_ID)
                .registerAssetDefinition(DEFAULT_ASSET_DEFINITION_ID, new AssetValueType.Store())
                .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(registerAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final VersionedSignedTransaction keyValueTx = TransactionBuilder.Companion
                .builder()
                .account(ALICE_ACCOUNT_ID)
                .setKeyValue(
                        DEFAULT_ASSET_DEFINITION_ID,
                        assetKey,
                        assetValue
                ).buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(keyValueTx).get(10, TimeUnit.SECONDS);

        final QueryAndExtractor<Value> assetDefinitionValueQuery = QueryBuilder
                .findAssetDefinitionKeyValueByIdAndKey(DEFAULT_ASSET_DEFINITION_ID, assetKey)
                .account(ALICE_ACCOUNT_ID)
                .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Value> future = client.sendQueryAsync(assetDefinitionValueQuery);

        final Value value = future.get(10, TimeUnit.SECONDS);
        Assertions.assertEquals(
                ((Value.String) value).getString(),
                assetValue.getString()
        );
    }
}
