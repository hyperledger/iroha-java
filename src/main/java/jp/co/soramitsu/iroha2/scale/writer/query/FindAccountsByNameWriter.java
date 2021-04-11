package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.query.FindAccountsByName;

import java.io.IOException;

/**
 * Scale writer that writes nothing.
 */
class FindAccountsByNameWriter implements ScaleWriter<FindAccountsByName> {

  @Override
  public void write(ScaleCodecWriter writer, FindAccountsByName value) throws IOException {
    writer.writeAsList(value.getName().getBytes());
  }
}
