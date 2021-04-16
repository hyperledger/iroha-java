package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByDomainNameAndAssetDefinitionId;
import jp.co.soramitsu.iroha2.scale.writer.DefinitionIdWriter;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

import java.io.IOException;

class FindAssetsByDomainNameAndAssetDefinitionIdWriter implements
        ScaleWriter<FindAssetsByDomainNameAndAssetDefinitionId> {

    private static ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

    @Override
    public void write(ScaleCodecWriter writer, FindAssetsByDomainNameAndAssetDefinitionId value)
            throws IOException {
        EXPRESSION_WRITER.write(writer, value.getDomainName());
        EXPRESSION_WRITER.write(writer, value.getAssetDefinitionId());
    }
}
