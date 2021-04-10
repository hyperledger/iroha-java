package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Asset implements IdentifiableBox {

  @NonNull
  private AssetId id;

  @NonNull
  AssetValue value;

  public Asset(AssetId id, AssetValue assetValue) {
    this.id = id;
    this.value = assetValue;
  }

  @Override
  public int getIndex() {
    return 2;
  }
}
