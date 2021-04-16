package jp.co.soramitsu.iroha2;

import jp.co.soramitsu.iroha2.model.*;
import jp.co.soramitsu.iroha2.model.expression.Raw;
import jp.co.soramitsu.iroha2.model.query.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.security.KeyPair;

@Timeout(5)
public class QueryTest {

  // root account keys:
  // priv: 9ac47abf59b356e0bd7dcbbbb4dec080e302156a48ca907e47cb6aea1d32719e
  // pub:  7233bfc89dcbd68c19fde6ce6158225298ec1131b6a130d1aeb454c1ab5183c0
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
      Query query = new FindAssetsByAssetDefinitionId(new Raw(new Value(new Id(assetDefinitionId))));
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
