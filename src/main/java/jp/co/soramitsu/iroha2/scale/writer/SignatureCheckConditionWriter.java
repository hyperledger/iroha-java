package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.SignatureCheckCondition;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

import java.io.IOException;

public class SignatureCheckConditionWriter implements ScaleWriter<SignatureCheckCondition> {

    private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

    @Override
    public void write(ScaleCodecWriter writer, SignatureCheckCondition signatureCheckCondition) throws IOException {
        EXPRESSION_WRITER.write(writer, signatureCheckCondition.getValue());
    }
}
