package jp.co.soramitsu.iroha2.model.instruction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair implements Instruction {

  @NonNull
  private Instruction left;
  @NonNull
  private Instruction right;

  @Override
  public int getIndex() {
    return 6;
  }
}
