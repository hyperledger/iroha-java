package jp.co.soramitsu.iroha2.scale.writer.instruction;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.instruction.SetKeyValue;

import java.io.IOException;

public class SetKeyValueWriter implements ScaleWriter<SetKeyValue> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, SetKeyValue setKeyValue) throws IOException {
        throw new RuntimeException();
    }
}
