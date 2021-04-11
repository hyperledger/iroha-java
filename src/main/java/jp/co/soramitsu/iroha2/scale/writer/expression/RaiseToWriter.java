package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.RaiseTo;

import java.io.IOException;

public class RaiseToWriter implements ScaleWriter<RaiseTo> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, RaiseTo raiseTo) throws IOException {
        throw new RuntimeException();
    }
}
