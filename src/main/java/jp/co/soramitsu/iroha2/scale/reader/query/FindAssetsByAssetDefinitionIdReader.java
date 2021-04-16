package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByAssetDefinitionId;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindAssetsByAssetDefinitionIdReader implements
        ScaleReader<FindAssetsByAssetDefinitionId> {

    private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

    @Override
    public FindAssetsByAssetDefinitionId read(ScaleCodecReader reader) {
        return new FindAssetsByAssetDefinitionId(reader.read(EXPRESSION_READER));
    }
}
