package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.If;

import java.io.IOException;

public class IfWriter implements ScaleWriter<If> {

    private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

    @Override
    public void write(ScaleCodecWriter writer, If value) throws IOException {
        writer.write(EXPRESSION_WRITER, value.getCondition());
        writer.write(EXPRESSION_WRITER, value.getThenExpression());
        writer.write(EXPRESSION_WRITER, value.getElseExpression());
    }

}
