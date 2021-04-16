package jp.co.soramitsu.iroha2.scale.writer.query;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.query.FindAssetQuantityById;
import jp.co.soramitsu.iroha2.scale.writer.AssetIdWriter;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

import java.io.IOException;

class FindAssetQuantityByIdWriter implements ScaleWriter<FindAssetQuantityById> {

  private static ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, FindAssetQuantityById value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getAssetId());
  }
}
