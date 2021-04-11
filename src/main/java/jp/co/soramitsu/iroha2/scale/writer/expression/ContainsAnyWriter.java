package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.ContainsAny;

import java.io.IOException;

public class ContainsAnyWriter implements ScaleWriter<ContainsAny> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, ContainsAny containsAny) throws IOException {
        throw new RuntimeException();
    }
}
