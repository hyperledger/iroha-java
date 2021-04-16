package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByDomainName;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

/**
 * Scale writer that writes nothing.
 */
class FindAssetsByDomainNameWriter implements ScaleWriter<FindAssetsByDomainName> {

  private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, FindAssetsByDomainName value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getDomainName());
  }
}
