package jp.co.soramitsu.iroha2.scale.writer.instruction;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.instruction.Unregister;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

import java.io.IOException;

public class UnregisterWriter implements ScaleWriter<Unregister> {

  private static final ExpressionWriter EXPRESSION_WRITER_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, Unregister value) throws IOException {
    writer.write(EXPRESSION_WRITER_WRITER, value.getObject());
  }
}
