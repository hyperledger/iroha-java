package jp.co.soramitsu.iroha2.model.instruction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fail implements Instruction {

  @NonNull
  private String message;

  @Override
  public int getIndex() {
    return 8;
  }
}
