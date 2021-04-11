package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.Raw;
import jp.co.soramitsu.iroha2.scale.writer.ValueWriter;

import java.io.IOException;

public class RawWriter implements ScaleWriter<Raw> {

  private static final ValueWriter VALUE_WRITER = new ValueWriter();

  public void write(ScaleCodecWriter writer, Raw value) throws IOException {
    writer.write(VALUE_WRITER, value.getValue());
  }

}
