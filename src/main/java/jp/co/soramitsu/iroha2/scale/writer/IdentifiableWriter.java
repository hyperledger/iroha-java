package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.Identifiable;

import java.io.IOException;


public class IdentifiableWriter implements ScaleWriter<Identifiable> {

  private static final IdentifiableBoxWriter IDENTIFIABLE_BOX_WRITER = new IdentifiableBoxWriter();

  public void write(ScaleCodecWriter writer, Identifiable value) throws IOException {
    writer.write(IDENTIFIABLE_BOX_WRITER, value.getValue());
  }
}