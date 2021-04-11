package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.AssetDefinitionEntry;

import java.io.IOException;

public class AssetDefinitionEntryWriter implements ScaleWriter<AssetDefinitionEntry> {

  private static final DefinitionIdWriter DEFINITION_ID_WRITER = new DefinitionIdWriter();
  private static final AccountIdWriter ACCOUNT_ID_WRITER = new AccountIdWriter();

  @Override
  public void write(ScaleCodecWriter writer, AssetDefinitionEntry value) throws IOException {
    DEFINITION_ID_WRITER.write(writer, value.getDefinition());
    ACCOUNT_ID_WRITER.write(writer, value.getRegisteredBy());
  }
}
