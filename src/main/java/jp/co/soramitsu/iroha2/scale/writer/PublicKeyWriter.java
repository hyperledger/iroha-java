package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.PublicKey;

import java.io.IOException;

public class PublicKeyWriter implements ScaleWriter<PublicKey> {

  @Override
  public void write(ScaleCodecWriter writer, PublicKey value) throws IOException {
    writer.writeAsList(value.getDigestFunction().getBytes());
    writer.writeAsList(value.getPayload());
  }
}
