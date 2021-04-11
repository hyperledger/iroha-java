package jp.co.soramitsu.iroha2.model.instruction;

import lombok.Data;

@Data
public class RemoveKeyValue implements Instruction {
    @Override
    public int getIndex() {
        return 10;
    }
}
