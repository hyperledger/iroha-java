package jp.co.soramitsu.iroha2.model.instruction;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mint implements Instruction {

  @NonNull
  Expression object;
  @NonNull
  Expression destinationId;

  @Override
  public int getIndex() {
    return 2;
  }
}
