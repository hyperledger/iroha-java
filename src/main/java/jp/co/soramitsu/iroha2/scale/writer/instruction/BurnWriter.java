package jp.co.soramitsu.iroha2.scale.writer.instruction;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.instruction.Burn;
import jp.co.soramitsu.iroha2.scale.writer.ExpressionWriter;

import java.io.IOException;

public class BurnWriter implements ScaleWriter<Burn> {

  private static ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, Burn value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getObject());
    writer.write(EXPRESSION_WRITER, value.getDestinationId());
  }
}

