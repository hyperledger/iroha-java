package jp.co.soramitsu.iroha2.model.instruction;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
