package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.Metadata;
import jp.co.soramitsu.iroha2.model.Value;

public class MetadataWriter implements ScaleWriter<Metadata> {

  private static final MapWriter<String, Value> MAP_WRITER = new MapWriter<>(new StringWriter(),
      new ValueWriter());

  @Override
  public void write(ScaleCodecWriter scaleCodecWriter, Metadata metadata) throws IOException {
    MAP_WRITER.write(scaleCodecWriter, metadata.getMap());
  }
}
