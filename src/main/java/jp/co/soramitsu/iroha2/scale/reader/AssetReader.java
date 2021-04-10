package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.Asset;

public class AssetReader implements ScaleReader<Asset> {

  private static final AssetIdReader ASSET_ID_READER = new AssetIdReader();
  private static final AssetValueReader ASSET_VALUE_TYPE_READER = new AssetValueReader();

  @Override
  public Asset read(ScaleCodecReader reader) {
    return new Asset(ASSET_ID_READER.read(reader), ASSET_VALUE_TYPE_READER.read(reader));
  }
}
