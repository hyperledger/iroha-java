package jp.co.soramitsu.iroha2.scale.writer.expression;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.expression.ContainsAny;
import lombok.Data;

@Data
public class ContainsAnyWriter implements ScaleWriter<ContainsAny> {

  private static final ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, ContainsAny value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getCollection());
    writer.write(EXPRESSION_WRITER, value.getElements());
  }
}
