package jp.co.soramitsu.iroha2.model.instruction;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class Burn implements Instruction {

  @NonNull
  Expression object;
  @NonNull
  Expression destinationId;

  @Override
  public int getIndex() {
    return 3;
  }
}
