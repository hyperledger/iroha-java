package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.Asset;

import java.io.IOException;

/**
 * Scale writer that writes nothing.
 */
public class AssetWriter implements ScaleWriter<Asset> {

  private static final AssetIdWriter ASSET_ID_WRITER = new AssetIdWriter();
  private static final U32Writer U_32_WRITER = new U32Writer();
  private static final U128Writer U_128_WRITER = new U128Writer();
  private static final StringWriter STRING_WRITER = new StringWriter();
  private static final AssetValueWriter ASSET_VALUE_TYPE_WRITER = new AssetValueWriter();
  private static final MapWriter<String, String> MAP_WRITER = new MapWriter<>(STRING_WRITER,
      STRING_WRITER);

  @Override
  public void write(ScaleCodecWriter writer, Asset value) throws IOException {
    ASSET_ID_WRITER.write(writer, value.getId());
    ASSET_VALUE_TYPE_WRITER.write(writer, value.getValue());
  }
}
