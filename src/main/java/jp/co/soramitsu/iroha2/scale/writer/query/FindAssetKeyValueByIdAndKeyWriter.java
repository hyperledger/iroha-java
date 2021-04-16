package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.query.FindAssetKeyValueByIdAndKey;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

public class FindAssetKeyValueByIdAndKeyWriter implements ScaleWriter<FindAssetKeyValueByIdAndKey> {

  private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter scaleCodecWriter,
      FindAssetKeyValueByIdAndKey findAssetKeyValueByIdAndKey) throws IOException {
    EXPRESSION_WRITER.write(scaleCodecWriter, findAssetKeyValueByIdAndKey.getId());
    EXPRESSION_WRITER.write(scaleCodecWriter, findAssetKeyValueByIdAndKey.getKey());
  }
}