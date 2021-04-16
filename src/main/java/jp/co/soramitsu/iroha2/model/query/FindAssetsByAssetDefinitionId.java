package jp.co.soramitsu.iroha2.model.query;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.Data;
import lombok.NonNull;

@Data
public class FindAssetsByAssetDefinitionId implements Query {

    @NonNull
    private Expression assetDefinitionId;

    @Override
    public int getIndex() {
        return 10;
    }
}
