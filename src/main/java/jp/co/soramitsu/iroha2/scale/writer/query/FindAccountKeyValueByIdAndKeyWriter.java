package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.query.FindAccountKeyValueByIdAndKey;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

import java.io.IOException;

public class FindAccountKeyValueByIdAndKeyWriter implements ScaleWriter<FindAccountKeyValueByIdAndKey> {

    private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, FindAccountKeyValueByIdAndKey findAccountKeyValueByIdAndKey) throws IOException {
        EXPRESSION_WRITER.write(scaleCodecWriter, findAccountKeyValueByIdAndKey.getId());
        EXPRESSION_WRITER.write(scaleCodecWriter, findAccountKeyValueByIdAndKey.getKey());
    }
}
