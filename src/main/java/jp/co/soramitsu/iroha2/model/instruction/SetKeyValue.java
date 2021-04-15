package jp.co.soramitsu.iroha2.model.instruction;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
