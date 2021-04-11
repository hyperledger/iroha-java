package jp.co.soramitsu.iroha2.scale.writer.instruction;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.instruction.RemoveKeyValue;

import java.io.IOException;

public class RemoveKeyValueWriter  implements ScaleWriter<RemoveKeyValue>  {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, RemoveKeyValue removeKeyValue) throws IOException {
        throw new RuntimeException();
    }
}
