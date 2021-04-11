package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.StringValue;

import java.io.IOException;

public class StringValueWriter implements ScaleWriter<StringValue> {
    @Override
    public void write(ScaleCodecWriter scaleCodecWriter, StringValue stringValue) throws IOException {
       throw new RuntimeException();
    }
}
