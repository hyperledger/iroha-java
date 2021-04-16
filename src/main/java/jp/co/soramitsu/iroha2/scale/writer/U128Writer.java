package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.U128;

import java.io.IOException;

public class U128Writer implements ScaleWriter<U128> {

    @Override
    public void write(ScaleCodecWriter writer, U128 value) throws IOException {
        writer.writeUint128(value.getValue());
    }
}
