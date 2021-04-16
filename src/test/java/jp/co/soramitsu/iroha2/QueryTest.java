package jp.co.soramitsu.iroha2;

import java.security.KeyPair;
import jp.co.soramitsu.iroha2.model.Account;
import jp.co.soramitsu.iroha2.model.AccountId;
import jp.co.soramitsu.iroha2.model.Asset;
import jp.co.soramitsu.iroha2.model.AssetId;
import jp.co.soramitsu.iroha2.model.DefinitionId;
import jp.co.soramitsu.iroha2.model.Domain;
import jp.co.soramitsu.iroha2.model.Id;
import jp.co.soramitsu.iroha2.model.Identifiable;
import jp.co.soramitsu.iroha2.model.StringValue;
import jp.co.soramitsu.iroha2.model.U32;
import jp.co.soramitsu.iroha2.model.Value;
import jp.co.soramitsu.iroha2.model.Vector;
import jp.co.soramitsu.iroha2.model.expression.Raw;
import jp.co.soramitsu.iroha2.model.query.FindAccountById;
import jp.co.soramitsu.iroha2.model.query.FindAccountsByDomainName;
import jp.co.soramitsu.iroha2.model.query.FindAccountsByName;
import jp.co.soramitsu.iroha2.model.query.FindAllAccounts;
import jp.co.soramitsu.iroha2.model.query.FindAllAssets;
import jp.co.soramitsu.iroha2.model.query.FindAllAssetsDefinitions;
import jp.co.soramitsu.iroha2.model.query.FindAllDomains;
import jp.co.soramitsu.iroha2.model.query.FindAllParameters;
import jp.co.soramitsu.iroha2.model.query.FindAllPeers;
import jp.co.soramitsu.iroha2.model.query.FindAssetById;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByAccountId;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByAccountIdAndAssetDefinitionId;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByAssetDefinitionId;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByDomainName;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByDomainNameAndAssetDefinitionId;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByName;
import jp.co.soramitsu.iroha2.model.query.FindDomainByName;
import jp.co.soramitsu.iroha2.model.query.Query;
import jp.co.soramitsu.iroha2.model.query.QueryResult;
import jp.co.soramitsu.iroha2.model.query.SignedQueryRequest;
import jp.co.soramitsu.iroha2.model.query.V1SignedQueryRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(5)
public class QueryTest {

  static String privateKeyHex = "de757bcb79f4c63e8fa0795edc26f86dfdba189b846e903d0b732bb644607720";
  static KeyPair keyPair = Utils.EdDSAKeyPairFromHexPrivateKey(privateKeyHex);
  static Iroha2Api api = new Iroha2Api("localhost:8080");
  static byte[] peerPublicKey = Utils.getActualPublicKey(keyPair.getPublic().getEncoded());

