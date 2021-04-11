package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.Mod;

import java.io.IOException;

public class ModWriter implements ScaleWriter<Mod> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, Mod mod) throws IOException {
       throw new RuntimeException();
    }
}
