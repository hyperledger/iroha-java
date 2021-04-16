package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.query.FindAccountsByDomainName;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

import java.io.IOException;

/**
 * Scale writer that writes nothing.
 */
class FindAccountsByDomainNameWriter implements ScaleWriter<FindAccountsByDomainName> {

  private static ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, FindAccountsByDomainName value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getDomainName());
  }
}
