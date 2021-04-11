package jp.co.soramitsu.iroha2.model.instruction;

import jp.co.soramitsu.iroha2.model.Id;
import jp.co.soramitsu.iroha2.model.Identifiable;
import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;

@Data
public class SetKeyValue implements Instruction {

    private Expression objectId;

    private Expression key;

    private Expression value;

    @Override
    public int getIndex() {
        return 9;
    }
}
