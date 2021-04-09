package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import io.emeraldpay.polkaj.scale.reader.UnionReader;
import jp.co.soramitsu.iroha2.model.AssetValueType;
import jp.co.soramitsu.iroha2.model.IdentifiableBox;

public class AssetValueTypeReader implements ScaleReader<AssetValueType> {

  private static final UnionReader<AssetValueType> ASSET_VALUE_TYPE_UNION_READER = new UnionReader<>(
          new QuantityReader(), // 0 Quantity
          new BigQuantityReader(), // 1 BigQuantity
          new StoreReader() // 2 Store
  );

  @Override
  public AssetValueType read(ScaleCodecReader reader) {
    return reader.read(ASSET_VALUE_TYPE_UNION_READER).getValue();
  }
}
