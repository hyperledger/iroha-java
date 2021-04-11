package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import jp.co.soramitsu.iroha2.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests SCALE serialization of Asset.
 */
public class AssetWriterTest extends ScaleWriterFixture {

  private byte[] scale(Asset asset) {
    return Assertions.assertDoesNotThrow(() -> {
      ByteArrayOutputStream encoded = new ByteArrayOutputStream();
      ScaleCodecWriter codec = new ScaleCodecWriter(encoded);
      codec.write(new AssetWriter(), asset);
      return encoded.toByteArray();
    });
  }

  /**
   * Compares scale serialization of Account with generated in rust one:
   * <pre>
   * {@code
   *        let account_id = AccountId::new("root", "global");
   *        let asset_id = AssetId::new(AssetDefinitionId::new("XOR", "Soramitsu"), account_id.clone());
   *        let asset = Asset::with_parameter(asset_id.clone(), "Key".to_string(), Value::U32(0), MetadataLimits::new(64, 64)).unwrap();r
   * </pre>
   */
  @Test
  public void testAsset() {
    AccountId accountId = new AccountId("root", "global");
    DefinitionId assetDefinitionId = new DefinitionId("XOR", "Soramitsu");
    AssetId assetId = new AssetId(assetDefinitionId, accountId);

    Map<String, Value> map = new HashMap<>();
    map.put("Key", new Value(new U32(0)));
    Asset asset = new Asset(assetId, new Store(new Metadata(map)));

    String expected = "[12, 88, 79, 82, 36, 83, 111, 114, 97, 109, 105, 116, 115, 117, 16, 114, 111, 111, 116, 24, 103, 108, 111, 98, 97, 108, 2, 4, 12, 75, 101, 121, 0, 0, 0, 0, 0]";
    Assertions.assertEquals(expected, bytesToJsonString(scale(asset)));
  }

}
