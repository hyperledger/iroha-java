package jp.co.soramitsu.iroha2.model.expression;

import jp.co.soramitsu.iroha2.model.Value;
import lombok.Data;
import lombok.NonNull;

@Data
public class Raw implements Expression {

    @NonNull
    private Value value;

    @Override
    public int getIndex() {
        return 13;
    }
}
