package jp.co.soramitsu.iroha2.model.query;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

// 1st index in tag union
@Data
public class FindAccountById implements Query {

  @NonNull
  private Expression id;

  @Override
  public int getIndex() {
    return 1;
  }
}
