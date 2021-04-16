package jp.co.soramitsu.iroha2.model.query;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class FindAssetQuantityById implements Query {

  @NonNull
  private Expression assetId;

  @Override
  public int getIndex() {
    return 14;
  }
}
