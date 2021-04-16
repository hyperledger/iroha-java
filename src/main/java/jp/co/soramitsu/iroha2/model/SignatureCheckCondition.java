package jp.co.soramitsu.iroha2.model;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class SignatureCheckCondition implements ValueBox {

  @NonNull
  private Expression value;

  @Override
  public int getIndex() {
    return 8;
  }
}
