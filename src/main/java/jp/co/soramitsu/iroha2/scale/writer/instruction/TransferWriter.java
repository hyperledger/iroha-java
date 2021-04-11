package jp.co.soramitsu.iroha2.scale.writer.instruction;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import jp.co.soramitsu.iroha2.model.instruction.Transfer;
import jp.co.soramitsu.iroha2.scale.writer.expression.ExpressionWriter;

import java.io.IOException;

public class TransferWriter implements ScaleWriter<Transfer> {

  private static ExpressionWriter EXPRESSION_WRITER = new ExpressionWriter();

  @Override
  public void write(ScaleCodecWriter writer, Transfer value) throws IOException {
    writer.write(EXPRESSION_WRITER, value.getSourceId());
    writer.write(EXPRESSION_WRITER, value.getObject());
    writer.write(EXPRESSION_WRITER, value.getDestinationId());
  }
}
