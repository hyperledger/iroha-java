package jp.co.soramitsu.iroha2.scale.reader;

import io.emeraldpay.polkaj.scale.ScaleCodecReader;
import io.emeraldpay.polkaj.scale.ScaleReader;
import jp.co.soramitsu.iroha2.model.AssetDefinition;

public class AssetDefinitionReader implements ScaleReader<AssetDefinition> {

  private static final DefinitionIdReader DEFINITION_ID_READER = new DefinitionIdReader();
  private static final AssetValueReader ASSET_VALUE_TYPE_READER = new AssetValueReader();

  @Override
  public AssetDefinition read(ScaleCodecReader reader) {
    return new AssetDefinition(reader.read(ASSET_VALUE_TYPE_READER), reader.read(DEFINITION_ID_READER));
  }
}
