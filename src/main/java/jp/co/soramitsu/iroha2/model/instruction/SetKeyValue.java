package jp.co.soramitsu.iroha2.model.instruction;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class SetKeyValue implements Instruction {

    @NonNull
    private Expression objectId;

    @NonNull
    private Expression key;

    @NonNull
    private Expression value;

    @Override
    public int getIndex() {
        return 9;
    }
}
