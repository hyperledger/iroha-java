package jp.co.soramitsu.iroha2;

import jp.co.soramitsu.iroha2.engine.DefaultGenesis;
import jp.co.soramitsu.iroha2.engine.IrohaRunnerExtension;
import jp.co.soramitsu.iroha2.engine.WithIroha;
import jp.co.soramitsu.iroha2.generated.datamodel.Name;
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue;
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType;
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Id;
import jp.co.soramitsu.iroha2.query.QueryBuilder;
import jp.co.soramitsu.iroha2.transaction.TransactionBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static jp.co.soramitsu.iroha2.engine.TestConstsKt.*;

@Disabled
@ExtendWith(IrohaRunnerExtension.class)
@Timeout(40)
public class JavaTest {

    public Iroha2Client client;

    @Test
    @WithIroha(genesis = DefaultGenesis.class)
    public void instructionFailed() {
        final var transaction = TransactionBuilder.Companion
            .builder()
            .account(ALICE_ACCOUNT_ID)
            .fail("FAIL MESSAGE")
            .buildSigned(ALICE_KEYPAIR);
        final var future = client.sendTransaction(transaction);
        Assertions.assertThrows(ExecutionException.class, () -> future.get(10, TimeUnit.SECONDS));
    }

    @Test
    @WithIroha(genesis = DefaultGenesis.class)
    public void registerDomainInstructionCommitted() throws ExecutionException, InterruptedException, TimeoutException {
        final var domainId = new Id(new Name("new_domain_name"));
        final var transaction = TransactionBuilder.Companion
            .builder()
            .account(ALICE_ACCOUNT_ID)
            .registerDomain(domainId)
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransaction(transaction).get(10, TimeUnit.SECONDS);

        final var query = QueryBuilder
            .findDomainById(domainId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR);
        final var future = client.sendQueryAsync(query);
        final var domain = future.get(10, TimeUnit.SECONDS);
        Assertions.assertEquals(domain.getId(), domainId);
    }

    @Test
    @WithIroha(genesis = DefaultGenesis.class)
    public void registerAccountInstructionCommitted() throws Exception {
        final var accountId = new jp.co.soramitsu.iroha2.generated.datamodel.account.Id(
            new Name("new_account"),
            DEFAULT_DOMAIN_ID
        );
        final var transaction = TransactionBuilder.Companion
            .builder()
            .account(ALICE_ACCOUNT_ID)
            .registerAccount(accountId, List.of())
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransaction(transaction).get(10, TimeUnit.SECONDS);

        final var query = QueryBuilder
            .findAccountById(accountId)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR);
        final var future = client.sendQueryAsync(query);
        final var account = future.get(10, TimeUnit.SECONDS);
        Assertions.assertEquals(account.getId(), accountId);
    }

    @Test
    @WithIroha(genesis = DefaultGenesis.class)
    public void mintAssetInstructionCommitted() throws Exception {
        final var registerAssetTx = TransactionBuilder.Companion
            .builder()
            .account(ALICE_ACCOUNT_ID)
            .registerAsset(DEFAULT_ASSET_DEFINITION_ID, new AssetValueType.Quantity())
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransaction(registerAssetTx).get(10, TimeUnit.SECONDS);

        final var mintAssetTx = TransactionBuilder.Companion
            .builder()
            .account(ALICE_ACCOUNT_ID)
            .mintAsset(DEFAULT_ASSET_ID, 5L)
            .buildSigned(ALICE_KEYPAIR);
        client.sendTransaction(mintAssetTx).get(10, TimeUnit.SECONDS);

        final var query = QueryBuilder
            .findAccountById(ALICE_ACCOUNT_ID)
            .account(ALICE_ACCOUNT_ID)
            .buildSigned(ALICE_KEYPAIR);
        final var future = client.sendQueryAsync(query);
        final var account = future.get(10, TimeUnit.SECONDS);
        final var value = account.getAssets().get(DEFAULT_ASSET_ID).getValue();
        Assertions.assertEquals(5, ((AssetValue.Quantity) value).getU32());
    }
}
