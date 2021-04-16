package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByAccountIdAndAssetDefinitionId;
import jp.co.soramitsu.iroha2.scale.reader.AccountIdReader;
import jp.co.soramitsu.iroha2.scale.reader.DefinitionIdReader;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindAssetsByAccountIdAndAssetDefinitionIdReader implements
        ScaleReader<FindAssetsByAccountIdAndAssetDefinitionId> {

    private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

    @Override
    public FindAssetsByAccountIdAndAssetDefinitionId read(ScaleCodecReader reader) {
        return new FindAssetsByAccountIdAndAssetDefinitionId(
                reader.read(EXPRESSION_READER),
                reader.read(EXPRESSION_READER)
        );
    }
}
