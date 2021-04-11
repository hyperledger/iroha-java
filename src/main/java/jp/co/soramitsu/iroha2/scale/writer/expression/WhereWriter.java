package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.expression.Expression;
import jp.co.soramitsu.iroha2.model.expression.Where;
import jp.co.soramitsu.iroha2.scale.writer.MapWriter;
import jp.co.soramitsu.iroha2.scale.writer.StringWriter;

import java.io.IOException;

public class WhereWriter implements ScaleWriter<Where> {

  private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();
  private static final MapWriter<String, Expression> VALUES_WRITER = new MapWriter<>(
      new StringWriter(), EXPRESSION_WRITER);

  @Override
  public void write(ScaleCodecWriter writer, Where value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getExpression());
    writer.write(VALUES_WRITER, value.getValues());
  }

}
