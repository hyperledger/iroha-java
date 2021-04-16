package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByAccountId;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindAssetsByAccountIdReader implements ScaleReader<FindAssetsByAccountId> {

    private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

    @Override
    public FindAssetsByAccountId read(ScaleCodecReader reader) {
        return new FindAssetsByAccountId(reader.read(EXPRESSION_READER));
    }
}
