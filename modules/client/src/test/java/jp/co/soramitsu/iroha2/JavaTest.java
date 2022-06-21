package jp.co.soramitsu.iroha2;

import jp.co.soramitsu.iroha2.client.Iroha2AsyncClient;
import jp.co.soramitsu.iroha2.engine.DefaultGenesis;
import jp.co.soramitsu.iroha2.engine.IrohaTest;
import jp.co.soramitsu.iroha2.engine.WithIroha;
import jp.co.soramitsu.iroha2.generated.datamodel.Name;
import jp.co.soramitsu.iroha2.generated.datamodel.Value;
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account;
import jp.co.soramitsu.iroha2.generated.datamodel.account.AccountId;
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetId;
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue;
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType;
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain;
import jp.co.soramitsu.iroha2.generated.datamodel.domain.DomainId;
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata;
import jp.co.soramitsu.iroha2.generated.datamodel.transaction.VersionedTransaction;
import jp.co.soramitsu.iroha2.query.QueryAndExtractor;
import jp.co.soramitsu.iroha2.query.QueryBuilder;
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static jp.co.soramitsu.iroha2.engine.TestConstsKt.*;

public class JavaTest extends IrohaTest<Iroha2AsyncClient> {

    @Test
    @WithIroha(genesis = DefaultGenesis.class)
    public void instructionFailed() {
        final VersionedTransaction transaction = TransactionBuilder.Companion
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
    @WithIroha(genesis = DefaultGenesis.class)
    public void registerDomainInstructionCommitted() throws ExecutionException, InterruptedException, TimeoutException {
        final DomainId domainId = new DomainId(new Name("new_domain_name"));
        final VersionedTransaction transaction = TransactionBuilder.Companion
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
    @WithIroha(genesis = DefaultGenesis.class)
    public void registerAccountInstructionCommitted() throws Exception {
        final AccountId accountId = new AccountId(
            new Name("new_account"),
            DEFAULT_DOMAIN_ID
        );
        final VersionedTransaction transaction = TransactionBuilder.Companion
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
    @WithIroha(genesis = DefaultGenesis.class)
    public void mintAssetInstructionCommitted() throws Exception {
        final VersionedTransaction registerAssetTx = TransactionBuilder.Companion
            .builder()
            .account(ALICE_ACCOUNT_ID)
            .registerAsset(DEFAULT_ASSET_DEFINITION_ID, new AssetValueType.Quantity())
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(registerAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final VersionedTransaction mintAssetTx = TransactionBuilder.Companion
            .builder()
            .account(ALICE_ACCOUNT_ID)
            .mintAsset(DEFAULT_ASSET_ID, 5L)
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
    @WithIroha(genesis = DefaultGenesis.class)
    public void updateKeyValueInstructionCommitted() throws Exception {
        final Name assetMetadataKey = new Name("asset_metadata_key");
        final Value.String assetMetadataValue = new Value.String("some string value");
        final Value.String assetMetadataValue2 = new Value.String("some string value 2");
        final Metadata metadata = new Metadata(new HashMap<Name, Value>() {{
            put(assetMetadataKey, assetMetadataValue);
        }});

        final VersionedTransaction registerAssetTx = TransactionBuilder.Companion
            .builder()
            .account(ALICE_ACCOUNT_ID)
            .registerAsset(DEFAULT_ASSET_DEFINITION_ID, new AssetValueType.Store(), metadata)
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(registerAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final AssetId assetId = new AssetId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID);
        final VersionedTransaction keyValueTx = TransactionBuilder.Companion
            .builder()
            .account(ALICE_ACCOUNT_ID)
            .setKeyValue(
                assetId,
                assetMetadataKey,
                assetMetadataValue2
            ).buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(keyValueTx).get(10, TimeUnit.SECONDS);

        final QueryAndExtractor<Value> assetDefinitionValueQuery = QueryBuilder
            .findAssetKeyValueByIdAndKey(assetId, assetMetadataKey)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR);
        final CompletableFuture<Value> future = client.sendQueryAsync(assetDefinitionValueQuery);

        final Value value = future.get(10, TimeUnit.SECONDS);
        Assertions.assertEquals(
            ((Value.String) value).getString(),
            assetMetadataValue2.getString()
        );
    }

    @Test
    @WithIroha(genesis = DefaultGenesis.class)
    public void setKeyValueInstructionCommitted() throws Exception {
        final Value.String assetValue = new Value.String("some string value");
        final Name assetKey = new Name("asset_metadata_key");

        final VersionedTransaction registerAssetTx = TransactionBuilder.Companion
            .builder()
            .account(ALICE_ACCOUNT_ID)
            .registerAsset(DEFAULT_ASSET_DEFINITION_ID, new AssetValueType.Store())
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransactionAsync(registerAssetTx).get(getTxTimeout().getSeconds(), TimeUnit.SECONDS);

        final VersionedTransaction keyValueTx = TransactionBuilder.Companion
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
