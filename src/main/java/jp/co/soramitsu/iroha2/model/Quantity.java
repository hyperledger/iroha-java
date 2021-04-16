package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Quantity implements AssetValue {

  @NonNull
  private U32 value;

  @Override
  public int getIndex() {
    return 0;
  }
}
