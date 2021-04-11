package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.query.FindDomainByName;

import java.io.IOException;

class FindDomainByNameWriter implements ScaleWriter<FindDomainByName> {

  @Override
  public void write(ScaleCodecWriter writer, FindDomainByName value) throws IOException {
    writer.writeAsList(value.getName().getBytes());
  }
}
