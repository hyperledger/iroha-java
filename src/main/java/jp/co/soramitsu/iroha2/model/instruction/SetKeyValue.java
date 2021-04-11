package jp.co.soramitsu.iroha2.model.instruction;

import lombok.Data;

@Data
public class SetKeyValue implements Instruction {
    @Override
    public int getIndex() {
        return 9;
    }
}
