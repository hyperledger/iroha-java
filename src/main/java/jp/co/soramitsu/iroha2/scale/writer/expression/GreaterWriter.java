package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.Greater;

import java.io.IOException;

public class GreaterWriter implements ScaleWriter<Greater> {

  private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, Greater value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getLeft());
    writer.write(EXPRESSION_WRITER, value.getRight());
  }

}
