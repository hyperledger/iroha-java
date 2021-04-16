package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByDomainNameAndAssetDefinitionId;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindAssetsByDomainNameAndAssetDefinitionIdReader implements
        ScaleReader<FindAssetsByDomainNameAndAssetDefinitionId> {

    private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

    @Override
    public FindAssetsByDomainNameAndAssetDefinitionId read(ScaleCodecReader reader) {
        return new FindAssetsByDomainNameAndAssetDefinitionId(
                reader.read(EXPRESSION_READER),
                reader.read(EXPRESSION_READER)
        );
    }
}