  @Test
  public void queryFindAllAccounts() {
    Assertions.assertDoesNotThrow(() -> {
      Query query = new FindAllAccounts();
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void queryFindAccountById() {
    Assertions.assertDoesNotThrow(() -> {
      AccountId accountId = new AccountId("alice", "wonderland");
      Query query = new FindAccountById(new Raw(new Value(new Id(accountId))));
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions
          .assertEquals("alice",
              ((Account) ((Identifiable) res.getValue().getValue()).getValue()).getId().getName());
      Assertions
          .assertEquals("wonderland",
              ((Account) ((Identifiable) res.getValue().getValue()).getValue()).getId()
                  .getDomainName());
    });
  }

  @Test
  public void queryFindAccountsByName() {
    Assertions.assertDoesNotThrow(() -> {
      Query query = new FindAccountsByName(new Raw(new Value(new StringValue("alice"))));
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void queryFindAccountsByDomainName() {
    Assertions.assertDoesNotThrow(() -> {
      Query query = new FindAccountsByDomainName(new Raw(new Value(new StringValue("wonderland"))));
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAllAssets() {
    Assertions.assertDoesNotThrow(() -> {
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAllAssets())
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAllAssetsDefinitions() {
    Assertions.assertDoesNotThrow(() -> {
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAllAssetsDefinitions())
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAssetById() {
    Assertions.assertDoesNotThrow(() -> {
      AccountId accountId = new AccountId("alice", "wonderland");
      DefinitionId definitionId = new DefinitionId("rose", "wonderland");
      AssetId assetId = new AssetId(definitionId, accountId);

      Query query = new FindAssetById(new Raw(new Value(new Id(assetId))));

      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions
          .assertEquals("rose",
              ((Asset) ((Identifiable) res.getValue().getValue()).getValue()).getId()
                  .getDefinitionId()
                  .getName());
      Assertions
          .assertEquals("wonderland",
              ((Asset) ((Identifiable) res.getValue().getValue()).getValue()).getId()
                  .getDefinitionId()
                  .getDomainName());
    });
  }

  @Test
  public void requestFindAssetsByName() {
    Assertions.assertDoesNotThrow(() -> {

      Query query = new FindAssetsByName(new Raw(new Value(new StringValue("rose"))));

      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAssetsByAccountId() {
    Assertions.assertDoesNotThrow(() -> {
      AccountId accountId = new AccountId("alice", "wonderland");
      Query query = new FindAssetsByAccountId(new Raw(new Value(new Id(accountId))));
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAssetsByAssetDefinitionId() {
    Assertions.assertDoesNotThrow(() -> {
      DefinitionId assetDefinitionId = new DefinitionId("rose", "wonderland");
      Query query = new FindAssetsByAssetDefinitionId(
          new Raw(new Value(new Id(assetDefinitionId))));
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAssetsByDomainName() {
    Assertions.assertDoesNotThrow(() -> {
      Query query = new FindAssetsByDomainName(new Raw(new Value(new StringValue("wonderland"))));

      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAssetsByAccountIdAndAssetDefinitionId() {
    Assertions.assertDoesNotThrow(() -> {
      AccountId accountId = new AccountId("alice", "wonderland");
      DefinitionId assetDefinitionId = new DefinitionId("rose", "wonderland");
      Query query = new FindAssetsByAccountIdAndAssetDefinitionId(
          new Raw(new Value(new Id(accountId))),
          new Raw(new Value(new Id(assetDefinitionId)))
      );

      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAssetsByDomainNameAndAssetDefinitionId() {
    Assertions.assertDoesNotThrow(() -> {
      DefinitionId assetDefinitionId = new DefinitionId("rose", "wonderland");

      Query query = new FindAssetsByDomainNameAndAssetDefinitionId(
          new Raw(new Value(new StringValue("wonderland"))),
          new Raw(new Value(new Id(assetDefinitionId)))
      );

      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  /**
   * Test get balance query for genesis. alice@wonderland has 13 rose#wonderland
   */
  @Test
  public void requestFindAssetQuantityById() {
    Assertions.assertDoesNotThrow(() -> {
      SignedQueryRequest request = new QueryBuilder()
          .findAssetQuantityById("rose", "wonderland", "alice", "wonderland")
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));

      Value value = res.getValue();
      Assertions.assertTrue(value.getValue() instanceof U32);
      Assertions.assertEquals(13, ((U32) value.getValue()).getValue());
    });
  }

  /**
   * FindAllDomains returns list of domains.
   */
  @Test
  public void requestFindAllDomains() {
    Assertions.assertDoesNotThrow(() -> {
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAllDomains())
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  /**
   * GetDomain query by name returns domain.
   */
  @Test
  public void requestFindDomainByName() {
    Assertions.assertDoesNotThrow(() -> {

      Query query = new FindDomainByName(new Raw(new Value(new StringValue("wonderland"))));

      SignedQueryRequest request = new QueryBuilder()
          .setQuery(query)
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions
          .assertEquals("wonderland",
              ((Domain) ((Identifiable) res.getValue().getValue()).getValue()).getName());
    });
  }

  /**
   * Find all peers. Returns list of Peer
   */
  @Test
  public void requestFindAllPeers() {
    Assertions.assertDoesNotThrow(() -> {
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAllPeers())
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));

      Value value = res.getValue();
      Assertions.assertTrue(value.getValue() instanceof Vector);
      Assertions.assertEquals(4, ((Vector) value.getValue()).getVector().size());
    });
  }


  /**
   * Find all parameters returns empty list of Values.
   */
  @Test
  public void requestFindAllParameters() {
    Assertions.assertDoesNotThrow(() -> {
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAllParameters())
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));

      Value value = res.getValue();
      Assertions.assertTrue(value.getValue() instanceof Vector);
      Assertions.assertTrue(((Vector) value.getValue()).getVector().isEmpty());
    });
  }

}
