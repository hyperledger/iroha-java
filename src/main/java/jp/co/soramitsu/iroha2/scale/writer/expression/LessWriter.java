package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.expression.Less;

public class LessWriter implements ScaleWriter<Less> {

  private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, Less value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getLeft());
    writer.write(EXPRESSION_WRITER, value.getRight());
  }

}
