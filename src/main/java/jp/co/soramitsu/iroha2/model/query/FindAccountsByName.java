package jp.co.soramitsu.iroha2.model.query;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class FindAccountsByName implements Query {

  @NonNull
  private Expression name;

  @Override
  public int getIndex() {
    return 3;
  }
}
