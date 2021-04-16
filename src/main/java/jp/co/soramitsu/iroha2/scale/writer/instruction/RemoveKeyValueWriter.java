package jp.co.soramitsu.iroha2.scale.writer.instruction;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.instruction.RemoveKeyValue;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

import java.io.IOException;

public class RemoveKeyValueWriter implements ScaleWriter<RemoveKeyValue> {

    private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, RemoveKeyValue removeKeyValue) throws IOException {
        EXPRESSION_WRITER.write(scaleCodecWriter, removeKeyValue.getObjectId());
        EXPRESSION_WRITER.write(scaleCodecWriter, removeKeyValue.getKey());
    }
}
