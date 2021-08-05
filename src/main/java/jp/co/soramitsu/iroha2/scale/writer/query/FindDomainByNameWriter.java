package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.query.FindDomainByName;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

class FindDomainByNameWriter implements ScaleWriter<FindDomainByName> {

  private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, FindDomainByName value) throws IOException {
    EXPRESSION_WRITER.write(writer, value.getName());
  }
}
