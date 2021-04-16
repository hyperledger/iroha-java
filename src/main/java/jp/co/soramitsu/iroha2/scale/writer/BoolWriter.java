package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.Bool;

public class BoolWriter implements ScaleWriter<Bool> {

  @Override
  public void write(ScaleCodecWriter writer, Bool value) throws IOException {
    writer.writeByte(value.getValue() ? 1 : 0);
  }
}
