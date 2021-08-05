package jp.co.soramitsu.iroha2.model.query;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class FindAssetsByAccountId implements Query {

  @NonNull
  private Expression accountId;

  @Override
  public int getIndex() {
    return 9;
  }
}
