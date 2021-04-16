package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindAssetQuantityById;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindAssetQuantityByIdReader implements ScaleReader<FindAssetQuantityById> {

    private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

    @Override
    public FindAssetQuantityById read(ScaleCodecReader reader) {
        return new FindAssetQuantityById(reader.read(EXPRESSION_READER));
    }
}
