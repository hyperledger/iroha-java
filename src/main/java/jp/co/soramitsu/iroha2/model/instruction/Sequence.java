package jp.co.soramitsu.iroha2.model.instruction;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class Sequence implements Instruction {

  @NonNull
  private List<Instruction> instructions;

  public Sequence() {
    instructions = new ArrayList<>();
  }

  public Sequence(List<Instruction> instructions) {
    this.instructions = instructions;
  }

  @Override
  public int getIndex() {
    return 7;
  }
}
