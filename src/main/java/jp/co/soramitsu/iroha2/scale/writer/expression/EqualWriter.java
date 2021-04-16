package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.Equal;

import java.io.IOException;

public class EqualWriter implements ScaleWriter<Equal> {

    private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

    @Override
    public void write(ScaleCodecWriter writer, Equal value) throws IOException {
        writer.write(EXPRESSION_WRITER, value.getLeft());
        writer.write(EXPRESSION_WRITER, value.getRight());
    }

}
