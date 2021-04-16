package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.ContainsAll;

import java.io.IOException;

public class ContainsAllWriter implements ScaleWriter<ContainsAll> {

    private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

    @Override
    public void write(ScaleCodecWriter writer, ContainsAll value) throws IOException {
        writer.write(EXPRESSION_WRITER, value.getCollection());
        writer.write(EXPRESSION_WRITER, value.getElements());
    }

}
