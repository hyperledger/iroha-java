package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.DomainName;

import java.io.IOException;

public class DomainNameWriter implements ScaleWriter<DomainName> {

  @Override
  public void write(ScaleCodecWriter writer, DomainName value) throws IOException {
    writer.writeAsList(value.getName().getBytes());
  }

}
