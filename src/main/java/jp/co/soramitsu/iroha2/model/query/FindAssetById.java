package jp.co.soramitsu.iroha2.model.query;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class FindAssetById implements Query {

    @NonNull
    private Expression id;

    @Override
    public int getIndex() {
        return 7;
    }
}
