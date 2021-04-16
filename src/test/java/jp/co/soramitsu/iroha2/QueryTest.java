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
import jp.co.soramitsu.iroha2.model.PeerId;
import jp.co.soramitsu.iroha2.model.PublicKey;
import jp.co.soramitsu.iroha2.model.U32;
import jp.co.soramitsu.iroha2.model.Value;
import jp.co.soramitsu.iroha2.model.Vector;
import jp.co.soramitsu.iroha2.model.expression.Raw;
import jp.co.soramitsu.iroha2.model.query.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

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
      Query query = new FindAccountsByName("alice");
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
      Query query = new FindAccountsByDomainName("wonderland");
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

      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAssetById(assetId))
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
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAssetsByName("rose"))
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAssetsByAccountId() {
    Assertions.assertDoesNotThrow(() -> {
      AccountId accountId = new AccountId("alice", "wonderland");
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAssetsByAccountId(accountId))
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAssetsByAssetDefinitionId() {
    Assertions.assertDoesNotThrow(() -> {
      DefinitionId assetDefinitionId = new DefinitionId("rose", "wonderland");
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAssetsByAssetDefinitionId(assetDefinitionId))
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAssetsByDomainName() {
    Assertions.assertDoesNotThrow(() -> {
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAssetsByDomainName("wonderland"))
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
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAssetsByAccountIdAndAssetDefinitionId(accountId, assetDefinitionId))
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions.assertFalse(((Vector) res.getValue().getValue()).getVector().isEmpty());
    });
  }

  @Test
  public void requestFindAssetsByDomainNameAndAssetDefinitionId() {
    Assertions.assertDoesNotThrow(() -> {
      DefinitionId assetDefinitionId = new DefinitionId("rose", "wonderland");
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindAssetsByDomainNameAndAssetDefinitionId("wonderland", assetDefinitionId))
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
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindDomainByName("global"))
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));
      Assertions
          .assertEquals("global",
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
   * Find peer by Id. Returns the same Peer
   */
  @Test
  public void requestFindPeerById() {
    Assertions.assertDoesNotThrow(() -> {
      PublicKey publicKey = new PublicKey("ed25519", peerPublicKey);
      PeerId peerIdToFind = new PeerId("iroha:1337", publicKey);
      SignedQueryRequest request = new QueryBuilder()
          .setQuery(new FindPeerById(peerIdToFind))
          .sign(keyPair);

      QueryResult res = api.query(new V1SignedQueryRequest(request));

      Value value = res.getValue();
      Assertions.assertTrue(value.getValue() instanceof Id);
      Assertions.assertTrue(((Id) value.getValue()).getId() instanceof PeerId);
      PeerId peerId = (PeerId) ((Id) value.getValue()).getId();
      Assertions.assertEquals("iroha:1337", peerId.getAddress());
      Assertions.assertEquals("ed25519", peerId.getPublicKey().getDigestFunction());
      Assertions.assertArrayEquals(peerPublicKey, peerId.getPublicKey().getPayload());
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