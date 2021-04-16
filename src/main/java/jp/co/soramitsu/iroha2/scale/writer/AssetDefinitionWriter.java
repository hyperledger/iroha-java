package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.AssetDefinition;

import java.io.IOException;

public class AssetDefinitionWriter implements ScaleWriter<AssetDefinition> {

  private static final DefinitionIdWriter DEFINITION_ID_WRITER = new DefinitionIdWriter();

  @Override
  public void write(ScaleCodecWriter writer, AssetDefinition value) throws IOException {
    writer.write(DEFINITION_ID_WRITER, value.getId());
  }
}