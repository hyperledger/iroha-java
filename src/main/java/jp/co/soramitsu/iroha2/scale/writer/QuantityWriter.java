package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.Quantity;

public class QuantityWriter implements ScaleWriter<Quantity> {

  @Override
  public void write(ScaleCodecWriter scaleCodecWriter, Quantity quantity) throws IOException {
    scaleCodecWriter.write(new U32Writer(), quantity.getValue());

  }
}
