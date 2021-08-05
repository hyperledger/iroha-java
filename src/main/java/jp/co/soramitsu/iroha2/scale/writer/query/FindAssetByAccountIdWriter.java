package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.query.FindAssetsByAccountId;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

class FindAssetByAccountIdWriter implements ScaleWriter<FindAssetsByAccountId> {

  private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, FindAssetsByAccountId value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getAccountId());
  }
}
