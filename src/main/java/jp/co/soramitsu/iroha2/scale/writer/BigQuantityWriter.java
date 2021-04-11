package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.BigQuantity;

import java.io.IOException;

public class BigQuantityWriter implements ScaleWriter<BigQuantity> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, BigQuantity bigQuantity) throws IOException {
        scaleCodecWriter.write(new U128Writer(), bigQuantity.getValue());
    }
}
