package jp.co.soramitsu.iroha2.model.query;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class FindAccountKeyValueByIdAndKey implements Query {

  @NonNull
  private Expression id;
  @NonNull
  private Expression key;

  @Override
  public int getIndex() {
    return 2;
  }
}
