package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import jp.co.soramitsu.iroha2.model.StringValue;

public class StringValueWriter implements ScaleWriter<StringValue> {

  @Override
  public void write(ScaleCodecWriter scaleCodecWriter, StringValue stringValue) throws IOException {
    scaleCodecWriter.writeAsList(stringValue.getValue().getBytes(StandardCharsets.UTF_8));
  }
}
