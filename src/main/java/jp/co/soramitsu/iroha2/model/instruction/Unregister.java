package jp.co.soramitsu.iroha2.model.instruction;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class Unregister implements Instruction {

  @NonNull
  private Expression object; // EvaluatesTo<IdentifiableBox>

  @Override
  public int getIndex() {
    return 1;
  }
}
