package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.Divide;

import java.io.IOException;

public class DivideWriter implements ScaleWriter<Divide> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, Divide divide) throws IOException {
        throw new RuntimeException();
    }
}
