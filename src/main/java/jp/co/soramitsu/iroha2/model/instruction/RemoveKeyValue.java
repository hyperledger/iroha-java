package jp.co.soramitsu.iroha2.model.instruction;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class RemoveKeyValue implements Instruction {

    @NonNull
    private Expression objectId;

    @NonNull
    private Expression key;

    @Override
    public int getIndex() {
        return 10;
    }
}
