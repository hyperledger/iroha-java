package jp.co.soramitsu.iroha2.scale.reader.query;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.query.FindDomainByName;
import jp.co.soramitsu.iroha2.scale.reader.ExpressionReader;

public class FindDomainByNameReader implements ScaleReader<FindDomainByName> {

    private static final ExpressionReader EXPRESSION_READER = new ExpressionReader();

    @Override
    public FindDomainByName read(ScaleCodecReader reader) {
        return new FindDomainByName(EXPRESSION_READER.read(reader));
    }
}
