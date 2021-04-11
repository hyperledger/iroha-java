package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.Multiply;

import java.io.IOException;

public class MultiplyWriter implements ScaleWriter<Multiply> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, Multiply multiply) throws IOException {
        throw new RuntimeException();
    }
}
