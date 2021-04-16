package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByName;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

/**
 * Scale writer that writes nothing.
 */
class FindAssetsByNameWriter implements ScaleWriter<FindAssetsByName> {

  private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, FindAssetsByName value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getName());
  }
}
