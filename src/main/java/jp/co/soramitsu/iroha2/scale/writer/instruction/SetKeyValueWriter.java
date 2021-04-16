package jp.co.soramitsu.iroha2.scale.writer.instruction;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.instruction.SetKeyValue;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

import java.io.IOException;

public class SetKeyValueWriter implements ScaleWriter<SetKeyValue> {

    private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, SetKeyValue setKeyValue) throws IOException {
        EXPRESSION_WRITER.write(scaleCodecWriter, setKeyValue.getObjectId());
        EXPRESSION_WRITER.write(scaleCodecWriter, setKeyValue.getKey());
        EXPRESSION_WRITER.write(scaleCodecWriter, setKeyValue.getValue());
    }
}
