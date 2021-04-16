package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.ContextValue;

import java.io.IOException;

public class ContextValueWriter implements ScaleWriter<ContextValue> {

    @Override
    public void write(ScaleCodecWriter writer, ContextValue value) throws IOException {
        writer.writeAsList(value.getValueName().getBytes());
    }

}
